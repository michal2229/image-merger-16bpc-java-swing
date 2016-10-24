/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OknoGlowne;

import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Michał Bokiniec
 */
public class Srednia extends Przetwarzanie{
    public Srednia(List<String> fileName, String directoryName) throws IOException { 
        wprowadzanieDanych(fileName, directoryName); // przetwarzanie wstepne
    } // konstruktor, odpala wprowadzanie danych
    
    @Override
    public Void doInBackground() throws IOException{// ilosc wszystkich plikow
        System.out.println("Przetwarzanie: średnia..."); // info
        liczenieSumy(tablicaRobocza, rasterDoZapisu);              
        
        System.out.println("Rozjasnianie obrazu wynikowego..."); // info
        przetwarzanieTablicyZapisRastru(tablicaRobocza, rasterDoZapisu, 256, totalFiles);
        zapisywanie("srednia", rasterDoZapisu); // zapis pliku
        
        
        System.out.println("Przetwarzanie: szumy..."); // info
        int tablicaNaSzumy[][][] = new int[szerokoscObrazu][wysokoscObrazu][3]; // tworze nowa zmienna 
        WritableRaster rasterDoSzumow = Raster.createBandedRaster(DataBuffer.TYPE_USHORT, szerokoscObrazu, wysokoscObrazu, 3, null); // tworze nowa zmienna 
        liczenieOdchyleniaStandardowego(tablicaNaSzumy, rasterDoSzumow, tablicaRobocza);
        
        System.out.println("Rozjasnianie obrazu szumów..."); // info
        przetwarzanieTablicyZapisRastru(tablicaNaSzumy, rasterDoSzumow, 256, 1);
        zapisywanie("szumy", rasterDoSzumow); // zapis pliku
        return null;
    }
    
    /**
     *
     * @param temp
     * @param destination
     * @param average
     */
    public void liczenieOdchyleniaStandardowego(int[][][] temp, WritableRaster destination, int[][][] average){
        for (int i = 0; i < totalFiles; i++) { // dodawanie
            plikPrzetwarzany = new File(fileName.get(i));
            
            try {
                image=ImageIO.read(plikPrzetwarzany);
            } catch (IOException ex) {
                Logger.getLogger(Srednia.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            raster = image.getRaster();
            
            for (int szer = 0; szer < szerokoscObrazu; szer++) { // skanowanie szerokosci
                for (int wys = 0; wys < wysokoscObrazu; wys++) { // skanowanie wysokosci
                    aktualnyPiksel = raster.getPixel(szer, wys, aktualnyPiksel);
                    temp[szer][wys][0] += (aktualnyPiksel[0] - average[szer][wys][0]/256)*(aktualnyPiksel[0] - average[szer][wys][0]/256); 
                    temp[szer][wys][1] += (aktualnyPiksel[1] - average[szer][wys][1]/256)*(aktualnyPiksel[1] - average[szer][wys][1]/256); 
                    temp[szer][wys][2] += (aktualnyPiksel[2] - average[szer][wys][2]/256)*(aktualnyPiksel[2] - average[szer][wys][2]/256); 
                }
            }
            
            setProgress(50 + (((i+1)*50/totalFiles)));
            System.out.print((i+1)+" z " + totalFiles + " (" + (i+1)*100.0/totalFiles + "%) obrazów przetworzonych: " + fileName.get(i) + " (" + raster.getWidth() + "x" + raster.getHeight() + ") "+ temp[szerokoscObrazu/2][wysokoscObrazu/2][1]+" \n");
        }
        
        for (int i = 0; i < totalFiles; i++) { // modyfikowanie pikseli, wyrzucone z poprzedniego fora dla przyspieszenia programu
             for (int szer = 0; szer < szerokoscObrazu; szer++) { // skanowanie szerokosci
                for (int wys = 0; wys < wysokoscObrazu; wys++) { // skanowanie wysokosci
                    if (i == totalFiles - 1) {
                        temp[szer][wys][0] = (int) (Math.sqrt(temp[szer][wys][0]/(totalFiles))); // to wykonywane jest dla KAZDEGO piksela
                        temp[szer][wys][1] = (int) (Math.sqrt(temp[szer][wys][1]/(totalFiles)));
                        temp[szer][wys][2] = (int) (Math.sqrt(temp[szer][wys][2]/(totalFiles)));                               
                    }
                }
            }
        }
    } // tu liczone sa szumy
}
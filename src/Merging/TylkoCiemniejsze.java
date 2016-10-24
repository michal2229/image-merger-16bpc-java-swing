/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OknoGlowne;

import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author Michał Bokiniec
 */
public class TylkoCiemniejsze extends Przetwarzanie{
    public TylkoCiemniejsze(List<String> fileName, String directoryName) throws IOException{
        wprowadzanieDanych(fileName, directoryName); // przetwarzanie wstepne
    } // konstruktor, odpala wprowadzanie danych
    
    @Override
    public Void doInBackground() throws IOException{// ilosc wszystkich plikow
        System.out.println("Przetwarzanie: tylko ciemniejsze..."); // info
        liczenieTylkoCiemniejszych(tablicaRobocza, rasterDoZapisu); 
        
        System.out.println("Rozjasnianie obrazu wynikowego..."); // info
        przetwarzanieTablicyZapisRastru(tablicaRobocza, rasterDoZapisu, 256, 1);
        
        zapisywanie("tylko_ciemniejsze", rasterDoZapisu); // zapis pliku
        return null;
    }
    
    public void liczenieTylkoCiemniejszych(int[][][] temp, WritableRaster destination) throws IOException{
        for (int szer = 0; szer < szerokoscObrazu; szer++) { // wypelnianie kolorem bialym
            for (int wys = 0; wys < wysokoscObrazu; wys++) { // skanowanie wysokosci
                temp[szer][wys][0] = 255;
                temp[szer][wys][1] = 255;
                temp[szer][wys][2] = 255;
            }
        }
                    
        for (int i = 0; i < totalFiles; i++) { // dodawanie
            plikPrzetwarzany = new File(fileName.get(i));
            image=ImageIO.read(plikPrzetwarzany);
            if ((image.getWidth() == szerokoscObrazu) && (image.getHeight() == wysokoscObrazu)){
                raster = image.getRaster();
                for (int szer = 0; szer < szerokoscObrazu; szer++) { // skanowanie szerokosci
                    for (int wys = 0; wys < wysokoscObrazu; wys++) { // skanowanie wysokosci
                        aktualnyPiksel = raster.getPixel(szer, wys, aktualnyPiksel); // pobieranie aktualnego piksela aktualnego obrazu
                        if (temp[szer][wys][0] > aktualnyPiksel[0]) temp[szer][wys][0] = aktualnyPiksel[0];
                        if (temp[szer][wys][1] > aktualnyPiksel[1]) temp[szer][wys][1] = aktualnyPiksel[1];
                        if (temp[szer][wys][2] > aktualnyPiksel[2]) temp[szer][wys][2] = aktualnyPiksel[2];
                    }
                }
                setProgress((i+1)*100/totalFiles);
                System.out.print((i+1)+" z " + totalFiles + " (" + (i+1)*100.0/totalFiles + "%) obrazów przetworzonych: " + fileName.get(i) + " (" + raster.getWidth() + "x" + raster.getHeight() + ") "+ temp[szerokoscObrazu/2][wysokoscObrazu/2][1]+" \n");
            } else{
                System.out.println("Plik " + fileName.get(i) + " jest niepoprawny. Nie zostanie przetworzony.");
                fileName.remove(i);
                i--; totalFiles--;
            }
        }
    } // tu liczony jest obraz bedacy minimum odpowiadajacych pikseli z wybranych obrazow
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OknoGlowne;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.TIFFEncodeParam;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

/**
 *
 * @author Michał Bokiniec
 */
public class Przetwarzanie extends SwingWorker<Void, Void>{ // SwingWorker potrzebny jest do stworzenia nowego watku w tle, zeby dalo sie uzywac paska postepu
    Raster raster = null;                                                   // raster na kazdy obraz skladowy
    WritableRaster rasterDoZapisu = null;                                   // raster, ktory bedzi zapisywany do tiff
    File plikPrzetwarzany = null;                                           // aktualnie przetwarzany plik, jest tutaj, aby nie tworzyc kilkaset razy tego samego
    BufferedImage image = null;                                             // aktualnie przetwarzany obraz, jest tutaj, aby nie tworzyc kilkaset razy tego samego
    String directoryName = null;                                            // folder, w ktorym znajduja sie pliki
    List<String> fileName = null;                                           // lista plikow do przetworzenia
    int totalFiles = 0;                                                     // ilosc wszystkich plikow do przetworzenia
    int szerokoscObrazu;
    int wysokoscObrazu;
    int tablicaRobocza[][][] ;                                              // tablica, w ktorej wykonywane sa obliczenia
    int aktualnyPiksel[] = new int [3];                                     // tablica zlozona z trzech elementow (skladowe R, G, B) o wystarczajacym zakresie

    void wprowadzanieDanych(List<String> fileName_, String directoryName_) throws IOException {
        directoryName = directoryName_;
        fileName = fileName_;
        totalFiles = fileName_.size();
        plikPrzetwarzany = new File(fileName_.get(0));
        image=ImageIO.read(plikPrzetwarzany);
        raster = image.getRaster();
        szerokoscObrazu = raster.getWidth();
        wysokoscObrazu  = raster.getHeight();
        rasterDoZapisu = Raster.createBandedRaster(DataBuffer.TYPE_USHORT, szerokoscObrazu, wysokoscObrazu, 3, null); // dziala!!  
        tablicaRobocza = new int[szerokoscObrazu][wysokoscObrazu][3];     // int wytrzymuje calkiem dobrze
    }

    void zapisywanie(String typOperacji, WritableRaster rasterWynikowy) throws FileNotFoundException, IOException{
        System.out.println("Zapisywanie wyniku: "+typOperacji);                        // info

        File file = new File(directoryName + "\\Wyjscie_"+typOperacji+"_z_"+totalFiles+"_plikow_" + System.currentTimeMillis() + "_final.tiff");
        FileOutputStream plikWyjsciowy = new FileOutputStream(file);
        TIFFEncodeParam encParam = null;
        ImageEncoder enc = ImageCodec.createImageEncoder("tiff", plikWyjsciowy, encParam);
        ColorModel modelKoloru = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] {8},  false, false, Transparency.OPAQUE, DataBuffer.TYPE_DOUBLE); // zmiana na double (16bit)
        enc.encode(rasterWynikowy, modelKoloru);                            // tu kodowany jest plik
        plikWyjsciowy.close();
        System.out.println("Zapisano wynik: "+typOperacji);                      // info
    }

    public void przetwarzanieTablicyZapisRastru(int [][][] source, WritableRaster destination, int licznik, int mianownik){
        for (int i = 0; i < totalFiles; i++) { // modyfikowanie pikseli, wyrzucone z poprzedniego fora dla przyspieszenia programu
            for (int szer = 0; szer < szerokoscObrazu; szer++) { // skanowanie szerokosci
                for (int wys = 0; wys < wysokoscObrazu; wys++) { // skanowanie wysokosci
                    if (i == totalFiles - 1)
                    {
                        source[szer][wys][0] = (source[szer][wys][0]*licznik)/mianownik; // to wykonywane jest dla KAZDEGO piksela
                        source[szer][wys][1] = (source[szer][wys][1]*licznik)/mianownik;
                        source[szer][wys][2] = (source[szer][wys][2]*licznik)/mianownik;                              
                        destination.setPixel(szer, wys, source[szer][wys]); // zapisywanie danych do rastra wynikowego
                    }
                }
            }
        }
    }

    public void liczenieSumy(int[][][] temp, WritableRaster destination) throws IOException{
        for (int i = 0; i < totalFiles; i++) { // dodawanie
            plikPrzetwarzany = new File(fileName.get(i));
            image=ImageIO.read(plikPrzetwarzany);
            
            if ((image.getWidth() == szerokoscObrazu) && (image.getHeight() == wysokoscObrazu)){
                raster = image.getRaster();
                for (int szer = 0; szer < szerokoscObrazu; szer++) { // skanowanie szerokosci
                    for (int wys = 0; wys < wysokoscObrazu; wys++) { // skanowanie wysokosci
                        aktualnyPiksel = raster.getPixel(szer, wys, aktualnyPiksel);
                        temp[szer][wys][0] += aktualnyPiksel[0]; 
                        temp[szer][wys][1] += aktualnyPiksel[1]; 
                        temp[szer][wys][2] += aktualnyPiksel[2];
                    }
                }
                setProgress((i+1)*50/totalFiles);
                System.out.print((i+1)+" z " + totalFiles + " (" + (i+1)*100.0/totalFiles + "%) obrazów przetworzonych: " + fileName.get(i) + " (" + raster.getWidth() + "x" + raster.getHeight() + ") "+ temp[szerokoscObrazu/2][wysokoscObrazu/2][1]+" \n");                
            } else{
                System.out.println("Plik " + fileName.get(i) + " jest niepoprawny. Nie zostanie przetworzony.");
                fileName.remove(i);
                i--; totalFiles--;
            }
        }
    } // dodawane sa kolejne obrazy do tablicy roboczej temp
    
    @Override
    protected Void doInBackground() throws Exception
    {
        throw new UnsupportedOperationException("Nie ma takiego numeru. Jest to klasa nadrzędna, należy używać klas z niej dziedziczących."); //To change body of generated methods, choose Tools | Templates.
    }
}
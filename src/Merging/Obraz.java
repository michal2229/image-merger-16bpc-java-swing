/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// This code is licensed under LGPL license.

package Merging;

import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codecimpl.TIFFImage;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;

/**
 *
 * @author Michał Bokiniec
 */
public class Obraz {
    public int tablica[] = {0, 0, 0}; // gwozdz programu
    
    protected int szerokoscObrazu;
    protected int wysokoscObrazu;  
    
    protected ColorModel modelKoloru = null;

    protected String nazwaPliku = "obraz_wynikowy"; // do kopiowania i konstruktora
    
    
    public Obraz(int tablica[], int szerokoscObrazu, int wysokoscObrazu, ColorModel modelKoloru) {
        this.tablica = tablica;
        this.szerokoscObrazu = szerokoscObrazu;
        this.wysokoscObrazu = wysokoscObrazu;
        this.modelKoloru = modelKoloru;
    }
    
    public Obraz(int szerokoscObrazu, int wysokoscObrazu) {
        this.tablica = new int [szerokoscObrazu*wysokoscObrazu*3];
        this.szerokoscObrazu = szerokoscObrazu;
        this.wysokoscObrazu = wysokoscObrazu;
        this.modelKoloru = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB)
                            , new int[] {8},  false, false, Transparency.OPAQUE, DataBuffer.TYPE_DOUBLE); // zmiana na double (16bit);
    }
    
    public Obraz(Obraz obrazSzablon) {
        this.tablica         = new int[obrazSzablon.getSzerokoscObrazu()*obrazSzablon.getWysokoscObrazu()*3];
        this.szerokoscObrazu = obrazSzablon.getSzerokoscObrazu();
        this.wysokoscObrazu  = obrazSzablon.getWysokoscObrazu();
        this.modelKoloru     = obrazSzablon.modelKoloru;
    }
    
    public Obraz(ImageDecoder dekoderTiff) throws IOException {
        TIFFImage obrazTiff = (TIFFImage) dekoderTiff.decodeAsRenderedImage();
        szerokoscObrazu     = obrazTiff.getWidth();
        wysokoscObrazu      = obrazTiff.getHeight();
        
        tablica = new int[szerokoscObrazu * wysokoscObrazu * 3];
        modelKoloru = obrazTiff.getColorModel();
        
        Raster rasterTiff = dekoderTiff.decodeAsRaster();
        wczytajRaster(rasterTiff);
    }
    
    public Obraz(BufferedImage obrazBuforowany) {
        szerokoscObrazu = obrazBuforowany.getWidth();
        wysokoscObrazu  = obrazBuforowany.getHeight();
        
        tablica     = new int[szerokoscObrazu * wysokoscObrazu * 3];
        modelKoloru = obrazBuforowany.getColorModel();
        
        Raster rasterObrazu = obrazBuforowany.getRaster();
        wczytajRaster(rasterObrazu);
       
    }
    
    void setPiksel(int szerokoscObrazu, int wysokoscObrazu, int piksel[]) {
        tablica[(this.szerokoscObrazu*wysokoscObrazu + szerokoscObrazu)*3 + 0] = piksel[0];
        tablica[(this.szerokoscObrazu*wysokoscObrazu + szerokoscObrazu)*3 + 1] = piksel[1];
        tablica[(this.szerokoscObrazu*wysokoscObrazu + szerokoscObrazu)*3 + 2] = piksel[2];
    }
    
    void setPiksel(int szerokoscObrazu, int wysokoscObrazu, int R, int G, int B) {
        tablica[(this.szerokoscObrazu*wysokoscObrazu + szerokoscObrazu)*3 + 0] = R;
        tablica[(this.szerokoscObrazu*wysokoscObrazu + szerokoscObrazu)*3 + 1] = G;
        tablica[(this.szerokoscObrazu*wysokoscObrazu + szerokoscObrazu)*3 + 2] = B;
    }
    
    int[] getPiksel(int szerokoscObrazu, int wysokoscObrazu) {
        int piksel[] = new int[3];
        
        piksel[0] = tablica[(this.szerokoscObrazu*wysokoscObrazu + szerokoscObrazu)*3 + 0] ;
        piksel[1] = tablica[(this.szerokoscObrazu*wysokoscObrazu + szerokoscObrazu)*3 + 1];
        piksel[2] = tablica[(this.szerokoscObrazu*wysokoscObrazu + szerokoscObrazu)*3 + 2];
        
        return piksel;
    }

    public void setSzerokoscObrazu(int szerokoscObrazu) {
        this.szerokoscObrazu = szerokoscObrazu;
    }

    public int getSzerokoscObrazu() {
        return szerokoscObrazu;
    }

    public void setWysokoscObrazu(int wysokoscObrazu) {
        this.wysokoscObrazu = wysokoscObrazu;
    }

    public int getWysokoscObrazu() {
        return wysokoscObrazu;
    }

    public void setModelKoloru(ColorModel modelKoloru) {
        this.modelKoloru = modelKoloru;
    }

    public ColorModel getModelKoloru() {
        return modelKoloru;
    }

    public void setNazwaPliku(String nazwaPliku) {
        this.nazwaPliku = nazwaPliku;
    }

    public String getNazwaPliku() {
        return nazwaPliku;
    }
    
    public int[] getMinMax() {
        Obraz tablicaRobocza = this.kopiuj();
        int minMax[] = new int[2];
        
        java.util.Arrays.sort(tablicaRobocza.tablica);
        
        minMax[0] = tablicaRobocza.tablica[0];
        minMax[1] = tablicaRobocza.tablica[tablicaRobocza.tablica.length-1];
        
        return minMax;
    }
    
    public void wczytajRaster(Raster konwertowanyRaster) {
        int piksel[] = new int[3];
        
        for (int x = 0; x<szerokoscObrazu; x++)
            for (int y = 0; y<wysokoscObrazu; y++) {
                piksel = konwertowanyRaster.getPixel(x, y, piksel);
                setPiksel(x, y, piksel);
            }
    }
    
    public WritableRaster zamienTabliceNaWritableRaster() {
        WritableRaster rasterWynikowy = Raster.createBandedRaster(DataBuffer.TYPE_USHORT, szerokoscObrazu, wysokoscObrazu, 3, null);
        int piksel[];
        
        for (int x = 0; x<szerokoscObrazu; x++)
            for (int y = 0; y<wysokoscObrazu; y++) {
                piksel = getPiksel(x, y);
                
                if (piksel[0]>65535) piksel[0]=65535;
                if (piksel[1]>65535) piksel[1]=65535;
                if (piksel[2]>65535) piksel[2]=65535;
                
                rasterWynikowy.setPixel(x, y, piksel);
            }
        
        return rasterWynikowy;
    }
    
    public Obraz kopiuj() {
        Obraz kopia = new Obraz(this);
        System.arraycopy(this.tablica, 0, kopia.tablica, 0, this.tablica.length);
        return kopia;   
    }
}

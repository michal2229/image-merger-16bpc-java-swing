/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// This code is licensed under LGPL license.

package Merging;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.SeekableStream;
import com.sun.media.jai.codec.TIFFDecodeParam;
import com.sun.media.jai.codec.TIFFEncodeParam;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Michał Bokiniec
 */
public class NarzedziaIO {
    public static Obraz odczytajObraz(String nazwaPliku) throws IOException {
        File plikPrzetwarzany = new File(nazwaPliku);
        Obraz obrazOdczytany;
        
        if (plikPrzetwarzany.getName().toLowerCase().endsWith(".tif") 
                    || plikPrzetwarzany.getName().toLowerCase().endsWith(".tiff")){
            SeekableStream s = new FileSeekableStream(plikPrzetwarzany);
            TIFFDecodeParam param = null;
            ImageDecoder dekoderTiff = ImageCodec.createImageDecoder("tiff", s, param);
            
            obrazOdczytany = new Obraz(dekoderTiff);
        } else {
            BufferedImage obrazBuforowany = ImageIO.read(plikPrzetwarzany);
            
            obrazOdczytany = new Obraz(obrazBuforowany);
        }
        
        return obrazOdczytany;
    }
    
    public static void zapiszObraz(Obraz obrazZapisywany, String sciezka) throws IOException {
        File file = new File(sciezka + "\\"+obrazZapisywany.getNazwaPliku()+"_final.tiff");
        FileOutputStream plikWyjsciowy = new FileOutputStream(file);
        
        TIFFEncodeParam encParam = new TIFFEncodeParam();
        encParam.setCompression(TIFFEncodeParam.COMPRESSION_DEFLATE);

        ImageEncoder enc = ImageCodec.createImageEncoder("tiff", plikWyjsciowy, encParam);
        ColorModel modelKoloru = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), 
                                new int[] {8},  false, false, Transparency.OPAQUE, DataBuffer.TYPE_DOUBLE); // zmiana na double (16bit)
        enc.encode(obrazZapisywany.zamienTabliceNaWritableRaster(), modelKoloru);                            // tu kodowany jest plik
    }
}

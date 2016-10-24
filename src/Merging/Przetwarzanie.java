/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// This code is licensed under LGPL license.

package Merging;

import java.io.IOException;
import java.util.List;
import javax.swing.SwingWorker;

/**
 *
 * @author Michał Bokiniec i Marta Bokiniec
 */
class Przetwarzanie extends SwingWorker<Void, Void>{ // SwingWorker potrzebny jest do stworzenia nowego watku w tle, zeby dalo sie uzywac paska postepu
    List<String> plikiDoPrzetworzenia;
    String nazwaFolderu;
    String typOperacji;
                                      
    public Przetwarzanie(List<String> plikiDoPrzetworzenia, 
                        String nazwaFolderu,
                        String typOperacji) throws IOException {
        this.plikiDoPrzetworzenia = plikiDoPrzetworzenia;
        this.nazwaFolderu = nazwaFolderu;
        this.typOperacji = typOperacji;    
    }


    void upublicznijWartosc(String nazwaWartosci, String wartosc){
        firePropertyChange("postep" + nazwaWartosci, null, wartosc);
        System.out.println(wartosc);
    }
    
    void upublicznijWartosc(String nazwaWartosci, int wartosc){
        firePropertyChange("postep" + nazwaWartosci, null, wartosc);
        System.out.println(wartosc);
    }
    
    @Override
    protected Void doInBackground() throws Exception
    {
        switch (typOperacji) {
            case "_srednia"           : odchylenieStandardowe(srednia(0, 50), 50, 50);  break;
            case "_tylkoJasniejsze"   : tylkoJasniejsze(0, 100);                        break;
            case "_tylkoCiemniejsze"  : tylkoCiemniejsze(0, 100);                       break;
            case "_uwydatnienieRuchu" : uwydatnienieRuchu(0, 100);                      break;   
            case "_histogram"         : histogram(0, 100);                              break;
            default : dummy();
        }
        return null;
    }

    public Obraz srednia(int postepPoczatkowy, int wagaCzasu) throws IOException {
        NarzedziaObrazu.Histogram.reset();
        new NarzedziaObrazu.Histogram(256);
        for (int i=0; i<plikiDoPrzetworzenia.size(); i++) {
            Obraz obrazRoboczy  = NarzedziaIO.odczytajObraz(plikiDoPrzetworzenia.get(i));
            NarzedziaObrazu.Srednia.dodajKolejnyObraz(obrazRoboczy);
            NarzedziaObrazu.Histogram.dodajPikselZKolejnegoObrazu(obrazRoboczy);
            
            upublicznijWartosc(typOperacji, (postepPoczatkowy + (i+1)*wagaCzasu/(plikiDoPrzetworzenia.size())));
        }
                
        Obraz obrazWynikowy = NarzedziaObrazu.Srednia.getSrednia();
        NarzedziaObrazu.Srednia.reset();
        
        int minMax [] = obrazWynikowy.getMinMax(); 
        Obraz obrazWynikowyDoZapisu = NarzedziaObrazu.rozciagnijHistogram(obrazWynikowy, 65535);
        
        
        obrazWynikowyDoZapisu.setNazwaPliku(typOperacji + " z " + plikiDoPrzetworzenia.size() 
                                    + " plikow "  + minMax [0]/655.36 + "-" + minMax [1]/655.36);
        
        NarzedziaIO.zapiszObraz(obrazWynikowyDoZapisu, nazwaFolderu);
        
        NarzedziaObrazu.Histogram.getHistogram();
        Obraz histogramWynikowy = NarzedziaObrazu.Histogram.getHistogram(640,480, 
                                                        plikiDoPrzetworzenia.size());
        histogramWynikowy.setNazwaPliku("_histogram");
        NarzedziaIO.zapiszObraz(histogramWynikowy, nazwaFolderu);
        
        System.out.println("Koniec dodawania");

        return obrazWynikowy;
    }
    
    public void odchylenieStandardowe(Obraz srednia, int postepPoczatkowy, int wagaCzasu) throws IOException {
        for (int i=0; i<plikiDoPrzetworzenia.size(); i++) {
            Obraz obrazRoboczy  = NarzedziaIO.odczytajObraz(plikiDoPrzetworzenia.get(i));
            
            NarzedziaObrazu.OdchylenieStandardowe.dodajKolejnyObraz(srednia, obrazRoboczy);
            
            upublicznijWartosc(typOperacji, (postepPoczatkowy+(i+1)*wagaCzasu/(plikiDoPrzetworzenia.size())));
        }
        
        Obraz obrazWynikowy = NarzedziaObrazu.OdchylenieStandardowe.getOdchylenieStandardowe();
        NarzedziaObrazu.OdchylenieStandardowe.reset(); // zeby kolejne przejscie dzialalo prawidlowo
        
        int minMax [] = obrazWynikowy.getMinMax();
        obrazWynikowy = NarzedziaObrazu.rozciagnijHistogram(obrazWynikowy, 65535);
        
        obrazWynikowy.setNazwaPliku("_szumy" + " z " + plikiDoPrzetworzenia.size() 
                            + " plikow " + minMax[0]/655.36 + "-" + minMax[1]/655.36);
        NarzedziaIO.zapiszObraz(obrazWynikowy, nazwaFolderu);
        System.out.println("Koniec szumienia");
    }
        
    private void tylkoJasniejsze(int postepPoczatkowy, int wagaCzasu) throws IOException {
        for (int i=0; i<plikiDoPrzetworzenia.size(); i++) {
            Obraz obrazRoboczy  = NarzedziaIO.odczytajObraz(plikiDoPrzetworzenia.get(i));
            NarzedziaObrazu.Maksimum.dodajKolejnyObraz(obrazRoboczy);
            
            upublicznijWartosc(typOperacji, (postepPoczatkowy + (i+1)*wagaCzasu/(plikiDoPrzetworzenia.size())));
        }
        
        Obraz obrazWynikowy = NarzedziaObrazu.Maksimum.getMaksimum();
        NarzedziaObrazu.Maksimum.reset();
        
        int minMax [] = obrazWynikowy.getMinMax();
        
        obrazWynikowy.setNazwaPliku("_tylko_jasniejsze" + " z " + plikiDoPrzetworzenia.size() 
                                        + " plikow " + minMax [0]/655.36 + "-" + minMax [1]/655.36);
        NarzedziaIO.zapiszObraz(obrazWynikowy, nazwaFolderu);
    }

    private void tylkoCiemniejsze(int postepPoczatkowy, int wagaCzasu) throws IOException {
        for (int i=0; i<plikiDoPrzetworzenia.size(); i++) {
            Obraz obrazRoboczy  = NarzedziaIO.odczytajObraz(plikiDoPrzetworzenia.get(i));
            NarzedziaObrazu.Minimum.dodajKolejnyObraz(obrazRoboczy);
            
            upublicznijWartosc(typOperacji, (postepPoczatkowy + (i+1)*wagaCzasu/(plikiDoPrzetworzenia.size())));
        }
        
        Obraz obrazWynikowy = NarzedziaObrazu.Minimum.getMinimum();
        NarzedziaObrazu.Minimum.reset();
        
        int minMax [] = obrazWynikowy.getMinMax();
        
        obrazWynikowy.setNazwaPliku("_tylko_ciemniejsze" + " z " + plikiDoPrzetworzenia.size() + " plikow " + minMax [0]/655.36 + "-" + minMax [1]/655.36);
        NarzedziaIO.zapiszObraz(obrazWynikowy, nazwaFolderu);
    }

    private void uwydatnienieRuchu(int postepPoczatkowy, int wagaCzasu) throws IOException {
        srednia(postepPoczatkowy,wagaCzasu);
    }
    
    private void histogram(int postepPoczatkowy, int wagaCzasu) throws IOException {
        
        new NarzedziaObrazu.Histogram(256);
        
        for (int i=0; i<plikiDoPrzetworzenia.size(); i++) {
            Obraz obrazRoboczy  = NarzedziaIO.odczytajObraz(plikiDoPrzetworzenia.get(i));
            NarzedziaObrazu.Histogram.dodajPikselZKolejnegoObrazu(obrazRoboczy);
            
            upublicznijWartosc(typOperacji, (postepPoczatkowy + (i+1)*wagaCzasu/(plikiDoPrzetworzenia.size())));
        }
        
        NarzedziaObrazu.Histogram.getHistogram();
        Obraz obrazWynikowy = NarzedziaObrazu.Histogram.getHistogram(640,480, plikiDoPrzetworzenia.size());
        obrazWynikowy.setNazwaPliku("_histogram");
        NarzedziaIO.zapiszObraz(obrazWynikowy, nazwaFolderu);
        
    }

    private void dummy() {
        System.out.println("Cos jest nie tak...");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
}
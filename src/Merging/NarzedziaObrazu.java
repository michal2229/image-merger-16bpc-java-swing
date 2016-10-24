/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// This code is licensed under LGPL license.

package Merging;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Michał Bokiniec
 */
public class NarzedziaObrazu {       
    public static Obraz dodaj(Obraz suma, Obraz skladnik) {
        Obraz sumaWynikowa = new Obraz(suma);
        
        for (int i = 0; i<skladnik.tablica.length; i++) {
            sumaWynikowa.tablica[i] = suma.tablica[i] + skladnik.tablica[i];
        }
        return sumaWynikowa;
    }
    
    public static int[] dodajPiksel(int piksel1[], int piksel2[]) {
        int wynik[] = new int[3];
        
        wynik[0] = piksel1[0] + piksel2[0];
        wynik[1] = piksel1[1] + piksel2[1];
        wynik[2] = piksel1[2] + piksel2[2];
        
        return wynik;
    }
    
    public static Obraz odejmij(Obraz roznica, Obraz odjemnik) {
        Obraz roznicaWynikowa = new Obraz(roznica);
        
        for (int i = 0; i<roznica.tablica.length; i++) {
            if (roznica.tablica[i] > odjemnik.tablica[i]) { 
                roznicaWynikowa.tablica[i] = (roznica.tablica[i] - odjemnik.tablica[i]);
            }
            else {
                roznicaWynikowa.tablica[i] = 0;
            }
        }
        return roznicaWynikowa;
    }
    
    public static Obraz roznica(Obraz roznica, Obraz odjemnik) {
        Obraz roznicaWynikowa = new Obraz(roznica);
        
        for (int i = 0; i<roznica.tablica.length; i++) {
            roznicaWynikowa.tablica[i] = Math.abs(roznica.tablica[i] - odjemnik.tablica[i]);
        }
        return roznicaWynikowa;
    }
    
    
    public static Obraz pomnoz(Obraz iloczyn, Obraz czynnik) {
        Obraz iloczynWynikowy = new Obraz(iloczyn);
        
        for (int i = 0; i<iloczyn.tablica.length; i++) {
            iloczynWynikowy.tablica[i] = iloczyn.tablica[i] * czynnik.tablica[i];
        }
        return iloczynWynikowy;
    }

    
    public static Obraz pomnoz(Obraz iloczyn, int czynnik) {
        Obraz iloczynWynikowy = new Obraz(iloczyn);
        
        for (int i = 0; i<iloczyn.tablica.length; i++) {
            iloczynWynikowy.tablica[i] = iloczyn.tablica[i] * czynnik;
        }
        return iloczynWynikowy;
    }
    
    public static Obraz pomnozPiksele(Obraz iloczyn, double[] piksel) {
        Obraz iloczynWynikowy = new Obraz(iloczyn);
        
        for (int i = 0; i<(iloczyn.tablica.length)/3; i++) {
            iloczynWynikowy.tablica[i*3 + 0] = (int) (iloczyn.tablica[i*3 + 0] * piksel[0]);
            iloczynWynikowy.tablica[i*3 + 1] = (int) (iloczyn.tablica[i*3 + 1] * piksel[1]);
            iloczynWynikowy.tablica[i*3 + 2] = (int) (iloczyn.tablica[i*3 + 2] * piksel[2]);
        }
        return iloczynWynikowy;
    }    
    
    public static Obraz pomnozPiksele(Obraz iloczyn, double R, double G, double B) {
        Obraz iloczynWynikowy = new Obraz(iloczyn);
       
        for (int i = 0; i<(iloczyn.tablica.length)/3; i++) {
            iloczynWynikowy.tablica[i*3 + 0] = (int) (iloczyn.tablica[i*3 + 0] * R);
            iloczynWynikowy.tablica[i*3 + 1] = (int) (iloczyn.tablica[i*3 + 1] * G);
            iloczynWynikowy.tablica[i*3 + 2] = (int) (iloczyn.tablica[i*3 + 2] * B);
        }
        return iloczynWynikowy;
    }    
    
    
    public static Obraz podziel(Obraz iloraz, Obraz dzielnik) {
        Obraz ilorazWynikowy = new Obraz(iloraz);
        
        for (int i = 0; i<iloraz.tablica.length; i++) {
            ilorazWynikowy.tablica[i] = iloraz.tablica[i] / dzielnik.tablica[i];
        }
        return ilorazWynikowy;
    }
    
    public static Obraz podziel(Obraz iloraz, int dzielnik) {
        Obraz ilorazWynikowy = new Obraz(iloraz);
        
        for (int i = 0; i<iloraz.tablica.length; i++) {
            ilorazWynikowy.tablica[i] = iloraz.tablica[i] / dzielnik;
        }
        
        return ilorazWynikowy;
    }
        
    public static Obraz podziel(Obraz iloraz, float dzielnik) {
        Obraz ilorazWynikowy = new Obraz(iloraz);
        
        for (int i = 0; i<iloraz.tablica.length; i++) {
            ilorazWynikowy.tablica[i] = (int) (iloraz.tablica[i] / dzielnik);
        }
        return ilorazWynikowy;
    }
    
    
    public static Obraz kwadratDiv256(Obraz kwadrat) {
        Obraz kwadratWynikowy = new Obraz(kwadrat);
        
        for (int i = 0; i<kwadrat.tablica.length; i++) {
            kwadratWynikowy.tablica[i] = ((kwadrat.tablica[i]/256) * (kwadrat.tablica[i]/256));
        }
        return kwadratWynikowy;
    }
    
    public static Obraz pierwiastek(Obraz pierwiastek) {
        Obraz pierwiastekWynikowy = new Obraz(pierwiastek);
        
        for (int i = 0; i<pierwiastek.tablica.length; i++) {
            pierwiastekWynikowy.tablica[i] = (int) Math.sqrt((double) pierwiastek.tablica[i]);
        }
        return pierwiastekWynikowy;
    }
      
    
    public static Obraz desaturyzacja(Obraz szary) {
        Obraz szaryWynikowy = new Obraz(szary);
        int srednia; // zmienna tymczasowa bedaca srednia z wartosci skladowych piksela
        
        for (int i = 0; i<(szary.tablica.length)/3; i++) {
            srednia = szary.tablica[i*3 + 0] + szary.tablica[i*3 + 1] + szary.tablica[i*3 + 2];
            srednia /=3;
            
            szaryWynikowy.tablica[i*3 + 0] = srednia;
            szaryWynikowy.tablica[i*3 + 1] = srednia;
            szaryWynikowy.tablica[i*3 + 2] = srednia;
        }
        return szaryWynikowy;
    }  
    
    public static Obraz desaturyzacja(Obraz szary, int[] waga) {
        Obraz szaryWynikowy = new Obraz(szary);
        int srednia; // zmienna tymczasowa bedaca srednia z wartosci skladowych piksela
        
        for (int i = 0; i<(szary.tablica.length)/3; i++) {
            srednia = szary.tablica[i*3 + 0]*waga[0] 
                    + szary.tablica[i*3 + 1]*waga[1] 
                    + szary.tablica[i*3 + 2]*waga[2];
            
            srednia /= waga[0] + waga[1] + waga[2];
            
            szaryWynikowy.tablica[i*3 + 0] = srednia;
            szaryWynikowy.tablica[i*3 + 1] = srednia;
            szaryWynikowy.tablica[i*3 + 2] = srednia;
        }
        return szaryWynikowy;
    }  
    
    
    public static Obraz histogram(Obraz histogram) {    
        Obraz histogramWynikowy = new Obraz(histogram);
        
        java.util.Arrays.sort(histogramWynikowy.tablica); // poki co nie jest to histogram...
        return histogramWynikowy;
    }
    
    public static Obraz rozciagnijHistogram(Obraz histogram, int limit) {
        Obraz rozciagnietyWynikowy = new Obraz(histogram);
        int minMax[] = histogram.getMinMax();
        
        for (int i = 0; i<histogram.tablica.length; i++) {
            rozciagnietyWynikowy.tablica[i] = (int) (    ( limit * ((double) histogram.tablica[i] - minMax[0]) )
                                                        /((double) (minMax[1] - minMax[0])) 
                                                    );
        }
        return rozciagnietyWynikowy;
    }
            
    
    public static class Maksimum {
        static Obraz maksimum = null;
                
        public static void dodajKolejnyObraz(Obraz obrazSprawdzany){
            if (maksimum == null)
                maksimum = new Obraz(obrazSprawdzany);
        
            for (int i = 0; i<obrazSprawdzany.tablica.length; i++) {
                if (obrazSprawdzany.tablica[i] > maksimum.tablica[i]) 
                    maksimum.tablica[i] = obrazSprawdzany.tablica[i];
            }
        }
        
        public static Obraz getMaksimum(){
            Obraz maksimumDoZwrocenia = pomnoz(maksimum, 256);
            return maksimumDoZwrocenia;
        }
        
        public static void reset(){
            maksimum = null;
        }
    }   

    public static class Minimum {
        static Obraz minimum = null;
                
        public static void dodajKolejnyObraz(Obraz obrazSprawdzany) {
            dodajKolejnyObraz(obrazSprawdzany, 0);
        }
        
        public static void dodajKolejnyObraz(Obraz obrazSprawdzany, int tryb){
            if (minimum == null) {
                minimum = new Obraz(obrazSprawdzany);
                for (int i=0; i<minimum.tablica.length; i++) {
                    minimum.tablica[i]=255;
                }
            }
        
            switch (tryb) {
                case 1 : {
                    for (int i = 0; i<(obrazSprawdzany.tablica.length/3); i++) {
                        if ((obrazSprawdzany.tablica[3*i] + obrazSprawdzany.tablica[3*i+1] + obrazSprawdzany.tablica[3*i+2]) 
                                < (minimum.tablica[3*i] + minimum.tablica[3*i+1] + minimum.tablica[3*i+2])) {
                            minimum.tablica[3*i]   = obrazSprawdzany.tablica[3*i];
                            minimum.tablica[3*i+1] = obrazSprawdzany.tablica[3*i+1];
                            minimum.tablica[3*i+2] = obrazSprawdzany.tablica[3*i+2];                            
                        }   
                    }
                    break;
                }
                
                default : {
                    for (int i = 0; i<obrazSprawdzany.tablica.length; i++) {
                        if (obrazSprawdzany.tablica[i] < minimum.tablica[i]) 
                            minimum.tablica[i] = obrazSprawdzany.tablica[i];
                    }
                    break;
                }
            }
        }
        
        public static Obraz getMinimum(){
            Obraz minimumDoZwrocenia = pomnoz(minimum, 256);
            return minimumDoZwrocenia;
        }
        
        public static void reset(){
            minimum = null;
        }
    }
    
    public static class Srednia {
        static Obraz suma = null;
        static int iloscPlikowPrzetworzonych = 0;
        
        public static void dodajKolejnyObraz(Obraz pomiar) {
            if (suma == null) suma = new Obraz(pomiar);
                        
            suma = dodaj(suma, pomiar);
            
            iloscPlikowPrzetworzonych++;
        }
        
        public static Obraz getSrednia() {
            Obraz srednia;
            
            srednia = pomnoz(suma, 256); 
            srednia = podziel(srednia, iloscPlikowPrzetworzonych);
            return srednia;
        }
        
        public static void reset() {
            suma = null;
            iloscPlikowPrzetworzonych = 0;
        }
    }
    
    public static class OdchylenieStandardowe {
        static Obraz sumaKwadratowRoznic = null;
        static int iloscPlikowPrzetworzonych = 0;
        

              
        public static void dodajKolejnyObraz(Obraz srednia, Obraz pomiar) {      
            if (sumaKwadratowRoznic == null)  sumaKwadratowRoznic = new Obraz(srednia);
            
            Obraz kwadratRoznic = pomnoz(pomiar, 256);
            kwadratRoznic = roznica(srednia, kwadratRoznic); // to trzeba zapisywac do czegos...
            

            
            kwadratRoznic = kwadratDiv256(kwadratRoznic);
            sumaKwadratowRoznic = dodaj(sumaKwadratowRoznic, kwadratRoznic);
            
            iloscPlikowPrzetworzonych++;
        }
        
        public static Obraz getOdchylenieStandardowe() {
            Obraz odchylenie;
            
            odchylenie = pierwiastek(sumaKwadratowRoznic);
            odchylenie = pomnoz(odchylenie,256);
            odchylenie = podziel(odchylenie, iloscPlikowPrzetworzonych);
            
            return odchylenie.kopiuj();
        }
        
        public static void reset() {
            sumaKwadratowRoznic = null;
            iloscPlikowPrzetworzonych = 0;
        }
    }

    
    public static class Histogram {
        static int iloscPrzedzialow = 12;
        static int iloscWartosci = 256;
        static List<int[]> listaDoHistogramu = new ArrayList<>(); // lista trojkanałowych pikseli
        static int licznosc[][] = new int [3][iloscPrzedzialow]; // ma objac 3 kanaly o wartosciach 0..65536
        
        public Histogram(int iloscPrzedzialow) {
            this.iloscPrzedzialow = iloscPrzedzialow;
            licznosc = new int [3][this.iloscPrzedzialow];
        }
        
        public static void dodajPikselZKolejnegoObrazu(Obraz obraz) {
            dodajPikselZKolejnegoObrazu(obraz, obraz.getSzerokoscObrazu()/2, obraz.getWysokoscObrazu()/2);
        }
        
        public static void dodajPikselZKolejnegoObrazu(Obraz obraz, int x, int y) {
            listaDoHistogramu.add(obraz.getPiksel(x, y));
            
            licznosc[0][obraz.getPiksel(x, y)[0]/(iloscWartosci/iloscPrzedzialow)]++;
            licznosc[1][obraz.getPiksel(x, y)[1]/(iloscWartosci/iloscPrzedzialow)]++;
            licznosc[2][obraz.getPiksel(x, y)[2]/(iloscWartosci/iloscPrzedzialow)]++;
            
            System.out.println("R: " + obraz.getPiksel(x, y)[0] 
                    + "  G: " + obraz.getPiksel(x, y)[1] 
                    + "  B: " + obraz.getPiksel(x, y)[2]);
            
            System.out.println("R++: " + obraz.getPiksel(x, y)[0]/(iloscWartosci/iloscPrzedzialow) 
                    + "  G++: " + obraz.getPiksel(x, y)[1]/(iloscWartosci/iloscPrzedzialow) 
                    + "  B++: " + obraz.getPiksel(x, y)[2]/(iloscWartosci/iloscPrzedzialow));
        }
        
        public static int[][] getHistogram() {
            for (int i = 0; i<3; i++)
                for (int j=0; j<iloscPrzedzialow; j++) { // kazdy element listy to trojelementowa tablica (piksel)
                    System.out.print(i + ":" + j + ": ");
                    for (int k = 0; k<licznosc[i][j]; k++)
                        System.out.print("x");
                    System.out.println("\n");
                }
            return licznosc; // tu przetwarzana bedzie lista na histogram w formie obrazu, moze byc odpalana funkcja histogram() z NarzedziaObrazu
        }
        
        public static Obraz getHistogram(int szer, int wys, int iloscPlikow) {
            Obraz obrazRoboczy = new Obraz(szer, wys);
          
            for (int i=0; i<iloscPrzedzialow; i++) 
                for (int j=0; j<szer/iloscPrzedzialow; j++)
                    for (int k=0; k<3 ;k++)
                        for (int l=0; l<licznosc[k][i]*wys/iloscPlikow;l++) {
                            int x = i+j;
                            int y = wys-l-1;
                            int piksel1[] = obrazRoboczy.getPiksel(x, y);
                            int piksel2[] = new int[3];
                            
                            piksel2[0] = (k == 0) ? 65535 :  0;
                            piksel2[1] = (k == 1) ? 65535 :  0;
                            piksel2[2] = (k == 2) ? 65535 :  0;
                            
                            obrazRoboczy.setPiksel(x, y, dodajPiksel(piksel1, piksel2));
                            //obrazRoboczy.setPiksel(x, y, 65535,65535,65535);
                        }
            return obrazRoboczy;
        }
        
        public static void reset() {
            iloscPrzedzialow = 12;
            iloscWartosci = 256;
            listaDoHistogramu = new ArrayList<>();
            licznosc = null;
            licznosc = new int [3][iloscPrzedzialow];
        }
    }
} 


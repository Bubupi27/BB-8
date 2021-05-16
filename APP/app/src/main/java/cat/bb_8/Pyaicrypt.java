package cat.bb_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.lang.Math;

//Pyaicrypt 4.4

public class Pyaicrypt {
    static ArrayList<Character> ll=new ArrayList<>(Arrays.asList(' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '<', '>', '_', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', ']', '^', '_', '`', 'à', 'á', 'è', 'é', 'ì', 'í', 'ò', 'ó', 'ù', 'ú', 'À', 'Á', 'È', 'É', 'Ì', 'Í', 'Ò', 'Ó', 'Ù', 'Ú', 'ï', 'ë', 'ä', 'ü', 'ö', 'Ä', 'Ë', 'Ï', 'Ö', 'Ü', 'â', 'ê', 'î', 'ô', 'û', 'Â', 'Ê', 'Î', 'Ô', 'Û', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
    public static void main(String[] args){
        while (Integer.valueOf(1).equals(1)){
            Scanner scaner=new Scanner(System.in);
            System.out.println("Que vols fer xifrar (x), desxifrar (d) o sortir (s)?");
            String opcio = scaner.nextLine();
            if (opcio.equals("x")){
                System.out.println("Quin tipus de xifrat vols simple (s) mk2 (mk2) o complex (c)?");
                String opcio2 = scaner.nextLine();
                switch (opcio2) {
                    case "s":
                    case "simple":
                    case "S":
                    case "Simple":
                    case "SIMPLE": {
                        System.out.println("Introdueix un text: ");
                        String text = scaner.nextLine();
                        System.out.println("Introdueix una clau: ");
                        String clau = scaner.nextLine();
                        System.out.println("El resultat de xifrar " + text + " amb " + clau + " és " + xifrar(text, clau));
                        break;
                    }
                    case "mk2":
                    case "MK2": {
                        System.out.println("Introdueix un text: ");
                        String text = scaner.nextLine();
                        System.out.println("Introdueix una clau: ");
                        String clau = scaner.nextLine();
                        System.out.println("El resultat de xifrar " + text + " amb " + clau + " és " + xifrar_mk2(text, clau));
                        break;
                    }
                    case "c":
                    case "C":
                    case "complex":
                    case "Complex":
                    case "COMPLEX": {
                        System.out.println("Introdueix un text: ");
                        String text = scaner.nextLine();
                        System.out.println("Introdueix una clau primaria: ");
                        String clau = scaner.nextLine();
                        System.out.println("Introdueix una clau secundaria: ");
                        String clau2 = scaner.nextLine();
                        System.out.println("Introdueix una llavor: ");
                        long llavor = Long.parseLong(scaner.nextLine());
                        System.out.println("El resultat de xifrar " + text + " amb la clau primaria " + clau + " la clau secundaria " + clau2 + " i la llavor " + llavor + " és " + xifrat_complex(text, clau, clau2, llavor));
                        break;
                    }
                }
            }
            if (opcio.equals("d")||opcio.equals("D")||opcio.equals("desxifrar")||opcio.equals("Desxifrar")||opcio.equals("DESXIFRAR")){
                System.out.println("Amb quin tipus de xifrat es va xifrar el text simple (s) mk2 (mk2) o complex (c)?");
                String opcio2 = scaner.nextLine();
                switch (opcio2) {
                    case "s":
                    case "simple":
                    case "S":
                    case "Simple":
                    case "SIMPLE": {
                        System.out.println("Introdueix un text: ");
                        String text = scaner.nextLine();
                        System.out.println("Introdueix una clau: ");
                        String clau = scaner.nextLine();
                        System.out.println("El resultat de desxifrar " + text + " amb " + clau + " és " + desxifrar(text, clau));
                        break;
                    }
                    case "mk2":
                    case "MK2": {
                        System.out.println("Introdueix un text: ");
                        String text = scaner.nextLine();
                        System.out.println("Introdueix una clau: ");
                        String clau = scaner.nextLine();
                        System.out.println("El resultat de desxifrar " + text + " amb " + clau + " és " + desxifrar_mk2(text, clau));
                        break;
                    }
                    case "c":
                    case "C":
                    case "complex":
                    case "Complex":
                    case "COMPLEX": {
                        System.out.println("Introdueix un text: ");
                        String text = scaner.nextLine();
                        System.out.println("Introdueix una clau primaria: ");
                        String clau = scaner.nextLine();
                        System.out.println("Introdueix una clau secundaria: ");
                        String clau2 = scaner.nextLine();
                        System.out.println("Introdueix una llavor: ");
                        long llavor = Long.parseLong(scaner.nextLine());
                        System.out.println("El resultat de desxifrar " + text + " amb la clau primaria " + clau + " la clau secundaria " + clau2 + " i la llavor " + llavor + " és " + desxifrat_complex(text, clau, clau2, llavor));
                        break;
                    }
                }
            }
        scaner.close();
        }
        
    }
    static long text_to_clue(String text){
        ArrayList<Character> lll = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
        StringBuilder numbers= new StringBuilder();
        StringBuilder charstonum= new StringBuilder();
        for (int i=0;i<text.length();i++){
            long quant_numbers=0L;
            for (int i1=0;i1<lll.size();i1++){
                if (text.charAt(i)==lll.get(i1)){
                    numbers.append(text.charAt(i));
                    quant_numbers++;
                }
            }
            if (quant_numbers==0){
                charstonum.append((int) text.charAt(i));
            }
        }
        String groud = numbers+ charstonum.toString();
        return Long.parseLong(overflow_case(groud));
    }

    static String overflow_case(String text){
        StringBuilder reformed_groud= new StringBuilder();
        long x1=0L;
        StringBuilder clau= new StringBuilder();
        if(text.length()>18){
            long x2=0L;
            while(x1<4){
                clau.append(text.charAt(Math.toIntExact(x1)));
                x1++;
            }
            long clau_original=Long.parseLong(clau.toString());
            long clau2=Long.parseLong(clau.toString());
            while(x2<18){
                reformed_groud.append(text.charAt(Math.toIntExact(((clau2 + clau_original + x2) % text.length()))));
                clau2=(clau2+clau_original+x2)% text.length();
                x2++;
            }
        }
        if (reformed_groud.toString().equals("")){
            return text;
        }
        else{
            return reformed_groud.toString();
        }
    }

    static HashMap<Character, Character>generate_dic(long groud, ArrayList<Character> llista){
        HashMap<Character, Character>dic= new HashMap<>();
        ArrayList<Character> l_original= new ArrayList<>();
        for (int i=0;i<llista.size();i++){
            l_original.add(llista.get(i));
        }
        ArrayList<Character> l= new ArrayList<>();
        for (int i=0;i<llista.size();i++){
            l.add(llista.get(i));
        }
        int longitud_inicial_l=l.size();
        long clau=groud;
        long aleatoritzador=groud;
        long i=0L;
        while(i!=longitud_inicial_l){
            long rand =  ((((clau % 1000000000000L) + aleatoritzador % 1000000000) + 96739) % l.size());
            clau=((clau % 1000000000003L)+(aleatoritzador % 1000000000)+104729);
            dic.put(l_original.get(Math.toIntExact(i)), l.get(Math.toIntExact(rand)));
            l.remove(Math.toIntExact(rand));
            aleatoritzador+=3;
            i++;
        }
        return dic;
    }
    static HashMap<Character, Character>invert_dic(HashMap<Character, Character> dic, ArrayList<Character> llista){
        HashMap<Character, Character>inv_dic= new HashMap<>();
        int i=0;
        while (i< llista.size()){
            char pos1=dic.get(llista.get(i));
            inv_dic.put(pos1, llista.get(i));
            i++;
        }
        return inv_dic;
    }
    static String encoder(HashMap<Character, Character> dic, String text){
        StringBuilder xifrat= new StringBuilder();
        for(int i=0;i<text.length();i++){
            xifrat.append(dic.get(text.charAt(i)));
        }
        return xifrat.toString();
    }
    static String xifrar(String text, String clau){
        long clau2=text_to_clue(clau);
        System.out.println(clau2);
        HashMap<Character, Character>dic=generate_dic(clau2, ll);
        System.out.println(dic);
        return encoder(dic, text);
    }
    static String desxifrar(String text, String clau){
        long clau2=text_to_clue(clau);
        HashMap<Character, Character>dic=generate_dic(clau2, ll);
        HashMap<Character, Character>inv_dic=invert_dic(dic, ll);
        return encoder(inv_dic, text);
    }
    static String xifrar_mk2(String text, String clau){
        long clau2=text_to_clue(clau);
        StringBuilder xifrat= new StringBuilder();
        for (int i=0;i<text.length();i++){
            HashMap<Character, Character>dic=generate_dic(clau2, ll);
            xifrat.append(dic.get(text.charAt(i)));
            clau2++;
        }
        return xifrat.toString();
    }
    static String desxifrar_mk2(String text, String clau){
        long clau2=text_to_clue(clau);
        StringBuilder xifrat= new StringBuilder();
        for (int i=0;i<text.length();i++){
            HashMap<Character, Character>dic=generate_dic(clau2, ll);
            HashMap<Character, Character>inv_dic=invert_dic(dic, ll);
            xifrat.append(inv_dic.get(text.charAt(i)));
            clau2++;
        }
        return xifrat.toString();
    }
    static String xifrat_complex(String text, String clau_primaria, String clau_secundaria, long rexifrar){
        long clau_primaria2=text_to_clue(clau_primaria);
        long clau_secundaria2=text_to_clue(clau_secundaria);
        long aleatoritzador=clau_primaria2;
        int i=0;
        while (i<rexifrar){
            HashMap<Character, Character>dic=generate_dic(clau_primaria2, ll);
            clau_primaria=encoder(dic, String.valueOf(clau_secundaria2));
            clau_primaria2=text_to_clue(clau_primaria);
            clau_secundaria2= clau_primaria2 +aleatoritzador;
            aleatoritzador+=3;
            i++;
        }
        HashMap<Character, Character>dic=generate_dic(clau_secundaria2, ll);
        return encoder(dic, text);
    }
    static String desxifrat_complex(String text, String clau_primaria, String clau_secundaria, long rexifrar){
        long clau_primaria2=text_to_clue(clau_primaria);
        long clau_secundaria2=text_to_clue(clau_secundaria);
        long aleatoritzador=clau_primaria2;
        int i=0;
        while (i<rexifrar){
            HashMap<Character, Character>dic=generate_dic(clau_primaria2, ll);
            clau_primaria=encoder(dic, String.valueOf(clau_secundaria2));
            clau_primaria2=text_to_clue(clau_primaria);
            clau_secundaria2= clau_primaria2 +aleatoritzador;
            aleatoritzador+=3;
            i++;
        }
        HashMap<Character, Character>dic=generate_dic(clau_secundaria2, ll);
        HashMap<Character, Character>inv_dic=invert_dic(dic, ll);
        return encoder(inv_dic, text);
    }
}

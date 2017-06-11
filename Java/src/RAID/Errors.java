package RAID;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Belzi on 2017-06-11.
 */
public class Errors {

    private String data;
    private String bits;
    private String enterData="1101101";

   public Errors(){

       saveData(enterData, setCalculatedBits());
       loadData();
       //textFiles();
    }


    private void loadData() {

        //pobranie  nazwy użytkownika z systemu
        String username = System.getProperty("user.name");

        //scieżki dostępu
        String pathFolder = "C:/Users/" + username + "/Documents/RAID";
        String pathDataFile = pathFolder + "/Dane.txt";
        String pathBitsFile = pathFolder + "/Bity.txt";

        if (!Files.exists(Paths.get(pathFolder))) {
            System.out.println("Brak Katalogu");
            System.exit(0);
        }

        if (!Files.exists(Paths.get(pathDataFile))) {
            System.out.println("Brak pliku z danymi");
            System.exit(0);
        }

        if (!Files.exists(Paths.get(pathBitsFile))) {
            System.out.println("Brak pliku z bitami");
            System.exit(0);
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader(pathDataFile));
            System.out.println("Udało się otworzyć plik z danymi");
            data=in.readLine();
            System.out.println(data);
            in.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader(pathBitsFile));
            System.out.println("Udało się otworzyć plik z bitami");
            bits=in.readLine();
            System.out.println(bits);
            in.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    //zapisywanie danych do plików
    private void saveData(String data, String bits){

        //pobranie  nazwy użytkownika z systemu
        String username = System.getProperty("user.name");

        //scieżki dostępu
        String pathFolder="C:/Users/"+username+"/Documents/RAID";
        String pathDataFile=pathFolder+"/Dane.txt";
        String pathBitsFile=pathFolder+"/Bity.txt";


        //utworzenie katalogu w moich dokumentach
        if(!Files.exists(Paths.get(pathFolder))){
            try {
                Files.createDirectory(Paths.get(pathFolder));
                System.out.println("Utworzono katalog");
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        //utworzenie pliku w moich dokumentach
        if(!Files.exists(Paths.get(pathDataFile))){
            try {
                Files.createFile(Paths.get(pathDataFile));
                System.out.println("Utoworzono plik z Danymi");
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        //utworzenie pliku w moich dokumentach
        if(!Files.exists(Paths.get(pathBitsFile))){
            try {
                Files.createFile(Paths.get(pathBitsFile));
                System.out.println("Utoworzono plik z Bitami");
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(pathDataFile));
            out.write(data);
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(pathBitsFile));
            out.write(bits);
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    // generowanie raportu
    private void textFiles() {

        //pobranie  nazwy użytkownika z systemu
        String username = System.getProperty("user.name");

        //scieżki dostępu
        String pathFolder="C:/Users/"+username+"/Documents/RAID";
        String pathFile=pathFolder+"/Raport.txt";

        //utworzenie katalogu w moich dokumentach
        if(!Files.exists(Paths.get(pathFolder))){
            try {
                Files.createDirectory(Paths.get(pathFolder));
                System.out.println("Utworzono katalog");
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        //utworzenie pliku w moich dokumentach
        if(!Files.exists(Paths.get(pathFile))){
            try {
                Files.createFile(Paths.get(pathFile));
                System.out.println("Utoworzono plik z raportem");
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(pathFile));
            out.write("123");
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void genetateRaport(){

    }

    private int xor(int a ,int b){

        if(a==b)
            return 0;
        else return 1;
    }

    public String getData() {
        return data;
    }

    public String getBits() {
        return bits;
    }

    private String setCalculatedBits(){

        String result="";
       char[] arr= enterData.toCharArray();
        for (int i=0;i<arr.length/2;i++) {
            result+=xor(arr[i*2],arr[i*2+1]);
        }
        if(arr.length%2==1)
            result+=arr[arr.length-1];
        return result;
    }
}

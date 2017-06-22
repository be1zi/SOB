package RAID;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Belzi on 2017-06-11.
 */
public class Filess {

    private String data_1;
    private String data_2;
    private String data="";
    private String bits;
    private String enterData="1101101000101101";

   public Filess(){

       saveData(enterData);
       loadData();
       //textFiles();
    }

    private void loadData() {

        //pobranie  nazwy użytkownika z systemu
        String username = System.getProperty("user.name");

        //scieżki dostępu
        String pathFolder = "C:/Users/" + username + "/Documents/RAID";
        String pathData1File = pathFolder + "/Dane1.txt";
        String pathData2File = pathFolder + "/Dane2.txt";
        String pathBitsFile = pathFolder + "/Bity.txt";

        if (!Files.exists(Paths.get(pathFolder))) {
            System.out.println("Brak Katalogu");
            System.exit(0);
        }

        if (!Files.exists(Paths.get(pathData1File))) {
            System.out.println("Brak pliku z danymi (1)");
            System.exit(0);
        }

        if (!Files.exists(Paths.get(pathData2File))) {
            System.out.println("Brak pliku z danymi (2)");
            System.exit(0);
        }


        if (!Files.exists(Paths.get(pathBitsFile))) {
            System.out.println("Brak pliku z bitami");
            System.exit(0);
        }


        try {

            BufferedReader in_1 = new BufferedReader(new FileReader(pathData1File));
            System.out.println("Udało się otworzyć plik z danymi (1)");

            BufferedReader in_2 = new BufferedReader(new FileReader((pathData2File)));
            System.out.println("Udało się otworzyć plik z danymi (2)");

            BufferedReader in = new BufferedReader(new FileReader(pathBitsFile));
            System.out.println("Udało się otworzyć plik z bitami ");

            data_1 = in_1.readLine();
            data_2 = in_2.readLine();
            bits = in.readLine();
            System.out.println(data_1);
            System.out.println(data_2);
            System.out.println(bits);

            margeData(data_1,data_2);

            in.close();
            in_1.close();
            in_2.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //zapisywanie danych do plików
    private void saveData(String data){

        //pobranie  nazwy użytkownika z systemu
        String username = System.getProperty("user.name");

        //scieżki dostępu
        String pathFolder="C:/Users/"+username+"/Documents/RAID";
        String pathData1File = pathFolder + "/Dane1.txt";
        String pathData2File = pathFolder + "/Dane2.txt";
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
        if(!Files.exists(Paths.get(pathData1File))){
            try {
                Files.createFile(Paths.get(pathData1File));
                System.out.println("Utoworzono plik z Danymi (1)");
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        if(!Files.exists(Paths.get(pathData2File))){
            try {
                Files.createFile(Paths.get(pathData2File));
                System.out.println("Utoworzono plik z Danymi (2)");
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
            char[] arr= enterData.toCharArray();

            BufferedWriter out_1 = new BufferedWriter(new FileWriter(pathData1File));
            System.out.println("Udało się zapisac plik z danymi (1) ");
            BufferedWriter out_2 = new BufferedWriter(new FileWriter(pathData2File));
            System.out.println("Udało się zapisac plik z danymi (2) ");
            BufferedWriter outBit = new BufferedWriter(new FileWriter(pathBitsFile));
            System.out.println("Udało się zapisac plik z bitami ");

            for(int i=0;i<arr.length/2;i++){
                out_1.write(arr[i*2]);
                out_2.write(arr[i*2+1]);
                char tmp=xor(arr[i*2],arr[i*2+1]);
                outBit.write(tmp);
            }

            if(arr.length%2==1)
                outBit.write(arr[arr.length-1]);

            out_1.close();
            out_2.close();
            outBit.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    // generowanie raportu
    public void genetateRaport(String input, String disk1, String disk2, String bits,String errors) {

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
            margeData(disk1,disk2);
            out.write("Dane wejściowe: "+input + "     "+"Dysk 1: "+data_1 + "     Dysk 2: "+data_2 + "\r\n");
            out.write("Dane wyjściowe: "+data+"     "+ "Dysk 1: "+disk1+"     Dysk 2: "+disk2+"\r\n");
            out.write("Bity parzystości: "+bits+"\r\n");

            if(!data_1.equals(disk1) && !data_2.equals(disk2))
                out.write(errors+" uszkodzony dysk 1 i 2");
            else if(!data_1.equals(disk1))
                out.write(errors+" na dysku 1");
            else if(!data_2.equals(disk2))
                out.write(errors+" na dysku 2");

            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    private char xor(int a ,int b){

        if(a==b)
            return '0';
        else return '1';
    }

    public String getData() {

        return data;
    }

    public String getBits() {

        return bits;
    }

    private void margeData(String data1, String data2){

        char[] tmp1 = data1.toCharArray();
        char[] tmp2 = data2.toCharArray();

        System.out.println("Dlugosc danych 1: "+tmp1.length);
        System.out.println("Dlugosc danych 2: "+tmp2.length);
        data="";

        for(int i=0;i<tmp1.length;i++){
            data+=tmp1[i];
            data+=tmp2[i];
        }
        if(tmp1.length!=tmp2.length)
            data+=tmp1[tmp1.length];
        System.out.println("Zmergowane dane: "+data + " ,Rozmiar : " +data.length());
    }

}

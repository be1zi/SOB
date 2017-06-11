package RAID;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Belzi on 2017-06-11.
 */
public class Errors {

   public Errors(){

       textFiles();
       saveData();
    }

    private void saveData(){

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
            out.write("Dane");
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


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
}

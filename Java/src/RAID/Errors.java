package RAID;

import java.util.Random;

/**
 * Created by Kamil on 2017-06-13.
 */
public class Errors {

    public Errors(){
    }

    public String generateError(String input){

        String output="";
        char[] tab = input.toCharArray();

        Random r = new Random();
        int tmp = r.nextInt(input.length());
        System.out.println(tmp);
        if(tab[tmp]=='0')
            tab[tmp]='1';
        else
            tab[tmp]='0';

        for(int i=0;i<tab.length;i++){
            output+=tab[i];
        }

        return output;
    }

    public String rotateArray(String input){

       System.out.println("Dane przed obroceniem: "+ input);
        String output="";
        char[] tab = input.toCharArray();

        Random r = new Random();
        int it=r.nextInt(2);

        for(int i=0;i<tab.length/2;i++){
           if(it==1) {
               output+=tab[i*2];
               if (tab[i * 2 + it] == '0')
                   output += '1';
               else
                   output += '0';
           }
           else{
                if(tab[i*2]=='0')
                    output+='1';
                else
                    output+='0';
            output+=tab[i*2+1];
           }
        }

        System.out.println("Dane po obroceniu:     "+output);
        return output;
    }

    private char xor(int a ,int b){

        if(a==b)
            return '0';
        else return '1';
    }

    public boolean isCorrect(String out, String bits){

        boolean tmp = true;
        char[] arr = out.toCharArray();
        char[] bitsArr = bits.toCharArray();

        for(int i=0;i<arr.length/2;i++){
            if(xor(arr[i*2],arr[i*2+1])!=bitsArr[i])
                tmp = false;
        }
        if(arr.length%2!=0){
            if((int)arr[arr.length-1]!=bitsArr[bitsArr.length-1])
                tmp=false;
        }

        return tmp;
    }

}

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
        if(tab[tmp]==0)
            tab[tmp]=1;
        else
            tab[tmp]=0;

        for(int i=0;i<tab.length;i++){
            output+=tab[i];
        }

        return output;
    }

    public String rotateArray(String input){
        String output="";
        char[] tab = input.toCharArray();

        for(int i=0;i<tab.length;i++){
            if(tab[i]=='0')
                output+=1;
            else
                output+=0;
        }

        System.out.println(output);
        return output;
    }

}

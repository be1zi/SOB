package GUI;

import RAID.Errors;
import RAID.Filess;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Złoty on 2017-06-11.
 */
public class MainWindow {

    private JPanel MainPanel;
    private JLabel label_Topic;
    private JButton zmien1LosowyBitButton;
    private JButton odwrocWszystkieBityButton;
    private JButton zmienKilkaBitowButton;
    private JTextPane inputData;
    private JTextPane outputData;
    private JTextPane textField1;
    private JButton zatwierdzButton;
    private JTextPane disk1;
    private JTextPane disk2;
    private static JFrame frame;


    /*
        Metoda sprawdza czy poprawnie wprowadzono dane
     */
    private Boolean checkIfBit(){
        for(int i=0; i<inputData.getText().length(); i++){
            if(inputData.getText().charAt(i) != '0' && inputData.getText().charAt(i) != '1'){
                return false;
            }
        }

        return true;
    }


    /*
        Metoda koloruje bledne bity
     */
    private void setColourForEachBit(String error){
        String tmp = inputData.getText();

        /*if(!error.equals(tmp)){
            tmp = error;
        }*/

        StyledDocument doc = outputData.getStyledDocument();        //wyjscie dla calosci - kolor
        StyledDocument doc_disk1 = disk1.getStyledDocument();            //wyjscie dla dysku 1 - kolor
        StyledDocument doc_disk2 = disk2.getStyledDocument();            //wyjscie dla dysku 2 - kolor

        Style style = outputData.addStyle("Red coloured text", null);
        Style style_disk1 = disk1.addStyle("Red coloured tex", null);
        Style style_disk2 = disk2.addStyle("Red coloured te", null);
        StyleConstants.setBackground(style, Color.red);
        StyleConstants.setBackground(style_disk1, Color.red);
        StyleConstants.setBackground(style_disk2, Color.red);

        Style style2 = outputData.addStyle("Black coloured text", null);
        Style style2_disk1 = disk1.addStyle("Black coloured tex", null);
        Style style2_disk2 = disk2.addStyle("Black coloured te", null);
        StyleConstants.setBackground(style2, Color.white);
        StyleConstants.setBackground(style2_disk1, Color.white);
        StyleConstants.setBackground(style2_disk2, Color.white);

        int j = 0;
        int k = 0;
        for(int i=0; i<error.length(); i++) {
            if(inputData.getText().charAt(i) != error.charAt(i)){
                try {
                    doc.insertString(i, String.valueOf(tmp.charAt(i)), style);
                    if(i % 2 == 0){     //dla dysku 1
                        doc_disk1.insertString(j++, String.valueOf(tmp.charAt(i)), style_disk1);
                    } else {            //dla dysku 2
                        doc_disk2.insertString(k++, String.valueOf(tmp.charAt(i)), style_disk2);
                    }
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    doc.insertString(i, String.valueOf(tmp.charAt(i)), style2);
                    if(i % 2 == 0){      //dla dysku 1
                        doc_disk1.insertString(j++, String.valueOf(tmp.charAt(i)), style2_disk1);
                    } else {             //dla dysku 2
                        doc_disk2.insertString(k++, String.valueOf(tmp.charAt(i)), style2_disk2);
                    }
                } catch (BadLocationException e) {
                    //e.printStackTrace();
                }
            }
        }
    }

    /*
        Metoda koloruje bledne bity parzystosci
     */
    private void setColourForParityBits(String error){
        String tmp = outputData.getText();

        StyledDocument doc = textField1.getStyledDocument();

        Style style = textField1.addStyle("Red coloured text", null);
        StyleConstants.setBackground(style, Color.red);

        Style style2 = textField1.addStyle("Black coloured text", null);
        StyleConstants.setBackground(style2, Color.white);

        System.out.println(tmp);
        System.out.println(error);
        int j = 0;
        for(int i=0; i<tmp.length(); i+=2) {
            if((tmp.charAt(i) != tmp.charAt(i+1) && error.charAt(j) == '0') ||
                    (tmp.charAt(i) == tmp.charAt(i+1) && error.charAt(j) == '1')){
                try {
                    //System.out.println("IF red");
                    doc.insertString(j, String.valueOf(error.charAt(j)), style);
                    j++;
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    //System.out.println("IF black");
                    doc.insertString(j, String.valueOf(error.charAt(j)), style2);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
                j++;
            }
        }


    }


    public MainWindow() {
        Filess err = new Filess();
        inputData.setText(err.getData());
        textField1.setText(err.getBits());

        zmienKilkaBitowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputData.setEditable(true);
            }
        });

        zatwierdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputData.setEditable(false);
                try {
                    if (checkIfBit()) {
                        //kolorowanie i wypisywanie
                        outputData.setText("");
                        disk1.setText("");
                        disk2.setText("");
                        Errors error = new Errors();
                        setColourForEachBit(err.getData());
                        inputData.setText(err.getData());

                        System.out.println(error.isCorrect(outputData.getText(), err.getBits()));
                    } else {
                        inputData.setText(err.getData());
                        outputData.setText("Podaj na wejsciu tylko bity");
                    }
                } catch(ArrayIndexOutOfBoundsException aIOOBE){
                    outputData.setText("Podaj na wejsciu poprawna ilosc bitow");
                }

                //kolorowanie i wypisywanie bitow parzystosci
                textField1.setText("");
                setColourForParityBits(err.getBits());
            }
        });
        zmien1LosowyBitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputData.setText("");
                disk1.setText("");
                disk2.setText("");

                Errors error = new Errors();
                String tmp = inputData.getText();

                inputData.setText(error.generateError(tmp));
                //kolorowanie i wypisywanie
                setColourForEachBit(tmp);
                inputData.setText(err.getData());


                //kolorowanie i wypisywanie bitow parzystosci
                textField1.setText("");
                setColourForParityBits(err.getBits());

                System.out.println(inputData.getText());
                System.out.println(outputData.getText());
                System.out.println(error.isCorrect(outputData.getText(), err.getBits()));
            }
        });
        odwrocWszystkieBityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputData.setText("");
                disk1.setText("");
                disk2.setText("");

                Errors error = new Errors();
                String tmp = inputData.getText();

                System.out.println(error.isCorrect(outputData.getText(), err.getBits()));
                inputData.setText(error.rotateArray(tmp));
                //kolorowanie i wypisywanie
                setColourForEachBit(tmp);

                inputData.setText(err.getData());


                //kolorowanie i wypisywanie bitow parzystosci
                textField1.setText("");
                setColourForParityBits(err.getBits());
            }
        });

    }

    public static void main(String[] args) {
        frame = new JFrame("RAID");
        frame.setContentPane(new MainWindow().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //wycentrowanie okna
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}

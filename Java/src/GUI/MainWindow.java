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
        StyledDocument doc = outputData.getStyledDocument();

        Style style = outputData.addStyle("Red coloured text", null);
        StyleConstants.setForeground(style, Color.red);

        Style style2 = outputData.addStyle("Black coloured text", null);
        StyleConstants.setForeground(style2, Color.black);

        for(int i=0; i<error.length(); i++) {
            if(inputData.getText().charAt(i) != error.charAt(i)){
                try {
                    doc.insertString(i, String.valueOf(error.charAt(i)), style);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    doc.insertString(i, String.valueOf(error.charAt(i)), style2);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
        Metoda koloruje bledne bity parzystosci
     */
    private void setColourForParityBits(String error){

        StyledDocument doc = textField1.getStyledDocument();

        Style style = textField1.addStyle("Red coloured text", null);
        StyleConstants.setForeground(style, Color.red);

        Style style2 = textField1.addStyle("Black coloured text", null);
        StyleConstants.setForeground(style2, Color.black);

        System.out.println(outputData.getText());
        System.out.println(error);
        int j = 0;
        for(int i=0; i<outputData.getText().length(); i+=2) {
            if((outputData.getText().charAt(i) != outputData.getText().charAt(i+1) && error.charAt(j) == 0) ||
                    (outputData.getText().charAt(i) == outputData.getText().charAt(i+1) && error.charAt(j) == 1)){
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
                        outputData.setText("");
                        setColourForEachBit(err.getData());
                        inputData.setText(err.getData());

                        Errors error = new Errors();
                        System.out.println(error.isCorrect(outputData.getText(), err.getBits()));
                    } else {
                        inputData.setText(err.getData());
                        outputData.setText("Podaj na wejsciu tylko bity");
                    }
                } catch(ArrayIndexOutOfBoundsException aIOOBE){
                    outputData.setText("Podaj na wejsciu poprawna ilosc bitow");
                }
                textField1.setText("");
                setColourForParityBits(err.getBits());
            }
        });
        zmien1LosowyBitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputData.setText("");
                Errors error = new Errors();
                String tmp = inputData.getText();

                System.out.println(inputData.getText());
                System.out.println(outputData.getText());

                System.out.println(error.isCorrect(outputData.getText(), err.getBits()));

                setColourForEachBit(error.generateError(tmp));


                textField1.setText("");
                setColourForParityBits(err.getBits());
            }
        });
        odwrocWszystkieBityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputData.setText("");
                Errors error = new Errors();
                String tmp = inputData.getText();

                System.out.println(error.isCorrect(outputData.getText(), err.getBits()));

                setColourForEachBit(error.rotateArray(tmp));


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

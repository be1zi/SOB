package GUI;

import RAID.Errors;
import RAID.Filess;

import javax.swing.*;
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
    private JTextArea inputData;
    private JTextArea outputData;
    private JTextField textField1;
    private JButton zatwierdzButton;



    /*
        Metoda sprawdza czy poprawnie wprowadzono dane
     */
    private Boolean checkIfBit(){
        for(int i=0; i<inputData.getText().length(); i++){
            if(inputData.getText().charAt(i) != '0' || inputData.getText().charAt(i) != '1'){
                return false;
            }
        }

        return true;
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
                String tmp=inputData.getText();
                outputData.setText(tmp);
                inputData.setText(err.getData());

                Errors error = new Errors();
                System.out.println(error.isCorrect(outputData.getText(), err.getBits()));
            }
        });
        zmien1LosowyBitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Errors error = new Errors();
                String tmp=inputData.getText();
                //inputData.setText(tmp);
                outputData.setText(error.generateError(tmp));
                System.out.println(inputData.getText());
                System.out.println(outputData.getText());

                System.out.println(error.isCorrect(outputData.getText(), err.getBits()));
            }
        });
        odwrocWszystkieBityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Errors error = new Errors();
                String tmp=inputData.getText();
                outputData.setText(error.rotateArray(tmp));

               System.out.println(error.isCorrect(outputData.getText(), err.getBits()));
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RAID");
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

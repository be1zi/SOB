package GUI;

import RAID.Errors;

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
        Errors err = new Errors();
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
            }
        });
        zmien1LosowyBitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

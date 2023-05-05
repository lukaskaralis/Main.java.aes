package com.company;

import javax.crypto.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
public class Main {

    public static String input;
    public static String password;
    public static String cipherText;
    public static String plainText;
    public static String mod;
    public static String str;

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {

        JFrame frame = new JFrame();
        frame.setSize(550,300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Write code to encrypt: ");
        label.setBounds(20,10,200,20);
        frame.add(label);

        JTextField textField = new JTextField();
        textField.setBounds(20,40,150,20);
        frame.add(textField);

        JLabel label1 = new JLabel("Write your key with 16 symbols: ");
        label1.setBounds(20,60,200,20);
        frame.add(label1);

        JTextField textField1 = new JTextField();
        textField1.setBounds(20,90,150,20);
        frame.add(textField1);

        JTextField textField2 = new JTextField();
        textField2.setBounds(20,200,300,40);
        frame.add(textField2);

        JRadioButton cbcc = new JRadioButton("CBC");
        cbcc.setBounds(20,120,60,30);
        cbcc.setSelected(true);
        frame.add(cbcc);

        JRadioButton ofbb = new JRadioButton("OFB");
        ofbb.setBounds(90,120,60,30);
        frame.add(ofbb);

        JRadioButton cfbb = new JRadioButton("CFB");
        cfbb.setBounds(160,120,60,30);
        frame.add(cfbb);
        ButtonGroup bg=new ButtonGroup();
        bg.add(cfbb);bg.add(cbcc);bg.add(ofbb);





        JButton button1 = new JButton("Save to file");
        button1.setBounds(380,200,100, 40);
        frame.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileWriter writer = null;
                try {
                    writer = new FileWriter("Encypted.txt");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    writer.write(textField2.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                textField2.setText("");textField.setText("");
            }
        });


        JButton button2 = new JButton("Decrypt");
        button2.setBounds(130,150,100, 40);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbcc.isSelected()){
                    mod = "AES/CBC/PKCS5PADDING";
                }if(cfbb.isSelected()){
                    mod = "AES/CFB/PKCS5PADDING";
                }if(ofbb.isSelected()){
                    mod = "AES/OFB/PKCS5PADDING";
                }
                input = textField.getText();
                password = textField1.getText();
                startDecrypt();
                textField2.setText(cipherText);
                if(textField.getText().equals("") || textField1.getText().equals("")){
                    textField2.setText("");
                }
                textField.setText("");
            }
        });

        JButton button3 = new JButton("Get from file");
        button3.setBounds(200,20,150, 40);
        frame.add(button3);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    str = Files.readString(Path.of("Encypted.txt"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                textField.setText(str);
            }
        });

                JButton button = new JButton("Encrypt");
        button.setBounds(20,150,100, 40);
        frame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbcc.isSelected()){
                    mod = "AES/CBC/PKCS5PADDING";
                }if(cfbb.isSelected()){
                    mod = "AES/CFB/PKCS5PADDING";
                }if(ofbb.isSelected()){
                    mod = "AES/OFB/PKCS5PADDING";
                }
                input = textField.getText();
                password = textField1.getText();
                start();
                textField2.setText(plainText);
                if(textField.getText().equals("") || textField1.getText().equals("")){
                    textField2.setText("");
                }
            }
        });

    }

    private static void startDecrypt() {
        String decryptedString = Encrypt.decrypt(input);
        cipherText = decryptedString;
    }

    public static void start() {
        String encryptedString = Encrypt.encrypt(input);
        plainText = encryptedString;
    }
}

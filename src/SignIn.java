import javax.swing.*;
import java.sql.Connection;
import java.util.Base64;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

public class SignIn implements ActionListener {

    JPanel panelOne, panelTwo, panelThree;
    JFrame jFrame;
    JLabel tvName, tvPword;
    JButton btnRegister, btnLogin;
    String uName, pWord;
    HashMap<String,String> credentialData;
    JTextField textFieldUserName;
    JPasswordField passwordFieldLogin;
    File pFile;
    FileInputStream pwFis;
    FileOutputStream pwFos;

    SignIn(){


        credentialData = new HashMap<>();
        pFile = new File("/Users/sandeepgautam/IdeaProjects/GoodOlLoginAgain/src/PasswordFile");

        try {
            pwFis = new FileInputStream(pFile);
        } catch (FileNotFoundException fnf){
            JOptionPane.showMessageDialog(null,
                    "Password integrity breached, re-download the program and try again!","Missing Program Integrity",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(4);
        }

        try{
            pwFos = new FileOutputStream(pFile, true);
        } catch (FileNotFoundException fnf){
            JOptionPane.showMessageDialog(null,
                    "Password integrity breached, re-download the program and try again!","Missing Program Integrity",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(4);
        }

        jFrame = new JFrame("MIS");

        panelOne = new JPanel();

        tvName = new JLabel("Username: ");
        tvName.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        tvPword = new JLabel("Password: ");
        tvPword.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        btnRegister = new JButton("REGISTER");
        btnLogin = new JButton("LOGIN");

        btnRegister.addActionListener(this);
        btnLogin.addActionListener(this);

        textFieldUserName = new JTextField();
        textFieldUserName.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        passwordFieldLogin = new JPasswordField();
        passwordFieldLogin.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        panelOne.setLayout(new GridLayout(3,2,40,20));
        panelOne.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panelOne.add(tvName);
        panelOne.add(textFieldUserName);

        panelOne.add(tvPword);
        panelOne.add(passwordFieldLogin);

        panelOne.add(btnRegister);
        panelOne.add(btnLogin);

        jFrame.setContentPane(panelOne);

        jFrame.setDefaultCloseOperation(3);
        jFrame.setSize((192*2),(108*2));
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "REGISTER"){
            uName = textFieldUserName.getText().trim();
            pWord = new String(passwordFieldLogin.getPassword()).trim();
            boolean isAdded = credentialWriter(uName,pWord,pwFos);
            if(isAdded){
                JOptionPane.showMessageDialog(null,"Credential added successfully!",
                        "Success",JOptionPane.INFORMATION_MESSAGE);
            }
            textFieldUserName.setText(null);
            passwordFieldLogin.setText(null);
        }
        else if(e.getActionCommand() == "LOGIN"){
            String un = textFieldUserName.getText();
            String pw = new String(passwordFieldLogin.getPassword());

            long size = pFile.length();
            HashMap<String,String> dataFromFile = credentialReader(pwFis,size);

            if(dataFromFile.containsKey(un)){
                if(pw.equals(dataFromFile.get(un))){
                    new Dashboard();
                    jFrame.dispose();

                } else {
                    JOptionPane.showMessageDialog(null,"Incorrect username or password, try again!",
                            "Incorrect Credentials",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "The entered account does not exist, please register!","Account Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public HashMap<String,String> credentialReader(FileInputStream fis, long size){
        HashMap<String,String> cred = new HashMap<>();

        byte[] b = new byte[(int) size];

        try{
           fis.read(b);
        } catch (IOException ioe){
            JOptionPane.showMessageDialog(null,
                    "File Integrity Breached, Re-download and Try Again!",
                    "Integrity Failed!",JOptionPane.ERROR_MESSAGE);
        }

        String allDat = new String(b);
        System.out.println(allDat);

        String[] one = allDat.split("\n");
        String[] two;
        System.out.println(one.length);
        for(int j=0; j<one.length; ++j){
            two = one[j].split(" ");

            cred.put(two[0].trim(),two[1].trim());
        }

        try{
            fis.close();
        } catch (IOException ioe){

        }
        return cred;
    }

    public boolean credentialWriter(String userName, String passWord, FileOutputStream fos){
        boolean addSuccessful = false;
        String finalDatString = userName + " " + passWord + "\n";
        byte[] byteDat = finalDatString.getBytes(StandardCharsets.UTF_8);

        try {
            fos.write(byteDat);
            addSuccessful = true;
        } catch (IOException fnf){
            JOptionPane.showMessageDialog(null,
                    "File Integrity Breached, Re-download and Try Again!","Integrity Failed!",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(4);
        }
        try{
            fos.close();
        } catch (IOException ioe){

        }
        return addSuccessful;
    }


    public static void main(String[] args) { new SignIn(); }
}

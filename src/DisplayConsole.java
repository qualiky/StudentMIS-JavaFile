import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DisplayConsole {
    JFrame jFrame;
    JTable jTable;
    DefaultTableModel tableModel;

    public DisplayConsole(){


        jFrame = new JFrame("Display Console");

        tableModel = new DefaultTableModel();

        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Stream");
        tableModel.addColumn("Address");
        tableModel.addColumn("Roll number");
        tableModel.addColumn("Contact");
        tableModel.addColumn("Java");
        tableModel.addColumn("Macroeconomics");
        tableModel.addColumn("Mathematics");
        tableModel.addColumn("English");
        tableModel.addColumn("Total");

        jTable = new JTable(tableModel);
        int sz = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/sandeepgautam/IdeaProjects/GoodOlLoginAgain/src/StudentDataFile.txt"));
            while(br.readLine() != null) {
                sz++;
            }
        } catch (IOException ioe) {

        }

        System.out.println(sz);

        int i=0;

        Student[] finalArr = readFile();

        System.out.println(finalArr.length);

        for(i=0; i<finalArr.length; ++i){



            tableModel.addRow(new Object[] {
                    finalArr[i].getFirstName(),
                    finalArr[i].getLastName(),
                    finalArr[i].getGender(),
                    finalArr[i].getStream(),
                    finalArr[i].getAddress(),
                    finalArr[i].getRollNum(),
                    finalArr[i].getContact(),
                    finalArr[i].getSubJava(),
                    finalArr[i].getSubMacro(),
                    finalArr[i].getSubMaths(),
                    finalArr[i].getSubEnglish(),
                    (finalArr[i].getSubJava() +  finalArr[i].getSubMacro() + finalArr[i].getSubMaths() + finalArr[i].getSubEnglish())
            });
        }

        jTable.setPreferredScrollableViewportSize(new Dimension(900,10));
        jTable.setFillsViewportHeight(true);

        JScrollPane jsp = new JScrollPane(jTable);

        jFrame.add(jsp);
        jFrame.setSize(500,400);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public Student[] readFile() {

        Student[] st = new Student[1000];
        String line = "";
        Student s = new Student();
        int i = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/sandeepgautam/IdeaProjects/GoodOlLoginAgain/src/StudentDataFile.txt"));

            while(br.readLine()!= null){
                System.out.println("Data is: " + br.readLine());
                line = br.readLine();
                String[] arr = line.split(" ");

                //val = name + " " + address + " " + stream + " " + gender + " " + contact + " " +rollNum + " " + java + " " + english + " " + macro + " " + math + "\n";

                s.setFirstName(arr[0]);
                s.setLastName(arr[1]);
                s.setAddress(arr[2]);
                s.setStream(arr[3]);

                switch (arr[4]){
                    case "1" : s.setGender(s.GENDER_FEMALE); break;
                    case "2" : s.setGender(s.GENDER_MALE); break;
                    default: s.setGender(s.GENDER_OTHER);
                }

                s.setContact(arr[5]);
                s.setRollNum(Long.parseLong(arr[6]));
                s.setSubJava(Double.parseDouble(arr[7]));
                s.setSubEnglish(Double.parseDouble(arr[8]));
                s.setSubMacro(Double.parseDouble(arr[9]));
                s.setSubMaths(Double.parseDouble(arr[10]));

                st[i] = s;
                i++;
            }

        } catch (FileNotFoundException fnf){
            JOptionPane.showMessageDialog(null,
                    "File Integrity Breached, download and install the program again!",
                    "File Integrity Breached",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException ioe) {

        }

        return st;
    }

}

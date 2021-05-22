import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Dashboard extends JFrame implements ActionListener {

    JFrame frameDashboard;
    JPanel panelDashboard, panelGender;
    JLabel tvName, tvAddress, tvStream, tvGender, tvContact, tvRollNum, tvMath, tvEnglish, tvJava, tvMacro;
    JTextField tbName, tbAddress, tbContact, tbRollNum, tbMath, tbEnglish, tbJava, tbMacro;
    JRadioButton btnMale, btnFemale, btnOther;
    JComboBox<String> listStream;
    ButtonGroup gbGroup;
    String[] streamListData = {"BBA","BBA-BI","BBA-TT","BCIS","MBA","MBA-IT"};
    JButton btnAdd, btnDisplay;
    FileOutputStream fos;
    FileWriter fw;
    File f;
    String val;

    public Dashboard(){

        frameDashboard = new JFrame("Student Dashboard");

        panelDashboard = new JPanel(new GridLayout(11,2,30,30));
        panelDashboard.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        tvName = new JLabel("Name: ");
        tvName.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tvAddress = new JLabel("Address: ");
        tvAddress.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tvStream = new JLabel("Stream: ");
        tvStream.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tvGender = new JLabel("Gender: ");
        tvGender.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tvContact = new JLabel("Contact: ");
        tvContact.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tvRollNum = new JLabel("Roll Number: ");
        tvRollNum.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tbName = new JTextField();
        tbName.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

        tbAddress = new JTextField();
        tbAddress.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

        tbContact = new JTextField();
        tbContact.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

        tbRollNum = new JTextField();
        tbRollNum.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

        tvMath = new JLabel("Marks of Math: ");
        tvMath.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tbMath = new JTextField();
        tbMath.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

        tvEnglish = new JLabel("Marks of English: ");
        tvEnglish.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tbEnglish = new JTextField();
        tbEnglish.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

        tvJava = new JLabel("Marks of Java: ");
        tvJava.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tbJava = new JTextField();
        tbJava.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

        tvMacro = new JLabel("Marks of Macro: ");
        tvMacro.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tbMacro = new JTextField();
        tbMacro.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));


        listStream = new JComboBox<>(streamListData);

        btnMale = new JRadioButton("Male");
        btnMale.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        btnFemale = new JRadioButton("Female");
        btnFemale.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        btnOther = new JRadioButton("Other");
        btnOther.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        gbGroup = new ButtonGroup();
        gbGroup.add(btnMale);
        gbGroup.add(btnFemale);
        gbGroup.add(btnOther);

        btnAdd = new JButton("ADD");
        btnAdd.addActionListener(this);

        btnDisplay = new JButton("DISPLAY");
        btnDisplay.addActionListener(this);


        panelGender = new JPanel(new FlowLayout());
        panelGender.add(btnMale);
        panelGender.add(btnFemale);
        panelGender.add(btnOther);

        panelDashboard.add(tvName);
        panelDashboard.add(tbName);
        panelDashboard.add(tvStream);
        panelDashboard.add(listStream);
        panelDashboard.add(tvRollNum);
        panelDashboard.add(tbRollNum);
        panelDashboard.add(tvContact);
        panelDashboard.add(tbContact);
        panelDashboard.add(tvGender);
        panelDashboard.add(panelGender);
        panelDashboard.add(tvAddress);
        panelDashboard.add(tbAddress);
        panelDashboard.add(tvMath);
        panelDashboard.add(tbMath);
        panelDashboard.add(tvEnglish);
        panelDashboard.add(tbEnglish);
        panelDashboard.add(tvJava);
        panelDashboard.add(tbJava);
        panelDashboard.add(tvMacro);
        panelDashboard.add(tbMacro);
        panelDashboard.add(btnDisplay);
        panelDashboard.add(btnAdd);

        frameDashboard.add(panelDashboard);
        frameDashboard.setDefaultCloseOperation(3);
        frameDashboard.setSize((192*4),(108*7));
        frameDashboard.setLocationRelativeTo(null);
        frameDashboard.setVisible(true);

        f = new File("/Users/sandeepgautam/IdeaProjects/GoodOlLoginAgain/src//StudentDataFile.txt");

        try {
            fos = new FileOutputStream(f);
        } catch (IOException ioe){
            JOptionPane.showMessageDialog(null,
                    "File Integrity Breached, download and install the program again!",
                    "File Integrity Breached",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(4);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Student s = new Student();
        if(e.getActionCommand()=="ADD"){
            boolean yOn = studentDataWriter();
            if(yOn){
                JOptionPane.showMessageDialog(null,
                        "User data added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "User data could not be added!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getActionCommand() == "DISPLAY"){
            new DisplayConsole();
        }
    }

    public boolean studentDataWriter() {
        boolean didCorrectly = false;

        Student s = new Student();
        //getting the required data

        try {


        String name = tbName.getText();
        String[] nm = name.split(" ");
        String address = tbAddress.getText();
        String stream = (String) listStream.getSelectedItem();
        long rollNum = Long.parseLong(tbRollNum.getText());
        String contact = tbContact.getText();
        double java = Double.parseDouble(tbJava.getText());
        double macro = Double.parseDouble(tbMacro.getText());
        double math = Double.parseDouble(tbMath.getText());
        double english = Double.parseDouble(tbEnglish.getText());

        //getting the gender from radio buttons
        int gender = 0;
        if (btnMale.isSelected())
            gender = s.GENDER_MALE;
        else if (btnFemale.isSelected())
            gender = s.GENDER_FEMALE;
        else if (btnOther.isSelected())
            gender = s.GENDER_OTHER;

        if (tbName.getText() == null)
            tbName.setBackground(Color.RED);
        else{
            s.setFirstName(nm[0]);
            s.setLastName(nm[1]);
            System.out.println(s.getLastName() + " and " + s.getFirstName());
        }


        if (tbAddress.getText() == null)
            tbAddress.setBackground(Color.RED);
        else
            s.setAddress(address);

        if (tbRollNum.getText() == null)
            tbRollNum.setBackground(Color.RED);
        else
            s.setRollNum(rollNum);

        if (tbContact.getText() == null)
            tbContact.setBackground(Color.RED);
        else
            s.setContact(contact);

        if (tbJava.getText() == null)
            tbJava.setBackground(Color.RED);
        else
            s.setSubJava(java);

        if (tbMacro.getText() == null)
            tbMacro.setBackground(Color.RED);
        else
            s.setSubMacro(macro);

        if (tbMath.getText() == null)
            tbMath.setBackground(Color.RED);
        else
            s.setSubMaths(math);

        if (tbEnglish.getText() == null)
            tbEnglish.setBackground(Color.RED);
        else
            s.setSubEnglish(english);

        s.setStream(stream);
        s.setGender(gender);

            val = s.getFirstName() + " " + s.getLastName() + " " + address + " " + stream + " " + gender + " " + contact + " " +rollNum + " " + java + " " + english + " " + macro + " " + math + "\n";

    } catch (NullPointerException np){
            JOptionPane.showMessageDialog(null,"File error, cannot write!","Error",JOptionPane.ERROR_MESSAGE);
        }


        try {
            fos.write(val.getBytes(StandardCharsets.UTF_8));
            System.out.println(val);
            didCorrectly = true;
        } catch (IOException ioe){
            JOptionPane.showMessageDialog(null,
                    "File Integrity Breached, download and install the program again!",
                    "File Integrity Breached",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(4);
        }

        return didCorrectly;
    }

}

package palindrometester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PalindromeTesterUI{
    public String testWord;
    private String password;
    public Boolean alphaOnly = false;
    public Boolean alphanumericOnly = false;
    public Boolean caseSensitive = false;
    public Boolean allowSpace = false;
    public Boolean ignoreSpace = false;
    public Boolean ignorePunctuation = false;
    public Boolean upload = false;
        public PalindromeTesterUI(){
            final Frame f = new Frame("Palindrome Tester");
            final JTextArea infoText = new JTextArea();
            infoText.setBounds(30,60,350,40);
            infoText.setSize(350, 40);
            Font f1 = new Font("Ariel", Font.BOLD, 20);
            infoText.setFont(f1);
            infoText.setLineWrap(true);
            infoText.setWrapStyleWord(true);
            final JTextArea resultText = new JTextArea();
            resultText.setBounds(20,330,375,70);
            resultText.setSize(375, 70);
            resultText.setLineWrap(true);
            resultText.setWrapStyleWord(true);
            final Checkbox checkbox1 = new Checkbox("Alphabetic Characters Only");
            checkbox1.setBounds(50, 110, 170, 30);
            final Checkbox checkbox2 = new Checkbox("Alphanumeric Characters Only");
            checkbox2.setBounds(50, 140, 185, 30);
            final Checkbox checkbox3 = new Checkbox("Allow Space");
            checkbox3.setBounds(50, 170, 100, 30);
            final Checkbox checkbox4 = new Checkbox("Case Sensitive");
            checkbox4.setBounds(50, 200, 100, 30);
            final Checkbox checkbox5 = new Checkbox("Ignore Space");
            checkbox5.setBounds(200, 170, 100, 30);
            final Checkbox checkbox6 = new Checkbox("Ignore Punctuation");
            checkbox6.setBounds(200, 200, 150, 30);
            final TextField testField = new TextField(testWord);
            testField.setBounds(50,250, 300, 40);
            final Button B1 = new Button("Test Now");
            B1.setBounds(125,300,150, 30);
            final Checkbox checkbox7 = new Checkbox("Optional: Upload palindrome to database?");
            checkbox7.setBounds(50, 400, 300, 30);
            final TextField testField2 = new TextField(password);
            testField2.setBounds(50,450, 300, 30);
            final Label passwordLabel = new Label("enter database password:");
            passwordLabel.setBounds(50, 420, 300, 40);
            infoText.setText("Please select criteria for palindrome:");
            f.add(infoText);
            f.add(checkbox1);
            f.add(checkbox3);
            f.add(checkbox2);
            f.add(checkbox4);
            f.add(checkbox5);
            f.add(checkbox6);
            f.add(resultText);
            f.add(testField);
            f.add(B1);
            f.add(checkbox7);
            checkbox1.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange()==1){
                        alphaOnly = true;
                        alphanumericOnly = false;
                        checkbox2.setState(false);
                    }
                    else if(e.getStateChange()!=1){
                        alphaOnly = false;
                    }
                }
            });
            checkbox2.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange()==1){
                        alphanumericOnly = true;
                        alphaOnly = false;
                        checkbox1.setState(false);
                    }
                    else if(e.getStateChange()!=1){
                        alphanumericOnly = false;
                    }
                }
            });
            checkbox3.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange()==1){
                        allowSpace = true;
                        ignoreSpace = false;
                        checkbox5.setState(false);
                    }
                    else if(e.getStateChange()!=1){
                        allowSpace = false;
                    }
                }
            });
            checkbox4.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange()==1){
                        caseSensitive = true;
                    }
                    else if(e.getStateChange()!=1){
                        caseSensitive = false;
                    }
                }
            });
            checkbox5.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange()==1){
                        ignoreSpace = true;
                        allowSpace = false;
                        checkbox3.setState(false);
                    }
                    else if(e.getStateChange()!=1){
                        ignoreSpace = false;
                    }
                }
            });
            checkbox6.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    ignorePunctuation = e.getStateChange() == ItemEvent.SELECTED;
                }
            });
            checkbox7.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange()==1){
                        upload = true;
                        f.add(testField2);
                        f.add(passwordLabel);
                    }
                    else if(e.getStateChange()!=1){
                        upload = false;
                        f.remove(testField2);
                        f.remove(passwordLabel);
                    }
                }
            });
            B1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    testWord = testField.getText();
                    Palindrome P1 = new Palindrome(testWord, alphaOnly, alphanumericOnly, caseSensitive, allowSpace, ignoreSpace, ignorePunctuation);
                    if(P1.IsPalindrome()==true){
                        resultText.setText(testField.getText() + " is a Palindrome.");
                        if(upload){
                            password = testField2.getText();
                            DBConnector DB = new DBConnector();
                            try{
                                DB.setConnection(password);
                                try{
                                    if(DB.checkDB(testWord)){
                                        resultText.setText(testField.getText() + " is already in the DB.");
                                    }
                                    else{
                                        try{
                                            DB.addPalindrome(testWord, alphaOnly, alphanumericOnly, caseSensitive, allowSpace, ignoreSpace, ignorePunctuation);
                                            resultText.setText(testField.getText() + " has been added to the DB");
                                        }
                                        catch (Exception ex){
                                            resultText.setText(ex.toString());
                                        }
                                    }
                                }
                                catch (Exception ex){
                                    resultText.setText(ex.toString());
                                }
                                }
                                catch (Exception ex){
                                    resultText.setText(ex.toString());
                                }


                        }
                    }
                    else{
                        resultText.setText(testField.getText() + " is NOT a Palindrome.");
                    }

                }
            });
            f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent we)
                {
                    System.exit(0);
                }
            });
            f.setSize(400, 500);
            f.setLayout(null);
            f.setVisible(true);
        }

        public static void main(String args[]){
        new PalindromeTesterUI();
        }
}

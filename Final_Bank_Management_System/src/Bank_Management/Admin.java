package Bank_Management;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Admin extends JFrame  implements ActionListener {

    String adminNo,adminPin;
//    Conn c = new Conn();
    JButton btnLoadData,date,searchButton,deleteButton,exit;
    JTextArea area;
    String searchText;
    String originalText;
    JTextField searchField,TextField;
    JLabel TextField1;
    JDateChooser dateChooser;
    Admin(){



        setLayout(null);
        setTitle("Admin Account View");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(("icons/admin2.png")));
        Image i2 = i1.getImage().getScaledInstance(788,730, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); //need to create new imageIcon as we cant directly pass image to JLable
        JLabel j1 = new JLabel(i3);
        j1.setBounds(0,0,788,725);
        add(j1);

        area = new JTextArea();
//        area.setForeground(Color.WHITE);
//        area.setFont(new Font("Osward",Font.BOLD,38));
//        area.setBounds(180,190,500,100);
//        j1.add(area);

        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);
        area.setFont(new Font("Raleway",Font.BOLD,15));
        scrollPane.setPreferredSize(new Dimension(400, 300));
        scrollPane.setBounds(200,160,550,200);

        j1.add(scrollPane);

        searchField = new JTextField();
        searchField.setFont(new Font("Raleway",Font.BOLD,15));
        searchField.setBounds(200,390,250,35);
        j1.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setForeground(Color.YELLOW);
        searchButton.setFont(new Font("Raleway",Font.BOLD,15));
        searchButton.setBackground(new Color(134,0,224) );
        searchButton.setBounds(30,390,165,35);
        searchButton.addActionListener(this);
        j1.add(searchButton);

        deleteButton = new JButton("Delete");
        deleteButton.setForeground(Color.YELLOW);
        deleteButton.setFont(new Font("Raleway",Font.BOLD,15));
        deleteButton.setBackground(new Color(134,0,224) );
        deleteButton.addActionListener(this);
        deleteButton.setBounds(30,455,165,35);
        j1.add(deleteButton);

        TextField = new JTextField("Enter the Account No.");
        TextField.setFont(new Font("Raleway",Font.BOLD,15));
        TextField.setBounds(200,455,250,35);
        TextField.addActionListener(this);
        TextField.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                // Clear the text of the JTextField when it gains focus
                TextField.setText("");
            }

            public void focusLost(FocusEvent e) {
                // Do nothing when focus is lost
//                TextField.setText("Enter the Account No");
            }
        });
        j1.add(TextField);

        btnLoadData = new JButton("Load Data");
        btnLoadData.setForeground(Color.YELLOW);
        btnLoadData.setFont(new Font("Raleway",Font.BOLD,15));
        btnLoadData.setBackground(new Color(134,0,224) );
        btnLoadData.setBounds(30,160,165,40);
        btnLoadData.addActionListener(this);
        j1.add(btnLoadData);

        date = new JButton("Transaction History" );
        date.setForeground(Color.YELLOW);
        date.setFont(new Font("Raleway", Font.BOLD,15));
        date.setBackground(new Color(134,0,224) );
        date.setBounds(200,520,200,35);
        date.addActionListener(this);
        j1.add(date);

        exit  = new JButton("Exit");
        exit.setBounds(450,520,130,35);
        exit.setForeground(Color.YELLOW);
        exit.setBackground(new Color(255,0,0) );
        exit.addActionListener(this);
        j1.add(exit);


        setSize(800,750);
        setVisible(true);
//        setUndecorated(true);
        setLocation(350 , 50);
    }


    public  void loadData(){
        area.setText(""); // Clear the text area
        try {

            Conn c = new Conn();
            // Execute a SELECT query to fetch all accounts from the "bank" table
            String query = "SELECT * FROM login";

            ResultSet rss = c.s.executeQuery(query);

            // Loop through the result set and append the account details to the text area
            StringBuilder sb = new StringBuilder();
            sb.append("   IFSC Code\t\tAccount Number\tAccount Holder\tAccount Type\t\tBalance\n");
            while (rss.next()) {
                String accountNumber = rss.getString("Account_No");
                String ifsc = rss.getString("IFSC");
                String accountHolderName = rss.getString("Name");
                String balance = rss.getString("Balance");
                String accountType = rss.getString("Type");

//                String accountInfo = "     IFSC Code : " + ifsc + "\n"
//                        + "    Account Number: " + accountNumber + "\n"
//                        + "    Account Holder Name: " + accountHolderName + "\n"
//                        + "    Balance: Rs " + balance + "\n"
//                        + "    Account Type: " + accountType + "\n\n";

//                sb.append(accountInfo);
                sb.append(String.format("   %-15s\t%-10s\t%-10s\t\t%-10s\t%-10s\n",
                        ifsc, accountNumber, accountHolderName,
                        accountType, balance));

            }
            area.setText(sb.toString());
//            c.close();
//            rss.close();


        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Failed to load data from database.", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnLoadData) {
            loadData();
        }
        if (ae.getSource() == searchButton) {

            loadData();
            String searchTerm = (searchField.getText()).toUpperCase();
            String text = (area.getText()).toUpperCase();
            if (originalText == null) {
                originalText = text;
            }
            area.setText(""); // Clear the text area
            if (!searchTerm.isEmpty()) {
                String[] lines = originalText.split("\\n"); // Split text into lines
                for (String line : lines) {
                    if (line.contains(searchTerm)) {
                        area.append(line + "\n"); // Add line to text area if it contains the search term
                    }
                }
            } else {
                area.setText(originalText); // If search term is empty, show original text
            }
        }

        if(ae.getSource()==deleteButton) {
//			area.setText("Hello");

            String deleteTerm =TextField.getText().toLowerCase();
            Conn c = new Conn();
            try {
                c.s.executeUpdate("delete from login where Account_No = '"+deleteTerm +"';");
                area.setText("");
                loadData();

            }catch(Exception e) {
                System.out.println(e);
            }

        }
        if(ae.getSource()==date){

            setVisible(false);
            new History().setVisible(true);
        }
        if(ae.getSource()==exit){
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Admin();
    }
}

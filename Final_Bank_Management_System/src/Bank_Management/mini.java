

package Bank_Management;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mini extends JFrame implements ActionListener {

    JDateChooser dateChooser;
    JButton date;
    Date currentDate;
    Date selectedDate;
    mini() {
        setLayout(null);
        // Create a JDateChooser object
         dateChooser = new JDateChooser();

        // Get the selected date from the JDateChooser


        // Create a Date object representing the current date
        currentDate = new Date();

        date = new JButton("Transaction" );
        date.setForeground(Color.YELLOW);
        date.setFont(new Font("Raleway", Font.BOLD,15));
        date.setBackground(new Color(134,0,224) );
        date.setBounds(30,520,165,35);
        date.addActionListener(this);
        add(date);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(200,520,250,35);
        dateChooser.setForeground(Color.RED);
        add(dateChooser);

        // Compare the two dates


        setSize(800,750);
        setVisible(true);
//        setUndecorated(true);
        setLocation(350 , 50);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==date){
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate1 = sdf2.format(currentDate);
            System.out.println("Date object  "+formattedDate1);

            selectedDate = dateChooser.getDate();
            int comparisonResult = currentDate.compareTo(selectedDate);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(selectedDate);
            System.out.println(selectedDate);
            System.out.println(formattedDate);
            if (comparisonResult < 0) {
                System.out.println("Current date is earlier than selected date.");
            } else if (comparisonResult > 0) {
                System.out.println("Current date is later than selected date.");
            } else {
                System.out.println("Current date is equal to selected date.");
            }
        }
    }
    public static void main(String[] args) {
            new mini();

    }
}

//
//
//
//
//
//
//
//
//
//
//
////import java.awt.BorderLayout;
////import java.awt.Color;
////import java.awt.Dimension;
////import java.awt.DisplayMode;
////import java.awt.Font;
////import java.awt.Rectangle;
////import java.awt.event.ActionEvent;
////import java.awt.event.ActionListener;
////import java.awt.geom.Area;
////import java.sql.ResultSet;
////import javax.swing.JButton;
////import javax.swing.JFrame;
////import javax.swing.JOptionPane;
////import javax.swing.JScrollPane;
////import javax.swing.JTextArea;
////import javax.swing.JTextField;
////import javax.swing.text.BadLocationException;
////import javax.swing.text.DefaultHighlighter;
////import javax.swing.text.Document;
////import javax.swing.text.Highlighter;
////
////public class AdminScreen extends JFrame implements ActionListener{
////    JButton btnButton, btnButton1;
////    JTextArea area;
////    JTextField TextField, TextField1;
////    String original = "";
////    String arr[] = {""};
////    Conn c = new Conn();
////
////    Highlighter.HighlightPainter painter =
////            new DefaultHighlighter.DefaultHighlightPainter( Color.cyan );
////
////
////    public AdminScreen() {
////        // TODO Auto-generated constructor stub
////
////        setTitle("Online VIIT Bank");
////
////        setLayout(null);
////
////        area = new JTextArea();
////        area.setEditable(false);
////        JScrollPane scrollPane = new JScrollPane(area);
////        scrollPane.setPreferredSize(new Dimension(1800, 400));
////        scrollPane.setBounds(100,100,600,200);
////        add(scrollPane, BorderLayout.CENTER);
////
////        area.setText(""); // Clear the text area
////
////        Display();
////        original = area.getText();
////
////        arr = original.split("\n");
////
////        for(int i=0; i<arr.length; i++) {
////            System.out.println(arr[i]);
////        }
////
////
////        btnButton = new JButton("Delete");
////        btnButton.addActionListener(this);
////        btnButton.setBounds(100,500,100,30);
////        add(btnButton);
////
////        btnButton1 = new JButton("Search");
////        btnButton1.addActionListener(this);
////        btnButton1.setBounds(100, 600, 100, 30);
////        add(btnButton1);
////
////        TextField = new JTextField();
////        TextField.setBounds(300,500,220,23);
////        TextField.setFont(new Font("Arial",Font.PLAIN,16));
////        add(TextField);
////
////        TextField1 = new JTextField();
////        TextField1.setBounds(300,600,220,23);
////        TextField1.setFont(new Font("Arial",Font.PLAIN,16));
////        add(TextField1);
////
////
////        getContentPane().setBackground(new Color(194, 197, 204));
////
////        setSize(800,750);
////        setVisible(true);
////        setLocation(350 , 50);
////
////    }
////
////    public static void main(String[] args) {
////        new AdminScreen();
////    }
////
////    @Override
////    public void actionPerformed(ActionEvent e) {
////        // TODO Auto-generated method stub
////        if(e.getSource()==btnButton) {
//////			area.setText("Hello");
////
////            String deleteTerm =TextField.getText().toLowerCase();
////
////            try {
////                c.s.executeUpdate("delete from login where Card_Number = '"+deleteTerm +"';");
////                area.setText("");
////                Display();
////
////            }catch(Exception ae) {
////                System.out.println(ae);
////            }
////
////        }else if(e.getSource()==btnButton1) {
////
////            String searchTerm = TextField1.getText().toLowerCase();
////            String text = area.getText();
////            Highlighter highlighter = area.getHighlighter();
////            highlighter.removeAllHighlights();
////
////            try {
////                Document doc = area.getDocument();
////                String documentText = doc.getText(0, doc.getLength()).toLowerCase();
////                System.out.println(documentText);
////                int searchIndex = 0;
////                if (text.contains(searchTerm)) {
////                    area.setSelectionStart(text.indexOf(searchTerm));
////                    area.setSelectionEnd(text.indexOf(searchTerm) + searchTerm.length());
////                }
////                while ((searchIndex = document.indexOf(searchTerm, searchIndex)) != -1) {
////                    int endIndex = searchIndex + searchTerm.length();
////                    highlighter.addHighlight(searchIndex, endIndex, DefaultHighlighter.DefaultPainter);
////                    searchIndex = endIndex;
////
////                }
////                if (highlighter.getHighlights().length == 0) {
////                    JOptionPane.showMessageDialog(null, "Account not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
////                }else {
////                    area.setCaretPosition(highlighter.getHighlights()[0].getStartOffset());
////                    Rectangle rect = area.modelToView(area.getCaretPosition());
////                    if (rect != null) {
////                        area.scrollRectToVisible(rect);
////                    }
////                }
////            }catch (BadLocationException ex) {
////                ex.printStackTrace();
////            }
////        }
////    }
////
////    public void Display() {
////        try {
////            // Execute a SELECT query to fetch all accounts from the "bank" table
////            String query = "select login.Card_number, signup.Name, login.type, login.balance from login inner join signup on signup.Form_no=login.Form_no;";
////
////            ResultSet rss = c.s.executeQuery(query);
////
////            // Loop through the result set and append the account details to the text area
////            StringBuilder sb = new StringBuilder();
////            String title = "Account Number\tAccount Holder Name\tBalance\tAccount Type\n\n";
////            sb.append(title);
////            while (rss.next()) {
////                String accountNumber = rss.getString("Card_number");
////                String accountHolderName = rss.getString("Name");
////                String balance = rss.getString("balance");
////                String accountType = rss.getString("type");
////
////                String accountInfo =
////                        accountNumber
////                                + "\t" + accountHolderName
////                                + "\t\tRs." + balance
////                                + "\t" + accountType + "\n\n";
////
////                sb.append(accountInfo);
////
////            }
////            area.setText(sb.toString());
////
////        } catch (Exception ex) {
////            ex.printStackTrace();
////            JOptionPane.showMessageDialog(this, "Error: Failed to load data from database.", "Error",
////                    JOptionPane.ERROR_MESSAGE);
////        }
////    }
////
////}
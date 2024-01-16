package Bank_Management;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class History extends JFrame implements ActionListener {

    String formattedDate;
    JButton back,date;
    JDateChooser dateChooser;
    DefaultTableModel model;
    JTextArea area;
    JTable table;
    Date selectedDate;

    History( ){




        setLayout(null);
        setTitle("Transaction History");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Logo1.png"));
        Image i2 = i1.getImage().getScaledInstance(140,140,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(160,10,140,140);
        add(image);

        JLabel text = new JLabel("Vishwakarma Bank");
        text.setBounds(280,60,700,35);
        text.setFont(new Font("System",Font.BOLD,30));
        text.setForeground(Color.BLACK);
        add(text);

//        area.setEditable(false);
        model = new DefaultTableModel();
        table = new JTable(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(table);
//        area.setFont(new Font("Raleway",Font.BOLD,15));
        scrollPane.setPreferredSize(new Dimension(700, 300));
        scrollPane.setPreferredSize(new Dimension(table.getPreferredSize().width, table.getPreferredSize().height)); // Set preferred size to table size

        scrollPane.setBounds(70,130,650,350);
        scrollPane.setViewportView(table);
//        table.setSize(600,500);
//        TableColumn column = table.getColumnModel().getColumn(1);
//        column.setPreferredWidth(150);


        add(scrollPane);



        back = new JButton("Back");
        back.setBounds(300,550,150,30);
        back.setForeground(Color.BLACK);
        back.setBackground(new Color(255,0,0) );
        back.addActionListener(this);
        add(back);

        date = new JButton("Transaction " );
        date.setForeground(Color.YELLOW);
        date.setFont(new Font("Raleway", Font.BOLD,15));
        date.setBackground(new Color(134,0,224) );
        date.setBounds(140,500,165,35);
        date.addActionListener(this);
        add(date);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(310,500,250,35);
        dateChooser.setForeground(Color.RED);
        add(dateChooser);


        setSize(750,650);
        setLocation(500,70);
        setBackground(new Color(255,255,255));
        setUndecorated(true);
        setVisible(true);
        setUndecorated(true);
    }



    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
            setVisible(false);
            new Admin().setVisible(true);
        }
        if(ae.getSource()==date){
            String TrDate = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
            selectedDate = dateChooser.getDate();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = dateFormat.format(selectedDate);

            String[] columnNames = {"Account_No","Date", "Type", "Amount","Balance"};
            String rowData[][] =fetchDataFromDatabase();
            model.setColumnIdentifiers(columnNames);
            // Add the data to the model
            for (Object[] row : rowData) {
                model.addRow(row);
            }
//        // Set preferred width for another column (column index 1)
        TableColumn column = table.getColumnModel().getColumn(1);
//        TableColumn  column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(150);

        }
    }
    private String[][] fetchDataFromDatabase(){

        String [][] rowData = null;
        try{
            Conn c = new Conn();
//            String q = "select Account_no, date , Type , amount,Balance from bank where DATE_FORMAT(date,'%Y-%m-%d')='"+formattedDate+"'";
//            ResultSet rr = c.s1.executeQuery(q);
            String q = "select Account_no, date , Type , amount,Balance from bank where date1='"+formattedDate+"'";
//            PreparedStatement ps = c.getPreparedStatement1(q);

            ResultSet rr = c.s1.executeQuery(q);
            int rowCount = 0;
            if(rr.last()){
                rowCount = rr.getRow();
                rr.beforeFirst();
            }
                rowData = new String[rowCount][5];
            int i=0;

            while(rr.next()){
                 rowData[i][0] = rr.getString("Account_No");
                 rowData[i][1] = rr.getString("date");
                 rowData[i][2] =rr.getString("Type");
                 rowData[i][3] =rr.getString("amount");
                 rowData[i][4] =rr.getString("Balance");
                 i++;

            }


        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

        return  rowData ;
        }

    public static void main(String[] args) {
        new History();

    }
}

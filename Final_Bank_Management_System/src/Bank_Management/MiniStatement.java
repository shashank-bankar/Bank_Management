package Bank_Management;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class MiniStatement extends JFrame implements ActionListener{

    String pinnumber , account_no;
    JButton back;

    JLabel txt1;

            Conn conn = new Conn();
    MiniStatement(String pin,String Acc){
        pinnumber = pin;
        account_no = Acc ;


        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Logo1.png"));
        Image i2 = i1.getImage().getScaledInstance(140,140,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(80,10,140,140);
        add(image);

        txt1 = new JLabel();
        add(txt1);

        JLabel text = new JLabel("Vishwakarma Bank");
        text.setBounds(190,60,700,35);
        text.setFont(new Font("System",Font.BOLD,30));
        text.setForeground(Color.BLACK);
        add(text);

        JLabel account = new JLabel("Account No :"+account_no.substring(0,6)+"XXXXXX"+account_no.substring(12));
        account.setForeground(Color.BLACK);
        account.setFont(new Font("System",Font.BOLD,19));
        account.setBounds(70,160,300,25  );
        add(account);

        Date date = new Date();
        // Create a SimpleDateFormat object with the desired date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(date);
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = sdf1.format(date);

        JLabel date1 = new JLabel("Date : " + formattedDate );
        date1.setForeground(Color.BLACK);
        date1.setFont(new Font("System",Font.BOLD,15));
        date1.setBounds(70,186,300,25  );
        add(date1);

        JLabel time1 = new JLabel("Time : " + formattedTime+"  IST" );
        time1.setForeground(Color.BLACK);
        time1.setFont(new Font("System",Font.BOLD,15));
        time1.setBounds(70,211,300,25  );
        add(time1);

        StringBuilder sb= new StringBuilder();
        sb.append(String.format( "%-32s%-10s%-10s","\tDate and Time","\tType"," Amount"));
//        sb.append();
        JLabel heading = new JLabel();
        heading.setText(sb.toString());
        heading.setForeground(Color.BLACK);
        heading.setFont(new Font("System",Font.BOLD,15));
        heading.setBounds(70,240,350,25  );
        add(heading);

        try {
        ResultSet rs1 = conn.s.executeQuery("select date,Type,Amount from bank where Account_No = '"+account_no+"' ORDER BY date DESC LIMIT 5");
        while(rs1.next()) {
        txt1.setText(txt1.getText() +"<html>"+ rs1.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs1.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs1.getString("Amount") + "<br><br><html>");
        }


        }catch(Exception e) {
        System.out.println(e);
        }

//
//
        txt1.setBounds(70,150,400,400);

        back = new JButton("Back");
        back.setBounds(300,550,150,30);
        back.setForeground(Color.BLACK);
        back.setBackground(new Color(255,0,0) );
        back.addActionListener(this);
        add(back);


        setSize(550,600);
        setLocation(500,70);
        setBackground(new Color(255,255,255));
        setUndecorated(true);
        setVisible(true);
        setUndecorated(true);

    }

    public void actionPerformed(ActionEvent ae){
       if( ae.getSource()==back){
        setVisible(false);

        new Transactions(pinnumber,account_no).setVisible(true);

    }

    }

    public static void main(String[] args) {
        new MiniStatement("","");
    }
}

//import java.awt.Color;
//import java.sql.ResultSet;
//
//import javax.swing.*;
//
//import javax.swing.JFrame;
//public class MiniStatement extends JFrame{
//    String pinnumber , account_no;
//    public MiniStatement(String pinnumber,String ) {
//        // TODO Auto-generated constructor stub
//        setTitle("Mini Statement");
//        setLayout(null);
//
//        JLabel mini = new JLabel();
//        add(mini);
//
//        JLabel bankName = new JLabel("VIIT Bank");
//        bankName.setBounds(150, 20, 100, 20);
//        add(bankName);
//
//        JLabel cardNo = new JLabel();
//        cardNo.setBounds(20, 80, 300, 20);
//        add(cardNo);
//
//        JLabel bal = new JLabel();
//        add(bal);
//
//        Conn conn = new Conn();
//
//        try {
//            ResultSet rs = conn.s.executeQuery("select * from login where Pin_Number = '"+pinNumber+"'");
//            while(rs.next()) {
//                cardNo.setText("Card number : "+ rs.getString("Card_Number").substring(0, 4)+ "XXXXXXXX" + rs.getString("Card_Number").substring(12));
//
//            }
//        }
//        catch(Exception e) {
//            System.out.println(e);
//        }
//
//        try {
//            ResultSet rs1 = conn.s.executeQuery("select * from bank where Pin_Number = '"+pinNumber+"' ORDER BY date DESC LIMIT 5");
//            while(rs1.next()) {
//                mini.setText(mini.getText() +"<html>"+ rs1.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs1.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs1.getString("balance") + "<br><br><html>");
//            }
//
////			for(int i=0; i<5; i++) {
////				rs1.next();
////				mini.setText(mini.getText() +"<html>"+ rs1.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs1.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs1.getString("balance") + "<br><br><html>");
////			}
//        }catch(Exception e) {
//            System.out.println(e);
//        }
//
//        try {
//            ResultSet rs2 = conn.s.executeQuery("select Balance FROM bank where Pin_Number = '"+pinNumber+"' ORDER BY date DESC  LIMIT 1;");
//            int balance = 0;
//
//            rs2.next();
//            balance += Integer.parseInt(rs2.getString("Balance"));
//
//            bal.setText("Your current account balance is Rs "+ balance);
//        }catch(Exception e) {
//            System.out.println(e);
//        }
//
//
//        mini.setBounds(20, 140, 400, 200);
//        bal.setBounds(20,400,300,20);
//
//        setSize(400, 600);
//        setLocation(20, 20);
//        getContentPane().setBackground(Color.WHITE);
//        setVisible(true);
//
//    }
//
//    public static void main(String[] args) {
//        new MiniStatement("","");
//    }
//
//}
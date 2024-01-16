package Bank_Management;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import  java.util.*;
public class Deposit extends JFrame implements ActionListener{

    JButton deposit, back;
    JTextField amount;
    String pinnumber , account_no ;
    Deposit(String pinnumber,String account_no)
    {
        this.pinnumber = pinnumber ;
        this.account_no = account_no;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,850,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,-10,900,850);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setBounds(190,275,700,35);
        image.add(text);

        amount = new JTextField();
        amount.setBounds(175 , 325,320,20);
        image.add(amount);

        deposit = new JButton("Deposit");
        deposit.setBounds(355,430,150,30);
        deposit.setForeground(Color.BLACK);
        deposit.setBackground(new Color(51,236,168) );
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(355,470,150,30);
        back.setForeground(Color.BLACK);
        back.setBackground(new Color(255,0,0) );
        back.addActionListener(this);
        image.add(back);

        setSize(900,800);
        setLocation(300,20);
        setUndecorated(true);
        setVisible(true);
    }

        public void actionPerformed(ActionEvent ae){
            ResultSet rs1=null;
        if(ae.getSource()==deposit){
            String number = amount.getText();
            Date date = new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null,"Please Enter a valid amount");
            }
            else{
                    Conn conn = new Conn();
                try {
//                    String query = "select * from bank where Pin = '"+pinnumber+"'";
//                    ResultSet rs = conn.s.executeQuery(query);
                    String query = "select Balance FROM Bank where Account_No = '"+account_no+"' ORDER BY date DESC  LIMIT 1;";
                    rs1 = conn.s.executeQuery(query);
                    long balance = Long.parseLong(number);


                    if(rs1.next()==false){
                        balance = Long.parseLong(number);
                    }
                    else {

                        balance += Long.parseLong(rs1.getString("Balance"));
                    }

//                    int balance = Integer.parseInt(number);
//                    String query1 = "select Balance FROM Bank ORDER BY date DESC  LIMIT 1;";
//                    ResultSet r = conn.s.executeQuery(query1);
//                    if(r.next()==false){
//                        balance = Integer.parseInt(number);
//                    }
//                    else if(r.getString("Pin")==pinnumber){
//                        balance += Integer.parseInt(r.getString("Balance"));
                    SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");

                    String formattedDate = formatter.format(date);
//                    }
                    String query1 = "insert into bank values('"+account_no+"','" + pinnumber + "','" + date + "','" + formattedDate + "','Deposit','" + number + "','"+balance+"')";
                    String query3 = "update login set Balance ='"+balance+"'  where Account_No = '"+account_no+"'";
                    conn.s.executeUpdate(query1);
                    conn.s.executeUpdate(query3);
                    JOptionPane.showMessageDialog(null, "Rs " + number + " Deposited Successfully");
                    setVisible(false);
                    new Transactions(pinnumber,account_no).setVisible(true);
                }
                catch (Exception e){
                    System.out.println(e);
                }
                finally {
                    try {
                        rs1.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }


            }
        } else if (ae.getSource()==back) {
            setVisible(false);
            new Transactions(pinnumber,account_no).setVisible(true);
        }
        }
    public static void main(String[] args) {

        new Deposit("","");
    }
}

package Bank_Management;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import  java.util.Date;
public class Withdraw extends JFrame implements ActionListener{

    JButton withdraw, back;
    JTextField amount;
    String pinnumber , account_no ;
    Withdraw(String pinnumber,String account_no)
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

        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setBounds(190,275,700,35);
        image.add(text);

        amount = new JTextField();
        amount.setBounds(175 , 325,320,20);
        image.add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(355,430,150,30);
        withdraw.setForeground(Color.BLACK);
        withdraw.setBackground(new Color(51,236,168) );
        withdraw.addActionListener(this);
        image.add(withdraw);

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
        if(ae.getSource()==withdraw){
            String number = amount.getText();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null,"Please Enter a valid amount");
            }
            else{
                    Conn conn = new Conn();

                    ResultSet rs = null;
                try {
//                    String query = "select * from bank where Pin = '"+pinnumber+"'";
//                    ResultSet rs = conn.s.executeQuery(query);
//                    int balance = 0;
//                    while(rs.next()){
//                     if(rs.getString("Pin")==pinnumber){
//                    if(rs.getString("Type").equals("Deposit")){
//                        balance += Integer.parseInt(rs.getString("Amount"));
//                    }
//                    else {
//                        balance -= Integer.parseInt(rs.getString("Amount"));
//                    }}}

                    String query = "select Balance FROM Bank where Account_No = '"+account_no+"' ORDER BY date DESC  LIMIT 1;";
                     rs = conn.s.executeQuery(query);
                    if(rs.next()==false){
                        JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    }
                    else{
//                     rs.next();
                    long balance =Long.parseLong(rs.getString("Balance"));
                            if(Long.parseLong(number)>balance){
                                JOptionPane.showMessageDialog(null,"Insufficient Balance");
                            }
                            else {

                        Date date1 = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
                        String formattedDate = formatter.format(date1);
                        balance -= Long.parseLong(number);
                        String query2 = "insert into bank values('"+account_no+"','"+pinnumber+"','"+date1+"','" + formattedDate + "','Withdraw','"+number+"','"+balance+"')";
                        String query3 = "update login set Balance ='"+balance+"'  where Account_No = '"+account_no+"'";
                        conn.s.executeUpdate(query2);
                        conn.s.executeUpdate(query3);
                        JOptionPane.showMessageDialog(null, "Rs " + number + " Withdrew Successfully");
                }}}
                catch (Exception e){
                    System.out.println(e);
                }
                finally {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                setVisible(false);
                new Transactions(pinnumber,account_no).setVisible(true);
            }
        }
        else if (ae.getSource()==back) {
            setVisible(false);
            new Transactions(pinnumber,account_no).setVisible(true);
        }
    }
    public static void main(String[] args) {

        new Deposit("","");
    }
}

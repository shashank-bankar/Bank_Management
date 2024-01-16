package Bank_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FastCash extends JFrame implements ActionListener {

    JButton hundred , fiveHundred,thousand,back, twoThousand, fiveThouasand, tenThousand;

    String pinnumber , account_no ;
    FastCash(String pinnumber,String account_no){

        this.pinnumber = pinnumber ;
        this.account_no = account_no;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,850,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,-10,900,850);
        add(image);

        JLabel text = new JLabel("Please select your Amount");
        text.setBounds(217,275,700,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text); // We want our text to be over the image so we call add function from image
        //if not then text will be over the frame but below the image

        hundred  = new JButton("Rs 100");
        hundred.setBounds(180,340,130,30);
        hundred.setForeground(Color.BLACK);
        hundred.setBackground(new Color(51,236,168) );
        hundred.addActionListener(this);
        image.add(hundred);

        fiveHundred = new JButton("Rs 500");
        fiveHundred.setBounds(360,340,130,30);
        fiveHundred.setForeground(Color.BLACK);
        fiveHundred.setBackground(new Color(51,236,168) );
        fiveHundred.addActionListener(this);
        image.add(fiveHundred);

        thousand  = new JButton("Rs 1000");
        thousand.setBounds(180,390,130,30);
        thousand.setForeground(Color.BLACK);
        thousand.setBackground(new Color(51,236,168) );
        thousand.addActionListener(this);
        image.add(thousand);

        twoThousand  = new JButton("Rs 2000");
        twoThousand.setBounds(360,390,130,30);
        twoThousand.setForeground(Color.BLACK);
        twoThousand.setBackground(new Color(51,236,168) );
        twoThousand.addActionListener(this);
        image.add(twoThousand);

        fiveThouasand  = new JButton("Rs 5000");
        fiveThouasand.setBounds(180,440,130,30);
        fiveThouasand.setForeground(Color.BLACK);
        fiveThouasand.setBackground(new Color(51,236,168) );
        fiveThouasand.addActionListener(this);
        image.add(fiveThouasand);

        tenThousand  = new JButton("Rs 10000");
        tenThousand.setBounds(360,440,130,30);
        tenThousand.setForeground(Color.BLACK);
        tenThousand.setBackground(new Color(51,236,168) );
        tenThousand.addActionListener(this);
        image.add(tenThousand);

        back  = new JButton("Back");
        back.setBounds(270,480,130,30);
        back.setForeground(Color.YELLOW);
        back.setBackground(new Color(255,0,0) );
        back.addActionListener(this);
        image.add(back);

        setSize(900,800);
        setLocation(300,20);
        setUndecorated(true);
        setVisible(true);
        setUndecorated(true);

    }

    public void actionPerformed(ActionEvent ae){
        ResultSet rs2 = null;
        if(ae.getSource()==back){
            setVisible(false);
            new Transactions(pinnumber,account_no).setVisible(true);
        } else {
            String amount = ((JButton)ae.getSource()).getText().substring(3);///Rs 500
            Conn c = new Conn();
            try{
                 rs2 = c.s.executeQuery("select Balance FROM Bank where Account_No = '"+account_no+"' ORDER BY date DESC  LIMIT 1;");
                if(!(rs2.next())){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                }
                else{
//                rs2.next();
                long balance =Long.parseLong(rs2.getString("Balance"));
                if(Long.parseLong(amount)>balance){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                }
                else {
                    Date date1 = new Date();
                    balance -= Long.parseLong(amount);
                    SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
                    String formattedDate = formatter.format(date1);
                    String query2 = "insert into bank values('"+account_no+"','"+pinnumber+"','"+date1+"','" + formattedDate + "','Withdraw','"+amount+"','"+balance+"')";
                    String query3 = "update login set Balance ='"+balance+"'  where Account_No = '"+account_no+"'";
                    c.s.executeUpdate(query2);
                    c.s.executeUpdate(query3);
                    JOptionPane.showMessageDialog(null,"Rs "+amount+" debited successfully.");
                }
            }}
            catch(Exception e){
                System.out.println(e);
            }
            finally {
                try {
                    rs2.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static void main(String[] args) {

        new FastCash("","");
    }
}

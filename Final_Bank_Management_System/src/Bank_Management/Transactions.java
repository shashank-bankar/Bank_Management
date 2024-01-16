package Bank_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class Transactions extends JFrame implements ActionListener {

    JButton deposit , fastCash,checkBalance,exit, withdrawl , ministatement, pinchange;

    String pinnumber , account_no ;

    Transactions(String pinnumber,String account_no){

        this.pinnumber = pinnumber ;
        this.account_no = account_no;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,850,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,-10,900,850);
        add(image);

        JLabel text = new JLabel("Please select your Transaction");
        text.setBounds(217,275,700,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text); // We want our text to be over the image so we call add function from image
        //if not then text will be over the frame but below the image

        deposit  = new JButton("Deposit");
        deposit.setBounds(180,340,130,30);
        deposit.setForeground(Color.BLACK);
        deposit.setBackground(new Color(51,236,168) );
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl  = new JButton("Cash Withdrawl");
        withdrawl.setBounds(360,340,130,30);
        withdrawl.setForeground(Color.BLACK);
        withdrawl.setBackground(new Color(51,236,168) );
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        fastCash  = new JButton("Fast Cash");
        fastCash.setBounds(180,390,130,30);
        fastCash.setForeground(Color.BLACK);
        fastCash.setBackground(new Color(51,236,168) );
        fastCash.addActionListener(this);
        image.add(fastCash);

        ministatement  = new JButton("Mini Statement");
        ministatement.setBounds(360,390,130,30);
        ministatement.setForeground(Color.BLACK);
        ministatement.setBackground(new Color(51,236,168) );
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange  = new JButton("Pin Change");
        pinchange.setBounds(180,440,130,30);
        pinchange.setForeground(Color.BLACK);
        pinchange.setBackground(new Color(51,236,168) );
        pinchange.addActionListener(this);
        image.add(pinchange);

        checkBalance  = new JButton("Check Balance");
        checkBalance.setBounds(360,440,130,30);
        checkBalance.setForeground(Color.BLACK);
        checkBalance.setBackground(new Color(51,236,168) );
        checkBalance.addActionListener(this);
        image.add(checkBalance);

        exit  = new JButton("Exit");
        exit.setBounds(270,480,130,30);
        exit.setForeground(Color.YELLOW);
        exit.setBackground(new Color(255,0,0) );
        exit.addActionListener(this);
        image.add(exit);

        setSize(900,800);
        setLocation(300,20);
        setUndecorated(true);
        setVisible(true);
        setUndecorated(true);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==exit){
            System.exit(0);
        } else if (ae.getSource()==deposit) {
            setVisible(false);
            new Deposit(pinnumber,account_no).setVisible(true);
        } else if (ae.getSource()==withdrawl) {
            setVisible(false);
            new Withdraw(pinnumber,account_no).setVisible(true);
        } else if (ae.getSource()==fastCash) {
            setVisible(false);
            new FastCash(pinnumber,account_no).setVisible(true);
        } else if (ae.getSource()==pinchange) {
            setVisible(false);
            new PinChange(pinnumber,account_no).setVisible(true);
        }
        else if (ae.getSource()==checkBalance) {
            setVisible(false);
            new CheckBalance(pinnumber,account_no).setVisible(true);
        }
        else if (ae.getSource()==ministatement) {
            setVisible(false);
             new MiniStatement(pinnumber,account_no);
            setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Transactions("","");
    }
}

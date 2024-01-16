package Bank_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PinChange extends JFrame implements ActionListener {

    JButton back,change;
    JPasswordField  repinTxtF,pinTxtF;
    String pinnumber , account_no ;

    PinChange(String pinnumber,String account_no){
        this.pinnumber = pinnumber;
        this.account_no = account_no;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,850,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,-10,900,850);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Raleway",Font.BOLD,16));
        text.setBounds(250,280,400,35);
        image.add(text);

        JLabel pintext = new JLabel("NEW PIN");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("Raleway",Font.BOLD,16));
        pintext.setBounds(180,335,200,35);
        image.add(pintext);

        pinTxtF = new JPasswordField ("");
        pinTxtF.setBounds(370,335,130,35);
        image.add(pinTxtF);

        JLabel repin = new JLabel("RE-ENTER NEW PIN");
        repin.setForeground(Color.WHITE);
        repin.setFont(new Font("Raleway",Font.BOLD,16));
        repin.setBounds(180,385,200,35);
        image.add(repin);

        repinTxtF = new JPasswordField ("");
        repinTxtF.setBounds(370,385,130,35);
        image.add(repinTxtF);

        change = new JButton("Change");
        change.setForeground(Color.BLACK);
        change.setBackground(new Color(51,236,168) );
        change.setBounds(370,435,130,30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("Back");
        back.setForeground(Color.BLACK);
        back.setBackground(new Color(255,0,0) );
        back.setBounds(200,435,130,30);
        back.addActionListener(this);
        image.add(back);

        setSize(900,800);
        setLocation(300,20);
        setUndecorated(true);
        setVisible(true);
    }

    public  boolean checkPIN(String a){

        try {
            Double.parseDouble(a); // Check for double
            if(a.length()==4){
                return true;}
            else{
                return  false ;
            }
        } catch (NumberFormatException e2) {
            return false;
        }
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == change){
            try{
            String npin = pinTxtF.getText();
            String rpin = repinTxtF.getText();
                System.out.println(npin);
            if(npin.equals("")){
                JOptionPane.showMessageDialog(null,"Please Enter PIN");
                return ;
            }
            else if (!(checkPIN(npin))) {
                JOptionPane.showMessageDialog(null,"Enter a valid pin of 4 digit number");
            }
            else if (!(npin.equals(rpin))) {
                JOptionPane.showMessageDialog(null,"Entered PIN does not match");
            }
            else if (rpin.equals("")) {
                JOptionPane.showMessageDialog(null,"Please Re-Enter PIN");
            }
            else{
                Conn c = new Conn();
                String query1 = "update bank set Pin = '"+rpin+"' where Account_No ='"+account_no+"'";
                String query2 = "update signupthree set Pin_Number = '"+rpin+"' where Account_No='"+account_no+"'";
                String query3 = "update login set Pin_Number = '"+rpin+"' where Account_No ='"+account_no+"'";

                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                c.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null,"PIN CHANGED SUCCESSFULLY");
                setVisible(false);
                new Transactions(pinnumber,account_no).setVisible(true);

            }

            }
        catch (Exception e){
            System.out.println(e);
        }}
        else{
            setVisible(false);
            new Transactions(pinnumber,account_no).setVisible(true);
        }
    }
    public static void main(String[] args) {
        new PinChange("","");
    }
}

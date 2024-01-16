package Bank_Management;
import com.mysql.cj.exceptions.StreamingNotifiable;

import javax.swing.* ;
import java.awt.* ;
import java.awt.event.*;
import java.util.*;
public class SignupThree extends JFrame implements ActionListener{

    JRadioButton savings, current , joint ;
    JCheckBox c1,c2,c3,c4,c5,c6,c7;
    JButton submit , cancel;
    JPasswordField pinnumber ;

    String formno,ifsc;
    SignupThree(String formno,String IFSC){
        this.formno = formno;
        this.ifsc = IFSC;
        setLayout(null);

        JLabel l1 = new JLabel("Page 3 : Account Details");
        l1.setFont(new Font("Railway",Font.BOLD,22));
        l1.setBounds(250,40,400,40);
        add(l1);

        JLabel type = new JLabel("Account Type");
        type.setFont(new Font("Raleway",Font.BOLD,20));
        type.setBounds(100,140,200,30);
        add(type);

        current = new JRadioButton("Current account");
        current.setFont(new Font("Raleway",Font.BOLD,16));
        current.setBackground(Color.WHITE);
        current.setBounds(330,140,180,30);
        add(current);

        savings = new JRadioButton("Savings Account");
        savings.setFont(new Font("Raleway",Font.BOLD,16));
        savings.setBackground(Color.WHITE);
        savings.setBounds(570,140,220,30);
        add(savings);

        ButtonGroup account = new ButtonGroup();
        account.add(current);
        account.add(savings);

        JLabel card = new JLabel("Card Number");
        card.setFont(new Font("Raleway",Font.BOLD,20));
        card.setBounds(100,190,200,30);
        add(card);

        JLabel number = new JLabel("XXXX-XXXX-XXXX-4184");
        number.setFont(new Font("Raleway",Font.BOLD,20));
        number.setBounds(330,190,300,30);
        add(number);

        JLabel carddetail = new JLabel("(16 digit card number)");
        carddetail.setFont(new Font("Raleway",Font.BOLD,17));
        carddetail.setBounds(100,220,300,25);
        add(carddetail);

        JLabel pnumber = new JLabel("Enter PIN");
        pnumber.setFont(new Font("Raleway",Font.BOLD,20));
        pnumber.setBounds(100,270,200,30);
        add(pnumber);


        pinnumber = new JPasswordField();
        pinnumber.setFont(new Font("Raleway",Font.BOLD,20));
        pinnumber.setBounds(330,270,300,30);
        add(pinnumber);

        JLabel pcarddetail = new JLabel("4 digit number");
        pcarddetail.setFont(new Font("Raleway",Font.BOLD,17));
        pcarddetail.setBounds(100,300,300,25);
        add(pcarddetail);

        JLabel service = new JLabel("Services Required: ");
        service.setFont(new Font("Raleway",Font.BOLD,20));
        service.setBounds(100,350,300,30);
        add(service);

        c1 = new JCheckBox("ATM CARD");
        c1.setBackground(Color.WHITE);
        c1.setBounds(100,400,200,30);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(Color.WHITE);
        c2.setBounds(350,400,200,30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(Color.WHITE);
        c3.setBounds(100,450,200,30);
        add(c3);

        c4 = new JCheckBox("Email Alerts");
        c4.setBackground(Color.WHITE);
        c4.setBounds(350,450,200,30);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        c5.setBackground(Color.WHITE);
        c5.setBounds(100,500,200,30);
        add(c5);

        c6 = new JCheckBox("E-statement");
        c6.setBackground(Color.WHITE);
        c6.setBounds(350,500,200,30);
        add(c6);

        c7 = new JCheckBox("I hereby declare that all the details filled above are correct");
        c7.setBackground(Color.WHITE);
        c7.setBounds(100,550,400,30);
        add(c7);

        submit =new JButton("Submit");
        submit.setForeground(Color.YELLOW);
        submit.setBackground(new Color(134,0,224));
        submit.setFont(new Font("Raleway",Font.BOLD,14));
        submit.setBounds(250,650,100,30);
        submit.addActionListener(this);
        add(submit);

        cancel =new JButton("Cancel");
        cancel.setForeground(Color.YELLOW);
        cancel.setBackground(new Color(134,0,224));
        cancel.setFont(new Font("Raleway",Font.BOLD,14));
        cancel.setBounds(420,650,100,30);
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);

        setSize(850,820);
        setLocation(350,0);
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
            if(ae.getSource()== submit){
                String accountType = null;
                String password = pinnumber.getText();
                if(current.isSelected()){
                    accountType = "Current Account" ;
                } else if (savings.isSelected()) {
                    accountType = "Savings Account";
                }
            Random random = new Random();
            String cardnumber = ""+(random.nextLong() % 90000000+ 5040936000000000L);
//            String pinnumber = ""+Math.abs((random.nextLong() % 9000L)+ 1000L);
            String facility = "";
                if(c1.isSelected()){
                    facility = facility +"ATM Card ";
                } if (c2.isSelected()) {
                    facility = facility + "Internet Banking " ;
                } if (c3.isSelected()) {
                    facility = facility + "Mobile Banking " ;
                } if (c4.isSelected()) {
                    facility = facility + "Email Alerts " ;
                }  if (c5.isSelected()) {
                    facility = facility + "Cheque Book " ;
                }  if (c6.isSelected()) {
                    facility = facility + "E-statement " ;
                }
                try{
                    if(accountType==null){
                       JOptionPane.showMessageDialog(null,"Account Type is Required"); 
                    } else if (!(c7.isSelected())) {
                        JOptionPane.showMessageDialog(null,"Confirmation  of all the fields filled are correct is necessary");
                    }else if (!(checkPIN(password))) {
                        JOptionPane.showMessageDialog(null,"Enter a valid pin of 4 digit number");
                    } else{
                        Conn conn = new Conn();
                        String query1 = "insert into signupthree values('"+formno+"','"+accountType+"','"+cardnumber+"','"+password+"','"+facility+"')";
//                        String query2 = "insert into login(IFSC,Form_no ,Account_No,Pin_Number,Type,Balance) values('"+ifsc+"','"+formno+"','"+cardnumber+"','"+password+"','"+accountType+"','"+0+"')";
                        String query2 = "update  login set IFSC='"+ifsc+"',Form_no='"+formno+"' ,Account_No='"+cardnumber+"',Pin_Number='"+password+"',Type='"+accountType+"',Balance='"+0+"' where Form_No='"+formno+"'";
                        conn.s.executeUpdate(query1);
                        conn.s.executeUpdate(query2);

                        JOptionPane.showMessageDialog(null,"Account Number: "+cardnumber+ "\nPin: "+password+ "\nIFSC CODE: "+ifsc );
                        setVisible(false);
                        new Login().setVisible(true);
                    }
                }
                catch(Exception e){
                   System.out.println(e);
                }
            }
            else if (ae.getSource()== cancel) {
                setVisible(false);
                new SignupTwo(formno,ifsc).setVisible(true);
            }
        }

    public static void main(String[] args) {
        new SignupThree("","");
    }
}

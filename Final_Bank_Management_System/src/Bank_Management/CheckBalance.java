package Bank_Management;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckBalance extends JFrame implements ActionListener {
        String account_no,pinnumber;
        JButton back;

        CheckBalance(String pinnumber,String account){
            this.pinnumber = pinnumber;
            this.account_no = account;

            setLayout(null);
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
            Image i2 = i1.getImage().getScaledInstance(900,850,Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(0,-10,900,850);
            add(image);

            JLabel text = new JLabel("Your available balance is ");
            text.setBounds(227,310,700,35);
            text.setForeground(Color.WHITE);
            text.setFont(new Font("System",Font.BOLD,16));
            image.add(text);
            ResultSet r = null;
            try {
                Conn c = new Conn();
                String query = "select Balance FROM Bank where Account_No = '"+account_no+"' ORDER BY date DESC  LIMIT 1;";
                r = c.s.executeQuery(query);
                if(r.next()){
//                rs.next();
                    String balance = r.getString("Balance");
                    JLabel Balance = new JLabel("     "+balance);
                    Balance.setBounds(270,370,150,35);
                    Balance.setFont(new Font("Raleway",Font.BOLD,19));
                    Balance.setBorder(new LineBorder(new Color(250, 242, 2), 2));
                    Balance.setBackground(new Color(100, 6, 215));
                    Balance.setForeground(new Color(14, 232, 232));
                    Balance.setOpaque(true);
                    image.add(Balance);


                }
                else {
//                rs.next();

//                    System.out.println(rs);
                    JLabel Balance = new JLabel("     You have 0 Balance");

                    Balance.setBounds(200,370,280,35);
                    Balance.setFont(new Font("Raleway",Font.BOLD,19));
                    Balance.setBorder(new LineBorder(new Color(250, 242, 2), 2));
                    Balance.setBackground(new Color(100, 6, 215));
                    Balance.setForeground(new Color(14, 232, 232));
                    Balance.setOpaque(true);
                    image.add(Balance);


                }
            }
            catch (Exception e){
                System.out.println(e);
            }                finally {
                try {
                    r.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


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
            setUndecorated(true);

        }

        public void actionPerformed(ActionEvent ae){

                if(ae.getSource()==back){
                    setVisible(false);
                    new Transactions(pinnumber,account_no).setVisible(true);
                }
        }

    public static void main(String[] args) {
        new CheckBalance("","");
    }
}

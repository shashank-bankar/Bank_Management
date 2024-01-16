package Bank_Management;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.KeyEvent;

//5040936057576238
public class Login extends JFrame implements ActionListener {
    JButton login,clear, register,admin;
    JTextField cardTextField;
    JComboBox IFSC ;
    JPasswordField PinPassword;
    Login(){ //constructorL
        setTitle("Online VISHWAKARMA Bank");

        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(("icons/Login2.png")));
        Image i2 = i1.getImage().getScaledInstance(800,750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); //need to create new imageIcon as we cant directly pass image to JLable
        JLabel j1 = new JLabel(i3);
        j1.setBounds(0,0,788,725);
        add(j1);


        //Writng content on frame
         JLabel txt1 = new JLabel("Welcome to VISHWAKARMA Bank ") ;
         txt1.setForeground(Color.WHITE);
         txt1.setFont(new Font("Osward",Font.BOLD,25));
         txt1.setBounds(195,238,500,100);
         j1.add(txt1);

         JLabel txt = new JLabel("VISHWAKARMA BANK") ;
         txt.setForeground(Color.WHITE);
         txt.setFont(new Font("Times New Roman",Font.BOLD,38));
         txt.setBounds(190,110,500,120);
         j1.add(txt);

        JLabel txt4 = new JLabel("The Bank made for students by students") ;
        txt4.setForeground(Color.WHITE);
        txt4.setFont((new Font("Osward",Font.BOLD,16)));
        txt4.setBounds(240,269,400,100);
        j1.add(txt4);

        JLabel CardNo = new JLabel("Enter your Account No: ") ;
        CardNo.setFont((new Font("Raleway",Font.BOLD,19)));
        CardNo.setForeground(Color.YELLOW);
        CardNo.setBounds(150,325,250,100);
        j1.add(CardNo);

        cardTextField = new JTextField();
        cardTextField.setBounds(405,370,200,23);
        cardTextField.setFont(new Font("Arial",Font.BOLD,15));
        j1.add(cardTextField);


        JLabel PIN = new JLabel("Enter your PIN: ") ;
        PIN.setFont((new Font("Raleway",Font.BOLD,19)));
        PIN.setForeground(Color.YELLOW);
        PIN.setBounds(150,385,250,100);
        j1.add(PIN);

        PinPassword = new JPasswordField();
        PinPassword.setBounds(405,425,200,23);
        PinPassword.setFont(new Font("Arial",Font.BOLD,15));
        j1.add(PinPassword);

        //Buttons
        login = new JButton("USER LOGIN");
        login.setForeground(Color.YELLOW);
        login.setBackground(new Color(134,0,224) );
        login.setBounds(230,490,165,40);
        login.addActionListener(this);
        j1.add(login);

        register = new JButton("USER SIGN UP");
        register.setBounds(410,490,165,40);
        register.setForeground(Color.YELLOW);
        register.setBackground(new Color(134,0,224) );
        register.addActionListener(this);
        j1.add(register);

        admin = new JButton("ADMIN LOGIN");
        admin.setBounds(230,550,165,40);
        admin.setForeground(Color.YELLOW);
        admin.setBackground(new Color(134,0,224) );
        admin.addActionListener(this);
        j1.add(admin);

        clear = new JButton("CLEAR");
        clear.setBounds(410,550,165,40);
        clear.setForeground(Color.YELLOW);
        clear.setBackground(new Color(134,0,224) );
        clear.addActionListener(this);
        j1.add(clear);


//        getContentPane().setBackground(new Color(252,228,77,255));

        setSize(800,750);
        setVisible(true);
//        setUndecorated(true);
        setLocation(350 , 50);

    }
        public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==clear){
        cardTextField.setText("");
        PinPassword.setText("");
        }
        else if (ae.getSource()==login) {
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber  = PinPassword.getText();
            String query = "select * from  login where Account_No = '"+cardnumber+"' and Pin_Number = '"+pinnumber+"';";
            try{
                ResultSet ra = conn.s.executeQuery(query); // execute query returns a result of type ResultSet
                if(ra.next()){
                    setVisible(false);
                    new Transactions(pinnumber,cardnumber).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect Account number or Pin");
                }
            }
            catch (Exception e){
                System.out.println(e);
            }

        } else if (ae.getSource()==register) {
                setVisible(false);
                new SignupOne().setVisible(true);
        }
        else if (ae.getSource()==admin) {
            Conn conn = new Conn();
            String adminNo = cardTextField.getText();
            String adminPin  = PinPassword.getText();
            String query = "select * from Admin where Admin_No = '"+adminNo+"' and Pin_No = '"+adminPin+"';";
            try{
                ResultSet ra = conn.s.executeQuery(query); // execute query returns a result of type ResultSet
                if(ra.next()){
                    setVisible(false);
                    new Admin().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect Account number or Pin");
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        }


    public static void main(String args[]){

        new Login();
    }
}

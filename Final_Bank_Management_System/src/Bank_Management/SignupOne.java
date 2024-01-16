package Bank_Management;
import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupOne  extends JFrame implements ActionListener{

        JTextField nameTxtF , fnameTxtF , emailTxtF ,AdressTxtF,cityTxtF,pincodeTxtF,stateTxtF;
        JButton next;
        JDateChooser dateChooser ;
        JComboBox college;
        String []valClg ;
        String ifsc;
        JRadioButton genderCheck,genderCheck2 ,unmarried,married,other ;
        long random ;
    SignupOne(){
        setLayout(null);

        Random ran = new Random();
        random = (Math.abs((ran.nextLong()%9000L) + 1000L));

        JLabel formno = new JLabel("Application Form No : " + random);
        formno.setFont(new Font("Raleway", Font.BOLD,35));
        formno.setBounds(190,20,600,40);
        add(formno);


        JLabel personalDetails = new JLabel("Page 1: Personal Details" );
        personalDetails.setFont(new Font("Raleway", Font.BOLD,22));
        personalDetails.setBounds(290,80,400,28);
        add(personalDetails);

        JLabel clg = new JLabel("College/School: " );
        clg.setFont(new Font("Raleway", Font.BOLD,20));
        clg.setBounds(100,140,400,30);
        add(clg);

        valClg =new String[7];
        valClg[0] = "VIT, Pune";
        valClg[1] ="VIIT, Pune" ;
        valClg[2] = "Wisdom World School (WWS) - Hadapsar and Wakad, Pune" ;
        valClg[3] = "Vishwakarma College of Arts, Commerce & Science (VCACS), Pune";
        valClg[4] ="Vishwakarma Vidyalaya (VV), Pune" ;
        valClg[5] = "Vishwakarma Empros International School (VEIS) - Chinchwad and Talegaon, Pune";
        valClg[6] = "Vishwakarma Commerce & Science College (VCSC), Kolhapur";

        college = new JComboBox(valClg);
        college.setBackground(Color.WHITE);
        college.setBounds(300,140,400,30);
        add(college);

        JLabel name = new JLabel("Name: " );
        name.setFont(new Font("Raleway", Font.BOLD,20));
        name.setBounds(100,190,100,30);
        add(name);

        nameTxtF = new JTextField();
        nameTxtF.setFont(new Font("Raleway",Font.BOLD,20));
        nameTxtF.setBounds(300,190,400,30);
        add(nameTxtF);

        JLabel fname = new JLabel("Father's Name: " );
        fname.setFont(new Font("Raleway", Font.BOLD,20));
        fname.setBounds(100,240,200,25);
        add(fname);

        fnameTxtF = new JTextField();
        fnameTxtF.setFont(new Font("Raleway",Font.BOLD,20));
        fnameTxtF.setBounds(300,240,400,30);
        add(fnameTxtF);

        JLabel DOB = new JLabel("Date of Birth: " );
        DOB.setFont(new Font("Raleway", Font.BOLD,20));
        DOB.setBounds(100,290,200,30);
        add(DOB);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300,290,400,30);
        dateChooser.setForeground(Color.RED);
        add(dateChooser);

        JLabel Gender = new JLabel("Gender: " );
        Gender.setFont(new Font("Raleway", Font.BOLD,20));
        Gender.setBounds(100,340,200,30);
        add(Gender);

        genderCheck = new JRadioButton("Male");
        genderCheck.setBounds(300,340,120,30);
        genderCheck.setBackground(Color.WHITE);
        add(genderCheck);

        genderCheck2 = new JRadioButton("Female");
        genderCheck2.setBounds(450,340,120,30);
        genderCheck2.setBackground(Color.WHITE);
        add(genderCheck2);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(genderCheck);
        gendergroup.add(genderCheck2);

        JLabel email = new JLabel("Email : " );
        email.setFont(new Font("Raleway", Font.BOLD,20));
        email.setBounds(100,390,200,30);
        add(email);

        emailTxtF = new JTextField();
        emailTxtF.setFont(new Font("Raleway",Font.BOLD,20));
        emailTxtF.setBounds(300,390,400,30);
        add(emailTxtF);

        JLabel Marital = new JLabel("Marital Status : " );
        Marital.setFont(new Font("Raleway", Font.BOLD,20));
        Marital.setBounds(100,440,200,30);
        add(Marital);

        married = new JRadioButton("Married");
        married.setBounds(300,440,120,30);
        married.setBackground(Color.WHITE);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(450,440,120,30);
        unmarried.setBackground(Color.WHITE);
        add(unmarried);

        other = new JRadioButton("Other");
        other.setBounds(600,440,120,30);
        other.setBackground(Color.WHITE);
        add(other);

        ButtonGroup Maritalgroup = new ButtonGroup();
        Maritalgroup.add(married);
        Maritalgroup.add(unmarried);
        Maritalgroup.add(other);


        JLabel Adress = new JLabel("Address : " );
        Adress.setFont(new Font("Raleway", Font.BOLD,20));
        Adress.setBounds(100,490,200,30);
        add(Adress);

        AdressTxtF = new JTextField();
        AdressTxtF.setFont(new Font("Raleway",Font.BOLD,20));
        AdressTxtF.setBounds(300,490,400,30);
        add(AdressTxtF);

        JLabel City = new JLabel("City : " );
        City.setFont(new Font("Raleway", Font.BOLD,20));
        City.setBounds(100,540,200,30);
        add(City);

        cityTxtF = new JTextField();
        cityTxtF.setFont(new Font("Raleway",Font.BOLD,20));
        cityTxtF.setBounds(300,540,400,30);
        add(cityTxtF);

        JLabel State = new JLabel("State : " );
        State.setFont(new Font("Raleway", Font.BOLD,20));
        State.setBounds(100,590,200,30);
        add(State);

        stateTxtF = new JTextField();
        stateTxtF.setFont(new Font("Raleway",Font.BOLD,20));
        stateTxtF.setBounds(300,590,400,30);
        add(stateTxtF);

        JLabel pincode = new JLabel("Pin Code : " );
        pincode.setFont(new Font("Raleway", Font.BOLD,20));
        pincode.setBounds(100,640,200,30);
        add(pincode);

        pincodeTxtF = new JTextField();
        pincodeTxtF.setFont(new Font("Raleway",Font.BOLD,20));
        pincodeTxtF.setBounds(300,640,400,30);
        add(pincodeTxtF);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setBounds(620,690,80,30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850,800);
        setTitle("SignUp Page");

        setLocation(350,10);
        setUndecorated(true);
        setVisible(true);


    }

    public  boolean pincode(String a){

        try {
            Double.parseDouble(a); // Check for double
            if(a.length()==6){
                return true;}
            else{
                return  false ;
            }
        } catch (NumberFormatException e2) {
            return false;
        }
    }
    public void actionPerformed(ActionEvent ae){
        String College = (String)college.getSelectedItem();
        ifsc = null;
        String formno = "" + random; //to enter in database we need string value so to convert long random in strng concatenate it with "" (string)
        String name = nameTxtF.getText().toUpperCase(); //get text entered in nameTxtF
        String fname = fnameTxtF.getText().toUpperCase();
        String dob = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();


        String gender = null;
        if (genderCheck.isSelected()){
            gender = "Male";
        } else if (genderCheck2.isSelected()) {
            gender = "Female";
        }

        String email = emailTxtF.getText();
        String regex = "^[A-Za-z0-9+.-]+@[A-Za-z0-9]+.[A-Za-z0-9.]+$";
        Pattern pattern = Pattern.compile((regex));
        Matcher tryMatching = pattern.matcher(email);
        boolean flag = tryMatching.matches();

        String Marital = null;
        if (married.isSelected()){
            Marital = "Married";
        } else if (unmarried.isSelected()) {
            Marital = "Unmarried";
        }
        else if(other.isSelected()){
            Marital = "Other";
        }
        String adress = AdressTxtF.getText().toUpperCase();
        String city = cityTxtF.getText().toUpperCase();
        String state = stateTxtF.getText().toUpperCase();


        String pincode = pincodeTxtF.getText();


        try{

        if(College.equals("")){
            JOptionPane.showMessageDialog(null , "College/School field is required");
        }
        else if(name.equals("")){
            JOptionPane.showMessageDialog(null , "Name is required");
        }
        else if(fname.equals("")){
            JOptionPane.showMessageDialog(null , "Father's name is required");
        }
        else if(dob.equals("")){
            JOptionPane.showMessageDialog(null , "Date of Birth is required");
        }
        else if(gender.equals("")){
            JOptionPane.showMessageDialog(null , "Gender is required");
        }
        else if(email.equals("")){
            JOptionPane.showMessageDialog(null , "Email is required");
        }
        else if(Marital.equals("")){
            JOptionPane.showMessageDialog(null , "Marital Status is required");
        }
        else if(adress.equals("")){
            JOptionPane.showMessageDialog(null , "Address is required");
        }
        else if(city.equals("")){
            JOptionPane.showMessageDialog(null , "City is required");
        }
        else if(state.equals("")){
            JOptionPane.showMessageDialog(null , "State is required");
        }
        else if(pincode.equals("")){
            JOptionPane.showMessageDialog(null , "Pin Code is required");
        }
        else if(!flag){
            JOptionPane.showMessageDialog(null,"Enter a valid Email Address");
        }
        else if(!(pincode(pincode))){
            JOptionPane.showMessageDialog(null,"Enter a valid Pincode");
        }
        else {
            if(College.equals(valClg[0])){
                ifsc = "VIT00000001";
            }
            else if (College.equals(valClg[1])) {
                ifsc = "VIIT0000002";
            }
            else if (College.equals(valClg[2])) {
                ifsc = "WWS00000003";
            }
            else if (College.equals(valClg[3])) {
                ifsc = "VV000000004";
            }else if (College.equals(valClg[4])) {
                ifsc = "VCAC0000005";
            }else if (College.equals(valClg[5])) {
                ifsc = "VEIS0000006";
            }else if (College.equals(valClg[6])) {
                ifsc = "VCSC0000007";
            }
            Conn c = new Conn();
            String query = "insert into signup values('"+formno+"','"+name+"','"+fname+"','"+dob+"','"+gender+"','"+email+"','"+Marital+"','"+adress+"','"+city+"','"+state+"','"+pincode+"')";
            String query1 = "insert into login (Form_No,Name) values('"+formno+"','"+name+"') ";

//            INSERT INTO tblemployee (employee_first_name, employee_last_name) values ('Nisarg','Upadhyay')
            c.s.executeUpdate(query);
            c.s.executeUpdate(query1);
            setVisible(false);
            new SignupTwo(formno,ifsc).setVisible(true);
        }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String ar[]){
        new SignupOne();
    }
}

package Bank_Management;
import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupTwo  extends JFrame implements ActionListener{

    JTextField  divTxtF ,adhaarTxtF,panTxtF,prnTxtF,rollTxtF;
    JButton next;
    JComboBox religion , category , department,yearOfStudy;
    String formno ,ifsc;
    SignupTwo(String formno,String ifsc){
        this.formno = formno ;
        this.ifsc = ifsc ;
        setLayout(null);
        setTitle("New Account Application Form : Page 2");

        JLabel additionalDetails = new JLabel("Page 2: Additional Details" );
        additionalDetails.setFont(new Font("Raleway", Font.BOLD,22));
        additionalDetails.setBounds(290,80,400,28);
        add(additionalDetails);

        JLabel Religion = new JLabel("Religion: " );
        Religion.setFont(new Font("Raleway", Font.BOLD,20));
        Religion.setBounds(100,140,100,30);
        add(Religion);

        String valReligion[] = {"Hindu", "Islam","Sikh","Christian","Jain","Other"} ;
        religion = new JComboBox(valReligion);
        religion.setBackground(Color.WHITE);
        religion.setBounds(300,140,400,30);
        add(religion);


        JLabel Category = new JLabel("Category: " );
        Category.setFont(new Font("Raleway", Font.BOLD,20));
        Category.setBounds(100,190,200,25);
        add(Category);

        String valCat [] = {"General","OBC","SC","ST","NT","EWS","TFWS","OTHER"} ;
        category = new JComboBox(valCat);
        category.setBackground(Color.WHITE);
        category.setBounds(300,190,400,30);
        add(category);


        JLabel Department = new JLabel("Department: " );
        Department.setFont(new Font("Raleway", Font.BOLD,20));
        Department.setBounds(100,240,200,30);
        add(Department);

        String valDepartment[] = {"Computer", "IT","ENTC","AI-DS","CIVIL","Mechanical","First Year"} ;
        department = new JComboBox(valDepartment);
        department.setBounds(300,240,400,30);
        add(department);


        JLabel YearOfStudy = new JLabel("Year of Study : " );
        YearOfStudy.setFont(new Font("Raleway", Font.BOLD,20));
        YearOfStudy.setBounds(100,290,200,30);
        add(YearOfStudy);

        String valYear[] = {"First", "Second","Third","Fourth"} ;
        yearOfStudy = new JComboBox(valYear);
        yearOfStudy.setBounds(300,290,400,30);
        add(yearOfStudy);

        JLabel Division = new JLabel("Division: " );
        Division.setFont(new Font("Raleway", Font.BOLD,20));
        Division.setBounds(100,340,200,30);
        add(Division);

        divTxtF = new JTextField();
        divTxtF.setFont(new Font("Raleway",Font.BOLD,20));
        divTxtF.setBounds(300,340,400,30);
        add(divTxtF);

        JLabel PanNo = new JLabel("Pan No  : " );
        PanNo.setFont(new Font("Raleway", Font.BOLD,20));
        PanNo.setBounds(100,390,200,30);
        add(PanNo);

        panTxtF = new JTextField();
        panTxtF.setFont(new Font("Raleway",Font.BOLD,20));
        panTxtF.setBounds(300,390,400,30);
        add(panTxtF);

        JLabel Adhaar = new JLabel("Adhaar No : " );
        Adhaar.setFont(new Font("Raleway", Font.BOLD,20));
        Adhaar.setBounds(100,440,200,30);
        add(Adhaar);

        adhaarTxtF = new JTextField();
        adhaarTxtF.setFont(new Font("Raleway",Font.BOLD,20));
        adhaarTxtF.setBounds(300,440,400,30);
        add(adhaarTxtF);

        JLabel PRN = new JLabel("PRN No : " );
        PRN.setFont(new Font("Raleway", Font.BOLD,20));
        PRN.setBounds(100,490,200,30);
        add(PRN);

        prnTxtF = new JTextField();
        prnTxtF.setFont(new Font("Raleway",Font.BOLD,20));
        prnTxtF.setBounds(300,490,400,30);
        add(prnTxtF);

        JLabel ROLL = new JLabel("Roll No : " );
        ROLL.setFont(new Font("Raleway", Font.BOLD,20));
        ROLL.setBounds(100,540,200,30);
        add(ROLL);

        rollTxtF = new JTextField();
        rollTxtF.setFont(new Font("Raleway",Font.BOLD,20));
        rollTxtF.setBounds(300,540,400,30);
        add(rollTxtF);


        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setBounds(620,660,80,30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850,800);
        setTitle("SignUp Page");

        setLocation(350,10);
        setVisible(true);


    }
    public  boolean check(String a){

        boolean f;
        try {
            Double.parseDouble(a); // Check for double
            return true;
        } catch (NumberFormatException e2) {
            return false;
        }
    }
    public  boolean checkAdhhar(String a){

        try {
            Double.parseDouble(a); // Check for double
            if(a.length()==12){
                return true;}
            else{
                return  false ;
            }
        } catch (NumberFormatException e2) {
            return false;
        }
    }
    public  boolean checkPRN(String a){

        try {
            Double.parseDouble(a); // Check for double
            if(a.length()==8){
                return true;}
            else{
                return  false ;
            }
        } catch (NumberFormatException e2) {
            return false;
        }
    }
    public  boolean checkPAN(String a){


            if(a.length()==10){
                return true;}
            else{
                return  false ;
            }

    }
    public void actionPerformed(ActionEvent ae){

        String strReligion = (String)religion.getSelectedItem(); //getSelectedItem return object type therefore we need to typecast
        String strCategory = (String)category.getSelectedItem() ;
        String strDepartment = (String)department.getSelectedItem();
        String strYear = (String)yearOfStudy.getSelectedItem();
        String strDiv = (divTxtF.getText()).toUpperCase();
        String strPan = (panTxtF.getText()).toUpperCase();
        String strPrn = prnTxtF.getText();
        String strRoll = rollTxtF.getText();
        String strAdhaar = adhaarTxtF.getText();

        try{
            if(strReligion.equals("")){
                JOptionPane.showMessageDialog(null , "Religion is required");
            }
            else if(strCategory.equals("")){
                JOptionPane.showMessageDialog(null , "Category is required");
            }
            else if(strDepartment.equals("")){
                JOptionPane.showMessageDialog(null , "Department is required");
            }
            else if(strYear.equals("")){
                JOptionPane.showMessageDialog(null , "Year of Study is required");
            }
            else if(strDiv.equals("") || strDiv.length()!=1 || !strDiv.matches("[A-Z]")){
                JOptionPane.showMessageDialog(null , "Appropriate division required");
            }
            else if(strPan.equals("")){
                JOptionPane.showMessageDialog(null , "Pan Number is required");
            }
            else if(strAdhaar.equals("")){
                JOptionPane.showMessageDialog(null , "Adhaar Number is required");
            }
            else if(strPrn.equals("")){
                JOptionPane.showMessageDialog(null , "PRN No is required");
            }
            else if(strRoll.equals("")){
                JOptionPane.showMessageDialog(null , "Roll No is required");
            }
            else if (!(checkPAN(strPan))) {
                JOptionPane.showMessageDialog(null,"Enter a valid PAN Number");
            }
            else if (!(checkAdhhar(strAdhaar))) {
                JOptionPane.showMessageDialog(null,"Enter a valid Adhaar Number");
            }
            else if (!(checkPRN(strPrn))) {
                JOptionPane.showMessageDialog(null,"Enter a valid PRN Number");
            } else if (!(check(strRoll))) {
                JOptionPane.showMessageDialog(null,"Enter a valid Roll Number");
            }
            else {
                Conn c = new Conn();
                String query = "insert into signupTwo values('"+formno+"','"+strReligion+"','"+strCategory+"','"+strDepartment+"','"+strYear+"','"+strDiv+"','"+strPan+"','"+strAdhaar+"','"+strPrn+"','"+strRoll+"')";
                c.s.executeUpdate(query);

                //Signup3 object
                setVisible(false);
                new SignupThree(formno,ifsc).setVisible(true);

            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String ar[]){
        new SignupTwo("","");
    }
}

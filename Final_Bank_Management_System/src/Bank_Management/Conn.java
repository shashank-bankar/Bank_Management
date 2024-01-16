package Bank_Management;
import java.sql.* ;
import java.sql.Connection;
import java.sql.Statement;

public class Conn {

    Connection c ;
    Statement s ,s1;
    PreparedStatement ps,ps2;
    public Conn(){
        //MYSQL is a external entity so it may show error in runtime so we need to have the error handling system

        try{
            //Register the driver
//            dont need to explicitly register driver cause we have added library
//            Class.forName(com.mysql.cj.jdbc.Driver);
            //Create connection
            c = DriverManager.getConnection("jdbc:mysql:///pract","root","SHA#27bankar7");
//             url = connection string = jdbc:mysql:///
            s = c.createStatement();
            s1 = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


        }
        catch(Exception e){
            System.out.println(e);
        }
    }
            public PreparedStatement getPreparedStatement(String sql) throws SQLException {
                ps = c.prepareStatement(sql);

                return ps;
            }
            public PreparedStatement getPreparedStatement1(String sql) throws SQLException {
                ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE);

                return ps;
            }
    public void close() {
        try {
            if (c != null && !c.isClosed()) {
                c.close();
                s.close();
                System.out.println("Database connection closed successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

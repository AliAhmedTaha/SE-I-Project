package DataBase;

import javax.sql.DataSource;
import java.sql.*;
import System.User;

public class AdminsDatabase {

    public AdminsDatabase() 
    {
        try { Class.forName("com.mysql.jdbc.Driver"); }
        catch (ClassNotFoundException e){System.out.println("Constructor is here !");}
    }

    public  boolean Store(User user) {
      boolean test= true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "INSERT INTO admin VALUES('"+user.getName()+"','"+user.getID()+"','"+user.getUsername()+"','"+user.getPassword()+"');";
            Statement statment=con.createStatement();
            statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {
            System.out.println("this is SQL");test=false;}
        return test;
    }

    public boolean remove(String Username){
        boolean test=true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "DELETE FROM `admin`where Username='"+Username+"'";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {test=false;}
        return test;
    }

    public boolean Verify(String Username,String Password){
        Connection con;
        try 
        {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT `password` FROM `admin` WHERE `Username`='"+Username+"'";
            Statement statment=con.createStatement();
            ResultSet result =statment.executeQuery(SQL);
            String test="";
            if (result.next())
            {test = result.getString("password");
            }
            result.close();
            statment.close();
            con.close();
            if(test.equals(Password)){
                return true;
            }
        }
        catch (SQLException e) {
            System.out.println("this from exciption");
        }
        return false;
    }

    public User getAdmin(String Username){
        Connection con;
        try {

            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT * FROM `admin` WHERE `Username`='"+Username+"'";
            Statement statment=con.createStatement();
            ResultSet result =statment.executeQuery(SQL);
            User data=null;
            if(result.next())
                data=new User(result.getString("Name"),result.getString("Username"),result.getString("password"),result.getString("AdminID"));
            result.close();
            statment.close();
            con.close();
            return data;
        }
        catch (SQLException e) {}
        return null;
    }

}

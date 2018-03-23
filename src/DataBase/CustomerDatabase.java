package DataBase;

import System.User;
import java.sql.*;

public class CustomerDatabase {

    public CustomerDatabase() 
    {
        try { Class.forName("com.mysql.jdbc.Driver"); }
        catch (ClassNotFoundException e){}
    }

    public  boolean Store(User user) 
    {
        boolean test= true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "INSERT INTO `customer`(`Name`, `Password`, `CustomerID`, `Username`) VALUES('"+user.getName()+"','"+user.getPassword()+"','"+user.getID()+"','"+user.getUsername()+"')";       
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
            System.out.println("DataBase is Clear :D");
            return true;
        }
        catch (SQLException e) {System.out.println("this is SQL Proble in user reg");test=false;}
        return test;
    }

    public boolean remove(String Username){
        boolean test=true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "DELETE FROM `customer` WHERE Username='"+Username+"'";
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
            String SQL= "SELECT `Password` FROM `customer` WHERE `Username`='"+Username+"'";
            Statement statment=con.createStatement();
            ResultSet result =statment.executeQuery(SQL);
            String test="";
            if (result.next())
            {test = result.getString("Password");
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

    public User getCustomer(String Username){
        Connection con;
        try 
        {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT * FROM `customer` WHERE `Username`='"+Username+"'";
            Statement statment=con.createStatement();
            ResultSet result =statment.executeQuery(SQL);
            User data=null;
            if(result.next())
            data=new User(result.getString("Name"),result.getString("Username"),result.getString("Password"),result.getString("CustomerID"));
            result.close();
            statment.close();
            con.close();
            return data;
        }
        catch (SQLException e) {}
        return null;
    }
}

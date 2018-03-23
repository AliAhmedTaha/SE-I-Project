package DataBase;

import System.User;
import java.sql.*;

public class StoreownersDatabase {

    public StoreownersDatabase()
    {
        try { Class.forName("com.mysql.jdbc.Driver"); }
        catch (ClassNotFoundException e){}
    }

    public  boolean Store(User user) {
        boolean test= true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "INSERT INTO `storeowner` VALUES('"+user.getName()+"','"+user.getID()+"','"+user.getUsername()+"','"+user.getPassword()+"');";
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
            String SQL= "DELETE FROM `storeowner`where Username='"+Username+"'";
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
            String SQL= "SELECT `Password` FROM `storeowner` WHERE `Username`='"+Username+"'";
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

    public User getStoreOwner(String Username){
        Connection con;
        try {

            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT * FROM `storeowner` WHERE `Username`='"+Username+"'";
            Statement statment=con.createStatement();
            ResultSet result =statment.executeQuery(SQL);
            User data=null;
            if(result.next())
                data=new User(result.getString("Name"),result.getString("Username"),result.getString("Password"),result.getString("StoreOwnerID"));
            result.close();
            statment.close();
            con.close();
            return data;
        }
        catch (SQLException e) {}
        return null;
    }

}

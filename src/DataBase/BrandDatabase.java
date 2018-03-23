package DataBase;

import System.Brand;
import java.sql.*;
import java.util.Vector;

public class BrandDatabase {
    
    public BrandDatabase(){
        try { Class.forName("com.mysql.jdbc.Driver"); }
        catch (ClassNotFoundException e){}
    }

    public boolean Store(Brand brand){
        boolean test= true;
        try {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "INSERT INTO `brand` VALUES('"+brand.getName()+"','"+brand.getBrandID()+"',"+brand.getCounter()+",'"+brand.getType()+"');";
            System.out.println(SQL);
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
            return true;
        }
        catch (SQLException e) {
            System.out.println("this is SQL");test=false;}
        return test;}

    public boolean remove(String BrandID){
        boolean test=true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "DELETE FROM `brand` WHERE `BrandID`='"+BrandID+"'";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {test=false;}
        return test;
    }

    public Brand getBrand(String Brandname){
        Connection con;
        try 
        {

            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT * FROM `brand` WHERE `Name`='"+Brandname+"'";
            Statement statment=con.createStatement();
            ResultSet result =statment.executeQuery(SQL);
            Brand data=null;
            if(result.next())
                data=new Brand(result.getString("Name"),result.getString("BrandID"),result.getInt("counter"),result.getString("type"));
            result.close();
            statment.close();
            con.close();
            return data;
        }
        catch (SQLException e) {}
        return null;
    }

    public boolean RemoveByname(String Brandname){
        boolean test=true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "DELETE FROM `brand` WHERE `Name`='"+Brandname+"'";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {test=false;}
        return test;
    }

    public boolean IsExist(String Brandname){
        boolean test= true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT `BrandID`FROM `brand` WHERE `Name`='"+Brandname+"'";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {
            System.out.println("this is SQL");test=false;}
        return test;
    }

    public boolean UpdateBrandCounter(String BrandID){
        boolean test=true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "UPDATE `brand` SET `counter`=`counter`+1 WHERE `BrandID`='"+BrandID+"'";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {test=false;}
        return test;
    }

    public Vector<Brand> GetTheMostOrdered(){
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL = "SELECT * FROM `brand` ORDER BY counter DESC LIMIT 0,3";
            Statement statment = con.createStatement();
            ResultSet result = statment.executeQuery(SQL);
            Vector<Brand> AllProducts = new Vector<>();
            while (result.next()) {
                Brand temp = new Brand(result.getString("Name"), result.getString("BrandID"), result.getInt("counter"), result.getString("type"));
                AllProducts.add(temp);
            }
            result.close();
            statment.close();
            con.close();
            return AllProducts;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public Vector<String> GetAllOriginalBrands()
    {
        Connection con;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL = "SELECT `BrandID` FROM `brand` WHERE `type`= 'original'";
            Statement statment = con.createStatement();
            ResultSet result = statment.executeQuery(SQL);
            Vector<String> AllOriginalBrands = new Vector<>();
            while (result.next()){
                AllOriginalBrands.add(result.getString("BrandID"));
            }
            result.close();
            statment.close();
            con.close();
            return AllOriginalBrands;
        } catch (SQLException e) {
        }
        return null;
    }
}

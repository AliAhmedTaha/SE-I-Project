package DataBase;

import System.Store;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Vector;

public class StoreDatabase {
    
    public StoreDatabase() 
    {
        try { Class.forName("com.mysql.jdbc.Driver"); }
        catch (ClassNotFoundException e){}
    }

     public Vector<String> AllStores()
    {
        Connection con;
        try
        {

            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT * FROM `store`";
            Statement statment=con.createStatement();
            ResultSet result =statment.executeQuery(SQL);
            Vector<String> data=new Vector<>();
            while(result.next()){data.add(result.getString("Name"));}
            result.close();
            statment.close();
            con.close();
            return data;
        }
        catch (SQLException e) {}
        return null; 
    }
    
    public boolean Store(Store store ,String StoreownerID){
        boolean test= true;
        try {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "INSERT INTO `store`(`Name`, `Numberofbuyers`, `numberofvisitors`, `StoreID`, `StoreOwnerID`) VALUES ('"+store.getName()+"',"+store.getNumOfBuyers()+","+store.getNumOfExplorers()+",'"+store.getID()+"','"+StoreownerID+"');";
            Statement statment=con.createStatement();
            statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {
            System.out.println("this is SQL");test=false;}
        return test;
    }

    public boolean remove(String StoreID){
        boolean test=true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "DELETE FROM `store` WHERE `StoreID`='"+StoreID+"'";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {test=false;}
        return test;
    }

    public Store getStore(String StoreID){
        Connection con;
        try 
        {

            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT * FROM `store` WHERE `StoreID`='"+StoreID+"'";
            Statement statment=con.createStatement();
            ResultSet result =statment.executeQuery(SQL);
            Store data=null;
            ProductDatabase productDatabase =new ProductDatabase();
            if(result.next())
                data=new Store(result.getString("Name"),result.getString("StoreID"),result.getString("adress"),result.getInt("numberofvisitors"),result.getInt("Numberofbuyers"),productDatabase.getProductsinStore(StoreID));
            result.close();
            statment.close();
            con.close();
            return data;
        }
        catch (SQLException e) {}
        return null;
    }

    /*public void UpdateStore(Store store){//Kona m7tgnha f ayh

    }*/

    public boolean IsExist(String StoreID){
        boolean test= true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT `StoreOwnerID` FROM `store` WHERE `StoreID`='"+StoreID+"'";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {
            System.out.println("this is SQL");
            test=false;
        }
        return test;
    }

    public int GetNumberofBuyers(String StoreID){
        Connection con;
        try {

            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT `Numberofbuyers` FROM `store` WHERE `StoreID`='"+StoreID+"'";
            Statement statment=con.createStatement();
            ResultSet result =statment.executeQuery(SQL);
            int data=0;
            ProductDatabase productDatabase =new ProductDatabase();
            if(result.next())
                data=result.getInt("Numberofbuyers");
            result.close();
            statment.close();
            con.close();
            return data;
        }
        catch (SQLException e) {}
        return 0;
    }

    public int GetnumberofExplorers(String StoreID)
    {
        Connection con;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT `numberofvisitors` FROM `store` WHERE `StoreID`='"+StoreID+"'";
            Statement statment=con.createStatement();
            ResultSet result =statment.executeQuery(SQL);
            int data=0;
            ProductDatabase productDatabase =new ProductDatabase();
            if(result.next())
                data=result.getInt("numberofvisitors");
            result.close();
            statment.close();
            con.close();
            return data;
        }
        catch (SQLException e) {}
        return 0;
    }
    
    public Vector<String> GetStoreOwnerStores(String StoreOwnerID)
    {
        Connection con;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL = "SELECT `StoreID` FROM `store` WHERE `StoreOwnerID`= '"+StoreOwnerID+"'";
            Statement statment = con.createStatement();
            ResultSet result = statment.executeQuery(SQL);
            Vector<String> AllOriginalProducts = new Vector<>();
            while (result.next()){
                AllOriginalProducts.add(result.getString("StoreID"));
            }
            result.close();
            statment.close();
            con.close();
            return AllOriginalProducts;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public boolean SetnumberofExplorers(String StoreID)
    {
        Connection con;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "UPDATE `store` SET `numberofvisitors`=`numberofvisitors`+1 WHERE `StoreID`='"+StoreID+"'";
            Statement statment=con.createStatement();
            statment.execute(SQL);
            statment.close();
            con.close();
            return true;
        }
        catch (SQLException e) {return false;}  
    }
    
    public boolean SetnumberofBuyers(String StoreID)
    {
        Connection con;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "UPDATE `store` SET `Numberofbuyers`=`Numberofbuyers`+1 WHERE `StoreID`='"+StoreID+"'";
            Statement statment=con.createStatement();
            statment.execute(SQL);
            statment.close();
            con.close();
            return true;
        }
        catch (SQLException e) {return false;}  
    }
    
}

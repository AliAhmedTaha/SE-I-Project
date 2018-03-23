package DataBase;

import java.sql.*;
import java.util.Vector;
import System.Product;

public class ProductDatabase {
    
    public ProductDatabase() 
    {
        try { Class.forName("com.mysql.jdbc.Driver"); }
        catch (ClassNotFoundException e){}
    }

    public boolean StoreForAdmin(Product product){
        boolean test= true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "INSERT INTO `product`(`name`, `ProductID`, `sold`, `BrandID`, `Type`) VALUES('"+product.getName()+"','"+product.getProductID()+"',"+product.getSold()+",'"+product.getBrandID()+"','"+product.getType()+"');";
            System.out.println(SQL);
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
            return true;
        }
        catch (SQLException e) {
            System.out.println("this is SQL");test=false;}
        return test;
    }

    public boolean removeForAdmin(String ProductID){
        boolean test=true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "DELETE FROM `product` WHERE `ProductID`='"+ProductID+"'";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {test=false;}
        return test;
    }

    public Boolean AddProductinStore(String StoreID, String PrductID, int Price, int Quantity) {
        boolean test= true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "INSERT INTO `has`(`StoreID`, `ProductID`, `Price`, `Quantity`) VALUES('"+StoreID+"','"+PrductID+"',"+Price+","+Quantity+");";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {
            System.out.println("this is SQL");test=false;}
        return test;
    }

    public boolean RemoveFromStore(String StoreID,String ProductID) {
        boolean test=true;
        try
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "DELETE FROM `has` WHERE `StoreID`='"+StoreID+"'AND`ProductID`='"+ProductID+"'";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {test=false;}
        return test;
    }

  /*  public  boolean AddUnitstostore(String StoreID,String ProductID,int Quantity)//not yet done
    {
        return true;
    }*/

    public Vector<Product> getProductsinStore(String StoreID) {
        Connection con;
        try {

            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL = "SELECT has.ProductID,has.Price,product.BrandID,has.Quantity FROM has,product WHERE has.ProductID=product.ProductID AND has.StoreID='" + StoreID + "'";
            Statement statment = con.createStatement();
            ResultSet result = statment.executeQuery(SQL);
            Vector<Product> AllProducts = new Vector<>();
            while (result.next()) {
                Product temp = new Product(result.getString("ProductID"), result.getString("ProductID"), 0, result.getString("BrandID"),result.getInt("Price"),result.getInt("Quantity"),"original");
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

    public boolean IsExist(String ProductID){
        boolean test= true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT `product`FROM `brand` WHERE `Name`='"+ProductID+"'";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {
            System.out.println("this is SQL");test=false;}
        return test;}

    /*public Vector<Pair<String,Integer>> getAllProducts(String Productname){
        return new Vector<Pair<String,Integer>>();
    }*/

    public boolean UpdateSold(String ProductID){
        boolean test=true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "UPDATE `product` SET `sold`= `sold`+1 WHERE `ProductID`='"+ProductID+"'";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {test=false;}
        return test;
    }

    public boolean UpdateProduct(String StoreID,String ProductID) {
        boolean test=true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "UPDATE `has` SET `Quantity`= `Quantity`-1 WHERE `StoreID`= '"+StoreID+"'AND`ProductID`='"+ProductID+"'";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
            UpdateSold(ProductID);
        }
        catch (SQLException e) {test=false;}
        return test;
    }

    public Vector<Product> GetHighestProduct(){
        Connection con;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL = "SELECT * FROM `product` ORDER BY sold DESC Limit 0 ,3";
            Statement statment = con.createStatement();
            ResultSet result = statment.executeQuery(SQL);
            Vector<Product> AllProducts = new Vector<>();
            while (result.next()) {
                Product temp = new Product(result.getString("name"), result.getString("ProductID"), result.getInt("sold"), result.getString("BrandID"));
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
    
    public Vector<String> GetSuggestedProducts(){
        Connection con;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL = "SELECT `name` FROM `product` WHERE `type`= 'Suggested'";
            Statement statment = con.createStatement();
            ResultSet result = statment.executeQuery(SQL);
            Vector<String> AllSuggestedProducts = new Vector<>();
            while (result.next()){
                AllSuggestedProducts.add(result.getString("name"));
            }
            result.close();
            statment.close();
            con.close();
            return AllSuggestedProducts;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public Vector<Product> GetAllOriginalProducts()
    {
        Connection con;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL = "SELECT  `ProductID`,  `BrandID` FROM `product` WHERE`type`= 'original'";
            Statement statment = con.createStatement();
            ResultSet result = statment.executeQuery(SQL);
            Vector<Product> AllOriginalProducts = new Vector<>();
            while (result.next()){
                AllOriginalProducts.add(new Product (result.getString("ProductID"),result.getString("ProductID"),0,result.getString("BrandID"),0,0,"original"));
            }
            result.close();
            statment.close();
            con.close();
            return AllOriginalProducts;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public Product getProduct(String StoreID, String ProductID) {
        Connection con;
        try {

            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL = "SELECT has.ProductID,has.Price,product.BrandID,has.Quantity FROM has,product WHERE has.ProductID=product.ProductID AND has.StoreID='" + StoreID + "'AND product.ProductID='"+ProductID+"'";
            System.out.println(SQL);
            Statement statment = con.createStatement();
            ResultSet result = statment.executeQuery(SQL);
            result.next();
            Product temp = new Product(result.getString("ProductID"), result.getString("ProductID"), 0, result.getString("BrandID"),result.getInt("Price"),result.getInt("Quantity"),"original");
            System.out.println(temp.getPrice()+"    "+temp.getQuantity());
            result.close();
            statment.close();
            con.close();
            return temp;
        } catch (SQLException e) {
        }
        return null;
    }
    public boolean UpdateProductDetails(String ProductID , String StoreID , int price,int quantity )
    {
        boolean test=true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "UPDATE `has` SET`Price`="+price+",`Quantity`="+quantity+" WHERE `StoreID`='"+StoreID+"'AND`ProductID`='"+ProductID+"'";
            System.out.println(SQL);
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
            return true;
        }
        catch (SQLException e) {test=false;}
        return test;
        
    }
    
    
    public boolean ExistsInStore(String ProductID,String StoreID)
    {
        
         boolean test= true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT `StoreID` FROM `has` WHERE `StoreID`= '"+StoreID+"' AND `ProductID`= '"+ProductID+"'";
            Statement statment=con.createStatement();
            ResultSet result =statment.executeQuery(SQL);
            result.next();
            String temp = result.getString("StoreID");
            statment.close();
            result.close();
            con.close();
            return true;
        }
        catch (SQLException e) {
            System.out.println("this is SQL");test=false;}
        return test;
    }
        

    
}

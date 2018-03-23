package DataBase;

import System.VoucherCard;
import java.sql.*;

public class VoucherCardsDatabase {
    
    public VoucherCardsDatabase() 
    {
        try { Class.forName("com.mysql.jdbc.Driver"); }
        catch (ClassNotFoundException e){}
    }

    public int  getvalue (String ID) {
        Connection con;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "SELECT  `Value` FROM `vouchercard` WHERE `VoucherID`='"+ID+"'";
            Statement statment=con.createStatement();
            ResultSet result =statment.executeQuery(SQL);
            int data=0;
            if(result.next())
                data=result.getInt("Value");
            result.close();
            statment.close();
            con.close();
            return data;
        }
        catch (SQLException e) {}
        return  0;
    }

    public boolean Store(VoucherCard Card){
        boolean test= true;
        try {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "INSERT INTO `vouchercard`(`Name`, `VoucherID`, `Value`) VALUES('"+Card.getName()+"','"+Card.getVoucherID()+"',"+Card.getValue()+");";
            Statement statment=con.createStatement();
            statment.execute(SQL);
            statment.close();
            con.close();
        }
        catch (SQLException e) {
            System.out.println("this is SQL");test=false;}
        return test;}

    public boolean remove(String VoucherID){
        boolean test=true;
        try 
        {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/software","root","12345678");
            String SQL= "DELETE FROM `vouchercard` WHERE `VoucherID`='"+VoucherID+"'";
            Statement statment=con.createStatement();
            test=statment.execute(SQL);
            statment.close();
            con.close();
            return true;
        }
        catch (SQLException e) {test=false;}
        return test;

    }
}

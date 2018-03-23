package Controllers;

import DataBase.CustomerDatabase;
import DataBase.ProductDatabase;
import DataBase.StoreDatabase;
import DataBase.VoucherCardsDatabase;
import System.Product;
import System.User;
import java.util.*;

 
public class Customer
{
    public Customer() {}
    
    public  boolean SignIn(String name,String pass)
    {
        CustomerDatabase C = new CustomerDatabase();
        return C.Verify(name, pass);
    }
    
    public  boolean SignUp(String Name,String PassWord,String UserName)
    {
        CustomerDatabase C = new CustomerDatabase();
        User U = new User(Name, UserName, PassWord, UserName); 
        return C.Store(U);
    }

     public Vector<String> Explore() 
    {
        StoreDatabase S = new StoreDatabase();
        return S.AllStores();
    }
     
    public Vector<Product> ExploreStore(String StoreName)
    {
        ProductDatabase p = new ProductDatabase();
        return p.getProductsinStore(StoreName);
    }

     
    public int BuyProduct(String StoreName, ArrayList<Product> Cart,int TotalPrice,String VoucherCode )
    {
        int value = VoucherValidation(VoucherCode);
        if(value==0){return 0;}
        else if(value<TotalPrice){return 1;}
        else
            {
                ProductDatabase ProductDB = new ProductDatabase();
                for(int i=0 ; i<Cart.size() ; i++)
                {
                    Product pro = ProductDB.getProduct(StoreName, Cart.get(i).getProductID());
                    if(pro.getQuantity()<=0){return 3;}
                    ProductDB.UpdateProduct(StoreName, Cart.get(i).getProductID());
                    new Statistics().IncrementNumberOfBuyers(StoreName);
                }
                return 2;
            }
    }
    
    public int VoucherValidation(String ID)
    {
        VoucherCardsDatabase V = new VoucherCardsDatabase();
        return V.getvalue(ID);
    }

    public boolean DeleteVoucher(String VoucherCode) {
         VoucherCardsDatabase V = new VoucherCardsDatabase();
        return V.remove(VoucherCode);
       }

    


}
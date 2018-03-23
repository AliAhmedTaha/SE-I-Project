package Controllers;

import java.util.*;
import System.*;
import DataBase.*;

public class Storeowner 
{
    
    public Storeowner() {}
    
    public  boolean SignIn(String name,String pass)
    {
        StoreownersDatabase S = new StoreownersDatabase();
        return S.Verify(name, pass);
    }
    
    public  boolean SignUp(String Name,String PassWord,String UserName)
    {
        StoreownersDatabase S = new StoreownersDatabase();
        User U = new User(Name, UserName, PassWord, UserName);
        return S.Store(U);
    }

    public boolean AddStore(String name, String address, String StoreOwnerID) 
    {
         Store S = new Store(name, name, address, 0, 0, null);
         StoreDatabase StoreDB = new StoreDatabase();
         return StoreDB.Store(S, StoreOwnerID);
    }
    
    public boolean AddProduct(String StoreID, String ProductID, int Price,int Quantity)
    {
        ProductDatabase ProductDB = new ProductDatabase();
        if (ProductDB.ExistsInStore(ProductID, StoreID) )
            return false;
        else
        {ProductDB.AddProductinStore(StoreID, ProductID, Price, Quantity);
            return true;
        } 
    }

    public void SusggestProduct(Product product) 
    {
        ProductDatabase ProductDB = new ProductDatabase();
        ProductDB.StoreForAdmin(product);
    }

    public void SusggestBrand(Brand brand)
    {
        BrandDatabase BrandDB = new BrandDatabase();
        BrandDB.Store(brand);
    }
    
    public Vector<String> GetStores(String StoreOwnerID)
    {
        StoreDatabase S = new StoreDatabase();
        return S.GetStoreOwnerStores(StoreOwnerID);
    }
    
    public Vector<Product> GetSystemProducts()
    {
        ProductDatabase P = new ProductDatabase();
        return P.GetAllOriginalProducts();
    }
    
    public Vector<Product> GetStoreProducts(String StoreID)
    {
        ProductDatabase P = new ProductDatabase();
        return P.getProductsinStore(StoreID);
    }
    
    
    public Vector<String> GetSystemBrands()
    {
        BrandDatabase B = new BrandDatabase();
        return B.GetAllOriginalBrands();
    }
    
    
    public boolean UpdateProductDetails(String ProductID , String StoreID , int price,int quantity )
    {
        ProductDatabase ProductDB = new ProductDatabase();

        return ProductDB.UpdateProductDetails(ProductID, StoreID, price, quantity);
    }

}

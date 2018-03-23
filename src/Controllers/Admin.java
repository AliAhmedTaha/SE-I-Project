package Controllers;

import System.*;
import DataBase.*;
import java.util.Vector;
        
public class Admin 
{
    
    public boolean AddProduct(String ProductID,String BrandID)
    {
        Product Temp = new Product(ProductID,ProductID,0,BrandID,0,0,"original");
        ProductDatabase productDB = new ProductDatabase();
        return productDB.StoreForAdmin(Temp);
        
    }
    
    public boolean AddBrand(String BrandID)
    {
        Brand Temp = new Brand(BrandID,BrandID,0,"original");
        BrandDatabase brandDB = new BrandDatabase();
        return brandDB.Store(Temp);    
    }
    
    public boolean RemoveProduct(String ProductID)
    {
        ProductDatabase productDB = new ProductDatabase();
        if(productDB.IsExist(ProductID))
        {
            return productDB.removeForAdmin(ProductID);
        }
        else
        {
            return false;
        }
    }
    
    public boolean RemoveBrand(String BrandID)
    {
        BrandDatabase brandDB = new BrandDatabase();
        if(brandDB.IsExist(BrandID))
        {
            return brandDB.remove(BrandID);
        }
        else
        {
            return false;
        }
    }
    
    public boolean ProvideVocherCard(String CardID,int Value)
    {
        VoucherCard Temp= new VoucherCard(CardID, CardID, Value);
        VoucherCardsDatabase VoucherCardDB = new VoucherCardsDatabase();
        return VoucherCardDB.Store(Temp);
    }
    
    public boolean RemoveStore(String StoreID)
    {
        StoreDatabase Temp = new StoreDatabase();
        if(Temp.IsExist(StoreID)){
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean RemoveStoreowner(String StoreOwnerUsername)
    {
          StoreownersDatabase Temp = new StoreownersDatabase();
          return Temp.remove(StoreOwnerUsername);
    }
    
    public boolean RemoveCustomer(String CustomerUsername){
        CustomerDatabase Temp = new CustomerDatabase();
        return Temp.remove(CustomerUsername);
    }
    
    public boolean Login(String Username,String Password){
        AdminsDatabase Temp = new AdminsDatabase();
        return Temp.Verify(Username, Password);
    }
    
    public boolean Register(String Name,String Username,String Password)
    {
        AdminsDatabase AdminDB = new AdminsDatabase();
        User Temp = new User(Name, Username, Password, Username);
        return AdminDB.Store(Temp);
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
    
    public Vector<String> GetSystemBrands()
    {
        BrandDatabase B = new BrandDatabase();
        return B.GetAllOriginalBrands();
    }
}

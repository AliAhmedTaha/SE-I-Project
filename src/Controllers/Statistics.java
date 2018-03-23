package Controllers;

import System.*;
import DataBase.*;
import java.util.Vector;

public class Statistics 
{
    public int NumberOfExplorers(String StoreID)
    {
        StoreDatabase StoreDB = new StoreDatabase();
        return StoreDB.GetnumberofExplorers(StoreID);
    }
    
    public int NumberOfBuyers(String StoreID)
    {
        StoreDatabase StoreDB = new StoreDatabase();
        return StoreDB.GetNumberofBuyers(StoreID);
    }
    
    public boolean IncrementNumberOfExplorers(String StoreID)
    {
        StoreDatabase StoreDB = new StoreDatabase();
        return StoreDB.SetnumberofExplorers(StoreID);
    }
    
    public boolean IncrementNumberOfBuyers(String StoreID)
    {
        StoreDatabase StoreDB = new StoreDatabase();
        return StoreDB.SetnumberofBuyers(StoreID);
    }
    
    public Product MostOrderedProducts()
    {
        ProductDatabase ProductDB = new ProductDatabase();
        return ProductDB.GetHighestProduct().get(0);
    }
            
    public Vector<Brand> MostOrderedBrand()
    {
        BrandDatabase BrandDB = new BrandDatabase();
        return BrandDB.GetTheMostOrdered();
    }
}

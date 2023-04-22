package warehouse;

public class Restock {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

        Warehouse theWarehouse = new Warehouse();
        int numOfProducts = StdIn.readInt(); 

        Product[] allProduct = new Product[numOfProducts];
      
        for(int i = 0; i < allProduct.length; i++)
        {
            String addRestock = StdIn.readString(); 
            
            if(addRestock.equals("add"))
            {
                int theDay = StdIn.readInt(); 
                int productID = StdIn.readInt(); 
                String daName = StdIn.readString(); 
                int daStock = StdIn.readInt(); 
                int daDemand = StdIn.readInt(); 

                theWarehouse.addProduct(productID, daName, daStock, theDay, daDemand);    
            }
            else 
            if(addRestock.equals("restock"))
            {
                int productID = StdIn.readInt(); 
                int amount = StdIn.readInt();

                theWarehouse.restockProduct(productID, amount); 
            }
        }
       StdOut.println(theWarehouse);
    
        

	// Uset his file to test restock
    }
}

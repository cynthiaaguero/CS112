package warehouse;

/*
 * Use this class to test the deleteProduct method.
 */ 
public class DeleteProduct {
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
            if(addRestock.equals("delete"))
            {
                int productID = StdIn.readInt(); 
                theWarehouse.deleteProduct(productID);
            }
        }
       StdOut.println(theWarehouse);

	// Use this file to test deleteProduct
    }
}

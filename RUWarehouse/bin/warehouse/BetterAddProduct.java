package warehouse;

/*
 * Use this class to test the betterAddProduct method.
 */ 
public class BetterAddProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

        Warehouse theWarehouse = new Warehouse();
        int numOfProducts = StdIn.readInt(); 

        Product[] allProduct = new Product[numOfProducts];
      
        for(int i = 0; i < allProduct.length; i++)
        {
            int theDay = StdIn.readInt(); 
            int productID = StdIn.readInt(); 
            String daName = StdIn.readString(); 
            int daStock = StdIn.readInt(); 
            int daDemand = StdIn.readInt(); 
            
           // Product tester = new Product(productID, daName, daStock, theDay, daDemand);
            //System.out.println(tester.getName());
            theWarehouse.betterAddProduct(productID, daName, daStock, theDay, daDemand);
          
        }
       StdOut.println(theWarehouse);
    
        
        // Use this file to test betterAddProduct
    }
}

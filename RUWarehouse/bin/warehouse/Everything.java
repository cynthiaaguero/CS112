package warehouse;

/*
 * Use this class to put it all together.
 */ 
public class Everything {
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
             //   System.out.println("add");
                int theDay = StdIn.readInt(); 
                int productID = StdIn.readInt(); 
                String daName = StdIn.readString(); 
                int daStock = StdIn.readInt(); 
                int daDemand = StdIn.readInt(); 

                theWarehouse.addProduct(productID, daName, daStock, theDay, daDemand);    
            }
            if(addRestock.equals("purchase"))
            {
               // System.out.println("purchase");
                int day = StdIn.readInt();
                int productID = StdIn.readInt(); 
                int amount = StdIn.readInt(); 

              //  System.out.println(productID + " "+  day +" " +amount);

                theWarehouse.purchaseProduct(productID, day, amount);
            }
            if(addRestock.equals("restock"))
            {
              //  System.out.println("restock");
                int productID = StdIn.readInt(); 
                int amount = StdIn.readInt();

                theWarehouse.restockProduct(productID, amount); 
            } 
            if(addRestock.equals("delete"))
            {
              //  System.out.println("delete");
                int productID = StdIn.readInt(); 
                theWarehouse.deleteProduct(productID);
            }
        }
        StdOut.println(theWarehouse);

    

	// Use this file to test all methods
    }
}

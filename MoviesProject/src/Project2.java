import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This program will display a product inventory summary which shows the name, inventory code, type,
 * rating, quantity and price of each product. Then it will display the highest/lowest ranked item and the highest/lowest total dollar amounts.
 * 
 * 	 @author Jared Heidotting
 *   @version February 3, 2016
 *   
 */
  
public class Project2 {

public Project2(){}

/*
* Provides user opportunity to allow the user to enter
* a product file and display the results
* 
*/

	public static void main(String[] args) {
		Project2 project02 = new Project2();
		project02.displayProductList(project02.getListOfProducts("Movies.txt"));
	}


/*
* getListOfProducts
* reads and stores product data from a file and returns an List of products
* 
*/
	
	public List<Product> getListOfProducts(String fileName) {
		List<Product> products = new ArrayList<Product>();
		try	{
			Scanner productList = new Scanner(new File("Movies.txt"));
			while(productList.hasNextLine() ) {
				Product product = new Product();
				product.setName(productList.nextLine());
				product.setInventoryCode(productList.nextLine());
				product.setQuantity(Integer.valueOf(productList.nextLine()));
				product.setPrice(Double.valueOf(productList.nextLine()));
				product.setType(productList.nextLine());
				while(productList.hasNextInt() && !productList.hasNext("-1")) {
					product.addUserRating(productList.nextInt());
				}
				if(productList.hasNext("-1")) {	
					while(productList.nextLine().isEmpty());
				}
				products.add(product);
			}
			productList.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("The File could not be found.");
		}
		return products;
	}

/*
* displayProductList
* main method to call display methods for product 
* 
*/
	
	public void displayProductList(List<Product> products) {
		if(products.size()>0) {
			displayProductInventory(products);
			displayProductInformation(products);
		}
		else
			System.out.println("No records to display");
	}



/*
* displayProductInformation
* displays general product information
* 
*/
	
	private void displayProductInformation(List<Product> products) {
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.format("Total products in database: %1d\n", products.size());
		System.out.format("Highest Average Ranked Item: %s\n",this.getHighestAverageRating(products));
		System.out.format("Lowest Average Ranked item: %s\n", this.getLowestAverageRating(products));
		System.out.format("Highest Total Dollar item: %s\n", this.getHighestDollarAmount(products));
		System.out.format("Lowest Total Dollar item: %s\n", this.getLowestDollarAmount(products));
		System.out.println("----------------------------------------------------------------------------------------");
	}


/*
* displayProductInventory
* displays product inventory
* 
*/
	
	private void displayProductInventory(List<Product> products) {
		System.out.println("Product Inventory Summary Report");
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.format("Product Name %5s %4s %6s %6s %6s %6s\n", "                    Inv. Code", "Type", "Rating","# Rat.","Quant.", "Price");
		System.out.format("-------------------------------- %9s %4s %6s %6s %6s %6s\n","---------","----","------","------","------","------");
		for(Product product : products) {
			System.out.format("%-32s %8s   %-4s  %-5s   %1d     %3d   %5s \n",product.getName(), product.getInventoryCode(),product.getType(), retrieveNumberOfRatingStars(product.getAvgUserRating()), product.getUserRatingCount(), product.getQuantity(), product.getPrice());
		}
	}


/*
* getHighestAverageRating
* Retrieves highest user rating from all products in the list
* 
*/
	
	public String getHighestAverageRating(List<Product> products) {
		int[] allProductUserRatings = retrieveAllAverageRatingsFromProducts(products);
		int initialUserRating = allProductUserRatings[0];
		String productName = null;
		for(int index = 0; index < allProductUserRatings.length; index++){
			if(initialUserRating <= allProductUserRatings[index]){
				initialUserRating = allProductUserRatings[index];
				productName = products.get(index).getName();
			}
		}	
		return String.format("%s (%s)", productName, retrieveNumberOfRatingStars(initialUserRating));
	}



/*
* getLowestAverageRating
* Retrieves lowest user rating from all products in the list
* 
*/
	
	public String getLowestAverageRating(List<Product> products) {
		int[] allProductUserRatings = retrieveAllAverageRatingsFromProducts(products);
		int initialUserRating = allProductUserRatings[0];
		String productName = null;
		for(int index = 0; index < allProductUserRatings.length; index++){
			if(initialUserRating >= allProductUserRatings[index]){
				initialUserRating = allProductUserRatings[index];
				productName = products.get(index).getName();
			}
		}
		return String.format("%s (%s)", productName, retrieveNumberOfRatingStars(initialUserRating));
	}

/*
* getHighestDollarAmount
* Retrieves highest dollar amount from all products in the list
* 
*/
	
	public String getHighestDollarAmount(List<Product> products) {
		double initialTotalDollarAmount = (products.get(0).getQuantity() * products.get(0).getPrice());
		String productName = null;
		for(int index = 0; index<products.size(); index++){
			if(initialTotalDollarAmount <= retrieveTotalPriceFromProduct(products, index)){
				initialTotalDollarAmount = retrieveTotalPriceFromProduct(products, index);
				productName = products.get(index).getName();
			}
		}
		return String.format("%s ($%4.2f)", productName, initialTotalDollarAmount);
	}

/*
* getLowestDollarAmount
* Retrieves lowest dollar amount from all products in the list
* 
*/
	
	public String getLowestDollarAmount(List<Product> products) {
		double initialTotalDollarAmount = retrieveTotalPriceFromProduct(products, 0);
		String productName = null;
		for(int index = 0; index < products.size(); index++) {
			if(initialTotalDollarAmount >= retrieveTotalPriceFromProduct(products, index)) {
				initialTotalDollarAmount = retrieveTotalPriceFromProduct(products, index);
				productName = products.get(index).getName();
			}
		}
		return String.format("%s ($%4.2f)", productName, initialTotalDollarAmount);
	}

/*
* retrieveAllAverageRatingsFromProducts
* Retrieves the highest average user rating from each product and returns them as an array
* 
*/
	
	private int[] retrieveAllAverageRatingsFromProducts(List<Product> products) {
		int[] allProductUserRatings = new int[products.size()];
		for(int index = 0; index < products.size(); index++){
			allProductUserRatings[index] = products.get(index).getAvgUserRating();
		}
		return allProductUserRatings;
	}

/*
* retrieveTotalPriceFromProduct
* Retrieves the total dollar amount for a product
* 
*/
	
	private double retrieveTotalPriceFromProduct(List<Product> products, int index) {
		return products.get(index).getQuantity() * products.get(index).getPrice();
	}

/*
 * retrieveNumberOfRatingStars
 * returns the number of user rating stars given to each particular product
 * 	
 */

	private String retrieveNumberOfRatingStars(int size) {
		String starsToPrint = "";
		for(int i = 0; i < size; i++) {
			starsToPrint += "*";
		}
		return starsToPrint;
	}
}


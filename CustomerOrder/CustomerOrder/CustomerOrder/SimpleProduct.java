/*
 * SimpleProduct
 *
 *   @author Jared Heidotting
 *   @version 20120928
 */
package CustomerOrder;

import java.util.Scanner;

public class SimpleProduct implements Product {
    private String name;
    private String type;
    private Double price;
    private Integer quantity;
    private Boolean inStock;

    public SimpleProduct() {
        this.name = " ";
        this.type = " ";
        this.price = 0.0;
        this.quantity = 0;
        this.inStock = false;
    }

    /*
     * setName
     *
     * @param name - new name for the product
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /*
     * getName
     *
     * @return the name of the product
     */
    @Override
    public String getName() {
        return this.name;
    }

    /*
     * setType
     *
     * @param type - the type of the product
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /*
     * getType
     *
     * @return - the product type
     */
    @Override
    public String getType() {
        return this.type;
    }

    /*
     * setPrice
     *
     * @param price - the price of the product
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /*
     * getPrice
     *
     * @return the price of the product
     */
    @Override
    public double getPrice() {
        return this.price;
    }

    /*
     * setQuantity
     *
     * @param quantity - the number of this product in inventory
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /*
     * getQuantity
     *
     * @return the number of this product in inventory
     */
    @Override
    public int getQuantity() {
        return this.quantity;
    }

    /*
     * setInStock
     *
     * @param inStock - true if this product is in stock
     */
    @Override
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    /*
     * getQuantity
     *
     * @return true if this product is in stock
     */
    @Override
    public boolean getInStock() {
        return this.inStock;
    }

    /*
     * readNextProduct
     *
     * @param inFile - a Scanner containing product entries
     *
     * @return false if the product cannot be completely read, true otherwise
     */
    @Override
    public boolean readNextProduct(Scanner inFile) {
        boolean result = false;
        if (inFile.hasNext()) {
            if (inFile.hasNext()) {
                if (inFile.hasNext()) {
                    if (inFile.hasNext()) {
                        if (inFile.hasNext()) {
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "(" + this.name + ", " + this.type + ", " + this.price
                + ", " + this.quantity + ")";
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this.getClass() == obj.getClass()) {
            SimpleProduct test = (SimpleProduct) obj;
            if (this.name.equals(test.getName())) {
                if (this.type.equals(test.getType())) {
                    if (this.price.equals(test.getPrice())) {
                        if (this.quantity.equals(test.getQuantity())) {
                            if (this.inStock == test.inStock) {
                                result = true;
                            }
                        }
                    }
                }
            }

        }
        return result;
    }

}
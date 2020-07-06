package CustomerOrder;

/*
 * Customer
 * 
 * @author Jared Heidotting
 * @date 3/8/16
 * 
 * Extra Credit Customer Class
 * 
 */

import java.util.Scanner;

public class Customer {
    private String first;
    private String last;
    private String address;
    private String city;
    private String state;
    private String zip;
    private Double tax;

    public Customer() {
        this.first = " ";
        this.last = " ";
        this.address = " ";
        this.city = " ";
        this.zip = "00000";
        this.tax = 0.0;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getFirst() {
        return this.first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLast() {
        return this.last;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getZip() {
        return this.zip;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTax() {
        return this.tax;
    }

    public boolean readNextCustomer(Scanner inFile) {
        boolean result = false;
        if (inFile.hasNext()) {
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
        }
        return result;
    }
}

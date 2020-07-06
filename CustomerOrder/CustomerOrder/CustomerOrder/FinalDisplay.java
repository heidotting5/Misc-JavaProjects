/*
 * Final Formatted Display
 * 
 * @author Jared Heidotting
 * @date 3/8/16
 * 
 * Implements the customer class extra credit
 * 
 */
package CustomerOrder;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class FinalDisplay {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an input file name: ");
        String fileName = input.nextLine();
        try {
            Scanner inFile = new Scanner(new File(fileName));
            Customer person = new Customer();
            Queue<Product> onTime = new LinkedList<>();
            Stack<Product> back = new Stack<>();
            if (person.readNextCustomer(inFile)) {
                person.setLast(inFile.nextLine());
                person.setFirst(inFile.nextLine());
                person.setAddress(inFile.nextLine());
                person.setCity(inFile.nextLine());
                person.setState(inFile.nextLine());
                person.setZip(inFile.nextLine());
                person.setTax(Double.parseDouble(inFile.nextLine()));
                while (inFile.hasNext()) {
                    SimpleProduct in = new SimpleProduct();
                    while (in.readNextProduct(inFile)) {
                        in.setName(inFile.nextLine());
                        in.setType(inFile.nextLine());
                        in.setPrice(Double.parseDouble(inFile.nextLine()));
                        in.setQuantity(Integer.parseInt(inFile.nextLine()));
                        String bol = inFile.nextLine();
                        if (bol.equals("true")) {
                            in.setInStock(true);
                        } else {
                            in.setInStock(false);
                        }
                        if (in.getInStock()) {
                            onTime.add(in);
                        } else {
                            back.push(in);
                        }
                        in = new SimpleProduct();
                    }
                }
            } else {
                System.out.println("Error: unable to process file");
            }
            System.out.println();
            System.out.println("Shipping to: ");
            System.out.print(person.getFirst() + " " + person.getLast());
            System.out.println();
            System.out.format("%10.20s", person.getAddress());
            System.out.println();
            System.out.format("%-3s %2s %-5.5s", person.getCity(), person.getState(), person.getZip());
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------");
            double amount = 0;
            while (!onTime.isEmpty()) {
                Product test = onTime.remove();
                double price = test.getPrice() * test.getQuantity();
                amount += price;
                System.out.format("%10.10s %-35.55s %-25.25s %1s %-10.5s",test.getQuantity() + " X", test.getName(),test.getType(), "$", amount);
                System.out.println();
            }
            double shipping = 0.0;
            if (amount <= 10) {
            	shipping = amount * .15;
            }
            else if(amount > 10 && amount < 25) {
            	shipping = amount * .05;
            }
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.format("%10.50s %25.10s", "Total Balance:", amount);
            System.out.println();
            double tax = (amount * person.getTax());
            System.out.format("%10.15s %1s %5.5s %1s %19.3s", "Sales Tax:", "(",person.getTax(),")", tax);
            System.out.println();
            System.out.format("%8.15s %30.3s" , "Shipping:", shipping);
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.format("%6.50s %34.10s", "Total:", amount + tax + shipping);
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("Outstanding Orders: ");
            System.out.print(person.getFirst() + " " + person.getLast());
            System.out.println();
            System.out.format("%-10.25s", person.getAddress());
            System.out.println();
            System.out.format("%-3s %2s %-5.5s", person.getCity(), person.getState(), person.getZip());
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------");
            double sum = 0;
            while (back.size() > 0) {
                Product test = back.pop();
                amount = test.getPrice() * test.getQuantity();
                System.out.format("%10.10s %-35.55s %-25.25s %1s %-10.10s", test.getQuantity() + " X", test.getName(), test.getType(), "$", amount);
                System.out.println();
                sum += test.getPrice() * test.getQuantity();
            }
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.format("%10.50s %59.10s", "Outstanding Balance:", sum);
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------");

        } catch (IOException e) {
            System.out.println("Error reading file name " + fileName + e);
        }

    }

}

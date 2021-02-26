package models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BookingCinema4D {
    private static void booking(Queue<String> myQueueBookingCinema) {
        Scanner input = new Scanner(System.in);
        ArrayList<Customer> customerArrayList = WriteAndReadFileCustomerCSV.readFile();
        System.out.println("List customer:");
        int count = 1;
        for (Customer customer : customerArrayList) {
            System.out.println(count + ". " + customer.getNameCus() + ", " + customer.getId());
            count++;
        }
        System.out.println("Enter customer:");
        int indexCustomer = input.nextInt();
        myQueueBookingCinema.add(customerArrayList.get((indexCustomer - 1)).getNameCus());
    }

    private static void displayListBookingCinema(Queue<String> myQueueBookingCinema) {
        System.out.println("List booking cinema:");
        for (String elementBooking : myQueueBookingCinema) {
            System.out.println(elementBooking);
        }
        myQueueBookingCinema.clear();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue<String> myQueueBookingCinema = new LinkedList<>();
        System.out.println("Enter max num of ticket");
        int maxOfTicket = input.nextInt();
        for (int i = 1; i <= maxOfTicket; i++) {
            booking(myQueueBookingCinema);
        }
        displayListBookingCinema(myQueueBookingCinema);
    }
}

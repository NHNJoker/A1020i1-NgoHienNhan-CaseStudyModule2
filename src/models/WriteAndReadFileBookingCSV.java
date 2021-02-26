package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WriteAndReadFileBookingCSV {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String HEADER = "ID customer,Name customer,ID service,Name service";

    public static void writeFile(ArrayList<Customer> listBooking) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/Data/Booking.csv");
            fileWriter.append(HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);
            for (Customer customer : listBooking) {
                fileWriter.append(String.valueOf(customer.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(customer.getNameCus());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(customer.getUseService().getId());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(customer.getUseService().getServiceName());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public static ArrayList<Customer> readFile() {
        BufferedReader br = null;
        ArrayList<Customer> listArrayBooking = new ArrayList<Customer>();
        Path path = Paths.get("src/Data/Booking.csv");
        if (!Files.exists(path)) {
            try {
                FileWriter fileWriter = new FileWriter("src/Data/Booking.csv");
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        try {
            String line;
            br = new BufferedReader(new FileReader("src/Data/Booking.csv"));
            while ((line = br.readLine()) != null) {
                String[] splitData = line.split(",");
                if (splitData[0].equals("ID customer")) {
                    continue;
                }
                Customer customer = new Customer();
                customer.setId(Integer.parseInt(splitData[0]));
                customer.setNameCus(splitData[1]);
                switch (splitData[2].substring(0, 4)) {
                    case "SVVL":
                        Services villa = new Villa();
                        villa.setId(splitData[2]);
                        villa.setServiceName(splitData[3]);
                        customer.setUseService(villa);
                        listArrayBooking.add(customer);
                        break;
                    case "SVHO":
                        Services house = new House();
                        house.setId(splitData[2]);
                        house.setServiceName(splitData[3]);
                        customer.setUseService(house);
                        listArrayBooking.add(customer);
                        break;
                    case "SVRO":
                        Services room = new Room();
                        room.setId(splitData[2]);
                        room.setServiceName(splitData[3]);
                        customer.setUseService(room);
                        listArrayBooking.add(customer);
                        break;
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return listArrayBooking;
    }
}
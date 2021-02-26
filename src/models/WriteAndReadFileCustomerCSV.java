package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WriteAndReadFileCustomerCSV {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String HEADER = "Name,Birth,Gender,Id,Email,Type of customer,address,Use service";

    public static void writeFile(ArrayList<Customer> listCustomer) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/Data/Customer.csv");
            fileWriter.append(HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);
            for (Customer customer : listCustomer) {
                fileWriter.append(customer.getNameCus());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(customer.getDateOfBirth());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(customer.getGender());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(customer.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(customer.getEmail());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(customer.getTypeOfCus());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(customer.getAddress());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append((CharSequence) customer.getUseService());
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

    public static ArrayList<Customer> readFile(){
        BufferedReader br = null;
        ArrayList<Customer> listCustomer = new ArrayList<Customer>();
        Path path = Paths.get("src/Data/Customer.csv");
        if (!Files.exists(path)){
            try {
                FileWriter fileWriter = new FileWriter("src/Data/Customer.csv");
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }
        try {
            String line;
            br = new BufferedReader(new FileReader("src/Data/Customer.csv"));
            while ((line = br.readLine()) != null) {
                String[] splitData = line.split(",");
                if (splitData[3].equals("Id")) {
                    continue;
                }
                Customer customer = new Customer();
                customer.setNameCus(splitData[0]);
                customer.setDateOfBirth(splitData[1]);
                customer.setGender(splitData[2]);
                customer.setId(Integer.parseInt(splitData[3]));
                customer.setEmail(splitData[4]);
                customer.setTypeOfCus(splitData[5]);
                customer.setAddress(splitData[6]);
                listCustomer.add(customer);
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }finally {
            try {
                assert br != null;
                br.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return listCustomer;
    }
}

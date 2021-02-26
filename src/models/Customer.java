package models;

import java.util.*;
import java.util.regex.Pattern;

public class Customer {
    private String nameCus;
    private String dateOfBirth;
    private String gender;
    private int id;
    private String email;
    private String typeOfCus;
    private String address;
    private Services useService;

    public Customer() {
    }

    public Customer(String nameCus, String dateOfBirth, String gender, int id, String email,
                    String typeOfCus, String address, Services useService) {
        this.nameCus = nameCus;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.id = id;
        this.email = email;
        this.typeOfCus = typeOfCus;
        this.address = address;
        this.useService = useService;
    }


    public String getNameCus() {
        return nameCus;
    }

    public void setNameCus(String nameCus) {
        this.nameCus = nameCus;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTypeOfCus() {
        return typeOfCus;
    }

    public void setTypeOfCus(String typeOfCus) {
        this.typeOfCus = typeOfCus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Services getUseService() {
        return useService;
    }

    public void setUseService(Services useService) {
        this.useService = useService;
    }

    public void addNewCustomer(Customer customer) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of customer:");
        customer.setNameCus(input.nextLine());
        System.out.println("Enter date of birth:");
        customer.setDateOfBirth(input.nextLine());
        System.out.println("Enter gender:");
        customer.setGender(input.nextLine());
        System.out.println("Enter id:");
        customer.setId(input.nextInt());
        input.nextLine();
        System.out.println("Enter email:");
        customer.setEmail(input.nextLine());
        System.out.println("Enter type of customer:\n" + "1. Diamond\n"
                + "2. Platinum\n" + "3. Gold\n" + "4. Silver\n"+"5. Member");
        int chose = input.nextInt();
        switch (chose){
            case 1:
                setTypeOfCus("Diamond");
                break;
            case 2:
                setTypeOfCus("Platinum");
                break;
            case 3:
                setTypeOfCus("Gold");
                break;
            case 4:
                setTypeOfCus("Silver");
                break;
            case 5:
                setTypeOfCus("Member");
                break;
        }
        System.out.println("Enter address:");
        customer.setAddress(input.nextLine());
    }

    @Override
    public String toString() {
        return "nameCus='" + nameCus + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", typeOfCus='" + typeOfCus + '\'' +
                ", address='" + address + '\'' +
                ", useService=" + useService +
                '}';
    }

    public void showInfor(Customer customer) {
        System.out.println(customer.toString());
    }

    public static void showInformationCustomer() {
        ArrayList<Customer> customerArrayList = WriteAndReadFileCustomerCSV.readFile();
        Collections.sort(customerArrayList, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o2.getNameCus().compareTo(o1.getNameCus()) != 0) {
                    return o2.getNameCus().compareTo(o1.getNameCus());
                } else {
                    int yearO1 = Integer.parseInt(o1.getDateOfBirth().substring(6, 10));
                    int yearO2 = Integer.parseInt(o2.getDateOfBirth().substring(6, 10));
                    return yearO1 - yearO2;
                }
            }
        });
        for (Customer customer : customerArrayList) {
            System.out.println(customer.toString());
            System.out.println("----------------------------");
        }
    }

    public void userException(Customer customer) {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            if (!Pattern.matches("[A-Z][a-z]+([ ][A-Z][a-z]+|[ ][A-Z])+", customer.getNameCus())) {
                System.out.println("Client Name must capitalize first letter of each word.(Ex:Nguyen Van A)");
                customer.setNameCus(input.nextLine());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (!Pattern.matches("([A-z]|[a-z]|[.])+[@][a-z]+[.][a-z]+", customer.getEmail())) {
                System.out.println("Email must be in the correct format abc@abc.abc.");
                customer.setEmail(input.nextLine());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (!Pattern.matches("^((male)|(female)|(unknown))$", customer.getGender().toLowerCase())) {
                System.out.println("Enter gender (Male, Female, Unknown)");
                customer.setEmail(input.nextLine());
            } else {
                customer.setGender(customer.getGender().toLowerCase());
                String newGender = customer.getGender().substring(0, 1).toUpperCase() + customer.getGender().substring(1);
                customer.setGender(newGender);
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (!Pattern.matches("[0-9]{9}$", Integer.toString(customer.getId()))) {
                System.out.println("The Id Card must have 9 digits and be in the format XXX XXX XXX");
                customer.setId(input.nextInt());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (Pattern.matches("^(([0-2][0-9])|(30)|(31))\\/(([0][1-9])|([1][0-2]))\\/(([1][9][0-9)][0-9])|([2][0][0-2][0-1]))$", customer.getDateOfBirth())) {
                if ((Integer.parseInt(customer.getDateOfBirth().substring(6, 10)) + 18) <= Calendar.getInstance().get(Calendar.YEAR)) {
                    check = true;
                } else {
                    System.out.println("Year of birth must be greater than 1900 and less than 18 years from" +
                            " the current year, in the correct format dd / mm / yyyy");
                    customer.setDateOfBirth(input.nextLine());
                }
            } else {
                System.out.println("Year of birth must be greater than 1900 and less than 18 years from" +
                        " the current year, in the correct format dd / mm / yyyy");
                customer.setDateOfBirth(input.nextLine());
            }
        }

    }
}
package models;

import java.util.Calendar;
import java.util.Scanner;
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
        System.out.println("Enter type of customer:");
        customer.setTypeOfCus(input.nextLine());
        System.out.println("Enter address:");
        customer.setAddress(input.nextLine());
        System.out.println("Enter use service:");
        checkUseService(customer);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "nameCus='" + nameCus + '\'' +
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

    public void showInformationCustomer(Customer customer) {
        System.out.println("Name of customer:");
        System.out.println(customer.getNameCus());
        System.out.println("Date of birth:");
        System.out.println(customer.getDateOfBirth());
        System.out.println("Gender:");
        System.out.println(customer.getGender());
        System.out.println("ID:");
        System.out.println(customer.getId());
        System.out.println("Email:");
        System.out.println(customer.getEmail());
        System.out.println("Type of customer:");
        System.out.println(customer.getTypeOfCus());
        System.out.println("Address:");
        System.out.println(customer.getAddress());
        System.out.println("Use service:");

    }

    public void userException(Customer customer) {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            if (!Pattern.matches("[A-Z][a-z]+([ ][A-Z][a-z]+)+", customer.getNameCus())) {
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
                if ((Integer.parseInt(customer.getDateOfBirth().substring(6,10))+18)<= Calendar.getInstance().get(Calendar.YEAR)){
                    check = true;
                }else {
                    System.out.println("Year of birth must be greater than 1900 and less than 18 years from" +
                            " the current year, in the correct format dd / mm / yyyy");
                    customer.setDateOfBirth(input.nextLine());
                }
            }else {
                System.out.println("Year of birth must be greater than 1900 and less than 18 years from" +
                        " the current year, in the correct format dd / mm / yyyy");
                customer.setDateOfBirth(input.nextLine());
            }
        }

    }
    public void checkUseService(Customer customer){
        Scanner input = new Scanner(System.in);
        boolean check = false;
        String checkUseService = input.nextLine();
        while (!check) {
            switch (checkUseService) {
                case "Villa": {
                    Services services = new Villa();
                    customer.setUseService(services);
                    check=true;
                    break;
                }
                case "House": {
                    Services services = new House();
                    customer.setUseService(services);
                    check = true;
                    break;
                }
                case "Room": {
                    Services services = new Room();
                    customer.setUseService(services);
                    check = true;
                    break;
                }
                default:
                    System.out.println("Enter use service (Villa or House or Room):");
                    checkUseService = input.nextLine();
            }

        }
    }
}

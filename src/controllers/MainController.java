package controllers;

import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class MainController {

    static void displayMainMenu() {
        Scanner input = new Scanner(System.in);

        boolean check = false;
        while (!check) {
            System.out.println("1. Add new services");
            System.out.println("2. Show service");
            System.out.println("3. Add new customer");
            System.out.println("4. Show information of customer");
            System.out.println("5. Add new booking");
            System.out.println("6. Show information of employee");
            System.out.println("7. Exit");
            int choose = input.nextInt();
            switch (choose) {
                case 1:
                    addNewServices();
                    break;
                case 2:
                    showServices();
                    break;
                case 3:
                    addNewCustomer();
                    break;
                case 4:
                    Customer.showInformationCustomer();
                    break;
                case 5:
                    addNewBooking();
                    break;
                case 6:
                    displayEmployee();
                    break;
                case 7:
                    check = true;
                    break;
            }
        }
    }

    private static void addNewCustomer() {
        ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
        customerArrayList = WriteAndReadFileCustomerCSV.readFile();
        Customer customerObj = new Customer();
        customerObj.addNewCustomer(customerObj);
        customerObj.userException(customerObj);
        customerArrayList.add(customerObj);
        WriteAndReadFileCustomerCSV.writeFile(customerArrayList);
    }

    private static void displayEmployee() {
        HashMap<Integer, String> employeeHashMap = new HashMap<>();
        ArrayList<Employee> employeeList = WriteAndReadFileEmployeeCSV.readFile();
        int key = 1;
        for (Employee employee : employeeList) {
            employeeHashMap.put(key, String.valueOf(employee.toString()));
            key = key + 1;
        }
        for (String i : employeeHashMap.values()) {
            System.out.println(i);
        }
    }

    static void inputInstanceServices(Services services) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter id:");
        services.setId(input.nextLine());
        System.out.println("Enter service name:");
        services.setServiceName(input.nextLine());
        System.out.println("Enter rental costs:");
        services.setRentalCosts(input.nextInt());
        System.out.println("Enter use acreage:");
        services.setAreaUsed(input.nextDouble());
        input.nextLine();
        System.out.println("Enter rental type:");
        services.setRentalType(input.nextLine());
        System.out.println("Enter max people:");
        services.setMaxPeople(input.nextInt());

        if (services instanceof Villa) {
            input.nextLine();
            System.out.println("Enter type of room:");
            ((Villa) services).setTypeOfRoom(input.nextLine());
            System.out.println("Enter other amenities:");
            ((Villa) services).setOtherAmenities(input.nextLine());
            System.out.println("Enter acreage pool:");
            ((Villa) services).setAcreagePool(input.nextDouble());
            System.out.println("Enter num of floor:");
            ((Villa) services).setNumOfFloor(input.nextInt());
        } else if (services instanceof House) {
            input.nextLine();
            System.out.println("Enter type of room:");
            ((House) services).setTypeOfRoom(input.nextLine());
            System.out.println("Enter other amenities:");
            ((House) services).setOtherAmenities(input.nextLine());
            System.out.println("Enter num of floor:");
            ((House) services).setNumOfFloor(input.nextInt());
        } else if (services instanceof Room) {
            input.nextLine();
            ServiceFree serviceFreeObj = new ServiceFree();
            System.out.println("Enter service free name (massage or karaoke or food or drink or car):");
            serviceFreeObj.setServiceFreeName(input.nextLine());
            System.out.println("Enter unit:");
            serviceFreeObj.setUnit(input.nextInt());
            System.out.println("Enter price:");
            serviceFreeObj.setPrice(input.nextInt());
            ((Room) services).setServiceFreeObj(serviceFreeObj);
        }
    }

    static void addNewServices() {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            System.out.println("1. Add new villa");
            System.out.println("2. Add new house");
            System.out.println("3. Add new room");
            System.out.println("4. Back to menu");
            System.out.println("5. Exit");
            int choose = input.nextInt();
            switch (choose) {
                case 1:
                    addNewVilla();
                    break;
                case 2:
                    addNewHouse();
                    break;
                case 3:
                    addNewRoom();
                    break;
                case 4:
                    displayMainMenu();
                    break;
                case 5:
                    check = true;
            }
        }
    }

    private static void addNewRoom() {
        ArrayList<Services> listServiceRoom = new ArrayList<Services>();
        listServiceRoom = WriteAndReadFileServiceCSV.readFileCSV("src/Data/Room.csv",
                "Room");
        Services roomObj = new Room();
        inputInstanceServices(roomObj);
        checkValue(roomObj);
        listServiceRoom.add(roomObj);
        WriteAndReadFileServiceCSV.writeCsvFile(listServiceRoom, "Room");
        System.out.println("successfully added");
    }

    private static void addNewHouse() {
        ArrayList<Services> listServiceHouse = new ArrayList<Services>();
        listServiceHouse = WriteAndReadFileServiceCSV.readFileCSV("src/Data/House.csv",
                "House");
        Services houseObj = new House();
        inputInstanceServices(houseObj);
        checkValue(houseObj);
        listServiceHouse.add(houseObj);
        WriteAndReadFileServiceCSV.writeCsvFile(listServiceHouse, "House");
        System.out.println("successfully added");
    }

    private static void addNewVilla() {
        ArrayList<Services> listServiceVilla = new ArrayList<Services>();
        listServiceVilla = WriteAndReadFileServiceCSV.readFileCSV("src/Data/Villa.csv",
                "Villa");
        Services villaObj = new Villa();
        inputInstanceServices(villaObj);
        checkValue(villaObj);
        listServiceVilla.add(villaObj);
        WriteAndReadFileServiceCSV.writeCsvFile(listServiceVilla, "Villa");
        System.out.println("successfully added");
    }

    static void checkValue(Services services) {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            if (services instanceof Villa) {
                if (!Pattern.matches("^SVVL-[0-9]{4}$", ((Villa) services).getId())) {
                    System.out.println("Enter Id (SVVL-XXXX):");
                    services.setId(input.nextLine());
                } else {
                    check = true;
                }
            } else if (services instanceof House) {
                if (!Pattern.matches("^SVHO-[0-9]{4}$", ((House) services).getId())) {
                    System.out.println("Enter Id (SVHO-XXXX):");
                    services.setId(input.nextLine());
                } else {
                    check = true;
                }
            } else if (services instanceof Room) {
                if (!Pattern.matches("^SVRO-[0-9]{4}$", ((Room) services).getId())) {
                    System.out.println("Enter Id (SVRO-XXX):");
                    services.setId(input.nextLine());
                } else {
                    check = true;
                }
            }
        }
        check = false;
        while (!check) {
            if (!Pattern.matches("[A-Z][a-z]+", services.getServiceName())) {
                System.out.println("Enter service name - Service name must be first capital letters:");
                services.setServiceName(input.nextLine());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services.getAreaUsed() < 30) {
                System.out.println("Enter use acreage (>30):");
                services.setAreaUsed(input.nextDouble());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services instanceof Villa) {
                if (((Villa) services).getAcreagePool() < 30) {
                    System.out.println("Enter acreage pool (>30):");
                    ((Villa) services).setAcreagePool(input.nextDouble());
                } else {
                    check = true;
                }
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services.getRentalCosts() < 0) {
                System.out.println("Enter rental costs (>0):");
                services.setRentalCosts(input.nextInt());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services.getMaxPeople() < 0 && services.getMaxPeople() > 20) {
                System.out.println("Enter max people (>0 and <20):");
                services.setMaxPeople(input.nextInt());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services instanceof Room) {
                if (!Pattern.matches("(massage)|(karaoke)|(food)|(drink)|(car)",
                        ((Room) services).getServiceFreeObj().getServiceFreeName())) {
                    System.out.println("Enter service free name (massage or karaoke or food or drink or car):");
                    ((Room) services).getServiceFreeObj().setServiceFreeName(input.nextLine());
                } else {
                    check = true;
                }
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services instanceof Villa) {
                if (((Villa) services).getNumOfFloor() < 0) {
                    System.out.println("Enter num of floor (>0):");
                    ((Villa) services).setNumOfFloor(input.nextInt());
                } else {
                    check = true;
                }
            } else if (services instanceof House) {
                if (((House) services).getNumOfFloor() < 0) {
                    System.out.println("Enter num of floor (>0):");
                    ((House) services).setNumOfFloor(input.nextInt());
                } else {
                    check = true;
                }
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (!Pattern.matches("[A-Z][a-z]+", services.getRentalType())) {
                System.out.println("Enter rental type:");
                services.setRentalType(input.nextLine());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services instanceof Villa) {
                if (!Pattern.matches("[A-Z][a-z]+", ((Villa) services).getTypeOfRoom())) {
                    System.out.println("Enter type of room - Type of room must be first capital letters:");
                    ((Villa) services).setTypeOfRoom(input.nextLine());
                } else {
                    check = true;
                }
            } else if (services instanceof House) {
                if (!Pattern.matches("[A-Z][a-z]+", ((House) services).getTypeOfRoom())) {
                    System.out.println("Enter type of room - Type of room must be first capital letters:");
                    ((House) services).setTypeOfRoom(input.nextLine());
                } else {
                    check = true;
                }
            } else {
                check = true;
            }
        }

    }

    static void showServices() {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            System.out.println("1. Show all Villa:");
            System.out.println("2. Show all house:");
            System.out.println("3. Show all room:");
            System.out.println("4. Show All Name Villa Not Duplicate:");
            System.out.println("5. Show All Name House Not Duplicate:");
            System.out.println("6. Show All Name Name Not Duplicate:");
            System.out.println("7. Back to menu:");
            System.out.println("8. Exit:");
            int choose = input.nextInt();
            switch (choose) {
                case 1:
                    ArrayList<Services> showListDataVilla = new ArrayList<Services>();
                    showListDataVilla = WriteAndReadFileServiceCSV.readFileCSV("src/Data/Villa.csv",
                            "Villa");
                    for (Services services : showListDataVilla) {
                        ((Villa) services).showInfor();
                    }
                    break;
                case 2:
                    ArrayList<Services> showListDataHouse = new ArrayList<Services>();
                    showListDataHouse = WriteAndReadFileServiceCSV.readFileCSV("src/Data/House.csv",
                            "House");
                    for (Services services : showListDataHouse) {
                        ((House) services).showInfor();
                    }
                    break;
                case 3:
                    ArrayList<Services> showListDataRoom = new ArrayList<Services>();
                    showListDataRoom = WriteAndReadFileServiceCSV.readFileCSV("src/Data/Room.csv",
                            "Room");
                    for (Services services : showListDataRoom) {
                        ((Room) services).showInfor();
                    }
                    break;
                case 4:
                    ArrayList<Services> villaNotDup = WriteAndReadFileServiceCSV.readFileCSV("src/Data/Villa.csv",
                            "Villa");
                    TreeSet<String> treeSetVilla = new TreeSet<String>();
                    for (Services services : villaNotDup) {
                        treeSetVilla.add(((Villa) services).getServiceName());
                    }
                    for (String str : treeSetVilla) {
                        System.out.println(str);
                    }
                    break;
                case 5:
                    ArrayList<Services> houseNotDup = WriteAndReadFileServiceCSV.readFileCSV("src/Data/House.csv",
                            "House");
                    TreeSet<String> treeSetHouse = new TreeSet<String>();
                    for (Services services : houseNotDup) {
                        treeSetHouse.add(((House) services).getServiceName());
                    }
                    for (String str : treeSetHouse) {
                        System.out.println(str);
                    }
                    break;
                case 6:
                    ArrayList<Services> roomNotDup = WriteAndReadFileServiceCSV.readFileCSV("src/Data/Room.csv",
                            "Room");
                    TreeSet<String> treeSetRoom = new TreeSet<String>();
                    for (Services services : roomNotDup) {
                        treeSetRoom.add(((Room) services).getServiceName());
                    }
                    for (String str : treeSetRoom) {
                        System.out.println(str);
                    }
                    break;
                case 7:
                    displayMainMenu();
                    break;
                case 8:
                    check = true;
                    break;
            }
        }
    }

    static void addNewBooking() {
        Scanner input = new Scanner(System.in);
        ArrayList<Customer> listBooking = new ArrayList<Customer>();
        ArrayList<Customer> arrayList = WriteAndReadFileCustomerCSV.readFile();
        int i = 1;
        for (Customer customer : arrayList) {
            System.out.println(i + ". " + customer.getNameCus());
            i++;
        }
        System.out.println("Chon khach hang dat dich vu:");
        int indexCustomer = (input.nextInt() - 1);
        System.out.println("1.Villa \n" +
                "2.House \n" +
                "3.Room");
        int choose = input.nextInt();
        int numOder = 1;
        switch (choose) {
            case 1:
                ArrayList<Services> showListDataVilla = new ArrayList<Services>();
                listBooking = WriteAndReadFileBookingCSV.readFile();
                showListDataVilla = WriteAndReadFileServiceCSV.readFileCSV("src/Data/Villa.csv",
                        "Villa");
                for (Services services : showListDataVilla) {
                    System.out.println(numOder + ". " + ((Villa) services).getId() + "," + ((Villa) services).getServiceName());
                    numOder++;
                }
                int indexService = (input.nextInt() - 1);
                Customer customer = arrayList.get(indexCustomer);
                Villa villa = (Villa) showListDataVilla.get(indexService);
                customer.setUseService(villa);
                listBooking.add(customer);
                WriteAndReadFileBookingCSV.writeFile(listBooking);
                break;
            case 2:
                listBooking = WriteAndReadFileBookingCSV.readFile();
                ArrayList<Services> showListDataHouse = new ArrayList<Services>();
                showListDataHouse = WriteAndReadFileServiceCSV.readFileCSV("src/Data/House.csv",
                        "House");
                for (Services services : showListDataHouse) {
                    System.out.println(numOder + ". " + ((House) services).getServiceName());
                    numOder++;
                }
                int indexServiceHouse = (input.nextInt() - 1);
                Customer customer1 = arrayList.get(indexCustomer);
                House house = (House) showListDataHouse.get(indexServiceHouse);
                customer1.setUseService(house);
                listBooking.add(customer1);
                WriteAndReadFileBookingCSV.writeFile(listBooking);
                break;
            case 3:
                listBooking = WriteAndReadFileBookingCSV.readFile();
                ArrayList<Services> showListDataRoom = new ArrayList<Services>();
                showListDataRoom = WriteAndReadFileServiceCSV.readFileCSV("src/Data/Room.csv",
                        "Room");
                for (Services services : showListDataRoom) {
                    System.out.println(numOder + ". " + ((Room) services).getServiceName());
                    numOder++;
                }
                int indexServiceRoom = (input.nextInt() - 1);
                Customer customer2 = arrayList.get(indexCustomer);
                Room room = (Room) showListDataRoom.get(indexServiceRoom);
                customer2.setUseService(room);
                listBooking.add(customer2);
                WriteAndReadFileBookingCSV.writeFile(listBooking);
                break;
        }
    }


    public static void main(String[] args) {
        displayMainMenu();
    }
}

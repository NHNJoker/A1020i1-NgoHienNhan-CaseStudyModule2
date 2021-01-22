package controllers;

import models.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainController {

    static void displayMainMenu() {
        Scanner input = new Scanner(System.in);
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
                ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
                customerArrayList = WriteAndReadFileCustomerCSV.readFile();
                Customer customerObj = new Customer();
                customerObj.addNewCustomer(customerObj);
                customerObj.userException(customerObj);
                customerArrayList.add(customerObj);
                WriteAndReadFileCustomerCSV.writeFile(customerArrayList);
                for (Customer customer : customerArrayList) {
                    customerObj.showInformationCustomer(customerObj);
                }
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
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
        }
        if (services instanceof House) {
            input.nextLine();
            System.out.println("Enter type of room:");
            ((House) services).setTypeOfRoom(input.nextLine());
            System.out.println("Enter other amenities:");
            ((House) services).setOtherAmenities(input.nextLine());
            System.out.println("Enter num of floor:");
            ((House) services).setNumOfFloor(input.nextInt());
        }
        if (services instanceof Room) {
            input.nextLine();
            ServiceFree serviceFreeObj = new ServiceFree();
            System.out.println("Enter service name:");
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
                    ArrayList<Services> listServiceVilla = new ArrayList<Services>();
                    listServiceVilla = WriteAndReadFileServiceCSV.readFileCSV("src/Data/Villa.csv",
                            "Villa");
                    Services villaObj = new Villa();
                    inputInstanceServices(villaObj);
                    checkValue(villaObj);
                    listServiceVilla.add(villaObj);
                    WriteAndReadFileServiceCSV.writeCsvFile(listServiceVilla, "Villa");
                    System.out.println("successfully added");
                    break;
                case 2:
                    ArrayList<Services> listServiceHouse = new ArrayList<>();
                    listServiceHouse = WriteAndReadFileServiceCSV.readFileCSV("src/Data/House.csv",
                            "House");
                    Services houseObj = new House();
                    inputInstanceServices(houseObj);
                    checkValue(houseObj);
                    listServiceHouse.add(houseObj);
                    WriteAndReadFileServiceCSV.writeCsvFile(listServiceHouse, "House");
                    System.out.println("successfully added");
                    break;
                case 3:
                    ArrayList<Services> listServiceRoom = new ArrayList<>();
                    listServiceRoom = WriteAndReadFileServiceCSV.readFileCSV("src/Data/Room.csv",
                            "Room");
                    Services roomObj = new Room();
                    inputInstanceServices(roomObj);
                    checkValue(roomObj);
                    WriteAndReadFileServiceCSV.writeCsvFile(listServiceRoom, "Room");
                    System.out.println("successfully added");
                    break;
                case 4:
                    displayMainMenu();
                    break;
                case 5:
                    check = true;
            }
        }
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
                    System.out.println("Enter service free name (massage or karaoke or food or drink or car:");
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
            }
            if (services instanceof House) {
                if (!Pattern.matches("[A-Z][a-z]+", ((House) services).getTypeOfRoom())) {
                    System.out.println("Enter type of room - Type of room must be first capital letters:");
                    ((House) services).setTypeOfRoom(input.nextLine());
                } else {
                    check = true;
                }
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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
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

    public static void main(String[] args) {
        displayMainMenu();
    }
}

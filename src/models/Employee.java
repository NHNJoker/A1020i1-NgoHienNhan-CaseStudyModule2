package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Employee {
    private String nameOfEmployee;
    private String age;
    private String dateOfBirth;
    private String email;
    private int id;
    private int numOfPhone;
    private String level;
    private String workPosition;
    private int salary;
    private String addressOfEmployee;

    @Override
    public String toString() {
        return "nameOfEmployee='" + nameOfEmployee + '\'' +
                ", age=" + age +
                ", addressOfEmployee='" + addressOfEmployee + '\'';
    }

    public String getNameOfEmployee() {
        return nameOfEmployee;
    }

    public void setNameOfEmployee(String nameOfEmployee) {
        this.nameOfEmployee = nameOfEmployee;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddressOfEmployee() {
        return addressOfEmployee;
    }

    public void setAddressOfEmployee(String addressOfEmployee) {
        this.addressOfEmployee = addressOfEmployee;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfPhone() {
        return numOfPhone;
    }

    public void setNumOfPhone(int numOfPhone) {
        this.numOfPhone = numOfPhone;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private static void checkValue(Employee employee){
        Scanner input = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            if (!Pattern.matches("[A-Z][a-z]+([ ][A-Z][a-z]+|[ ][A-Z])+", employee.getNameOfEmployee())) {
                System.out.println("Name must capitalize first letter of each word.(Ex:Nguyen Van A)");
                employee.setNameOfEmployee(input.nextLine());
            } else {
                check = true;
            }
        }
        check=false;
        while (!check) {
            if (!Pattern.matches("([A-z]|[a-z]|[.])+[@][a-z]+[.][a-z]+", employee.getEmail())) {
                System.out.println("Email must be in the correct format abc@abc.abc.");
                employee.setEmail(input.nextLine());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (!Pattern.matches("[0-9]{9}$", Integer.toString(employee.getId()))) {
                System.out.println("The Id Card must have 9 digits and be in the format XXX XXX XXX");
                employee.setId(input.nextInt());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (Pattern.matches(
                    "^(([0-2][0-9])|(30)|(31))\\/(([0][1-9])|([1][0-2]))\\/(([1][9][0-9)][0-9])|([2][0][0-2][0-1]))$",
                            employee.getDateOfBirth())) {
                if ((Integer.parseInt(employee.getDateOfBirth().substring(6, 10)) + 18) <=
                        Calendar.getInstance().get(Calendar.YEAR)) {
                    check = true;
                } else {
                    System.out.println("Year of birth must be greater than 1900 and less than 18 years from" +
                            " the current year, in the correct format dd / mm / yyyy");
                    employee.setDateOfBirth(input.nextLine());
                }
            } else {
                System.out.println("Year of birth must be greater than 1900 and less than 18 years from" +
                        " the current year, in the correct format dd / mm / yyyy");
                employee.setDateOfBirth(input.nextLine());
            }
        }
    }

    private static void addNewEmployee(){
        Scanner input = new Scanner(System.in);
        ArrayList<Employee> employeeArrayList = new ArrayList<Employee>();
    for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee();
            System.out.println("Enter name of employee:");
            employee.setNameOfEmployee(input.nextLine());
            System.out.println("Enter Date of birth:");
            employee.setDateOfBirth(input.nextLine());
            System.out.println("Enter id:");
            employee.setId(input.nextInt());
            System.out.println("Enter num of phone:");
            employee.setNumOfPhone(input.nextInt());
            input.nextLine();
            System.out.println("Enter Email:");
            employee.setEmail(input.nextLine());
            System.out.println("Enter level:\n"+"1.Intermediate\n"+"2.Colleges\n"+"3.University\n"+
                    "4.After university");
            int chooseLevel = input.nextInt();
            switch (chooseLevel){
                case 1:
                    employee.setLevel("Intermediate");
                    break;
                case 2:
                    employee.setLevel("Colleges");
                    break;
                case 3:
                    employee.setLevel("University");
                    break;
                case 4:
                    employee.setLevel("After university");
                    break;
            }
            System.out.println("Enter work position:\n"+"1.Receptionist\n" +
                    "2.Staff\n" +
                    "3.Exert\n" +
                    "4.Supervisor\n" +
                    "5.Manager\n" +
                    "6.CEO");
            int choosePosition = input.nextInt();
            switch (choosePosition){
                case 1:
                    employee.setWorkPosition("Receptionist");
                    break;
                case 2:
                    employee.setWorkPosition("Staff");
                    break;
                case 3:
                    employee.setWorkPosition("Exert");
                    break;
                case 4:
                    employee.setWorkPosition("Supervisor");
                    break;
                case 5:
                    employee.setWorkPosition("Manager");
                    break;
                case 6:
                    employee.setWorkPosition("CEO");
                    break;
            }
            input.nextLine();
            System.out.println("Enter address:");
            employee.setAddressOfEmployee(input.nextLine());
            checkValue(employee);
            employee.setAge(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-
                    Integer.parseInt(employee.getDateOfBirth().substring(6, 10))));
            employeeArrayList.add(employee);
       }
        WriteAndReadFileEmployeeCSV.writeFile(employeeArrayList);
    }
    public static void main(String[] args) {
        addNewEmployee();
    }
}
package models;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class EmployeeFileCabinet {
    private static void addEmployeeInFileCabinet(Stack<Employee> myEmployeeFileCabinet) {
        ArrayList<Employee> employeeArrayList = WriteAndReadFileEmployeeCSV.readFile();
        for (Employee employee : employeeArrayList) {
            myEmployeeFileCabinet.push(employee);
        }
    }

    private static void searchEmployee(Stack<Employee> myEmployeeFileCabinet) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of employee:");
        String nameOfEmployee = input.nextLine();
        int count = 0;
        for (Employee employee: myEmployeeFileCabinet){
            if (employee.getNameOfEmployee().equals(nameOfEmployee)){
                System.out.println(employee);
                count++;
            }
        }
        if (count == 0){
            System.out.println("No employee found "+nameOfEmployee);
        }
    }

    public static void main(String[] args) {
        Stack<Employee> myEmployeeFileCabinet = new Stack<>();
        addEmployeeInFileCabinet(myEmployeeFileCabinet);
        searchEmployee(myEmployeeFileCabinet);
    }
}

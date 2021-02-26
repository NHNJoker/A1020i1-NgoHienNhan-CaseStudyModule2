package models;

import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Contract {
    private int contractNum;
    private String startDate;
    private String endDate;
    private int deposit;
    private int totalPayment;

    public int getContractNum() {
        return contractNum;
    }

    public void setContractNum(int contractNum) {
        this.contractNum = contractNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public static void haveAContract(Contract contract) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter contract number:");
        contract.setContractNum(input.nextInt());
        input.nextLine();
        System.out.println("Enter start date:");
        contract.setStartDate(input.nextLine());
        System.out.println("Enter end date:");
        contract.setEndDate(input.nextLine());
        System.out.println("Enter deposit:");
        contract.setDeposit(input.nextInt());
        System.out.println("Enter total payment:");
        contract.setTotalPayment(input.nextInt());
    }

    public static void checkValue(Contract contract) {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            if (Pattern.matches(
                    "^(([0-2][0-9])|(30)|(31))\\/(([0][1-9])|([1][0-2]))\\/(([1][9][0-9)][0-9])|([2][0][0-2][0-1]))$"
                    , contract.getStartDate())) {
                if (Integer.parseInt(contract.getStartDate().substring(6, 10)) >=
                        Calendar.getInstance().get(Calendar.YEAR)) {
                    if (Integer.parseInt(contract.getStartDate().substring(3, 5)) >=
                            (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
                        if (Integer.parseInt(contract.getStartDate().substring(0, 2)) >=
                                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                            check = true;
                        }
                    }
                }
            } else {
                System.out.println("correct format dd / mm / yyyy");
                contract.setStartDate(input.nextLine());
            }
        }
        check = false;
        while (!check) {
            if (Pattern.matches(
                    "^(([0-2][0-9])|(30)|(31))\\/(([0][1-9])|([1][0-2]))\\/(([1][9][0-9)][0-9])|([2][0][0-2][0-1]))$"
                    , contract.getStartDate())) {
                if (Integer.parseInt(contract.getEndDate().substring(6, 10)) >=
                        Calendar.getInstance().get(Calendar.YEAR)) {
                    if (Integer.parseInt(contract.getEndDate().substring(3, 5)) >=
                            (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
                        if (Integer.parseInt(contract.getEndDate().substring(0, 2)) >
                                Integer.parseInt(contract.getStartDate().substring(0, 2))) {
                            check = true;
                        }
                    }
                }
            } else {
                System.out.println("correct format dd / mm / yyyy");
                contract.setStartDate(input.nextLine());
            }
        }
    }
}

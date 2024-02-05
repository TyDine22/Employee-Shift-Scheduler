import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John Wick", "Cashier", "Morning", 40));
        employees.add(new Employee("Narith", "Cleaner", "Afternoon", 30));
        do{
            System.out.println("Mart Employee Shift Schedule");
            System.out.println("1. Show Position and Time preferences");
            System.out.println("2. Register Employee Shift");
            System.out.println("3. Employees Shift");
            System.out.println("4. Search Employee Shift by name");
            System.out.println("5. Delete Existing Shift");
            System.out.println("6. Exit program");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    showTimeAndPosition();
                    break;
                case 2:
                    employees.add(createEmployee(input));
                    break;
                case 3:
                    if (employees.isEmpty()) {
                        System.out.println("No registered employees.");
                    } else {
                        displayAllEmployeeShifts(employees);
                    }
                    break;
                case 4:
                    input.nextLine();
                    System.out.print("Input name to search: ");
                    String searchName = input.nextLine();
                    Employee foundEmployee = searchEmployeeByName(employees, searchName);

                    if (foundEmployee != null) {
                        // Display the found employee details, including start and end hours
                        displayEmployeeShift(foundEmployee);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                case 5:
                    input.nextLine();
                    System.out.print("Input name to delete: ");
                    String deleteName = input.nextLine();
                    deleteEmployeeShift(employees, deleteName);
                    break;
                case 6:
                    System.out.println("Thank you for using our program!");
                    break;
                default:
                    System.out.println("Pick a choice between 1 and 6!");
                    break;
            }
        }while(choice != 6);
        input.close();
    }
    private static boolean isValidPosition(String position) {
        return position.equals("security") || position.equals("cashier") || position.equals("stockcontroller") || position.equals("cleaner");
    }

    private static boolean isValidPreference(String prefer) {
        return prefer.equals("morning") || prefer.equals("afternoon") || prefer.equals("night");
    }

    //1. Function to input employee
    private static Employee createEmployee(Scanner input){
        System.out.print("Input Employee Name: ");
        String name = input.next();
        String position;
        do {
            System.out.print("Input Position (Security/Cashier/StockController/Cleaner): ");
            position = input.next().toLowerCase();

            if (!isValidPosition(position)) {
                System.out.println("Invalid position. Please enter a valid position.");
            }
        } while (!isValidPosition(position));

        String prefer;
        do {
            System.out.print("Input Time Preference (Morning/Afternoon/Night): ");
            prefer = input.next().toLowerCase();

            if (!isValidPreference(prefer)) {
                System.out.println("Invalid preference. Please enter Morning, Afternoon, or Night.");
            }
        } while (!isValidPreference(prefer));

        int legalWorkHour = 0;

        if (position.equals("security")) {
            if (!prefer.equals("morning") && !prefer.equals("night")) {
                System.out.println("Security position can only work Morning or Night shifts.");
                return createEmployee(input);
            }

            System.out.print("Input your total working hours per week (>= 50 for morning, >= 40 for night): ");
            legalWorkHour = input.nextInt();

            if ((prefer.equals("morning") && legalWorkHour < 50) || (prefer.equals("night") && legalWorkHour < 40)) {
                System.out.println("Invalid working hours for Security position.");
                return createEmployee(input);
            }
        } else if (position.equals("cleaner")) {
            if (!prefer.equals("afternoon")) {
                System.out.print("Input your total working hours per week (< 40): ");
                legalWorkHour = input.nextInt();

                if (legalWorkHour >= 40) {
                    System.out.println("Invalid working hours for Cleaner position.");
                    return createEmployee(input);  // Recursive call to re-enter details
                }
            }
        } else {
            // For other positions, validate working hours if needed
            if (!prefer.equals("afternoon")) {
                System.out.print("Input your total working hours per week: ");
                legalWorkHour = input.nextInt();
            }
        }

        System.out.println("Employee registered successfully!");
        System.out.println();
        return new Employee(name, position, prefer, legalWorkHour);
    }

    //2. Function to show time preference and position
    private static void showTimeAndPosition() {
        System.out.println("+ Preferences: Full Time or Part Time:");
        System.out.println("  Full Time:");
        System.out.println("    Morning Shift: 07:00 to 17:00");
        System.out.println("    Night Shift: 23:00 to 06:00");

        System.out.println("  Part Time:");
        System.out.println("    Morning Shift: 08:00 to 12:00");
        System.out.println("    Afternoon Shift: 13:00 to 17:00");
        System.out.println("    Night Shift: 18:00 to 22:00");

        System.out.println("\n+ Positions:");
        // Display Full Time positions for Morning and Night Shift

        System.out.println("  For Full Time Morning & Night Shift:");
        System.out.println("    " + FullTimePosition.Security.class.getSimpleName());
        System.out.println("    " + FullTimePosition.Cashier.class.getSimpleName());
        System.out.println("    " + FullTimePosition.StockController.class.getSimpleName());
        // Display Part Time positions
        System.out.println("  For Part Time:");
        System.out.println("    " + PartTimePosition.Cleaner.class.getSimpleName());
        System.out.println("    " + PartTimePosition.Cashier.class.getSimpleName());
        System.out.println("    " + PartTimePosition.StockController.class.getSimpleName());
        System.out.println();
    }

    private static String getShiftStartHour(Employee employee) {
        String prefer = employee.getPrefer().toLowerCase();
        if (prefer.equals("morning")) {
            return (employee.getLegalWorkHour() >= 50) ? String.valueOf(FullTime.MorningShift.START_HOUR) : String.valueOf(PartTime.MorningShift.START_HOUR);
        } else if (prefer.equals("night")) {
            return (employee.getLegalWorkHour() >= 40) ? String.valueOf(FullTime.NightShift.START_HOUR) : String.valueOf(PartTime.NightShift.START_HOUR);
        } else {
            return String.valueOf(PartTime.AfternoonShift.START_HOUR);
        }
    }

    private static String getShiftEndHour(Employee employee) {
        String prefer = employee.getPrefer().toLowerCase();
        if (prefer.equals("morning")) {
            return (employee.getLegalWorkHour() >= 50) ? String.valueOf(FullTime.MorningShift.END_HOUR) : String.valueOf(PartTime.MorningShift.END_HOUR);
        } else if (prefer.equals("night")) {
            return (employee.getLegalWorkHour() >= 40) ? String.valueOf(FullTime.NightShift.END_HOUR) : String.valueOf(PartTime.NightShift.END_HOUR);
        } else {
            return String.valueOf(PartTime.AfternoonShift.END_HOUR);
        }
    }

    //3. Function to show all registered employees
    private static void displayAllEmployeeShifts(List<Employee> employees) {
        System.out.println("All Employee Shift Information: ");
        System.out.printf("%-20s%-25s%-15s%-15s%-15s%n", "Name", "Position", "Shift", "Start Hour", "End Hour");

        for (Employee employee : employees) {
            System.out.printf("%-20s%-25s%-15s%-15s%-15s%n", employee.getName(),
                                                                  employee.getPosition(),
                                                                  employee.getPrefer(),
                                                                  getShiftStartHour(employee),
                                                                  getShiftEndHour(employee));
        }
        System.out.println();
    }

    //4. Function to search an individual employee by name
    private static Employee searchEmployeeByName(List<Employee> employees, String name) {
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                return employee;
            }
        }
        return null; // If not found
    }

    private static void displayEmployeeShift(Employee employee) {
        System.out.println("Employee Shift Information: ");
        System.out.printf("%-20s%-25s%-15s%-15s%-15s%-15s%n", "Name", "Position", "Shift", "Start Hour", "End Hour", "Legal Work Hour");
        System.out.printf("%-20s%-25s%-15s%-15s%-15s%-15s%n",
                employee.getName(),
                employee.getPosition(),
                employee.getPrefer(),
                getShiftStartHour(employee),
                getShiftEndHour(employee),
                employee.getLegalWorkHour());
        System.out.println();
    }

    //5. Function to delete an employee's shift by name
    private static void deleteEmployeeShift(List<Employee> employees, String name) {
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            if (employee.getName().equalsIgnoreCase(name)) {
                employees.remove(i);
                System.out.println("Shift for employee " + name + " deleted.");
                System.out.println();
                return;
            }
        }
        System.out.println("Employee with name " + name + " not found. No shift deleted.");
    }

}
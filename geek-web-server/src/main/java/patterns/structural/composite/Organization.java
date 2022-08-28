package patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Organization {

    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee){
        this.employees.add(employee);
    }

    public int getNetSalaries(){
        int netSalary = 0;
        for (Employee employee : this.employees) {
            netSalary +=employee.getSalary();
        }

        return netSalary;
    }
}

package patterns.structural.composite;

import java.util.List;

public class Designer implements Employee{

    private int salary;
    private String name;
    private List<String> roles;

    public Designer( String name, int salary) {
        this.salary = salary;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public List<String> getRoles() {
        return this.roles;
    }
}

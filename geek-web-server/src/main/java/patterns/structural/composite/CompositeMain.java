package patterns.structural.composite;

public class CompositeMain {

    public static void main(String[] args) {
        Employee john = new Designer("John", 200);
        Employee jack = new Developer("Jack",100);
        Organization organization = new Organization();
        organization.addEmployee(jack);
        organization.addEmployee(john);
        System.out.println(organization.getNetSalaries());
    }
}

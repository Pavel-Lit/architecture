package patterns.structural.decorator;

public class DecoratorMain {
    public static void main(String[] args) {
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println(simpleCoffee.getCost());
        System.out.println(simpleCoffee.getDescription());

        Coffee milkCoffee = new MilkCoffee(simpleCoffee);
        System.out.println(milkCoffee.getCost());
        System.out.println(milkCoffee.getDescription());
    }
}

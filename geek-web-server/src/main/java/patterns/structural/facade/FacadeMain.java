package patterns.structural.facade;

public class FacadeMain {

    public static void main(String[] args) {
        ComputerFacade computerFacade = new ComputerFacade(new Computer());

        computerFacade.turnOn();
        System.out.println("*******************");
        computerFacade.turnOff();
    }
}

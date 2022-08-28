package patterns.structural.proxy;

public class ProxyMain {
    public static void main(String[] args) {
        SecuredDoor securedDoor = new SecuredDoor(new LabDoor());

        securedDoor.open("222");
        securedDoor.open("111");
        securedDoor.close();

    }
}

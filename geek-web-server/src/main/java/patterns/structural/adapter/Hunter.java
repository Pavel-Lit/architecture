package patterns.structural.adapter;

public class Hunter {

    public void hunt(Lion lion){
        System.out.println("shot ");
        lion.roar();
    }
}

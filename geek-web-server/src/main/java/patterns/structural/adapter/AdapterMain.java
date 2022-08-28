package patterns.structural.adapter;

public class AdapterMain {

    public static void main(String[] args) {


        AfricanLion africanLion = new AfricanLion();
        WildDogAdapter wildDogAdapter = new WildDogAdapter(new WildDog());

        Hunter hunter = new Hunter();
        hunter.hunt(wildDogAdapter);

        hunter.hunt(africanLion);
    }
}

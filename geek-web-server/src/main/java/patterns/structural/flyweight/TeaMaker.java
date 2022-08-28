package patterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class TeaMaker {

    private static final Map<String, Tea> availableTea = new HashMap<>();

    public Tea make(String teaType) {
        Tea tea = availableTea.get(teaType);

        if (tea == null) {
            switch (teaType) {
                case "kara":
                    System.out.println("Making kara tea");
                    tea = new KaraTea();
                    break;
                case "black":
                    System.out.println("Making black tea");
                    tea = new BlackTea();
            }
            availableTea.put(teaType, tea);
        }
        return tea;
    }

}

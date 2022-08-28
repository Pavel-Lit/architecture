package patterns.structural.flyweight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeaShop {

//    private Map<String, Integer> orders = new HashMap<>();
//    private TeaMaker teaMaker;
//
//    public TeaShop(TeaMaker teaMaker) {
//        this.teaMaker = teaMaker;
//    }
//
//    public void takeOrder(String teaType, int table){
//        this.orders.put(this.teaMaker.make(teaType), table);
//    }
//
//    public void serve(){
//        for(Map.Entry<List<Tea>, Integer> entry: orders.entrySet())
//            System.out.println("Serving tea for table: "+entry.getValue());
//
//    }

    public static void main(String[] args) {
        TeaMaker teaMaker = new TeaMaker();

        List<Tea> teas = new ArrayList<>();

        teas.add(teaMaker.make("black"));
        teas.add(teaMaker.make("black"));
        teas.add(teaMaker.make("black"));
        teas.add(teaMaker.make("black"));
        teas.add(teaMaker.make("black"));
        teas.add(teaMaker.make("black"));
        teas.add(teaMaker.make("black"));
        teas.add(teaMaker.make("kara"));
        teas.add(teaMaker.make("kara"));
        teas.add(teaMaker.make("kara"));
        teas.add(teaMaker.make("kara"));
        teas.add(teaMaker.make("kara"));
        teas.add(teaMaker.make("kara"));
        teas.add(teaMaker.make("kara"));

        for (Tea t: teas ) {
            t.teaType();

        }
    }
}

package gp.solutions.taskNo346;

import java.util.ArrayList;
import java.util.List;

public class Main3 {

    public static void main(String[] args) {
        List<Integer> aResult = new ArrayList<>();
        aResult.add(1);
        aResult.add(2);
        aResult.add(3);
        aResult.add(Integer.valueOf(0));


        System.out.println(aResult.contains(0));
    }
}

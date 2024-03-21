package n1582C;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Special special = new Special();


        System.out.println(Arrays.deepToString(special.matrix_1()));
        System.out.println(special.numSpecial(special.matrix_1()));



        System.out.println(Arrays.deepToString(special.matrix_2()));
        System.out.println(special.numSpecial(special.matrix_2()));


    }


}

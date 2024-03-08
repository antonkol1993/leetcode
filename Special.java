package leetcode.special.number;


import java.util.Arrays;
import java.util.Random;

// 1582. Special Positions in a Binary Matrix
//  https://leetcode.com/problems/special-positions-in-a-binary-matrix/description/?envType=daily-question&envId=2024-03-06
public class Special {

    private int i;
    private int j;


    public Special(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int [][] matrix () {
        Random random = new Random();

        int [][]matrixSize = new int[i][j];
        for (int i = 0; i < matrixSize.length; i ++) {
            for (int j = 0; j < matrixSize[i].length; j ++) {
                matrixSize[i][j] = random.nextInt(2);
                System.out.printf("%3d", matrixSize[i][j]);
            }
            System.out.println();
        }
        return matrixSize;
    }

    public int numSpecial(int[][] mat) {
        mat = matrix();
        int count = 0;
        for (int i = 0; i < mat.length; i ++) {
            for (int j = 0; j < mat[i].length; j ++) {
                if (mat[i][j] == 1) {
                     for (int k = 0; k < mat.length; k ++) {
                         for (int f = 0; f < mat[i].length; f ++) {

                         }
                     }
                }
            }

        return count;
    }


}

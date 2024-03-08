package leetcode.special.number._1582.special.positions.in.a.binary.matrix;


import java.util.Arrays;

// 1582. Special Positions in a Binary Matrix
//  https://leetcode.com/problems/special-positions-in-a-binary-matrix/description/?envType=daily-question&envId=2024-03-06
public class Special {


    public int[][] matrix_1() {
        int[][] matrix = new int[3][3];
        for (int[] ints : matrix) {
            Arrays.fill(ints, 0);
        }
        matrix[0][0] = 1;
        matrix[1][2] = 1;
        matrix[2][0] = 1;

        return matrix;
    }

    public int[][] matrix_2() {
        int[][] matrix = new int[3][3];
        for (int[] ints : matrix) {
            Arrays.fill(ints, 0);
        }
        matrix[0][0] = 1;
        matrix[1][1] = 1;
        matrix[2][2] = 1;

        return matrix;
    }

    public int numSpecial(int[][] mat) {
        int countSpec = 0;

        for (int[] ints : mat) {
            for (int j = 0; j < ints.length; j++) {
                int countHorizontal = 0;
                int countVertical = 0;
                int k;

                if (ints[j] == 1) {
                    int f = 0;

                    for (k = 0; k < mat.length; k++, f++) {

                        if (mat[k][j] == 1) {
                            countVertical += 1;
                        }
                        if (ints[f] == 1) {
                            countHorizontal += 1;

                        }
                    }

                    if (countVertical == 1 && countHorizontal == 1) {
                        countSpec += 1;
                    }
                }
            }
        }
        return countSpec;
    }


}



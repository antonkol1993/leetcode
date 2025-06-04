package gp.solutions.taskNo193;

import java.io.*;
import java.util.Arrays;

import static java.util.Arrays.deepToString;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
        String[] params = reader.readLine().trim().split(" ");
        int heightN = Integer.parseInt(params[0]);
        int widthM = Integer.parseInt(params[1]);
        int countK = Integer.parseInt(params[2]);

        int[][] field = new int[heightN][widthM];

        for (int i = 0; i < heightN; i++) {
            String[] row = reader.readLine().trim().split(" ");
            for (int j = 0; j < widthM; j++) {
                field[i][j] = Integer.parseInt(row[j]);
            }
        }
        reader.close();

        for (int[] row : field) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }


        int[] minX = new int[countK + 1];
        int[] maxX = new int[countK + 1];
        int[] minY = new int[countK + 1];
        int[] maxY = new int[countK + 1];

        for (int i = 1; i <= countK; i++) {
            minX[i] = Integer.MAX_VALUE;
            maxX[i] = Integer.MIN_VALUE;
            minY[i] = Integer.MAX_VALUE;
            maxY[i] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < heightN; i++) {
            for (int j = 0; j < widthM; j++) {
                int num = field[i][j];
                if (num > 0) {
                    int y = heightN - 1 - i;
                    int x = j;

                    minX[num] = Math.min(minX[num], x);
                    maxX[num] = Math.max(maxX[num], x);
                    minY[num] = Math.min(minY[num], y);
                    maxY[num] = Math.max(maxY[num], y);
                }
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT.TXT"));
        for (int i = 1; i <= countK; i++) {
            writer.write(minX[i] + " " + minY[i] + " " + maxX[i] + " " + maxY[i]);
            writer.newLine();
        }
        writer.close();
    }
}

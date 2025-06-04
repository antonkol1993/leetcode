package gp.solutions.taskNo193;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
        String[] params = reader.readLine().trim().split(" ");
        int N = Integer.parseInt(params[0]);
        int M = Integer.parseInt(params[1]);
        int K = Integer.parseInt(params[2]);

        int[][] field = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] row = reader.readLine().trim().split(" ");
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(row[j]);
            }
        }
        reader.close();

        int[] minX = new int[K + 1];
        int[] maxX = new int[K + 1];
        int[] minY = new int[K + 1];
        int[] maxY = new int[K + 1];

        for (int i = 1; i <= K; i++) {
            minX[i] = M;
            maxX[i] = -1;
            minY[i] = N;
            maxY[i] = -1;
        }

        boolean[] found = new boolean[K + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = field[i][j];
                if (num > 0) {
                    found[num] = true;
                    minX[num] = Math.min(minX[num], j);
                    maxX[num] = Math.max(maxX[num], j);
                    minY[num] = Math.min(minY[num], N - 1 - i);
                    maxY[num] = Math.max(maxY[num], N - 1 - i);
                }
            }
        }

        int[][] top = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(field[i], 0, top[i], 0, M);
        }

        for (int k = K; k >= 1; k--) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (top[i][j] == k) {
                        top[i][j] = 0;
                    }
                }
            }
        }

        for (int k = 1; k <= K; k++) {
            boolean visible = false;
            minX[k] = M;
            maxX[k] = -1;
            minY[k] = N;
            maxY[k] = -1;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (field[i][j] == k && top[i][j] == 0) {
                        visible = true;
                        minX[k] = Math.min(minX[k], j);
                        maxX[k] = Math.max(maxX[k], j);
                        minY[k] = Math.min(minY[k], N - 1 - i);
                        maxY[k] = Math.max(maxY[k], N - 1 - i);
                    }
                }
            }

            if (!visible) {
                minX[k] = 0;
                minY[k] = 0;
                maxX[k] = M - 1;
                maxY[k] = N - 1;
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT.TXT"));
        for (int i = 1; i <= K; i++) {
            writer.write(minX[i] + " " + minY[i] + " " + (maxX[i] + 1) + " " + (maxY[i] + 1));
            writer.newLine();
        }
        writer.close();
    }
}

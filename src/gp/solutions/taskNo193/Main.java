package gp.solutions.taskNo193;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
        String[] params = reader.readLine().trim().split(" ");
        int N = Integer.parseInt(params[0]); // строки
        int M = Integer.parseInt(params[1]); // столбцы
        int K = Integer.parseInt(params[2]); // количество прямоугольников

        int[][] field = new int[N][M];
        boolean[] found = new boolean[K + 1]; // отмечаем, какие номера встретились
        boolean hasZero = false; // есть ли 0 в поле

        for (int i = 0; i < N; i++) {
            String[] row = reader.readLine().trim().split(" ");
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(row[j]);
                field[i][j] = num;
                if (num == 0) hasZero = true;
                if (num > 0) found[num] = true;
            }
        }
        reader.close();

        int[] minX = new int[K + 1];
        int[] minY = new int[K + 1];
        int[] maxX = new int[K + 1];
        int[] maxY = new int[K + 1];

        for (int i = 1; i <= K; i++) {
            minX[i] = M;
            minY[i] = N;
            maxX[i] = -1;
            maxY[i] = -1;
        }

        // Ищем границы каждого прямоугольника
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int rect = field[i][j];
                if (rect > 0) {
                    int y = N - 1 - i;
                    int x = j;    // избыточный, но так легче понимать код
                    minX[rect] = Math.min(minX[rect], x);
                    maxX[rect] = Math.max(maxX[rect], x + 1);
                    minY[rect] = Math.min(minY[rect], y);
                    maxY[rect] = Math.max(maxY[rect], y + 1);
                }
            }
        }

        // write result
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT.TXT"));
        for (int i = 1; i <= K; i++) {
            //условие. Если не найдено число в поле и оно не имеет 0, то rect скрыт под всеми
            //и он на всё поле.
            // если есть 0, то оно под всем прямоугольниками
            //Иначе, записываем
            if (!found[i]) {
                if (!hasZero) {
                    writer.write("0 0 " + M + " " + N);
                } else {
                    // Ищем минимальный прямоугольник, покрывающий все НЕнулевые ячейки
                    int localMinX = M;
                    int localMinY = N;
                    int localMaxX = -1;
                    int localMaxY = -1;
                    for (int row = 0; row < N; row++) {
                        for (int col = 0; col < M; col++) {
                            if (field[row][col] != 0) {
                                int x = col; // избыточный, но так легче понимать код
                                int y = N - 1 - row;
                                localMinX = Math.min(localMinX, x);
                                localMaxX = Math.max(localMaxX, x + 1);
                                localMinY = Math.min(localMinY, y);
                                localMaxY = Math.max(localMaxY, y + 1);
                            }
                        }
                    }
                    // Если хотя бы одна ячейка была ненулевой
                    if (localMinX <= localMaxX && localMinY <= localMaxY) {
                        writer.write(localMinX + " " + localMinY + " " + localMaxX + " " + localMaxY);
                    }
                }
            } else {
                writer.write(minX[i] + " " + minY[i] + " " + maxX[i] + " " + maxY[i]);
            }
            writer.newLine();
        }
        writer.close();
    }
}

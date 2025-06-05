package gp.solutions.taskNo492;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Читаем входной файл
        BufferedReader bufferedReader = new BufferedReader(new FileReader("INPUT.TXT"));
        PrintWriter printWriter = new PrintWriter(new FileWriter("OUTPUT.TXT"));

        // Чтение координат цели
        String[] parts = bufferedReader.readLine().split(" ");
        int Tx0 = Integer.parseInt(parts[0]);
        int Ty0 = Integer.parseInt(parts[1]);

        // Чтение скорости цели
        parts = bufferedReader.readLine().split(" ");
        int VTargetX = Integer.parseInt(parts[0]);
        int VTargetY = Integer.parseInt(parts[1]);

        // Чтение параметров РК-2000
        parts = bufferedReader.readLine().split(" ");
        int V = Integer.parseInt(parts[0]);
        int t = Integer.parseInt(parts[1]);
        int d = Integer.parseInt(parts[2]);

        // Положение цели через t секунд
        long targetX = Tx0 + 1L * VTargetX * t;
        long targetY = Ty0 + 1L * VTargetY * t;

        // Квадрат расстояния от начала координат до цели через t секунд
        double distanceToTarget = Math.sqrt(targetX * targetX + targetY * targetY);

        // Максимальный путь крейсера за t секунд
        double maxDistance = V * t;

        // Проверяем, может ли крейсер оказаться на расстоянии d от цели
        if (Math.abs(distanceToTarget - d) <= maxDistance + 1e-9) {
            printWriter.println("YES");
        } else {
            printWriter.println("NO");
        }

        printWriter.close();
        bufferedReader.close();
    }
}


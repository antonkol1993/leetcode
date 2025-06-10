package gp.solutions.taskNo492;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("INPUT.TXT"));
        PrintWriter pw = new PrintWriter(new FileWriter("OUTPUT.TXT"));

        // Начальные координаты цели
        String[] line = br.readLine().split(" ");
        long x0 = Long.parseLong(line[0]);
        long y0 = Long.parseLong(line[1]);

        // Скорость цели (вектор)
        line = br.readLine().split(" ");
        long vx = Long.parseLong(line[0]);
        long vy = Long.parseLong(line[1]);

        // Параметры РК-2000: скорость, время, расстояние
        line = br.readLine().split(" ");
        long v = Long.parseLong(line[0]);
        long t = Long.parseLong(line[1]);
        long d = Long.parseLong(line[2]);

        // Координаты цели через t секунд (движется равномерно)
        long xt = x0 + vx * t;
        long yt = y0 + vy * t;

        // Квадрат расстояния до цели
        long r2 = xt * xt + yt * yt;

        //проверка
        long maxDistance = v * t;
        long minReachable = d - maxDistance;
        if (minReachable < 0) minReachable = 0;
        long min2 = minReachable * minReachable;
        long max2 = (d + maxDistance) * (d + maxDistance);

        if (r2 >= min2 && r2 <= max2) {
            pw.println("YES");
        } else {
            pw.println("NO");
        }

        pw.close();
        br.close();
    }
}


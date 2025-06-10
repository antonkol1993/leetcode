package gp.solutions.taskNo548;

import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
        String num1 = reader.readLine().trim();
        String num2 = reader.readLine().trim();
        reader.close();

        int i = 0, j = 0;
        StringBuilder result = new StringBuilder();

        // цикл: пока есть хотя бы одна непросмотренная цифра
        while (i < num1.length() && j < num2.length()) {
            char c1 = num1.charAt(i);
            char c2 = num2.charAt(j);

            if (c1 < c2) {
                result.append(c1); // выбираем меньшую цифру
                i++;
            } else if (c1 > c2) {
                result.append(c2);
                j++;
            } else {
                // Если цифры равны — смотрим вперёд
                int tempI = i, tempJ = j;
                while (tempI < num1.length() && tempJ < num2.length() && num1.charAt(tempI) == num2.charAt(tempJ)) {
                    tempI++;
                    tempJ++;
                }
                // Если одна из строк закончилась — берем из другой
                if (tempI == num1.length()) {
                    result.append(c2);
                    j++;
                } else if (tempJ == num2.length()) {
                    result.append(c1);
                    i++;
                } else if (num1.charAt(tempI) < num2.charAt(tempJ)) {
                    result.append(c1);
                    i++;
                } else {
                    result.append(c2);
                    j++;
                }
            }
        }

        result.append(num1.substring(i));
        result.append(num2.substring(j));

        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT.TXT"));
        writer.write(result.toString());
        writer.close();
    }
}





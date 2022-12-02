package com.advent;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static File file;
    static BufferedReader reader = null;

    public static void main(String[] args) {
        String temp = "";
        // temp += day1("a");
        // temp += day1("b");
        // temp += day2("a");
        temp += day2("b");

        CopyToClipboard(temp);
        System.out.println(temp);
    }

    private static int day1(String teilaufgabe) {
        file = new File("resources/day1.txt");
        readFile(file);

        List<Integer> calorieList = new ArrayList<Integer>();
        int temp = 0;

        for (String string : getStringList()) {
            if (string.isEmpty()) {
                calorieList.add(temp);
                temp = 0;
            } else {
                temp += Integer.parseInt(string);
            }
        }

        // Verkehrt herum sortieren, damit die größten vorne sind.
        calorieList.sort((o1, o2) -> o2.compareTo(o1));

        if (teilaufgabe.equals("a")) {
            return calorieList.get(0);
        } else {
            return calorieList.get(0) + calorieList.get(1) + calorieList.get(2);
        }

    }

    private static int day2(String teilaufgabe) {
        file = new File("resources/day2.txt");

        readFile(file);
        // String[] test = { "A Y", "B X", "C Z" };
        int gesammtPunkte = 0;
        if (teilaufgabe.equals("a")) {
            for (String string : getStringList()) {
                int temp = 0;
                char first = string.charAt(0);
                char last = string.charAt(2);
                switch (first) {
                case 'A':
                    if (last == 'Y') {
                        temp += 8;
                    } else if (last == 'X') {
                        temp += 4;
                    } else {
                        temp += 3;
                    }
                    break;
                case 'B':
                    if (last == 'Z') {
                        temp += 9;
                    } else if (last == 'Y') {
                        temp += 5;
                    } else {
                        temp += 1;
                    }
                    break;
                case 'C':
                    if (last == 'X') {
                        temp += 7;
                    } else if (last == 'Z') {
                        temp += 6;
                    } else {
                        temp += 2;
                    }
                    break;

                default:
                    break;
                }
                gesammtPunkte += temp;
            }
        } else {
            for (String string : getStringList()) {
                int temp = 0;
                char first = string.charAt(0);
                char last = string.charAt(2);
                switch (last) {
                case 'X':
                    if (first == 'A') {
                        temp += 3;
                    } else if (first == 'B') {
                        temp += 1;
                    } else {
                        temp += 2;
                    }
                    break;
                case 'Y':
                    temp += 3;
                    if (first == 'A') {
                        temp += 1;
                    } else if (first == 'B') {
                        temp += 2;
                    } else {
                        temp += 3;
                    }
                    break;
                case 'Z':
                    temp += 6;
                    if (first == 'A') {
                        temp += 2;
                    } else if (first == 'B') {
                        temp += 3;
                    } else {
                        temp += 1;
                    }
                    break;
                }
                gesammtPunkte += temp;
            }
        }
        return gesammtPunkte;
    }

    private static List<String> getStringList() {
        List<String> stringList = new ArrayList<String>();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                stringList.add(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stringList;
    }

    private static void readFile(File file) {
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Kopiert den Text in die Zwischenablage.
     * 
     * @param text Den zu kopierenden Text.
     */
    public static void CopyToClipboard(String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
    }
}

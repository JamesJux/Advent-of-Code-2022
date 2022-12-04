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
        // temp += day2("b");
        // temp += day3("a");
        // temp += day3("b");
        // temp += day4("a");
        temp += day4("b");

        CopyToClipboard(temp);
        System.out.println(temp);
    }

    private static int day1(String teilaufgabe) {
        file = new File("resources/day1.txt");
        readFile(file);
        List<String> stringList = getStringList();

        List<Integer> calorieList = new ArrayList<Integer>();
        int temp = 0;
        for (String string : stringList) {
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
        // List<String> stringList = List.of("A Y", "B X", "C Z");
        List<String> stringList = getStringList();
        int gesammtPunkte = 0;
        if (teilaufgabe.equals("a")) {
            for (String string : stringList) {
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
            for (String string : stringList) {
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

    private static int day3(String teilaufgabe) {
        file = new File("resources/day3.txt");
        readFile(file);
        List<String> stringList = getStringList();
        // List<String> stringList = List.of("vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        // "PmmdzqPrVvPwwTWBwg", "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT",
        // "CrZsJsPPZsGzwwsLwLmpwMDw");

        int gesammtPunkte = 0;
        if (teilaufgabe.equals("a")) {
            for (String string : stringList) {
                String stringA = string.substring(0, string.length() / 2);
                String stringB = string.substring(string.length() / 2, string.length());

                int idx = 0;
                while (stringB.indexOf(stringA.charAt(idx)) == -1) {
                    idx++;
                }
                int temp = (int) stringA.charAt(idx);
                if (Character.isLowerCase(temp)) {
                    gesammtPunkte += temp - 'a' + 1;
                } else {
                    gesammtPunkte += temp - 'A' + 27;
                }
            }
        } else {
            for (int i = 0; i < stringList.size(); i += 3) {
                String stringA = stringList.get(i);
                String stringB = stringList.get(i + 1);
                String stringC = stringList.get(i + 2);

                int idx = 0;
                while (stringB.indexOf(stringA.charAt(idx)) == -1 || stringC.indexOf(stringA.charAt(idx)) == -1) {
                    idx++;
                }
                int temp = (int) stringA.charAt(idx);
                if (Character.isLowerCase(temp)) {
                    gesammtPunkte += temp - 'a' + 1;
                } else {
                    gesammtPunkte += temp - 'A' + 27;
                }
            }
        }
        return gesammtPunkte;
    }

    private static int day4(String teilaufgabe) {
        file = new File("resources/day4.txt");
        readFile(file);
        List<String> stringList = getStringList();
        // List<String> stringList = List.of("2-4,6-8", "2-3,4-5", "5-7,7-9", "2-8,3-7", "6-6,4-6",
        // "2-6,4-8");

        int gesammtPunkte = 0;
        for (String string : stringList) {
            String[] stringArray = string.split(",");
            int elveAfirst = Integer.parseInt(stringArray[0].split("-")[0]);
            int elveAsecond = Integer.parseInt(stringArray[0].split("-")[1]);
            int elveBfirst = Integer.parseInt(stringArray[1].split("-")[0]);
            int elveBsecond = Integer.parseInt(stringArray[1].split("-")[1]);

            if (teilaufgabe.equals("a")) {
                if ((elveAfirst >= elveBfirst && elveAsecond <= elveBsecond)
                        || (elveBfirst >= elveAfirst && elveBsecond <= elveAsecond)) {
                    gesammtPunkte += 1;
                }
            } else {
                if (!(elveAsecond < elveBfirst) && !(elveBsecond < elveAfirst)) {
                    gesammtPunkte += 1;
                }
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

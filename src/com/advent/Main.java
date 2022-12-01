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
        temp += day1("b");

        CopyToClipboard(temp);
        System.out.println(temp);
    }

    private static int day1(String teilaufgabe) {
        file = new File("resources/day1.txt");
        readFile(file);

        String line = null;
        List<Integer> calorieList = new ArrayList<Integer>();
        int temp = 0;

        try {
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    calorieList.add(temp);
                    temp = 0;
                } else {
                    temp += Integer.parseInt(line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Verkehrt herum sortieren, damit die größten vorne sind.
        calorieList.sort((o1, o2) -> o2.compareTo(o1));

        if (teilaufgabe.equals("a")) {
            return calorieList.get(0);
        } else {
            return calorieList.get(0) + calorieList.get(1) + calorieList.get(2);
        }

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

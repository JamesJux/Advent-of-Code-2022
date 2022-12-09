package com.advent;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.geom.Dimension2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import com.advent.day7.Datei;
import com.advent.day7.Ordner;

public class Main {
    static File file;
    static BufferedReader reader = null;

    public static void main(String[] args) {
        String temp = "";
//        temp += day1("a");
//        temp += day1("b");
//        temp += day2("a");
//        temp += day2("b");
//        temp += day3("a");
//        temp += day3("b");
//        temp += day4("a");
//        temp += day4("b");
//        temp += day5("a");
//        temp += day5("b");
//        temp += day6("a");
//        temp += day6("b");
//        temp += day7("a");
//        temp += day7("b");
//        temp += day8("a");
        temp += day8("b");

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

        // Verkehrt herum sortieren, damit die Größten vorne sind.
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
                if ((elveAfirst >= elveBfirst && elveAsecond <= elveBsecond) || (elveBfirst >= elveAfirst && elveBsecond <= elveAsecond)) {
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

    private static String day5(String teilaufgabe) {
        file = new File("resources/day5.txt");
        readFile(file);

        List<String> stringList = getStringList();
        // List<String> stringList = List.of(" [D] ", "[N] [C] ", "[Z] [M] [P]", " 1 2 3 ", "",
        // "move 1 from 2 to 1", "move 3 from 1 to 3", "move 2 from 2 to 1", "move 1 from 1 to 2");

        // Suche Anzahl an Stacks
        int numberOffArrays = 0;
        for (String string : stringList) {
            if (string.contains(" 1 ")) {
                String[] stringArray = string.split(" ");
                numberOffArrays = Integer.parseInt(stringArray[stringArray.length - 1]);
                break;
            }
        }

        // Erstelle Stacks mit vorgegebenen Inhalt
        List<Stack<Character>> stacks = new ArrayList<Stack<Character>>();
        for (int i = 0; i < numberOffArrays; i++) {
            stacks.add(new Stack<>());
            for (int j = stringList.size() - 1; j >= 0; j--) {
                if (stringList.get(j).contains("[")) {
                    if (stringList.get(j).charAt(i * 4) == '[') {
                        stacks.get(i).push(stringList.get(j).charAt((i * 4) + 1));
                    }
                }
            }
        }

        // Verschiebt die Inhalte nach den Befehlen aus der Quelldatei
        String gesammtString = "";
        for (String string : stringList) {
            if (string.contains("from")) {
                String[] stringArray = string.split(" ");
                int count = Integer.parseInt(stringArray[1]);
                int from = Integer.parseInt(stringArray[3]) - 1;
                int to = Integer.parseInt(stringArray[5]) - 1;

                stacks = moveStackContent(teilaufgabe, stacks, count, from, to);
            }
        }
        for (Stack<Character> stack : stacks) {
            gesammtString += stack.peek();
        }
        return gesammtString;
    }

    private static int day6(String teilaufgabe) {
        file = new File("resources/day6.txt");
        readFile(file);
        List<String> stringList = getStringList();
        // List<String> stringList = List.of("bvwbjplbgvbhsrlpgdmjqwftvncz", "nppdvjthqldpwncqszvftbrmjlhg",
        // "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw");

        int gesammtPunkte = 0;
        for (String string : stringList) {
            if (teilaufgabe.equals("a")) {
                gesammtPunkte = getIndexOfMarker(string, 4);
            } else {
                gesammtPunkte = getIndexOfMarker(string, 14);
            }
        }
        return gesammtPunkte;
    }

    private static int day7(String teilaufgabe) {
        file = new File("resources/day7.txt");
        readFile(file);
        List<String> stringList = getStringList();
        // List<String> stringList = List.of("$ cd /", "$ ls", "dir a", "14848514 b.txt", "8504156 c.dat",
        // "dir d", "$ cd a", "$ ls", "dir e", "29116 f", "2557 g", "62596 h.lst", "$ cd e", "$ ls",
        // "584 i", "$ cd ..", "$ cd ..", "$ cd d", "$ ls", "4060174 j", "8033020 d.log",
        // "5626152 d.ext", "7214296 k");

        // Verarbeitet die Eingabe in eine interne Struktur.
        Ordner homeOrdner = new Ordner("/", null);
        Ordner aktuellerOrdner = homeOrdner;
        for (String string : stringList) {
            String[] stringArray = string.split(" ");
            switch (stringArray[0]) {
                case "$": {
                    switch (stringArray[1]) {
                        case "cd": {
                            if (stringArray[2].equals("..")) {
                                aktuellerOrdner = aktuellerOrdner.getParent();
                            } else if (stringArray[2].equals("/")) {
                                aktuellerOrdner = homeOrdner;
                            } else {
                                aktuellerOrdner = aktuellerOrdner.getOrdner(stringArray[2]);
                            }
                            break;
                        }
                        case "ls": {
                            break;
                        }
                        default:
                            throw new IllegalArgumentException("Unexpected value: " + stringArray[1]);
                    }
                    break;
                }
                case "dir": {
                    aktuellerOrdner.addToOrdnerListe(new Ordner(stringArray[1], aktuellerOrdner));
                    break;
                }
                default: {
                    try {
                        int groesse = Integer.parseInt(stringArray[0]);
                        aktuellerOrdner.addToDateiListe(new Datei(stringArray[1], groesse));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Unexpected value: " + stringArray[0]);
                    }
                }
            }
        }

        // Beantwortet die Fragestellungen, zur Hilfe wird eine Liste alle Ordner erzeugt und absteigend
        // sortiert.
        List<Ordner> ordnerListe = new ArrayList<Ordner>();
        homeOrdner.getSubordnerList(ordnerListe);
        ordnerListe.sort((o1, o2) -> o1.getGroesse() - o2.getGroesse());

        int gesammtGroesse = ordnerListe.get(ordnerListe.size() - 1).getGroesse();
        int antwortAGroesse = 0;
        for (Ordner ordner : ordnerListe) {
            if (teilaufgabe.equals("a")) {
                if (ordner.getGroesse() <= 100000) {
                    antwortAGroesse += ordner.getGroesse();
                }
            } else {
                if (ordner.getGroesse() >= -40000000 + gesammtGroesse) {
                    return ordner.getGroesse();
                }
            }
        }
        return antwortAGroesse;
    }

    private static String day8(String teilaufgabe) {
        file = new File("resources/day8.txt");
        readFile(file);
        List<String> stringList = getStringList();
//        List<String> stringList = List.of("30373", "25512", "65332", "33549", "35390");
        Dimension2D dim = new Dimension(stringList.size(), stringList.get(0).length());
        int[][] arrayTree = makeArrayOfStrings(stringList, dim);
        boolean[][] arrayBool = new boolean[(int) dim.getWidth()][(int) dim.getHeight()];
        int[][] arraySichtweiten = new int[(int) dim.getWidth()][(int) dim.getHeight()];

        int ergebnis = 0;
        for (String string : stringList) {
            if (teilaufgabe.equals("a")) {
                findHigestTrees(arrayTree, arrayBool, dim, 'X', 0, +1);
                findHigestTrees(arrayTree, arrayBool, dim, 'X', (int) dim.getWidth() - 1, -1);
                findHigestTrees(arrayTree, arrayBool, dim, 'Y', 0, +1);
                findHigestTrees(arrayTree, arrayBool, dim, 'Y', (int) dim.getHeight() - 1, -1);
                ergebnis = countVisible(arrayBool, dim);
            } else {
                arraySichtweiten = zähleSichtweite(arrayTree, arraySichtweiten, dim);
                ergebnis = sucheMaximaleSichtweite(arraySichtweiten,dim);
            }
        }
        return "" + ergebnis;
    }

    private static int sucheMaximaleSichtweite(int[][] arraySichtweiten, Dimension2D dim) {
        int max = 0;
        for (int i = 0; i < dim.getWidth(); i++) {
            for (int j = 0; j < dim.getHeight(); j++) {
                if (arraySichtweiten[i][j]>max) {
                    max =arraySichtweiten[i][j];
                }
            }
        }
        return max;
    }

    private static int[][] zähleSichtweite(int[][] arrayTree, int[][] arraySichtweiten, Dimension2D dim) {
        for (int i = 0; i < dim.getWidth(); i++) {
            for (int j = 0; j < dim.getHeight(); j++) {
                int west = getSichtweite(arrayTree, -1, i, j, 0, -1, 0);
                int sued = getSichtweite(arrayTree, -1, i, j, 1, 0, 0);
                int ost = getSichtweite(arrayTree, -1, i, j, 0, 1, 0);
                int nord = getSichtweite(arrayTree, -1, i, j, -1, 0, 0);
                arraySichtweiten[i][j] = nord * ost * sued * west;
            }
        }
        return arraySichtweiten;
    }

    private static int getSichtweite(int[][] arrayTree, int startBaumhöhe, int startX, int startY, int x, int y, int sichtweite) {
        if (startBaumhöhe == -1) {
            startBaumhöhe = arrayTree[startX][startY];
        }
        int baumhöhe;
        int nextX = startX + x;
        int nextY = startY + y;
        try {
            baumhöhe = arrayTree[nextX][nextY];
        } catch (ArrayIndexOutOfBoundsException e) {
            return sichtweite;
        }

        if (startBaumhöhe > baumhöhe) {
            return sichtweite + 1 + getSichtweite(arrayTree, startBaumhöhe, nextX, nextY, x, y, sichtweite);
        } else {
            return sichtweite + 1;
        }
    }

    private static int countVisible(boolean[][] arrayBool, Dimension2D dim) {
        int count = 0;
        for (int i = 0; i < dim.getWidth(); i++) {
            for (int j = 0; j < dim.getHeight(); j++) {
                if (arrayBool[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void findHigestTrees(int[][] arrayTree, boolean[][] arrayBool, Dimension2D dim, char achse, int start, int richtung) {
        if (achse == 'Y') {
            for (int idx = 0; idx < dim.getWidth(); idx++) {
                int maxHöhe = -1;
                for (int j = 0; j < dim.getHeight(); j++) {
                    if (arrayTree[start + (j * richtung)][idx] > maxHöhe) {
                        maxHöhe = arrayTree[start + (j * richtung)][idx];
                        arrayBool[start + (j * richtung)][idx] = true;
                    }
                }
            }
        } else if (achse == 'X') {
            for (int idx = 0; idx < dim.getHeight(); idx++) {
                int maxHöhe = -1;
                for (int j = 0; j < dim.getWidth(); j++) {
                    if (arrayTree[idx][start + (j * richtung)] > maxHöhe) {
                        maxHöhe = arrayTree[idx][start + (j * richtung)];
                        arrayBool[idx][start + (j * richtung)] = true;
                    }
                }
            }
        }
    }

    private static void printArray(int[][] array, Dimension2D dim) {
        for (int i = 0; i < dim.getWidth(); i++) {
            for (int j = 0; j < dim.getHeight(); j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printArray(boolean[][] array, Dimension2D dim) {
        for (int i = 0; i < dim.getWidth(); i++) {
            for (int j = 0; j < dim.getHeight(); j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }

    private static int[][] makeArrayOfStrings(List<String> stringList, Dimension2D dim) {
        int[][] array = new int[(int) dim.getWidth()][(int) dim.getHeight()];
        for (int i = 0; i < dim.getWidth(); i++) {
            for (int j = 0; j < dim.getHeight(); j++) {
                array[i][j] = Integer.parseInt("" + stringList.get(i).charAt(j));
            }
        }
        return array;
    }

    private static int getIndexOfMarker(String string, int anzahl) {
        Set<Character> charSet = new HashSet<>(string.length());
        // System.out.println(string);
        for (int i = anzahl - 1; i < string.length() - 1; i++) {
            charSet.removeAll(charSet);
            for (int j = 0; j < anzahl; j++) {
                charSet.add(string.charAt(i - j));

            }
            if (charSet.size() == anzahl) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Hilfsmethode für Aufgabe Tag 5 Verschiebt chars count oft vom from nach to
     *
     * @param teilaufgabe Die Angabe ob Aufgabenteil 'A' oder 'B' berechnet werden soll.
     * @param stacks      Die Liste aller Stacks
     * @param count       Anzahl der Verschiebungen
     * @param from        Quell Stack
     * @param to          Ziel Stack
     * @return die aktualisierten Stacks , Inplace Ersetzung.
     */
    private static List<Stack<Character>> moveStackContent(String teilaufgabe, List<Stack<Character>> stacks, int count, int from, int to) {
        if (teilaufgabe.equals("a")) {
            while (count != 0) {
                stacks.get(to).push(stacks.get(from).pop());
                count--;
            }
        } else {
            Stack<Character> temp = new Stack<Character>();
            int idx = count;
            while (idx != 0) {
                temp.push(stacks.get(from).pop());
                idx--;
            }
            while (idx != count) {
                stacks.get(to).push(temp.pop());
                idx++;
            }
        }
        return stacks;
    }

    private static List<String> getStringList() {
        return reader.lines().collect(Collectors.toList());
    }

    private static void readFile(File file) {
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
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

package ru.geekbrains;

import java.io.BufferedReader;
import java.io.IOException;

public class Parser {

    private BufferedReader input;

    public static String[] parseMessage(BufferedReader input) throws IOException {
        String firstLine = input.readLine();
        String[] parts = firstLine.split(" ");
        System.out.println(firstLine);
        return parts;
    }
}

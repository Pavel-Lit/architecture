package ru.geekbrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler implements Runnable {

    private final Socket socket;

    private final String folder;

    public RequestHandler(Socket socket, String folder) {
        this.socket = socket;
        this.folder = folder;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))
        ) {
            while (!input.ready());

            String[] parts = Parser.parseMessage(input);
            while (input.ready()) {
                System.out.println(input.readLine());
            }

            FileCheck.fileCheck(folder, parts[1], socket);

            System.out.println("Client disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

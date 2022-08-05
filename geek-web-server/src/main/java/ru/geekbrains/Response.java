package ru.geekbrains;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class Response {
    private String responseMessage;

    public void responseMessageOk(Socket socket, Path path) {
        try (PrintWriter output = new PrintWriter(socket.getOutputStream())) {
            output.println(StatusCode.OK);
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();

            Files.newBufferedReader(path).transferTo(output);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void responseMessageNotFound(Socket socket, Path path) {
        try (PrintWriter output = new PrintWriter(socket.getOutputStream())) {
            output.println("HTTP/1.1 404 NOT_FOUND");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<h1>Файл не найден!</h1>");
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package ru.geekbrains;

import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCheck {

    private static Path path;

    public static void fileCheck(String folder, String filename, Socket socket){
        path = Paths.get(folder, filename);
        if(!Files.exists(path)) {
            new Response().responseMessageNotFound(socket, path);
            return;
        }
        new Response().responseMessageOk(socket, path);
    }
}

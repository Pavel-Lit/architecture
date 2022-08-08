package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler implements Runnable {

    private final SocketService socketService;

    private final FileService fileService;

    public RequestHandler(SocketService socketService, FileService fileService) {
        this.socketService = socketService;
        this.fileService = fileService;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();

        HttpRequest httpRequest = new RequestParser().parse(rawRequest);
        HttpResponse httpResponse = new HttpResponse();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type:", "text/html; charset=utf-8\n");

        if (!fileService.exists(httpRequest.getPath())) {

            httpResponse.setStatusCode(String.valueOf(StatusCode.NOT_FOUND));
            httpResponse.setHeaders(headers);
            httpResponse.setBody("<h1>Файл не найден!</h1>");
            socketService.writeResponse(new ResponseSerializer().serialize(httpResponse));
            return;
        }
        httpResponse.setStatusCode(String.valueOf(StatusCode.OK));
        httpResponse.setHeaders(headers);
        httpResponse.setBody(fileService.readFile(httpRequest.getPath()));
        socketService.writeResponse(new ResponseSerializer().serialize(httpResponse));


        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        System.out.println("Client disconnected!");

    }
}

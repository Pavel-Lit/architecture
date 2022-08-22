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
    private final RequestParser requestParser;
    private final ResponseSerializer responseSerializer;

    public RequestHandler(SocketService socketService, FileService fileService, RequestParser requestParser, ResponseSerializer responseSerializer) {
        this.socketService = socketService;
        this.fileService = fileService;
        this.requestParser = requestParser;
        this.responseSerializer = responseSerializer;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();
        HttpRequest httpRequest = requestParser.parse(rawRequest);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/html; charset=utf-8\n");
        if (!fileService.exists(httpRequest.getUrl())) {


            socketService.writeResponse(responseSerializer
                    .serialize(HttpResponse.
                            createBuilder()
                            .withStatusCode(404)
                            .withStatusCodeName("NOT_FOUND")
                            .withHeaders(headers)
                            .withBody("<h1>Файл не найден!</h1>")
                            .build()));
            return;
        }

        socketService.writeResponse(responseSerializer.serialize(HttpResponse.createBuilder()
                .withStatusCode(200)
                .withStatusCodeName("OK")
                .withHeaders(headers)
                .withBody(fileService.readFile(httpRequest.getUrl()))
                .build()));


        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        System.out.println("Client disconnected!");

    }
}

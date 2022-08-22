package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.util.Deque;

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
        HttpResponse httpResponse = new HttpResponse();

        if (!fileService.exists(httpRequest.getUrl())) {

            httpResponse.setStatusCode(404);
            httpResponse.setStatusCodeName("NOT_FOUND");
            httpResponse.getHeaders().put("Content-Type", "text/html; charset=utf-8\n");
            httpResponse.setBody("<h1>Файл не найден!</h1>");
            socketService.writeResponse(responseSerializer.serialize(httpResponse));
            return;
        }
        httpResponse.setStatusCode(200);
        httpResponse.getHeaders().put("Content-Type", "text/html; charset=utf-8\n");
        httpResponse.setBody(fileService.readFile(httpRequest.getUrl()));
        socketService.writeResponse(new ResponseSerializer().serialize(httpResponse));


        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        System.out.println("Client disconnected!");

    }
}

package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.SocketService;

import java.util.function.Function;

public class StrategyMethodHandler extends MethodHandlerImpl{

    private final Function<HttpRequest, HttpResponse> strategy;

    public StrategyMethodHandler(String method, MethodHandlerImpl next, SocketService socketService, ResponseSerializer responseSerializer, ServerConfig config, Function<HttpRequest, HttpResponse> strategy) {
        super(method, next, socketService, responseSerializer, config);
        this.strategy = strategy;
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return strategy.apply(request);
    }
}

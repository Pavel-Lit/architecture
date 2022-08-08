package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;

import java.util.*;

public class RequestParser {

    public HttpRequest parse(Deque<String> rawResponse) {
        HttpRequest httpRequest = new HttpRequest();
        LinkedList<String> tempList = (LinkedList<String>) rawResponse;
        Map<String, String> tempMap = new HashMap<>();
        String[] tempArray = tempList.peekFirst().split(" ");
        httpRequest.setMethod(tempArray[0]);
        System.out.println(Arrays.toString(tempArray));
        httpRequest.setPath(tempArray[1]);
        tempList.removeFirst();
        /* не понял как обработать body у http запроса
        if (tempList.peekLast().startsWith("Body: ")) {
            httpRequest.setBody(tempList.peekLast());
            tempList.removeLast();
        }
        */
        for (ListIterator<String> iterator = tempList.listIterator(); iterator.hasNext(); ) {

            String tempString = iterator.next();
            tempArray = tempString.split(" ", 2);
            System.out.println(Arrays.toString(tempArray));
            if (tempArray.length > 1)
                tempMap.put(tempArray[0], tempArray[1]);
        }
        httpRequest.setHeaders(tempMap);


        return httpRequest;
    }
}

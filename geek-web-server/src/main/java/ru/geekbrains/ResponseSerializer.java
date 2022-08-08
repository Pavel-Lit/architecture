package ru.geekbrains;

import ru.geekbrains.domain.HttpResponse;

import java.util.Arrays;
import java.util.Map;

public class ResponseSerializer {

    public String serialize(HttpResponse response) {

        /* возникли сложности с hasMap, при получении значений из hasMap получается такой вывод
        {Content-Type:=text/html; charset=utf-8}
        и он ломает структуру ответа и страница не отображается.
        Данный метод отработает только с одним заголовком, а вслучае с неправильным именем файла
        запрос виснет
         */
        String key = null;
        String value = null;
        for (Map.Entry<String, String> entry: response.getHeaders().entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
        }
//        return response.getStatusCode()+"\n" + "Content-Type: text/html; charset=utf-8\n"+"\n" + response.getBody();
        return response.getStatusCode()+"\n" + key+value+"\n" + response.getBody();
    }


}

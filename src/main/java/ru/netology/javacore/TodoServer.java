package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class TodoServer {
    private final int SERVER_PORT;
    private final Todos taskManager;
    private final Gson gson;

    public TodoServer(int port, Todos todos) {
        this.SERVER_PORT = port;
        taskManager = todos;
        gson = new Gson();
    }

    public void start() {
        try (var serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Starting server at " + SERVER_PORT + "...");

            while (true) {
                try (
                        var socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                    // Принимаем запрос
                    var request = in.readLine();
                    // Конвертируем в промежуточный объект
                    var requestTask = gson.fromJson(request, RequestTask.class);
                    // разбираем запрос
                    switch (requestTask.type) {
                        case ADD: taskManager.addTask(requestTask.task);
                            break;
                        case REMOVE: taskManager.removeTask(requestTask.task);
                            break;
                        case RESTORE: taskManager.restoreTask();
                            break;
                        default: throw new IllegalStateException("Unexpected value: " + requestTask.type);
                    }
                    // Запрашиваем результат и отправляем его клиенту
                    var response = taskManager.getAllTasks();
                    out.println(response);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private class RequestTask {
        Operation type;
        String task;
    }
}

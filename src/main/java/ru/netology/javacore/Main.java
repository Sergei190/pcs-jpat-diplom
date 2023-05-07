package ru.netology.javacore;

public class Main {
    private static final int SERVER_PORT = 8989;

    public static void main(String[] args) {
        Todos todos = new Todos();
        TodoServer server = new TodoServer(SERVER_PORT, todos);
        server.start();
    }
}
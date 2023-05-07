package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;


public class SocketRunner {
    static String[] requests = {
            "{\"type\":\"ADD\",\"task\":\"Первая\"}",
            "{\"type\":\"ADD\",\"task\":\"Вторая\"}",
            "{\"type\":\"REMOVE\",\"task\":\"Первая\"}",
            "{\"type\":\"ADD\",\"task\":\"Третья\"}",
            "{\"type\":\"RESTORE\"}",
            "{\"type\":\"RESTORE\"}"
    };

    public static void main(String[] args) throws IOException {
        var inetAddress = Inet4Address.getByName("localhost");

        for (String request : requests) {
            try (var socket = new Socket(inetAddress, 8989);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                out.println(request);
                String response;
                while ((response = in.readLine()) != null) {
                    System.out.println(response);
                }
            }
        }
    }
}

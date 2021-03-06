package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerX2 {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServerX2.class.getName());

    public static void main(String[] args) {
        try {
            try (ServerSocket server = new ServerSocket(9000)) {
                while (!server.isClosed()) {

                    Socket socket = server.accept();
                    try (OutputStream out = socket.getOutputStream();
                         BufferedReader in = new BufferedReader(
                                 new InputStreamReader(socket.getInputStream()))) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                            System.out.println(str);
                            if (str.contains("msg=Hello ")) {
                                out.write("Hello".getBytes());
                                break;
                            } else if (str.contains("msg=Exit ")) {
                                server.close();
                                break;
                            } else {
                                String[] arr = str.split(" ");
                                String[] splits = arr[1].split("=");
                                out.write(splits[1].getBytes());
                                break;
                            }
                        }
                        out.flush();
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }

    }
}
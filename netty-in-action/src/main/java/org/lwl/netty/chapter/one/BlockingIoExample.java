package org.lwl.netty.chapter.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author thinking_fioa
 * @createTime 2018/5/14
 * @description BIO 服务端例子
 */


public class BlockingIoExample {

    public void serve(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String request, response;
        while((request = in.readLine()) != null) {
            if("Done".equals(request)) {
                break;
            }
            response = processRequest(request);
            out.println(response);
        }
    }

    private String processRequest(String request) {
        return "Processed";
    }
}

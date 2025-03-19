package com.mycompany.serverside;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerSide {
    ServerSocket serverSocket;

    public ServerSide() {
        try 
        {
            serverSocket = new ServerSocket(5005);
            while (true) {
                Socket s = serverSocket.accept();
                new ChatHandler(s);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        new ServerSide();
    }
}

class ChatHandler extends Thread {
    DataInputStream dis;
    PrintStream ps;
    static Vector<ChatHandler> clientsVector = new Vector<>();

    public ChatHandler(Socket cs) {
        try {
            dis = new DataInputStream(cs.getInputStream());
            ps = new PrintStream(cs.getOutputStream());
            clientsVector.add(this);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                String str = dis.readLine();
                if (str == null) break; // Handle client disconnecting
                sendMessageToAll(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clientsVector.remove(this);
            try {
                dis.close();
                ps.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void sendMessageToAll(String msg) {
        for (ChatHandler client : clientsVector) {
            client.ps.println(msg);
        }
    }
}
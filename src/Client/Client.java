package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class Client implements Serializable{
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    Client(String host,int port){
        try {
            socket = new Socket(host,port);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e){
            e.printStackTrace();
        }
        new Thread(() -> {
            while (true){
                try {
                    System.out.println(dis.readUTF());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void send(String s){
        try {
            dos.writeUTF(s);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}


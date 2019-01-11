package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client_Element {
    private int num=0;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    public Client_Element(Socket s){
        socket = s;
        try {
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        String msg = "";
                        while (!msg.equals("exit")) {
                            msg = dis.readUTF();
                            System.out.println("Клієнт "+num+" : "+msg);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                        break;
                    }

                }
            }
        });
    }

    public DataInputStream getDis() {
        return dis;
    }

    public DataOutputStream getOut() {
        return dos;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Client_Element(this.socket);
    }
}

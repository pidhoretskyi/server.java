import PCommand.Commands.CommandDown;
import PCommand.Commands.CommandUp;
import PCommand.ICommand;
import PCommand.Invoker;
import PCommand.Macros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements IServer{
    private ServerSocket serverSocket;
    private List<Socket> clients;
    Server() {
        clients = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(880);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            Attach(serverSocket.accept());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void start() {
        for (Socket client : clients) {
            try {
                DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                dos.writeUTF("Роботу запущено, для роботи використовуйте команди:\n" +
                        "k [число] - перевіряє чи число є простим;\n" +
                        "[номер клієнта] [повідомлення] - повідомлення іншому клієнту;\n" +
                        "m [повідомлення] - повідомлення усім плієнтам;\n" +
                        "s [повідомлення] - повідомлення серверу.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            for(int i=0;i<clients.size();i++){
                int finalI = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int myNum = finalI;
                        DataInputStream dis = null;
                        try {
                            dis = new DataInputStream(clients.get(myNum).getInputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        while (true){
                            try {

                                String s = null;
                                if (dis != null) {
                                    s = dis.readUTF();

                                    int pos = s.indexOf(" ");
                                    if (pos == 1) {
                                        String s1 = s.substring(0, pos);
                                        String s2 = s.substring(s.indexOf(" ") + 1);
                                        if (isNumeric(s1)) {
                                            DataOutputStream dos = new DataOutputStream(clients.get(Integer.parseInt(s1)).getOutputStream());
                                            dos.writeUTF("Клієнт " + Integer.toString(myNum) + " : " + s2);
                                        } else if (s1.equals("k")) {
                                            // Invoker invoker = new Invoker();
                                            //ICommand command;
                                            int n = Integer.parseInt(s2);
                                            boolean res = Command(n);
                                            if (res) {
                                                new DataOutputStream(clients.get(myNum).getOutputStream()).writeUTF("Відповідь сервера: Число є простим");
                                                System.out.print("Клієнт" + Integer.toString(myNum) + ": Число є простим\n");
                                            } else {
                                                new DataOutputStream(clients.get(myNum).getOutputStream()).writeUTF("Відповідь сервера: число є складеним");
                                                System.out.print("Клієнт" + Integer.toString(myNum) + ": Число є складеним\n");
                                            }
                                        /*
                                        if(s2.length()==1){
                                            System.out.print("Клієнт "+ Integer.toString(myNum)+" : ");
                                            command = getCommand(s2.charAt(0));
                                        } else {
                                            command = new Macros("Клієнт "+ Integer.toString(myNum)+" : ");
                                            for(char id:s2.toCharArray())((Macros)(command)).addCommand(getCommand(id));
                                        }
                                        invoker.setPreaml("Клієнт "+ Integer.toString(myNum)+" : ");
                                        invoker.setCommand(command);
                                        invoker.run();
                                        */
                                        } else if (s1.equals("s")) {
                                            System.out.println("Клієнт " + Integer.toString(myNum) + " : " + s2);
                                        } else if (s1.equals("m")) {
                                            Notify("Клієнт " + Integer.toString(myNum) + " : " + s2, myNum);
                                        }
                                    }
                                }

                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
    }
    private ICommand getCommand(char s){
        if(s=='1')return new CommandUp();
        if(s=='2')return new CommandDown();
        return null;
    }
    private boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private boolean Command(int n){
        BigInteger big = BigInteger.valueOf(n);
        return big.isProbablePrime((int) Math.log(n));
    }

    @Override
    public void Attach(Socket socket) {
        try {
            clients.add(socket);
            System.out.println("Клієнт приєднався.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void Notify(String s,int num) {
        for(int i=0;i<clients.size();i++){
            if(i!=num){
                try {
                    new DataOutputStream(clients.get(i).getOutputStream()).writeUTF(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

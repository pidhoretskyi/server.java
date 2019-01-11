package Client;

import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        Client client = new Client("127.0.0.1",880);
        while(true){
            client.send(new Scanner(System.in).nextLine());
        }
    }
}

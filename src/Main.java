import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Server server = new Server();
        System.out.println("Запустіть клієнтів і введіть start");
        while (!in.nextLine().equals("start")){}
        server.start();
        while (true){}
    }

}

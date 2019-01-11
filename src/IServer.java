import java.net.Socket;

public interface IServer {
    void Attach(Socket socket);
    void Notify(String s,int num);
}

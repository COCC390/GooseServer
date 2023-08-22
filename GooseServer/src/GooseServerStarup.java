import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GooseServerStarup {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream dataInputStream = null;

    public GooseServerStarup(int port)
    {
        try
        {
            server = new ServerSocket(port);
            System.out.println("Goose server ready");

            System.out.println("Wait for connect...");

            socket = server.accept();

            System.out.println("Socket has been connected");

            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            String data = "";

            while (!data.equals("Disconnect"))
            {
                try
                {
                    data = dataInputStream.readUTF();
                    System.out.println(data);
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                }
            }

            System.out.println("Socket disconnected");

            socket.close();
            dataInputStream.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    public static void main(String[] args)
    {
        GooseServerStarup serverStarup = new GooseServerStarup(5000);
    }
}

import java.net.*;
import java.io.*;

class EchoServer {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(1102);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());

        String clientMessage = "";
        while (!clientMessage.equals("end")) {
            clientMessage = din.readUTF();
            System.out.println("Client Message: " + clientMessage);
            dout.writeUTF(clientMessage);
            dout.flush();
        }
        
        din.close();
        s.close();
        ss.close();
    }
}
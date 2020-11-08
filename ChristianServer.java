import java.net.*;
import java.io.*;

class ChristianServer {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(1102);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());

        if (din.readUTF().equals("Send Time")) {
            System.out.println("Time Requested");
            dout.writeUTF(String.valueOf(System.currentTimeMillis()));
            dout.flush();
        }
        
        din.close();
        s.close();
        ss.close();
    }
}
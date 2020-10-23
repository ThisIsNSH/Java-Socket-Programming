import java.net.*;
import java.io.*;

class EchoClient {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 1102);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String message = "";
        while (!message.equals("end")) {
            message = br.readLine();
            dout.writeUTF(message);
            dout.flush();
            System.out.println("Server Echo: " + din.readUTF());
        }

        dout.close();
        s.close();
    }
}   
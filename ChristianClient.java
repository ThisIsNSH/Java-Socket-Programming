import java.net.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;

class ChristianClient {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 1102);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());

        long t, t0, t1, st;
        Date date;

        t0 = System.currentTimeMillis();
        t = t0;
        date = new Timestamp(t);
        System.out.println("Client Time T0: " + date);

        dout.writeUTF("Send Time");
        dout.flush();

        st = Long.parseLong(din.readUTF());
        t = st;
        date = new Timestamp(t);
        System.out.println("Server Time: " + date);

        t1 = System.currentTimeMillis();
        t = t1;
        date = new Timestamp(t);
        System.out.println("Client Time T1: " + date);

        t = st + (t1-t0)/2;
        date = new Timestamp(t);
        System.out.println("Client Sync Time: " + date);

        dout.close();
        s.close();
    }
}
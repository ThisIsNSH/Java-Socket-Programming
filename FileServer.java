import java.net.*;
import java.io.*;

public class FileServer extends Thread {
	
	private ServerSocket ss;
    
	public FileServer(int port) {
		try {
            ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			try {
				Socket clientSock = ss.accept();

				DataInputStream din = new DataInputStream(clientSock.getInputStream());
				DataOutputStream dout = new DataOutputStream(clientSock.getOutputStream());
		
				String file = "", size = "";
				file = din.readUTF();
				File f = new File(file);
				size = String.valueOf(f.length());
				dout.writeUTF(size);
				dout.flush();

				sendFile(clientSock , file);

				din.close();
				clientSock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

    public void sendFile(Socket s, String file) throws IOException {
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[4096];
		
		while (fis.read(buffer) > 0) {
			dos.write(buffer);
		}
		
		fis.close();
		dos.close();	
	}
	
	public static void main(String[] args) {
		FileServer fs = new FileServer(1102);
        fs.start();
	}

}

import java.net.*;
import java.io.*;

public class FileClient {

	private static Socket s;
	private static int filesize = 0;

	public FileClient(String host, int port) {
		try {
			s = new Socket(host, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void saveFile(Socket clientSock, String file) throws IOException {
		DataInputStream dis = new DataInputStream(clientSock.getInputStream());
		FileOutputStream fos = new FileOutputStream("received-" + file);
		byte[] buffer = new byte[4096];

		int read = 0;
		int totalRead = 0;
		int remaining = filesize;
		while ((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
			totalRead += read;
			remaining -= read;
			System.out.println("read " + totalRead + " bytes.");
			fos.write(buffer, 0, read);
		}

		fos.close();
		dis.close();
	}

	public static void main(String[] args) throws Exception {
		FileClient fc = new FileClient("localhost", 1102);

		DataInputStream din = new DataInputStream(s.getInputStream());
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String file = "", size = "";
		file = br.readLine();
		dout.writeUTF(file);
		dout.flush();
		size = din.readUTF();

		System.out.println("File Size: " + size);
		filesize = Integer.parseInt(size);

		saveFile(s, file);

		dout.close();
		s.close();
	}

}

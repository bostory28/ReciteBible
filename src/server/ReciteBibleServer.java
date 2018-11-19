package server;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ReciteBibleServer extends Thread {
	private ServerSocket serverSocket;
	private boolean isAccept = true;

	public ReciteBibleServer(int port) throws Exception {
		this.serverSocket = new ServerSocket(port);
	}

	@Override
	public void run() {
		System.out.println("********System is waiting for client...********");
		while (isAccept) {
			Socket socket = null;
			MultiServerThread mst = null;
			ObjectInputStream in = null;
			try {
				serverSocket.setSoTimeout(60*60*60);
				socket = serverSocket.accept();
				
				in = new ObjectInputStream(socket.getInputStream());
				mst = new MultiServerThread(socket, in);
				mst.start();
			} catch (Exception e) {} 
		}
		System.out.println("Thread finished");
	}

	public void socketClose() {
		try {
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void escapeWhile() {
		isAccept = false;
	}
}

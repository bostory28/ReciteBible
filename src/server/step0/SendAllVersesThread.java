package server.step0;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import server.ArraylistSerialized;
import server.step1.UpdateDateAction;
import vo.UpdateVO;
import vo.VersesVO;

public class SendAllVersesThread extends Thread {
	private Socket socket;
	private SendAllVersesAction sendAllVersesAction;
	private ObjectInputStream in;
	
	public SendAllVersesThread(Socket socket, ObjectInputStream in) {
		this.socket = socket;
		sendAllVersesAction = new SendAllVersesAction();
		this.in = in;
	}

	@Override
	public void run() {
		try {
			ArrayList<Object> listOutputServer = new ArrayList<>();
			ArrayList<VersesVO> listVerses = sendAllVersesAction.getAllVerses();
			UpdateVO updateVO = sendAllVersesAction.getLastestUpdate();
			listOutputServer.add(listVerses);
			listOutputServer.add(updateVO);
			
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(listOutputServer);
			out.flush();
			
			socket.close();
			out.close();
			in.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

}

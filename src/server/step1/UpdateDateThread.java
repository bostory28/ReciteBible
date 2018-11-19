package server.step1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import vo.UpdateVO;

public class UpdateDateThread extends Thread{
	private Socket socket;
	private ArrayList<Object> listInputServer;
	private UpdateDateAction updateDateAction;
	private ObjectInputStream in;

	public UpdateDateThread(Socket socket, ArrayList<Object> listInputServer, ObjectInputStream in) {
		this.socket = socket;
		this.in = in;
		this.listInputServer = listInputServer;
		updateDateAction = new UpdateDateAction();
	}

	@Override
	public void run() {
		try {
			ArrayList<Object> listOutputServer = new ArrayList<>();
			ObjectOutputStream out = null;
			boolean isSameUpdateDate = false;
			
			isSameUpdateDate = updateDateAction.processSameUpdateDate(listInputServer.get(1));
			if (isSameUpdateDate) {
				listOutputServer.add(true);
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(listOutputServer);
				out.flush();
			} else {
				ArrayList<UpdateVO> listUpdate = updateDateAction.getUpdateHistory(listInputServer.get(1));
				listOutputServer.add(false);
				listOutputServer.add(listUpdate);
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(listOutputServer);
				out.flush();
			}
			socket.close();
			out.close();
			in.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
}

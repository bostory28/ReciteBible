package server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.NetworkInterface;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.ListFileFolder;
import server.step0.SendAllVersesAction;
import server.step1.UpdateDateAction;
import vo.TokensVO;
import vo.UpdateVO;
import vo.VersesVO;

public class MultiServerThread extends Thread {
	private Socket socket;
	private String appfoldername = "C:\\apache-tomcat-9.0.1\\webapps\\ReciteBible\\app";
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public MultiServerThread(Socket socket, ObjectInputStream in) {
		this.socket = socket;
		this.in = in;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("********Just connected to " + socket.getRemoteSocketAddress() + "  Time : "+ new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss").format(new Date()) +"********");
			ArrayList<Object> listInputServer = (ArrayList<Object>) in.readObject();
			
			ArrayList<Object> listOutputServer = new ArrayList<>();
			out = new ObjectOutputStream(socket.getOutputStream());
			UpdateDateAction updateDateAction = null;
			switch (Integer.valueOf(String.valueOf(listInputServer.get(0)))) {
				case 0:// case in NO DATA
					System.out.println("********STEP0 : SEND ALL DATA ********");
					SendAllVersesAction sendAllVersesAction = new SendAllVersesAction();
					
					ArrayList<VersesVO> listVerses = sendAllVersesAction.getAllVerses();
					UpdateVO updateVO = sendAllVersesAction.getLastestUpdate();
					listOutputServer.add(listVerses);
					listOutputServer.add(updateVO);
					
					out.writeObject(listOutputServer);
					out.flush();
					break;
				case 1:// case in Update process
					System.out.println("********STEP1 : SEND UPDATE HISTORY ********");
					boolean isSameUpdateDate = false;
					updateDateAction = new UpdateDateAction();
					
					isSameUpdateDate = updateDateAction.processSameUpdateDate(listInputServer.get(1));
					if (isSameUpdateDate) {
						System.out.println("********STEP1-1 : ALREADY DOWNLOADED ********");
						listOutputServer.add(true);
						out.writeObject(listOutputServer);
						out.flush();
					} else {
						System.out.println("********STEP1-2 : DOWNLOAD NEW VERSES ********");
						ArrayList<UpdateVO> listUpdate = updateDateAction.getUpdateHistory(listInputServer.get(1));
						listOutputServer.add(false);
						listOutputServer.add(listUpdate);
						out.writeObject(listOutputServer);
						out.flush();
					}
					break;
				case 2:
					System.out.println("********STEP2 : SEND LATEST DATE UPDATED AND NEW VERSION ********");
					//LATEST DATE UPDATED
					updateDateAction = new UpdateDateAction();
					String latestDateFromClient = String.valueOf(listInputServer.get(1));
					String latestDateFromServer = updateDateAction.getLastestUpdateDate();
					ArrayList<UpdateVO> listVersesUpdated = updateDateAction.getVersesNotUpdated(latestDateFromClient);
					
					listOutputServer.add(latestDateFromServer);
					listOutputServer.add(listVersesUpdated);
					
					//NEW VERSION
					String versionFromClient = String.valueOf(listInputServer.get(2));
					String versionFromServer = "";
					File folder = new File(appfoldername);
					ListFileFolder listFileFolder = new ListFileFolder();
					List<String> apkList = listFileFolder.listFilesForFolder(folder);
					if (!apkList.isEmpty()) {
						versionFromServer = apkList.get(0).substring(apkList.get(0).indexOf("_v")+2, apkList.get(0).indexOf(".apk"));	
						if (versionFromClient.equals(versionFromServer)) {
							listOutputServer.add("");
						} else {
							listOutputServer.add("http://visionchurchrecite.ddns.net:8888/ReciteBible/app/AVBCRecitingApp_v"+versionFromServer+".apk");
						}
					} else {
						listOutputServer.add("");
					}
					
					out.writeObject(listOutputServer);
					out.flush();
					break;
			}
			System.out.println("********System is waiting for client...********");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			} 
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}

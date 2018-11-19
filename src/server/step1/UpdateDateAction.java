package server.step1;

import java.util.ArrayList;

import vo.TokensVO;
import vo.UpdateVO;
import vo.VersesVO;

public class UpdateDateAction {
	private UpdateDateDAO updateDateDAO;
	public UpdateDateAction() {
		updateDateDAO = new UpdateDateDAO();
	}
	
	public boolean processSameUpdateDate(Object updateDateFromClientObject) {
		boolean isSame = false;
		String updateDateFromClient = null;
		if (updateDateFromClientObject instanceof String) {
			updateDateFromClient = String.valueOf(updateDateFromClientObject);
		} else {
			return false;
		}
		
		String latestUpdateDateFromServer = updateDateDAO.getLastestUpdateDate();
		if (updateDateFromClient.equals(latestUpdateDateFromServer)) {
			isSame = true;
		} else {
			isSame = false;
		}
		return isSame;
	}
	
	public ArrayList<UpdateVO> getUpdateHistory(Object updateDateFromClientObject) {
		String updateDateFromClient = null;
		if (updateDateFromClientObject instanceof String) {
			updateDateFromClient = String.valueOf(updateDateFromClientObject);
		} else {
			return null;
		}
		
		ArrayList<UpdateVO> listUpdate = (ArrayList<UpdateVO>) updateDateDAO.getUpdateHistory(updateDateFromClient);
		return listUpdate;
	}
	
	public ArrayList<VersesVO> getVersesUpdated(ArrayList<UpdateVO> listUpdate) {
		ArrayList<VersesVO> listVersesUpdated = new ArrayList<>();
		for(int i = 0; i <listUpdate.size(); i++) {
			VersesVO versesVO = updateDateDAO.getVersesUpdated(listUpdate.get(i).getVerses_sq());
			listVersesUpdated.add(versesVO);
		}
		return listVersesUpdated;
	}
	
	public String getLastestUpdateDate() {
		String lastDate = updateDateDAO.getLastestUpdateDate();
		return lastDate;
	}
	
	public ArrayList<UpdateVO> getVersesNotUpdated(String latestDateFromClient) {
		ArrayList<UpdateVO> listVersesUpdated = updateDateDAO.getVersesNotUpdated(latestDateFromClient);
		return listVersesUpdated;
	}
}

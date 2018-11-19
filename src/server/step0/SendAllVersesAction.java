package server.step0;

import java.util.ArrayList;

import vo.UpdateVO;
import vo.VersesVO;

public class SendAllVersesAction {
	private SendAllVersesDAO sendAllVersesDAO;
	
	public SendAllVersesAction() {
		sendAllVersesDAO = new SendAllVersesDAO();
	}
	public ArrayList<VersesVO> getAllVerses() {
		ArrayList<VersesVO> listAllVerses = null;
		listAllVerses = (ArrayList<VersesVO>)sendAllVersesDAO.getAllVerses();
		return listAllVerses;
	}
	
	public UpdateVO getLastestUpdate() {
		UpdateVO updateVO = sendAllVersesDAO.getLastestUpdate();
		return updateVO;
	}
}

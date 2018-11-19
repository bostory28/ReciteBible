package update;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.PagingProcess;
import vo.MonthsVO;
import vo.UpdateVO;
import vo.UpdateVO2;
import vo.VersesVO;
import vo.YearsVO;

public class UpdateAction extends ActionSupport {
	private String userId;
	private ArrayList<UpdateVO> listUpdate;
	private ArrayList<UpdateVO2> listUpdate2;
	private PagingProcess pagingProcess;
	private int page = 1;
	
	public UpdateAction() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = (Map<String, Object>) context.getSession();
		userId = (String) session.get("userId");
	}

	public String getUpdates() throws Exception {
		UpdateDAO updateDAO = new UpdateDAO();
		pagingProcess = new PagingProcess();
		int allRowCount = updateDAO.getAllRowCount();
		pagingProcess.process(allRowCount, page);
		listUpdate2 = (ArrayList<UpdateVO2>)updateDAO.getUpdates(pagingProcess); 
		return SUCCESS;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<UpdateVO> getListUpdate() {
		return listUpdate;
	}

	public void setListUpdate(ArrayList<UpdateVO> listUpdate) {
		this.listUpdate = listUpdate;
	}

	public PagingProcess getPagingProcess() {
		return pagingProcess;
	}

	public void setPagingProcess(PagingProcess pagingProcess) {
		this.pagingProcess = pagingProcess;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public ArrayList<UpdateVO2> getListUpdate2() {
		return listUpdate2;
	}

	public void setListUpdate2(ArrayList<UpdateVO2> listUpdate2) {
		this.listUpdate2 = listUpdate2;
	}
	
}

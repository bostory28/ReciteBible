package index;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import backup.BackupAction;
import common.PagingProcess;
import common.SendPushData;
import server.ReciteBibleServer;
import vo.MonthsVO;
import vo.UpdateVO;
import vo.VersesVO;
import vo.YearsVO;

public class IndexAction extends ActionSupport {
	private String userId;
	private ArrayList<VersesVO> listVerses;
	private ArrayList<MonthsVO> listMonths;
	private ArrayList<YearsVO> listYears;
	private VersesVO versesVo;
	
	private final int port = 5001;
	private static ReciteBibleServer thread;
	private boolean statusServer = false;
	
	private PagingProcess pagingProcess;
	private int page = 1;
	
	public IndexAction() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = (Map<String, Object>) context.getSession();
		userId = (String) session.get("userId");
	}

	public String index() throws Exception {
		getVerses();
		if (thread != null) {
			statusServer = true;
		} else {
			statusServer = false;			
		}
		return SUCCESS;
	}
	
	public String backup() throws Exception {
		BackupAction backupProcess = new BackupAction();
		backupProcess.createBackupDirectory();
		backupProcess.createTodayDirectory();
		backupProcess.backupDB();
		
		getVerses();
		return SUCCESS;
	}
	public String startServer() throws Exception {
		if (thread == null) {
			thread = new ReciteBibleServer(port);
			thread.start();
		} 
		statusServer = true;
		getVerses();
		return SUCCESS;
	}
	public String stopServer() throws Exception {
		if (thread != null) {
			thread.escapeWhile();
			thread.socketClose();
			thread.interrupt();
			thread = null;
		}
		statusServer = false;
		getVerses();
		return SUCCESS;
	}
	
	public String goAddVerseForm() throws Exception {
		IndexDAO indexDao = new IndexDAO();
		listYears = (ArrayList<YearsVO>) indexDao.getYears();
		listMonths = (ArrayList<MonthsVO>) indexDao.getMonths();
		return SUCCESS;
	}
	public String addVerse() throws Exception {
		IndexDAO indexDao = new IndexDAO();
		indexDao.addVerse(versesVo);
		getVerses();
		return SUCCESS;
	}
	public String goUpdateVerseForm() throws Exception {
		IndexDAO indexDao = new IndexDAO();
		versesVo = indexDao.getVerse(versesVo.getVerses_sq());
		listYears = (ArrayList<YearsVO>) indexDao.getYears();
		listMonths = (ArrayList<MonthsVO>) indexDao.getMonths();
		return SUCCESS;
	}
	public String updateVerse() throws Exception {
		IndexDAO indexDao = new IndexDAO();
		indexDao.updateVerse(versesVo);
		getVerses();
		return SUCCESS;
	}
	public String deleteVerse() throws Exception {
		IndexDAO indexDao = new IndexDAO();
		indexDao.deleteVerse(versesVo);
		getVerses();
		return SUCCESS;
	}
	public String push() {
		IndexDAO indexDAO = new IndexDAO();
    	ArrayList<UpdateVO> updateList = (ArrayList<UpdateVO>)indexDAO.getAllVersesNotPushed();
    	if (updateList.size() != 0) {
    		SendPushData pushData = new SendPushData();
    		pushData.sendNotification(updateList);
    		indexDAO.updatePush(updateList);
		}
		getVerses();
		return SUCCESS; 
	}
	
	public void getSessionId() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = (Map<String, Object>) context.getSession();
		userId = (String) session.get("userId");
	}
	
	public void getVerses() {
		IndexDAO indexDao = new IndexDAO();
		int allRowCount = indexDao.getAllRowCount();
		pagingProcess = new PagingProcess();
		pagingProcess.process(allRowCount, page);
		listVerses = (ArrayList<VersesVO>) indexDao.getVerses(pagingProcess);
		if (thread != null) {
			statusServer = true;
		} else {
			statusServer = false;			
		}
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<VersesVO> getListVerses() {
		return listVerses;
	}

	public void setListVerses(ArrayList<VersesVO> listVerses) {
		this.listVerses = listVerses;
	}

	public ArrayList<MonthsVO> getListMonths() {
		return listMonths;
	}

	public void setListMonths(ArrayList<MonthsVO> listMonths) {
		this.listMonths = listMonths;
	}

	public ArrayList<YearsVO> getListYears() {
		return listYears;
	}

	public void setListYears(ArrayList<YearsVO> listYears) {
		this.listYears = listYears;
	}

	public VersesVO getVersesVo() {
		return versesVo;
	}

	public void setVersesVo(VersesVO versesVo) {
		this.versesVo = versesVo;
	}

	public boolean isStatusServer() {
		return statusServer;
	}

	public void setStatusServer(boolean statusServer) {
		this.statusServer = statusServer;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PagingProcess getPagingProcess() {
		return pagingProcess;
	}

	public void setPagingProcess(PagingProcess pagingProcess) {
		this.pagingProcess = pagingProcess;
	}
	
	
	
}

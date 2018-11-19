package admin;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import index.IndexAction;
import vo.AdminsVO;
import vo.YearsVO;
import year.YearsDAO;

public class AdminsAction extends ActionSupport {
	private String userId;
	private AdminsVO adminsVo;
	private ArrayList<AdminsVO> listAdmins = new ArrayList<>();
	
	public AdminsAction() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = (Map<String, Object>) context.getSession();
		userId = (String) session.get("userId");
	}

	@Override
	public String execute() throws Exception {
		getAdmins();
		return SUCCESS;
	}

	public String addAdmin() throws Exception {
		AdminsDAO adminsDao = new AdminsDAO();
		adminsDao.addAdmin(adminsVo);
		getAdmins();
		return SUCCESS;
	}
	public String deleteAdmin() throws Exception {
		AdminsDAO adminsDao = new AdminsDAO();
		System.out.println(adminsVo.getAdmins_sq());
		adminsDao.deleteAdmin(adminsVo.getAdmins_sq());
		getAdmins();
		return SUCCESS;
	}
	
	private void getAdmins() {
		AdminsDAO adminsDao = new AdminsDAO();
		listAdmins = (ArrayList<AdminsVO>) (adminsDao.getAdmins());
	}
	
	public AdminsVO getAdminsVo() {
		return adminsVo;
	}

	public void setAdminsVo(AdminsVO adminsVo) {
		this.adminsVo = adminsVo;
	}

	public ArrayList<AdminsVO> getListAdmins() {
		return listAdmins;
	}

	public void setListAdmins(ArrayList<AdminsVO> listAdmins) {
		this.listAdmins = listAdmins;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}

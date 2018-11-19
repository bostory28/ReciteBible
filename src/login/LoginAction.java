package login;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import server.ReciteBibleServer;
import vo.AdminsVO;

public class LoginAction extends ActionSupport implements SessionAware{
	private AdminsVO adminsVo;
	private Map<String, Object> session;
	
	public String login() throws Exception {
		String result = "";
		LoginDAO loginDao = new LoginDAO();
		boolean isUser = loginDao.getUser(adminsVo);
		if (isUser) {
			session.put("userId", adminsVo.getId());
			result = "success";
			
		} else {
			result = "input";
		}
		return result;
	}
	
	public String logout() throws Exception {
		session.remove("userId");
		return "success";
	}
	public AdminsVO getAdminsVo() {
		return adminsVo;
	}
	public void setAdminsVo(AdminsVO adminsVo) {
		this.adminsVo = adminsVo;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;		
	}
	
}

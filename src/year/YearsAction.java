package year;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import vo.YearsVO;

public class YearsAction extends ActionSupport {
	private String userId;
	private ArrayList<YearsVO> listYears = new ArrayList<>();
	private int newYr;
	private YearsVO yearVo;
	
	public YearsAction() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = (Map<String, Object>) context.getSession();
		userId = (String) session.get("userId");
	}

	@Override
	public String execute() throws Exception {
		getYears();
		return SUCCESS;
	}
	
	public String addYear() throws Exception {
		YearsDAO yearsDAO = new YearsDAO();
		yearsDAO.addYear(newYr);
		getYears();
		return SUCCESS;
	}
	public String deleteYear() throws Exception {
		YearsDAO yearsDAO = new YearsDAO();
		yearsDAO.deleteYear(yearVo.getYr());
		getYears();
		return SUCCESS;
	}
	
	
	
	
	private void getYears() {
		YearsDAO yearsDAO = new YearsDAO();
		listYears = (ArrayList<YearsVO>) yearsDAO.getYears();
	}
	
	//getter and setter
	public ArrayList<YearsVO> getListYears() {
		return listYears;
	}

	public void setListYears(ArrayList<YearsVO> listYears) {
		this.listYears = listYears;
	}

	public int getNewYr() {
		return newYr;
	}

	public void setNewYr(int newYr) {
		this.newYr = newYr;
	}

	public YearsVO getYearVo() {
		return yearVo;
	}

	public void setYearVo(YearsVO yearVo) {
		this.yearVo = yearVo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	

}
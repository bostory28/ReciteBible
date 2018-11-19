package login;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import common.MybatisConfig;
import vo.AdminsVO;

public class LoginDAO {
	private SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	private SqlSession sqlSession;
	
	public boolean getUser(AdminsVO adminsVo) {
		boolean isUser = false;
		AdminsVO adminsVo_tmp = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			adminsVo_tmp = sqlSession.selectOne("login.getUser", adminsVo);
			if (adminsVo_tmp != null) {
				isUser = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return isUser;
	}
	
	

}

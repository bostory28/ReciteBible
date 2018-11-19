package admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import common.MybatisConfig;
import vo.AdminsVO;
import vo.YearsVO;

public class AdminsDAO {
	private SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	private SqlSession sqlSession;
	
	public List<AdminsVO> getAdmins() {
		List<AdminsVO> list = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList("admins.getAdmins");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return list;
	}
	
	public void addAdmin(AdminsVO adminsVo) {
		try {
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("admins.addAdmin", adminsVo);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
	}
	public void deleteAdmin(int admins_sq) {
		try {
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.delete("admins.deleteAdmin", admins_sq);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
	}
	
	

}

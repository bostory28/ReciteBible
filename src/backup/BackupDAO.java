package backup;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import common.MybatisConfig;
import common.PagingProcess;
import vo.AdminsVO;
import vo.UpdateVO;
import vo.UpdateVO2;
import vo.VersesVO;

public class BackupDAO {
	private SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	private SqlSession sqlSession;
	
	public List<AdminsVO> getAdmins() {
		List<AdminsVO> list = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList("backup.getAdmins");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return list;
	}	
	public List<VersesVO> getVerses() {
		List<VersesVO> list = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList("backup.getVerses");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return list;
	}	
	public List<UpdateVO2> getUpdates() {
		List<UpdateVO2> list = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList("backup.getUpdates");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return list;
	}	
	public List<Integer> getYears() {
		List<Integer> list = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList("backup.getYears");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return list;
	}	
	public List<Integer> getMonths() {
		List<Integer> list = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList("backup.getMonths");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return list;
	}
	
	
}

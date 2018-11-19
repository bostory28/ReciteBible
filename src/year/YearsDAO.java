package year;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import common.MybatisConfig;
import vo.YearsVO;

public class YearsDAO {
	private SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	private SqlSession sqlSession;
	
	public List<YearsVO> getYears() {
		List<YearsVO> list = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList("years.getYears");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return list;
	}
	
	public void addYear(int newYr) {
		try {
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("years.addYear", newYr);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		
	}
	public void deleteYear(int yr) {
		try {
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.delete("years.deleteYear", yr);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		
	}

}

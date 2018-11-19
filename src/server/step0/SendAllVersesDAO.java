package server.step0;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import common.MybatisConfig;
import vo.UpdateVO;
import vo.VersesVO;

public class SendAllVersesDAO {
	private SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	private SqlSession sqlSession;
	
	public List<VersesVO> getAllVerses() {
		List<VersesVO> listAllVerses = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			listAllVerses = sqlSession.selectList("server.getAllVerses");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return listAllVerses;
	}
	
	public UpdateVO getLastestUpdate() {
		UpdateVO updateVO = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			updateVO = sqlSession.selectOne("server.getLastestUpdate");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return updateVO;
	}
}

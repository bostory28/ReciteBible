package server.step1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import common.MybatisConfig;
import vo.TokensVO;
import vo.UpdateVO;
import vo.VersesVO;

public class UpdateDateDAO {
	private SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	private SqlSession sqlSession;
	
	public String getLastestUpdateDate() {
		String latestUpdateDateFromServer = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			latestUpdateDateFromServer = sqlSession.selectOne("server.getLastestUpdateDate");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return latestUpdateDateFromServer;
	}
	
	public List<UpdateVO> getUpdateHistory(String updateDateFromClient) {
		List<UpdateVO> listUpdate = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			listUpdate = sqlSession.selectList("server.getUpdateHistory", updateDateFromClient);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return listUpdate;
	}
	
	public VersesVO getVersesUpdated(int verses_sq) {
		VersesVO versesVO = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			versesVO = sqlSession.selectOne("server.getVersesUpdated", verses_sq);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return versesVO;
	}

	public ArrayList<UpdateVO> getVersesNotUpdated(String latestDateFromClient) {
		List<UpdateVO> listVerses = null;
		
		try {
			sqlSession = sqlSessionFactory.openSession();
			listVerses = sqlSession.selectList("server.getVersesNotUpdated", latestDateFromClient);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return (ArrayList<UpdateVO>) listVerses;
	}
}

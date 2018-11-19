package index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import common.MybatisConfig;
import common.PagingProcess;
import vo.MonthsVO;
import vo.UpdateVO;
import vo.VersesVO;
import vo.YearsVO;

public class IndexDAO {
	private SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	private SqlSession sqlSession;
	
	public List<VersesVO> getVerses(PagingProcess pagingProcess) {
		List<VersesVO> list = null;
		HashMap<String, Integer> map = new HashMap<>();
		map.put("start", pagingProcess.getFirstRow());
		map.put("end", pagingProcess.getLastRow());
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList("verses.getVerses", map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return list;
	}	
	
	public int getAllRowCount() {
		int allRowCount = 0;
		try {
			sqlSession = sqlSessionFactory.openSession();
			allRowCount = sqlSession.selectOne("verses.getAllRowCount");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return allRowCount;
	}
	public VersesVO getVerse(int verses_sq) {
		VersesVO versesVo = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			versesVo = sqlSession.selectOne("verses.getVerse", verses_sq);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return versesVo;
	}	
	
	public List<YearsVO> getYears() {
		List<YearsVO> list = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList("verses.getYears");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return list;
	}
	public List<MonthsVO> getMonths() {
		List<MonthsVO> list = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList("verses.getMonths");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return list;
	}
	public boolean addVerse(VersesVO versesVo) {
		boolean isAdd = false;
		try {
			sqlSession = sqlSessionFactory.openSession();
			int count = sqlSession.insert("verses.addVerse", versesVo);
			if (count == 1) {
				isAdd = true;
			}
			int verses_sq = sqlSession.selectOne("verses.getLastVerseSq");
			versesVo.setVerses_sq(verses_sq);
			sqlSession.insert("verses.updateDateOfAddVerse", versesVo);//update_type : 1
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return isAdd;
	}
	public boolean updateVerse(VersesVO versesVo) {
		boolean isUpdate = false;
		try {
			sqlSession = sqlSessionFactory.openSession();
			int count = sqlSession.update("verses.updateVerse", versesVo);
			if (count == 1) {
				isUpdate = true;
			}
			sqlSession.insert("verses.updateDateOfUpdateVerse", versesVo);//update_type : 2
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return isUpdate;
	}
	public boolean deleteVerse(VersesVO versesVO) {
		boolean isDelete = false;
		try {
			sqlSession = sqlSessionFactory.openSession();
			int count = sqlSession.delete("verses.deleteVerse", versesVO.getVerses_sq());
			if (count == 1) {
				isDelete = true;
			}
			sqlSession.insert("verses.updateDateOfDeleteVerse", versesVO);//update_type : 3
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return isDelete;
	}
	public List<UpdateVO> getAllVersesNotPushed() {
		List<UpdateVO> list = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList("verses.getAllVersesNotPushed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return list;
	}
	public void updatePush(ArrayList<UpdateVO> updateList) {
		try {
			sqlSession = sqlSessionFactory.openSession();
			for (int i = 0; i < updateList.size(); i++) {
				sqlSession.update("verses.updatePush", updateList.get(i));
			}
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
	}
}

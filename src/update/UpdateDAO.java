package update;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import common.MybatisConfig;
import common.PagingProcess;
import vo.MonthsVO;
import vo.UpdateVO;
import vo.UpdateVO2;
import vo.VersesVO;
import vo.YearsVO;

public class UpdateDAO {
	private SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	private SqlSession sqlSession;
	
	public List<UpdateVO2> getUpdates(PagingProcess pagingProcess) {
		List<UpdateVO2> list = null;
		HashMap<String, Integer> map = new HashMap<>();
		map.put("start", pagingProcess.getFirstRow());
		map.put("end", pagingProcess.getLastRow());
		try {
			sqlSession = sqlSessionFactory.openSession();
			list = sqlSession.selectList("update.getUpdates", map);
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
			allRowCount = sqlSession.selectOne("update.getAllRowCount");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return allRowCount;
	}
}

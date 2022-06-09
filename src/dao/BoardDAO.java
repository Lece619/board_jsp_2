package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.BoardVO;

public class BoardDAO {
	SqlSessionFactory factory;
	
	// single-ton pattern: 
	// 객체1개만생성해서 지속적으로 서비스하자
	static BoardDAO single = null;

	public static BoardDAO getInstance() {
		//생성되지 않았으면 생성
		if (single == null)
			single = new BoardDAO();
		//생성된 객체정보를 반환
		return single;
	}
	
	
	public BoardDAO() {
		factory=MyBatisConnector.getInstance().getFactory();
	}
	
	//게시판 목록 조회
	public List<BoardVO> selectList(){
		SqlSession sqlSession = factory.openSession(true);
		List<BoardVO> list = sqlSession.selectList("b.board_list");
		
		sqlSession.close();
		return list;
	}


	public int insert(BoardVO vo) {
		SqlSession sqlSession = factory.openSession(true); // 자동으로 커밋
		int res = sqlSession.insert("b.board_insert",vo);

		sqlSession.close();
		return res;
	}
	
	//하나의 인덱스 가져오기
	public BoardVO selectOne(int idx) {
		SqlSession sqlSession = factory.openSession();
		BoardVO vo = sqlSession.selectOne("b.board_one",idx);
		sqlSession.close();
		return vo;
	}


	public int update_readhit(int idx) {
		SqlSession sqlSession = factory.openSession(true);
		
		int res = sqlSession.update("b.update_readhit",idx);
		sqlSession.close();
		return res;
	}
	
	//조회수 하나 올리기
	
}

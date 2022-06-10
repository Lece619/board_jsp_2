package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardVO;

/**
 * Servlet implementation class BoardDelAction
 */
@WebServlet("/del.do")
public class BoardDelAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		//삭제하고자 하는 원본게시물 정보를 얻어온다.
		BoardVO baseVO = dao.selectOne(idx);
		baseVO.setSubject("이미 삭제된 글입니다.");
		baseVO.setName("unknown");
		baseVO.setContent("삭제된 게시글 입니다.");
		
		int res = dao.del_update(baseVO);
		if( res != 0) {
			response.getWriter().print("[{'param':'yes'}]");
		}else {
			response.getWriter().print("[{'param':'no'}]");
		}
	}
}

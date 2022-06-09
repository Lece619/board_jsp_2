package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardVO;

/**
 * Servlet implementation class BoardViewAction
 */
@WebServlet("/view.do")
public class BoardViewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//view.do?idx=ooo;
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardVO vo = dao.selectOne(idx);
		
		//상세보기 페이지로 전환하기 위해 바인딩 및 포워딩을 해준다.
		request.setAttribute("vo", vo);
		RequestDispatcher disp = request.getRequestDispatcher("board_view.jsp");
		disp.forward(request, response);
		
		
	}

}

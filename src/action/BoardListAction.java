package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import util.Common;
import util.Paging;
import vo.BoardVO;

/**
 * Servlet implementation class BoardListAction
 */
@WebServlet("/board_list.do")
public class BoardListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int nowPage = 1; //현재 페이지 설정
		String page = request.getParameter("page");//처음엔 null
		
		if(page != null && !page.isEmpty()) {
			nowPage = Integer.parseInt(page);
		}
		
		//한 페이지에 표시될 게시물의 시작과 끝 번호를 계산 해야한다.
		//page가 1이면 1~10 까지 계산이 되야되고
		//page가 2 명 11~20 까지 계산이 되야한다.
		int start = (nowPage-1) * Common.Board.BLOCKLIST + 1;
		int end = start + Common.Board.BLOCKLIST - 1;
		
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		
		//전체목록 가져오기 
		//List<BoardVO> list = BoardDAO.getInstance().selectList();
		
		//전체 게시글 조회 -> 페이지 번호에 따른 게시글 조회
		List<BoardVO> list = BoardDAO.getInstance().selectList(map);
		
		//전체 게시글 수 조회
		int rowTotal =  BoardDAO.getInstance().getRowTotal();
		
		String pageMenu = Paging.getPaging("board_list.do", nowPage, rowTotal, Common.Board.BLOCKLIST, Common.Board.BLOCKPAGE);
		
		request.setAttribute("list", list);
		request.setAttribute("pageMenu", pageMenu);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("show")!=null) {
			session.removeAttribute("show");
		}
	
		RequestDispatcher disp = request.getRequestDispatcher("board_list.jsp");
		
		disp.forward(request, response);
		
	}

}

package action;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardVO;

/**
 * Servlet implementation class BoardInsertAction
 */
@WebServlet("/insert.do")
public class BoardInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//insert.do?subject=? .... 이런식으로 파라미터가 넘어올것.
		
		String subject = request.getParameter("subject");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String ip = request.getRemoteAddr();//ip 구해오기
		
		String myIp = InetAddress.getLocalHost().getHostAddress();
		if(ip.equals("0:0:0:0:0:0:0:1")) {
			ip=myIp;
		}
		
		BoardVO vo = new BoardVO();	
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setIp(ip);
		
		int res = BoardDAO.getInstance().insert(vo);
		
		//성공시 등록완료후 게시판 첫 페이지로 복귀
		if(res > 0) {
			response.sendRedirect("board_list.do");
		}
	}

}

package servlet;

import java.util.List;
import entity.Paste;
import java.sql.SQLException;
import dto.ReadUserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PasteService;
import util.JspUrlUtil;

import java.io.IOException;

@WebServlet("/user")
public class UserpageServlet extends HttpServlet {
	
	private static final PasteService pasteService = PasteService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReadUserDto user = (ReadUserDto) request.getSession().getAttribute("user");
	
		request.setAttribute("username", user.getLogin());
		
		try {
			List<Paste> pastes = pasteService.getPastesByUserId(user.getId());
			request.setAttribute("pastes", pastes);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(JspUrlUtil.getJspUrl("userpage.jsp"))
			.forward(request, response);
	}

}

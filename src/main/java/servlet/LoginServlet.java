package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import validation.Error;
import dto.ReadUserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AuthenticationService;
import util.JspUrlUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final AuthenticationService authenticationService = AuthenticationService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspUrlUtil.getJspUrl("login.jsp"))
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ReadUserDto readUserDto = ReadUserDto.builder()
				.login(req.getParameter("login"))
				.password(req.getParameter("password"))
				.build();
		
		try {
			Optional<Error> error = authenticationService.login(readUserDto);
			if(error.isPresent()) {
				resp.sendRedirect("/pastebinApp/login?error");
			}else {
				req.getSession().setAttribute("user", readUserDto);
				resp.sendRedirect("/pastebinApp/user");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

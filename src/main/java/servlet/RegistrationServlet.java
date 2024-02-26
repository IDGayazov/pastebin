package servlet;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.List;

import dto.CreateUserDto;
import validation.Error;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import security.PasswordEncoder;
import security.Sha256PasswordEncoder;
import service.UserService;
import util.JspUrlUtil;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet{
	
	private static final UserService userService = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspUrlUtil.getJspUrl("registration.jsp"))
		.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CreateUserDto createUserDto = CreateUserDto.builder()
				.login(req.getParameter("login"))
				.email(req.getParameter("email"))
				.password(req.getParameter("password"))
				.build();
		
		try {
			userService.createUser(createUserDto);
			resp.sendRedirect("/pastebinApp/login");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(ValidationException v) {
			String logError = getLoginError(v.getErrors());
			String emailError = getEmailError(v.getErrors()); 
			String passwordError = getPasswordError(v.getErrors());
			if(logError != null) {
				req.setAttribute("loginError", logError);
			}
			if(emailError != null) {
				req.setAttribute("emailError", emailError);
			}
			if(passwordError != null) {
				req.setAttribute("passwordError", passwordError);
			}
			doGet(req, resp);
		}
	}
	
	private String getLoginError(List<Error> errors) {
		for(Error e: errors) {
			if(e.getCode().equals("invalid.login")) {
				return e.getMessage();
			}
		}
		return null;
	}
	
	private String getEmailError(List<Error> errors) {
		for(Error e: errors) {
			if(e.getCode().equals("invalid.email")) {
				return e.getMessage();
			}
		}
		return null;
	}
	
	private String getPasswordError(List<Error> errors) {
		for(Error e: errors) {
			if(e.getCode().equals("invalid.password")) {
				return e.getMessage();
			}
		}
		return null;
	}
}

package servlet;

import dto.ReadUserDto;
import util.JspUrlUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	Optional<ReadUserDto> user = Optional.ofNullable((ReadUserDto) req.getSession().getAttribute("user"));
    	
    	if(user.isPresent()) {
    		req.setAttribute("username", user.get().getLogin());
    	}else {
    		req.setAttribute("username", "Anonymous");
    	}
    	
    	String typeError = req.getParameter("typeError");
    	req.setAttribute("typeError", typeError);
    	
        req.getRequestDispatcher(JspUrlUtil.getJspUrl("error.jsp"))
                .forward(req, resp);
    }
}

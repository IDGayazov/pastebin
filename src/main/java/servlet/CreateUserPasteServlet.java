package servlet;

import java.util.Optional;
import dto.ReadUserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PasteService;
import util.JspUrlUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;

import dto.CreatePasteDto;

@WebServlet("/create_paste")
public class CreateUserPasteServlet extends HttpServlet {
	
	private enum ExpiredDate {
	    TEN_MINUTES("10 minutes"),
	    DAY("day"),
	    WEEK("week");

	    private final String expirationTime;

	    ExpiredDate(String time){
	        this.expirationTime = time;
	    }

	    public String getTime(){
	        return this.expirationTime;
	    }
	}
    
	private static final PasteService pasteService = PasteService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Optional<ReadUserDto> user = Optional.ofNullable((ReadUserDto) request.getSession().getAttribute("user"));
		
		if(user.isPresent()) {
			request.setAttribute("username", user.get().getLogin());
		}else {
			request.setAttribute("username", "Anonymous");
		}
		
		request.getRequestDispatcher(JspUrlUtil.getJspUrl("createUserPaste.jsp"))
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReadUserDto user = (ReadUserDto) request.getSession().getAttribute("user");
		
		String duration = request.getParameter("expiration");

        Duration d = null;
        if(duration.equals(ExpiredDate.TEN_MINUTES.getTime())){
            d = Duration.ofMinutes(10);
        }else if(duration.equals(ExpiredDate.DAY.getTime())){
            d = Duration.ofDays(1);
        }else if(duration.equals(ExpiredDate.WEEK.getTime())){
            d = Duration.ofDays(7);
        }

        CreatePasteDto createPasteDto = CreatePasteDto.builder()
                .name(request.getParameter("name"))
                .category(request.getParameter("category"))
                .text(request.getParameter("text"))
                .duration(d)
                .isPublic(Boolean.valueOf(request.getParameter("isPublic")))
                .build();

        String hash = null;
        try {
            hash = pasteService.createPaste(createPasteDto, user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/pastebinApp/paste/" + hash);
	}

}

package servlet;

import dto.ReadUserDto;
import dto.PasteDto;
import exception.DateExpiredException;
import exception.InvalidHashException;
import exception.AccessDeniedException;
import service.PasteService;
import util.JspUrlUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@WebServlet("/paste/*")
public class PasteServlet extends HttpServlet {
    
	private static final String DATE_EXPIRED_ERROR = "1";
	private static final String INVALID_HASH_ERROR = "2";
	private static final String ACCESS_DENIED_ERROR = "3";
	
	private static final PasteService pasteService = PasteService.getInstance();

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	Optional<ReadUserDto> user = Optional.ofNullable((ReadUserDto) req.getSession().getAttribute("user"));
    	
        String[] url = req.getRequestURI().split("/");
        String hash = url[url.length - 1];

        PasteDto pasteDto = null;
        try {
            pasteDto = pasteService.getPasteByHash(hash);
            
            if(!pasteDto.isPublic()) {
            	if(!(user.isPresent() && user.get().getLogin().equals(pasteDto.getUsername()))) {
            		throw new AccessDeniedException();
            	}
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (DateExpiredException e){
            resp.sendRedirect("/pastebinApp/error?typeError=" + DATE_EXPIRED_ERROR);
            return;
        }catch(InvalidHashException e){
        	resp.sendRedirect("/pastebinApp/error?typeError=" + INVALID_HASH_ERROR);
            return;
        }catch (AccessDeniedException e) {
        	resp.sendRedirect("/pastebinApp/error?typeError=" + ACCESS_DENIED_ERROR);
            return;
        }

        req.setAttribute("text", pasteDto.getText());
        req.setAttribute("createDate", pasteDto.getCreateDate().format(formatter));
        req.setAttribute("username", pasteDto.getUsername());

        req.getRequestDispatcher(JspUrlUtil.getJspUrl("paste.jsp"))
                .forward(req, resp);
    }
}

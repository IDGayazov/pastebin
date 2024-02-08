package servlet;

import dto.PasteDto;
import exception.DateExpiredException;
import exception.InvalidHashException;
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

@WebServlet("/paste/*")
public class PasteServlet extends HttpServlet {
    private static final PasteService pasteService = PasteService.getInstance();

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] url = req.getRequestURI().split("/");
        String hash = url[url.length - 1];

        PasteDto pasteDto = null;
        try {
            pasteDto = pasteService.getPasteByHash(hash);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (DateExpiredException | InvalidHashException e){
            resp.sendRedirect("/pastebinApp/paste_not_found");
            return;
        }

        req.setAttribute("text", pasteDto.getText());
        req.setAttribute("createDate", pasteDto.getCreateDate().format(formatter));
        req.setAttribute("username", pasteDto.getUsername());

        req.getRequestDispatcher(JspUrlUtil.getJspUrl("paste.jsp"))
                .forward(req, resp);
    }
}

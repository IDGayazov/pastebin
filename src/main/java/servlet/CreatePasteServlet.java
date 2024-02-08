package servlet;

import dto.CreatePasteDto;
import service.PasteService;
import util.EXPIRED_DATE;
import util.JspUrlUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;

@WebServlet("/start")
public class CreatePasteServlet extends HttpServlet {

	private static final PasteService pasteService = PasteService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF8");
        req.getRequestDispatcher(JspUrlUtil.getJspUrl("start.jsp"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF8");
        String duration = req.getParameter("expiration");

        Duration d = null;
        if(duration.equals(EXPIRED_DATE.TEN_MINUTES.getTime())){
            d = Duration.ofMinutes(10);
        }else if(duration.equals(EXPIRED_DATE.DAY.getTime())){
            d = Duration.ofDays(1);
        }else if(duration.equals(EXPIRED_DATE.WEEK.getTime())){
            d = Duration.ofDays(7);
        }

        CreatePasteDto createPasteDto = CreatePasteDto.builder()
                .name(req.getParameter("name"))
                .category(req.getParameter("category"))
                .text(req.getParameter("text"))
                .duration(d)
                .build();

        String hash = null;
        try {
            hash = pasteService.createPaste(createPasteDto, 0); // пока только для неавторизованных пользователей
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/pastebinApp/paste/" + hash);
    }
}

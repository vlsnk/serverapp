package web;

import dao.GuestBookController;
import dao.GuestBookControllerImpl;
import model.Record;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet( name = "GuestBookServlet", urlPatterns = {"/guestbook"} )
public class GuestBookServlet extends HttpServlet {
    /*
    если в томкате есть такой ресурс, то он его будет использовать
     */
    @Resource(name = "jdbc/testDS")
    private DataSource ds;
    private GuestBookController guestBookController;

    @Override
    public void init() throws ServletException {
        guestBookController = new GuestBookControllerImpl(ds);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("GET");
        List<Record> records = guestBookController.getRecords();
        req.setAttribute("guestBook", records);
        req.getRequestDispatcher("/WEB-INF/guestbook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = req.getParameter("message");
        System.out.println("doPost(message = " + msg + ")");
        if (msg != null && !msg.isEmpty()) guestBookController.addRecord(msg);
        List<Record> records = guestBookController.getRecords();
        req.setAttribute("guestBook", records);
        req.getRequestDispatcher("/WEB-INF/guestbook.jsp").forward(req, resp);
    }


}

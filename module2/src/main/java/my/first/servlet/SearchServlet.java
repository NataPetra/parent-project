package my.first.servlet;

import lombok.SneakyThrows;
import my.first.dao.ProductInfoDaoImpl;
import my.first.model.ProductInfo;
import my.first.service.SearchService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = "/search.do")
public class SearchServlet extends HttpServlet {

    private SearchService searchService;

    @SneakyThrows
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        searchService = new SearchService(new ProductInfoDaoImpl());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // читаем данные с HTTP запроса
        String pname = req.getParameter("pname");
        System.out.println("Product name for search:" + pname);

        // обработали полученный запрос в бизнес сервисе
        List<ProductInfo> searchResult = searchService.search(pname);
        // сохранили для выдачи результат
        req.setAttribute("searchResult", searchResult);

        getServletContext().getRequestDispatcher("/jsp/searchResult.jsp")
                .forward(req, resp);
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }
}

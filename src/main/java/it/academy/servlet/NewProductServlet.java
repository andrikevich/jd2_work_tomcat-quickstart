package it.academy.servlet;

import it.academy.data.DaoFactory;
import it.academy.data.DatabaseName;
import it.academy.data.ProductSpec;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet (urlPatterns = "/new-product")
public class NewProductServlet  extends HttpServlet {
    DaoFactory daoFactory;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            daoFactory = DaoFactory.getInstance(DatabaseName.MYSQL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductSpec product = new ProductSpec();
        PrintWriter writer = resp.getWriter();
        try {

            product.setId(Integer.parseInt(req.getParameter("product.id")));
            product.setProductName(req.getParameter("product.name"));
            product.setProductDate(Date.valueOf(req.getParameter("product.date")));
            product.setProductDetails(req.getParameter("product.details"));
            daoFactory.getProductSpecDao().create(product);

            writer.println("Successfully added 1 product:  " + product.toString() );

        } catch (Exception ex) {
            ex.printStackTrace();
            writer.println(" !!!! ERROR the product was not been added,  you try to add product: " +product.toString());
            writer.println(ex.getMessage());
        }
    }
}

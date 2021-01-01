package it.academy.servlet;

import it.academy.data.DaoFactory;
import it.academy.data.DatabaseName;
import it.academy.data.ProductSpec;
import it.academy.data.ProductSpecDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");

        try {
            ProductSpecDao productSpecDao  = daoFactory.getProductSpecDao();
            List<ProductSpec> listProductSpec  = new ArrayList<>();
            if (id == null) {

                listProductSpec = productSpecDao.readAll();
            } else {
                listProductSpec .add( productSpecDao.read(Integer.parseInt(id)));
            }
            for (ProductSpec tmpProductSpec : listProductSpec) {

                System.out.println("id=" + tmpProductSpec.getId() + " name=" + tmpProductSpec.getProductName()
                        + " product detail=" + tmpProductSpec.getProductDetails() + " product date" + tmpProductSpec.getProductDate());


                final PrintWriter writer = resp.getWriter();
                writer.println("id=" + tmpProductSpec.getId() + " name=" + tmpProductSpec.getProductName()
                        + " product detail=" + tmpProductSpec.getProductDetails() + " product date= " + tmpProductSpec.getProductDate());
            }


        } catch ( SQLException | IOException e) {
            e.printStackTrace();
        }

    }
}

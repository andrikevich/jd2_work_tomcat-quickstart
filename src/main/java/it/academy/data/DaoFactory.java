package it.academy.data;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private DaoFactory() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private static DaoFactory daoFactory;

    public ProductSpecDao getProductSpecDao() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/product?serverTimezone=UTC",
                "root",
                "root");
        return new ProductSpecDaoImpl(connection);
    }


    public static DaoFactory getInstance(DatabaseName databaseName) throws ClassNotFoundException {

        switch (databaseName) {
            case MYSQL: {
                if (daoFactory == null)
                    daoFactory = new DaoFactory();
                return daoFactory;
            }


            case ORACLE: {
                return null;
            }


        } return null;
    }
}




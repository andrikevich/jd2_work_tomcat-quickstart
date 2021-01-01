package it.academy.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecDaoImpl implements ProductSpecDao {

    private final Connection conn;

    public ProductSpecDaoImpl(Connection conn) {
        this.conn =conn;
    }


    @Override
    public void create(ProductSpec productSpec) {
        try (PreparedStatement ps = conn.prepareStatement("insert into product.product_spec " +
                "(id, product_name, product_details, product_date)\n" +
              "            values (?,?, ?, ?)" )) {
            ps.setInt(1,productSpec.getId());
            ps.setString(2,productSpec.getProductName());
            ps.setString(3,productSpec.getProductDetails());
            ps.setDate(4,productSpec.getProductDate());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
             throw  new RuntimeException(ex);
        }
    }

    @Override
    public ProductSpec read(int id) {

        ProductSpec productSpec = new ProductSpec();

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM product_spec where id=" + id)) {

            resultSet.next();

                int theId = resultSet.getInt("id");
                productSpec.setId(theId);
                String name = resultSet.getString("product_name");
                productSpec.setProductName(name);
                String details = resultSet.getString("product_details");
                productSpec.setProductDetails(details);
                Date date = resultSet.getDate("product_date");
                productSpec.setProductDate(date);
            return productSpec;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productSpec;
    }

    @Override
    public List<ProductSpec> readAll() {
    List<ProductSpec> productSpecsList = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM product_spec")) {

            while (resultSet.next()){
                        ProductSpec productSpec = new ProductSpec();
                        int id = resultSet.getInt("id");
                        productSpec.setId(id);
                        String name = resultSet.getString("product_name");
                        productSpec.setProductName(name);
                       String details = resultSet.getString("product_details");
                        productSpec.setProductDetails(details);
                        Date date = resultSet.getDate("product_date");
                        productSpec.setProductDate(date);

                        productSpecsList.add(productSpec);

                 }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productSpecsList;
    }

    @Override
    public void update(ProductSpec productSpec) {

    }

    @Override
    public void delete(ProductSpec productSpec) {

    }
}

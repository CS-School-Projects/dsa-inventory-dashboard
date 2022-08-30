package com.group20.inventory.inventory.models;

import com.group20.inventory.inventory.utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IssuedProduct {
    private final long timestamp;
    private final int id;
    private final int productId;
    private final int quantity;
    private final int vendorId;
    private final float grossPrice;
    private Product product = null;
    private Vendor vendor = null;

    private static final Connection CONNECTION = DBConnection.getConnection();

    private IssuedProduct(int id, long timestamp, int productId, int vendorId, int quantity, float grossPrice) {
        this.id = id;
        this.timestamp = timestamp;
        this.productId = productId;
        this.vendorId = vendorId;
        this.quantity = quantity;
        this.grossPrice = grossPrice;
    }

    public Product getProduct() {
        if (product == null)
            product = Product.getObjectById(this.productId);
        return product;
    }

    public Vendor getVendor() {
        if (vendor == null)
            vendor = Vendor.getObjectById(this.productId);
        return vendor;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.id + " " + this.getProduct();
    }

    public static IssuedProduct createObject(long timestamp, int productId, int vendorId, int quantity, float grossPrice) {
        String query = String.format(
                "INSERT INTO issued_products (timestamp,product_id,vendor_id,quantity,grossPrice) VALUES('%d','%d','%d','%d','%f')",
                timestamp, productId, vendorId, quantity, grossPrice);
        try {
            Statement statement = CONNECTION.createStatement();
            int result = statement.executeUpdate(query);
            return new IssuedProduct(result, timestamp, productId, vendorId, quantity, grossPrice);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int deleteObject(int id) {
        String query = "DELETE FROM issued_products where id =" + id + ";";
        try {
            Statement statement = CONNECTION.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static IssuedProduct getObjectById(int id) {
        String query = "SELECT * FROM issued_products where id =" + id + ";";
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return new IssuedProduct(
                        resultSet.getInt("id"),
                        resultSet.getLong("timestamp"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("vendor_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getFloat("gross_price")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<IssuedProduct> selectAllObjects() {
        ArrayList<IssuedProduct> result = new ArrayList<>();
        String query = "SELECT * FROM issued_products;";
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(
                        new IssuedProduct(
                                resultSet.getInt("id"),
                                resultSet.getLong("timestamp"),
                                resultSet.getInt("product_id"),
                                resultSet.getInt("vendor_id"),
                                resultSet.getInt("quantity"),
                                resultSet.getFloat("gross_price")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

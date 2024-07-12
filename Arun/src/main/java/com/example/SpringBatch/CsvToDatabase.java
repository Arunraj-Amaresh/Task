package com.example.SpringBatch;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvToDatabase {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/csv";
        String username = "root";
        String password = "Arunraj@45";

        String csvFilePath = "src/main/resources/invoice.csv";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO invoices (invoice_No, product_No, amount, order_Date, seller_Id, sync) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;

            int count = 0;

            lineReader.readLine(); 

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");

                int invoiceNo = Integer.parseInt(data[0]);
                String productNo = data[1];
                String amount = data[2];
                String orderDate = data[3];
                String sellerDate = data[4];
                String sync = data[5];

                statement.setInt(1, invoiceNo);
                statement.setString(2, productNo);
                statement.setString(3, amount);
                statement.setString(4, orderDate);
                statement.setString(5, sellerDate);
                statement.setString(6, sync);

                statement.addBatch();

               // if (count % 100 == 0) {
                  //  statement.executeBatch();
                //}
            }

            lineReader.close();

            statement.executeBatch();
            connection.commit();
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

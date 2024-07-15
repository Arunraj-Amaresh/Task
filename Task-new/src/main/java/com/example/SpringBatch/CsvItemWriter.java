package com.example.SpringBatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class CsvItemWriter implements ItemWriter<Invoice> {
	Log log = LogFactory.getLog(CsvItemWriter.class);

    private  JdbcTemplate jdbcTemplate;

    public CsvItemWriter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void write(Chunk<? extends Invoice> chunk) throws Exception {
        String sql = "INSERT INTO invoices (invoice_No, product_No, amount, order_Date, seller_Id, sync) VALUES (?, ?, ?, ?, ?, ?)";
        
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Invoice item = chunk.getItems().get(i);
                ps.setString(1, item.getInvoice_No());
                ps.setString(2, item.getProduct_No());
                ps.setString(3, item.getAmount());
                ps.setString(4, item.getOrder_Date());
                ps.setString(5, item.getSeller_Id());
                ps.setString(6, item.getSync());
            }

            @Override
            public int getBatchSize() {
                return chunk.getItems().size();
            }
        });
    }
}

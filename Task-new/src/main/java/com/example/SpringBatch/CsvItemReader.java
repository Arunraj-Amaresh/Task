package com.example.SpringBatch;

import org.springframework.batch.item.ItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class CsvItemReader implements ItemReader<Invoice> {

    private BufferedReader reader;
    private boolean headerSkipped = false;

    public CsvItemReader() {
        try {
            this.reader = new BufferedReader(new InputStreamReader(new ClassPathResource("invoice.csv").getInputStream(), StandardCharsets.UTF_8));
            // Skip the header line
            reader.readLine();
            headerSkipped = true;
        } catch (Exception e) {
            // Handle or log the exception appropriately
            e.printStackTrace();
        }
    }

    @Override
    public Invoice read() throws Exception {
        if (!headerSkipped) {
            // If header is not skipped for some reason, skip it now
            reader.readLine();
            headerSkipped = true;
        }

        String line = reader.readLine();
        if (line == null) {
            // Close the reader when reaching the end of file
            reader.close();
            return null;  // End of file
        }

        String[] fields = line.split(",", -1); // -1 to include empty trailing fields
        if (fields.length < 6) {
            // Handle or log the case where fields are less than expected
            throw new IllegalArgumentException("Invalid CSV line: " + line);
        }

        Invoice invoice = new Invoice();
        invoice.setInvoice_No(fields[0]);
        invoice.setProduct_No(fields[1]);
        invoice.setAmount(fields[2]);
        invoice.setOrder_Date(fields[3]);
        invoice.setSeller_Id(fields[4]);
        invoice.setSync(fields[5]);
        return invoice;
    }
}

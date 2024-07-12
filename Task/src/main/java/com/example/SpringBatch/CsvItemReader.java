package com.example.SpringBatch;

import org.springframework.batch.item.ItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvItemReader implements ItemReader<Invoice> {

    private  BufferedReader reader;
    private boolean firstLine = true;

    public CsvItemReader() throws Exception {
        this.reader = new BufferedReader(new InputStreamReader(new ClassPathResource("invoice.csv").getInputStream(), StandardCharsets.UTF_8));
    }

    @Override
    public Invoice read() throws Exception {
        if (firstLine) {
            reader.readLine(); 
            firstLine = false;
        }

        String line = reader.readLine();
        if (line == null) {
            return null;
        }

        String[] fields = line.split(",");
        Invoice invoice = new Invoice();
        invoice.setInvoice_No(fields[0]);
        invoice.setProduct_No(fields[1]);
        invoice.setAmount(fields[2]);
        invoice.setOrder_Date(fields[3]);
        invoice.setSeller_Id(fields[4]);
        invoice.setSync((fields[5]));

        return invoice;
    }
}

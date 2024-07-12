package com.example.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateInvoiceFinder {

    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\Task3\\src\\main\\java\\InBound\\InBound.csv";
        String outputFilePath = "C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\Task3\\src\\main\\java\\OutBound\\outbound.csv";

        Map<String, List<String>> invoiceRows = new HashMap<>();
        Map<String, Integer> invoiceCount = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
          
            while ((line = bufferedReader.readLine()) != null) {
                String[] columns = line.split(",");
                String invoiceNo = columns[0];

                invoiceCount.put(invoiceNo, invoiceCount.getOrDefault(invoiceNo, 0) + 1);

                if (!invoiceRows.containsKey(invoiceNo)) {
                    invoiceRows.put(invoiceNo, new ArrayList<>());
                }
                invoiceRows.get(invoiceNo).add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath))) {
            
            bufferedWriter.write("invoice_no, Due_Date, Invoice_Date, Amount, Buyer, Seller"); 
            bufferedWriter.newLine();

            for (Map.Entry<String, List<String>> entry : invoiceRows.entrySet()) {
                String invoiceNo = entry.getKey();
                List<String> rows = entry.getValue();

                if (invoiceCount.get(invoiceNo) > 1) {
                    for (String row : rows) {
                        bufferedWriter.write(row);
                        bufferedWriter.newLine();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
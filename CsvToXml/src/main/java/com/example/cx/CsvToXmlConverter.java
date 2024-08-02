package com.example.cx;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Configuration;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CsvToXmlConverter {

    public static void main(String[] args) {
        try {
        	
        	
            File csvFile = new File("C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\CsvToXml\\src\\main\\resources\\input.csv");
            FileReader reader = new FileReader(csvFile);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(reader);

            List<Record> recordList = new ArrayList<>();
            for (CSVRecord csvRecord : records) {
                Record record = new Record();
                record.setAccountNumber(csvRecord.get("AccountNumber"));
                record.setPostingAmount(csvRecord.get("PostingAmount"));
                record.setPostingCcy(csvRecord.get("PostingCcy"));
                recordList.add(record);
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("Records");
            doc.appendChild(rootElement);

            for (Record record : recordList) {
                Element recordElement = doc.createElement("Record");
                rootElement.appendChild(recordElement);

                Element accountNumber = doc.createElement("AccountNumber");
                accountNumber.appendChild(doc.createTextNode(record.getAccountNumber()));
                recordElement.appendChild(accountNumber);

                Element postingAmount = doc.createElement("PostingAmount");
                postingAmount.appendChild(doc.createTextNode(record.getPostingAmount()));
                recordElement.appendChild(postingAmount);

                Element postingCcy = doc.createElement("PostingCcy");
                postingCcy.appendChild(doc.createTextNode(record.getPostingCcy()));
                recordElement.appendChild(postingCcy);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\CsvToXml\\src\\main\\resources\\output.xml"));

            transformer.transform(source, result);

            System.out.println("CSV to XML conversion completed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
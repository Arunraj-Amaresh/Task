package com.example.SpringBatch;



import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute; 
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;  

@XmlRootElement(name = "invoice") 
public class Invoice { 
	
	
	
	public Invoice() {
		
	}

	public int getInvoice_No() {
		return invoice_No;
	}
	public void setInvoice_No(int invoice_No) {
		this.invoice_No = invoice_No;
	}
	public String getProduct_No() {
		return product_No;
	}
	public void setProduct_No(String product_No) {
		this.product_No = product_No;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOrder_Date() {
		return order_Date;
	}
	public void setOrder_Date(String order_Date) {
		this.order_Date = order_Date;
	}
	public String getSeller_Id() {
		return seller_Id;
	}
	public void setSeller_Id(String seller_Id) {
		this.seller_Id = seller_Id;
	}
	public String getSync() {
		return sync;
	}
	public void setSync(String sync) {
		this.sync = sync;
	}
	
	@Override
	public String toString() {
		return "Invoice [invoice_No=" + invoice_No + ", product_No=" + product_No + ", amount=" + amount
				+ ", order_Date=" + order_Date + ", seller_Id=" + seller_Id + ", sync=" + sync + "]";
	}
	
	public Invoice(int invoice_No, String product_No, String amount, String order_Date, String seller_Id, String sync) {
		super();
		this.invoice_No = invoice_No;
		this.product_No = product_No;
		this.amount = amount;
		this.order_Date = order_Date;
		this.seller_Id = seller_Id;
		this.sync = sync;
	}
	private int invoice_No;

	private String product_No;
	private String amount;
	private String order_Date;
	private String seller_Id;
	private String sync;

  
}  

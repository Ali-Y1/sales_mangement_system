package Invoices;

public class InvoicesModel {
	private String CostumerName;
	private int price;
	private String InvoiceNumber;
	private String date;
	private String status;
	public String getCostumerName() {
		return CostumerName;
	}
	public void setCostumerName(String costumerName) {
		CostumerName = costumerName;
	}
	public String getInvoiceNumber() {
		return InvoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		InvoiceNumber = invoiceNumber;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

}

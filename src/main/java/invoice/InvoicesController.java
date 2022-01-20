package Invoices;

public class InvoicesController {
	private InvoicesModel invoice;
	private InvoicesView view;
	public InvoicesController(InvoicesModel invoice,InvoicesView view){
		this.invoice=invoice;
		this.view=view;
		
	}
	public String getCostumerName() {
		return invoice.getCostumerName();
	}
	public void setCostumerName(String costumerName) {
		invoice.setCostumerName(costumerName); ;
	}
	public String getInvoiceNumber() {
		return invoice.getInvoiceNumber() ;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		invoice.setInvoiceNumber(invoiceNumber); ;
	}
	public int getPrice() {
		return invoice.getPrice() ;
	}
	public void setPrice(int price) {
		invoice.setPrice(price);;
	}
	public String getStatus() {
		return invoice.getStatus();
	}
	public void setStatus(String status) {
		invoice.setStatus(status);;
	}
	public String getDate() {
		return invoice.getDate();
	}
	public void setDate(String date) {
		invoice.setDate(date);
	}
	
	public void updateView() {
		view.printInvoice(getInvoiceNumber(), getCostumerName(), getPrice(), getDate(), getStatus());
		
	}

	

}

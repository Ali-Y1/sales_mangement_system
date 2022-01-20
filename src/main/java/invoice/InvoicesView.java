package Invoices;

public class InvoicesView {
	public void printInvoice(String InvoiceNumber,String costumerName,int price,String date,String status) {
		System.out.println(""+InvoiceNumber+" "+costumerName+" "+price+"$ "+date+" "+status);
	}

}

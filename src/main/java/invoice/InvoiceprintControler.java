package invoice;

import Database.SQLDatabaseConnection;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import order.OrderInfo;
import order.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InvoiceprintControler {

    @FXML
    private Label addressvalue;

    @FXML
    private Text addresvalue;

    @FXML
    private TableColumn<product, Integer> amount;

    @FXML
    private Button cancelbtn;

    @FXML
    private ImageView canceling;

    @FXML
    private Label datevalue;

    @FXML
    private Label idvalue;

    @FXML
    private Label namevalue;

    @FXML
    private Label phonevalue;

    @FXML
    private TableColumn<product, Integer> price;

    @FXML
    private Label pricevalue;

    @FXML
    private TableColumn<product, String> prname;



    @FXML
    private TableView<product> tableofproduct;


    ObservableList<product> pro= FXCollections.observableArrayList();

    private ArrayList<product> p;
    private OrderInfo or=null;
    private InvoicesModel data;

    public InvoiceprintControler(InvoicesModel rowData) {
        data=rowData;
    }
    @FXML
    public void initialize(){
        System.out.println(data);
        namevalue.setText(data.getCname());
        pricevalue.setText(String.valueOf(data.getPrice())+".00 $");
        idvalue.setText(String.valueOf(data.getCid()));
        phonevalue.setText(String.valueOf(data.getPhone()));
        addressvalue.setText(data.getAddress());
        datevalue.setText(data.getDate());
        products(data.getCid());
    }

    public void products(Integer id){
        SQLDatabaseConnection conectnow=new SQLDatabaseConnection();
        Connection connectDB=conectnow.getConnection();
        System.out.println(id);
        String stmt="select products from orders where cid="+id;
        or=new OrderInfo();
        try {
            Statement stm=connectDB.createStatement();
            ResultSet queryoutput2=stm.executeQuery(stmt);
            while (queryoutput2.next()){
                or.parseProduct(queryoutput2.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       p=or.getProducts();
        pro=FXCollections.observableArrayList(p);
        prname.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tableofproduct.setItems(pro);
    }

    @FXML
    public void savepdf(ActionEvent actionEvent) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.PDF)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());

       // String path="E:/invoce"+data.getCname()+".pdf";
        PdfWriter pdfWriter=new PdfWriter(file);
        PdfDocument pdfDocument=new PdfDocument(pdfWriter);
        Document document=new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        float col=280f;
        float colwidth[]={col, col};
        Table table=new Table(colwidth);

        table.setBackgroundColor(new DeviceRgb(128, 0, 255));
        table.addCell(new Cell().add(new Paragraph("Invoices")).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f).setMarginBottom(30f).setFontSize(30f).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("LU Store \n LUStore@gmail.com \n 71564355")).setTextAlignment(TextAlignment.RIGHT)
                .setMarginTop(30) .setMarginRight(10f).setMarginBottom(30f).setBorder(Border.NO_BORDER));


        float coluomwidth []={80,300,100,80};
        Table coustomerinfotable=new Table(coluomwidth);
        coustomerinfotable.addCell(new Cell(0,4).add(new Paragraph("Customer Info")).setBold().setBorder(Border.NO_BORDER));

        coustomerinfotable.addCell(new Cell().add(new Paragraph("Id :")).setBorder(Border.NO_BORDER));
        coustomerinfotable.addCell(new Cell().add(new Paragraph(data.getCid().toString())).setBorder(Border.NO_BORDER));
        coustomerinfotable.addCell(new Cell().add(new Paragraph("Address :")).setBorder(Border.NO_BORDER));
        coustomerinfotable.addCell(new Cell().add(new Paragraph(data.getAddress())).setBorder(Border.NO_BORDER));


        coustomerinfotable.addCell(new Cell().add(new Paragraph("Name :")).setBorder(Border.NO_BORDER));
        coustomerinfotable.addCell(new Cell().add(new Paragraph(data.getCname())).setBorder(Border.NO_BORDER));

        coustomerinfotable.addCell(new Cell().add(new Paragraph("Date :")).setBorder(Border.NO_BORDER));
        coustomerinfotable.addCell(new Cell().add(new Paragraph(data.getDate())).setBorder(Border.NO_BORDER));
        coustomerinfotable.addCell(new Cell().add(new Paragraph("Phone :")).setBorder(Border.NO_BORDER));
        coustomerinfotable.addCell(new Cell().add(new Paragraph(data.getPhone().toString())).setBorder(Border.NO_BORDER));

        float iteminfowidith[]={180,180,180};
        Table iteminfotable=new Table(iteminfowidith);

        iteminfotable.addCell(new Cell().add(new Paragraph("Name")).setBackgroundColor(new DeviceRgb(128, 0, 255)));
        iteminfotable.addCell(new Cell().add(new Paragraph("Amount")).setBackgroundColor(new DeviceRgb(128, 0, 255)).setTextAlignment(TextAlignment.RIGHT));
        iteminfotable.addCell(new Cell().add(new Paragraph("Price")).setBackgroundColor(new DeviceRgb(128, 0, 255)).setTextAlignment(TextAlignment.RIGHT));
        for (int i=0;i<p.size();i++){
           /// pro.add(new product(p.get(i).getName(),p.get(i).getPrice(),p.get(i).getAmount()));
            iteminfotable.addCell(new Cell().add(new Paragraph(p.get(i).getName())));
            iteminfotable.addCell(new Cell().add(new Paragraph(""+p.get(i).getAmount())).setTextAlignment(TextAlignment.RIGHT));
            iteminfotable.addCell(new Cell().add(new Paragraph(""+p.get(i).getPrice())).setTextAlignment(TextAlignment.RIGHT));
        }

        iteminfotable.addCell(new Cell().add(new Paragraph("")).setBackgroundColor(new DeviceRgb(128, 0, 255)).setBorder(Border.NO_BORDER));
        iteminfotable.addCell(new Cell().add(new Paragraph("Total Price :")).setBackgroundColor(new DeviceRgb(128, 0, 255)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        iteminfotable.addCell(new Cell().add(new Paragraph(data.getPrice().toString())).setBackgroundColor(new DeviceRgb(128, 0, 255)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));


        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(coustomerinfotable);
        document.add(new Paragraph("\n"));
        document.add(iteminfotable);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("WELCOME ").setTextAlignment(TextAlignment.RIGHT));
        document.close();
        System.out.println("done");
    }

     @FXML
    public void cancel(ActionEvent actionEvent) {
        Stage stage= (Stage) cancelbtn.getScene().getWindow();
        stage.close();
    }


}

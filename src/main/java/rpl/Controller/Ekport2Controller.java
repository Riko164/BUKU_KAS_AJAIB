/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl.Controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * FXML Controller class
 *
 * @author Shikimime
 */
public class Ekport2Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField namatxt;

    @FXML
    private Button filebtn, eksportbtn;

    @FXML
    private RadioButton excell, pdf;

    private String path;

    private TableView<Tabel> tbl = new TableView<Tabel>();
    private TableColumn<Tabel, String> no, jenis, kategori, judul, transaksi, jumlah;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void excelselected() {
        pdf.setSelected(false);
    }

    public void pdfselected() {
        excell.setSelected(false);
    }

    private boolean isavailabe(String nama, String jenis, String path) {
        File f = new File(path + nama + jenis);
        System.out.println(path + nama + jenis);
        System.out.println(f.exists());
        return f.exists();
    }

    public void Eksport() {
        if (excell.isSelected()) {
            try {
                Workbook workbook = new HSSFWorkbook();
                org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet("sample");

                Row row = spreadsheet.createRow(0);
                row.createCell(0).setCellValue("LAPORAN KEUANGAN BUKU KAS AJAIB");

                row = spreadsheet.createRow(1);
                for (int i = 0; i < tbl.getColumns().size(); i++) {
                    row.createCell(i).setCellValue(tbl.getColumns().get(i).getText());
                }

                for (int i = 1; i < tbl.getItems().size(); i++) {
                    row = spreadsheet.createRow(i + 1);
                    for (int j = 0; j < tbl.getColumns().size(); j++) {
                        if (tbl.getColumns().get(j).getCellData(i) != null) {
                            row.createCell(j).setCellValue(tbl.getColumns().get(j).getCellData(i).toString());
                        } else {
                            row.createCell(j).setCellValue("");
                        }
                    }
                }
                String nama=namatxt.getText();
                boolean available = true;
                while (available) {
                    available = isavailabe(nama, ".xls", this.path + "\\");
                    if (available) {
                        nama += "_1";
                    }
                    System.out.println(nama);
                }

                FileOutputStream fileout = new FileOutputStream(path + "\\" + nama + ".xls");
                workbook.write(fileout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Document ekspdf = new Document();
                
                
                String nama = namatxt.getText();
                boolean available = true;
                while (available) {
                    available = isavailabe(nama, ".pdf", this.path + "\\");
                    if (available) {
                        nama += "_1";
                    }
                    System.out.println(nama);
                }
                PdfWriter.getInstance(ekspdf, new FileOutputStream(path + "\\" + nama + ".pdf"));
                ekspdf.open();
                ekspdf.addTitle("LAPORAN KEUANGAN DARI BUKU KAS AJAIB");
                PdfPTable pdftbl = new PdfPTable(tbl.getColumns().size());

                PdfPCell table_cell;

                for (int i = 0; i < tbl.getColumns().size(); i++) {
                    table_cell = new PdfPCell(new Phrase(tbl.getColumns().get(i).getText()));
                    pdftbl.addCell(table_cell);
                }

                for (int i = 0; i < tbl.getItems().size(); i++) {
                    for (int j = 0; j < tbl.getColumns().size(); j++) {
                        if (tbl.getColumns().get(j).getCellData(i) != null) {
                            table_cell = new PdfPCell(new Phrase(tbl.getColumns().get(j).getCellData(i).toString()));
                            pdftbl.addCell(table_cell);
                        } else {
                            table_cell = new PdfPCell(new Phrase(""));
                            pdftbl.addCell(table_cell);
                        }
                    }
                }

                ekspdf.add(pdftbl);
                ekspdf.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(path + "\\" + this.namatxt.getText() + ".xlsx");
        System.out.println("berhasil bambang");
        message("berhasil eksport");
        Stage stage = (Stage) this.eksportbtn.getScene().getWindow();
        stage.close();
    }

    public void message(String isi) {
        Alert pesan = new Alert(Alert.AlertType.INFORMATION);
        pesan.setTitle("MESSAGE");
        pesan.setContentText(isi);
        pesan.showAndWait();
    }

    public void setTbl(TableView<Tabel> tbl) {
        this.tbl = tbl;
    }

    public void getlocation() {
        DirectoryChooser src = new DirectoryChooser();
        src.setTitle("SELECT LOCATION");
        File defaultDirectory = new File(System.getProperty("user.home"));
        src.setInitialDirectory(defaultDirectory);
        File selectedDirectory = src.showDialog(this.filebtn.getScene().getWindow());
        this.path = selectedDirectory.getAbsolutePath();
        System.out.println(path);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

/**
 *
 * @author Kevin
 */
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import dto.*;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GenerarReserva {

    public static void GenerarPdf(List lista, int tipo) {
        Document document = new Document();

        try {
            String nombre = "";
            if (tipo == 0) {
                nombre = "Medicamentos";
            } else if (tipo == 1) {
                nombre = "Reservas";
            }
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Sergio\\Desktop\\Informe" + nombre + ".pdf"));
            document.open();
            Paragraph par1 = new Paragraph();
            Font font_titulo = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
            par1.setAlignment(Element.ALIGN_CENTER);
            par1.add(new Phrase(Chunk.NEWLINE));
            par1.add(new Phrase(Chunk.NEWLINE));
            PdfPTable table = new PdfPTable(6);
            if (tipo == 0) { // Tipo 0 es Medicamento
                par1.add(new Phrase("MEDICAMENTOS", font_titulo));
                
                table.addCell("Nombre");
                table.addCell("Laboratorio");
                table.addCell("Cantidad (Pildoras)");
                table.addCell("Fecha de Vencimiento");
                table.addCell("Gramaje");
                table.addCell("Gramaje");
            } else if (tipo == 1) {
                par1.add(new Phrase("RESERVAS", font_titulo));
               
                table.addCell("Nombre paciente");
                table.addCell("Fecha Inicio");
                table.addCell("Fecha Termino");
                table.addCell("Estado");
                table.addCell("Nombre Medicamento");
                table.addCell("Cantidad Reservada");
            }

            document.add(par1);
            // Este codigo genera una tabla de 3 columnas

            // addCell() agrega una celda a la tabla, el cambio de fila
            // ocurre automaticamente al llenar la fila
            if (tipo == 0) { // Tipo 0 es Medicamento
                for (Object arg : lista) {
                    MedicamentoDto dto = (MedicamentoDto) arg;
                    table.addCell(dto.getNombre());
                    table.addCell(dto.getFabricante());
                    table.addCell(dto.getCantidad());
                    table.addCell(dto.getFecha_vencimiento().toString());
                    table.addCell(dto.getGramaje());
                }
            } else if (tipo == 1) { // Tipo 1 es Reserva
                for (Object arg : lista) {

                    ApoyoDto rese = (ApoyoDto) arg;
                    table.addCell(rese.getNombre_paciente());
                    table.addCell(rese.getFecha_inicio().toString());
                    table.addCell(rese.getFecha_termino().toString());
                    table.addCell(rese.getEstado());
                    table.addCell(rese.getNombre_Medicamento());
                    table.addCell(rese.getCantidad().toString());
                }
            }

            // Si desea crear una celda de mas de una columna
            // Cree un objecto Cell y cambie su propiedad span
            PdfPCell celdaFinal = new PdfPCell(new Paragraph("Filas Totales: " + lista.size()));

            // Indicamos cuantas columnas ocupa la celda
            celdaFinal.setColspan(6);
            table.addCell(celdaFinal);

            // Agregamos la tabla al documento            
            document.add(table);

            document.close();

        } catch (Exception e) {
            System.err.println("Ocurrio un error al crear el archivo");
            System.exit(-1);
        }
    }
}

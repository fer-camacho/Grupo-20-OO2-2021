package G20.OO2.helpers;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import G20.OO2.models.UserModel;

@Component("vendor/list_users")
public class ListUsersPDF extends AbstractPdfView {
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<UserModel> listUsuarios = (List<UserModel>) model.get("usuarios");
		
		Font fuente = FontFactory.getFont("Arial", 14, Color.WHITE);
		Font fuenteTituloCol = FontFactory.getFont("Arial", 12, Color.WHITE);
		Font fuenteCeldas = FontFactory.getFont("Arial", 10, Color.BLACK);
		
		document.setPageSize(PageSize.A4.rotate());
		document.open();
		PdfPCell celda = null;
		
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell titulo = null;
		
		
		titulo = new PdfPCell(new Phrase("LISTADO DE USUARIOS", fuente));
		titulo.setBorder(0);
		titulo.setBackgroundColor(new Color(2,62,138));//#023E8A
		titulo.setHorizontalAlignment(Element.ALIGN_CENTER);
		titulo.setVerticalAlignment(Element.ALIGN_CENTER);
		titulo.setPadding(18);
		
		tablaTitulo.addCell(titulo);
		tablaTitulo.setSpacingAfter(20);
		
		PdfPTable tablaUsuarios = new PdfPTable(5);
		tablaUsuarios.setWidths(new float[] {1f, 2f, 2f, 2f, 2f});
		
		celda = new PdfPCell(new Phrase("ID", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		celda = new PdfPCell(new Phrase("APELLIDO", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		celda = new PdfPCell(new Phrase("NOMBRE", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		celda = new PdfPCell(new Phrase("NRO DOCUMENTO", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		celda = new PdfPCell(new Phrase("USUARIO", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		
		for (UserModel u: listUsuarios) {
			celda = new PdfPCell(new Phrase(Integer.toString(u.getId()), fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
			
			celda = new PdfPCell(new Phrase(u.getApellido(), fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
			
			celda = new PdfPCell(new Phrase(u.getNombre(), fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
			
			celda = new PdfPCell(new Phrase(Long.toString(u.getNroDocumento()), fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
			
			celda = new PdfPCell(new Phrase(u.getUsername(), fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
		}
		
		document.add(tablaTitulo);
		document.add(tablaUsuarios);
	}
	
}

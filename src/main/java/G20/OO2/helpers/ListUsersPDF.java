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

@Component("user/list_users")
public class ListUsersPDF extends AbstractPdfView {
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String estado;
		
		@SuppressWarnings("unchecked")
		List<UserModel> listUsuarios = (List<UserModel>) model.get("usuarios");
		
		Font fuente = FontFactory.getFont("Arial", 14, Color.WHITE);
		Font fuenteTituloCol = FontFactory.getFont("Arial", 12, Color.WHITE);
		Font fuenteCeldas = FontFactory.getFont("Arial", 10, Color.BLACK);
		
		document.setPageSize(PageSize.A4.rotate());
		document.setMargins(-20, -20, 40, 20);
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
		
		PdfPTable tablaUsuarios = new PdfPTable(7);
		tablaUsuarios.setWidths(new float[] {2f, 2f, 3f, 3.5f, 2f, 2f, 3f});
		
		celda = new PdfPCell(new Phrase("NOMBRE", fuenteTituloCol));
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
		
		celda = new PdfPCell(new Phrase("DOCUMENTO", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		celda = new PdfPCell(new Phrase("EMAIL", fuenteTituloCol));
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
		
		celda = new PdfPCell(new Phrase("PERFIL", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		celda = new PdfPCell(new Phrase("ESTADO", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsuarios.addCell(celda);
		
		
		for (UserModel u: listUsuarios) {
			
			celda = new PdfPCell(new Phrase(u.getPersona().getNombre(), fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
			
			celda = new PdfPCell(new Phrase(u.getPersona().getApellido(), fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
			
			celda = new PdfPCell(new Phrase(Long.toString(u.getPersona().getNroDocumento()), fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
			
			celda = new PdfPCell(new Phrase(u.getPersona().getEmail(), fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
			
			celda = new PdfPCell(new Phrase(u.getUsername(), fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
			
			celda = new PdfPCell(new Phrase(u.getUserRole().getRole(), fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
			
			if(u.isEnabled()) estado = "DESBLOQUEADO";
			else estado = "BLOQUEADO";
			
			celda = new PdfPCell(new Phrase(estado, fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaUsuarios.addCell(celda);
		}
		
		document.add(tablaTitulo);
		document.add(tablaUsuarios);
	}
	
}

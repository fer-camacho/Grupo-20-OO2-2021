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

import G20.OO2.models.UserRoleModel;

@Component("role/list_roles")
public class ListUserRolesPDF extends AbstractPdfView {
	
	
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String estado;
		
		@SuppressWarnings("unchecked")
		List<UserRoleModel> listRoles = (List<UserRoleModel>) model.get("roles");
		
		Font fuente = FontFactory.getFont("Arial", 14, Color.WHITE);
		Font fuenteTituloCol = FontFactory.getFont("Arial", 12, Color.WHITE);
		Font fuenteCeldas = FontFactory.getFont("Arial", 10, Color.BLACK);
		
		document.setPageSize(PageSize.A4.rotate());
		document.setMargins(-20, -20, 40, 20);
		document.open();
		PdfPCell celda = null;
		
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell titulo = null;
		
		titulo = new PdfPCell(new Phrase("LISTADO DE PERFILES", fuente));
		titulo.setBorder(0);
		titulo.setBackgroundColor(new Color(2,62,138));//#023E8A
		titulo.setHorizontalAlignment(Element.ALIGN_CENTER);
		titulo.setVerticalAlignment(Element.ALIGN_CENTER);
		titulo.setPadding(18);
		
		tablaTitulo.addCell(titulo);
		tablaTitulo.setSpacingAfter(20);
		
		PdfPTable tablaRoles = new PdfPTable(2);
		tablaRoles.setWidths(new float[] {1f, 1f});
		
		celda = new PdfPCell(new Phrase("PERFIL", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaRoles.addCell(celda);
		
		celda = new PdfPCell(new Phrase("ESTADO", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaRoles.addCell(celda);
		
		for(UserRoleModel rol: listRoles) {
			
			celda = new PdfPCell(new Phrase(rol.getRole(), fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaRoles.addCell(celda);
			
			if(rol.isEnabled()) estado = "DESBLOQUEADO";
			else estado = "BLOQUEADO";
			
			celda = new PdfPCell(new Phrase(estado, fuenteCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setVerticalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaRoles.addCell(celda);
		}
		
		document.add(tablaTitulo);
		document.add(tablaRoles);
	}
	
}

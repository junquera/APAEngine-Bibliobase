package es.junquera.bibliobase.client;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;

import es.junquera.bibliobase.server.Libro;

public class LibroUI extends DockPanel {

	private TextBox titulo = new TextBox();
	private Image foto;

	public LibroUI(Libro libro) {
		super();
		HorizontalPanel hp1 = new HorizontalPanel();
		titulo.setText(libro.getTitulo());
		titulo.setReadOnly(true);
		titulo.setWidth("500px");
		hp1.add(titulo);
		hp1.add(new HTML("	ISBN: " + libro.getIsbn()));
		super.add(hp1, DockPanel.NORTH);

		foto = new Image(libro.getFoto());
		foto.setWidth("100px");
		super.add(foto, DockPanel.WEST);
		
		super.add(new HTML("CENTER"), DockPanel.CENTER);
		super.add(new HTML("EAST"), DockPanel.EAST);
		super.add(new HTML("SOUTH"), DockPanel.SOUTH);


	}

	/**
	 * var titulo: String,
	 * 
	 * @Persistent var Autores: List[String],
	 * @Persistent var edicion: Integer,
	 * @Persistent var resumen: String,
	 * @Persistent var fechaPublicacion: Date,
	 * @Persistent var paginas: Integer,
	 * @PrimaryKey @Persistent val isbn: String,
	 * @Persistent var url: URL,
	 * @Persistent var materia: String,
	 * @Persistent var foto: URL,
	 * @Persistent var copiasExistentes: Integer) {
	 */
}

package es.junquera.bibliobase.client;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;

import es.junquera.bibliobase.server.libro.Libro;

public class LibroUI extends DockPanel {

	public LibroUI(Libro libro) {
		super();
		super.add(new HTML("TITULO :" + libro.getTitulo()), DockPanel.NORTH);
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

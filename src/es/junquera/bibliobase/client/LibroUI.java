package es.junquera.bibliobase.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import es.junquera.bibliobase.server.Libro;

/**
 * Los problemas vienen de incongruencias entre init() y actualizaLibro()
 * 
 * @author Junquera
 *
 */
public class LibroUI extends VerticalPanel {

	protected Libro libro;
	List<TextBox> editables = new ArrayList<TextBox>();

	Image foto;
	TextBox titulo = new TextBox();
	TextBox resumen = new TextBox();
	TextBox foto_url = new TextBox();
	TextBox url = new TextBox();
	TextBox nPags = new TextBox();
	TextBox nCopias = new TextBox();
	TextBox materia = new TextBox();
	TextBox autores = new TextBox();
	TextBox isbn = new TextBox();

	VerticalPanel cabecera = new VerticalPanel();
	HorizontalPanel panelTitulo = new HorizontalPanel();
	HorizontalPanel cuerpo = new HorizontalPanel();
	VerticalPanel panelFoto = new VerticalPanel();
	VerticalPanel more = new VerticalPanel();

	protected void init() {
		editable(true);

		this.isbn.setText(this.libro.getIsbn());

		this.titulo.setText(this.libro.getTitulo());

		this.url.setText(this.libro.getUrl());

		this.nPags.setText(this.libro.getPaginas() + "");

		this.materia.setText(this.libro.getMateria());

		this.resumen.setText(this.libro.getResumen());

		this.nCopias.setText(this.libro.getCopiasExistentes() + "");

		String aut = "";
		for (String s : this.libro.getAutores())
			aut += s + "\n";
		this.autores.setText(aut);

		this.foto.setUrl("");
		this.foto.setUrl(this.libro.getFoto());

		this.foto_url.setText(this.libro.getFoto());

		editable(false);

	}

	public LibroUI(final Libro libro) {
		super();
		super.addStyleName("libro");
		this.libro = libro;

		this.panelTitulo.add(new HTML("	Titulo: "));
		this.panelTitulo.add(titulo);

		this.panelTitulo.add(new HTML("	ISBN: "));
		this.panelTitulo.add(isbn);

		this.cabecera.add(this.panelTitulo);

		this.panelTitulo.add(new HTML("	URL: "));
		this.cabecera.add(this.url);

		this.foto = new Image();
		this.foto.setWidth("100px");
		panelFoto.add(this.foto);
		this.foto_url.setVisible(false);
		panelFoto.add(this.foto_url);
		cuerpo.add(panelFoto);

		this.cuerpo.add(this.resumen);

		this.more.add(new HTML("Número de páginas:"));
		this.more.add(this.nPags);
		this.more.add(new HTML("Número de copias:"));
		this.more.add(this.nCopias);
		this.more.add(new HTML("Materia:"));
		this.more.add(this.materia);

		this.cuerpo.add(new HTML("	Autores: "));
		this.cuerpo.add(this.autores);

		this.cuerpo.add(this.more);

		super.add(this.cabecera);
		super.add(this.cuerpo);

		this.editables.add(this.nPags);
		this.editables.add(this.nCopias);
		this.editables.add(this.materia);
		this.editables.add(this.resumen);
		this.editables.add(this.foto_url);
		this.editables.add(this.titulo);
		this.editables.add(this.url);
		this.editables.add(this.isbn);
		this.editables.add(this.autores);
		
		init();

	}

	protected void borrarLibro() {
		super.clear();
	}

	protected void editable(boolean editable) {
		if (editable) {
			for (TextBox tb : editables)
				tb.setReadOnly(false);
			foto_url.setVisible(true);
		} else {
			for (TextBox tb : editables)
				tb.setReadOnly(true);
			foto_url.setVisible(false);
		}
	}
}

package es.junquera.bibliobase.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import es.junquera.bibliobase.server.Libro;

public class LibroUI extends VerticalPanel {

	private TextBox titulo = new TextBox();
	private TextBox resumen = new TextBox();
	private Image foto;
	private TextBox foto_url = new TextBox();
	private TextBox url = new TextBox();
	private Button editar = new Button("Editar");
	private Button guardarEdicion = new Button("Guardar");
	private Button cancelarEdicion = new Button("Cancelar");
	private List<TextBox> editables = new ArrayList<TextBox>();

	public LibroUI(Libro libro) {
		super();
		super.addStyleName("libro");

		editables.add(titulo);

		titulo.setText(libro.getTitulo());
		titulo.setReadOnly(true);
		titulo.setWidth("500px");

		VerticalPanel cabecera = new VerticalPanel();
		HorizontalPanel panelTitulo = new HorizontalPanel();
		panelTitulo.add(titulo);
		panelTitulo.add(new HTML("	ISBN: " + libro.getIsbn()));
		editables.add(titulo);
		cabecera.add(panelTitulo);
		url.setText(libro.getUrl());
		editables.add(url);
		cabecera.add(url);

		HorizontalPanel cuerpo = new HorizontalPanel();
		VerticalPanel panelFoto = new VerticalPanel();
		foto = new Image(libro.getFoto());
		foto.setWidth("100px");
		panelFoto.add(foto);
		foto_url.setText(libro.getFoto());
		foto_url.setReadOnly(true);
		editables.add(foto_url);
		panelFoto.add(foto_url);
		cuerpo.add(panelFoto);

		super.add(cabecera);
		super.add(cuerpo);

		resumen.setText(libro.getResumen());
		resumen.setReadOnly(true);
		editables.add(resumen);
		super.add(resumen);

		HorizontalPanel botones = new HorizontalPanel();
		editar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				for (TextBox tb : editables)
					tb.setReadOnly(false);
				editar.setEnabled(false);
				guardarEdicion.setVisible(true);
				cancelarEdicion.setVisible(true);
			}
		});

		guardarEdicion.setVisible(false);
		guardarEdicion.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				for (TextBox tb : editables)
					tb.setReadOnly(true);
				editar.setEnabled(true);
				guardarEdicion.setVisible(false);
				cancelarEdicion.setVisible(false);
			}
		});
		
		cancelarEdicion.setVisible(false);
		cancelarEdicion.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				for (TextBox tb : editables)
					tb.setReadOnly(true);
				editar.setEnabled(true);
				guardarEdicion.setVisible(false);
				cancelarEdicion.setVisible(false);
			}
		});

		botones.add(editar);
		botones.add(guardarEdicion);
		botones.add(cancelarEdicion);
		super.add(botones);
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
	 * @Persistent var copiasExistentes: Integer) {
	 */
}

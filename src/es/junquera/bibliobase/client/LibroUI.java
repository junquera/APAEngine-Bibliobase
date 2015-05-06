package es.junquera.bibliobase.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import es.junquera.bibliobase.server.Libro;

public class LibroUI extends VerticalPanel {

	private Libro libro;

	private Image foto;
	private TextBox titulo = new TextBox();
	private TextBox resumen = new TextBox();
	private TextBox foto_url = new TextBox();
	private TextBox url = new TextBox();
	private TextBox nPags = new TextBox();
	private TextBox nCopias = new TextBox();
	private TextBox materia = new TextBox();
	private Button editar = new Button("Editar");
	private Button guardarEdicion = new Button("Guardar");
	private Button cancelarEdicion = new Button("Cancelar");
	private Button reservar = new Button("Reservar");
	private Button devolver = new Button("Devolver");
	private List<TextBox> editables = new ArrayList<TextBox>();

	private void init() {
		super.clear();
		editables.add(titulo);

		titulo.setText(libro.getTitulo());
		titulo.setSize("100%", "100%");
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
		foto_url.setVisible(false);
		editables.add(foto_url);
		panelFoto.add(foto_url);
		cuerpo.add(panelFoto);

		resumen.setText(libro.getResumen());
		editables.add(resumen);
		cuerpo.add(resumen);

		VerticalPanel more = new VerticalPanel();

		nPags.setText(libro.getPaginas() + "");
		editables.add(nPags);

		nCopias.setText(libro.getCopiasExistentes() + "");
		editables.add(nCopias);

		materia.setText(libro.getMateria());
		editables.add(materia);

		more.add(new HTML("Número de páginas:"));
		more.add(nPags);
		more.add(new HTML("Número de copias:"));
		more.add(nCopias);
		more.add(new HTML("Materia:"));
		more.add(materia);

		cuerpo.add(more);

		super.add(cabecera);
		super.add(cuerpo);

		HorizontalPanel gestion = new HorizontalPanel();
		reservar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				nCopias.setText(Integer.parseInt(nCopias.getText()) - 1 + "");
				actualizaLibro();
			}
		});
		reservar.setEnabled(libro.getCopiasExistentes() > 0);

		devolver.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				nCopias.setText(Integer.parseInt(nCopias.getText()) + 1 + "");
				actualizaLibro();
			}
		});

		gestion.add(reservar);
		gestion.add(devolver);
		super.add(gestion);

		HorizontalPanel botones = new HorizontalPanel();
		editar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				editable(true);
			}
		});

		guardarEdicion.setVisible(false);
		guardarEdicion.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				editable(false);
				actualizaLibro();
			}
		});

		cancelarEdicion.setVisible(false);
		cancelarEdicion.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				editable(false);
				init();
			}
		});

		botones.add(editar);
		botones.add(guardarEdicion);
		botones.add(cancelarEdicion);
		super.add(botones);

		for (TextBox tb : editables)
			tb.setReadOnly(true);
		editar.setEnabled(true);
	}

	public LibroUI(Libro libro) {
		super();
		super.addStyleName("libro");
		this.libro = libro;
		init();
	}

	private void editable(boolean editable) {
		if (editable) {
			for (TextBox tb : editables)
				tb.setReadOnly(false);
			editar.setEnabled(false);
			foto_url.setVisible(true);
			guardarEdicion.setVisible(true);
			cancelarEdicion.setVisible(true);
		} else {
			for (TextBox tb : editables)
				tb.setReadOnly(true);
			editar.setEnabled(true);
			foto_url.setVisible(false);
			guardarEdicion.setVisible(false);
			cancelarEdicion.setVisible(false);
		}
	}

	private void actualizaLibro() {
		final Libro nuevo = new Libro();
		nuevo.setTitulo(this.titulo.getText());
		nuevo.setAutores(libro.getAutores()); //
		nuevo.setCopiasExistentes(Integer.parseInt(this.nCopias.getText()));
		nuevo.setEdicion(libro.getEdicion()); //
		nuevo.setFechaPublicacion(libro.getFechaPublicacion()); //
		nuevo.setFoto(this.foto_url.getText());
		nuevo.setMateria(this.materia.getText());
		nuevo.setPaginas(Integer.parseInt(this.nPags.getText()));
		nuevo.setResumen(this.resumen.getText());
		nuevo.setUrl(this.url.getText());
		nuevo.setIsbn(libro.getIsbn());
		BiblioBase.biblioBaseService.actualizaLibro(nuevo,
				new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						BiblioBase.alerta("Libro actualizado correctamente");
						libro = nuevo;
						init();
					}

					@Override
					public void onFailure(Throwable caught) {
						BiblioBase.alerta("Libro no actualizado");
						init();
					}
				});
	}
}

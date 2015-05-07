package es.junquera.bibliobase.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

import es.junquera.bibliobase.server.BiblioException;
import es.junquera.bibliobase.server.Libro;

public class LibroUIAdmin extends LibroUI {

	Button editar = new Button("Editar");
	Button guardarEdicion = new Button("Guardar");
	Button cancelarEdicion = new Button("Cancelar");
	Button borrarLibro = new Button("Borrar libro");

	Button reservar = new Button("Reservar");
	Button devolver = new Button("Devolver");

	HorizontalPanel gestion = new HorizontalPanel();
	HorizontalPanel botones = new HorizontalPanel();

	public LibroUIAdmin(final Libro libro) {
		super(libro);

		this.reservar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				try {
					libro.reserva();
				} catch (BiblioException e) {
					BiblioBase.alerta(e.getMessage());
				}
				BiblioBase.biblioBaseService.actualizaLibro(libro,
						new AsyncCallback<Boolean>() {

							@Override
							public void onSuccess(Boolean result) {
								BiblioBase
										.alerta("Libro actualizado correctamente");
								initAdmin();
							}

							@Override
							public void onFailure(Throwable caught) {
								BiblioBase.alerta("Libro no actualizado");
							}
						});

			}
		});

		this.devolver.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				libro.devuelve();
				BiblioBase.biblioBaseService.actualizaLibro(libro,
						new AsyncCallback<Boolean>() {

							@Override
							public void onSuccess(Boolean result) {
								BiblioBase
										.alerta("Libro actualizado correctamente");
								initAdmin();
							}

							@Override
							public void onFailure(Throwable caught) {
								BiblioBase.alerta("Libro no actualizado");
							}
						});

			}
		});

		gestion.add(this.reservar);
		gestion.add(this.devolver);
		super.add(gestion);

		this.editar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				editableAdmin(true);
			}
		});

		this.guardarEdicion.setVisible(false);
		this.guardarEdicion.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				editableAdmin(false);
				actualizaLibro();
			}
		});

		this.cancelarEdicion.setVisible(false);
		this.cancelarEdicion.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				editableAdmin(false);
				initAdmin();
			}
		});

		this.borrarLibro.setVisible(false);
		this.borrarLibro.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				BiblioBase.biblioBaseService.borraLibro(libro,
						new AsyncCallback<Boolean>() {

							@Override
							public void onSuccess(Boolean result) {
								BiblioBase
										.alerta("Libro borrado correctamente");
							}

							@Override
							public void onFailure(Throwable caught) {
								BiblioBase.alerta("Libro no actualizado");
							}
						});
				borrarLibro();
			}
		});

		botones.add(this.editar);
		botones.add(this.guardarEdicion);
		botones.add(this.cancelarEdicion);
		botones.add(this.borrarLibro);
		super.add(botones);

		this.initAdmin();
	}

	
	protected void initAdmin() {
		super.init();
		this.reservar.setEnabled(super.libro.getCopiasExistentes() > 0);
		editableAdmin(false);
	}

	private void actualizaLibro() {
		libro.setTitulo(titulo.getText());

		List<String> listaAutores = new ArrayList<>();
		for (String s : autores.getText().split(", "))
			listaAutores.add(s);
		libro.setAutores(listaAutores);

		libro.setCopiasExistentes(java.lang.Integer.parseInt(nCopias.getText()));
		libro.setEdicion(Integer.parseInt(edicion.getText())); 
		libro.setFechaPublicacion(fechaPublicacion.getText()); 
		libro.setFoto(foto_url.getText());
		libro.setMateria(materia.getText());
		libro.setPaginas(Integer.parseInt(nPags.getText()));
		libro.setResumen(resumen.getText());
		libro.setUrl(url.getText());

		BiblioBase.biblioBaseService.actualizaLibro(libro,
				new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						BiblioBase.alerta("Libro actualizado correctamente");
					}

					@Override
					public void onFailure(Throwable caught) {
						BiblioBase.alerta("Libro no actualizado");
					}
				});
		this.initAdmin();

	}

	protected void editableAdmin(boolean editable) {
		super.editable(editable);
		if (editable) {
			this.editar.setEnabled(false);
			this.guardarEdicion.setVisible(true);
			this.cancelarEdicion.setVisible(true);
			this.reservar.setEnabled(false);
			this.devolver.setEnabled(false);
			this.borrarLibro.setVisible(true);
		} else {

			this.editar.setEnabled(true);
			this.guardarEdicion.setVisible(false);
			this.cancelarEdicion.setVisible(false);
			this.reservar.setEnabled(super.libro.getCopiasExistentes() > 0);
			this.devolver.setEnabled(true);
			this.borrarLibro.setVisible(false);

		}
		this.isbn.setReadOnly(true);
	}

}

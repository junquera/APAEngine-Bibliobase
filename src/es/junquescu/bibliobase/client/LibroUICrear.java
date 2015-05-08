package es.junquescu.bibliobase.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;

import es.junquescu.bibliobase.server.Libro;

public class LibroUICrear extends LibroUI {

	HorizontalPanel gestion = new HorizontalPanel();
	Button guardarEdicion = new Button("Guardar");
	Button cancelarEdicion = new Button("Cancelar");

	public LibroUICrear() {

		super(new Libro());
		super.editable(true);
		this.isbn.setReadOnly(false);

		guardarEdicion.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				for (TextBox tb : editables) {
					if (tb.getText().isEmpty()) {
						BiblioBase
								.alerta("Hay que completar todos los campos.");
						return;
					}
				}
				libro.setTitulo(titulo.getText());

				List<String> listaAutores = new ArrayList<>();
				for (String s : autores.getText().split(", "))
					listaAutores.add(s);
				libro.setAutores(listaAutores);

				libro.setCopiasExistentes(java.lang.Integer.parseInt(nCopias
						.getText()));
				libro.setEdicion(Integer.parseInt(edicion.getText()));
				libro.setFechaPublicacion(fechaPublicacion.getText()); 
				libro.setFoto(foto_url.getText());
				libro.setMateria(materia.getText());
				libro.setPaginas(Integer.parseInt(nPags.getText()));
				libro.setResumen(resumen.getText());
				libro.setUrl(url.getText());
				libro.setIsbn(isbn.getText());

				BiblioBase.biblioBaseService.addLibro(libro,
						new AsyncCallback<Boolean>() {

							@Override
							public void onSuccess(Boolean result) {
								BiblioBase
										.alerta("Libro actualizado correctamente");
								clear();
								BiblioBase.cargarListaLibrosAdmin();
							}

							@Override
							public void onFailure(Throwable caught) {
								BiblioBase.alerta("Libro no actualizado");
							}
						});

			}
		});

		cancelarEdicion.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clear();
			}
		});

		gestion.add(guardarEdicion);
		gestion.add(cancelarEdicion);
		super.add(gestion);
	}

}

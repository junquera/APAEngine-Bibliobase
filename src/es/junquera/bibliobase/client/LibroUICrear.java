package es.junquera.bibliobase.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

import es.junquera.bibliobase.server.Libro;

public class LibroUICrear extends LibroUI {
	

	HorizontalPanel gestion = new HorizontalPanel();
	Button guardarEdicion = new Button("Guardar");
	Button cancelarEdicion = new Button("Cancelar");

	public LibroUICrear() {

		super(new Libro());
		
		this.isbn.setReadOnly(false);

		guardarEdicion.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Libro libro = new Libro();

				libro.setTitulo(titulo.getText());
				
				List<String> listaAutores = new ArrayList<>();
				for (String s : autores.getText().split("\n"))
					listaAutores.add(s);
				libro.setAutores(listaAutores);
				
				libro.setCopiasExistentes(java.lang.Integer.parseInt(nCopias
						.getText()));
				libro.setEdicion(libro.getEdicion()); //
				libro.setFechaPublicacion(libro.getFechaPublicacion()); //
				libro.setFoto(foto_url.getText());
				libro.setMateria(materia.getText());
				libro.setPaginas(Integer.parseInt(nPags.getText()));
				libro.setResumen(resumen.getText());
				libro.setUrl(url.getText());

				BiblioBase.biblioBaseService.addLibro(libro,
						new AsyncCallback<Boolean>() {

							@Override
							public void onSuccess(Boolean result) {
								BiblioBase
										.alerta("Libro actualizado correctamente");
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
				
			}
		});

		gestion.add(guardarEdicion);
		gestion.add(cancelarEdicion);
	}

}

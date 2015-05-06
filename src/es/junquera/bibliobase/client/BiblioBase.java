package es.junquera.bibliobase.client;

import java.util.ArrayList;

import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import es.junquera.bibliobase.server.Libro;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BiblioBase implements EntryPoint {
	Libro l;

	private void creaLibro() {
		l = new Libro();
		l.setTitulo("Juan Carlos Monedero: Famoso en el mundo entero");
		l.setAutores(new ArrayList<String>());
		l.setPaginas(0);
		l.setResumen("Se le quedó estrecho...");
		l.setFechaPublicacion("02/05/2015");
		l.setEdicion(0);
		l.setIsbn("P0D3M05");
		l.setUrl("https://pbs.twimg.com/media/Bw9J_XJCcAAiKKC.jpg");
		l.setMateria("CCCP");
		l.setFoto("https://pbs.twimg.com/media/Bw9J_XJCcAAiKKC.jpg");
		l.setCopiasExistentes(1);
	}

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */

	private final BiblioBaseServiceAsync biblioBaseService = GWT
			.create(BiblioBaseService.class);

	private void alerta(String alerta) {
	
	}

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		creaLibro();

		Button b = new Button();
		b.setText("CARGAR EN DB");
		b.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				biblioBaseService.addLibro(l, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						RootPanel.get("debug").add(
								new HTML("Fallo en addLibro"));
					}

					@Override
					public void onSuccess(Boolean result) {
						if (!result)
							alerta("El libro ya exite en la base de datos");
					}
				});
			}
		});
		RootPanel.get("cargar").add(b);

		Button b2 = new Button();
		b2.setText("CARGAR DE DB");
		b2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				biblioBaseService.getListaLibros(new AsyncCallback<Libro[]>() {

					@Override
					public void onFailure(Throwable caught) {
						RootPanel.get("debug").add(
								new HTML("Fallo en getListaLibros"));
					}

					@Override
					public void onSuccess(Libro[] result) {
						for (Libro libro : result)
							RootPanel.get("libros").add(new LibroUI(libro));
						RootPanel.get("debug").add(new HTML("Conseguido ;D"));
					}
				});
			}
		});
		RootPanel.get("cargar").add(b2);

		Button b3 = new Button();
		b3.setText("CARGAR SIN DB");
		b3.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("libros").add(new LibroUI(l));
			}
		});
		RootPanel.get("cargar").add(b3);

		Button ba = new Button();
		ba.setText("Alerta");
		ba.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				alerta("¡ALERTA!");
			}
		});
		RootPanel.get("cargar").add(ba);

	}
}

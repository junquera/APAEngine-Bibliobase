package es.junquera.bibliobase.client;

import java.util.ArrayList;

import com.google.api.server.spi.auth.common.User;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.oauth.OAuthService;
import com.google.appengine.api.oauth.OAuthServiceFactory;
import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.Element;
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
		l.setUrl("");
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

	protected static final BiblioBaseServiceAsync biblioBaseService = GWT
			.create(BiblioBaseService.class);

	protected static void alerta(String alerta) {
		RootPanel.get("debug").clear();
		RootPanel.get("debug").add(new HTML(alerta));
	}

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		final ToggleButton administrar = new ToggleButton("Ver como administrador", "Ver como usuario");
		administrar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(administrar.isDown())
					cargarListaLibros(true);
				else
					cargarListaLibros(false);
			}
		});

		RootPanel.get("cargar").add(administrar);

		Button b = new Button();
		b.setText("Crear nuevo libro");
		b.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				PopupPanel pp = new PopupPanel();
				pp.add(new LibroUICrear());

			}
		});
		RootPanel.get("cargar").add(b);

		Button reload = new Button();
		reload.setText("Actualizar lista de libros");
		reload.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				cargarListaLibros(administrar.isDown());
			}
		});

		RootPanel.get("cargar").add(reload);

		cargarListaLibros(administrar.isDown());
	}

	public void cargarListaLibros(final boolean administrador) {
		biblioBaseService.getListaLibros(new AsyncCallback<Libro[]>() {

			@Override
			public void onFailure(Throwable caught) {
				alerta("Fallo en getListaLibros");
			}

			@Override
			public void onSuccess(Libro[] result) {
				RootPanel.get("libros").clear();
				for (Libro libro : result)
					if(administrador)
						RootPanel.get("libros").add(new LibroUIAdmin(libro));
					else
						RootPanel.get("libros").add(new LibroUI(libro));

				alerta("Conseguido ;D");
			}
		});
	}

}

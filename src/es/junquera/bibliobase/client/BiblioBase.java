package es.junquera.bibliobase.client;

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

	Button cargar = new Button();

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */

	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(
			"Por favor inicie sesión para utilizar nuestro sistema de biblioteca.");
	private Anchor signInLink = new Anchor("Sign In");

	protected static final BiblioBaseServiceAsync biblioBaseService = GWT
			.create(BiblioBaseService.class);

	protected static final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	protected static void alerta(String alerta) {
		RootPanel.get("debug").clear();
		RootPanel.get("debug").add(new HTML(alerta));
	}

	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("login").add(loginPanel);
	}

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {

		loginService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(LoginInfo result) {
						loginInfo = result;
						if (!loginInfo.isLoggedIn()) {
							loadLogin();
						} else {
							build();
						}

					}
				});

	}

	public void build() {

		final ToggleButton administrar = new ToggleButton(
				"Ver como administrador", "Ver como usuario");
		administrar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (administrar.isDown())
					cargarListaLibros(true);
				else
					cargarListaLibros(false);
			}
		});

		RootPanel.get("cargar").add(administrar);

		cargar.setVisible(false);
		cargar.setText("Crear nuevo libro");
		cargar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("libros").add(new LibroUICrear());
			}
		});

		RootPanel.get("cargar").add(cargar);

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
					if (administrador) {
						RootPanel.get("libros").add(new LibroUIAdmin(libro));
						cargar.setVisible(true);
					} else {
						RootPanel.get("libros").add(new LibroUI(libro));
						cargar.setVisible(false);
					}
				alerta("Conseguido ;D");
			}
		});
	}

	public static void cargarListaLibrosAdmin() {
		biblioBaseService.getListaLibros(new AsyncCallback<Libro[]>() {

			@Override
			public void onFailure(Throwable caught) {
				alerta("Fallo en getListaLibros");
			}

			@Override
			public void onSuccess(Libro[] result) {
				RootPanel.get("libros").clear();
				for (Libro libro : result)
					RootPanel.get("libros").add(new LibroUIAdmin(libro));
				alerta("Conseguido ;D");
			}
		});
	}
}

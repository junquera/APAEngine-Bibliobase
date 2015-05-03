package es.junquera.bibliobase.client;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.tools.ant.types.resources.selectors.Date;

import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import es.junquera.bibliobase.PMF;
import es.junquera.bibliobase.server.Libro;
import es.junquera.bibliobase.server.Probando;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BiblioBase implements EntryPoint {
	Libro l = new Libro("Juan Carlos Monedero: Famoso en el mundo entero", new ArrayList<String>(), 0, "Se le quedó estrecho...", new java.util.Date(2, 5, 2015), 0, "P0D3M05", "https://pbs.twimg.com/media/Bw9J_XJCcAAiKKC.jpg", "CCCP", "https://pbs.twimg.com/media/Bw9J_XJCcAAiKKC.jpg", 1);
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
	/*
	 * private final BiblioBaseServiceAsync biblioBaseService = GWT
	 * .create(BiblioBaseService.class);
	 */

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel.get("libros").add(new LibroUI(l));

	}
}

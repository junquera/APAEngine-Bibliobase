package es.junquera.bibliobase.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.junquera.bibliobase.server.Libro;

public interface BiblioBaseServiceAsync {

	void addLibro(Libro libro, AsyncCallback<List<Libro>> callback);

	void reservaLibro(Libro libro, AsyncCallback<List<Libro>> callback);

	void actualizaLibro(Libro libro, AsyncCallback<List<Libro>> callback);

	void borraLibro(Libro libro, AsyncCallback<List<Libro>> callback);

	void getListaLibros(Void v, AsyncCallback<Libro[]> callback);
	
	void demo(Void v, AsyncCallback<Libro> callback);
}

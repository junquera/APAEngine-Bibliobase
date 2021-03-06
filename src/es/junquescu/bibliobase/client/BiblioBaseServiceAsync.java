package es.junquescu.bibliobase.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.junquescu.bibliobase.server.Libro;

public interface BiblioBaseServiceAsync {

	void addLibro(Libro libro, AsyncCallback<Boolean> callback);

	void reservaLibro(Libro libro, AsyncCallback<Boolean> callback);

	void actualizaLibro(Libro libro, AsyncCallback<Boolean> callback);

	void borraLibro(Libro libro, AsyncCallback<Boolean> callback);

	void getListaLibros(AsyncCallback<Libro[]> callback);

	void getLibro(String isbn, AsyncCallback<Libro> callback);

}

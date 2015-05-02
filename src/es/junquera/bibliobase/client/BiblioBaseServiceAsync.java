package es.junquera.bibliobase.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.junquera.bibliobase.server.libro.Libro;

public interface BiblioBaseServiceAsync {

	void addLibro(Libro libro, AsyncCallback<Boolean> callback);

	void reservaLibro(Libro libro, AsyncCallback<Boolean> callback);

	void actualizaLibro(Libro libro, AsyncCallback<Boolean> callback);

	void borraLibro(Libro libro, AsyncCallback<Boolean> callback);

	void getListaLibros(Void v, AsyncCallback<Libro[]> callback);

}

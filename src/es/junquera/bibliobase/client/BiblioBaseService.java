package es.junquera.bibliobase.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.junquera.bibliobase.server.Libro;

@RemoteServiceRelativePath("/service")
public interface BiblioBaseService extends RemoteService {
	boolean addLibro(Libro libro);

	boolean reservaLibro(Libro libro);

	boolean actualizaLibro(Libro libro);

	boolean borraLibro(Libro libro);

	Libro[] getListaLibros(Void v);

}

package es.junquera.bibliobase.server;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.*;

@SuppressWarnings("serial")
@PersistenceCapable
public class Libro implements Serializable {

	@Persistent
	private String titulo;
	@Persistent
	private List<String> autores;
	@Persistent
	private int edicion;
	@Persistent
	private String resumen;
	@Persistent
	private String fechaPublicacion;
	@Persistent
	private int paginas;
	@PrimaryKey
	@Persistent
	private String isbn;
	@Persistent
	private String url;
	@Persistent
	private String materia;
	@Persistent
	private String foto;
	@Persistent
	private int copiasExistentes;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<String> getAutores() {
		return autores;
	}

	public void setAutores(List<String> autores) {
		this.autores = autores;
	}

	public int getEdicion() {
		return edicion;
	}

	public void setEdicion(int edicion) {
		this.edicion = edicion;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getCopiasExistentes() {
		return copiasExistentes;
	}

	public void setCopiasExistentes(int copiasExistentes) {
		this.copiasExistentes = copiasExistentes;
	}

	public Libro() {
	}

	public void reserva() throws BiblioException {
		synchronized (this) {
			if (copiasExistentes > 0)
				copiasExistentes--;
			else
				throw new BiblioException("No hay suficientes libros");
		}
	}

	public void add(int n) {
		synchronized (this) {
			copiasExistentes += n;
		}
	}

	public void devuelve() {
		synchronized (this) {
			copiasExistentes++;
		}
	}

}

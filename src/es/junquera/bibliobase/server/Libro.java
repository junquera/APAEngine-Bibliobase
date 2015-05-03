package es.junquera.bibliobase.server;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Libro {
	@Persistent
	private String titulo;
	@Persistent
	private List<String> autores;
	@Persistent
	private int edicion;
	@Persistent
	private String resumen;
	@Persistent
	private Date fechaPublicacion;
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

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
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

	public Libro(String titulo, List<String> autores, int edicion,
			String resumen, Date fechaPublicacion, int paginas, String isbn,
			String url, String materia, String foto, int copiasExistentes) {
		this.titulo = titulo;
		this.autores = autores;
		this.edicion = edicion;
		this.resumen = resumen;
		this.fechaPublicacion = fechaPublicacion;
		this.paginas = paginas;
		this.isbn = isbn;
		this.url = url;
		this.materia = materia;
		this.foto = foto;
		this.copiasExistentes = copiasExistentes;
	}

	public void reserva() throws BiblioException {
		synchronized (this) {
			if (copiasExistentes > 0)
				copiasExistentes += 1;
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
package es.junquera.bibliobase.server

import java.util.Date
import java.net.URL
import javax.persistence._
import javax.jdo.annotations._
import es.junquera.bibliobase.BiblioException

@PersistenceCapable
class Libro(@Persistent var titulo: String,
            @Persistent var Autores: List[String],
            @Persistent var edicion: Integer,
            @Persistent var resumen: String,
            @Persistent var fechaPublicacion: Date,
            @Persistent var paginas: Integer,
            @PrimaryKey @Persistent val isbn: String,
            @Persistent var url: URL,
            @Persistent var materia: String,
            @Persistent var foto: URL,
            @Persistent var copiasExistentes: Integer) {

  def reserva() = {
    synchronized {
      if (copiasExistentes.>(0))
        copiasExistentes.-=(1)
      else
        throw new BiblioException("No hay suficientes libros")
    }
  }

  def add(n: Integer) = {
    synchronized {
      copiasExistentes.+=(n)
    }
  }

  def devuelve() = {
    add(1)
  }
  
}
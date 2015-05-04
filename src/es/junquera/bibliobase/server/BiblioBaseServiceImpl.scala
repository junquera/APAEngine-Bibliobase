package es.junquera.bibliobase.server

import com.google.gwt.user.server.rpc.RemoteServiceServlet

import javax.jdo._

import es.junquera.bibliobase.PMF
import es.junquera.bibliobase.client.BiblioBaseService

class BiblioBaseServiceImpl extends RemoteServiceServlet with BiblioBaseService {

  def addLibro(libro: Libro): Boolean = {
    val pm: PersistenceManager = PMF.get().getPersistenceManager();
    try {
      pm.getObjectById(libro.getIsbn())
    } catch {
      case e: Exception =>
        pm.makePersistent(libro)
        return true
    } finally {
      pm.close();
    }
    return false
  }

  def reservaLibro(libro: Libro): Boolean = {
    val pm: PersistenceManager = PMF.get().getPersistenceManager();
    try {
      val l = pm.getObjectById(libro.getIsbn())
    } finally {
      pm.close();
    }
    return true
  }

  def actualizaLibro(libro: Libro): Boolean = {
    val pm: PersistenceManager = PMF.get().getPersistenceManager();
    try {
      val l: Libro = pm.getObjectById(libro.getIsbn()).asInstanceOf[Libro];
      pm.deletePersistent(l)
      pm.makePersistent(libro)
    } finally {
      pm.close();
    }
    return true
  }

  def borraLibro(libro: Libro): Boolean = {
    val pm: PersistenceManager = PMF.get().getPersistenceManager()
    try {
      pm.deletePersistent(libro)
    } finally {
      pm.close();
    }
    return true
  }

  def getListaLibros(): Array[Libro] = {
    val pm: PersistenceManager = PMF.get().getPersistenceManager()
    val query: Query = pm.newQuery(classOf[Libro])
    query.setFilter("'*'")
    val libros: Array[Libro] = (query.execute().asInstanceOf[List[Libro]]).toArray
    query.closeAll()
    return libros
  }

}
package es.junquera.bibliobase.server

import com.google.gwt.user.server.rpc.RemoteServiceServlet
import javax.jdo._
import es.junquera.bibliobase.PMF
import es.junquera.bibliobase.client.BiblioBaseService
import scala.collection.JavaConversions._

class BiblioBaseServiceImpl extends RemoteServiceServlet with BiblioBaseService {

  def addLibro(libro: Libro): Boolean = {
    val pm: PersistenceManager = PMF.get().getPersistenceManager();
    try {
      val l = pm.getObjectById(classOf[Libro], libro.getIsbn())
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
      val l = pm.getObjectById(classOf[Libro], libro.getIsbn())
    } finally {
      pm.close();
    }
    return true
  }

  def actualizaLibro(libro: Libro): Boolean = {
    val pm: PersistenceManager = PMF.get().getPersistenceManager();
    try {
      val l: Libro = pm.getObjectById(classOf[Libro], libro.getIsbn()).asInstanceOf[Libro];
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
    var result: java.util.List[Libro] = query.execute().asInstanceOf[java.util.List[Libro]]
    result = pm.detachCopyAll(result).asInstanceOf[java.util.List[Libro]]
    pm.close()
    val libros: Array[Libro] = new Array[Libro](result.size)
    for (i: Int <- 0 to result.size - 1)
      libros.update(i, result.get(i))
    return libros
  }

}
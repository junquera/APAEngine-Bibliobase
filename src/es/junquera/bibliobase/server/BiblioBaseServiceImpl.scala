package es.junquera.bibliobase.server

import com.google.gwt.user.server.rpc.RemoteServiceServlet

import javax.jdo.JDOHelper
import javax.jdo.PersistenceManager
import javax.jdo.PersistenceManagerFactory
import javax.jdo.Query

import es.junquera.bibliobase.PMF
import es.junquera.bibliobase.client.BiblioBaseService
import es.junquera.bibliobase.server.libro.Libro

class BiblioBaseServiceImpl extends RemoteServiceServlet with BiblioBaseService {

  def addLibro(libro: Libro): Boolean = {
    val pm: PersistenceManager = PMF.get().getPersistenceManager();
    try {
      if (pm.getObjectById(libro.getIsbn()) == Nil)
        pm.makePersistent(libro)
    } finally {
      pm.close();
    }
    return true
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

  def getListaLibros(v: Void): Array[Libro] = {
    val pm: PersistenceManager = PMF.get().getPersistenceManager()
    val query: Query = pm.newQuery(classOf[Libro])
    query.setFilter("*")
    val libros: Array[Libro] = (query.execute().asInstanceOf[List[Libro]]).toArray
    query.closeAll()
    return libros
  }

}
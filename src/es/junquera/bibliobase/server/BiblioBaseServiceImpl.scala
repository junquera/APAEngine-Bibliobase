package es.junquera.bibliobase.server

import com.google.gwt.user.server.rpc.RemoteServiceServlet

import javax.jdo.JDOHelper
import javax.jdo.PersistenceManager
import javax.jdo.PersistenceManagerFactory
import javax.jdo.Query

import es.junquera.bibliobase.PMF;
import es.junquera.bibliobase.client.BiblioBaseService

class BiblioBaseServiceImpl extends RemoteServiceServlet with BiblioBaseService {

  def addLibro(libro: Libro): Boolean = {
    val pm: PersistenceManager = PMF.get().getPersistenceManager();
    try {
      if (pm.getObjectById(libro.isbn) == Nil)
        pm.makePersistent(libro)
    } finally {
      pm.close();
    }
    return true
  }
  
  def demo(): Libro = {
    return  new Libro("PRUEBA1", null, null, null, null, null, null, null, null, null, null)
  }

  def reservaLibro(libro: Libro): Boolean = {
    val pm: PersistenceManager = PMF.get().getPersistenceManager();
    try {
      val l = pm.getObjectById(libro.isbn)
    } finally {
      pm.close();
    }
    return true
  }

  def actualizaLibro(libro: Libro): Boolean = {
    val pm: PersistenceManager = PMF.get().getPersistenceManager();
    try {
      val l: Libro = pm.getObjectById(libro.isbn).asInstanceOf[Libro];
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

  def getListaLibros: Array[Libro] = {
    val pm: PersistenceManager = PMF.get().getPersistenceManager()
    val query: Query = pm.newQuery(classOf[Libro])
    query.setFilter("*")
    val libros:Array[Libro] = (query.execute().asInstanceOf[List[Libro]]).toArray
    query.closeAll()
    return libros
  }

}
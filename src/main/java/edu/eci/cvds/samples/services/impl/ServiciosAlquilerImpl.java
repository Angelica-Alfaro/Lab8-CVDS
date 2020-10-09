package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.guice.transactional.Transactional;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;
   
   @Inject
   private ClienteDAO clienteDAO;
   
   @Override
   public long valorMultaRetrasoxDia(int itemId) throws ExcepcionServiciosAlquiler, PersistenceException {
	   Item item = itemDAO.load(itemId);
	   if (item ==null) {
		   throw new ExcepcionServiciosAlquiler("El item no esta registrado");
	   } 
	   else {
           return item.getTarifaxDia();
       }
   }

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
	   try {
           return clienteDAO.load(docu);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el cliente "+docu,ex);
       }
   }
   
   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
	   Cliente cliente = consultarCliente(idcliente);
       if (cliente==null) {
    	   throw new ExcepcionServiciosAlquiler("El cliente no esta registrado");
       }
       else {
    	   return consultarCliente(idcliente).getRentados();
       }
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
	   try {
           return clienteDAO.load();
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar los clientes ",ex);
       }
   }

   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
	   try {
           return itemDAO.load(id);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item",ex);
       }
   }

   @Override
   public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler {
	   try {
           return itemDAO.consultarItems();
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar los items ",ex);
       }
   }

   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
	   throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   @Transactional
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
	   LocalDate actual=date.toLocalDate();
       LocalDate entrega=actual.plusDays(numdias);
	   try {
           clienteDAO.registrarAlquiler(docu,item.getId(),date,java.sql.Date.valueOf(entrega));
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al registrar alquiler del cliente",ex);
       }
   }

   @Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
       try {
           clienteDAO.save(c);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al registrar el cliente "+c,ex);
       }
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
	   Item item=consultarItem(iditem);
	   if(numdias<1) {
    	   throw new ExcepcionServiciosAlquiler("Error al consultar el item") ;
       }
	   if(item == null) {
		   throw new ExcepcionServiciosAlquiler("Error al consultar el item") ;
	   }
       else {
    	   return item.getTarifaxDia()*numdias;
       }
   }

   @Override
   @Transactional
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }
   
   @Override
   @Transactional
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
	   try {
           itemDAO.save(i);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al registrar el item"+i,ex);
       }
   }

   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
}
package edu.eci.cvds.sampleprj.dao;

import java.sql.Date;
import java.util.List;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;

public interface ClienteDAO {
	
	public void save(Cliente cl) throws PersistenceException;

	public Cliente load(long id) throws PersistenceException;
	
	public List<Cliente> load() throws PersistenceException;
	
	public void registrarAlquiler(long docu, int item, Date dateInicio, Date dateFin) throws PersistenceException;

}

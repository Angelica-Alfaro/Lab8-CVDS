package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;

public interface ClienteDAO {
	
	public void save(Cliente cl) throws PersistenceException;

	public Cliente load(long id) throws PersistenceException;

}

package edu.eci.cvds.sampleprj.dao;

import org.mybatis.guice.transactional.Transactional;

import edu.eci.cvds.samples.entities.Item;

public interface ItemDAO {
	
	@Transactional
	public void save(Item it) throws PersistenceException;
	
	public Item load(int id) throws PersistenceException;

}

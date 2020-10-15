

	package edu.eci.cvds.test;

	import java.sql.Date;
	import java.text.SimpleDateFormat;
	import java.time.LocalDate;
	import java.util.ArrayList;
	import java.util.List;

	import com.google.inject.Inject;
	import edu.eci.cvds.sampleprj.dao.PersistenceException;
	import edu.eci.cvds.samples.entities.Cliente;
	import edu.eci.cvds.samples.entities.Item;
	import edu.eci.cvds.samples.entities.ItemRentado;
	import edu.eci.cvds.samples.entities.TipoItem;
	import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
	import edu.eci.cvds.samples.services.ServiciosAlquiler;
	import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
	import org.apache.ibatis.session.SqlSession;
	import org.junit.Before;
	import org.junit.Test;
	import org.junit.Assert;

	public class ServiciosAlquilerTest {

	    @Inject
	    private SqlSession sqlSession;

	    ServiciosAlquiler serviciosAlquiler;

	    public ServiciosAlquilerTest() {
	        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
	    }
	    
	    //Consultar el valor de la multa por retraso de un item inexistente.
	    @Test
	    public void valorMultaRetrasoxDiaNoExisteItemTest() throws PersistenceException{
	        try{
	            serviciosAlquiler.valorMultaRetrasoxDia(30);
	        }
	        catch (Exception e ){
	            Assert.assertEquals(null,e.getMessage());
	        }
	    }
	    
	    
	    //Consultar el valor de la multa por retraso de un item existente
	    @Test
	    public void valorMultaRetrasoxDiaExistentesTest(){
	        try{
	        	Assert.assertEquals(serviciosAlquiler.valorMultaRetrasoxDia(13),1998);
	        }
	        catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    //Consultar cliente que no existe.
	    @Test
	    public void consultarClienteNoExistenteTest() {
	    	try{
	            serviciosAlquiler.consultarCliente(46);
	        }
	        catch (ExcepcionServiciosAlquiler e ){
	            Assert.assertEquals("Error al consultar el cliente ",e.getMessage());
	        }
	        catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    //Consultar cliente que existe.
	    @Test
	    public void consultarClienteExisteTest() {
	    	try{
	    		Assert.assertEquals(serviciosAlquiler.consultarCliente(30).getEmail(),"wvhneivjbn");
	        }
	        catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    //Consultar items de cliente no existente.
	    @Test
	    public void consultarItemsClienteNoExisteTest() {
	    	try{
	            serviciosAlquiler.consultarItemsCliente(47);
	        }
	        catch (ExcepcionServiciosAlquiler e ){
	            Assert.assertEquals("El cliente no esta registrado",e.getMessage());
	        }
	        catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    //Consultar items de cliente existente.
	    @Test
	    public void consultarItemsClienteExistenteTest() {
	    	try{
	            serviciosAlquiler.consultarItemsCliente(20);
	            Assert.assertEquals(serviciosAlquiler.consultarCliente(20).getDocumento(),20);
	    	}catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    //Consultar item no existente.
	    @Test
	    public void consultarItemNoExisteTest() {
	    	try{
	            serviciosAlquiler.consultarItem(17);
	        }
	        catch (ExcepcionServiciosAlquiler e ){
	            Assert.assertEquals("Error al consultar el item",e.getMessage());
	        }
	        catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    //Consultar item existente.
	    @Test
	    public void consultarItemExisteTest() {
	    	try{
	            Assert.assertEquals(serviciosAlquiler.consultarItem(13).getId(), 13);
	    	}catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    //Registrar alquiler del cliente.
	    @Test
	    public void registrarAlquilerClienteInvalidoTest() {
	    	try{
	    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    		Item it = new Item(new TipoItem(100,"Drama"),2026,"Si decido quedarme","Historia de amor",dateFormat.parse("2026-07-25"),5000, "Compra","Romance");
	    		serviciosAlquiler.registrarItem(it);
	    		LocalDate at =LocalDate.parse("2019-09-28");
	    		serviciosAlquiler.registrarAlquilerCliente(Date.valueOf(at),19985L, it, 10);
	        }
	        catch (Exception e){
	        	Assert.assertEquals("Error al registarar el item 2026",e.getMessage());
	        }
	    }
	    
	    //Registrar un cliente.
	    @Test
	    public void registrarClienteValidoTest() {
	    	try{
	    		serviciosAlquiler.registrarCliente(new Cliente("Pepito Perez",672375,"32222","narnia3","pepitoperez@yahoo"));
	    		String email = serviciosAlquiler.consultarCliente(672375).getEmail();
	    		
	            Assert.assertEquals(email,"pepitoperez@yahoo");
	        }
	        catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    //Consulta costo de alquiler de un item no existente
	    @Test
	    public void consultarCostoAlquilerNoExistenteTest(){
	        try{
	            serviciosAlquiler.consultarCostoAlquiler(89,2);
	        }
	        catch (ExcepcionServiciosAlquiler e ){
	            Assert.assertEquals("Error al consultar el item",e.getMessage());
	        }
	        catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	    }
	     
	    //Consulta costo de alquiler cuando el número de dias en menor a cero.
	    @Test
	    public void consultarCostoAlquilerNumDiasMenorCero(){
	        try{
	            serviciosAlquiler.consultarCostoAlquiler(92,-4);
	        }
	        catch (ExcepcionServiciosAlquiler e ){
	            Assert.assertEquals("Error al consultar el item" ,e.getMessage());
	        }
	        catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    //Consultar costo de alquiler de un item existente.
	    @Test
	    public void consultarCostoAlquilerExistente(){
	        try{
	            Assert.assertEquals(serviciosAlquiler.consultarCostoAlquiler(99,1),99);
	        }
	        catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    //Registrar un item.
	    @Test
	    public void registrarItemValidoTest() {
	    	try{
	    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    		Item it = new Item(new TipoItem(90,"Aventura"),2027,"Si decido quedarme","Historia de amor",dateFormat.parse("2026-07-25"),5000, "Compra","Romance");
	    		serviciosAlquiler.registrarItem(it);
	            Assert.assertEquals(serviciosAlquiler.consultarItem(2027).getDescripcion(),"Historia de amor");
	        }
	        catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	    }
	    //@Test
	    public void emptyDB() {
	        for(int i = 0; i < 100; i += 10) {
	            boolean r = false;
	            try {
	                Cliente cliente = serviciosAlquiler.consultarCliente(8);
	            } catch(ExcepcionServiciosAlquiler e) {
	                r = true;
	            } catch(IndexOutOfBoundsException e) {
	                r = true;
	            }
	            // Validate no Client was found;
	            Assert.assertTrue(r);
	        }
	    }     
	}

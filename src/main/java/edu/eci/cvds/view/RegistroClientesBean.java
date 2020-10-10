<<<<<<< HEAD
package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.sql.Date;
import java.util.List;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;

@ManagedBean(name="AlquilerItemsBean")
@SessionScoped
public class RegistroClientesBean extends BasePageBean{
    private Cliente cliente = null;
    private List<Cliente> clientes= null;
    @Inject
    private ServiciosAlquiler servicios;
    

    public Cliente consultarCliente (long docu){
        try{
        cliente = servicios.consultarCliente(docu);
        }catch(ExcepcionServiciosAlquiler excepcionServiciosAlquiler ){
        
        }
        return cliente;
    }

    public List<Cliente> consultaClientes () {
        try{
        clientes =servicios.consultarClientes();
        }catch(ExcepcionServiciosAlquiler excepcionServiciosAlquiler){

        }
        return clientes;
    }

    public void registrarCliente(String nombre, long documento, String telefono, String direccion, String email){
        try{
        cliente = new Cliente(nombre,documento,telefono,direccion,email);
        servicios.registrarCliente(cliente);
        }catch(ExcepcionServiciosAlquiler excepcionServiciosAlquiler){

        }

    }




}

=======
package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.sql.Date;
import java.util.List;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;

@ManagedBean(name="AlquilerItemsBean")
@SessionScoped
public class RegistroClientesBean extends BasePageBean{
    private Cliente cliente = null;
    private List<Cliente> clientes= null;
    @Inject
    private ServiciosAlquiler servicios;
    

    public Cliente consultarCliente (long docu){
        try{
        cliente = servicios.consultarCliente(docu);
        }catch(ExcepcionServiciosAlquiler excepcionServiciosAlquiler ){
        
        }
        return cliente;
    }

    public List<Cliente> consultaClientes () {
        try{
        clientes =servicios.consultarClientes();
        }catch(ExcepcionServiciosAlquiler excepcionServiciosAlquiler){

        }
        return clientes;
    }

    public void registrarCliente(String nombre, long documento, String telefono, String direccion, String email){
        try{
        cliente = new Cliente(nombre,documento,telefono,direccion,email);
        servicios.registrarCliente(cliente);
        }catch(ExcepcionServiciosAlquiler excepcionServiciosAlquiler){

        }

    }




}

>>>>>>> c6df5524df06a38adecbc9bca28842b754c808b0

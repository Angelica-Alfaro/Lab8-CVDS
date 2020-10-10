package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
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


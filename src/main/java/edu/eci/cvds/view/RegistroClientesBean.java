package edu.eci.cvds.view;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;

import java.lang.invoke.InjectedProfile;

import javax.faces.bean.SessionScoped;

import edu.eci.cvds.samples.services.ServiciosAlquiler;
import excepciones.ExcepcionServiciosAlquiler;
import jdk.jfr.internal.PrivateAccess;

@managedBean(name="AlquilerItemsBean")
@SessionScoped
public class RegistroClientesBean extends BasePageBean{
    @Inject
    private ServiciosAlquiler servicios;
    @Inject
    private Cliente clienteusado;

    public Cliente consultarCliente (long docu){
        Cliente cliente = serviciosAlquiler.consultarCliente(docu);
        return cliente;
    }

    public List<Cliente> consultaClientes () {
        List<Cliente> clientes =servicios.consultarClientes();
        return clientes;
    }

    public void registrarCliente(String nombre, long documento, String telefono, String direccion, String email){
        Cliente cliente = new Cliente(nombre,documento,telefono,direccion,email);
        servicios.registrarCliente(cliente);
    }




}


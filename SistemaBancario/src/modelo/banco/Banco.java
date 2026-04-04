
package modelo.banco;

import modelo.abstractas.Cliente;
import modelo.abstractas.Cuenta;
import modelo.abstractas.Empleado;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.ClienteNoEncontradoException;
import modelo.excepciones.DatoInvalidoException;

public class Banco {
    
    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private String nombre;
    private Empleado[] empleados;
    private int empleadosActual = 0; // Contador para índices de array
    private Cliente[] clientes;
    private int clientesActual = 0; // Contador para índices de array
    private Cuenta[] cuentas;
    private int cuentasActual = 0; // Contador para índices de array
    
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public Banco(String nombre){
        setNombre(nombre);
        this.empleados = new Empleado[50]; // Max 50
        this.clientes = new Cliente[200]; // Max 200
        this.cuentas = new Cuenta[500]; // Max 500
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public String getNombre(){ return nombre; }
    
    public Empleado[] getEmpleados(){
        Empleado[] copia = new Empleado[50];
        System.arraycopy(empleados, 0, copia, 0, 50);
        return copia;
    }
    
    public Cliente[] getClientes(){ 
        Cliente[] copia = new Cliente[200];
        System.arraycopy(clientes, 0, copia, 0, 200);
        return copia;
    }
    
    public Cuenta[] getCuentas(){ 
        Cuenta[] copia = new Cuenta[500];
        System.arraycopy(cuentas, 0, copia, 0, 500);
        return copia;
    }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setNombre(String nombre){
        if (nombre == null || nombre.isEmpty()) {
            throw new DatoInvalidoException("Nombre", "Vacio");
        }
        this.nombre = nombre;
    }
    
    // ── MÉTODOS ───────────────────────────────────────────────────────
    public void registrarCliente(Cliente c) throws CapacidadExcedidaException {
        if (clientesActual >= clientes.length){
            throw new CapacidadExcedidaException(200);
        }
        
        for (int i = 0; i < clientesActual; i++) {
            if (clientes[i].getId().equals(c.getId())) {
                throw new DatoInvalidoException("Registrar Cliente", "ID ya existente");
            }
        }

        clientes[clientesActual] = c;
        clientesActual++;
    }
    
    public void registrarEmpleado(Empleado e) throws CapacidadExcedidaException {
        if (empleadosActual >= empleados.length){
            throw new CapacidadExcedidaException(50);
        }
        
        for (int i = 0; i < empleadosActual; i++) {
            if (empleados[i].getId().equals(e.getId())) {
                throw new DatoInvalidoException("Registrar Empleado", "ID ya existente");
            }
        }

        empleados[empleadosActual] = e;
        empleadosActual++;
    }
    
    public void abrirCuenta(String idCliente, Cuenta c){
        try {
            Cliente clienteLocalizado = buscarCliente(idCliente);
            clienteLocalizado.agregarCuenta(c);
        } catch (ClienteNoEncontradoException | CapacidadExcedidaException e) {
            e.getMessage();
        }
    }
    
    public Cliente buscarCliente(String id) throws ClienteNoEncontradoException{
        if (id == null || id.isBlank()) {
            throw new DatoInvalidoException("ID", "Vacio");
        }
        
        Cliente clienteLocalizado = null;
        for (Cliente c : clientes) {
            if (c.getId().equalsIgnoreCase(id)) {
                clienteLocalizado = c;
                break;
            }
        }
        
        if (clienteLocalizado == null) {
            throw new ClienteNoEncontradoException(id);
        }   
        return clienteLocalizado;
    }
    
    public double calcularNominaTotal(){
        double nominaTotal = 0;
        for (int i = 0; i < empleadosActual; i++) {
            nominaTotal += empleados[i].calcularSalario();
        }
        return nominaTotal;
    }
    
    public void calcularInteresesMensuales(){
        // FALTA CÓDIGO
    }
}

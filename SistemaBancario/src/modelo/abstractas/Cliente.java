package modelo.abstractas;
 
import java.time.LocalDate;
import java.time.LocalDateTime;
import modelo.excepciones.BancoRuntimeException;

import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Transaccionable;
 
public abstract class Cliente extends Persona {
 
    // ── ATRIBUTOS ────────────────────────────────────────────
    private static final int maxCuentas = 5; // Máximo de cuentas p/c
    private Cuenta[] cuentas;
    private int totalCuentas;
    private boolean activo;
    private final LocalDateTime fechaCreacion; // final porque nunca se modifica
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
 
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public Cliente(String id, String nombre, String apellido,
                   LocalDate fechaNacimiento, String email) {
        super(id, nombre, apellido, fechaNacimiento, email);
        this.cuentas = new Cuenta[maxCuentas];
        this.totalCuentas = 0; // Por defecto 0
        this.activo = true; // Por defecto el cliente está activo
        this.fechaCreacion = LocalDateTime.now(); 
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = ""; // Ninguna por defecto
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public Cuenta[] getCuentas() {
        Cuenta[] copia = new Cuenta[maxCuentas];
        System.arraycopy(cuentas, 0, copia, 0, maxCuentas);
        return copia;
    }
        
    public int getTotalCuentas() { return totalCuentas; }
    public boolean isActivo() { return activo; }
        // protected porque sólo deben acceder las subclases al método
    protected LocalDateTime getFechaCreacion() { return fechaCreacion; }
    protected LocalDateTime getUltimaModificacion() { return ultimaModificacion; }
    protected String getUsuarioModificacion() { return usuarioModificacion; }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setActivo(boolean activo) { this.activo = activo; }
    
    protected void setUsuarioModificacion(String usuarioModificacion) {
        if (usuarioModificacion == null || usuarioModificacion.isBlank()) {
            throw new DatoInvalidoException("Usuario Modificacion", "Vacio");
        }
        this.usuarioModificacion = usuarioModificacion;
        this.ultimaModificacion  = LocalDateTime.now();
    }
    
    // ── MÉTODOS CONCRETOS ───────────────────────────────────────────────────────
    public void agregarCuenta(Cuenta cuenta) throws CapacidadExcedidaException {
        if (cuenta == null) {
            throw new DatoInvalidoException("Cuenta", "Vacio");
        }
        if (totalCuentas >= maxCuentas) {
            throw new CapacidadExcedidaException(5);
        }
        cuentas[totalCuentas] = cuenta;
        totalCuentas++;
    }
    
    public void depositarACuenta(String idCuenta, double monto) throws CuentaBloqueadaException {
            Cuenta cuentaADepositar = buscarCuenta(idCuenta);
            if (cuentaADepositar == null) {
                throw new BancoRuntimeException("La cuenta a buscar no existe");
            }
            cuentaADepositar.depositar(monto);
    }
    
    public Cuenta buscarCuenta(String idCuenta) throws CuentaBloqueadaException {
        for (int i = 0; i < totalCuentas; i++) {
            if (cuentas[i].getNumeroCuenta().equals(idCuenta)){
                if (cuentas[i].isBloqueada()) {
                    throw new CuentaBloqueadaException();
                }
                return cuentas[i];
            }
        }
        return null;
    }
    
}

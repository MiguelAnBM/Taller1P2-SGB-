package modelo.cuentas;

import java.time.LocalDateTime;
import modelo.abstractas.Cuenta;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import modelo.interfaces.Transaccionable;

public class CuentaCredito extends Cuenta implements Consultable,Transaccionable, Auditable {

    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private double limiteCredito;
    private double tasaInteres;
    private double deudaActual;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public CuentaCredito(double limiteCredito, double tasaInteres, double deudaActual, String numeroCuenta, double saldo, LocalDateTime fechaCreacion, LocalDateTime ultimaModificacion, String usuarioModificacion) {
        super(numeroCuenta, saldo, fechaCreacion, ultimaModificacion, usuarioModificacion);
        this.limiteCredito = limiteCredito;
        this.tasaInteres = tasaInteres;
        this.deudaActual = deudaActual;
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public double getLimiteCredito() { return limiteCredito;}

    public double getTasaInteres() { return tasaInteres; }

    public double getDeudaActual() { return deudaActual; }

    // ── SETTERS ───────────────────────────────────────────────────────
    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public void setDeudaActual(double deudaActual) {
        this.deudaActual = deudaActual;
    }

    // ── MÉTODOS ABSTRACTOS HEREDADOS ───────────────────────────────────────────────────────
    @Override
    public double calcularInteres() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public double getLimiteRetiro() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String getTipoCuenta() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    // ── MÉTODOS DE INTERFACES ───────────────────────────────────────────────────────
    @Override
    public String obtenerResumen() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean estaActivo() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String obtenerTipo() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void depositar(double monto) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void retirar(double monto) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public double calcularComision(double monto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double consultarSaldo() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public LocalDateTime obtenerFechaCreacion() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public LocalDateTime obtenerUltimaModificacion() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String obtenerUsuarioModificacion() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void registrarModificacion(String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
}

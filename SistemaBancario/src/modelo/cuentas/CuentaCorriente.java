package modelo.cuentas;

import java.time.LocalDateTime;
import modelo.abstractas.Cuenta;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import modelo.interfaces.Transaccionable;

public class CuentaCorriente extends Cuenta implements Consultable, Transaccionable, Auditable {

    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private double montoSobregiro;
    private double comisionMantenimiento;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public CuentaCorriente(double montoSobregiro, double comisionMantenimiento, String numeroCuenta, double saldo, LocalDateTime fechaCreacion, LocalDateTime ultimaModificacion, String usuarioModificacion) {
        super(numeroCuenta, saldo, fechaCreacion, ultimaModificacion, usuarioModificacion);
        this.montoSobregiro = montoSobregiro;
        this.comisionMantenimiento = comisionMantenimiento;
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public double getMontoSobregiro() { return montoSobregiro; }

    public double getComisionMantenimiento() { return comisionMantenimiento; }
    
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setMontoSobregiro(double montoSobregiro) {
        this.montoSobregiro = montoSobregiro;
    }

    public void setComisionMantenimiento(double comisionMantenimiento) {
        this.comisionMantenimiento = comisionMantenimiento;
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

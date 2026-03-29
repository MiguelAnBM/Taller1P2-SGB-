package modelo.cuentas;

import java.time.LocalDateTime;
import modelo.abstractas.Cuenta;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import modelo.interfaces.Transaccionable;

public class CuentaAhorros extends Cuenta implements Consultable,Transaccionable, Auditable {

    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private double tasaInteres;
    private int retirosMesActual, maxRetirosmes;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public CuentaAhorros(double tasaInteres, int retirosMesActual, int maxRetirosmes, String numeroCuenta, double saldo, 
            LocalDateTime fechaCreacion, LocalDateTime ultimaModificacion, String usuarioModificacion) {
        super(numeroCuenta, saldo, fechaCreacion, ultimaModificacion, usuarioModificacion);
        this.tasaInteres = tasaInteres;
        this.retirosMesActual = retirosMesActual;
        this.maxRetirosmes = maxRetirosmes;
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public double getTasaInteres() { return tasaInteres;}

    public int getRetirosMesActual() {return retirosMesActual;}

    public int getMaxRetirosmes() {return maxRetirosmes;}

    // ── SETTERS ───────────────────────────────────────────────────────
    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public void setRetirosMesActual(int retirosMesActual) {
        this.retirosMesActual = retirosMesActual;
    }

    public void setMaxRetirosmes(int maxRetirosmes) {
        this.maxRetirosmes = maxRetirosmes;
    }

    
    // ── MÉTODOS ABSTRACTOS HEREDADOS ───────────────────────────────────────────────────────
    @Override
    public double calcularInteres() {
        return (getSaldo() * tasaInteres) / 12;
    }

    @Override
    public double getLimiteRetiro() {
        return 0;
    }

    @Override
    public String getTipoCuenta() {
        return "hola";
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double consultarSaldo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LocalDateTime obtenerFechaCreacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LocalDateTime obtenerUltimaModificacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String obtenerUsuarioModificacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void registrarModificacion(String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

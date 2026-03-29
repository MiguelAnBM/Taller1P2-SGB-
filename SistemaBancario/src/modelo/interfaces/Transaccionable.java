package modelo.interfaces;

public interface Transaccionable {

    // ── MÉTODOS ABSTRACTOS ───────────────────────────────────────────────────────
    void depositar(double monto);
    void retirar(double monto);
    double calcularComision(double monto);
    double consultarSaldo();

}

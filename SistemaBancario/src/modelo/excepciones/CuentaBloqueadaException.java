package modelo.excepciones;

public class CuentaBloqueadaException extends SistemaBancarioException {

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public CuentaBloqueadaException() {
        super("[Error] Esta cuenta se encuentra bloqueada, no puede realizar operaciones");
    }

    // ── MÉTODO SOBREESCRITO ───────────────────────────────────────────────────────
    @Override
    public String toString() {
        return super.toString();
    }

}

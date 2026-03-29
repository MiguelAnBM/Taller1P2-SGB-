package modelo.excepciones;

public class ModeloExcepciones {

    public static void main(String[] args) throws CuentaBloqueadaException {
        try {
            throw new CuentaBloqueadaException("Hola");
        } catch (EstadoTransaccionInvalidoException e) {
            e.printStackTrace();
        }
    }
}

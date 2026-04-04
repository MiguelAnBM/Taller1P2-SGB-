
package principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import modelo.abstractas.Cliente;
import modelo.abstractas.Cuenta;
import modelo.banco.Banco;
import modelo.cuentas.CuentaAhorros;
import modelo.cuentas.CuentaCorriente;
import modelo.cuentas.CuentaCredito;
import modelo.enums.TipoDocumento;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.SaldoInsuficienteException;
import modelo.interfaces.Consultable;
import modelo.interfaces.Transaccionable;
import modelo.personas.ClienteEmpresarial;
import modelo.personas.ClienteNatural;

public class SistemaBancarioDemo {
    
    // Para formatear las fechas y horas
    public static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) {
        
        // DEMOSTRACIÓN DE ESCENARIOS
        
        Banco banco = new Banco("Banco Comfenalco");
    
        // ===================================================================
        //  Registrar al menos 2 clientes naturales y 1 empresarial
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 1 
                           =======================================""");

        ClienteNatural clienteN1 = new ClienteNatural("CN-001", "Miguel", "Benitez", LocalDate.parse("2007-06-14"), 
                                               "benitez@gmail.com", TipoDocumento.CEDULA, "1043974154");
        try {
            banco.registrarCliente(clienteN1);
        } catch (CapacidadExcedidaException e) {
            System.out.println(e.getMessage());
        }
        
        ClienteNatural clienteN2 = new ClienteNatural("CN-002", "Delany", "Mendoza", LocalDate.parse("2007-08-29"), 
                                                "mendoza@gmail.com", TipoDocumento.NIT, "123456789");
        try {
            banco.registrarCliente(clienteN2);
        } catch (CapacidadExcedidaException e) {
            System.out.println(e.getMessage());
        }
        
        ClienteEmpresarial clienteE1 = new ClienteEmpresarial("CE-001", "Belany", "Mendoza", LocalDate.parse("2006-05-15"), 
                                                       "belanym@gmail.com", "123456", "Inversiones Mendoza S.A.S.", "Mario");
        try {
            banco.registrarCliente(clienteE1);
        } catch (CapacidadExcedidaException e) {
            System.out.println(e.getMessage());
        }
        
        // Todo esto es para corroborar que los clientes fueron creados correctamente
        for (Cliente c : banco.getClientes()) {
            if (c == null) break;
            System.out.println("---------------------------------------" + "\n"
                             + "          Cliente Registrado" + "\n"
                             + "ID: " + c.getId() + "\n"
                             + "Cliente: " + c.getNombreCompleto() + "\n"
                             + "Tipo: " + c.obtenerTipo() + "\n"
                             + "FechaNac: " + c.getFechaNacimiento() + "\n"
                             + "Email: " + c.getEmail() + "\n"
                             + c.obtenerDocumentoIdentidad());
            
            if (c instanceof ClienteEmpresarial) {
                ClienteEmpresarial ce = (ClienteEmpresarial) c;
                System.out.println("Razon social: " + ce.getRazonSocial());
                System.out.println("Representante legal: " + ce.getRepresentanteLegal());
            }
        }
        
        System.out.println("---------------------------------------");

        // ===================================================================
        //  Abrir al menos una cuenta de cada tipo (corriente, ahorros, crédito)
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 2
                           =======================================""");

        banco.abrirCuenta("CN-001", new CuentaAhorros("CA-001", 5000000, "Sistema", 0.12, 50));
        banco.abrirCuenta("CN-002", new CuentaCorriente("CC-001", 3000000, "Sistema", 1000000, 25000));
        banco.abrirCuenta("CE-001", new CuentaCredito("CCR-001", 8000000, "Sistema", 4000000, 0.018));
        
        // Todo esto es para corroborar que las cuentas fueron creadas correctamente
        System.out.println("---------------------------------------");
        System.out.println("           CUENTAS CREADAS");
        for (Cliente c : banco.getClientes()) {
            if (c == null) { break; }

            Cuenta[] cuentas = c.getCuentas();
            for (int i = 0; i < c.getTotalCuentas(); i++) {
                Cuenta cuenta = cuentas[i];
                System.out.println(cuenta.obtenerResumen());
            }
        }

        // ===================================================================
        //  Realizar un depósito exitoso y capturar CuentaBloqueadaException 
        //  al intentar depositar en una cuenta bloqueada
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 3
                           =======================================""");
        
        // Depósito de 50.000 exitoso
        try {
            System.out.println("---------------------------------------");
            System.out.println(" Deposito de " + clienteN1.getNombreCompleto() + " ($50.000)");
            Cuenta cuenta = clienteN1.buscarCuenta("CA-001");
            System.out.println("Saldo antes: $" + cuenta.getSaldo());
            
            clienteN1.depositarACuenta("CA-001", 50000);
            System.out.println("Saldo actual: $" + cuenta.getSaldo());
        } catch (CuentaBloqueadaException e) {
            System.out.println(e.getMessage());
        }
        
        // Captura de error
        try {
            System.out.println("---------------------------------------");
            System.out.println("     Captura de error de deposito");
            Cuenta cuenta = clienteN1.buscarCuenta("CA-001");
            cuenta.setBloqueada(true);
            clienteN1.depositarACuenta("CA-001", 25000);
        } catch (CuentaBloqueadaException e) {
            System.out.println(e.getMessage());
        }
        
        // ===================================================================
        //  Realizar un retiro exitoso y capturar SaldoInsuficienteException 
        // al intentar retirar más del saldo disponible
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 4
                           =======================================""");
        // Retiro de 100.000 exitoso
        try {
            System.out.println("---------------------------------------");
            System.out.println("  Retiro de " + clienteN2.getNombreCompleto() + " ($100.000)");
            Cuenta cuenta = clienteN2.buscarCuenta("CC-001");
            System.out.println("Saldo antes: $" + cuenta.getSaldo());
            
            cuenta.retirar(100000);
            System.out.println("Saldo actual: $" + cuenta.getSaldo());
        } catch (SaldoInsuficienteException | CuentaBloqueadaException e) {
            System.out.println(e.getMessage());
        }
        
        // Captura de error
        try {
            System.out.println("---------------------------------------");
            System.out.println("      Captura de error de retiro");
            Cuenta cuenta = clienteN2.buscarCuenta("CC-001");
            cuenta.retirar(10000000);
        } catch (SaldoInsuficienteException | CuentaBloqueadaException e) {
            System.out.println(e.getMessage());
        }
        
        // ===================================================================
        //  Realizar una transferencia entre dos cuentas
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 5
                           =======================================""");
        
        // ===================================================================
        //  Recorrer un array Empleado[] con instancias de los 3 tipos e 
        //  imprimir el resultado de calcularSalario() en cada uno
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 6
                           =======================================""");
        
        // ===================================================================
        //  Recorrer un array Cuenta[] con los 3 tipos e imprimir el resultado 
        //  de calcularInteres() en cada una
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 7
                           =======================================""");
        
        // ===================================================================
        //  Intentar cambiar el estado de una transacción con una transición 
        //  inválida y capturar EstadoTransaccionInvalidoException
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 8
                           =======================================""");
        
        // ===================================================================
        //  Intentar aprobar un crédito desde un Cajero y capturar 
        //  PermisoInsuficienteException
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 9
                           =======================================""");
        
        // ===================================================================
        //  Llamar a notificar() en un cliente que acepta notificaciones y 
        //  en uno que no
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 10
                           =======================================""");
        
        // ===================================================================
        //  Llamar a registrarModificacion() sobre una cuenta y luego imprimir 
        //  obtenerUltimaModificacion() y obtenerUsuarioModificacion()
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 11
                           =======================================""");
        
        // ===================================================================
        //  Calcular e imprimir la nómina total del banco
        // ===================================================================
        
        System.out.println("""
                           =======================================
                                         ESCENARIO 12
                           =======================================""");
        
        
    }
}

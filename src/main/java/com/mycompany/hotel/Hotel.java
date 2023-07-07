/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Libreria;

import javax.swing.*;

public class Hotel {
    private static GestorHabitaciones gestorHabitaciones = new GestorHabitaciones();

    public static void main(String[] args) {
        // mostrarMenuHabitaciones();

        // contrasena;
        String Contraseña = "Admin";
        String opcionPass = "";
        int opcionmenu = 0;
        do {
            opcionmenu = JOptionPane.showOptionDialog(null,
                    "=== Menú de Mantenimiento de Habitaciones ===",
                    "Menú",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[]{"Ingresar contraseña", "Salir"},
                    null);

            switch (opcionmenu) {
                case 0:
                    opcionPass = JOptionPane.showInputDialog("Ingrese la contraseña:");
                    if (opcionPass != null && opcionPass.equals(Contraseña)) {
                        JOptionPane.showMessageDialog(null, "Acceso exitoso");
                        MenuGeneral();
                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                    }
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                    break;
                case JOptionPane.CLOSED_OPTION:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (opcionmenu != 1 && opcionmenu != JOptionPane.CLOSED_OPTION);
    }
        
    private static void MenuGeneral() {
        int MenuGeneralOption = 0;
        do {
            MenuGeneralOption = JOptionPane.showOptionDialog(null,
                    "=== Hotel UTP ===\n" +
                            "1. Modulo Habitaciones\n" +
                            "2. Modulo Clientes\n" +
                            "3. Modulo hospedaje\n" +
                            "0. Salir\n" +
                            "Seleccione una opción:",
                    "Menú General",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[]{"Modulo Habitaciones", "Modulo Clientes", "Modulo Hospedaje", "Salir"},
                    null);
    
            switch (MenuGeneralOption) {
                case 0:
                    mostrarMenuHabitaciones();
                    break;
                case 1:
                    mostrarMenuClientes();
                    break;
                case 2:
                    mostrarMenuHospedaje();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                    break;
                case JOptionPane.CLOSED_OPTION:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (MenuGeneralOption != 3 && MenuGeneralOption != JOptionPane.CLOSED_OPTION);
    }
    

    ////////////// INICIO HABITACIONES/////
    private static void mostrarMenuHabitaciones() {
        int opcion = 0;
        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "=== Menú de Mantenimiento de Habitaciones ===",
                    "Menú Habitaciones",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[]{"Mostrar habitaciones", "Buscar habitación", "Insertar habitación", "Modificar habitación", "Eliminar habitación", "Salir"},
                    null);
    
            switch (opcion) {
                case 0:
                    gestorHabitaciones.mostrarHabitaciones();
                    break;
                case 1:
                    buscarHabitacion();
                    break;
                case 2:
                    insertarHabitacion();
                    break;
                case 3:
                    modificarHabitacion();
                    break;
                case 4:
                    eliminarHabitacion();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Volviendo al menú principal...");
                    break;
                case JOptionPane.CLOSED_OPTION:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 5 && opcion != JOptionPane.CLOSED_OPTION);
    }
    

    private static Habitacion buscarHabitacion() {
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de habitación a buscar:"));
        Habitacion habitacion = gestorHabitaciones.buscarHabitacion(numero);

        if (habitacion != null) {
            JOptionPane.showMessageDialog(null, "Habitación encontrada: " + habitacion.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Habitación no encontrada.");
        }
        return habitacion;
    }

    private static void insertarHabitacion() {
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de habitación:"));
        String tipo = JOptionPane.showInputDialog("Ingrese el tipo de habitación (simple | doble | triple):");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio de la habitación:"));
        String estado = JOptionPane.showInputDialog("Ingrese el estado de la habitación (libre | ocupado):");

        Habitacion habitacion = new Habitacion(numero, tipo, precio, estado);
        gestorHabitaciones.insertarHabitacion(habitacion);
        JOptionPane.showMessageDialog(null, "La habitación ha sido insertada exitosamente.");
    }

    private static void modificarHabitacion() {
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de habitación a modificar:"));
        Habitacion habitacion = gestorHabitaciones.buscarHabitacion(numero);

        if (habitacion != null) {
            JOptionPane.showMessageDialog(null, "Habitación encontrada: " + habitacion.toString());
            String tipo = JOptionPane.showInputDialog("Ingrese el nuevo tipo de habitación (simple | doble | triple):");
            double precio = Double
                    .parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio de la habitación:"));
            String estado = JOptionPane.showInputDialog("Ingrese el nuevo estado de la habitación (libre | ocupado):");

            gestorHabitaciones.modificarHabitacion(numero, tipo, precio, estado);
        } else {
            JOptionPane.showMessageDialog(null, "Habitación no encontrada.");
        }
    }

    private static void eliminarHabitacion() {
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de habitación a eliminar:"));
        gestorHabitaciones.eliminarHabitacion(numero);
    }

    private static void cambiarEstadoHabitacion(int numero) {
        Habitacion habitacion = gestorHabitaciones.buscarHabitacion(numero);

        if (habitacion != null) {
            String estadoActual = habitacion.getEstado();
            String nuevoEstado = "";

            if (estadoActual.equals("libre")) {
                nuevoEstado = "ocupado";
            } else if (estadoActual.equals("ocupado")) {
                nuevoEstado = "libre";
            }

            gestorHabitaciones.modificarEstadoHabitacion(numero, nuevoEstado);
            JOptionPane.showMessageDialog(null, "Estado de la habitación cambiado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Habitación no encontrada.");
        }
    }

    ////////////// FIN HABITACIONES
    ///////////// INICIO CLIENTES
    private static void mostrarMenuClientes() {
        int opcionClientes = 0;
        do {
            opcionClientes = JOptionPane.showOptionDialog(null,
                    "=== Menú de Gestión de Clientes ===",
                    "Menú Clientes",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[]{"Mostrar clientes", "Buscar cliente", "Registrar cliente", "Modificar cliente", "Eliminar cliente", "Salir"},
                    null);
    
            switch (opcionClientes) {
                case 0:
                    gestorHabitaciones.mostrarClientes();
                    break;
                case 1:
                    buscarCliente();
                    break;
                case 2:
                    registrarCliente();
                    break;
                case 3:
                    modificarCliente();
                    break;
                case 4:
                    eliminarCliente();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Volviendo al menú principal...");
                    break;
                case JOptionPane.CLOSED_OPTION:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (opcionClientes != 5 && opcionClientes != JOptionPane.CLOSED_OPTION);
    }
    

    private static void registrarCliente() {
        String dni = JOptionPane.showInputDialog(null, "Ingrese el DNI del cliente:");
        String nombres = JOptionPane.showInputDialog(null, "Ingrese los nombres del cliente:");
        String apellidos = JOptionPane.showInputDialog(null, "Ingrese los apellidos del cliente:");
        String direccion = JOptionPane.showInputDialog(null, "Ingrese la dirección del cliente:");
        String sexo = JOptionPane.showInputDialog(null, "Ingrese el sexo del cliente:");
        String fechaNacimiento = JOptionPane.showInputDialog(null, "Ingrese la fecha de nacimiento del cliente:");
        String nacionalidad = JOptionPane.showInputDialog(null, "Ingrese la nacionalidad del cliente:");
        String correo = JOptionPane.showInputDialog(null, "Ingrese el correo del cliente:", "Registro de Cliente");
        String celular = JOptionPane.showInputDialog(null, "Ingrese el celular del cliente:", "Registro de Cliente");

        // Crear un objeto Cliente con los datos ingresados
        Cliente cliente = new Cliente(dni, nombres, apellidos, direccion, sexo, fechaNacimiento, nacionalidad, correo,
                celular);

        // Llamar al método correspondiente del GestorHabitaciones para registrar al
        // cliente
        gestorHabitaciones.registrarCliente(cliente);

        JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
    }

    /////// SE REALIZO UN CAMBIO ESPECIFICIO PARA
    private static Cliente buscarCliente() {
        // SE USA STRING AL BUSCAR DNI EN CASO DE QUE EL DOCUMENT ODEL CLIENTE USE
        // CARACTERES DISTINTOS A NUNMEROS
        String dni = JOptionPane.showInputDialog(null, "Ingrese el DNI del cliente a buscar:");

        // Llamar al método correspondiente del GestorHabitaciones para buscar al
        // cliente
        Cliente cliente = gestorHabitaciones.buscarCliente(dni);

        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado: \n" + cliente.toString());
        } else {
            JOptionPane.showMessageDialog(null, "El cliente con el DNI ingresado no existe.");
        }

        return cliente; // Devolver el cliente encontrado
    }

    private static void eliminarCliente() {
        String dni = JOptionPane.showInputDialog(null, "Ingrese el DNI del cliente a eliminar:");

        // Verificar si el cliente existe
        // Llamar al método correspondiente del GestorHabitaciones para buscar al
        // cliente
        Cliente cliente = gestorHabitaciones.buscarCliente(dni);
        if (cliente != null) {
            // Llamar al método correspondiente del GestorHabitaciones para eliminar al
            // cliente
            gestorHabitaciones.eliminarCliente(dni);

            JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "El cliente con el DNI ingresado no existe.");
        }
    }

    private static void modificarCliente() {
        String dni = JOptionPane.showInputDialog(null, "Ingrese el dni del cliente a modificar:");
        Cliente clienteExistente = gestorHabitaciones.buscarCliente(dni);

        if (clienteExistente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado: " + clienteExistente.toString());
            JOptionPane.showMessageDialog(null, "Ingrese los nuevos datos del cliente:");

            String nuevosNombres = JOptionPane.showInputDialog(null, "Nuevos nombres:");
            String nuevosApellidos = JOptionPane.showInputDialog(null, "Nuevos apellidos:");
            String nuevaDireccion = JOptionPane.showInputDialog(null, "Nueva dirección:");
            String nuevoSexo = JOptionPane.showInputDialog(null, "Nuevo sexo:");
            String nuevaFechaNacimiento = JOptionPane.showInputDialog(null, "Nueva fecha de nacimiento:");
            String nuevaNacionalidad = JOptionPane.showInputDialog(null, "Nueva nacionalidad:");
            String nuevoCorreo = JOptionPane.showInputDialog(null, "Nuevo correo:");
            String nuevoCelular = JOptionPane.showInputDialog(null, "Nuevo celular:");

            gestorHabitaciones.modificarCliente(dni, nuevosNombres, nuevosApellidos, nuevaDireccion, nuevoSexo,
                    nuevaFechaNacimiento, nuevaNacionalidad, nuevoCorreo, nuevoCelular);

            JOptionPane.showMessageDialog(null, "Cliente modificado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "El cliente con el DNI ingresado no existe.");
        }
    }

    ///////////////////////////////// FIN CLIENTES//////////
    ///////////////////////////////// INICIO HOSPEDAJE//
    private static void mostrarMenuHospedaje() {
        int opcionHospedaje = 0;
        do {
            opcionHospedaje = JOptionPane.showOptionDialog(null,
                    "=== Módulo Hospedaje ===",
                    "Menú Hospedaje",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[]{"Registrar hospedaje", "Consultar Huespedes", "Registrar salida de hospedaje", "Salir"},
                    null);
    
            switch (opcionHospedaje) {
                case 0:
                    registrarHospedaje();
                    break;
                case 1:
                    gestorHabitaciones.consultarHospedaje();
                    break;
                case 2:
                    registrarsalidaHospedaje();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Volviendo al menú principal...");
                    break;
                case JOptionPane.CLOSED_OPTION:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (opcionHospedaje != 3 && opcionHospedaje != JOptionPane.CLOSED_OPTION);
    }
    

    private static void registrarHospedaje() {
        Cliente cliente;
        do {
            cliente = buscarCliente();
            if (cliente == null) {
                // Verificar si se desea cancelar
                String cancelarbusqueda = JOptionPane
                        .showInputDialog("Oprima enter y intente nuevamente o ingrese 0 para cancelar");
                if (cancelarbusqueda == "0") {
                    JOptionPane.showMessageDialog(null, "Operación cancelada.");
                    return; // Salir de la función
                }
            }
        } while (cliente == null);
        gestorHabitaciones.mostrarHabitacionesLibres();
        Habitacion habitacion = buscarHabitacion();
        String dnicliente = cliente.getDni();
        if (habitacion != null) {
            int numHospedaje = habitacion.getNumero();
            String fechadeIngreso = JOptionPane.showInputDialog(null, "Ingrese la fecha de ingreso del hospedaje:");
            int numDiasHospedaje = Integer
                    .parseInt(JOptionPane.showInputDialog(null, "Ingrese los días de hospedaje:"));
            String lugardeorigen = JOptionPane.showInputDialog(null, "Ingrese la nacionalidad del cliente:");
            String observaciones = JOptionPane.showInputDialog(null, "Ingrese las observaciones:");

            Hospedaje hospedaje = new Hospedaje(numHospedaje, fechadeIngreso, numDiasHospedaje, lugardeorigen,
                    observaciones, dnicliente);
            gestorHabitaciones.registrarHospedaje(hospedaje);
            cambiarEstadoHabitacion(numHospedaje);
            JOptionPane.showMessageDialog(null, "Hospedaje registrado exitosamente.");

            // Mostrar los datos del hospedaje registrado
            JOptionPane.showMessageDialog(null, "Datos del hospedaje:\n"
                    + "Habitación: " + numHospedaje + "\n"
                    + "Fecha de ingreso: " + fechadeIngreso + "\n"
                    + "Días de hospedaje: " + numDiasHospedaje + "\n"
                    + "Lugar de origen: " + lugardeorigen + "\n"
                    + "Observaciones: " + observaciones + "\n"
                    + "Dni: " + dnicliente);
            // Mostrar datos del cliente
            JOptionPane.showMessageDialog(null, "Datos del cliente:\n" + cliente.toString() + "\n");
            // Mostrar datos de la habitacion
            JOptionPane.showMessageDialog(null, "Datos de la habitación:\n" + habitacion.toString() + "\n");

        } else {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el hospedaje. La habitación no existe.");
        }
    }

    private static void registrarsalidaHospedaje() {
        int opcionHospedaje = 0;
        do {
            opcionHospedaje = JOptionPane.showOptionDialog(null,
                    "=== Registrar salida de huésped ===",
                    "Registrar salida",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[]{"Buscar huésped por DNI", "Buscar huésped por habitación", "Salir"},
                    null);
    
            switch (opcionHospedaje) {
                case 0:
                    Hospedaje hospedajeDni = buscarHospedajeDni();
                    if (hospedajeDni != null) {
                        insertarHistorial(hospedajeDni);
                        gestorHabitaciones.eliminarHospedaje(hospedajeDni);
                    }
                    break;
                case 1:
                    Hospedaje hospedajeHabitacion = buscarHospedajeHabitacion();
                    if (hospedajeHabitacion != null) {
                        insertarHistorial(hospedajeHabitacion);
                        gestorHabitaciones.eliminarHospedaje(hospedajeHabitacion);
                    }
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Volviendo al menú principal...");
                    break;
                case JOptionPane.CLOSED_OPTION:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (opcionHospedaje != 2 && opcionHospedaje != JOptionPane.CLOSED_OPTION);
    }
    

    private static Hospedaje buscarHospedajeDni() {
        String dnicliente = JOptionPane.showInputDialog(null, "Ingrese el dni del huesped a buscar");
        Hospedaje hospedaje = gestorHabitaciones.buscarHospedajeDni(dnicliente);
        if (hospedaje != null) {
            JOptionPane.showMessageDialog(null, "Huesped encontrado: \n" + hospedaje.toString());
        } else {
            JOptionPane.showMessageDialog(null, "El husped con el DNI ingresado no existe.");
        }

        return hospedaje; // Devolver lo encontrado
    }

    
    private static Hospedaje buscarHospedajeHabitacion() {
        int habitacion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de la habitacion del huesped a buscar"));
        Hospedaje hospedaje = gestorHabitaciones.buscarHospedajeHabitacion(habitacion);
        if (hospedaje != null) {
            JOptionPane.showMessageDialog(null, "Habitacion del huesped encontrada: \n" + hospedaje.toString());
        } else {
            JOptionPane.showMessageDialog(null, "La habitacion del huesped ingresada no existe.");
        }

        return hospedaje; // Devolver lo encontrado
    }

    private static void insertarHistorial(Hospedaje hospedaje) {
        String dni = hospedaje.getDni();
        int habitacion = hospedaje.getNumHospedaje();
        String fecha = JOptionPane.showInputDialog(null, "Ingrese la fecha de salida");
        String hora = JOptionPane.showInputDialog(null, "Ingrese la hora de salida");
        String comentario = JOptionPane.showInputDialog(null, "Ingrese un comentario");
        HistorialHuspedes historialHuspedes = new HistorialHuspedes(dni, habitacion, fecha, hora, comentario);
        cambiarEstadoHabitacion(habitacion);
        gestorHabitaciones.registrarHistorial(historialHuspedes);
        //calcular
        int dias = hospedaje.getNumDiasHospedaje();
        Habitacion Preciohabitacion = gestorHabitaciones.buscarHabitacion(habitacion);
        Double precio = Preciohabitacion.getPrecio();
        Double Totalpago = dias * precio;
        JOptionPane.showMessageDialog(null, "El total a pagar por el hospedaje es: " + Totalpago);
        JOptionPane.showMessageDialog(null, "La habitación ha sido insertada exitosamente.");
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.organizadordetareas.Vista;

import java.util.Scanner;
import java.util.regex.*;
import java.time.LocalDateTime;
import com.mycompany.organizadordetareas.Controlador.*;
import com.mycompany.organizadordetareas.Controlador.estDatos.estDatLin.*;
import com.mycompany.organizadordetareas.Modelo.*;
//._.

/**
 * La clase Main es la clase principal que contiene el método main para ejecutar
 * el programa de gestión de tareas y eventos.
 */

public class Main {
    private final static Scanner entrada = new Scanner(System.in);
    private static GestorGeneral g;
    private final static GestorUser gestorUsuarios = new GestorUser();

    /**
     * Método principal que inicia el programa de gestión de tareas y eventos.
     * 
     * @param args Los argumentos de línea de comandos (no se utilizan en este
     *             programa).
     */
    public static void main(String args[]) {
        int opcion = lanzarMenuUsuario();
        switch (opcion) {
            case 1:
                iniciarSesion();
                break;
            case 2:
                registrarUsuario();
                main(args);
                break;
            case 3:
                System.out.println("Exito, nos vemos pronto");
                System.exit(0);
                break;
        }
    }

    private static void setGestor(Usuario us) {
        g = new GestorGeneral(us);
    }

    public static void registrarUsuario() {
        System.out.println("Por favor, ingrese el nombre de usuario:\nO ingrese 0 para salir");
        String nombreUsuario = entrada.nextLine();
        if (nombreUsuario.equals("0")) {
            main(null);
        }
        boolean segura = false;
        String contrasenaUsuario;
        do {
            System.out.println("Ahora, elija una contraseña segura:\nO ingrese 0 para retroceder");
            contrasenaUsuario = entrada.nextLine();
            if (contrasenaUsuario.equals("0")) {
                main(null);
            }
            segura = validarContraSegura(contrasenaUsuario);

            if (!segura) {
                System.out.println(
                        "La contraseña debe contener al menos 8 caracteres, incluyendo al menos una letra mayúscula, una minúscula, un número y un carácter especial.");
            }

        } while (!segura);
        if (gestorUsuarios.registrarUsuario(new Usuario(nombreUsuario, contrasenaUsuario, "", 1))) {
            System.out.println("Usuario creado exitosamente, por favor inicie sesion");
        } else {
            System.out.println("Error, el usuario ingresado ya existe, intente de nuevo");
            registrarUsuario();
        }
    }

    public static boolean validarContraSegura(String contrasena) {
        // Al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter
        // especial
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(contrasena);

        return m.matches();
    }

    public static void iniciarSesion() {
        System.out.println("Por favor, ingrese el nombre de usuario:\nO ingrese 0 para salir");
        String nombreUsuario = entrada.nextLine();
        if (nombreUsuario.equals("0")) {
            main(null);
        }
        System.out.println("Por favor, ingrese la contraseña del usuario:\nO ingrese 0 para salir");
        String contrasenaUsuario = entrada.nextLine();
        if (contrasenaUsuario.equals("0")) {
            main(null);
        }
        Usuario user = gestorUsuarios.buscarUsuario(nombreUsuario, contrasenaUsuario);
        if (user != null) {
            setGestor(user);
            lanzarMenu();
        } else {
            System.out.println("Las credenciales ingresadas son incorrectas, intente de nuevo.");
            iniciarSesion();
        }
    }

    public static int lanzarMenuUsuario() {
        System.out.println("Bienvenido al gestor de Pendientes v2.0");
        int res = -1;
        boolean bandera = false;
        do {
            mostrarMenuUsuario();
            String input = entrada.nextLine();
            if (validarPrioridad(input)) {
                res = Integer.parseInt(input);
                bandera = true;
            } else {
                System.out.println("Por favor ingrese un opcion valida entre 1 y 3");
            }
        } while (!bandera);
        return res;
    }

    public static void mostrarMenuUsuario() {
        System.out.println("* Ingrese 1 para iniciar sesion");
        System.out.println("* Ingrese 2 para registar un nuevo usuario");
        System.out.println("* Ingrese 3 para cerrar el programa");
    }

    public static void lanzarMenu() {

        System.out.println("¡Bienvenido a tu gestor de tareas y eventos!");
        boolean banderin = true;
        do {
            mostrarMenu();
            boolean evento = true;
            int menu = 0;
            while (evento) {
                String eventito = entrada.nextLine();
                if (validarMenu(eventito)) {
                    menu = Integer.parseInt(eventito);
                    evento = false;
                } else {
                    System.out.println("Por favor, ingrese una opción válida (1, 2, 3, 4, 5, 6 o 7)");
                }
            }
            switch (menu) {
                case 1:
                    g.imprimirOrdenado();
                    break;
                case 2:
                    recibirPendiente();
                    break;
                case 3:
                    buscarPendiente();
                    break;
                case 4:
                    eliminarPendiente();
                    break;
                case 5:
                    modificarPendiente();
                    break;
                case 6:
                    cerrarSesion();
                    break;
                case 7:
                    banderin = false;
                    System.out.println("¡Éxito! Nos vemos.");
                    break;
            }
        } while (banderin);
    }

    static void modificarPendiente() {
        boolean bandera = true;
        do {
            System.out.println("Desea modificar una tarea o evento?\nO ingrese 0 para volver atras");
            String input = entrada.nextLine();
            if (input.equals("tarea")) {
                bandera = false;
                int opcion = lanzarMenuModificacionTarea();
                switch (opcion) {
                    case 0:
                        lanzarMenu();
                        break;
                    case 1:
                        modificarTitulo(1);
                        break;
                    case 2:
                        modificarPrioridad(1);
                        break;
                    case 3:
                        modificarHora(1);
                        break;
                    case 4:
                        modificarFecha(1);
                        break;
                }
            } else if (input.equals("evento")) {
                bandera = false;
                int opcion = lanzarMenuModificacionEvento();
                switch (opcion) {
                    case 0:
                        lanzarMenu();
                        break;
                    case 1:
                        modificarTitulo(2);
                        break;
                    case 2:
                        modificarPrioridad(2);
                        break;
                    case 3:
                        modificarHora(2);
                        break;
                    case 4:
                        modificarFecha(2);
                        break;
                    case 5:
                        modificarDescripcion();
                        break;
                }
            } else if (input.equals("0")) {
                bandera = false;
                lanzarMenu();
            } else {
                System.out.println("Ingrese una opcion correcta por favor");
            }
        } while (bandera);
    }

    static void modificarDescripcion() {
        boolean bandera = true;
        do {
            System.out.println("Ingrese el titulo del evento a modificar\nO ingrese 0 para volver atras");
            String titulo = entrada.nextLine();
            if (titulo.equals("0")) {
                modificarPendiente();
            } else {
                System.out.println("Ingrese la nueva descripcion para ese titulo");
                String input = entrada.nextLine();
                if (g.modificarDescripcion(input, titulo)) {
                    bandera = false;
                    System.out.println("Logramos modificar su evento con exito");
                } else {
                    System.out.println(
                            "No encontramos el evento con el titulo proporcionado, intente de nuevo por favor");
                }
            }
        } while (bandera);
    }

    static void modificarFecha(int tipo) {
        if (tipo == 1) {
            System.out.println("Por favor ingrese el titulo de su Tarea\nO ingrese 0 para volver atras");
            String titulo = entrada.nextLine();
            if (titulo.equals("0")) {
                modificarPendiente();
                return;
            }
            System.out.println(
                    "Por favor, ingrese la fecha, usando el siguiente formato dd-mm-aaaa\nO ingrese 0 para volver atras");
            String fecha;
            do {
                fecha = entrada.nextLine();
                if (fecha.equals("0")) {
                    modificarPendiente();
                    return;
                }
                if (!validarFecha(fecha)) {
                    System.out.println(
                            "La fecha ingresada no tiene el formato correcto indicado (dd-mm-aaaa), vuelva a ingresarla por favor\nO ingrese 0 para volver atras");
                }
            } while (!validarFecha(fecha));
            Fecha f = parsearFecha(fecha);
            int estado = g.modificarFecha(f, titulo, tipo);
            if (estado == 1) {
                System.out.println(
                        "No logramos modificar la fecha de tu tarea porque aun tienes algo pendiente justo a esa fecha y hora :)\nCambialo a otra fecha");
                modificarHora(tipo);
            } else if (estado == 2) {
                System.out.println(
                        "No logramos encontrar la tarea con el titulo proporcionado\nIntenta de nuevo por favor");
                modificarHora(tipo);
            } else {
                System.out.println("Logramos modificar la fecha de su tarea con exito");
            }
        } else {
            System.out.println("Por favor ingrese el titulo de su Evento\nO ingrese 0 para volver atras");
            String titulo = entrada.nextLine();
            if (titulo.equals("0")) {
                modificarPendiente();
                return;
            }
            System.out.println(
                    "Por favor, ingrese la fecha, usando el siguiente formato dd-mm-aaaa\nO ingrese 0 para volver atras");
            String fecha;
            do {
                fecha = entrada.nextLine();
                if (fecha.equals("0")) {
                    modificarPendiente();
                    return;
                }
                if (!validarFecha(fecha)) {
                    System.out.println(
                            "La fecha ingresada no tiene el formato correcto indicado (dd-mm-aaaa), vuelva a ingresarla por favor\nO ingrese 0 para volver atras");
                }
            } while (!validarFecha(fecha));
            Fecha f = parsearFecha(fecha);
            int estado = g.modificarFecha(f, titulo, tipo);
            if (estado == 1) {
                System.out.println(
                        "No logramos modificar la fecha de tu Evento porque aun tienes algo pendiente justo a esa fecha y hora :)\nCambialo a otra fecha");
                modificarHora(tipo);
            } else if (estado == 2) {
                System.out.println(
                        "No logramos encontrar el evento con el titulo proporcionado\nIntenta de nuevo por favor");
                modificarHora(tipo);
            } else {
                System.out.println("Logramos modificar la fecha de su evento con exito");
            }
        }
    }

    static void modificarHora(int tipo) {
        if (tipo == 1) {
            System.out.println("Por favor ingrese el titulo de su Tarea\nO ingrese 0 para volver atras");
            String titulo = entrada.nextLine();
            if (titulo.equals("0")) {
                modificarPendiente();
                return;
            }
            System.out.println(
                    "Por favor, ingrese la hora, usando el siguiente formato (horas:minutos)\nO ingrese 0 para volver atras");
            String hora;
            do {
                hora = entrada.nextLine();
                if (hora.equals("0")) {
                    modificarPendiente();
                    return;
                }
                if (!validarHora(hora)) {
                    System.out.println(
                            "La hora ingresada no tiene el formato correcto indicado (horas:minutos), vuelva a ingresarla por favor\n O ingrese 0 para volver atras");
                }
            } while (!validarHora(hora));
            Hora h = parsearHora(hora);
            int estado = g.modificarHora(h, titulo, tipo);
            if (estado == 1) {
                System.out.println(
                        "No logramos modificar la hora de tu tarea porque aun tienes algo pendiente a esa hora :)\n Cambialo a otra hora");
                modificarHora(tipo);
            } else if (estado == 2) {
                System.out.println(
                        "No logramos encontrar la tarea con el titulo proporcionado\nIntenta de nuevo por favor");
                modificarHora(tipo);
            } else {
                System.out.println("Logramos modificar la hora de su tarea con exito");
            }
        } else {
            System.out.println("Por favor ingrese el titulo de su Evento\nO ingrese 0 para volver atras");
            String titulo = entrada.nextLine();
            if (titulo.equals("0")) {
                modificarPendiente();
                return;
            }
            System.out.println(
                    "Por favor, ingrese la hora, usando el siguiente formato (horas:minutos)\nO ingrese 0 para volver atras");
            String hora;
            do {
                hora = entrada.nextLine();
                if (hora.equals("0")) {
                    modificarPendiente();
                    return;
                }
                if (!validarHora(hora)) {
                    System.out.println(
                            "La hora ingresada no tiene el formato correcto indicado (horas:minutos), vuelva a ingresarla por favor\n O ingrese 0 para volver atras");
                }
            } while (!validarHora(hora));
            Hora h = parsearHora(hora);
            int estado = g.modificarHora(h, titulo, tipo);
            if (estado == 1) {
                System.out.println(
                        "No logramos modificar la hora de tu evento porque aun tienes algo pendiente a esa hora :)\nCambialo a otra hora");
                modificarHora(tipo);
            } else if (estado == 2) {
                System.out.println(
                        "No logramos encontrar el evento con el titulo proporcionado\nIntenta de nuevo por favor");
                modificarHora(tipo);
            } else {
                System.out.println("Logramos modificar la hora de su evento con exito");
            }
        }
    }

    static void modificarPrioridad(int tipo) {
        boolean bandera = true;
        if (tipo == 1) {
            do {
                System.out.println(
                        "Ingrese el titulo de su tarea para modificar su prioridad\nO ingrese 0 para volver atras");
                String titulo = entrada.nextLine();
                if (titulo.equals("0")) {
                    modificarPendiente();
                } else {
                    System.out.println("Ingrese la nueva prioridad por favor\nO ingrese 0 para volver atras");
                    String input = entrada.nextLine();
                    if (input.equals("0")) {
                        modificarPendiente();
                        break;
                    }
                    if (validarPrioridad(input)) {
                        if (g.modificarPrioridad(Integer.parseInt(input), titulo, tipo)) {
                            System.out.println("No logramos encontrar su tarea, por favor intente de nuevo");
                        } else {
                            System.out.println("Su tarea fue modificada con exito");
                            bandera = false;
                        }
                    } else {
                        System.out.println(
                                "La nueva prioridad ingresada no se encuentra en el rango [1, 3], intente de nuevo por favor");
                    }
                }
            } while (bandera);
        } else {
            // Evento
            do {
                System.out.println(
                        "Ingrese el titulo de su evento para modificar su prioridad\nOingrese 0 para volver atras");
                String titulo = entrada.nextLine();
                if (titulo.equals("0")) {
                    modificarPendiente();
                } else {
                    System.out.println("Ingrese la nueva prioridad por favor\nO ingrese 0 para volver atras");
                    String input = entrada.nextLine();
                    if (input.equals("0")) {
                        modificarPendiente();
                        break;
                    }
                    if (validarPrioridad(input)) {
                        if (g.modificarPrioridad(Integer.parseInt(input), titulo, tipo)) {
                            System.out.println("Su evento fue modificada con exito");
                            bandera = false;
                        } else {
                            System.out.println("No logramos encontrar su evento, por favor intente de nuevo");
                        }
                    } else {
                        System.out.println(
                                "La nueva prioridad ingresada no se encuentra en el rango [1, 3], intente de nuevo por favor");
                    }
                }
            } while (bandera);
        }
    }

    static void modificarTitulo(int tipo) {
        boolean bandera = true;
        if (tipo == 1) {
            // tarea
            do {
                System.out.println("Ingrese el titulo a modificar de su Tarea por favor\nOIngrese 0 para volver atras");
                String input = entrada.nextLine();
                if (input.equals("0")) {
                    modificarPendiente();
                    break;
                }
                System.out.println("Ingrese el nuevo titulo de su Tarea por favor\nO ingrese 0 para volver atras");
                String nuevo = entrada.nextLine();
                if (nuevo.equals("0")) {
                    bandera = false;
                    modificarPendiente();
                    break;
                } else if (g.modificarTitulo(nuevo, input, tipo)) {
                    System.out.println("Exito, modificamos el titulo de su tarea");
                    bandera = false;
                } else {
                    System.out.println(
                            "No logramos encontrar la tarea con el titulo proporcionado, intente de nuevo por favor");
                }
            } while (bandera);
        } else {
            // evento
            do {
                System.out
                        .println("Ingrese el titulo a modificar de su Evento por favor\nO ingrese 0 para volver atras");
                String input = entrada.nextLine();
                if (input.equals("0")) {
                    modificarPendiente();
                    break;
                }
                System.out.println("Ingrese el nuevo titulo de su Evento por favor\nO ingrese 0 para volver atras");
                String nuevo = entrada.nextLine();
                if (nuevo.equals("0")) {
                    bandera = false;
                    modificarPendiente();
                    break;
                } else if (g.modificarTitulo(nuevo, input, tipo)) {
                    System.out.println("Exito, modificamos el titulo de su evento");
                    bandera = false;
                } else {
                    System.out.println(
                            "No logramos encontrar el evento con el titulo proporcionado, intente de nuevo por favor");
                }
            } while (bandera);
        }
    }

    static int lanzarMenuModificacionTarea() {
        boolean bandera = true;
        int res = 0;
        do {
            System.out.println("Ingrese 0 para volver atras");
            System.out.println("Ingrese 1 para modificar el titulo de su Tarea");
            System.out.println("Ingrese 2 para modificar la prioridad de su Tarea");
            System.out.println("Ingrese 3 para modificar la Fecha de su tarea");
            System.out.println("Ingrese 4 para modificar la Hora de su Tarea");
            String input = entrada.nextLine();
            if (validarMenuTarea(input)) {
                bandera = false;
                res = Integer.parseInt(input);
            } else {
                System.out.println("Ingrese una opcion valida a elegir por favor");
            }
        } while (bandera);
        return res;
    }

    static int lanzarMenuModificacionEvento() {
        boolean bandera = true;
        int res = 0;
        do {
            System.out.println("Ingrese 0 para volver atras");
            System.out.println("Ingrese 1 para modificar el titulo de su Evento");
            System.out.println("Ingrese 2 para modificar la prioridad de su Evento");
            System.out.println("Ingrese 3 para modificar la Fecha de su Evento");
            System.out.println("Ingrese 4 para modificar la Hora de su Evento");
            System.out.println("Ingrese 5 para modificar la descripcion de su Evento");
            String input = entrada.nextLine();
            if (validarMenuEvento(input)) {
                bandera = false;
                res = Integer.parseInt(input);
            } else {
                System.out.println("Ingrese una opcion valida a elegir por favor");
            }
        } while (bandera);
        return res;
    }

    private static boolean validarMenuTarea(String n) {
        String patron = "[0-4]";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(n);
        return matcher.matches();
    }

    private static boolean validarMenuEvento(String n) {
        String patron = "[0-5]";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(n);
        return matcher.matches();
    }

    static void cerrarSesion() {
        String nomUsuario = g.getNomUs();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Adios, " + nomUsuario + " esperamos volver a verte de nuevo en el gestor");
        System.out.println("---------------------------------------------------------------------");
        main(null);
    }

    private static void buscarPendiente() {
        String tipo;
        System.out.println("¿Desea buscar una tarea o un evento?");
        boolean tipito = true;
        do {
            tipo = entrada.nextLine();
            tipo = tipo.toLowerCase();
            if (tipo.equals("tarea")) {
                buscarTarea();
                tipito = false;
                break;
            } else if (tipo.equals("evento")) {
                buscarEvento();
                tipito = false;
                break;
            } else {
                System.out.println("Por favor ingrese un tipo de pendiente valido, ya sea tarea o evento.");
            }
        } while (tipito);
    }

    private static void buscarTarea() {
        String titulo;
        System.out.println("Por favor ingrese el titulo de la tarea que esta buscando.");
        titulo = entrada.nextLine();
        System.out.println("---------------------------------------------");
        System.out.println(g.buscarTarea(titulo));
        System.out.println("---------------------------------------------");
    }

    private static void buscarEvento() {
        String titulo;
        System.out.println("Por favor ingrese el titulo del evento que esta buscando.");
        titulo = entrada.nextLine();
        System.out.println("---------------------------------------------");
        System.out.println(g.buscarEvento(titulo));
        System.out.println("---------------------------------------------");
    }

    private static void eliminarPendiente() {
        String tipo;
        System.out.println("¿Desea eliminar una tarea o un evento?");
        boolean bandera = true;
        do {
            tipo = entrada.nextLine();
            tipo = tipo.toLowerCase();
            if (tipo.equals("tarea")) {
                eliminarTarea();
                bandera = false;
            } else if (tipo.equals("evento")) {
                eliminarEvento();
                bandera = false;
            } else {
                System.out.println("Por favor ingrese un tipo de pendiente valido, ya sea tarea o evento.");
            }
        } while (bandera);
    }

    private static void eliminarTarea() {
        String titulo;
        System.out.println("Por favor ingrese el titulo de la tarea que desea eliminar.");
        titulo = entrada.nextLine();
        if (g.eliminarTarea(titulo)) {
            System.out.println("---------------------------------------------");
            System.out.println("Se elimino la tarea con titulo: " + titulo + " exitosamente!");
            System.out.println("---------------------------------------------");
        } else {
            System.out.println("No se logro encontrar la tarea con ese titulo.");
        }
    }

    private static void eliminarEvento() {
        String titulo;
        System.out.println("Por favor ingrese el titulo del evento que desea eliminar.");
        titulo = entrada.nextLine();
        if (g.eliminarEvento(titulo)) {
            System.out.println("---------------------------------------------");
            System.out.println("Se elimino el evento con titulo: " + titulo + " exitosamente!");
            System.out.println("---------------------------------------------");
        } else {
            System.out.println("No se logro encontrar el evento con ese titulo.");
        }
    }

    private static boolean validarMenu(String n) {
        String patron = "[1-7]";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(n);
        return matcher.matches();
    }

    /**
     * Método para recibir un nuevo pendiente (tarea o evento) del usuario.
     */
    private static void recibirPendiente() {
        boolean banderita = true;
        System.out.println("¿Desea registrar una tarea o evento?");
        while (banderita) {
            String pendiente = entrada.nextLine();
            pendiente = pendiente.toLowerCase();
            if (pendiente.equals("tarea")) {
                banderita = false;
                recibirDatos("tarea");
            } else if (pendiente.equals("evento")) {
                banderita = false;
                recibirDatos("evento");
            } else {
                System.out.println("Vuelve a ingresar un dato válido, por favor.");
            }
        }
    }

    /**
     * Método para mostrar el menú de opciones al usuario.
     */
    private static void mostrarMenu() {
        System.out.println("* Ingrese 1 para ver la lista de todos sus pendientes.");
        System.out.println("* Ingrese 2 para registrar un pendiente.");
        System.out.println("* Ingrese 3 para buscar un pendiente.");
        System.out.println("* Ingrese 4 para eliminar un pendiente.");
        System.out.println("* Ingrese 5 para modificar un pendiente");
        System.out.println("* Ingrese 6 para cerrar su sesion");
        System.out.println("* Ingrese 7 para acabar con el proceso.");
    }

    private static boolean validarHora(String cad) {
        String patron = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cad);
        return matcher.matches();
    }

    private static boolean validarFecha(String cad) {
        String patron = "^([0-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-(\\d{4})$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cad);
        return matcher.matches();
    }

    private static boolean validarPrioridad(String cad) {
        String patron = "[1-3]";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cad);
        return matcher.matches();
    }

    private static boolean validarTiempoFuturo(Fecha fecha, Hora hora) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fechaHoraIngresadas = LocalDateTime.of(fecha.getA(), fecha.getM(), fecha.getD(),
                hora.getHora(), hora.getMin());
        return fechaHoraIngresadas.isAfter(ahora);
    }

    private static Par<Hora, Fecha> recibirTiempo(String tipo) {
        Par<String, String> res;
        boolean flag = true;
        String hora, fecha;
        Hora first;
        Fecha second;
        do {
            System.out.println("Por favor, ingrese la hora, usando el siguiente formato (horas:minutos):");
            do {
                hora = entrada.nextLine();
                if (!validarHora(hora)) {
                    System.out.println(
                            "La hora ingresada no tiene el formato correcto indicado (horas:minutos), vuelva a ingresarla por favor");
                }
            } while (!validarHora(hora));
            System.out.println("Por favor, ingrese la fecha, usando el siguiente formato (dd-mm-aaaa):");
            do {
                fecha = entrada.nextLine();
                if (!validarFecha(fecha)) {
                    System.out.println(
                            "La fecha ingresada no tiene el formato correcto correcto (dd-mm-aaaa), vuelva a ingresarla por favor");
                }
            } while (!validarFecha(fecha));
            first = parsearHora(hora);
            second = parsearFecha(fecha);
            if (!validarTiempoFuturo(second, first)) {
                System.out.println(
                        "*Por favor ingrese una fecha y hora validos*\n*Recuerde que la fecha y hora que ingrese deben de ser de un tiempo futuro*");
            } else {
                flag = false;
            }
        } while (flag);
        if (!g.validarTiempo(first, second, tipo)) {
            System.out.println("Tienes un pendiente en esa hora.\nIngresa otra fecha y hora.");
            return recibirTiempo(tipo);
        }
        return new Par(first, second);
    }

    private static void recibirDatos(String tipo) {
        String titulo, descripcion, lugar;
        System.out.println("Por favor, ingrese el título:");
        titulo = entrada.nextLine();
        Par<Hora, Fecha> tiempo = recibirTiempo(tipo);
        int prioridad = recibirPrioridad();
        if (tipo.equals("tarea")) {
            Tarea tarea = new Tarea(titulo, tiempo.first, tiempo.second, prioridad);
            g.registrarTarea(tarea);
            System.out.println("¡Su tarea fue registrada exitosamente!\n");
        } else {
            System.out.println("Por favor, ingrese la descripción de su Evento:");
            descripcion = entrada.nextLine();
            System.out.println("Por favor, ingrese el lugar de su evento:");
            lugar = entrada.nextLine();
            Evento evento = new Evento(titulo, descripcion, tiempo.first, tiempo.second, prioridad, lugar);
            g.registrarEvento(evento);
            System.out.println("¡Su evento fue registrado exitosamente!\n");
        }
    }

    private static int recibirPrioridad() {
        int prioridad;
        String input;
        boolean en = true;
        do {
            System.out.println(
                    "Ingrese la prioridad, del 1 al 3, siendo 1 el menos importante y 3 como el más importante");
            input = entrada.nextLine();
            if (!validarPrioridad(input)) {
                System.out.println("Por favor ingrese una prioridad dentro de los parametros establecidos");
            } else {
                en = false;
            }
        } while (en);
        prioridad = Integer.parseInt(input);
        return prioridad;
    }

    private static Fecha parsearFecha(String cadena) {
        String[] split = cadena.split("-");
        int dia = Integer.parseInt(split[0]);
        int mes = Integer.parseInt(split[1]);
        int ano = Integer.parseInt(split[2]);
        return new Fecha(dia, mes, ano);
    }

    private static Hora parsearHora(String cadena) {
        String split[] = cadena.split(":");
        int hora = Integer.parseInt(split[0]);
        int minutos = Integer.parseInt(split[1]);
        return new Hora(hora, minutos);
    }
}

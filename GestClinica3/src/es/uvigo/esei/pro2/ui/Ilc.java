package es.uvigo.esei.pro2.ui;

import es.uvigo.esei.pro2.core.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.ParsingException;

public class Ilc {

    public void ler() {
        int opcionPri = 0;

        // Lee el num. max. de pacientes
        int maxPacientes;
        int maxMedicos;
        int maxCitas;
        String nombre;
        // Prepara
        Clinica coleccion;
        try {
            coleccion = new Clinica("paciente.xml","medico.xml","cita.xml");
            System.out.println("Arrancando con datos...\n");

// LOS BLOQUES DE CAPTURA NORMALMENTE SOLO SE ENCARGAN DE INFORMAR DEL ERROR
// QUE SE HA PRODUCIDO NO DE LEER DATOS DE ENTRADA NI DE CREAR OBJETOS DE 
// OTRAS CLASES
// ADEMÁS, REPETIR VARIAS VECES EL MISMO CÓDIGO. TENEIS QUE EVITAR ESTO, ES UNA
// REGLA IMPORTANTE DE UN BUEN PROGRAMADOR.
// LOS MENSAJES DE ERROR SIEMPRE SE LANZAN POR EL FLUJO ERR NO OUT
        } catch (IOException exc) {
            maxPacientes = leeNum("Num. max. pacientes: ");
            maxMedicos = leeNum("Num. max. medicos: ");
            maxCitas = leeNum("Num. max. citas medicas: ");
            nombre = leeString("Nombre de la clínica");
            coleccion = new Clinica(maxPacientes, maxMedicos, maxCitas, nombre);
            System.out.println("Archivo no encontrado. arrancando sin datos...\n");
        } catch (ParsingException exc) {
            maxPacientes = leeNum("Num. max. pacientes: ");
            maxMedicos = leeNum("Num. max. medicos: ");
            maxCitas = leeNum("Num. max. citas medicas: ");
            nombre = leeString("Nombre de la clínica");
            coleccion = new Clinica(maxPacientes, maxMedicos, maxCitas, nombre);
// PARA DETECTAR DONDE SE PRODUCE EL ERROR AL LEER EL FICHERO XML, ES NECESARIO
// VISUALIZAR (POR EL FLUJO DE ERROR) EL MENSAJE QUE SE MANDA CUANDO SE LANZA
// LA EXCEPCIÓN PARSINGEXCEPTION
            System.err.println("Archivo con errores, arrancando sin datos..." 
                    + exc.getMessage() + "\n");
        } catch (Clinica.LimiteException ex) {
            maxPacientes = leeNum("Num. max. pacientes: ");
            maxMedicos = leeNum("Num. max. medicos: ");
            maxCitas = leeNum("Num. max. citas medicas: ");
            nombre = leeString("Nombre de la clínica");
            coleccion = new Clinica(maxPacientes, maxMedicos, maxCitas, nombre);
            System.out.println(ex.getMessage());
        }

        // Bucle ppal
        do {
            try {
                opcionPri = menuPrincipal(coleccion);

                switch (opcionPri) {
                    case 1:
                        gestionPacientes(coleccion);
                        break;
                    case 2:
                        gestionMedicos(coleccion);
                        break;
                    case 3:
                        gestionCitas(coleccion);
                        break;
                    case 4:
                        try {
                            System.out.println("Guardando...");
                            coleccion.toXML("paciente.xml", Clinica.Categoria.PACIENTE);
                            coleccion.toXML("medico.xml", Clinica.Categoria.MEDICO);
                            coleccion.toXML("cita.xml", Clinica.Categoria.CITA);
                        } catch (IOException exc) {
                            coleccion = new Clinica();
                            System.out.println("Archivo no encontrado");
                        }
                        break;
                }
            } catch (NumberFormatException exc) {
                System.err.println("\nError. Formato numÃ©rico invÃ¡lido.");
            } catch (Exception e) {
                System.err.println("\nERROR inesperado: " + e.getMessage());
            }
        } while (opcionPri != 4);

    }

    private void gestionPacientes(Clinica coleccion) throws Exception {
        int opcionSec = 0;

        do {
            try {
                opcionSec = menuGestionPacientes();

                switch (opcionSec) {
                    case 1:
                        inserta(coleccion, Clinica.Categoria.PACIENTE);
                        break;
                    case 2:
                        modifica(coleccion, Clinica.Categoria.PACIENTE);
                        break;
                    case 3:
                        elimina(coleccion, Clinica.Categoria.PACIENTE);
                        break;
                    case 4:
                        System.out.println(coleccion.toString(Clinica.Categoria.PACIENTE));
                        break;
                    case 5:
                        System.out.println(coleccion.listarPacientesPorTipo(leeTipoPaciente()));
                        break;
                }
            } catch (Clinica.SinElementosException | Clinica.ConCitaException | Clinica.LimiteException exc) {
                System.err.println(exc.getMessage());
            }

        } while (opcionSec != 6);
    }

    private void gestionMedicos(Clinica coleccion) throws Exception {
        int opcionSec = 0;

        do {
            try {
                opcionSec = menuGestionMedicos();
                switch (opcionSec) {
                    case 1:
                        inserta(coleccion, Clinica.Categoria.MEDICO);
                        break;
                    case 2:
                        modifica(coleccion, Clinica.Categoria.MEDICO);
                        break;
                    case 3:
                        elimina(coleccion, Clinica.Categoria.MEDICO);
                        break;
                    case 4:
                        System.out.println(coleccion.toString(Clinica.Categoria.MEDICO));
                        break;
                }
            } catch (Clinica.SinElementosException | Clinica.ConCitaException | Clinica.LimiteException exc) {
                System.err.println(exc.getMessage());
            }
        } while (opcionSec != 5);
    }

    private void gestionCitas(Clinica coleccion) throws Exception {
        int opcionSec = 0;

        do {
            try {
                opcionSec = menuGestionCitas();
                switch (opcionSec) {
                    case 1:
                        inserta(coleccion, Clinica.Categoria.CITA);
                        break;
                    case 2:
                        modifica(coleccion, Clinica.Categoria.CITA);
                        break;
                    case 3:
                        elimina(coleccion, Clinica.Categoria.CITA);
                        break;
                    case 4:
                        System.out.println(coleccion.toString(Clinica.Categoria.CITA));
                        break;
                    case 5:
                        System.out.println(listarCitaMedico(coleccion));
                        break;
                }
            } catch (Clinica.SinElementosException | Clinica.ConCitaException | Clinica.LimiteException exc) {
                System.err.println(exc.getMessage());
            }
        } while (opcionSec != 6);
    }

    private String listarCitaMedico(Clinica coleccion) throws Clinica.SinElementosException {
        Scanner entrada = new Scanner(System.in);
        String num;
        int dia;
        int mes;
        int año;
        leeString("Introduce el número de colegiado del medico:");
        num = entrada.nextLine();
        leeString("Introduce el dia de la cita:");
        dia = Integer.parseInt(entrada.nextLine());
        leeString("Introduce el mes de la cita:");
        mes = Integer.parseInt(entrada.nextLine());
        leeString("Introduce el año de la cita:");
        año = Integer.parseInt(entrada.nextLine());
        Fecha f = new Fecha(dia, mes, año);
        return coleccion.listarCitaPorMedico(num, f);
    }

    private int leeNum(String msg) {
        boolean repite;
        int toret = 0;
        Scanner teclado = new Scanner(System.in);

        do {
            repite = false;
            System.out.print(msg);

            try {
                toret = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException exc) {
                repite = true;
            }
        } while (repite);

        return toret;
    }

    private String leeString(String msg) {
        Scanner teclado = new Scanner(System.in);
        String toret;
        do {
            System.out.print(msg);

            toret = teclado.nextLine();

        } while (toret.trim().equalsIgnoreCase(""));
        return toret;
    }

    private int menuPrincipal(Clinica coleccion) {
        int toret;

        do {
            System.out.println("\n\tMENU GESTIÓN CLÍNICA HOSPITALARIA\n"
                    + "\n1. Gestión Pacientes "
                    + "\n2. Gestión Médicos "
                    + "\n3. Gestión Citas Médicas"
                    + "\n4. Salir\n");
            toret = leeNum("Selecciona: ");
        } while (toret < 1
                || toret > 4);

        System.out.println();
        return toret;
    }

    private int menuGestionPacientes() {
        int toret;

        do {
            System.out.println("\n\tGESTIÓN PACIENTES: "
                    + "\n1. Inserta un nuevo paciente\n"
                    + "2. Modifica un paciente\n"
                    + "3. Elimina un paciente\n"
                    + "4. Lista pacientes\n"
                    + "5. Lista pacientes por tipo\n"
                    + "6. Vuelve al menú principal\n");
            toret = leeNum("Selecciona: ");
        } while (toret < 1
                || toret > 6);

        System.out.println();
        return toret;
    }

    private int menuGestionMedicos() {
        int toret;

        do {
            System.out.println("\n\tGESTIÓN MÉDICOS: "
                    + "\n1. Inserta un nuevo médico\n"
                    + "2. Modifica un médico\n"
                    + "3. Elimina un médico\n"
                    + "4. Lista médicos\n"
                    + "5. Vuelve al menú principal\n");
            toret = leeNum("Selecciona: ");
        } while (toret < 1
                || toret > 5);

        System.out.println();
        return toret;
    }

    private int menuGestionCitas() {
        int toret;

        do {
            System.out.println("\n\tGESTION CITAS MEDICAS"
                    + "\n1. Inserta una nueva cita médica\n"
                    + "2. Modifica una cita médica\n"
                    + "3. Elimina una cita médica\n"
                    + "4. Lista citas medicas\n"
                    + "5. Listar citas de un médico en un día concreto\n"
                    + "6. Vuelve al menú principal\n");
            toret = leeNum("Selecciona: ");
        } while (toret < 1
                // LA CONDICIÓN DE TORET ES MIENTRAS QUE SEA MAYOR QUE 6 NO 5
                || toret > 6);

        System.out.println();
        return toret;
    }

    private void inserta(Clinica coleccion, Clinica.Categoria categoria) throws Clinica.SinElementosException, Exception {

        switch (categoria) {
            case PACIENTE:
                Paciente p = new Privado("", "", new Fecha(0, 0, 0), "", "");
                switch (leeTipoPaciente()) {
                    case ASEGURADO:
                        p = new Asegurado("", "", "", new Fecha(0, 0, 0), "", "");
                        break;
                    case PRIVADO:
                        p = new Privado("", "", new Fecha(0, 0, 0), "", "");
                        break;
                }

                modifica((Paciente) p, coleccion);
                coleccion.inserta(p);
                break;

            case MEDICO:
                Medico m = new Medico("", "", "");
                modifica((Medico) m, coleccion);
                coleccion.inserta(m);
                break;

            case CITA:
                CitaMedica c = new CitaMedica(new Privado("", "", new Fecha(0, 0, 0), "", ""), new Fecha(0, 0, 0), new Hora(0, 0), new Medico("", "", ""));
                if (coleccion.getNumMedicos() > 0 && coleccion.getNumPacientes() > 0) {
                    modifica((CitaMedica) c, coleccion);
                    coleccion.inserta(c);
                } else {
                    throw new Clinica.SinElementosException("no se pueden insertar citas sin medicos o pacientes");
                }
                break;
        }
    }

    private void elimina(Clinica coleccion, Clinica.Categoria c) throws Clinica.ConCitaException, Clinica.SinElementosException, Exception {
        int i = 0;
        int pos;
        switch (c) {
            case PACIENTE:

                if (coleccion.getNumPacientes() > 0) {
                    pos = leePos(coleccion, Clinica.Categoria.PACIENTE);
                    while (i < coleccion.getNumCitas() && !(coleccion.getCita(i).getPaciente().equals(coleccion.getPaciente(pos)))) {
                        i++;
                    }
                    if (i == coleccion.getNumCitas()) {
                        coleccion.elimina(pos, Clinica.Categoria.PACIENTE);
                    } else {
                        throw new Clinica.ConCitaException("Este paciente tiene una cita asignada");
                    }

                } else {
                    throw new Clinica.SinElementosException("La coleccion no contiene pacientes.");
                }
                break;
            case MEDICO:

                if (coleccion.getNumMedicos() > 0) {
                    pos = leePos(coleccion, Clinica.Categoria.MEDICO);
                    while (i < coleccion.getNumCitas() && !(coleccion.getCita(i).getMedico().equals(coleccion.getMedico(pos)))) {
                        i++;
                    }
                    if (i == coleccion.getNumCitas()) {
                        coleccion.elimina(pos, Clinica.Categoria.MEDICO);
                    } else {
                        throw new Clinica.ConCitaException("Este medico tiene una cita asignada");
                    }

                } else {
                    throw new Clinica.SinElementosException("La coleccion no contiene medicos.");
                }
                break;
            case CITA:

                if (coleccion.getNumCitas() > 0) {
                    pos = leePos(coleccion, Clinica.Categoria.CITA);
                    coleccion.elimina(pos, Clinica.Categoria.CITA);
                } else {
                    throw new Clinica.SinElementosException("La coleccion no contiene citas.");
                }
                break;
        }

    }

    private void modifica(Clinica coleccion, Clinica.Categoria categoria) throws Clinica.SinElementosException, Exception {
        int pos;
        switch (categoria) {
            case PACIENTE:

                if (coleccion.getNumPacientes() == 0) {
                    throw new Clinica.SinElementosException("La coleccion no contiene pacientes.");

                } else {
                    pos = leePos(coleccion, Clinica.Categoria.PACIENTE);
                    this.modifica(coleccion.getPaciente(pos), coleccion);
                }
                break;
            case MEDICO:

                if (coleccion.getNumMedicos() == 0) {
                    throw new Clinica.SinElementosException("La coleccion no contiene medicos.");
                } else {
                    pos = leePos(coleccion, Clinica.Categoria.MEDICO);
                    this.modifica(coleccion.getMedico(pos), coleccion);
                }
            case CITA:
                if (coleccion.getNumCitas() == 0) {
                    throw new Clinica.SinElementosException("La coleccion no contiene citas.");
                } else {
                    pos = leePos(coleccion, Clinica.Categoria.CITA);
                    this.modifica(coleccion.getCita(pos), coleccion);
                }

        }

    }

    private void modifica(Object o, Clinica coleccion) throws Exception {
        String opc;
        Scanner teclado = new Scanner(System.in);
        if (o instanceof CitaMedica) {
            CitaMedica c = (CitaMedica) o;
            modificaFecha(c.getFecha());
            modificaHora(c.getHora());

            if (!c.getMedico().getNombre().trim().equalsIgnoreCase("")) {
                System.out.print("[" + c.getMedico().getNombre() + "]");
            }
            System.out.println("Médicos: ");

            for (int i = 0; i < coleccion.getNumMedicos(); i++) {
                System.out.println(i + " " + coleccion.getMedicos().get(i) + "\n");
            }
            System.out.println("Introduce el médico:");
            opc = teclado.nextLine().trim();
            if (opc.length() > 0) {
                c.setMedico(coleccion.getMedico(Integer.parseInt(opc)));
            }

            if (!c.getPaciente().getNombre().trim().equalsIgnoreCase("")) {
                System.out.print("[" + c.getPaciente().getNombre() + "]");
            }
            System.out.println("Pacientes: ");
            for (int i = 0; i < coleccion.getNumPacientes(); i++) {
                System.out.println(i + " " + coleccion.getPacientes().get(i) + "\n");
            }
            System.out.println("Que paciente necesita una cita?");
            opc = teclado.nextLine().trim();
            c.setPaciente(coleccion.getPaciente(Integer.parseInt(opc)));
        } else if (o instanceof Paciente) {
            Paciente p = (Paciente) o;
            // Numero de historial
            System.out.print("Numero de historial del paciente ");
            if (p.getNumHistorial().length() > 0) {
                System.out.print("[" + p.getNumHistorial() + "]");
            }
            opc = teclado.nextLine().trim();

            if (opc.length() > 0) {
                p.setNumHistorial(opc);
            }

            modificaPersona((Persona) p);

            modificaFecha(p.getFechaNacimiento());

            if (p instanceof Asegurado) {
                modificaAsegurado((Asegurado) p);
            } else if (p instanceof Privado) {
                modificaPrivado((Privado) p);
            }
        } else if (o instanceof Medico) {
            Medico m = (Medico) o;
            System.out.print("Número de colegiado ");
            if (m.getNumColegiado().length() > 0) {
                System.out.print("[" + m.getNumColegiado() + "]");
            }
            opc = teclado.nextLine().trim();

            if (opc.length() > 0) {
                m.setNumColegiado(opc);
            }
            modificaPersona((Persona) m);
        }
    }

    private void modificaHora(Hora h) {
        Scanner teclado = new Scanner(System.in);
        String info;

        // hora
        System.out.print("Hora");
        if (h.getHora() > 0) {
            System.out.print("[" + h.getHora() + "]");
        }
        System.out.print(": ");
        info = teclado.nextLine().trim();

        if (info.length() > 0) {
            h.setHora(Integer.parseInt(info));
        }
        // minutos
        System.out.print("Minutos");
        if (h.getMinutos() > 0) {
            System.out.print("[" + h.getMinutos() + "]");
        }
        System.out.print(": ");
        info = teclado.nextLine().trim();

        if (info.length() > 0) {
            h.setMinutos(Integer.parseInt(info));
        }
    }

    private void modificaFecha(Fecha f) {
        Scanner teclado = new Scanner(System.in);
        String info;

        // dia
        System.out.print("Dia");
        if (f.getDia() > 0) {
            System.out.print("[" + f.getDia() + "]");
        }
        System.out.print(": ");
        info = teclado.nextLine().trim();

        if (info.length() > 0) {
            f.setDia(Integer.parseInt(info));
        }
        // mes
        System.out.print("Mes");
        if (f.getMes() > 0) {
            System.out.print("[" + f.getMes() + "]");
        }
        System.out.print(": ");
        info = teclado.nextLine().trim();

        if (info.length() > 0) {
            f.setMes(Integer.parseInt(info));
        }
        // año
        System.out.print("Año");
        if (f.getAnho() > 0) {
            System.out.print("[" + f.getAnho() + "]");
        }
        System.out.print(": ");
        info = teclado.nextLine().trim();

        if (info.length() > 0) {
            f.setAnho(Integer.parseInt(info));
        }
    }

    private void modificaPrivado(Privado p) {
        String dni = "";
        Scanner teclado = new Scanner(System.in);
        if (p.getDni().length() > 0) {
            System.out.print("[" + p.getDni() + "]");
        }
        System.out.println("Introduce el dni:");
        dni = teclado.nextLine().trim();
        if (dni.length() > 0) {
            p.setDni(dni);
        }
    }

    private void modificaAsegurado(Asegurado a) {
        Scanner teclado = new Scanner(System.in);
        String poliza = "";
        String companhia = "";
        if (a.getPoliza().length() > 0) {
            System.out.println("[" + a.getPoliza() + "]");
        }
        System.out.println("Introduce la poliza ");
        poliza = teclado.nextLine().trim();
        if (poliza.length() > 0) {
            a.setPoliza(poliza);
        }
        if (a.getCompanhia().length() > 0) {
            System.out.println("[" + a.getCompanhia() + "]");
        }
        System.out.println("Introduce la compañia ");
        companhia = teclado.nextLine().trim();
        if (companhia.length() > 0) {
            a.setCompanhia(companhia);
        }
    }

    private void modificaPersona(Persona p) {
        Scanner teclado = new Scanner(System.in);
        String nombre;
        String dom;
        // Nombre
        System.out.print("Nombre ");
        if (p.getNombre().length() > 0) {
            System.out.print("[" + p.getNombre() + "]");
        }
        nombre = teclado.nextLine().trim();

        if (nombre.length() > 0) {
            p.setNombre(nombre);
        }

        // Domicilio
        System.out.print("Domicilio ");
        if (p.getDomicilio().length() > 0) {
            System.out.print("[" + p.getDomicilio() + "]");
        }
        dom = teclado.nextLine().trim();

        if (dom.length() > 0) {
            p.setDomicilio(dom);
        }
    }

    private int leePos(Clinica coleccion, Clinica.Categoria categoria) {
        int toret = 0;

        switch (categoria) {

            case PACIENTE:
                final int numPacientes = coleccion.getNumPacientes();

                do {
                    toret = leeNum("Introduzca posición del paciente (1..."
                            + numPacientes + "): ");
                } while (toret < 1
                        || toret > numPacientes);
                break;

            case MEDICO:
                final int numMedicos = coleccion.getNumMedicos();

                do {
                    toret = leeNum("Introduzca posición del medico (1..."
                            + numMedicos + "): ");
                } while (toret < 1
                        || toret > numMedicos);
                break;

            case CITA:
                final int numCitas = coleccion.getNumCitas();

                do {
                    toret = leeNum("Introduzca posición de la cita (1..."
                            + numCitas + "): ");
                } while (toret < 1
                        || toret > numCitas);
                break;

        }

        return toret - 1;
    }

    private Paciente.Tipo leeTipoPaciente() {
        Scanner entrada = new Scanner(System.in);
        int opc;
        do {
            System.out.println("Elige un tipo: ");
            for (int i = 0; i < Paciente.Tipo.values().length; i++) {
                System.out.println(i + " " + Paciente.Tipo.values()[i]);
            }
            opc = Integer.parseInt(entrada.nextLine());
        } while (opc != 0 && opc != 1);
        return Paciente.Tipo.values()[opc];
    }

}

package es.uvigo.esei.pro2.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.Serializer;

public class Clinica {

    public static class GestClinicaException extends Exception {

        public GestClinicaException(String msg) {
            super(msg);
        }
    }

    public static class LimiteException extends GestClinicaException {

        public LimiteException(String msg) {
            super(msg);
        }

    }

    public static class SinElementosException extends GestClinicaException {

        public SinElementosException(String msg) {
            super(msg);
        }

    }

    public static class ConCitaException extends GestClinicaException {

        public ConCitaException(String msg) {
            super(msg);
        }

    }
    //**************************************************************************************************************************************
    //SE DECLARAN CONSTANTES QUE REPRESENTAN LAS ETIQUETAS PARA CADA VALOR
    public static final String ETQ_CLINICA = "clinica";//la constante de la clase es pública para poder acceder
    //**************************************************************************************************************************************
    public static enum Categoria {
        MEDICO, CITA, PACIENTE
    };
    private ArrayList<Paciente> pacientes;
    private ArrayList<Medico> medicos;
    private ArrayList<CitaMedica> citasMedicas;

    private String nombre;

    public Clinica(int maxPacientes, int maxMedicos, int maxCitas, String nombre) {
        this.nombre = nombre;
        pacientes = new ArrayList<Paciente>(maxPacientes);
        medicos = new ArrayList<Medico>(maxMedicos);
        citasMedicas = new ArrayList<CitaMedica>(maxCitas);
    }
    public Clinica(){
        pacientes = new ArrayList<Paciente>();
        medicos = new ArrayList<Medico>();
        citasMedicas = new ArrayList<CitaMedica>();
    }
     //**************************************************************************************************************************************
    //LEER DE UN FICHERO XML MEDIANTE UN CONSTRUCTOR AL QUE SE LE PASA COMO PARÁMETRO EL NOMBRE DEL ARCHIVO XML
    public Clinica(String nf, String nf2, String nf3) throws ParsingException, IOException, LimiteException {
        this(); //llamada al constructor de la clase
        Builder leer = new Builder();
        
        //leer el arbol de nodos
        File fich = new File (nf);
        Document docPaciente = leer.build(fich);
        Element raizPaciente = docPaciente.getRootElement();
        Elements eltoPaciente = raizPaciente.getChildElements();
//        Elements eltoPaciente= leer.build(new File(nf)).getRootElement().getChildElements();
        Elements eltoMedico= leer.build(new File(nf2)).getRootElement().getChildElements();
        Elements eltoCita= leer.build(new File(nf3)).getRootElement().getChildElements();
       
        //crear los objetos
        for (int i = 0; i <eltoPaciente.size(); i++) {
            Element paciente= eltoPaciente.get(i);
            Paciente p;
            switch(paciente.getLocalName()){
                case Privado.ETQ_PRIVADO:
                    p=new Privado(paciente);
                    break;
                case Asegurado.ETQ_ASEGURADO:
                    p=new Asegurado(paciente);
                    break;
                default:
                    throw new ParsingException("Falta privado/asegurado");
            }
            pacientes.add(p);
        }
        for (int i = 0; i <eltoMedico.size(); i++) {
            Element medico= eltoMedico.get(i);
            
            if(medico==null){
                throw new ParsingException("Falta medico");
            }
            medicos.add(new Medico(medico));
        }
        for (int i = 0; i < eltoCita.size(); i++) {
            Element cita= eltoCita.get(i);
            
            if(cita==null){
                throw new ParsingException("Falta CITA MEDICA");
            }
            citasMedicas.add(new CitaMedica(cita));
        }
    }
 //**************************************************************************************************************************************
    public Paciente getPaciente(int pos) throws LimiteException {
        if (pos >= pacientes.size()) {
            throw new LimiteException("get(): sobrepasa la pos: " + (pos + 1)
                    + " / " + getMaxPacientes());
        }

        return pacientes.get(pos);
    }

    public Medico getMedico(int pos) throws LimiteException {
        if (pos >= medicos.size()) {
            throw new LimiteException("get(): sobrepasa la pos: " + (pos + 1)
                    + " / " + getMaxMedicos());
        }

        return medicos.get(pos);
    }

    public CitaMedica getCita(int pos) throws LimiteException {
        if (pos >= citasMedicas.size()) {
            throw new LimiteException("get(): sobrepasa la pos: " + (pos + 1)
                    + " / " + getMaxCitas());
        }

        return citasMedicas.get(pos);
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public ArrayList<Medico> getMedicos() {
        return medicos;
    }

    public ArrayList<CitaMedica> getCitasMedicas() {
        return citasMedicas;
    }

    public int getNumMedicos() {
        return medicos.size();
    }

    public int getNumPacientes() {
        return pacientes.size();
    }

    public int getNumCitas() {
        return citasMedicas.size();
    }

    public int getMaxPacientes() {
        return pacientes.size();
    }

    public int getMaxMedicos() {
        return medicos.size();
    }

    public int getMaxCitas() {
        return citasMedicas.size();
    }

    public void inserta(Object o) throws LimiteException {

        if (o instanceof Paciente ) {
            pacientes.add((Paciente) o);
        } else if (o instanceof Medico ) {
            medicos.add((Medico) o);
        } else if (o instanceof CitaMedica ) {
            citasMedicas.add((CitaMedica) o);
        }
    }

    public void elimina(int pos, Categoria c) throws LimiteException {
        switch (c) {
            case PACIENTE:
                if (pos >= pacientes.size()) {
                    throw new LimiteException("elimina(): sobrepasa el numero de pacientes: "
                            + pacientes.size());
                }
                pacientes.remove(pos);
                break;
            case MEDICO:
                if (pos >= medicos.size()) {
                    throw new LimiteException("elimina(): sobrepasa el numero de medicos: "
                            + medicos.size());
                }
                medicos.remove(pos);
                break;
            case CITA:
                if (pos >= citasMedicas.size()) {
                    throw new LimiteException("elimina(): sobrepasa el numero de citas: "
                            + citasMedicas.size());
                }
                citasMedicas.remove(pos);
                break;
        }
    }
 //**************************************************************************************************************************************
    public Element toDom(Categoria c) throws LimiteException {
        Element toret = new Element(ETQ_CLINICA);
        switch (c) {
            case PACIENTE:
                for (int i = 0; i < pacientes.size(); i++) {
                    toret.appendChild(getPaciente(i).toDOM());
                }   break;
            case MEDICO:
                for (int i = 0; i < medicos.size(); i++) {
                    toret.appendChild(getMedico(i).toDOM());
                }   break;
            case CITA:
                for (int i = 0; i < citasMedicas.size(); i++) {
                    toret.appendChild(getCita(i).toDOM());
                }   break;
            default:
                break;
        }
        return toret;
    }
    

    public void toXML(String nf, Categoria c) throws IOException, LimiteException {
        FileOutputStream f = new FileOutputStream(nf);
        Serializer serial = new Serializer(f);
        // DEPENDIENDO DE LA CATEGORIA LA SERIALIZACION SE REALIZARA SOLO CON
        // UNO DE LOS ELEMENTOS YA QUE EL OBJETO SERIAL SOLO ESTARÁ RELACIONADO
        // CON EL FICHERO XML DE ESA CATEGORIA
        switch (c) {
            case PACIENTE:
                serial.write(new Document(toDom(Clinica.Categoria.PACIENTE)));
                break;
            case MEDICO:
                serial.write(new Document(toDom(Clinica.Categoria.MEDICO)));
                break;
            case CITA:
                serial.write(new Document(toDom(Clinica.Categoria.CITA)));
                break;
            default:
                break;
        }
//        serial.write(new Document(toDom(Clinica.Categoria.PACIENTE)));
//        serial.write(new Document(toDom(Clinica.Categoria.MEDICO)));
//        serial.write(new Document(toDom(Clinica.Categoria.CITA)));
        f.close();

    }
 //**************************************************************************************************************************************
    public String toString(Categoria c) throws SinElementosException {
        StringBuilder toret = new StringBuilder();
        switch (c) {
            case PACIENTE:
                final int numPacientes = pacientes.size();

                if (numPacientes > 0) {
                    for (int i = 0; i < numPacientes; i++) {
                        toret.append((i + 1)).append(". ");
                        toret.append(pacientes.get(i).toString()).append("\n");
                    }
                } else {
                    throw new SinElementosException("No hay pacientes");
                }
                break;
            case MEDICO:
                final int numMedicos = medicos.size();

                if (numMedicos > 0) {
                    for (int i = 0; i < numMedicos; i++) {
                        toret.append((i + 1)).append(". ");
                        toret.append(medicos.get(i)).append("\n");
                    }
                } else {
                    throw new SinElementosException("No hay medicos.");
                }
                break;
            case CITA:
                final int numCitas = citasMedicas.size();

                if (numCitas > 0) {
                    for (int i = 0; i < numCitas; i++) {
                        toret.append((i + 1)).append(". ");
                        toret.append(citasMedicas.get(i).toString()).append("\n");
                    }
                } else {
                    throw new SinElementosException("No hay citas.");
                }
                break;

        }
        return toret.toString();
    }

    public String listarPacientesPorTipo(Paciente.Tipo t) throws SinElementosException {
        StringBuilder toret = new StringBuilder();
        int numPac = pacientes.size();
        for (int i = 0; i < numPac; i++) {
            switch (t) {
                case ASEGURADO:
                    if (pacientes.get(i) instanceof Asegurado) {
                        toret.append("\n\t").append(pacientes.get(i).toString());
                    }
                    break;
                case PRIVADO:
                    if (pacientes.get(i) instanceof Privado) {
                        toret.append("\n\t").append(pacientes.get(i).toString());
                    }
                    break;
            }
        }

        if (toret.length() == 0) {
            throw new SinElementosException("No existen pacientes de ese tipo ");
        }
        return toret.toString();
    }

    public String listarCitaPorMedico(String num, Fecha f) throws SinElementosException {
        StringBuilder toret = new StringBuilder();
        Medico m = new Medico("", "", "");
        for (int i = 0; i < medicos.size() && !getMedicos().get(i).getNumColegiado().equals(num); i++) {
            m = getMedicos().get(i);
        }
        for (int j = 0; j < citasMedicas.size(); j++) {
            if (getCitasMedicas().get(j).getMedico().equals(m) && getCitasMedicas().get(j).getFecha().equals(f)) {
                toret.append(getCitasMedicas().get(j));
            } else {
                toret.append("El médico no tiene citas en esta fecha");
            }
        }
        toret.append("\nMédico:\n");
        toret.append(m);
        return toret.toString();
    }
}

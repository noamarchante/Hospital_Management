package es.uvigo.esei.pro2.core;

import nu.xom.Element;
import nu.xom.ParsingException;

public class CitaMedica {
//**************************************************************************************************************************************
    //SE DECLARAN CONSTANTES QUE REPRESENTAN LAS ETIQUETAS PARA CADA VALOR

    public static final String ETQ_CITA_MEDICA = "citaMedica";//la constante de la clase es pública para poder acceder
//**************************************************************************************************************************************
    private Paciente paciente;
    private Fecha fecha;
    private Hora hora;
    private Medico medico;

    public CitaMedica(Paciente paciente, Fecha fecha, Hora hora, Medico medico) {
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
    }

    public CitaMedica(Paciente paciente, Medico medico, int dia, int mes, int año, int hora, int minutos) {
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = new Fecha(dia, mes, año);
        this.hora = new Hora(hora, minutos);
    }

    //**************************************************************************************************************************************
    //LEER DEL FICHERO XML MEDIANTE UN CONSTRUCTOR AL QUE SE LE PASA UN ELEMENT COMO PARÁMETRO
    public CitaMedica(Element e) throws ParsingException {
        System.out.println("citaMedica");
        //se crean elementos para cada variable a los que se le asigna aquellos elementos con la etiqueta correspondiente
        Element eltoMedico = e.getFirstChildElement(Medico.ETQ_MEDICO);
        Element eltoFecha = e.getFirstChildElement(Fecha.ETQ_FECHA);
        Element eltoHora = e.getFirstChildElement(Hora.ETQ_HORA);

        // TAL COMO HAS IMPLEMENTADO EL MÉTODO TODOM DE ESTA CLASE 
        // EN LA CITA MEDICA NUNCA VAS A TENER LA ETIQUETA ETQ_PACIENTE,
        // PERO EN ESTE CONSTRUCTOR BUSCAS UN NODO CON ESA ETIQUETA. OJO!!!
        // FIJATE QUE UN PACIENTE SERÁ ASEGURADO O PRIVADO, POR LO QUE PODRÁS
        // TENER ALGUNA DE ESAS ETIQUETAS PERO NUNCA LA DE PACIENTE
        // MODIFICO EL TODOM PARA QUE EXISTA ESA ETIQUETA
        Element eltoPaciente = e.getFirstChildElement(Paciente.ETQ_PACIENTE);

        //se comprueba que los elementos no están vacíos
        if (eltoPaciente == null) {
            throw new ParsingException("Falta paciente");
        }
        if (eltoMedico == null) {
            throw new ParsingException("Falta medico");
        }
        if (eltoFecha == null) {
            throw new ParsingException("Falta fecha");
        }
        if (eltoHora == null) {
            throw new ParsingException("Falta hora");
        }

        //se asigna a la variable el valor almacenado por el element
        // EL NODO HIJO DEL ELTOPACIENTE SERÁ EL QUE TENGA LA ETIQUETA
        // DE PRIVADO U ASEGURADO.
        // OS ACONSEJO QUE OS DIBUJEIS EL ÁRBOL DE NODOS. CREO QUE GRAN PARTE
        // DE VUESTROS ERRORES SON DEBIDOS A QUE NO SEGUIS EL ÁRBOL DE NODOS
        // NI PARA CREARLO NI PARA LEERLO
        Element eltoAsegurado = eltoPaciente.getFirstChildElement(Asegurado.ETQ_ASEGURADO);
        Element eltoPrivado = eltoPaciente.getFirstChildElement(Privado.ETQ_PRIVADO);
        if (eltoAsegurado != null) {
            this.paciente = new Asegurado(eltoAsegurado);
        }
        if (eltoPrivado != null) {
            this.paciente = new Privado(eltoPrivado);
        }
//        if (eltoPaciente.getLocalName().equals(Privado.ETQ_PRIVADO)) {
//            this.paciente = new Privado(eltoPaciente);
//        } else {
//            this.paciente = new Asegurado(eltoPaciente);
//        }
        this.medico = new Medico(eltoMedico);
        this.fecha = new Fecha(eltoFecha);
        this.hora = new Hora(eltoHora);
    }
//**************************************************************************************************************************************

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public boolean equals(CitaMedica c) {
        boolean res;
        if (this.paciente.equals(c.paciente) && this.medico.equals(c.medico) && this.hora.equals(c.hora) && this.fecha.equals(c.fecha)) {
            res = true;
        } else {
            res = false;
        }
        return res;
    }
//**************************************************************************************************************************************
    //CREACIÓN DE LA ESTRUCTURA XML MEDIANTE UN MÉTODO TODOM()

    public Element toDOM() {

        //se crean los elementos correspondientes a la estructura del xml (propios de la clase)
        Element raiz = new Element(ETQ_CITA_MEDICA);

        //se le asigna a cada elemento la variable que guarda el valor
        //se le asigna al elemento raiz cada uno de los elementos que engloba
        // PARA QUE EN EL XML EXISTA LA ETQ_PACIENTE DEBES CREAR EL NODO CORRESPONDIENTE
        // AQUI
        Element eltoPaciente = new Element(Paciente.ETQ_PACIENTE);
        raiz.appendChild(eltoPaciente);

        eltoPaciente.appendChild(getPaciente().toDOM());//composición: se llama al toDom() de la clase

        raiz.appendChild(getMedico().toDOM());//composición: se llama al toDom() de la clase
        raiz.appendChild(getFecha().toDom());//composición: se llama al toDom() de la clase
        raiz.appendChild(getHora().toDom());//composición: se llama al toDom() de la clase

        return raiz;
    }
//**************************************************************************************************************************************

    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(getPaciente().toString()).append(", ").append(getFecha().toString()).append(", ").append(getHora().toString()).append(", ").append(getMedico().toString());
        return toret.toString();
    }

}

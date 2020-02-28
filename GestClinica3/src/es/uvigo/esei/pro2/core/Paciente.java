package es.uvigo.esei.pro2.core;

import nu.xom.*;

public abstract class Paciente extends Persona {

    //**************************************************************************************************************************************
    //SE DECLARAN CONSTANTES QUE REPRESENTAN LAS ETIQUETAS PARA CADA VALOR
    public static final String ETQ_PACIENTE = "paciente";//la constante de la clase es pública para poder acceder
    private static final String ETQ_NUM_HIST = "numHistorial";
    private static final String ETQ_FECHA_NAC = "fechaNacimiento";

    //**************************************************************************************************************************************
    private String numHistorial;
    private Fecha fechaNacimiento;

    public static enum Tipo {
        ASEGURADO, PRIVADO
    };

    public Paciente(String numHistorial, Fecha fechaNacimiento, String nombre, String domicilio) {
        super(nombre, domicilio);
        this.numHistorial = numHistorial;
        this.fechaNacimiento = fechaNacimiento;
    }

    //**************************************************************************************************************************************
    //LEER DEL FICHERO XML MEDIANTE UN CONSTRUCTOR AL QUE SE LE PASA UN ELEMENT COMO PARÁMETRO
    public Paciente(Element e) throws ParsingException {
        
        super(e); //se llama al constructor utlizado para leer el xml de la superclase

        //se crean elementos para cada variable a los que se le asigna aquellos elementos con la etiqueta correspondiente
        Element eltoNumHistorial = e.getFirstChildElement(ETQ_NUM_HIST);
        
        // LA ETIQUETA QUE ALMACENAS EN EL FICHERO XML NO ES "fechaNacimiento"
        // ES "fecha"
        Element eltoFechaNacimiento = e.getFirstChildElement(ETQ_FECHA_NAC);

        //se comprueba que los elementos no están vacíos
        if (eltoNumHistorial == null) {
            throw new ParsingException("Falta numero historial");
        }
        
        if (eltoFechaNacimiento == null) {
            throw new ParsingException("Falta fecha");
        }

        //se asigna a la variable el valor almacenado por el element
        this.numHistorial = eltoNumHistorial.getValue();
        
        this.fechaNacimiento = new Fecha(eltoFechaNacimiento);
       
    }

    //**************************************************************************************************************************************
    public String getNumHistorial() {
        return numHistorial;
    }

    public void setNumHistorial(String nH) {
        numHistorial = nH;
    }

    public Fecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean equals(Paciente p) {
        boolean res;
        if (super.equals(p) && this.numHistorial.equals(p.numHistorial) && this.fechaNacimiento.equals(p.fechaNacimiento)) {
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
        Element raiz = super.toDOM();//el elemento debe contener todos los datos (superclase y la clase actual)
        Element eltoNumHist = new Element(ETQ_NUM_HIST);

        //se cambia la etiqueta del elemento raiz para que se corresponda con la de la clase
        raiz.setLocalName(ETQ_PACIENTE);

        //se le asigna a cada elemento la variable que guarda el valor
        // USA LOS MÉTODOS GETS PARA ACCEDER A LOS ATRIBUTOS
        eltoNumHist.appendChild(numHistorial);

        //se le asigna al elemento raiz cada uno de los elementos que engloba
        raiz.appendChild(eltoNumHist);
       
     //   raiz.appendChild(getFechaNacimiento().toDom());//composición: se llama al toDom() de la clase
     // SI QUIERES QUE EN EL FICHERO PACIENTE.XML SE GUARDE LA ETIQUETA
     // "fechaNacimiento" EN VEZ DE LA ETIQUETA "fecha" ES NECESARIO
     // ACTUALIZAR LA ETIQUETA DE DICHO NODO
        Element fecha = getFechaNacimiento().toDom();
        fecha.setLocalName(ETQ_FECHA_NAC);
        raiz.appendChild(fecha);
        
        return raiz;
    }

    //**************************************************************************************************************************************
    public String toString() {
        StringBuilder toret = new StringBuilder();

        toret.append(super.toString()).append(", ");
        toret.append(getNumHistorial());
        toret.append(getFechaNacimiento().toString()).append(", ");

        return toret.toString();
    }
}

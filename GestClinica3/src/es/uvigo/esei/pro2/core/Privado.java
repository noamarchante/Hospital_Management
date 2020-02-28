package es.uvigo.esei.pro2.core;

import nu.xom.Element;
import nu.xom.ParsingException;

public class Privado extends Paciente {

    //**************************************************************************************************************************************
    //SE DECLARAN CONSTANTES QUE REPRESENTAN LAS ETIQUETAS PARA CADA VALOR
    public static final String ETQ_PRIVADO = "privado";//la constante de la clase es pública para poder acceder
    private static final String ETQ_DNI = "dni";

    //**************************************************************************************************************************************
    private String dni;

    public Privado(String dni, String numHistorial, Fecha fechaNacimiento, String nombre, String domicilio) {
        super(numHistorial, fechaNacimiento, nombre, domicilio);
        this.dni = dni;
    }

    //**************************************************************************************************************************************
    //LEER DEL FICHERO XML MEDIANTE UN CONSTRUCTOR AL QUE SE LE PASA UN ELEMENT COMO PARÁMETRO
    public Privado(Element e) throws ParsingException {
        super(e);//se llama al constructor utlizado para leer el xml de la superclase
        
        //se crean elementos para cada variable a los que se le asigna aquellos elementos con la etiqueta correspondiente
        Element eltoDni = e.getFirstChildElement(ETQ_DNI);

        //se comprueba que los elementos no están vacíos
        if (eltoDni == null) {
            throw new ParsingException("Falta numero dni");
        }

        //se asigna a la variable el valor almacenado por el element
        this.dni = eltoDni.getValue();
    }

    //**************************************************************************************************************************************
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean equals(Privado p) {
        boolean res;
        if (super.equals(p) && this.dni.equals(p.dni)) {
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
        Element eltoDni = new Element(ETQ_DNI);

        //se cambia la etiqueta del elemento raiz para que se corresponda con la de la clase
        raiz.setLocalName(ETQ_PRIVADO);

        //se le asigna a cada elemento la variable que guarda el valor
        eltoDni.appendChild(dni);

        //se le asigna al elemento raiz cada uno de los elementos que engloba
        raiz.appendChild(eltoDni);

        return raiz;
    }

    //**************************************************************************************************************************************
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(super.toString()).append("Privado ").append(", ").append(getDni());
        return toret.toString();
    }

}

package es.uvigo.esei.pro2.core;

import nu.xom.Element;
import nu.xom.ParsingException;

public abstract class Persona {

    //SE DECLARAN CONSTANTES QUE REPRESENTAN LAS ETIQUETAS PARA CADA VALOR
    public static final String ETQ_PERSONA = "persona"; //la constante de la clase es pública para poder acceder
    private static final String ETQ_NOMBRE = "nombre";
    private static final String ETQ_DOMICILIO = "domicilio";

    //**************************************************************************************************************************************
    private String nombre;
    private String domicilio;

    public Persona(String nombre, String domicilio) {
        this.nombre = nombre;
        this.domicilio = domicilio;
    }
    //**************************************************************************************************************************************

    //LEER DEL FICHERO XML MEDIANTE UN CONSTRUCTOR AL QUE SE LE PASA UN ELEMENT COMO PARÁMETRO
    public Persona(Element e) throws ParsingException {
       
        //se crean elementos para cada variable a los que se le asigna aquellos elementos con la etiqueta correspondiente
        Element eltoNombre = e.getFirstChildElement(ETQ_NOMBRE);
        Element eltoDomicilio = e.getFirstChildElement(ETQ_DOMICILIO);

        //se comprueba que los elementos no están vacíos
        if (eltoNombre == null) {
            throw new ParsingException("Falta nombre");
        }
        if (eltoDomicilio == null) {
            throw new ParsingException("Falta domicilio");
        }

        //se asigna a la variable el valor almacenado por el element
        this.nombre = eltoNombre.getValue().trim();
        this.domicilio = eltoNombre.getValue().trim();
    }

    //**************************************************************************************************************************************
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public boolean equals(Persona p) {
        boolean res;
        if (this.nombre.equals(p.nombre) && this.domicilio.equals(p.domicilio)) {
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
        Element raiz = new Element(ETQ_PERSONA);//elemento raiz que engloba a todos los elementos
        Element eltoNombre = new Element(ETQ_NOMBRE);
        Element eltoDomicilio = new Element(ETQ_DOMICILIO);

        //se le asigna a cada elemento la variable que guarda el valor
        // MÉTODOS GETS NO ACCESO DIRECTO A LOS ATRIBUTOS        
        eltoNombre.appendChild(nombre);
        eltoDomicilio.appendChild(domicilio);

        //se le asigna al elemento raiz cada uno de los elementos que engloba
        raiz.appendChild(eltoNombre);
        raiz.appendChild(eltoDomicilio);

        return raiz;
    }

    //**************************************************************************************************************************************
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(getNombre()).append(", ").append(getDomicilio());
        return toret.toString();
    }
}

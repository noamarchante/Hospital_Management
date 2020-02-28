package es.uvigo.esei.pro2.core;

import nu.xom.Element;
import nu.xom.ParsingException;

public class Asegurado extends Paciente {

    //**************************************************************************************************************************************
    //SE DECLARAN CONSTANTES QUE REPRESENTAN LAS ETIQUETAS PARA CADA VALOR
    public static final String ETQ_ASEGURADO = "asegurado";//la constante de la clase es pública para poder acceder
    private static final String ETQ_POLIZA = "poliza";
    private static final String ETQ_COMP = "compañia";

    //**************************************************************************************************************************************
    private String poliza;
    private String companhia;

    public Asegurado(String poliza, String companhia, String numHistorial, Fecha fechaNacimiento, String nombre, String domicilio) {
        super(numHistorial, fechaNacimiento, nombre, domicilio);
        this.poliza = poliza;
        this.companhia = companhia;
    }

    //**************************************************************************************************************************************
    //LEER DEL FICHERO XML MEDIANTE UN CONSTRUCTOR AL QUE SE LE PASA UN ELEMENT COMO PARÁMETRO
    public Asegurado(Element e) throws ParsingException {
        super(e);//se llama al constructor utlizado para leer el xml de la superclase
        //se crean elementos para cada variable a los que se le asigna aquellos elementos con la etiqueta correspondiente
        Element eltoPoliza = e.getFirstChildElement(ETQ_POLIZA);
        Element eltoComp = e.getFirstChildElement(ETQ_COMP);

        //se comprueba que los elementos no están vacíos
        if (eltoPoliza == null) {
            throw new ParsingException("Falta poliza");
        }

        if (eltoComp == null) {
            throw new ParsingException("Falta compañia");
        }

        //se asigna a la variable el valor almacenado por el element
        this.companhia = eltoComp.getValue();
        this.poliza = eltoPoliza.getValue();
    }

//**************************************************************************************************************************************
    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    public String getCompanhia() {
        return companhia;
    }

    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    public boolean equals(Asegurado a) {
        boolean res;
        if (super.equals(a) && this.companhia.equals(a.companhia) && this.poliza.equals(a.poliza)) {
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
        Element eltoPoliza = new Element(ETQ_POLIZA);
        Element eltoComp = new Element(ETQ_COMP);

        //se cambia la etiqueta del elemento raiz para que se corresponda con la de la clase
        raiz.setLocalName(ETQ_ASEGURADO);

        //se le asigna a cada elemento la variable que guarda el valor
        eltoPoliza.appendChild(poliza);
        eltoComp.appendChild(companhia);

        //se le asigna al elemento raiz cada uno de los elementos que engloba
        raiz.appendChild(eltoPoliza);
        raiz.appendChild(eltoComp);

        return raiz;
    }

    //**************************************************************************************************************************************
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(super.toString()).append("Asegurado ").append(", ").append(getPoliza()).append(", ").append(getCompanhia());
        return toret.toString();
    }
}

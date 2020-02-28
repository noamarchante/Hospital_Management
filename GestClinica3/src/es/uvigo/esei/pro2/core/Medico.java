package es.uvigo.esei.pro2.core;

import nu.xom.Element;
import nu.xom.ParsingException;

public class Medico extends Persona {
    //**************************************************************************************************************************************

    //SE DECLARAN CONSTANTES QUE REPRESENTAN LAS ETIQUETAS PARA CADA VALOR
    public static final String ETQ_MEDICO = "medico";//la constante de la clase es pública para poder acceder
    private static final String ETQ_NUM_COL = "numColegiado";

    //**************************************************************************************************************************************
    private String numColegiado;

    public Medico(String numColegiado, String nombre, String domicilio) {
        super(nombre, domicilio);
        this.numColegiado = numColegiado;
    }

    //**************************************************************************************************************************************
    //LEER DEL FICHERO XML MEDIANTE UN CONSTRUCTOR AL QUE SE LE PASA UN ELEMENT COMO PARÁMETRO
    public Medico(Element e) throws ParsingException {
        super(e); //se llama al constructor utlizado para leer el xml de la superclase
        //se crean elementos para cada variable a los que se le asigna aquellos elementos con la etiqueta correspondiente
        Element eltoNumCol = e.getFirstChildElement(ETQ_NUM_COL);

        //se comprueba que los elementos no están vacíos
        if (eltoNumCol == null) {
            throw new ParsingException("Falta numero colegiado");
        }

        //se asigna a la variable el valor almacenado por el element
        this.numColegiado = eltoNumCol.getValue();
    }

    //**************************************************************************************************************************************
    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
    }

    public boolean equals(Medico m) {
        boolean res;
        if (super.equals(m) && this.numColegiado.equals(m.numColegiado)) {
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
        Element raiz = super.toDOM(); //el elemento debe contener todos los datos (superclase y la clase actual)
        Element eltoNumCol = new Element(ETQ_NUM_COL);

        //se cambia la etiqueta del elemento raiz para que se corresponda con la de la clase
        raiz.setLocalName(ETQ_MEDICO); 
        
        //se le asigna a cada elemento la variable que guarda el valor
        eltoNumCol.appendChild(numColegiado);

        //se le asigna al elemento raiz cada uno de los elementos que engloba
        raiz.appendChild(eltoNumCol);

        return raiz;
    }

//**************************************************************************************************************************************
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(getNumColegiado()).append(",").append(getNombre()).append(",").append(getDomicilio());
        return toret.toString();
    }
}

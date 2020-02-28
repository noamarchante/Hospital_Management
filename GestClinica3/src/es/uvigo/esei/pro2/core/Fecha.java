package es.uvigo.esei.pro2.core;

import nu.xom.Element;
import nu.xom.ParsingException;

public class Fecha {

    //**************************************************************************************************************************************
   //SE DECLARAN CONSTANTES QUE REPRESENTAN LAS ETIQUETAS PARA CADA VALOR
    public final static String ETQ_FECHA = "fecha";//la constante de la clase es pública para poder acceder
    private final static String ETQ_DIA = "dia";
    private final static String ETQ_MES = "mes";
    private final static String ETQ_ANHO = "anho";

    //**************************************************************************************************************************************
    private int dia;
    private int mes;
    private int anho;

    public Fecha(int dia, int mes, int anho) {
        this.dia = dia;
        this.mes = mes;
        this.anho = anho;
    }

    //**************************************************************************************************************************************
    //LEER DEL FICHERO XML MEDIANTE UN CONSTRUCTOR AL QUE SE LE PASA UN ELEMENT COMO PARÁMETRO
    public Fecha(Element e) throws ParsingException {
        //se crean elementos para cada variable a los que se le asigna aquellos elementos con la etiqueta correspondiente
        Element eltoDia = e.getFirstChildElement(ETQ_DIA);
        Element eltoMes = e.getFirstChildElement(ETQ_MES);
        Element eltoAnho = e.getFirstChildElement(ETQ_ANHO);
       
        //se comprueba que los elementos no están vacíos
        if (eltoDia == null) {
            throw new ParsingException("Falta el DIA en el elemento fecha");
        }
        if (eltoMes == null) {
            throw new ParsingException("Falta el MES en el elemento fecha");
        }
        if (eltoAnho == null) {
            throw new ParsingException("Falta el ANHO en el elemento fecha");
        }
        
        //se asigna a la variable el valor almacenado por el element
        this.dia = Integer.parseInt(eltoDia.getValue().trim());
        this.mes = Integer.parseInt(eltoMes.getValue().trim());
        this.anho = Integer.parseInt(eltoAnho.getValue().trim());
    }

    //**************************************************************************************************************************************
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    public boolean equals(Fecha f) {
        if (f.dia == this.dia && f.mes == mes && f.anho == this.anho) {
            return true;
        } else {
            return false;
        }
    }

    //**************************************************************************************************************************************
   
    //CREACIÓN DE LA ESTRUCTURA XML MEDIANTE UN MÉTODO TODOM()
    public Element toDom() {
        
        //se crean los elementos correspondientes a la estructura del xml (propios de la clase)
        Element raiz = new Element(ETQ_FECHA);
        Element eltoDia = new Element(ETQ_DIA);
        Element eltoMes = new Element(ETQ_MES);
        Element eltoAnho = new Element(ETQ_ANHO);

        //se le asigna a cada elemento la variable que guarda el valor
        eltoDia.appendChild(Integer.toString(dia));
        eltoMes.appendChild(Integer.toString(mes));
        eltoAnho.appendChild(Integer.toString(anho));

        //se le asigna al elemento raiz cada uno de los elementos que engloba
        raiz.appendChild(eltoDia);
        raiz.appendChild(eltoMes);
        raiz.appendChild(eltoAnho);

        return raiz;
    }

    //**************************************************************************************************************************************
    public String toString() {
        return String.format("%d/%d/%d", dia, mes, anho);
    }

}

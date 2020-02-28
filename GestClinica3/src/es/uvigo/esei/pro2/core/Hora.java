package es.uvigo.esei.pro2.core;

import static es.uvigo.esei.pro2.core.Fecha.ETQ_FECHA;

import nu.xom.Element;
import nu.xom.ParsingException;

public class Hora {
    //**************************************************************************************************************************************
    //SE DECLARAN CONSTANTES QUE REPRESENTAN LAS ETIQUETAS PARA CADA VALOR
    public final static String ETQ_HORA = "hora";//la constante de la clase es pública para poder acceder
    private final static String ETQ_HORA2 = "hora";
    private final static String ETQ_MINUTOS = "minutos";
    //**************************************************************************************************************************************

    private int hora;
    private int minutos;

    public Hora(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    //**************************************************************************************************************************************
    //LEER DEL FICHERO XML MEDIANTE UN CONSTRUCTOR AL QUE SE LE PASA UN ELEMENT COMO PARÁMETRO
    public Hora(Element e) throws ParsingException {
        //se crean elementos para cada variable a los que se le asigna aquellos elementos con la etiqueta correspondiente
        Element eltoHora2 = e.getFirstChildElement(ETQ_HORA2);
        Element eltoMinutos = e.getFirstChildElement(ETQ_MINUTOS);

        //se comprueba que los elementos no están vacíos
        if (eltoHora2 == null) {
            throw new ParsingException("Falta el HORA2 en el elemento hora");
        }
        if (eltoMinutos == null) {
            throw new ParsingException("Falta el MINUTOS en el elemento minutos");
        }

        //se asigna a la variable el valor almacenado por el element
        this.hora = Integer.parseInt(eltoHora2.getValue().trim());
        this.minutos = Integer.parseInt(eltoMinutos.getValue().trim());
    }

    //**************************************************************************************************************************************
    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    //**************************************************************************************************************************************
    //CREACIÓN DE LA ESTRUCTURA XML MEDIANTE UN MÉTODO TODOM()
    public Element toDom() {

        //se crean los elementos correspondientes a la estructura del xml (propios de la clase)
        Element raiz = new Element(ETQ_HORA);
        Element eltoHora2 = new Element(ETQ_HORA2);
        Element eltoMinutos = new Element(ETQ_MINUTOS);

        //se le asigna a cada elemento la variable que guarda el valor
        eltoHora2.appendChild(Integer.toString(hora));
        eltoMinutos.appendChild(Integer.toString(minutos));

        //se le asigna al elemento raiz cada uno de los elementos que engloba
        raiz.appendChild(eltoHora2);
        raiz.appendChild(eltoMinutos);

        return raiz;
    }

    //**************************************************************************************************************************************
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(getHora()).append(":").append(getMinutos());
        return toret.toString();
    }

}

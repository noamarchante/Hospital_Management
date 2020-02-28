/*
 * Definicion de la clase Paciente
 */
package es.uvigo.esei.pro2.core;

/**
 *
 * @author nrufino
 */
public class Paciente {
    public static enum tipoPaciente { PRIVADO , ASEGURADO};
    private String numHistorial; // Código de la historia médica 
    private String nombre;  // Nombre completo del paciente
    private String domicilio;  
    private int ano;    // Año de nacimiento del paciente
    private tipoPaciente tipo;
    //private boolean privado;
    //private boolean asegurado;

    /** Crea un nuevo paciente, con sus datos: numero historial, nombre, 
     *  domicilio y año
     * @param numHistorial número del historial médico
     * @param nombre nombre completo del paciente
     * @param domicilio  el domicilio del paciente
     * @param ano el ano de nacimiento del paciente
     */
    public Paciente(String numHistorial, String nombre, String domicilio, 
                    int ano, tipoPaciente tipo)
    {
        //setEtiquetaPrivado( false );
        //setEtiquetaAsegurado( false );
        this.tipo=tipo;
        this.setNumHistorial( numHistorial );
        this.setNombre( nombre );
        this.setDomicilio( domicilio );
        this.setAno(ano);
    }

    
    /** Devuelve el número del historial médico del paciente
     * @return el numHistorial del paciente, como String.
     */
    public String getNumHistorial()
    {
        return numHistorial;
    }

    /** Cambia el  número del historial médico del paciente
     * @param nH el numHistorial del paciente
     */
    public void setNumHistorial(String nH)
    {
        numHistorial = nH;
    }

    /** Devuelve el nombre del paciente
     *  @return El valor como cadena
     **/
    public String getNombre() {
        return nombre;
    }

    /** Cambia el nombre del paciente
     * @param nombre El nuevo valor, como cadena
     */
    public void setNombre(String nombre) {
        this.nombre = nombre.trim();
    }
    
    /** Devuelve el domicilio del paciente
     *  @return El valor como cadena
     **/
    public String getDomicilio() {
        return domicilio;
    }

    /** Cambia el domicilio del paciente
     * @param domicilio El nuevo valor, como cadena
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio.trim();
    }

    /** Devuelve el año de nacimiento del paciente
     *  @return El valor como entero
     **/
    public int getAno() {
        return ano;
    }

    /** Cambia el año de nacimiento del paciente
     * @param ano El nuevo valor, como entero
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    public tipoPaciente getTipo() {
        return tipo;
    }

    public void setTipo(tipoPaciente tipo) {
        this.tipo = tipo;
    }
    

    /** Devuelve el valor de la etiqueta privado
     * @return true si se encuentra activa, false en otro caso
     */
    /*public boolean estaEtiquetadoComoPrivado() {
        return privado;
    }*/

    /** Cambia la etiqueta privado
     * @param privado El nuevo valor, como boolean
     */
    /*public void setEtiquetaPrivado(boolean privado) {
        this.privado = privado;
    }*/

    /** Devuelve el valor de la etiqueta asegurado
     * @return true si se encuentra activa, false en otro caso
     */
    /*public boolean estaEtiquetadoComoAsegurado() {
        return asegurado;
    }*/

    /** Cambia la etiqueta asegurado
     * @param asegurado El nuevo valor, como boolean
     */
    /*public void setEtiquetaAsegurado(boolean asegurado) {
        this.asegurado = asegurado;
    }*/

    public String toString(){
        StringBuilder toret = new StringBuilder();
        toret.append(getNumHistorial()).append(" ; ").append(getNombre()).append(" ; ");
        toret.append(getDomicilio()).append(" ; ").append(getAno()).append(" ; ");
        toret.append(getTipo()).append(" ; ");
       /* if ( estaEtiquetadoComoPrivado() ) {
            toret.append("privado ");
        } 
        else if ( estaEtiquetadoComoAsegurado() ) {
                toret.append("asegurado ");
             }
       */
        return toret.toString();
    }
}


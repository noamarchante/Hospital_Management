/*  Definición de la clase Clinica
 *  En una clínica tendremos una serie de pacientes
*/

package es.uvigo.esei.pro2.core;

/**
 *
 * @author nrufino
 */
public class Clinica {
    private Paciente[] pacientes;
    private int numPacientes;

    /** Nueva Clinica con un num. max. de pacientes.
     * @param maxPacientes el num. max. de pacientes, como entero.
     */
    public Clinica(int maxPacientes)
    {
        numPacientes = 0;
        pacientes = new Paciente[ maxPacientes  ];
    }

    /**
     * Devuelve el paciente situado en pos
     * @param pos el lugar del paciente en el vector de pacientes
     * @return el objeto Paciente correspondiente.
     */
    public Paciente get(int pos)
    {
        try {
        if ( pos >= getNumPacientes() ) {
            throw new Exception ("get(): sobrepasa la pos: " + ( pos + 1 ) 
                                + " / " + getMaxPacientes());
            /*System.err.println( "get(): sobrepasa la pos: " + ( pos + 1 ) 
                                + " / " + getMaxPacientes() );*/
            //System.exit( -1 );
        }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return pacientes[ pos ];
    }

    /** Devuelve el num. de pacientes creados.
     * @return el num. de pacientes existentes, como entero.
     */
    public int getNumPacientes()
    {
        return numPacientes;
    }

    /** Devuelve el max. de numPacientes
     * @return el num. de pacientes max,, como entero
     */
    public int getMaxPacientes()
    {
        return pacientes.length;
    }

    /** Inserta un nuevo paciente
     * @param p el nuevo objeto Paciente
     */
    public void inserta(Paciente p)
    {
        final int maxPacientes = getMaxPacientes();
        try{
            if ( getNumPacientes() >= maxPacientes ) {
                throw new Exception ("inserta(): sobrepasa max.: " + maxPacientes);
                //System.err.println( "inserta(): sobrepasa max.: " + maxPacientes );
                //System.exit( -1 );
            }
            pacientes[ numPacientes ] = p;
            ++numPacientes;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void elimina(int pos){
       /*for(int i=pos;i<(getNumPacientes()-1);i++){
            pacientes[i]=pacientes[i+1];
        }
        numPacientes--;*/
       //pacientes[pos]=pacientes[--numPacientes];
       pacientes[pos]=pacientes[(getNumPacientes()-1)];
       numPacientes--;
    }
    public String toString(){
        StringBuilder toret = new StringBuilder();
        for(int i=0;i<getNumPacientes();i++){
            toret.append(pacientes[i].toString()).append("\n");
        }
        return toret.toString();
    }
    public String toString(Paciente.tipoPaciente t){
        
        StringBuilder toret = new StringBuilder();
        for(int i=0;i<getNumPacientes();i++){
            if(pacientes[i].getTipo()==t){
                toret.append(pacientes[i].toString()).append("\n");
            }
        }
        return toret.toString();
    }
}

//despues de un año dado listar
//insertar mod para ordenar segun numero de historial

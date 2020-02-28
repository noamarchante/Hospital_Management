package es.uvigo.esei.pro2.core;

public abstract class Paciente extends Persona{   
    private String numHistorial; // Código de la historia médica   
    private Fecha fechaNacimiento;
    public static enum Tipo {
        ASEGURADO, PRIVADO
    };
    
    public Paciente(String numHistorial, Fecha fechaNacimiento, String nombre, String domicilio)    
    {
        super(nombre, domicilio);
        this.numHistorial = numHistorial;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumHistorial() {
        return numHistorial;
    }

    public void setNumHistorial(String nH)
    {
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
        if(super.equals(p) && this.numHistorial.equals(p.numHistorial) && this.fechaNacimiento.equals(p.fechaNacimiento)){
            res=true;
        }else{
            res=false;
        }
        return res; 
    }
    
    public String toString()
    {
        StringBuilder toret = new StringBuilder();
        
        toret.append(getNumHistorial()).append(" ; ");
        toret.append(super.toString());
        toret.append(getFechaNacimiento().toString()).append(" ; ");
             
        return toret.toString();
    }
}


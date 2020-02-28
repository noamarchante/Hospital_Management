package es.uvigo.esei.pro2.core;

public class Privado extends Paciente{
    private String dni;

    public Privado(String dni, String numHistorial, Fecha fechaNacimiento, String nombre, String domicilio) {
        super(numHistorial, fechaNacimiento, nombre, domicilio);
        this.dni = dni;
    }

    

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    public boolean equals(Privado p) {
        boolean res;
        if(super.equals(p) && this.dni.equals(p.dni)){
            res=true;
        }else{
            res=false;
        }
        return res; 
    }
    
    public String toString() {
       StringBuilder toret = new StringBuilder();
       toret.append(super.toString()).append("Privado ").append(";").append(getDni());
       return toret.toString();
    }
    
    
}

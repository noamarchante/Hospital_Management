package es.uvigo.esei.pro2.core;

public abstract class Persona {
    private String nombre;
    private String domicilio;

    public Persona(String nombre, String domicilio) {
        this.nombre = nombre;
        this.domicilio = domicilio;
    }

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
        if(this.nombre.equals(p.nombre) && this.domicilio.equals(p.domicilio)){
            res=true;
        }else{
            res=false;
        }
        return res; 
    }
    public String toString()
    {
        StringBuilder toret = new StringBuilder();
        
        toret.append(getNombre()).append(" ; ");
        toret.append(getDomicilio()).append(" ; ");
             
        return toret.toString();
    }
}

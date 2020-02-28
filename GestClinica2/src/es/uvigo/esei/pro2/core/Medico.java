package es.uvigo.esei.pro2.core;

import java.util.Objects;

public class Medico extends Persona {
    private String numColegiado;

    public Medico(String numColegiado, String nombre, String domicilio) {
        super(nombre, domicilio);
        this.numColegiado = numColegiado;
    }

    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
    }

    public boolean equals(Medico m) {
        boolean res;
        if(super.equals(m)&& this.numColegiado.equals(m.numColegiado)){
            res=true;
        }else{
            res=false;
        }
        return res; 
    }
    
    public String toString(){
        StringBuilder toret = new StringBuilder();
        toret.append(getNumColegiado()).append(";").append(getNombre()).append(";").append(getDomicilio());
        return toret.toString();
    }
}

package es.uvigo.esei.pro2.core;

public class Asegurado extends Paciente {
    private String poliza;
    private String companhia;

    public Asegurado(String poliza, String companhia, String numHistorial, Fecha fechaNacimiento, String nombre, String domicilio) {
        super(numHistorial, fechaNacimiento, nombre, domicilio);
        this.poliza = poliza;
        this.companhia = companhia;
    }

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
        if(super.equals(a) && this.companhia.equals(a.companhia) && this.poliza.equals(a.poliza)){
            res=true;
        }else{
            res=false;
        }
        return res; 
    }
    
    public String toString (){
        StringBuilder toret = new StringBuilder();
        toret.append(super.toString()).append("Asegurado ").append(";").append(getPoliza()).append(";").append(getCompanhia());
        return toret.toString();
    }
}

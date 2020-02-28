package es.uvigo.esei.pro2.core;

public class Fecha {
    private int dia;
    private int mes;
    private int anho;

    public Fecha(int dia, int mes, int anho) {
        this.dia = dia;
        this.mes = mes;
        this.anho = anho;
    }

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
    
    public String toString(){
        StringBuilder toret= new StringBuilder();
        toret.append(getDia()).append("/").append(getMes()).append("/").append(getAnho());
        return toret.toString();
    }
    
    public boolean equals (Fecha f){
        if(f.dia==this.dia && f.mes==mes && f.anho==this.anho){
            return true;
        }else{
            return false;
        }
    }
}

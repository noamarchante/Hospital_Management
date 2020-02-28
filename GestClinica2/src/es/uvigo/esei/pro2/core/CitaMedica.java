package es.uvigo.esei.pro2.core;

public class CitaMedica {
    private Paciente paciente;
    private Fecha fecha;
    private Hora hora;
    private Medico medico;

    public CitaMedica(Paciente paciente, Fecha fecha, Hora hora, Medico medico) {
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
    }

    public CitaMedica (Paciente paciente, Medico medico, int dia, int mes, int año, int hora, int minutos ){
        this.paciente=paciente;
        this.medico=medico;
        this.fecha= new Fecha(dia, mes, año);
        this.hora= new Hora(hora, minutos);
    }
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
    public boolean equals(CitaMedica c) {
        boolean res;
        if(this.paciente.equals(c.paciente) && this.medico.equals(c.medico) && this.hora.equals(c.hora) && this.fecha.equals(c.fecha)){
            res=true;
        }else{
            res=false;
        }
        return res; 
    }
    
    public String toString(){
        StringBuilder toret = new StringBuilder();
        toret.append(getPaciente().toString()).append(";").append(getFecha().toString()).append(";").append(getHora().toString()).append(";").append(getMedico().toString());
        return toret.toString();
    }
    
    
}

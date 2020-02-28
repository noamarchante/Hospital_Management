package es.uvigo.esei.pro2.core;

public class Clinica {

    public static class GestClinicaException extends Exception {

        public GestClinicaException(String msg) {
            super(msg);
        }
    }

    public static class LimiteException extends GestClinicaException {

        public LimiteException(String msg) {
            super(msg);
        }

    }

    public static class SinElementosException extends GestClinicaException {

        public SinElementosException(String msg) {
            super(msg);
        }

    }

    public static class ConCitaException extends GestClinicaException {

        public ConCitaException(String msg) {
            super(msg);
        }

    }

    public static enum Categoria {
        MEDICO, CITA, PACIENTE
    };
    private Paciente[] pacientes;
    private Medico[] medicos;
    private CitaMedica[] citasMedicas;
    private int numPacientes;
    private int numMedicos;
    private int numCitas;
    private String nombre;

    public Clinica(int maxPacientes, int maxMedicos, int maxCitas, String nombre) {
        numPacientes = 0;
        numCitas = 0;
        numMedicos = 0;
        this.nombre = nombre;
        pacientes = new Paciente[maxPacientes];
        medicos = new Medico[maxMedicos];
        citasMedicas = new CitaMedica[maxCitas];
    }

    public Paciente getPaciente(int pos) throws LimiteException {
        if (pos >= getNumPacientes()) {
            throw new LimiteException("get(): sobrepasa la pos: " + (pos + 1)
                    + " / " + getMaxPacientes());
        }

        return pacientes[pos];
    }

    public Medico getMedico(int pos) throws LimiteException {
        if (pos >= getNumMedicos()) {
            throw new LimiteException("get(): sobrepasa la pos: " + (pos + 1)
                    + " / " + getMaxMedicos());
        }

        return medicos[pos];
    }

    public CitaMedica getCita(int pos) throws LimiteException {
        if (pos >= getNumCitas()) {
            throw new LimiteException("get(): sobrepasa la pos: " + (pos + 1)
                    + " / " + getMaxCitas());
        }

        return citasMedicas[pos];
    }

    public int getNumPacientes() {
        return numPacientes;
    }

    public int getNumMedicos() {
        return numMedicos;
    }

    public Paciente[] getPacientes() {
        return pacientes;
    }

    public Medico[] getMedicos() {
        return medicos;
    }

    public CitaMedica[] getCitasMedicas() {
        return citasMedicas;
    }

    public int getNumCitas() {
        return numCitas;
    }

    public int getMaxPacientes() {
        return pacientes.length;
    }

    public int getMaxMedicos() {
        return medicos.length;
    }

    public int getMaxCitas() {
        return citasMedicas.length;
    }

    public void inserta(Object o) throws LimiteException {
        if (o instanceof Paciente) {
            final int maxPacientes = getMaxPacientes();

            if (getNumPacientes() >= maxPacientes) {
                throw new LimiteException("inserta(): sobrepasa max.: " + maxPacientes);
            }

            pacientes[numPacientes] = (Paciente) o;
            ++numPacientes;
        } else if (o instanceof Medico) {
            final int maxMedicos = getMaxMedicos();
            if (getNumMedicos() >= maxMedicos) {
                throw new LimiteException("inserta(): sobrepasa max.: " + maxMedicos);
            }

            medicos[numMedicos] = (Medico) o;
            ++numMedicos;
        } else if (o instanceof CitaMedica) {
            final int maxCitas = getMaxCitas();
            if (getNumCitas() >= maxCitas) {
                throw new LimiteException("inserta(): sobrepasa max.: " + maxCitas);
            }
            citasMedicas[numCitas] = (CitaMedica) o;
            ++numCitas;
        }
    }

    public void elimina(int pos, Categoria c) throws LimiteException {
        switch (c) {
            case PACIENTE:
                if (pos >= getNumPacientes()) {
                    throw new LimiteException("elimina(): sobrepasa el numero de pacientes: "
                            + getNumPacientes());
                }
                pacientes[pos] = pacientes[--numPacientes];
                break;
            case MEDICO:
                if (pos >= getNumMedicos()) {
                    throw new LimiteException("elimina(): sobrepasa el numero de medicos: "
                            + getNumMedicos());
                }
                medicos[pos] = medicos[--numMedicos];
                break;
            case CITA:
                if (pos >= getNumCitas()) {
                    throw new LimiteException("elimina(): sobrepasa el numero de citas: "
                            + getNumCitas());
                }
                citasMedicas[pos] = citasMedicas[--numCitas];
                break;
        }
    }

    public String toString(Categoria c) throws SinElementosException {
        StringBuilder toret = new StringBuilder();
        switch (c) {
            case PACIENTE:
                final int numPacientes = getNumPacientes();

                if (numPacientes > 0) {
                    for (int i = 0; i < numPacientes; i++) {
                        toret.append((i + 1)).append(". ");
                        toret.append(pacientes[i].toString()).append("\n");
                    }
                } else {
                    throw new SinElementosException("No hay pacientes");
                }
                break;
            case MEDICO:
                final int numMedicos = getNumMedicos();

                if (numMedicos > 0) {
                    for (int i = 0; i < numMedicos; i++) {
                        toret.append((i + 1)).append(". ");
                        toret.append(medicos[i]).append("\n");
                    }
                } else {
                    throw new SinElementosException("No hay medicos.");
                }
                break;
            case CITA:
                final int numCitas = getNumCitas();

                if (numCitas > 0) {
                    for (int i = 0; i < numCitas; i++) {
                        toret.append((i + 1)).append(". ");
                        toret.append(citasMedicas[i].toString()).append("\n");
                    }
                } else {
                    throw new SinElementosException("No hay citas.");
                }
                break;

        }
        return toret.toString();
    }

    public String listarPacientesPorTipo(Paciente.Tipo t) throws SinElementosException {
        StringBuilder toret = new StringBuilder();
        int numPac = getNumPacientes();
        for (int i = 0; i < numPac; i++) {
            switch (t) {
                case ASEGURADO:
                    if (pacientes[i] instanceof Asegurado) {
                        toret.append("\n\t").append(pacientes[i].toString());
                    }
                    break;
                case PRIVADO:
                    if (pacientes[i] instanceof Privado) {
                        toret.append("\n\t").append(pacientes[i].toString());
                    }
                    break;
            }
        }

        if (toret.length() == 0) {
            throw new SinElementosException("No existen pacientes de ese tipo ");
        }
        return toret.toString();
    }

    public String listarCitaPorMedico(String num, Fecha f) throws SinElementosException {
        StringBuilder toret = new StringBuilder();
       Medico m=new Medico("","","");
        for(int i=0; i<getNumMedicos() && !getMedicos()[i].getNumColegiado().equals(num);i++){
            m=getMedicos()[i];
        }
        for (int j = 0; j < getNumCitas(); j++) {
            if(getCitasMedicas()[j].getMedico().equals(m) && getCitasMedicas()[j].getFecha().equals(f)){
                toret.append(getCitasMedicas()[j]);
            }else{
                toret.append("El médico no tiene citas en esta fecha");
            } 
        }
         toret.append("\nMédico:\n");
         toret.append(m);
        return toret.toString();
    }
}

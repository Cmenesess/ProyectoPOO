
package Termino;

import concurso.*;
import java.util.ArrayList;
import java.util.Objects;


public class Paralelo {
    private int numero;
    private ArrayList<Estudiante> estudiantes;
    private Materia materia;
    public Paralelo(int numero, ArrayList<Estudiante> estudiantes,Materia materia) {
        this.numero = numero;
        this.estudiantes = estudiantes;
        this.materia=materia;
    }
    public Paralelo(int numero,Materia materia){
        this.numero=numero;
        this.materia=materia;
        estudiantes=new ArrayList<>();
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
    public Estudiante compararMatricula(String matricula){
        for(Estudiante e: estudiantes){
           if (e.getMatricula().equals(matricula)){
               return e;
           }
        }
        return null;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.numero;
        hash = 19 * hash + Objects.hashCode(this.materia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paralelo other = (Paralelo) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.materia, other.materia)) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        if(materia!=null){
            return materia.getNombre()+ " paralelo: "+ numero ;
        }
        else{
            return "paralelo:"+ numero;
        }
    }   
}

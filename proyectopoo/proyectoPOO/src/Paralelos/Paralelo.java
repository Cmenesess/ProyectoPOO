
package Paralelos;

import Termino.Materia;
import concurso.*;
import java.util.ArrayList;
import java.util.Objects;


public class Paralelo {
    private int numero;
    private ArrayList<Estudiante> estudiantes;
    private Materia materia;
    //Constuctores
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
    /**
     * Se usa para retornar el Estudiante que tenga la  matricula registrada
     * @param matricula
     * @return Estudiante
     */
    public Estudiante compararMatricula(String matricula){
        for(Estudiante e: estudiantes){
           if (e.getMatricula().equals(matricula)){
               return e;
           }
        }
        return null; //retorna null en caso de que no exista esa matricula en ese paralelo
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
    //Reescribir el metodo equals
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
    /**
     * Se usa este metodo para mostrar los estudiantes asignados al paralelo y en caso de que no existen se muestran que no hay estudiantes
     */
    public void MostrarEstudiante(){
        int contador=1;
        if(!estudiantes.isEmpty()){
            for(Estudiante estudiante: estudiantes){
                System.out.println(contador+ ") "+estudiante.getNombre());
                contador++;
            }
            System.out.println("Hay un total de: "+estudiantes.size()+" estudiantes");
        }else{
            System.out.println("Actualmente no hay estudiantes asignados a ese paralelo");
        }
    }
    //Sobreescribir el metodo toString
    @Override
    public String toString() {
        if(materia!=null){
            return materia.getNombre()+ " paralelo: "+ numero ;
        }
        else{
            return "paralelo:"+ numero;
        }
    }
    //Getters && Setters
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
}

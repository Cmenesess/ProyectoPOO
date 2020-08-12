/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Termino;

import Paralelos.Paralelo;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author carloshumbertomenesesmurillo
 */
public class Termino {
    private int año;
    private int termino;
    private ArrayList<Paralelo> paralelos;
    //Constructores
    public Termino(int año, int termino, ArrayList<Paralelo> paralelos) {
        this.año = año;
        this.termino = termino;
        this.paralelos = paralelos;
    }
    public Termino(int anio,int termino){
        this.año=anio;
        this.termino=termino;
        paralelos=new ArrayList<>();
    }
    @Override
    public String toString() {
        return año +"-"+termino;
    }
    /**
     * Retorna el Paralelo que tenga ese numero y en caso de no encontrarlo retorna un null
     * @param numero
     * @return 
     */
    public Paralelo retornarParalelo(int numero){
        for(Paralelo par: paralelos){
            if(par.getNumero()==numero){
                return par;
            }
        }
        return null;
    }
    /**
     *Se muestran solo los paralelos en los que la materias se encuentren activadas en el menu;  
     */
    public void mostrarMenu(){
        int numero=0;
        ArrayList<Integer> paralelos_desactivados= new ArrayList<>();
        for(Paralelo parl: paralelos){
            if(parl.getMateria().isActivada()){
                numero+=1;
                System.out.println(numero+" "+parl); 
            }else{
               paralelos_desactivados.add(paralelos.indexOf(parl)); //Se ven cuales son las posiciones de las que se van a desactivar
            }
        }
        for(int num: paralelos_desactivados){
            Paralelo par= paralelos.remove(num); //Se eliminar las posiciones en las que se encuentran las materias desactivadas
            paralelos.add(par); //Se agregan al final  esas materias desactivadas para que el ususario no pueda escoger esas opciones.
        }
    }
    /**
     * Se retorna el numero de paralelos que se encuentra activos
     * @return int
     */
    public int mostrarParalelosActivos(){
        int numero=0;
        for(Paralelo parl: paralelos){
            if(parl.getMateria().isActivada()){
                numero+=1;
            }
        }
        return numero;
    }
    /**
     * Se muestran los paralelos que se encuentran asociadas a la materia y se retorna el paralelo que se selecciono    
     * @param materia
     * @return 
     */
    public Paralelo ParalelosMateria(Materia materia){
        Scanner sc=new Scanner(System.in);
        int numero=1;
        ArrayList<Paralelo> paralelos_materia= new ArrayList<>();
        System.out.println("paralelo asociados a la materia");
        if(materia.isActivada()){
            for(Paralelo parl: paralelos){
                if(parl.getMateria().getNombre().equals(materia.getNombre())){
                    paralelos_materia.add(parl);
                    System.out.println(numero+".-  paralelo "+parl.getNumero());
                    numero++;
                 }
            }
        }else{
            System.out.println("Recuerde que esa ");
        }
        System.out.println("Ingrese la opcion a elegir: ");
        int opcion=Integer.parseInt(sc.nextLine());
        return paralelos_materia.get(opcion-1);
    }
    /**
     * Se agrega un paralelo a ese termino 
     * @param paralelo 
     */
    public void agregarParalelo(Paralelo paralelo){
        paralelos.add(paralelo);
    }
    //Getter && Setters
    public void setParalelos(ArrayList<Paralelo> paralelos) {
        this.paralelos = paralelos;
    }
    public int getAño() {
        return año;
    }
    public void setAño(int año) {
        this.año = año;
    }
    public int getTermino() {
        return termino;
    }
    public void setTermino(int termino) {
        this.termino = termino;
    }
    public ArrayList<Paralelo> getParalelos() {
        return paralelos;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.año;
        hash = 47 * hash + this.termino;
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
        final Termino other = (Termino) obj;
        if (this.año != other.año) {
            return false;
        }
        if (this.termino != other.termino) {
            return false;
        }
        return true;
    }

}

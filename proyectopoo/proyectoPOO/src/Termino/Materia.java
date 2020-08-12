/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Termino;


import concurso.MenuPrincipal;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author carloshumbertomenesesmurillo
 */
public class Materia {
    private String codigo;
    private String nombre;
    private int nivel;
    private ArrayList<Pregunta> pregunta;
    private boolean activada;
    //Constructores
    public Materia(String codigo, String nombre, int nivel) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nivel = nivel;
        this.activada=true;
        this.pregunta = concurso.MenuPrincipal.leerPreguntas(nivel,codigo);
        escritor(codigo, pregunta);    
    }
    public Materia(String codigo,String nombre) {
        this.codigo = codigo;
        this.nombre= nombre;
        this.nivel = 3;
        pregunta= concurso.MenuPrincipal.leerPreguntas();
        this.activada=true;
        escritor(codigo, pregunta);
    }
    public Materia(String codigo){
        this.codigo = codigo;
        pregunta= new ArrayList<>();
        this.activada=true;
    }
    /**
     * 
     * Metodo para verificar que la materia tenga ese nivel de preguntas por nivel y retorna las preguntas_por nivel en caso de existir
     * @param preguntas_por_nivel
     * @return ArrayList<Pregunta>
     */
    public ArrayList<Pregunta> Verificar_preguntas(int preguntas_por_nivel){
        ArrayList<Pregunta> Preguntas_seleccionadas=new ArrayList<>();
        int contador=0;
        for(Pregunta pregun:pregunta){
            contador=contarPreguntasPorNivel(Integer.parseInt(pregun.getNivel()),Preguntas_seleccionadas,preguntas_por_nivel);
            if(contador<preguntas_por_nivel){
                Preguntas_seleccionadas.add(pregun);
            }
        }
        int contador_por_nivel=0;
        for(int i=1;i<=nivel;i++){
            contador=contarPreguntasPorNivel(i,Preguntas_seleccionadas,preguntas_por_nivel);
            if(contador==preguntas_por_nivel){
                contador_por_nivel+=1;
            }     
        }
        if(contador_por_nivel==nivel){
            return Preguntas_seleccionadas;
        }
        return null; //retorn un null en caso de que no tenga esa cantidad de preguntas por nivel
    }
    /**
     * Metodo para contar la cantidad de preguntas que tienen en un nivel especifico
     * @param nivel
     * @param preguntas_selecc
     * @param preguntas_por_nivel
     * @return int
     */
    public static int contarPreguntasPorNivel(int nivel,ArrayList<Pregunta> preguntas_selecc,int preguntas_por_nivel){
        int contador=0;
        if(!preguntas_selecc.isEmpty()){
            for(Pregunta pregu:preguntas_selecc){
                if(Integer.parseInt(pregu.getNivel())==nivel){
                    contador+=1;
                }
            }
        }
        return contador;
    }
    /**
     * Escribe las preguntas dentro de un archivo 
     * @param codigo
     * @param pregunta 
     */
    public static void escritor(String codigo, ArrayList<Pregunta> pregunta ){
        FileWriter writer = null;
        try {
            String ruta = "src/archivos/Preguntas-" + codigo + ".csv";
            writer = new FileWriter(ruta);
            writer.write("enunciado,nivel,respuesta_correcta,respuesta_posible1,respuesta_posible2,respuesta_posible3\n");
            for (Pregunta p : pregunta) {
                writer.write(p.getEnunciado() + ";" + p.getNivel() + ";"
                        + p.getCorrecta() + ";" + p.getRespuesta1() + ";" + p.getRespuesta2() + ";"
                        + p.getRespuesta3() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null,
                        ex);
            }
        }
    }
    //Reescribir el metod equals
    @Override
    public boolean equals(Object o){
        if (o==this){
            return true;
        }
        if(o!=null && getClass()==o.getClass()){
            Materia obj= (Materia) o;
            return codigo.equals(obj.codigo);
        }else{
            return false;
        }
    }

    public boolean isActivada() {
        return activada;
    }

    public String getCodigo() {
        return codigo;
    }
    //Getter && Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public ArrayList<Pregunta> getPregunta() {
        return pregunta;
    }

    public void setPregunta(ArrayList<Pregunta> pregunta) {
        this.pregunta = pregunta;
    }
    public void NoDisponible(){
        activada=false;
    }
}
    

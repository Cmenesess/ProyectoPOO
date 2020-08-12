package concurso;

import Paralelos.Estudiante;
import Paralelos.Paralelo;
import Termino.*;
import java.util.*;


public class Juego {
    private Materia materia;
    private Paralelo paralelo;
    private Estudiante aleatorio;
    private java.util.Random random = new java.util.Random();
    private java.util.Date fechaActual;
    private ArrayList<Pregunta> preguntas;
    private int nivel;
    private ArrayList<Comodin> comodines;
    //Constructores
    //Este se utiliza cuando se registra la matricula
    public Juego(Materia materia, Paralelo paralelo, String matricula,ArrayList<Pregunta> preguntas,int nivel){
        this.materia = materia;
        this.paralelo = paralelo;
        this.aleatorio= paralelo.compararMatricula(matricula); //Se retorna la matricula y se verifica que exista esa matricula
        Collections.sort(preguntas, (Pregunta p1, Pregunta p2) -> new Integer(p1.getNivel()).compareTo(new Integer(p2.getNivel())));
        this.preguntas=preguntas;
        this.fechaActual = new java.util.Date();
        this.nivel=nivel;
        comodines = new ArrayList<>();
        comodines.add(Comodin.CONSULTA_COMPANIERO); comodines.add(Comodin.CONSULTA_SALON); comodines.add(Comodin.CINCUENTA_CINCUENTA); //Se inicializan todos los comodines para el juego
    }
    //Se utiliza este constuctore para seleccionar un estudiante al azar
    public Juego(Materia materia, Paralelo paralelo,ArrayList<Pregunta> preguntas,int nivel){
        this.materia = materia;
        this.paralelo = paralelo;  
        this.aleatorio = paralelo.getEstudiantes().get(random.nextInt(paralelo.getEstudiantes().size()));
        System.out.println("El estudiante seleccionado es: "+aleatorio.getNombre());
        Collections.sort(preguntas, (Pregunta p1, Pregunta p2) -> new Integer(p1.getNivel()).compareTo(new Integer(p2.getNivel())));
        this.preguntas=preguntas;
        this.fechaActual = new java.util.Date();
        this.nivel=nivel;
        comodines = new ArrayList<>();
        comodines.add(Comodin.CONSULTA_COMPANIERO); comodines.add(Comodin.CONSULTA_SALON); comodines.add(Comodin.CINCUENTA_CINCUENTA);
    }    
    /**
     * Este metodo es para retornar una cadena en la que tenga la respuesta correcta seguida de una respuesta incorrecta
     * @param enunciados
     * @param pregunta
     * @return String
     */
    public String comodinCincuenta(ArrayList<String> enunciados, Pregunta pregunta){
        int numero_aleatorio1=random.nextInt(2);
        String[] correctas = new String[2];
        String respuesta = " ";
        for (String o: enunciados){
            if (!o.equals(pregunta.getCorrecta())){
                respuesta = o;
            }
        }
        correctas[0] = pregunta.getCorrecta();
        correctas[1] = respuesta;
        if (numero_aleatorio1 == 1){ //Se verifica si el numero aleatorio es 1
            return correctas[1] + "-" + correctas[0];
        }else{ //En caso de ser otro va por la segunda opcion
            return correctas[0] + "-" + correctas[1];
        }
    }
    /**
     * Se usa como parametro el nombre del comodin para retornar el comodin a utilizar;
     * @param nombre
     * @return 
     */
    public Comodin retornarComodin(String nombre){
        for (Comodin c: comodines){
            if(c.getNombre().equals(nombre)){
                return c;
            }
        }
        return null;
    }
    //Getters && Setters
     public Materia getMateria() {
        return materia;
    }
    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    public Paralelo getParalelo() {
        return paralelo;
    }
    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }
    public Estudiante getAleatorio() {
        return aleatorio;
    }
    public void setAleatorio(Estudiante aleatorio) {
        this.aleatorio = aleatorio;
    }
    public Random getRandom() {
        return random;
    }
    public void setRandom(Random random) {
        this.random = random;
    }
    public Date getFechaActual() {
        return fechaActual;
    }
    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }
    public ArrayList<Pregunta> getPreguntas() {
       return preguntas;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public ArrayList<Comodin> getComodines() {
        return comodines;
    }
    public void setComodines(ArrayList<Comodin> comodines) {
        this.comodines = comodines;
    }
}


package Termino;

import java.util.ArrayList;
import java.util.Objects;

public class Pregunta {
    private String enunciado, nivel, correcta, respuesta1, respuesta2, respuesta3;
    private java.util.Random random = new java.util.Random();
    //Constructores
    public Pregunta(String enunciado,String nivel, String correcta, String respuesta1, String respuesta2, String respuesta3 ){
        this.enunciado = enunciado;
        this.nivel = nivel;
        this.correcta = correcta;
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;        
    }
    //Sobreescribir el metodo toString
    @Override
    public String toString(){
        return "Pregunta: " + enunciado + "\n" + "Nivel: " + nivel + "\n" + "Respuesta correcta: " + correcta + "\n" + "Respuesta 1: " + respuesta1 + "\n" + "Respuesta 2: " + respuesta2 + "\n" + "Respuesta 3: " + respuesta3 + "\n";
    }
    /**
     * Se usa este metodo para mostrar los enunciados de las preguntas de forma aleatoria y va a retornas un ArrayList con los enunciados de forma aleatoria.
     * @param enunciados
     * @return ArrayList<String>
     */
    public ArrayList<String> mostrarPreguntasJuego(ArrayList<String> enunciados){
        int numero_aleatorio1=random.nextInt(4);
        ArrayList<String> listaCopiada = (ArrayList<String>) enunciados.clone();
        String respuesta_a_1=listaCopiada.remove(numero_aleatorio1);
        int numero_aleatorio2=random.nextInt(listaCopiada.size());
        String respuesta_a_2=listaCopiada.remove(numero_aleatorio2);
        int numero_aleatorio3=random.nextInt(listaCopiada.size());
        String respuesta_a_3=listaCopiada.remove(numero_aleatorio3);
        String respuesta_a_4=listaCopiada.get(0);
        ArrayList<String> aleatorio  = new ArrayList<String>();
        aleatorio.add(respuesta_a_1);
        aleatorio.add(respuesta_a_2);
        aleatorio.add(respuesta_a_3);
        aleatorio.add(respuesta_a_4);
        System.out.println("Pregunta: " + enunciado + "\n" + "A.-" + respuesta_a_1 + "\n" + "B.-" + respuesta_a_2+ "\n" + "C.- " + respuesta_a_3 + "\n" + "D.- " + respuesta_a_4+ "\n");
        return aleatorio;
    }
    /**
     * Este metodo se hace para agregar a un arrayList las respuestas que tiene el enunciado
     * @param enunciados 
     */
    public void AgregarEnunciados(ArrayList<String> enunciados){
        enunciados.add(correcta);
        enunciados.add(respuesta1);
        enunciados.add(respuesta2);
        enunciados.add(respuesta3);
    }
    //Getters && Setters
    public String getEnunciado() {
        return enunciado;
    }
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
    public String getCorrecta() {
        return correcta;
    }
    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }
    public String getRespuesta1() {
        return respuesta1;
    }
    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }
    public String getRespuesta2() {
        return respuesta2;
    }
    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }
    public String getRespuesta3() {
        return respuesta3;
    }
    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }
    public String getNivel() {
        return nivel;
    }
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.nivel);
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
        final Pregunta other = (Pregunta) obj;
        if (!Objects.equals(this.nivel, other.nivel)) {
            return false;
        }
        return true;
    }
}

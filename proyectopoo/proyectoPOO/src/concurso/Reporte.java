package concurso;

import Termino.Termino;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class Reporte {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private String premio, participante, termino, codigo, nivelMax, paralelo, fechaActual;
    private String[] report = new String[7];//El reporte siempre va a tener 7 elementos 
    //Constructores
    //Se crea el reporte con el premio elegido en caso de que haya superado un nivel
    public Reporte(Termino termino, Juego juego, int nivelMax, String premio) {
        this.participante = juego.getAleatorio().getNombre();
        this.codigo = juego.getMateria().getCodigo();
        this.paralelo = Integer.toString(juego.getParalelo().getNumero());
        this.nivelMax = Integer.toString(nivelMax);
        this.premio = premio;
        this.fechaActual = sdf.format(juego.getFechaActual());
        report[0] = termino.toString() ;report[1] = codigo; report[2] = paralelo; report[3] = fechaActual; report[4] = participante; report[5] = this.nivelMax; report[6] = this.premio; 
        crearReporte(termino);
    }
    // En caso de que no haya superado automaticamente el premio es ninguno
    public Reporte(Termino termino, Juego juego) {
        this.participante = juego.getAleatorio().getNombre();
        this.codigo = juego.getMateria().getCodigo().toUpperCase();
        this.paralelo = Integer.toString(juego.getParalelo().getNumero());
        this.nivelMax = "1";
        this.premio = "ninguno";
        this.fechaActual = sdf.format(juego.getFechaActual());
        report[0] = termino.toString() ;report[1] = codigo; report[2] = paralelo; report[3] = fechaActual; report[4] = participante; report[5] = this.nivelMax; report[6] = this.premio;
        crearReporte(termino);
    }
    /**
     * En caso de que no exista el archivo de reporte,este se crea y en cambio si existe se va a escribir el resultado del juego hecho.
     * @param termino 
     */
    public void crearReporte(Termino termino) {
        File file = new File("src/archivos/Reportes.csv");
        
        FileWriter write;
        PrintWriter line;
        
        try {
            if (!file.exists()){
                file.createNewFile();
                write = new FileWriter(file, true);
                line = new PrintWriter(write);
                write.write("Termino;Materia;Paralelo;Fecha del Juego;Participante;Nivel maximo alcanzado;Premio\n");                
            }else{
                write = new FileWriter(file, true);
                line = new PrintWriter(write);
            }
            line.print(termino.toString() + ";" + this.codigo + ";" + this.paralelo + ";" + this.fechaActual + ";" + this.participante + ";" + this.nivelMax + ";");
            line.println(this.premio);

            line.close();
            write.close();

        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //Getter && Setters
    public String[] getReport() {
        return report;
    }
    public void setReport(String[] report) {
        this.report = report;
    }
    @Override
    public String toString() {
        return "Reporte{" + "fechaActual=" + fechaActual + ", participante=" + participante + ", nivelMax=" + nivelMax + ", premio=" + premio + '}';
    }
    
}

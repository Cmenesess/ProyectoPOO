package concurso;

import Paralelos.Estudiante;
import Paralelos.Paralelo;
import Termino.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MenuPrincipal {

    private static ArrayList<Termino> termino = new ArrayList<>();
    private static ArrayList<Materia> materia = new ArrayList<>();
    private static ArrayList<String[]> reporte = new ArrayList<>();
    public static Scanner sc=new Scanner(System.in);

    /**
     * Programa principal
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Antes de comenzar: \nRecuerde guardar los archivos de preguntas en la carpta archivos como Preguntas-codigo.csv");
        System.out.println("Tambien los de para leer para los estudiantes de la forma codigo-paralelo-anio-termino.csv en la carpeta de archivos");
        System.out.println("Pulse para continuar");
        sc.nextLine();
        MenuPrincipal.termino.add(new Termino(2019, 2));
        Termino termino_seleccionado = MenuPrincipal.termino.get(0);
        Materia materia_inicial=new Materia("CCPG1005", "POO");
        materia.add(materia_inicial);
        Paralelo paralelo_inicial=new Paralelo(2, materia_inicial);
        termino_seleccionado.agregarParalelo(paralelo_inicial);
        leerEstudiantes(materia_inicial.getCodigo(),paralelo_inicial,termino_seleccionado.getAño(),termino_seleccionado.getTermino());
        if (reporte.isEmpty()){
            reporte = leerReporte();
        }
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        while (opcion != 4) {
            opcion = MenuPrincipal();
            switch (opcion) {
                case 1:
                    System.out.println("Opción 1: Configuraciones\n"
                            + "1. Administrar términos académicos\n"
                            + "2. Administrar materias y paralelos\n"
                            + "3. Administrar estudiantes\n"
                            + "4. Administrar preguntas\n"
                            + "5. Salir");
                    System.out.println("Ingrese su opcion a elegir");
                    int literal = Integer.parseInt(sc.nextLine());
                    switch (literal) {
                        case 1:
                            Termino termino = Opcion1();
                            if (termino != null) {
                                termino_seleccionado = termino;
                            }
                            break;
                        case 2:
                            System.out.println("****************************************************");
                            System.out.println("Usted selecciono : administrar materias y paralelos");
                            System.out.println("*****************************************************");
                            System.out.println("En el termino: " + termino_seleccionado + " hay " + termino_seleccionado.mostrarParalelosActivos() + " paralelo(s) activos");
                            termino_seleccionado.mostrarMenu();
                            System.out.println("1.-Ingresar materia\n"
                                    + "2.- Editar materia\n"
                                    + "3.- Desactivar materia\n"
                                    + "4.-Agregar paralelo\n"
                                    + "5.- Eliminar paralelo");
                            System.out.println("Ingrese su opcion a elegir");
                            int op = Integer.parseInt(sc.nextLine());
                            Opcion2(termino_seleccionado, op);
                            break;
                        case 3:
                            System.out.println("****************************************************");
                            System.out.println("Usted selecciono : administrar estudiantes");
                            System.out.println("*****************************************************");
                            Opcion3();
                            break;                            
                        case 4:
                            System.out.println("****************************************");
                            System.out.println("Usted selecciono : administrar preguntas");
                            System.out.println("****************************************");
                            System.out.println("1.- Visualizar preguntas\n"
                                            + "2.- Agregar pregunta\n"
                                            + "3.- Eliminar pregunta");
                            System.out.println("Ingrese su opcion a elegir");
                            int option = Integer.parseInt(sc.nextLine());
                            Opcion4(option);
                            break;
                        case 5:
                            break;
                    }
                break;
                case 2:
                    Juego juego=Concurso(termino_seleccionado);
                    if(juego!=null){
                        String premio=Nuevo_juego(juego, termino_seleccionado);  
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el termino academico ej: 2019-2: ");
                    String ter = sc.nextLine();
                    System.out.println("Ingrese la materia: ");
                    String mat = sc.nextLine().toUpperCase();
                    System.out.println("ingrese el paralelo: ");
                    String par = sc.nextLine();                    
                    if (reporte.isEmpty()){
                        System.out.println("No se encuentra ningun reportes, intente jugar primero.");
                    }else{
                        System.out.println("Fecha del juego            Participante               Nivel máximo alcanzado     Premio");
                    for(int i=0; i<=reporte.size()-1 ;i++){
                        if(reporte.get(i)[0].equalsIgnoreCase(ter)&&reporte.get(i)[1].equalsIgnoreCase(mat)&&reporte.get(i)[2].equalsIgnoreCase(par)){
                            System.out.printf("%-17s %-45s %-15s %-5s\n", reporte.get(i)[3],reporte.get(i)[4],reporte.get(i)[5],reporte.get(i)[6]);
                        }
                    }
                }
                    break;
            }
        }
    }
    /**
     * Visualizar las opciones del menu principal y retorna la opcion elegida
     * @return int
     */
    public static int MenuPrincipal() {
        System.out.println("Menu Principal\n"
                + "1. Configuraciones\n"
                + "2. Nuevo juego\n"
                + "3. Reporte\n"
                + "4. Salir");
        System.out.println("Ingrese su opcion:");
        int opcion = Integer.parseInt(sc.nextLine());
        return opcion;
    }
    /**
     * Se visualiza la opcion 1 del menu principal y se retorna un termino en caso de que se quiera modificar el termino a jugar, por eso se retorna null si no se escogio esa opcion y se retorna un termino si se modifico.
     * @return Termino
     */
    public static Termino Opcion1() {
        for (int i = 1; i < termino.size() + 1; i++) {
            System.out.println(i + ") " + termino.get(i - 1));
        }
        System.out.println("1.- Ingresar término\n"
                + "2.- Eliminar término\n"
                + "3.- Configurar término para el juego");
        System.out.println("Ingrese su opcion a elegir");
        int numero = Integer.parseInt(sc.nextLine());
        Termino termino_escogido = null;
        switch (numero) {
            case 1:
                System.out.println("*********************");
                System.out.println("1.1 Ingresar término");
                System.out.println("*********************");
                System.out.println("Ingrese el anio y el termino ej: 2019-2");
                String termino_a_elegir = sc.nextLine();
                String[] parts = termino_a_elegir.split("-");
                Termino termino_seleccionado = new Termino(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                MenuPrincipal.termino.add(termino_seleccionado);
                break;
            case 2:
                System.out.println("********************");
                System.out.println("1.2 Eliminar Termino");
                System.out.println("*********************");
                System.out.println("Seleccione el termino a eliminar");
                for (int i = 1; i <termino.size() + 1; i++) {
                    System.out.println(i + ") " + termino.get(i - 1));
                }
                int pos1 = Integer.parseInt(sc.nextLine());
                MenuPrincipal.termino.remove(pos1 - 1);
                break;
            case 3:
                System.out.println("************************************");
                System.out.println("1.3 Configurar término para el juego ");
                System.out.println("*************************************");
                System.out.println("Seleccion el termino a jugar");
                for (int i = 1; i < termino.size() + 1; i++) {
                    System.out.println(i + ") " + termino.get(i - 1));
                }
                int pos = Integer.parseInt(sc.nextLine());
                termino_escogido = MenuPrincipal.termino.get(pos - 1);
                break;
        }
        return termino_escogido;
    }
    /**
     * Se usa para acceder a la opcion y se pone la numero de la opcion elegida previamente  y termino seleccionado para oder acceder a las subopciones de esta
     * @param termino_seleccionado
     * @param opcion2 
     */
    public static void Opcion2(Termino termino_seleccionado, int opcion2) {
        switch (opcion2) {
            case 1:
                System.out.println("************************************");
                System.out.println("Usted ingreso para registrar materia");
                System.out.println("Ingrese el codigo de la materia: ");
                String codigo = sc.nextLine().toUpperCase();
                if(!materia.contains(new Materia(codigo))){
                    System.out.println("Ingrese el nombre de la materia");
                    String nombre = sc.nextLine();
                    System.out.println("Por ultimo ingrese  la cantidad de niveles en las que se van a agrupar las preguntas");
                    int nivel = Integer.parseInt(sc.nextLine());
                    materia.add(new Materia(codigo, nombre, nivel));
                }else{
                    System.out.println("Materia ya existente");
                }
                break;
            case 2:
                Opcion2_2();
                break;
            case 3:
                System.out.println("*********************************************");
                System.out.println("Usted selecciono para desactivar una materia");
                System.out.println("Ingrese el codigo de la materia a desactivar: ");
                String codigo_a_desactivar = sc.nextLine().toUpperCase();
                if (materia.contains(new Materia(codigo_a_desactivar))) {
                    Materia materia_a_desactivar = MenuPrincipal.retornarMateria(codigo_a_desactivar);
                    System.out.println("Seguro que desea  desactivar la materia? Presione si o no");
                    String respuesta= sc.nextLine();
                    if(respuesta.equalsIgnoreCase("si")){
                        materia_a_desactivar.NoDisponible();
                    }
                } else {
                    System.out.println("No se encontro el codigo de esa materia");
                }
                break;
            case 4:
               Opcion2_4();
                break;
            case 5:
                System.out.println("*************************************************");
                System.out.println("Usted selecciono la opcion 2.5 Eliminar paralelo");
                for (int a = 1; a < termino_seleccionado.getParalelos().size() + 1; a++) {
                    System.out.println(a + " " + termino_seleccionado.getParalelos().get(a - 1));
                }
                System.out.println("Ingrese el numero del paralelo que desea eliminar: ");
                int nu = Integer.parseInt(sc.nextLine());
                termino_seleccionado.getParalelos().remove(nu - 1);
                break;
        }
    }
    /**
     * Se usa para realiza la subopcion 2.2
     */
    public static void Opcion2_2(){
        System.out.println("*********************************");
        System.out.println("Usted ingreso para editar materia");
        System.out.println("Ingrese el codigo de la materia a modificar: ");
        String codigo_a_modificar = sc.nextLine().toUpperCase();
        if (materia.contains(new Materia(codigo_a_modificar))) {
            System.out.println("Ingrese el numero con el parametro a cambiar:\n"
                    + "1.-Modificar el nombre\n"
                    + "2.-Modificar cantidad de niveles\n"
                    + "3.-Modificar las dos cosas");
            int op_para = Integer.parseInt(sc.nextLine());
            Materia materia_modificar = MenuPrincipal.retornarMateria(codigo_a_modificar);
            if (op_para == 1) {
                System.out.println("Ingrese el nuevo nombre de esa materia: ");
                String name = sc.nextLine();
                materia_modificar.setNombre(name);
            } else if (op_para == 2) {
                System.out.println("Ingrese la cantidad de niveles a modificar: ");
                int ni = Integer.parseInt(sc.nextLine());
                materia_modificar = new Materia(materia_modificar.getCodigo(), materia_modificar.getNombre(), ni);
            } else {
                System.out.println("Ingrese la cantidad de niveles a modificar: ");
                int niv = Integer.parseInt(sc.nextLine());
                materia_modificar = new Materia(materia_modificar.getCodigo(), materia_modificar.getNombre(), niv);
                System.out.println("Ingrese el nuevo nombre de esa materia: ");
                String nam = sc.nextLine();
                materia_modificar.setNombre(nam);
            }
        } else {
            System.out.println("No se encontro ese codigo de la materia");
        }
    }
    /**
     * Se usa para realizar la subopcion 2.4
     */
    public static void Opcion2_4(){
        System.out.println("***********************************************");
        System.out.println("Usted selecciono la opcion 2.4-Agregar paralelo");
        System.out.println("Ingrese el termino academico ej 2019-2");
        String term = sc.nextLine();
        String[] partes = term.split("-");
        Termino termino_a_comprobar = new Termino(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]));
        if (MenuPrincipal.termino.contains(termino_a_comprobar)) {
            System.out.println("Ahora ingrese el codigo de la materia:");
            String cod = sc.nextLine().toUpperCase();
            if (materia.contains(new Materia(cod))) {
                Materia materia_a_agregar = MenuPrincipal.retornarMateria(cod);
                if (materia_a_agregar.isActivada()) {
                    System.out.println("Finalmente ingrese el numero de paralelo: ");
                    int nume = Integer.parseInt(sc.nextLine());
                    Termino termino_a_modificar = MenuPrincipal.retornarTermino(termino_a_comprobar);
                    termino_a_modificar.agregarParalelo(new Paralelo(nume, materia_a_agregar));
                } else {
                    System.out.println("Esa materias se encuentra desactivada en estos momentos");
                }
            } else {
                System.out.println("Antes de asignarle esa materia a un paralelo, primero cree esa materia");
            }
        } else {
            System.out.println("No se encontro ese termino, primero crealo");
        }
    }
    /**
     * Se realiza la opcion 3 para poder realizar la carga de archivos o visualizar estudiantes
     */
    public static void Opcion3(){
        System.out.println("Ingrese el termino del que desea previsualizar de la forma(2019-2) : ");
        String term = sc.nextLine();
        String[] partes = term.split("-");
        Termino termino_estudiante = retornarTermino(new Termino(Integer.parseInt(partes[0]), Integer.parseInt(partes[1])));
        if(termino_estudiante!=null){
            System.out.println("Ahora ingresar el codigo de la materia");
            String codigo=sc.nextLine().toUpperCase();
            Materia materia_estudiante=retornarMateria(codigo.toUpperCase());
            if(materia_estudiante!=null){
                Paralelo paralelo=termino_estudiante.ParalelosMateria(materia_estudiante);
                System.out.println("Escriba 1 si desea visualizar los estudiantes o 2 si desea carga de archivos");
                int opcion=Integer.parseInt(sc.nextLine());
                switch(opcion){
                    case 1:
                        paralelo.MostrarEstudiante();
                        break;
                    case 2:
                        leerEstudiantes(codigo ,paralelo ,termino_estudiante.getAño(),termino_estudiante.getTermino());
                }
            }else{
                System.out.println("No se encontro el codigo de esa materia");
            }
        }else{
            System.out.println("No se encontro ese termino ");
        }
    }
    /**
     * Se pasa como parametro la opcion para poder acceder a las subopciones de la opcion 4.
     * @param option 
     */
    public static void Opcion4(int option) {
        switch (option) {
            case 1:
                System.out.print("Ingrese el codigo de la materia: ");
                String cod = sc.nextLine().toUpperCase();
                if (materia.contains(new Materia(cod))) {
                    if (retornarMateria(cod).isActivada()) {
                        for (Materia mat : materia) {
                            if (mat.getCodigo().equalsIgnoreCase(cod)) {
                                if (!mat.getPregunta().isEmpty()) {
                                    for (Pregunta pregun : mat.getPregunta()) {
                                        System.out.println(pregun);
                                    }
                                } else {
                                    System.out.println("Actualmente no tiene preguntas esa materia, primero ingrese preguntas");
                                }
                            }
                        }
                    } else {
                        System.out.println("Materia desactivada");
                    }
                }
                break;
            case 2:
                System.out.print("Ingrese el codigo de la materia: ");
                String cod2 = sc.nextLine().toUpperCase();
                if (materia.contains(new Materia(cod2))) {
                    if (retornarMateria(cod2).isActivada()) {
                        for (Materia mat : materia) {
                            if (mat.getCodigo().equalsIgnoreCase(cod2)) {
                                System.out.println("El nivel maximo establecido para esta materia es :" + mat.getNivel());
                                agregarPreguntas(cod2);
                                releerPreguntas(cod2, mat);
                            }
                        }
                    } else {
                        System.out.println("Materia desactivada");
                    }
                } else {
                    System.out.println("Primero registre ese codigo");
                }
                break;
            case 3:
                System.out.print("Ingrese el codigo de la materia: ");
                String cod3 = sc.nextLine().toUpperCase();
                if (materia.contains(new Materia(cod3))) {
                    if (retornarMateria(cod3).isActivada()) {
                        for (Materia mat : materia) {
                            if (mat.getCodigo().equalsIgnoreCase(cod3)) {
                                for (int i = 0; i < mat.getPregunta().size(); i++) {
                                    System.out.println(Integer.toString(i + 1) + ".-" + mat.getPregunta().get(i));
                                }
                                System.out.println("Escoja la posicion de la pregunta que desea eliminar");
                                int pos = sc.nextInt();
                                sc.nextLine();
                                mat.getPregunta().remove(pos - 1);
                                actualizarPregunta(cod3, mat);
                            }
                        }
                    } else {
                        System.out.println("Materia desactivada");
                    }
                } else {
                    System.out.println("Primero registre ese codigo");
                }
                break;
        }
    }
    /**
     * Para cargar el archivo inicial para que corra el programa.
     * @return 
     */
     public static ArrayList<Pregunta> leerPreguntas() {
        BufferedReader question = null;
        ArrayList<Pregunta> preguntas = new ArrayList<>();
            try {
                String ruta = "src/archivos/Preguntas-CCPG1005.csv";
                question = new BufferedReader(new FileReader(ruta));
                String line = question.readLine();
                while ((line = question.readLine()) != null) {
                    String[] campos = line.split(";");
                    preguntas.add(new Pregunta(campos[0], campos[1],campos[2], campos[3], campos[4], campos[5]));    
                }
            } catch (FileNotFoundException e) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null,e);
            } catch (IOException e) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null,e);
            } finally {
                try {
                    question.close();
                } catch (IOException e) {
                    Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null,e);
                }
            }
            return preguntas;
        }

    /**
     * Metodo para agregar las preguntas de un archivo a una lista y retornar el array con las preguntas para que las tenga la materia
     * @param nivel
     * @param codigo
     * return ArrayList<Pregunta>
     */
    public static ArrayList<Pregunta> leerPreguntas(int nivel,String codigo) {
        BufferedReader question = null;
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        File file;
        file = new File("src/archivos/Preguntas-" + codigo + ".csv");
        if(file.exists()){
            try {
                String ruta = "src/archivos/Preguntas-"+codigo+".csv";
                question = new BufferedReader(new FileReader(ruta));
                String line = question.readLine();
                while ((line = question.readLine()) != null) {
                    String[] campos = line.split(";");
                    if(Integer.parseInt(campos[1]) <= nivel){
                    preguntas.add(new Pregunta(campos[0], campos[1],campos[2], campos[3], campos[4], campos[5]));    
                    }
                }
            } catch (FileNotFoundException e) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null,e);
            } catch (IOException e) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null,e);
            } finally {
                try {
                    question.close();
                } catch (IOException e) {
                    Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null,e);
                }
            }
        }
        return preguntas;
    }
    /**
     * Se usa para leer nuevamente el archivo y poder agregar las preguntas nuevas a la materia.
     * @param cod
     * @param mat 
     */
    public static void releerPreguntas(String cod, Materia mat) {
        BufferedReader question = null;
        try {
            String ruta = "src/archivos/Preguntas-" + cod + ".csv";
            question = new BufferedReader(new FileReader(ruta));
            String line = question.readLine();
            mat.getPregunta().clear();
            while ((line = question.readLine()) != null) {
                String[] campos = line.split(";");
                mat.getPregunta().add(new Pregunta(campos[0], campos[1],campos[2], campos[3], campos[4], campos[5]));    
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null,e);
        } catch (IOException e) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null,e);
        } finally {
            try {
                question.close();
            } catch (IOException e) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null,e);
            }
        }
        
    }
    
    
    /**
     * Metodo con el cual las preguntas nuevas ingresadas por el profesor se
     * registran en el archivo
     */
    public static void agregarPreguntas(String codigo) {
        File file;
        FileWriter write;
        PrintWriter line;
        String enunciado, nivel, correcta, respuesta1, respuesta2, respuesta3;
        file = new File("src/archivos/Preguntas-" + codigo + ".csv");
        try {
            write = new FileWriter(file, true);
            System.out.println("Ingrese el nivel de la pregunta");
            nivel = sc.nextLine();
            while(Integer.parseInt(nivel)> retornarMateria(codigo).getNivel()){
                System.out.println("Ingrese un nivel de acuerdo al nivel maximo establecido en esa materia que es de: "+retornarMateria(codigo).getNivel());
                nivel = sc.nextLine();
            }
            enunciado = JOptionPane.showInputDialog(null, "Ingrese la pregunta: ", "Agregando pregunta", 3);
            correcta = JOptionPane.showInputDialog(null, "Ingrese la respuesta correcta: ", "Agregando pregunta", 3);
            respuesta1 = JOptionPane.showInputDialog(null, "Ingrese una posible respuesta: ", "Agregando pregunta", 3);
            respuesta2 = JOptionPane.showInputDialog(null, "Ingrese la segunda posible respuesta: ", "Agregando pregunta", 3);
            respuesta3 = JOptionPane.showInputDialog(null, "Ingrese la ultima posible respuesta: ", "Agregando pregunta", 3);
            line = new PrintWriter(write);
            line.print(enunciado + ";");
            line.print(nivel + ";");
            line.print(correcta + ";");
            line.print(respuesta1 + ";");
            line.print(respuesta2 + ";");
            line.println(respuesta3);
            line.close();
            write.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * Metodo para actualizar el archivo con nuevas preguntas o borradas
     */
    public static void actualizarPregunta(String codigo, Materia materia) {
        FileWriter writer = null;
        try {
            String ruta = "src/archivos/Preguntas-" + codigo + ".csv";
            writer = new FileWriter(ruta);
            writer.write("enunciado,nivel,respuesta_correcta,respuesta_posible1,respuesta_posible2,respuesta_posible3");
            for (Pregunta p : materia.getPregunta()) {
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
    /**
     * Se utiliza este metodo para realizar el concurso y se pasa como parametro el concurso del cual se obtendra las preguntas y el termino para la creacion de los reportes.
     * @param concurso
     * @param termino
     * @return String
     */
    public static String Nuevo_juego(Juego concurso, Termino termino){        
        System.out.println("Porfavor : Presione una letra para continuar");
        sc.nextLine();
        boolean continuar_juego=true;
        int contador=0;
        int nivel_pregunta=0;
        ArrayList<String> enunciados= new ArrayList<>();
        for(Pregunta pregunta_juego: concurso.getPreguntas()){
            if(continuar_juego){
                enunciados.clear();
                if(nivel_pregunta!=Integer.parseInt(pregunta_juego.getNivel())){
                    System.out.println("Nivel de pregunta:"+pregunta_juego.getNivel());
                    nivel_pregunta=Integer.parseInt(pregunta_juego.getNivel());
                }
                pregunta_juego.AgregarEnunciados(enunciados);
                String respuesta = "";
                while(respuesta == ""){
                    ArrayList<String> enunciados_al_azar= pregunta_juego.mostrarPreguntasJuego(enunciados);
                    if(!concurso.getComodines().isEmpty()){
                        System.out.println("Presiona * para escoger un comodin");
                    }
                    System.out.println("Ingrese la opcion a elegir: ");
                    String opcion=sc.nextLine().toUpperCase();
                    char letra=opcion.charAt(0);
                    respuesta = Respuesta_seleccionada(letra,enunciados_al_azar, concurso, pregunta_juego);
                    if(respuesta.indexOf("-")!=-1){
                        System.out.println("Se volvera a presentar el enunciado: ");
                        System.out.println(pregunta_juego.getEnunciado());
                        String[] respuestas = respuesta.split("-");
                        System.out.println("A.-"+ respuestas[0] + "\n" + "B.-"+ respuestas[1]);
                        System.out.println("Ingrese su opcion: ");
                        opcion=sc.nextLine().toUpperCase();
                        letra=opcion.charAt(0);
                        respuesta = Respuesta_seleccionada(letra,respuestas, concurso, pregunta_juego); 
                    }  
                    if (pregunta_juego.getCorrecta().equals(respuesta)){
                        contador+=1;
                        System.out.println("Felicitaciones respondiste correctamente");
                    }else if(respuesta != ""){
                        continuar_juego=false;
                        System.out.println("Respondiste incorrectamente, la respuesta correcta era la:"+ pregunta_juego.getCorrecta());
                    }   
                }    
            }    
        }
        int niveles_completados;
        if(contador% 2==0){
            niveles_completados=contador/concurso.getNivel()+1;
        }else{
            niveles_completados=contador/concurso.getNivel();
        }
        if(niveles_completados ==concurso.getMateria().getNivel()+1){
            System.out.println("Felicitaciones usted a completado todos los niveles");
            System.out.println("Ingrese el premio: ");
            String premio = sc.nextLine();            
            reporte.add((new Reporte(termino, concurso, concurso.getMateria().getNivel(), premio)).getReport());
            reporte = leerReporte();
            return premio;     
        }else if(niveles_completados>0){
            System.out.println("Usted pudo alcanzar el "+ nivel_pregunta +"th nivel");
            System.out.println("Ingrese el premio: ");
            String premio = sc.nextLine();
            reporte.add((new Reporte(termino, concurso, nivel_pregunta, premio)).getReport());
            reporte = leerReporte();
            return premio;
        }else{
            System.out.println("Usted no pudo completar ningun nivel, lamentablemente no recibe premio");
            reporte.add((new Reporte(termino, concurso)).getReport());
            reporte = leerReporte();
        }
        return null;
    }
    /**
     * Se retorna la respuesta seleccionada segun lo ingresado por el usuario.
     * @param opcion
     * @param enunciados
     * @param juego
     * @param pregunta
     * @return String
     */
    public static String Respuesta_seleccionada(char opcion,ArrayList<String> enunciados, Juego juego, Pregunta pregunta){
        switch(opcion){
            case 'A' :
                return enunciados.get(0);
            case 'B':
                return enunciados.get(1);
            case 'C':
                return enunciados.get(2);
            case 'D':
                return enunciados.get(3);
            case '*':
                if(juego.getComodines().size()>0){
                        System.out.println("Tiene disponible ");
                        for (int i = 0; i<= juego.getComodines().size()-1; i++){
                            if (juego.getComodines().get(i).isEstado()){
                                System.out.println(i+1 +  ".- " + juego.getComodines().get(i).getNombre());
                            }
                        }
                        System.out.println("Ingrese su opcion: ");
                        int co = Integer.parseInt(sc.nextLine());
                        Comodin comodinUsar = juego.retornarComodin(juego.getComodines().get(co-1).getNombre());
                        if (comodinUsar.getNombre().equals(Comodin.CINCUENTA_CINCUENTA.getNombre())){
                            juego.getComodines().remove(co-1);
                            return juego.comodinCincuenta(enunciados, pregunta);
                        }else if(comodinUsar.getNombre().equals(Comodin.CONSULTA_COMPANIERO.getNombre())){
                            juego.getComodines().remove(co-1);
                            System.out.println("Tienes autorizacion para preguntarle a un compañero la respuesta a esta pregunta. Cuando estes listo presiona cualquier boton.");
                            sc.nextLine();
                        }else if(comodinUsar.getNombre().equals(Comodin.CONSULTA_SALON.getNombre())){
                            juego.getComodines().remove(co-1);
                            System.out.println("Ahora puedes preguntarle al salon cual respuesta creen ellos que es la correcta. Cuando estes listo presiona cualquier boton.");
                            sc.nextLine();
                        }
                        
                }else{
                    System.out.println("No tiene comodines disponible :(");
                    return "";
                }
        }
        return "";
    }
    /**
     * Se retorna la respuesta seleccionado en caso de que se usa el comodin 50 y 50.
     * @param opcion
     * @param enunciados
     * @param juego
     * @param pregunta
     * @return 
     */
     public static String Respuesta_seleccionada(char opcion, String[] enunciados, Juego juego, Pregunta pregunta){
        switch(opcion){
            case 'A' :
                return enunciados[0];
            case 'B':
                return enunciados[1];
        }
        return "";
    }
    /**
     * Se crea el juego de acuerdo al termino seleccionado  y los parametros establecidos
     * @param termino_seleccionado
     * @return Juego
     */
    public static Juego Concurso(Termino termino_seleccionado){
        System.out.println("Porfavor ingrese el codigo de la materia");
        String codigo = sc.nextLine().toUpperCase();
        Materia materia_escogida= retornarMateria(codigo);
        if (materia_escogida!=null) {
            System.out.println("Ahora porfavor ingrese el Paralelo");
            int numero = Integer.parseInt(sc.nextLine());
            Paralelo paralelo_elegido = termino_seleccionado.retornarParalelo(numero);
            if (paralelo_elegido != null) {
                System.out.println("Ingrese por favor el número de preguntas por nivel que desea:");
                int nivel= Integer.parseInt(sc.nextLine());
                ArrayList<Pregunta> Preguntas_juego= Corroborar_preguntas(materia_escogida,nivel);
                if (Preguntas_juego!=null){
                    System.out.println("Ahora presione 1 si desea escoger al estudiante o 2 si desea escoger al azar");
                    int opcion =Integer.parseInt(sc.nextLine());
                    switch(opcion){
                        case 1:
                            System.out.println("Ingrese el numero de matricula");
                            String matricula=sc.nextLine();
                            Juego juego_a_realizar=new Juego(materia_escogida,paralelo_elegido,matricula,Preguntas_juego,nivel);
                            if(juego_a_realizar.getAleatorio()!=null){
                                return juego_a_realizar;
                            }
                            System.out.println("No se encontro la matricula");
                            break;
                        case 2:
                            return new Juego(materia_escogida,paralelo_elegido,Preguntas_juego,nivel);
                    }
                }else{
                    System.out.println("No hay esa cantidad de niveles por materia");
                }
            }else{
                System.out.println("Paralelo no existente");
            }
        }else{
            System.out.println("Materia no existente");
        }
        return null;
    }
    /**
     * Se corrobora la existencia de ese numero de preguntas de la materia y retorna el array list con las preguntas seleccionados
     * @param materia
     * @param nivel
     * @return 
     */
    public static ArrayList<Pregunta> Corroborar_preguntas(Materia materia,int nivel){
        return materia.Verificar_preguntas(nivel);
    }
    /**
     * Se lee los estudiantes de acuerdo al codigo,paralelo, anio y termino.De paso se agrega los estudiantes a los paralelos. 
     * @param codigo
     * @param paralelo
     * @param anio
     * @param termino 
     */
    public static void leerEstudiantes(String codigo,Paralelo paralelo,int anio,int termino) {
        BufferedReader csvReader = null;
        File file;
        file = new File("src/archivos/"+codigo+"-"+paralelo.getNumero()+"-"+anio+"-"+termino+".csv");
        if(file.exists()){
            try {
                String ruta = "src/archivos/"+codigo+"-"+paralelo.getNumero()+"-"+anio+"-"+termino+".csv"; //ruta del archivo que se va a leer
                csvReader = new BufferedReader(new FileReader(ruta));
                ArrayList<Estudiante> estudiantes = new ArrayList<>();
                String fila;
                while ((fila = csvReader.readLine()) != null) { //iterar en el contenido del archivo
                    String[] data = fila.split(",");
                    estudiantes.add(new Estudiante(data[0], data[1], data[2]));
                }
                paralelo.setEstudiantes(estudiantes);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    csvReader.close();
                } catch (IOException ex) {
                    Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            System.out.println("Asegure que haya puesto el archivo de la forma indicada");
        }
    }
    /**
     * Se retorna la Materia segun el codigo y en caso de que no exista la materia se retorna null
     * @param codigo
     * @return Materia
     */
    public static Materia retornarMateria(String codigo) {
        for (Materia mate : materia) {
            if (mate.getCodigo().equals(codigo)) {
                return mate;
            }
        }
        return null;
    }
    /**
     * Se retorna el Termino segun el anio y temino y en caso de que no exista el termino se retorna null
     * @param term
     * @return Termino
     */
    public static Termino retornarTermino(Termino term) {
        for (Termino te : termino) {
            if (te.equals(term)) {
                return te;
            }
        }
        return null;
    }
    /**
     * Se leen  los reportes y se entregan los reportes segun los parametros establecidos con el fin de tenerlos en el menu principal.
     * @return 
     */
    public static ArrayList<String[]> leerReporte() {
        ArrayList<String[]> reportes = new ArrayList<>();
        BufferedReader report = null;
        String ruta = "src/archivos/Reportes.csv";
        try {
            report = new BufferedReader(new FileReader(ruta));
            String line;
            report.readLine();
            while ((line = report.readLine()) != null) {
                String[] datos = line.split(";");
                reportes.add(datos);
                
            }
        } catch (FileNotFoundException ex) {
            return reportes;
        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reportes;
    }

    public static ArrayList<String[]> getReporte() {
        return reporte;
    }

    public static void setReporte(ArrayList<String[]> reporte) {
        MenuPrincipal.reporte = reporte;
    }   
}
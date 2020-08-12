
package concurso;
public enum Comodin{
    CONSULTA_COMPANIERO("Consulta a un companiero",true), CONSULTA_SALON("Consulta al salon",true), CINCUENTA_CINCUENTA("Cincuenta/Cincuenta",true);
    
    private String nombre;
    private boolean estado;
    
    private Comodin(String nombre, boolean estado){
        this.nombre = nombre;
        this.estado = estado;
    }
    //Getters && Setters
    public void usarComodin(){
        this.estado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}


/*
Constructor
    tipo
    marca
    modelo
    consumo
Metodos
    obtener
        tipo
        marca
        modelo
        consumo
    cambiar consumo

*/
class Dispositivo {
    private String tipo;
    private String marca;    
    private String modelo;    
    private double consumo;    
    

    public Dispositivo(String tipo, String marca, String modelo, double consumo) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        // de momento se considera el consumo mensual del dispositivo
        this.consumo = consumo;
    }
    
    public String getTipo() {
        return this.tipo;
    }

    public String getModelo() {
        return this.modelo;
    }

    public String getMarca() {
        return this.marca;
    }

    public double getConsumo() {
        return this.consumo;
    }

    // Actualizar consumo
    public void cambiarConsumo(double newConsumo) {
        this.consumo = newConsumo;
    }
    
    
}
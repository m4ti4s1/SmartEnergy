


/* 
Cosntructor
    nombre
    password
    email
    dispositivos
    empresa electrica
Metodos
    agregar dispositivo
    quitar dispositivo
    ver lista dispositivos
    obtener informacion usuario
    consumo diario, mensual
    
*/
import java.util.ArrayList;
class Usuario {
    private ArrayList<Dispositivo>  dispositivos;
    private String nombre;
    private String password;
    private String email;
    private String empresa;
    
    public Usuario(String nombre, String password, String email, String empresa) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.empresa = empresa;
        this.dispositivos = new ArrayList<>();
    }
    
    public void cambiarPassword(String oldPassword, String newPassword) {

        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
        } else {
            System.out.println("Old Password incorrect");
        }
    }
    

    /**
     * Metodo para agregar un dispositivo a un usuario
     * @param dispositivo dispositivo
     */
    public void addDispositivo(Dispositivo dispositivo) {
        dispositivos.add(dispositivo);
    }

    public void removeDispositivo(Dispositivo dispositivo) {
        dispositivos.remove(dispositivo);
    }

    public void listaDispositivos() {
        ArrayList<String> dispositivosTipo = new ArrayList<>();
        ArrayList<String> dispositivosMarca = new ArrayList<>();
        ArrayList<Double> dispositivosConsumo = new ArrayList<>();

        for (Dispositivo dispositivo : dispositivos) {
            dispositivosTipo.add(dispositivo.getTipo());
            dispositivosMarca.add(dispositivo.getMarca());
            dispositivosConsumo.add(dispositivo.getConsumo());
        }
        

        System.out.println("Lista Dispositivos"); 
        System.out.printf("%-15s %-15s %-10s", "Tipo", "Marca", "Consumo");
        for (int i = 0; i < dispositivosTipo.size(); i++) {
            System.out.printf("%n%-15s %-15s %.2f", dispositivosTipo.get(i), dispositivosMarca.get(i), dispositivosConsumo.get(i));
        }

    }
    
    /**
     * 
     */
    public void getInfo() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("email: " + this.email);
        System.out.println("Empresa: " + this.empresa);
        listaDispositivos();
        

    }

    public void getConsumoMensual() {
        String empresaCalculo = this.empresa;
        double consumoTotalDispositivos = 0;
        double iva, subtotal, cargoFijo, kWh, cargoTransporte, cargoCompra;
        
        double totalFinal = 0;
        
        for (Dispositivo dispositivo : dispositivos) {
            consumoTotalDispositivos += dispositivo.getConsumo();
        }


            
        empresaCalculo = empresaCalculo.toLowerCase();
        switch (empresaCalculo) {
            case "cge":
                cargoFijo = 977.35;
                kWh = 93.975;
                cargoCompra = 21.519;
                cargoTransporte = 3732;

                subtotal = ((kWh + cargoCompra) * (consumoTotalDispositivos)) + cargoFijo + cargoTransporte;
                iva = subtotal * 0.19;

                totalFinal = subtotal + iva;

                break;
        }
        
        System.out.println("\nConsumo Mensual: " + totalFinal);
    }
}
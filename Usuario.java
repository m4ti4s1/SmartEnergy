


/* 
Constructor
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
import java.util.Scanner;
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
    
    public void cambiarPassword(String newPassword) {
        this.password = newPassword;
    }
    
    public void cambiarNombre(String newNombre) {
        this.nombre = newNombre;
    }
    
    public void cambiarEmail(String newEmail) {
        this.email = newEmail;
    }
    
    public void cambiarEmpresa(String newEmpresa) {
        this.empresa = newEmpresa;
    }
    

    public boolean existeDispositivo(String modelo) {

        for (Dispositivo dispositivo : dispositivos) {
            if (dispositivo.getModelo().equalsIgnoreCase(modelo)) {
                return true;
            }
        }

        return false;
    }






    public String getPassword() {
        return this.password;
    }
    public String getNombre() {
        return this.nombre;
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

    public void removeDispositivoModelo(String modelo) {
        for (Dispositivo dispositivo : dispositivos) {
            if (dispositivo.getModelo().equalsIgnoreCase(modelo)) {
                removeDispositivo(dispositivo);
            }
        }
    }

    public void modificarDispositivo(String modelo) {
        Scanner sc = new Scanner(System.in);
        int opc;
        for (Dispositivo dispositivo : dispositivos) {
            if (dispositivo.getModelo().equalsIgnoreCase(modelo)){
                do {
                    System.out.println("\n--- Ingresa una opcion ---");
                    System.out.println("1. Modificar tipo");
                    System.out.println("2. Modificar marca");
                    System.out.println("3. Modificar modelo");
                    System.out.println("4. Modificar consumo");
                    System.out.println("5. volver a menu anterior");
                    opc = ingresaOpcion(5);
                    switch (opc) {
                        case 1:
                            String newTipo = sc.next();
                            dispositivo.cambiarTipo(newTipo);
                        case 2:
                            String newMarca = sc.next();
                            dispositivo.cambiarMarca(newMarca);
                        case 3:
                            String newModelo = sc.next();
                            dispositivo.cambiarModelo(newModelo);
                        case 4:
                            double consumo = sc.nextDouble();
                            dispositivo.cambiarConsumo(consumo);
                        case 5:
                            System.out.println("Saliendo a menu anterior...");
                            break;
                    }
                } while (opc != 5);
            }
        }
        sc.close();
        
    }
    

    public void listaDispositivos() {
        ArrayList<String> dispositivosTipo = new ArrayList<>();
        ArrayList<String> dispositivosMarca = new ArrayList<>();
        ArrayList<String> dispositivosModelo = new ArrayList<>();
        ArrayList<Double> dispositivosConsumo = new ArrayList<>();

        for (Dispositivo dispositivo : dispositivos) {
            dispositivosTipo.add(dispositivo.getTipo());
            dispositivosMarca.add(dispositivo.getMarca());
            dispositivosModelo.add(dispositivo.getModelo());
            dispositivosConsumo.add(dispositivo.getConsumo());
        }
        

        System.out.println("\n--- Lista Dispositivos ---\n"); 
        System.out.printf("%-15s %-15s %-15s %-10s", "Tipo", "Marca","Modelo", "Consumo");
        for (int i = 0; i < dispositivosTipo.size(); i++) {
            System.out.printf("%n%-15s %-15s %-15s %.2f", dispositivosTipo.get(i), dispositivosMarca.get(i), dispositivosModelo.get(i), dispositivosConsumo.get(i));
        }
        System.out.println();

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
    
    public static int ingresaOpcion(int cantOpciones) {
        Scanner sc = new Scanner(System.in);
        int opc = 0;
        boolean valido = false; 
        while (!valido) {
            try {
                System.out.print("\tIngresa una opcion: ");
                opc = sc.nextInt();
                if (opc > 0 && opc <= cantOpciones) {
                    valido = true;
                } else {
                    System.out.println("Opcion invalida, elija una valida");
                }
            }catch (Exception e) {
                System.out.println("La opcion debe ser un numero entero");
                sc.next();
            }
        }
        sc.close();
        return opc;
    }
}
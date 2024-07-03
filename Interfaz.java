// Clase para manejar la "aplicacion"
public class Interfaz {
    public static void main(String[] args) {
        Usuario user1 = new Usuario("matias", "12345", "matias@gmail.com", "cge");

        Dispositivo notebook = new Dispositivo("Notebook", "Asus", "Rog Zephyrus", 51);
        Dispositivo TV = new Dispositivo("TV", "LG", "Smart TV", 51);
        Dispositivo Refrigerador = new Dispositivo("Electrodomestico", "Samsung", "NewOne", 52);

        user1.addDispositivo(notebook);
        user1.addDispositivo(Refrigerador);
        user1.addDispositivo(TV);
        
        user1.listaDispositivos();
        user1.removeDispositivo(TV);
        user1.listaDispositivos();
    }
}
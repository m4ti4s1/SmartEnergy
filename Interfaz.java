// Clase para manejar la "aplicacion"
import java.util.Scanner;
import java.util.ArrayList;
public class Interfaz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        ArrayList<Usuario> usuarios = new ArrayList<>();

        boolean exit = false;

        do {


            String userName = sc.next();
            String password = sc.next();
            String email = sc.next();
            String empresa = "cge"; 
            

            if (userName.equalsIgnoreCase("exit")) {
                exit = true;
                System.out.println("No more users then");
            } else {
                Usuario userGeneric = new Usuario(userName, password, email, empresa);
                usuarios.add(userGeneric);
            }
        } while (!exit);

        
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombre());
        }
        /*
        To access to certain methods of an user we have to iterate to the user using the name and the method getName
        1. Ask the user for a name
        2. Iterate the list ultil we find the user
        3. use the identifider "usuario" to access the methods
        */
        

        /* 
         * 1. Add an user (check that the name is not repeated)
         * 2. select an user (give the list)
         *      2.1 Add device
         *      2.2 Remove device (see list of devices)
         *      2.3 See the devices list
         *      2.4 get full info
         *      2.5 get mensual consume
         *      2.6 access a device (see list)
         *          2.6.1 change consume
         *          2.6.2 get info of the device
         *          2.6.2 change any attribute of the device (type, brand, model, consume)
         * 3. Modify an user
         *      3.1 change (name, email, company) 
         *      3.2 change password (verify password)
         */






        /*
        // Usuario 1
        Usuario user = new Usuario("matias", "12345", "matias@gmail.com", "cge");
        usuarios.add(user);

        Dispositivo notebook1 = new Dispositivo("Notebook", "Asus", "Rog Zephyrus", 51);
        Dispositivo TV1 = new Dispositivo("TV", "LG", "Smart TV", 51);
        Dispositivo Refrigerador1 = new Dispositivo("Electrodomestico", "Samsung", "NewOne", 52);

        user.addDispositivo(notebook1);
        user.addDispositivo(Refrigerador1);
        user.addDispositivo(TV1);
        



        user.listaDispositivos();
        user.removeDispositivo(TV1);
        System.out.println();
        user.listaDispositivos();

        System.out.println("\n");
        user.getInfo();

        

        
        Usuario user1 = new Usuario("matias", "12345", "matias@gmail.com", "cge");
        usuarios.add(user);

        Dispositivo notebook2 = new Dispositivo("Notebook", "HP", "Vector", 51);
        Dispositivo TV2 = new Dispositivo("TV", "LG", "Smart TV", 51);
        Dispositivo Refrigerador2 = new Dispositivo("Electrodomestico", "Samsung", "NewOne", 52);

        user.addDispositivo(notebook2);
        user.addDispositivo(Refrigerador2);
        user.addDispositivo(TV2);
        */
    }
}
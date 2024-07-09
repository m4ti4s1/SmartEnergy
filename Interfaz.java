// Clase para manejar la "aplicacion"
import java.util.Scanner;
import java.util.ArrayList;
public class Interfaz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 


        //      TODO
        //  1.  Agregar lista de empresas
        //  2.  add option in case 3 (print info user)
        //
       
        

        /* 
         * 1. [x] Add an user (check that the name is not repeated)
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
         * 3. [x] Modify an user
         *      3.1 change (name, email, company) 
         *      3.2 change password (verify password)
         * 4. [x] exit
         */
        

        ArrayList<Usuario> usuarios = new ArrayList<>();

        String[] empresas = {"cge","copelec", "enel"};
        
        Usuario user1 = new Usuario("Matias", "matias12345", "matias@gmail.com", "cge");
        Usuario user2 = new Usuario("Antonella", "antonella12345", "Antonella@gmail.com", "cge");
        
        usuarios.add(user1);
        usuarios.add(user2);

        Dispositivo cel1 = new Dispositivo("Celular", "Samsung", "A50", 14);
        Dispositivo cel2 = new Dispositivo("Celular", "Xiaomi", "X3 Pro", 14);

        Dispositivo laptop1 = new Dispositivo("Laptop", "Asus", "Zephyrus", 35);
        Dispositivo laptop2 = new Dispositivo("Laptop", "HP", "Vector", 35);

        user1.addDispositivo(cel1);
        user1.addDispositivo(laptop1);
        
        user2.addDispositivo(cel2);
        user2.addDispositivo(laptop2);
        


        int opc = 0;
        String nombre;
        String password;
        String email;
        String empresa;
        boolean usuarioValido, dispositivoDuplicado;
        
        
        do {
            
            System.out.println("\n1. Agrega un nuevo usuario");
            System.out.println("2. Selecccionar un usuario para realizar acciones");
            System.out.println("3. Modificar datos de un usuario");
            System.out.println("4. Salir");
            opc = ingresaOpcion(sc, 4);

            switch (opc) {
                case 1:
                    boolean usuarioRepetido = false;
                    System.out.println("Ingresa el nombre de usuario");
                    nombre = sc.next();

                    // verificar que el nombre de usuario no exista
                    for (Usuario usuario : usuarios) {
                        if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                            usuarioRepetido = true;
                        }
                    }
                    
                    // opciones dependiendo si es nombre esta o no en la lista de usuarios
                    if (!usuarioRepetido) {
                        System.out.print("Ingresa contraseña: ");
                        password = sc.next();

                        System.out.print("Ingresa email: ");
                        email = sc.next();
                    
                        System.out.print("Selecciona una empresa");
                        empresa = "";
                        // agregar empresa
                        int nroEmpresa = ingresaOpcion(sc, empresas.length);
                        
                        switch (nroEmpresa) {
                            case 1 -> empresa = empresas[0];
                            case 2 -> empresa = empresas[1];
                            case 3 -> empresa = empresas[2];
                        }

                        Usuario user = new Usuario(nombre, password, email, empresa);
                        usuarios.add(user);
                    } else {
                        System.out.println("Nombre de usuario no disponible");
                    }

                    break;

                case 2:
                    // seleccionar un usuario
                    // 1. lista de usuarios
                    // ask a name and verify that is a valid username
                    listaUsuarios(usuarios);
                    System.out.print("Selecciona un usuario: ");
                    nombre = sc.next();  
                    
                    usuarioValido = existeUsuario(nombre, usuarios);
                    
                    if (usuarioValido) {
                        for (Usuario usuario : usuarios) {
                            if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                                do {
                                    System.out.println("\n--- Ingresa una Opcion ---");    
                                    System.out.println("1. Ver lista dispositivos");
                                    System.out.println("2. Agregar un dispostitivo");
                                    System.out.println("3. Eliminar un dispositivo");
                                    System.out.println("4. Obtener informacion de usuario");
                                    System.out.println("5. Obtener consumo mensual");
                                    System.out.println("6. Modificar un dispotitivo");
                                    System.out.println("7. Volver a menu principal");

                                    opc = ingresaOpcion(sc, 7);
                                
                                    switch (opc) {
                                        case 1: // ready
                                            usuario.listaDispositivos();
                                            break;
                                        case 2:
                                            System.out.println("\n--- Agregando un nuevo dispositivo ---\n");

                                            // confirmar duplicados con el modelo de un dispositivo
                                
                                            System.out.print("\nIngresa el modelo de un nuevo dispositivo"); 
                                            String modelo = sc.next();
                                            dispositivoDuplicado = usuario.existeDispositivo(modelo);

                                            if (!dispositivoDuplicado) {
                                                System.out.print("Ingresa el tipo de dispostivo: ");
                                                String tipo = sc.next();
                                                System.out.print("Ingresa la marca del dispositivo: ");
                                                String marca = sc.next();
                                                System.out.print("Ingresa el consumo mensual del dispositivo: ");
                                                double consumo = sc.nextDouble();

                                                Dispositivo newDispositivo = new Dispositivo(tipo, marca, modelo, consumo);
                                                usuario.addDispositivo(newDispositivo);                                            
                                            } else {
                                                System.out.println("El modelo de dispositivo ya existe");
                                            }
                                            break;

                                        case 3:
                                            // eliminar un dispositivo por modelo
                                            usuario.listaDispositivos();
                                            System.out.print("Ingresa el modelo del dispositivo a elimiar: ");
                                            modelo = sc.next();

                                            boolean existe = usuario.existeDispositivo(modelo);
                                            
                                            if (existe) {
                                                usuario.removeDispositivoModelo(modelo);
                                                System.out.println("Dispositivo Eliminado");
                                            } else {
                                                System.out.println("El dispositivo no existe");
                                            }
                                            break;
                                    
                                        case 4:
                                            usuario.getInfo();
                                            break;
                                        case 5:
                                            usuario.getConsumoMensual();
                                            break;
                                        case 6:
                                            // modificar un dispositivo
                                           
                                            usuario.listaDispositivos();
                                            System.out.print("Ingresa modelo de dispositivo para modificar: ");
                                            modelo = sc.next();
                                            existe = usuario.existeDispositivo(modelo);

                                            if (existe) {
                                                // funcion en bucle con menu interno
                                                usuario.modificarDispositivo(modelo);;
                                            } else {
                                                System.out.println("Dispositivo no existe");
                                            }
                                            break;
                                        case 7:
                                            System.out.println("Saliendo a menu principal...");
                                            break;
                                    }
                                } while (opc !=7);
                            }
                        }
                    } else {
                        System.out.println("Usuario Invalido");
                    }

                    break;
                case 3:
                    listaUsuarios(usuarios);
                    boolean pswdValida = false;
                    int intentosPswd = 0;

                    if (usuarios.size() > 0) {
                        nombre = sc.next();
                        usuarioValido = existeUsuario(nombre, usuarios);
                        
                        // case if the user is valid
                        // verify that the password mached with the user password
                        
                        if (usuarioValido) {
                            // verify user by passing the password
                            for (Usuario usuario : usuarios) {
                                if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                                    do {
                                        System.out.println("Intentos Restante: " + (3 - intentosPswd));
                                        System.out.print("Ingresa contraseña: ");
                                        password = sc.next();
                                        if (usuario.getPassword().equals(password)) {
                                            pswdValida = true;
                                        } else {
                                            System.out.println("Contraseña incorrecta...");
                                            intentosPswd++;
                                        }
                                    } while (intentosPswd <3 && pswdValida == false);
                                
                                
                                    // if the password for the user is valid, do whateaver he wants
                                    if (pswdValida) {
                                        System.out.println("--- Ingresa una opcion ---");
                                        System.out.println("1. Cambiar Nombre");
                                        System.out.println("2. Cambiar contrasena");
                                        System.out.println("3. Cambiar email");
                                        System.out.println("4. Cambiar empresa");
                                        System.out.println("5. salir a menu principal");
                                        opc = ingresaOpcion(sc, 5);


                                        //          TODO
                                        //   Add confirmation of each option
                                    
                                        switch (opc) {
                                            case 1:
                                                nombre = sc.next();
                                                usuario.cambiarNombre(nombre);
                                                break;
                                            case 2:
                                                password = sc.next();
                                                usuario.cambiarPassword(password);
                                                break;
                                            case 3:
                                                email = sc.next();
                                                usuario.cambiarEmail(email);
                                                break;
                                            case 4:
                                                empresa = sc.next();
                                                usuario.cambiarEmpresa(empresa);
                                                break;
                                            case 5:
                                                System.out.println("Saliendo a menu principal...");
                                                break;
                                        }
                                    } else {
                                        System.out.println("Accesso dengado...");
                                    }
                                }
                            }     
                        } else {
                            System.out.println("Usuario Invalido");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
            }
        }while (opc != 4);
    }
    
    




    /**
     * Metodo para verficar el input del usuario dado un rango desde 0 hasta cantOpciones
     * @param sc Scanner
     * @param cantOpciones limite de input
     * @return devuelve un valor valido
     */
    public static int ingresaOpcion(Scanner sc, int cantOpciones) {
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
        return opc;
    }
    
    
    /**
     * Metodo para listar los usuarios creados, de no haber ninguno muestra que no existen usuarios
     * @param usuarios lista de usuarios
     */
    public static void listaUsuarios(ArrayList<Usuario> usuarios) {
        if (usuarios.size() > 0) {
            System.out.println("\n--- Lista de Usuarios ---");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario.getNombre());
                }
        } else {
            System.out.println("No existe ningun usuario");
        }
    }
   
    

    
    // verify that a use exist
    /**
     * Metodo que retorna un boolean si un usuario existe
     * @param nombre usuario a buscar
     * @param usuarios lista de usuarios creados
     * @return true or false si el nombre coincide con algun usuario creado
     */
    public static boolean existeUsuario(String nombre, ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    
}
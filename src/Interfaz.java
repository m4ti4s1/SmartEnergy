// Clase para manejar la "aplicacion"
package src;
import java.util.Scanner;
import java.util.ArrayList;
public class Interfaz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 


        // TODO 
        

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

        src.Dispositivo cel1 = new src.Dispositivo("Celular", "Samsung", "A50", 14);
        src.Dispositivo cel2 = new src.Dispositivo("Celular", "Xiaomi", "X3 Pro", 14);

        src.Dispositivo laptop1 = new src.Dispositivo("Laptop", "Asus", "Zephyrus", 35);
        src.Dispositivo laptop2 = new src.Dispositivo("Laptop", "HP", "Vector", 35);

        user1.addDispositivo(cel1);
        user1.addDispositivo(laptop1);
        
        user2.addDispositivo(cel2);
        user2.addDispositivo(laptop2);
        

        // variables que se usan en el curso del menu
        int opc = 0;
        String nombre, password, email, empresa;
        boolean usuarioValido, dispositivoDuplicado;
        
        
        do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Agrega un nuevo usuario");
            System.out.println("2. Selecccionar un usuario para realizar acciones");
            System.out.println("3. Modificar datos de un usuario");
            System.out.println("4. Salir");
            opc = ingresaOpcion(sc, 4);

            switch (opc) {
                // Ingresa un nuevo usuario
                case 1:
                    boolean usuarioRepetido = false;
                    System.out.println("Ingresa el nombre de usuario");
                    nombre = sc.next();

                    // verificar que el nombre de usuario no exista en los usuarios creados
                    usuarioRepetido = existeUsuario(nombre, usuarios);
                
                    // opciones dependiendo si es nombre esta o no en la lista de usuarios
                    if (!usuarioRepetido) {
                        System.out.print("Ingresa contraseña: ");
                        password = sc.next();

                        System.out.print("Ingresa email: ");
                        email = sc.next();
                    
                        // agregar empresa
                        
                        System.out.print("Selecciona una empresa");
                        for (int i = 0; i < empresas.length; i++) {
                            System.out.println((i+1) + ". " + empresas[i]);
                        }
                        int nroEmpresa = ingresaOpcion(sc, empresas.length);
                    
                        empresa = "";
                        
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

                // seleccionar un usuario para realizar acciones
                case 2:

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
                                        case 1: 
                                            usuario.listaDispositivos();
                                            break;
                                        case 2:
                                            System.out.println("\n--- Agregando un nuevo dispositivo ---\n");

                                            // confirmar duplicados con el modelo de un dispositivo
                                
                                            System.out.print("\nIngresa el modelo del nuevo dispositivo"); 
                                            String modelo = sc.next();
                                            dispositivoDuplicado = usuario.existeDispositivo(modelo);

                                            if (!dispositivoDuplicado) {
                                                System.out.print("Ingresa el tipo de dispostivo: ");
                                                String tipo = sc.next();
                                                System.out.print("Ingresa la marca del dispositivo: ");
                                                String marca = sc.next();
                                        
                                                // verificar consumo valido
                                                double consumo = 0;
                                                boolean consumoValido = false;
                                                while (!consumoValido) {
                                                    try {
                                                        System.out.print("Ingresa el consumo mensual del dispositivo: ");
                                                        consumo = sc.nextDouble();
                                                        if (consumo >= 0) {
                                                            consumoValido = true;
                                                        } else {
                                                            System.out.println("El consumo debe ser mayor o igual a 0");
                                                        }
                                                        
                                                    } catch (Exception e) {
                                                        System.out.println("El consumo debe ser un numero");
                                                        sc.next();
                                                    }
                                                } 

                                                src.Dispositivo newDispositivo = new src.Dispositivo(tipo, marca, modelo, consumo);
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
                                                System.out.println("src.Dispositivo eliminado exitosamente");
                                            } else {
                                                System.out.println("El dispositivo no existe o el modelo esta mal escrito");
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
                                                System.out.println("src.Dispositivo no existe o el modelo esta mal escrito");
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
                    // modificar datos de un usuario
                    listaUsuarios(usuarios);
                    boolean pswdValida = false;
                    int intentosPswd = 0;

                    if (usuarios.size() > 0) {
                        System.out.print("Ingresa nombre de un usuario: ");
                        nombre = sc.next();
                        usuarioValido = existeUsuario(nombre, usuarios);
                        
                        
                        if (usuarioValido) {
                            // verify user by passing the password
                            for (Usuario usuario : usuarios) {
                                if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                                    do {
                                        System.out.print("Ingresa contraseña: ");
                                        password = sc.next();
                                        if (usuario.getPassword().equals(password)) {
                                            pswdValida = true;
                                        } else {
                                            System.out.println("Contraseña incorrecta...");
                                            System.out.println("Intentos Restante: " + (2 - intentosPswd));
                                            intentosPswd++;
                                        }
                                    } while (intentosPswd <3 && !pswdValida);
                                
                                
                                    // if the password for the user is valid, do whateaver he wants
                                    if (pswdValida) {
                                        System.out.println("\n--- Ingresa una opcion ---");
                                        System.out.println("1. Cambiar Nombre");
                                        System.out.println("2. Cambiar contrasena");
                                        System.out.println("3. Cambiar email");
                                        System.out.println("4. Cambiar empresa");
                                        System.out.println("5. salir a menu principal");
                                        opc = ingresaOpcion(sc, 5);


                                        //          TODO
                                        //   Add confirmation of each option
                                        boolean confirm;
                                        int intentos;
                                        switch (opc) {
                                            case 1:
                                                System.out.print("Ingresa nuevo nombre: ");
                                                nombre = sc.next();
                                                String nombreComfirmado = "";
                                                intentos = 0;
                                                confirm = false;
                                                do {
                                                    System.out.print("\nConfirma nombre: ");
                                                    nombreComfirmado = sc.next();

                                                    if (nombre.equals(nombreComfirmado)) {
                                                        confirm = true;
                                                    } else {
                                                        System.out.println("Nombre no coincide");
                                                        System.out.println("Intentos restantes" + (2 - intentos));
                                                        intentos++;
                                                    }

                                                } while (intentos < 3 && !confirm);

                                                if (confirm) {
                                                    usuario.cambiarNombre(nombre);
                                                    System.out.println("Nombre cambiado");
                                                } else {
                                                    System.out.println("La confimacion no fue exitosa");
                                                }
                                                break;

                                            case 2:
                                                String passwordConfimada;
                                                confirm = false;
                                                intentos = 0;
                                        
                                                System.out.print("Ingresa nueva contraseña: ");
                                                password = sc.next();
                                                
                                                do {
                                                    System.out.println("Confirma contraseña: ");
                                                    passwordConfimada = sc.next();
                                                    
                                                    if (password.equals(passwordConfimada)) {
                                                        confirm = true;
                                                    } else {
                                                        System.out.println("Contraseñas no coinciden");
                                                        System.out.println("Intentos restantes: " + (2 - intentos));
                                                        intentos++;
                                                    }
                                                } while (intentos < 3 && !confirm);


                                                if (confirm) {
                                                    usuario.cambiarPassword(password);
                                                    System.out.println("Contraseña cambiada");
                                                } else {
                                                    System.out.println("La confimacion no fue exitosa");
                                                }

                                                break;
                                            case 3:
                                                String emailConfirmado;
                                                confirm = false;
                                                intentos = 0;
                                        
                                                System.out.print("Ingresa nuevo email: ");
                                                email = sc.next();
                                                
                                                do {
                                                    System.out.println("Confirma email: ");
                                                    emailConfirmado = sc.next();
                                                    
                                                    if (password.equals(emailConfirmado)) {
                                                        confirm = true;
                                                    } else {
                                                        System.out.println("Emails no coinciden");
                                                        System.out.println("Intentos restantes: " + (2 - intentos));
                                                        intentos++;
                                                    }
                                                } while (intentos < 3 && !confirm);


                                                if (confirm) {
                                                    usuario.cambiarEmail(email);
                                                    System.out.println("Email cambiado");
                                                } else {
                                                    System.out.println("La confimacion no fue exitosa");
                                                }
                                                break;

                                            case 4:
                                                int empresaConfirmada;
                                                confirm = false;
                                                intentos = 0;
                                                empresa = "";
                                                
                                                System.out.print("Selecciona una empresa");
                                                for (int i = 0; i < empresas.length; i++) {
                                                    System.out.println((i+1) + ". " + empresas[i]);
                                                }
                                        
                                                int nroEmpresa = ingresaOpcion(sc, empresas.length);
                                                switch (nroEmpresa) {
                                                    case 1 -> empresa = empresas[0];
                                                    case 2 -> empresa = empresas[1];
                                                    case 3 -> empresa = empresas[2];
                                                } 
                                        
                                                do {
                                                    System.out.println("Confirma empresa: ");
                                                    empresaConfirmada = ingresaOpcion(sc, empresas.length);
                                                    
                                                    if (nroEmpresa == empresaConfirmada) {

                                                        switch (nroEmpresa) {
                                                            case 1 -> empresa = empresas[0];
                                                            case 2 -> empresa = empresas[1];
                                                            case 3 -> empresa = empresas[2];
                                                        } 
                                                        confirm = true;
                                                    } else {
                                                        System.out.println("Empresas no coinciden");
                                                        System.out.println("Intentos restantes: " + (2 - intentos));
                                                        intentos++;
                                                    }
                                                } while (intentos < 3 && !confirm);


                                                if (confirm) {
                                                    usuario.cambiarEmpresa(empresa);                                                    
                                                    System.out.println("Empresa cambiada");
                                                } else {
                                                    System.out.println("La confimacion no fue exitosa");
                                                }
                                        
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
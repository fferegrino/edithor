package org.edithor.servidor;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Controlador del servidor
 *
 * @author Feregrino Bolaños Antonio
 */
public final class HiloServidor extends Thread {

    Socket socketCliente1 = null;
    Socket socketCliente2 = null;
    DataInputStream entrada = null;
    DataOutputStream salida = null;
    DataOutputStream salida2 = null;
    public static ArrayList<HiloServidor> listaClientes = new ArrayList<>();
    String nameUser;
    PanelServidor serv;

    public HiloServidor(Socket scliente, Socket scliente2, PanelServidor serv) {
        socketCliente1 = scliente;
        socketCliente2 = scliente2;
        this.serv = serv;
        nameUser = "";
        boolean add;
        listaClientes.add(this);

        serv.muestraEnLog("Cliente añadido: " + this.getNameUser() + " en " + this);

    }

    /**
     * Obtiene el nombre del cliente al que le pertenece el hilo
     *
     * @return
     */
    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String name) {
        nameUser = name;
    }

    @Override
    public void run() {
        serv.muestraEnLog("Servidor preparado.");

        try {
            entrada = new DataInputStream(socketCliente1.getInputStream());
            salida = new DataOutputStream(socketCliente1.getOutputStream());
            salida2 = new DataOutputStream(socketCliente2.getOutputStream());
            this.setNameUser(entrada.readUTF());
            enviaUserActivos();
            enviaArchivo();
        } catch (IOException e) {
            //e.printStackTrace();    
        }


        int opcion = 0, numUsers = 0;
        String amigo = "", mencli = "";

        while (true) {
            try {
                opcion = entrada.readInt();
                switch (opcion) {
                    case 1://envio de mensage a todos
                        mencli = entrada.readUTF();
                        enviaMensaje(mencli);
                        break;
                    case 2://envio de lista de activos
                        numUsers = listaClientes.size();
                        salida.writeInt(numUsers);
                        for (int i = 0; i < numUsers; i++) {
                            salida.writeUTF(listaClientes.get(i).nameUser);
                        }
                        break;
                    case 3: // envia mensage a uno solo
                        amigo = entrada.readUTF();//captura nombre de amigo
                        mencli = entrada.readUTF();//mensage enviado
                        enviaMensaje(amigo, mencli);
                        break;
                    case 4:
                        int pos = entrada.readInt();
                        String bn = entrada.readUTF();
                        escribeTexto(pos, bn);
                        break;
                    case 5:
                        int aux = entrada.readInt();
                        int lon1 = entrada.readInt();
                        borradoBackspace(aux, lon1);
                        break;
                    case 6:
                        int aux1 = entrada.readInt();
                        int lon2 = entrada.readInt();
                        borradoSuprimir(aux1, lon2);
                        break;
                }
            } catch (IOException e) {
                //System.out.println("El cliente termino la conexion");
                break;
            }
        }
        actualizaUsuario(this);
        listaClientes.remove(this);
        try {
            socketCliente1.close();
            serv.muestraEnLog("Se desconectó " + this.getNameUser() + " en " + this);
        } catch (Exception et) {
            serv.muestraEnLog("No se puede cerrar el socket");
        }
    }

    /**
     * Cuando un cliente nuevo es añadido, envía el nombre a los demás
     *
     * @param usuarioNuevo
     */
    public void actualizaUsuario(HiloServidor usuarioNuevo) {
        HiloServidor user;
        for (int i = 0; i < listaClientes.size(); i++) {
            try {
                user = listaClientes.get(i);
                user.salida2.writeInt(4);
                user.salida2.writeUTF(usuarioNuevo.getNameUser());
            } catch (IOException e) {

                System.out.println("ERROR:" + e.getMessage());
            }
        }
    }

    /**
     * Propaga el nuevo texto a los demás clientes
     *
     * @param posicion
     * @param texto
     */
    public void escribeTexto(int posicion, String texto) {
        HiloServidor user = null;
        serv.editarServer(posicion, texto);
        for (int i = 0; i < listaClientes.size(); i++) {

            try {
                user = listaClientes.get(i);
                if (user == this) {
                    //El cliente es el que envía, no hay necesidad de enviarle los cambios a él 
                    continue;
                }
                user.salida2.writeInt(5);//opcion de mensage 
                user.salida2.writeInt(posicion);
                user.salida2.writeUTF(texto);
            } catch (IOException e) {
                System.out.println("ERROR:" + e.getMessage());
            }
        }
    }

    /**
     * Cuando un cliente ingresa este método es el encargado de enviarle el
     * archivo que se está editando
     */
    public void enviaArchivo() {
        try {
            this.salida2.writeInt(99);
            this.salida2.writeUTF(serv.recuperaArchivoCompleto());
        } catch (IOException e) {
        }
    }

    /**
     * Método para propagar un borrado con backspace a los demás clientes
     *
     * @param posicionInicial
     * @param longitud
     */
    public void borradoBackspace(int posicionInicial, int longitud) {
        HiloServidor user = null;
        serv.borrarServer(posicionInicial, longitud);
        for (int i = 0; i < listaClientes.size(); i++) {
            try {
                user = listaClientes.get(i);
                if (user == this) {
                    //El cliente es el que envía, no hay necesidad de enviarle los cambios a él
                    continue;
                }
                user.salida2.writeInt(6);//opcion de mensage 
                user.salida2.writeInt(posicionInicial);
                user.salida2.writeInt(longitud);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (StringIndexOutOfBoundsException ex) {

                System.out.println("ERROR:" + ex.getMessage());
            }
        }
    }

    /**
     * Método para propagar un borrado con suprimir a los demás clientes
     *
     * @param posicionInicial
     * @param longitud
     */
    public void borradoSuprimir(int posicionInicial, int longitud) {
        HiloServidor user = null;
        serv.borrarSuprServer(posicionInicial, longitud);
        for (int i = 0; i < listaClientes.size(); i++) {
            //serv.mostrar("MENSAJE DEVUELTO:" + texto);
            try {
                user = listaClientes.get(i);
                if (user == this) {
                    //El cliente es el que envía, no hay necesidad de enviarle los cambios a él 
                    continue;
                }
                user.salida2.writeInt(7);//opcion de mensage 
                user.salida2.writeInt(posicionInicial);
                user.salida2.writeInt(longitud);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Envía un mensaje al chat público
     *
     * @param mensaje
     */
    public void enviaMensaje(String mensaje) {
        HiloServidor user = null;
        for (int i = 0; i < listaClientes.size(); i++) {
            //
            try {
                user = listaClientes.get(i);
                user.salida2.writeInt(1);//opcion de mensage 
                user.salida2.writeUTF("" + this.getNameUser() + " dice: " + mensaje);
            } catch (IOException e) {

                System.out.println("ERROR:" + e.getMessage());
            }
        }
    }

    public void enviaUserActivos() {
        HiloServidor user = null;
        for (int i = 0; i < listaClientes.size(); i++) {
            try {
                user = listaClientes.get(i);
                if (user == this) {
                    continue;//ya se lo envie
                }
                user.salida2.writeInt(2);//opcion de agregar 
                user.salida2.writeUTF(this.getNameUser());
            } catch (IOException e) {
                System.out.println("ERROR:" + e.getMessage());
            }
        }
    }

    /**
     * Envía mensaje de manera privada
     * @param amigo
     * @param mencli 
     */
    private void enviaMensaje(String amigo, String mencli) {
        HiloServidor user = null;
        for (int i = 0; i < listaClientes.size(); i++) {
            try {
                user = listaClientes.get(i);
                if (user.nameUser.equals(amigo)) {
                    user.salida2.writeInt(3);//opcion de mensage amigo   
                    user.salida2.writeUTF(this.getNameUser());
                    user.salida2.writeUTF("" + this.getNameUser() + " dice: " + mencli);
                }
            } catch (IOException e) {
                System.out.println("ERROR:" + e.getMessage());
            }
        }
    }
}
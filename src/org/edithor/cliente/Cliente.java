package org.edithor.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;

/**
 *
 * @author Antonio Feregrino
 */
public class Cliente {

    DataInputStream entrada;
    DataInputStream entrada2;
    DataOutputStream salida;
    VentanaCliente ventanaC;
    VentanaPrivada ventanaP;
    Socket sComunication1;
    Socket sComunication2;
    String ipHost;
    ArrayList<String> usuarios;
    String nombreCliente;

    public Cliente(VentanaCliente ventanaCliente, String username) {
        nombreCliente = username;
        this.ventanaC = ventanaCliente;
    }

    /**
     * Conectar con el servidor remoto
     *
     * @param ipHost
     * @param puerto
     */
    public void conectar(String ipHost, int puerto1, int puerto2)
            throws IOException {
        sComunication1 = new Socket(ipHost, puerto1);
        sComunication2 = new Socket(ipHost, puerto2);
        entrada = new DataInputStream(sComunication1.getInputStream());
        entrada2 = new DataInputStream(sComunication2.getInputStream());
        salida = new DataOutputStream(sComunication1.getOutputStream());
        salida.writeUTF(nombreCliente);
        HiloCliente hiloCliente = new HiloCliente(entrada, this);
        usuarios = this.recuperaUsuarios();
        hiloCliente.start();
    }

    /**
     * Para enviar un mensaje <b>Método en red</b>
     *
     * @param mensaje
     */
    public void enviaMensaje(String mensaje) {
        try {
            salida.writeInt(1);
            salida.writeUTF(mensaje);
        } catch (IOException ex) {
        }
    }

    /**
     * Solicita al servidor la lista de usuarios activos <b>Método en red</b>
     *
     * @return
     */
    public ArrayList<String> recuperaUsuarios() {
        ArrayList<String> users = new ArrayList<>();
        try {
            salida.writeInt(2);
            int numUsers = entrada.readInt();
            for (int i = 0; i < numUsers; i++) {
                users.add(entrada.readUTF());
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    /**
     * Para enviar un mensaje privado <b>Método en red</b>
     *
     * @param amigo
     * @param mensaje
     */
    public void enviaMensaje(String amigo, String mensaje) {
        try {
            salida.writeInt(3);//opcion de mensage a amigo
            salida.writeUTF(amigo);
            salida.writeUTF(mensaje);
        } catch (IOException e) {
            System.out.println("error...." + e);
        }
    }

    /**
     * Escribe en el area de texto <b>Método en red</b>
     *
     * @param longitud
     */
    public void escritura(int longitud) {
        try {
            salida.writeInt(4);
            int pos = ventanaC.getCaretPositionEditor();
            salida.writeInt(pos);
            String c;
            c = ventanaC.getTextoEditar().substring(pos, pos + longitud);
            salida.writeUTF(c);
        } catch (IOException e) {
            System.out.println("error...." + e);
        }
    }

    /**
     * <p>Método para borrado backspace</p> <p><b>Método en red</b></p>
     *
     * @param longitud
     */
    public void borradoBackspace(int longitud) {
        try {
            salida.writeInt(5);
            int pos = ventanaC.getCaretPositionEditor();
            salida.writeInt(pos);
            salida.writeInt(longitud);
        } catch (IOException e) {
            System.out.println("error...." + e);
        }
    }

    /**
     * <p>Borrado al presionar el botón 'supr'</p> <p><b>Método en red</b></p>
     *
     * @param longitud
     */
    public void borradoSuprimir(int longitud) {
        try {
            salida.writeInt(6);
            int pos = ventanaC.getCaretPositionEditor();
            salida.writeInt(pos);
            salida.writeInt(longitud);
        } catch (IOException e) {
            System.out.println("error...." + e);
        }
    }

    /**
     * Agrega el texto en la posición indicada <p><b>Método en local</b></p>
     *
     * @param posicion
     * @param textoEdit
     * @return
     */
    public boolean agregarTexto(int posicion, String textoEdit) {
        boolean editado = false;
        String texto = ventanaC.getTextoEditar();
        int pos = ventanaC.getDotAreaEditor();
        //System.out.println("Trato de escribir " + cambio + " entre 0;" + posicion +" y " + (posicion+cambio.length()) +"; el tamaño del texto es "+ f.length());
        if (texto.length() > posicion && posicion < 0) {
            editado = false;
        } else {
            ventanaC.setTextoEditar(texto.substring(0, posicion) + textoEdit + texto.substring(posicion));
            if (pos < posicion) {
                ventanaC.setDotAreaEditor(pos);
            } else {
                ventanaC.setDotAreaEditor(pos + textoEdit.length());
            }
            editado = true;
        }
        return editado;
    }

    /**
     * Para reiniciar el editor
     *
     * @param nuevoTexto El texto que queremos poner en la * *
     * ventana, <code>null</code> si no deseamos poner nada
     */
    public void reinicioEdicion(String nuevoTexto) {
        ventanaC.setTextoEditar("");
        if (nuevoTexto != null) {
            agregarTexto(0, nuevoTexto);
        }
    }

    /**
     * Borrado con 'backspace' <p><b>Método en local</b></p>
     *
     * @param posicion
     * @param longitud
     * @return
     */
    public boolean borradoBackspace(int posicion, int longitud) {
        boolean borrado = true;

        String texto = ventanaC.getTextoEditar();
        int pos = ventanaC.getDotAreaEditor();
        if (texto.length() > posicion && posicion < longitud) {
            borrado = false;
        } else {
            ventanaC.setTextoEditar(texto.substring(0, posicion - longitud) + texto.substring(posicion));

            if (pos < posicion) {
                ventanaC.setDotAreaEditor(pos);
            } else {
                ventanaC.setDotAreaEditor(pos - longitud);
            }
            borrado = true;
        }
        return borrado;
    }

    /**
     * <p>Borrado al presionar el botón 'supr'</p> <p><b>Método en local</b></p>
     *
     * @param posicion
     * @param longitud
     * @return
     */
    public boolean borradoSuprimir(int posicion, int longitud) {
        boolean borrado = false;

        String texto = ventanaC.getTextoEditar();
        int posicionCaret = ventanaC.getCaretPositionEditor();

        if ((texto.length() - longitud) > posicion
                && posicion < 0) {
            borrado = false;
        } else {
            String nuevoTexto = texto.substring(0, posicion)
                    + texto.substring(posicion + longitud);

            ventanaC.setTextoEditar(nuevoTexto);
            if (posicionCaret < posicion) {
                ventanaC.setDotAreaEditor(posicionCaret);
            } else {
                ventanaC.setDotAreaEditor(posicionCaret - longitud);
            }
        }
        return borrado;
    }

    /**
     * Agrega un usuario al panel de usuarios activos
     *
     * @param usuarioNuevo
     */
    public void agregaUsuario(String usuarioNuevo) {
        usuarios.add(usuarioNuevo);
        ponerDatosActivo();
    }

    /**
     * Reueve un usuario del panel de usuarios activos
     *
     * @param usuario
     */
    public void retiraUsuario(String usuario) {
        usuarios.remove(usuario);
        ponerDatosActivo();
    }

    /**
     * Muestra la lista de usuarios activos
     */
    public void ponerUsuariosActivos() {
        ponerDatosActivo();
    }

    public void ponerDatosActivo() {
        ventanaC.getLlistaUsuarios().setModel(new AbstractListModel() {
            @Override
            public int getSize() {
                return usuarios.size();
            }

            @Override
            public Object getElementAt(int index) {
                return usuarios.get(index);
            }
        });
    }

    /**
     * Agrega un mensaje al area de chat
     *
     * @param mensaje
     */
    public void agregaMensajeAreaChat(String mensaje) {
        ventanaC.agregaMensajeChat(mensaje);
    }

    /**
     * Obtiene el nombre del cliente
     *
     * @return
     */
    public String getNombreCliente() {
        return this.nombreCliente;
    }

    /**
     * Inicializa el chat privado
     *
     * @param amigo
     * @param mensaje
     */
    public void mensajePrivado(String amigo, String mensaje) {
        if (ventanaP == null) {
            ventanaP = new VentanaPrivada(this);
        }
        ventanaP.setAmigo(amigo);
        ventanaP.appendMessage(mensaje);
        ventanaP.setVisible(true);
    }

    public void setClientControl(boolean control) {
        ventanaC.setControl(control);
    }
}

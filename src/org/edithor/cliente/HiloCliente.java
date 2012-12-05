package org.edithor.cliente;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio Feregrino
 */
public class HiloCliente extends Thread {

    DataInputStream entrada;
    ArrayList<String> usuarios;
    Cliente cliente;
    boolean conectado;

    @Override
    public void run() {
        String mensaje;
        String usuario;
        int opcion;
        int posicion;
        while (true) {
            try {
                opcion = entrada.readInt();
                cliente.setClientControl(false);
                switch (opcion) {
                    case 1:
                        // Mensaje grupal recibido
                        mensaje = entrada.readUTF();
                        cliente.agregaMensajeAreaChat(mensaje);
                        break;
                    case 2:
                        // Se agrega un usuario
                        usuario = entrada.readUTF();
                        cliente.agregaUsuario(usuario);
                        usuarios.add(usuario);
                        break;
                    case 3:
                        // Mensaje de amigo
                        usuario = entrada.readUTF();
                        mensaje = entrada.readUTF();
                        cliente.mensajePrivado(usuario, mensaje);
                        break;
                    case 4:
                        // Se retira un usuario
                        usuario = entrada.readUTF();
                        cliente.retiraUsuario(usuario);
                        break;
                    case 5:
                        // Edición de texto
                        posicion = entrada.readInt();
                        mensaje = entrada.readUTF();
                        cliente.agregarTexto(posicion, mensaje);
                        break;
                    case 6:
                        // Borrado con backspace
                        posicion = entrada.readInt();
                        int long0 = entrada.readInt();
                        cliente.borradoBackspace(posicion, long0);
                        break;
                    case 7:
                        // Borrado con suprimir
                        posicion = entrada.readInt();
                        int long1 = entrada.readInt();
                        cliente.borradoSuprimir(posicion, long1);
                        break;
                    case 99:
                        // Reinicio del editorareaEditor.setText("");
                        mensaje = entrada.readUTF();
                        cliente.reinicioEdicion(mensaje);
                        break;
                }
                cliente.setClientControl(true);
            } catch (IOException ex) {
                break;
            }
        }
    }

    public HiloCliente(DataInputStream entrada, Cliente cliente) {
        this.entrada = entrada;
        this.cliente = cliente;
        usuarios = new ArrayList<>();
        conectado = true;
    }
}

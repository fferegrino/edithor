package org.edithor.servidor;

/**
 *
 * @author Feregrino Bolaños Antonio
 */
import java.io.*;
import java.net.*;

public class PanelServidor {

    FrameServidor frameServer;

    public PanelServidor() {
        frameServer = new FrameServidor();
        frameServer.setVisible(true);

    }
    
    
    public static void main(String []args) throws IOException {
        PanelServidor ser = new PanelServidor();
        ser.runServer();
    }

    /**
     * Muestra un mensaje en la consola del servidor
     *
     * @param mensaje El mensaje a mostrar
     */
    public void muestraEnLog(String mensaje) {
        frameServer.getTextServerLog().append(mensaje + "\n");
    }

    public boolean editarServer(int posicion, String cambio) {
        String f = frameServer.getTextArchivo().getText();
       // muestraEnLog("Trato de escribir " + cambio + " entre 0;" + posicion +" y " + (posicion+cambio.length()) +"; el tamaño del texto es "+ f.length());
       if(f.length() >  posicion && posicion > 0) {
            return false;
        }
//       System.out.println("Editar: Trato de poner el caret en "+ pos +" L: " + f.length());
        frameServer.getTextArchivo().setText(f.substring(0, posicion) + cambio + f.substring(posicion));
        return true;
    }

    public boolean borrarServer(int posicion, int longitud) {
        String f =  frameServer.getTextArchivo().getText();
        if(f.length() >  posicion && posicion < longitud)
        {
           //System.out.println("Error en borrar bcsp\nTraté de borrar entre 0;"+(posicion-longitud)+" y "+posicion);
           return false;
        }
//       System.out.println("Borrar: Trato de poner el caret en "+ pos +" L: " + f.length());
         frameServer.getTextArchivo().setText(f.substring(0, posicion-longitud) + f.substring(posicion));
        return true;
    }

    public boolean borrarSuprServer(int posicion, int longitud) {
        String f =  frameServer.getTextArchivo().getText();
       if(f.length()-longitud > posicion && posicion < 0)
       {
           //System.out.println("Error en borrar supr\nTraté de borrar entre 0;"+posicion+" y "+(posicion+longitud));
           return false;
       }
       System.out.println("Borrsupr: Trato de poner el caret en "+  posicion +" L: " + f.length());
        frameServer.getTextArchivo().setText(f.substring(0, posicion) + f.substring(posicion+longitud));
        return true;
    }

    public void runServer() {
        ServerSocket serv = null;//para comunicacion
        ServerSocket serv2 = null;//para enviar mensajes
        boolean listening = true;
        try {
            serv = new ServerSocket(8081);
            serv2 = new ServerSocket(8082);
            muestraEnLog("Servidor activo :");
            while (listening) {
                Socket sock = null, sock2 = null;
                try {
                    muestraEnLog("Esperando Usuarios");
                    sock = serv.accept();
                    sock2 = serv2.accept();
                } catch (IOException e) {
                    muestraEnLog("Error aceptando cliente: " + serv + ", " + e.getMessage());
                    continue;
                }
                HiloServidor user = new HiloServidor(sock, sock2, this);
                user.start();
            }

        } catch (IOException e) {
            muestraEnLog("error :" + e);
        }
    }


    /**
     * Obtiene el archivo que se está editando en ese momento.
     *
     * @return El texto del archivo completo
     */
    public String recuperaArchivoCompleto() {
        return (frameServer.getTextArchivo().getText()==null)?"":frameServer.getTextArchivo().getText();
    }
}

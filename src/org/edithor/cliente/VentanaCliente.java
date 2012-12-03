package org.edithor.cliente;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Antonio
 */
public class VentanaCliente extends javax.swing.JFrame {

    boolean recienAbierto;
    boolean archivoModificado;
    File archivoActual;
    java.awt.Font segoeUI = new java.awt.Font("Segoe UI", 0, 14);
    Cliente cliente;
    private boolean control;

    /**
     * Creates new form VentanaCliente
     */
    public VentanaCliente() {
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                resizeEvent();
            }
        });
        control = true;
        initComponents();
        setDocumentListeners();
        resizeEvent();

    }
    
    
    public static void main(String[] args) {
        new VentanaCliente().setVisible(true);
    }

    private void setDocumentListeners() {
        txtAreaEditor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (control) {
                    try {
                        if (cliente != null) {
                            cliente.escritura(e.getLength());
                        }
                    } catch (NullPointerException ex) {
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (control) {
                    try {
                        if (cliente != null) {
                            if (txtAreaEditor.getCaretPosition() == e.getOffset()) {

                                cliente.borradoSuprimir(e.getLength());
                            } else {
                                cliente.borradoBackspace(e.getLength());
                            }
                        }
                    } catch (NullPointerException ex) {
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    /**
     * Método para reacomodar los jSplitPanes de una manera comoda
     */
    private void resizeEvent() {
        jSplitPane1.setDividerLocation((getSize().width / 3) * 2);
        jSplitPane1.setOneTouchExpandable(true);
        jSplitPane1.setEnabled(false);

        jSplitPane2.setDividerLocation((getSize().height / 5) * 3);
        jSplitPane2.setEnabled(false);

        jSplitPane3.setDividerLocation((jSplitPane3.getParent().getSize().width / 2));
        jSplitPane3.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        panelInformacion = new javax.swing.JPanel();
        labelEditando = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane1.setDividerLocation((getSize().width / 3) * 2);
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaEditor = new javax.swing.JTextArea();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaUsuarios = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaChat = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuAbrirArchivo = new javax.swing.JMenuItem();
        menuGuardarArchivo = new javax.swing.JMenuItem();
        menuGuardarComoArchivo = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        menuInicioConexion = new javax.swing.JMenuItem();
        menuDesconectar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuAcercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        labelEditando.setFont(segoeUI);
        labelEditando.setText("jLabel1");

        javax.swing.GroupLayout panelInformacionLayout = new javax.swing.GroupLayout(panelInformacion);
        panelInformacion.setLayout(panelInformacionLayout);
        panelInformacionLayout.setHorizontalGroup(
            panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionLayout.createSequentialGroup()
                .addComponent(labelEditando)
                .addGap(0, 544, Short.MAX_VALUE))
        );
        panelInformacionLayout.setVerticalGroup(
            panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionLayout.createSequentialGroup()
                .addComponent(labelEditando)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jToolBar1.add(panelInformacion);

        txtAreaEditor.setColumns(20);
        txtAreaEditor.setRows(5);
        jScrollPane1.setViewportView(txtAreaEditor);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );

        jSplitPane3.setLeftComponent(jPanel2);

        listaUsuarios.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listaUsuarios);

        jLabel2.setFont(segoeUI);
        jLabel2.setText("Usuarios conectados");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addComponent(jLabel2)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
        );

        jSplitPane3.setRightComponent(jPanel3);

        jSplitPane2.setBottomComponent(jSplitPane3);

        txtAreaChat.setColumns(20);
        txtAreaChat.setRows(5);
        jScrollPane2.setViewportView(txtAreaChat);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setLabelFor(txtAreaChat);
        jLabel1.setText("Chat");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
        );

        jSplitPane2.setTopComponent(jPanel1);

        jSplitPane1.setRightComponent(jSplitPane2);

        jMenu1.setText("Archivo");

        menuAbrirArchivo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuAbrirArchivo.setText("Abrir");
        menuAbrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbrirArchivoActionPerformed(evt);
            }
        });
        jMenu1.add(menuAbrirArchivo);

        menuGuardarArchivo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuGuardarArchivo.setText("Guardar");
        menuGuardarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuardarArchivoActionPerformed(evt);
            }
        });
        jMenu1.add(menuGuardarArchivo);

        menuGuardarComoArchivo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuGuardarComoArchivo.setText("Guardar como");
        menuGuardarComoArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuardarComoArchivoActionPerformed(evt);
            }
        });
        jMenu1.add(menuGuardarComoArchivo);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu4.setText("Conexion");

        menuInicioConexion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuInicioConexion.setText("Conectar");
        menuInicioConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInicioConexionActionPerformed(evt);
            }
        });
        jMenu4.add(menuInicioConexion);

        menuDesconectar.setText("Desconectar");
        menuDesconectar.setEnabled(false);
        jMenu4.add(menuDesconectar);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Ayuda");

        menuAcercaDe.setText("ediThor");
        menuAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAcercaDeActionPerformed(evt);
            }
        });
        jMenu3.add(menuAcercaDe);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jSplitPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuAbrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbrirArchivoActionPerformed
        abrirArchivoSeguro();
    }//GEN-LAST:event_menuAbrirArchivoActionPerformed

    private void menuGuardarComoArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuardarComoArchivoActionPerformed
        guardarArchivoComo();
    }//GEN-LAST:event_menuGuardarComoArchivoActionPerformed

    private void menuGuardarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuardarArchivoActionPerformed
        guardarArchivo();
    }//GEN-LAST:event_menuGuardarArchivoActionPerformed

    private void menuInicioConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInicioConexionActionPerformed
        iniciarConexion();
    }//GEN-LAST:event_menuInicioConexionActionPerformed

    private void menuAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAcercaDeActionPerformed
        AboutEdithor acercaDe = new AboutEdithor(this);
        acercaDe.setVisible(true);
    }//GEN-LAST:event_menuAcercaDeActionPerformed

    private boolean iniciarConexion() {
        try {
            String username = "";
            do {
                username = JOptionPane.showInputDialog(this,
                        "¿Cual es tu nombre de usuario?",
                        JOptionPane.QUESTION_MESSAGE);
            } while (username.isEmpty());
            String host = "";
            do {
                host = JOptionPane.showInputDialog("IP del servidor", "localhost");
            } while (host.isEmpty());
            cliente = new Cliente(this, username);
            cliente.conectar(host, 8081, 8082);
            cliente.ponerUsuariosActivos();
            componentesConexion(true);
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    /**
     * Establecer si está conectado o no el cliente para establecer los
     * controles adecuados
     *
     * @param habilitados
     */
    public void componentesConexion(boolean habilitados) {
        menuInicioConexion.setEnabled(!habilitados);
        menuDesconectar.setEnabled(habilitados);
        for (Component v : jPanel1.getComponents()) {
            v.setEnabled(habilitados);
        }
        for (Component v : jPanel2.getComponents()) {
            v.setEnabled(habilitados);
        }
        for (Component v : jPanel3.getComponents()) {
            v.setEnabled(habilitados);
        }
    }

    /**
     * Función para saber si el archivo ha sido editado o no
     *
     * @return
     */
    public boolean archivoEditado() {
        return archivoModificado || !txtAreaEditor.getText().equals("");
    }

    /**
     * Abre un archivo, verificando que se guarde el que está visualizando
     * actualmente
     *
     * @return
     */
    private boolean abrirArchivoSeguro() {
        boolean abierto;
        if (archivoEditado()) {
            int decision = JOptionPane.showConfirmDialog(txtAreaEditor, "¿Desea guardar el archivo?", "Aviso",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            switch (decision) {
                case JOptionPane.YES_OPTION:
                    // Si desea guardar el archivo
                    if (archivoActual != null) {
                        // Si el archivo es nuevo lo mandamos a la pantalla
                        // de guardar como
                        if (!guardarArchivoComo()) {
                            // Si eligió no guardar el archivo lo regresamos
                            return false;
                        }
                    } else {
                        // Si ya se tiene historial del archivo
                        if (!guardarArchivo()) {
                            return false;
                        }
                    }
                    txtAreaEditor.setText("");
                    break;
                case JOptionPane.NO_OPTION:
                    // Si no desea guardar el archivo que está editando
                    txtAreaEditor.setText("");
                    break;
                case JOptionPane.CANCEL_OPTION:
                    return false;

            }
            abierto = abrirArchivo();
        } else {
            abierto = abrirArchivo();
        }
        return abierto;
    }

    /**
     * Método para abrir un archivo
     *
     * @return
     */
    private boolean abrirArchivo() {
        boolean abierto;
        JFileChooser selectorDeArchivo = new JFileChooser();
        FileNameExtensionFilter filtroDeArchivos = new FileNameExtensionFilter("Texto plano \".txt\" ", "txt");
        selectorDeArchivo.setFileFilter(filtroDeArchivos);
        int seleccion = selectorDeArchivo.showOpenDialog(txtAreaEditor);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            BufferedReader reader = null;
            try {
                archivoActual = selectorDeArchivo.getSelectedFile();
                reader = new BufferedReader(new FileReader(archivoActual));
                labelEditando.setText("Editando: " + archivoActual.getName());
                String lineaArchivo = reader.readLine();
                while (lineaArchivo != null) {
                    txtAreaEditor.append(lineaArchivo);
                    txtAreaEditor.append(System.getProperty("line.separator"));
                    lineaArchivo = reader.readLine();
                }
            } catch (FileNotFoundException exception) {
            } catch (IOException exception) {
            } finally {
                try {
                    reader.close();
                } catch (IOException ex) {
                }
            }
            abierto = true;
        } else {
            abierto = false;
        }
        recienAbierto = true;
        archivoModificado = false;
        return abierto;
    }

    /**
     * Guarda un archivo del que ya se tenga informacion
     *
     * @return
     */
    private boolean guardarArchivo() {
        boolean guardado;
        PrintWriter writer = null;
        if (archivoActual != null) {
            try {
                writer = new PrintWriter(archivoActual);
                writer.print(txtAreaEditor.getText());
                guardado = true;
            } catch (FileNotFoundException ex) {
                guardado = false;
            } finally {
                writer.close();
            }
        } else {
            guardado = guardarArchivoComo();
        }
        return guardado;
    }

    /**
     * Para guardar el archivo con un nombre especifico
     *
     * @return
     */
    private boolean guardarArchivoComo() {
        boolean guardado;
        JFileChooser selectorDeArchivo = new JFileChooser();
        FileNameExtensionFilter filtroDeArchivos = new FileNameExtensionFilter("Texto plano \".txt\" ", "txt");
        selectorDeArchivo.setFileFilter(filtroDeArchivos);
        int seleccion = selectorDeArchivo.showSaveDialog(txtAreaEditor);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            archivoActual = selectorDeArchivo.getSelectedFile();
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(archivoActual);
                writer.print(txtAreaEditor.getText());
            } catch (FileNotFoundException exception) {
            } finally {
                writer.close();
            }
            labelEditando.setText("Editando: " + archivoActual.getName());
            guardado = true;
        } else {
            guardado = false;
        }

        return guardado;
    }

    /**
     * Método para crear un nuevo archivo
     */
    public void nuevoArchivo() {

        if (archivoEditado()) {
            int decision = JOptionPane.showConfirmDialog(txtAreaEditor, "¿Desea guardar el archivo que está editando?", "Aviso",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            switch (decision) {
                case JOptionPane.YES_OPTION:
                    if (archivoActual != null) {
                        if (!guardarArchivoComo()) {
                            return;
                        }
                    } else {
                        if (!guardarArchivo()) {
                            return;
                        }
                    }
                    txtAreaEditor.setText("");
                    labelEditando.setText("Nuevo archivo");
                    break;
            }
        } else {
            txtAreaEditor.setText("");
            labelEditando.setText("Nuevo archivo");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelEditando;
    private javax.swing.JList listaUsuarios;
    private javax.swing.JMenuItem menuAbrirArchivo;
    private javax.swing.JMenuItem menuAcercaDe;
    private javax.swing.JMenuItem menuDesconectar;
    private javax.swing.JMenuItem menuGuardarArchivo;
    private javax.swing.JMenuItem menuGuardarComoArchivo;
    private javax.swing.JMenuItem menuInicioConexion;
    private javax.swing.JPanel panelInformacion;
    private javax.swing.JTextArea txtAreaChat;
    private javax.swing.JTextArea txtAreaEditor;
    // End of variables declaration//GEN-END:variables

    public JTextArea getTxtAreaEditor() {
        return txtAreaEditor;
    }

    /**
     * Obtiene la posición del caret
     *
     * @return
     */
    public int getCaretPositionEditor() {
        return txtAreaEditor.getCaretPosition();
    }

    /**
     * Obtiene el texto del editor
     *
     * @return
     */
    public String getTextoEditar() {
        return txtAreaEditor.getText();
    }

    /**
     * Establece el texto del editor
     *
     * @param texto
     */
    public void setTextoEditar(String texto) {
        txtAreaEditor.setText(texto);
    }

    /**
     * Establece el dot del editor
     *
     * @param dot
     */
    public void setDotAreaEditor(int dot) {
        txtAreaEditor.getCaret().setDot(dot);
    }

    public int getDotAreaEditor() {
        return txtAreaEditor.getCaret().getDot();
    }

    /**
     * Agrega un mensaje al area de chat
     *
     * @param mensaje
     */
    public void agregaMensajeChat(String mensaje) {
        txtAreaChat.append(mensaje + "\n");
    }

    /**
     * Set control
     *
     * @param control
     */
    public void setControl(boolean control) {
        this.control = control;
    }

    /**
     *
     * @return
     */
    public JList getLlistaUsuarios() {
        return this.listaUsuarios;
    }
}

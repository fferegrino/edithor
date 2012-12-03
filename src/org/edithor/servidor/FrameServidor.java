package org.edithor.servidor;

import javax.swing.JTextArea;

/**
 *
 * @author Antonio
 */
public class FrameServidor extends java.awt.Frame {

    /**
     * Creates new form FrameServidor
     */
    public FrameServidor() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textServerLog = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArchivo = new javax.swing.JTextArea();

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(300, 400));

        jPanel1.setLayout(new java.awt.BorderLayout());

        textServerLog.setColumns(20);
        textServerLog.setRows(5);
        jScrollPane1.setViewportView(textServerLog);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("ServerLog", jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        textArchivo.setColumns(20);
        textArchivo.setRows(5);
        jScrollPane2.setViewportView(textArchivo);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Archivo", jPanel2);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        jTabbedPane1.getAccessibleContext().setAccessibleName("s");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrameServidor().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea textArchivo;
    private javax.swing.JTextArea textServerLog;
    // End of variables declaration//GEN-END:variables

    public JTextArea getTextServerLog() {
        return textServerLog;
    }

    public JTextArea getTextArchivo() {
        return textArchivo;
    }
}

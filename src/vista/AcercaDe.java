/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author Brian54
 */
public class AcercaDe extends javax.swing.JFrame {

    /**
     * Creates new form AcercaDe
     */
    public AcercaDe() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnFb = new javax.swing.JButton();
        btnGit = new javax.swing.JButton();
        lblCerrar = new javax.swing.JLabel();
        lblMinimiza = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFb.setBackground(new java.awt.Color(51, 51, 255));
        btnFb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fb.png"))); // NOI18N
        btnFb.setBorder(null);
        jPanel1.add(btnFb, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 40, -1));

        btnGit.setBackground(new java.awt.Color(153, 204, 255));
        btnGit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/github.png"))); // NOI18N
        btnGit.setBorder(null);
        jPanel1.add(btnGit, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 40, -1));

        lblCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar.png"))); // NOI18N
        jPanel1.add(lblCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, -1));

        lblMinimiza.setBackground(new java.awt.Color(102, 204, 255));
        lblMinimiza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minimiza.png"))); // NOI18N
        jPanel1.add(lblMinimiza, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/AcercaDe.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnFb;
    public javax.swing.JButton btnGit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lblCerrar;
    public javax.swing.JLabel lblMinimiza;
    // End of variables declaration//GEN-END:variables
}


package controlador;

import static java.awt.Frame.ICONIFIED;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.WindowConstants;
import vista.AcercaDe;

/**
 *
 * @author Brian54
 */
public class CtrlAcercaDe implements ActionListener,MouseListener {
    AcercaDe FrmAcercaDe = new AcercaDe();
    
    public CtrlAcercaDe(AcercaDe FrmAcercaDe){
        this.FrmAcercaDe = FrmAcercaDe;
        this.FrmAcercaDe.lblMinimiza.addMouseListener(this);
        this.FrmAcercaDe.lblCerrar.addMouseListener(this);
        this.FrmAcercaDe.btnFb.addActionListener(this);
        this.FrmAcercaDe.btnGit.addActionListener(this);
    }
    
    public void mostrar(){
        FrmAcercaDe.setVisible(true);
        FrmAcercaDe.setResizable(false);
        FrmAcercaDe.setLocationRelativeTo(null);
        FrmAcercaDe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == FrmAcercaDe.btnFb) {
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    try {
                        java.net.URI url = new java.net.URI("https://www.facebook.com/brian.murillosalas.1/");
                        desktop.browse(url);
                    } catch (URISyntaxException | IOException ev) {
                        System.out.println(ev.toString());
                    }
                }
            }
        }
        if (e.getSource() == FrmAcercaDe.btnGit) {
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    try {
                        java.net.URI url = new java.net.URI("https://github.com/Brian-54");
                        desktop.browse(url);
                    } catch (URISyntaxException | IOException ev) {
                        System.out.println(ev.toString());
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == FrmAcercaDe.lblCerrar){
            FrmAcercaDe.dispose();
        }
        if(e.getSource() == FrmAcercaDe.lblMinimiza){
            FrmAcercaDe.setExtendedState(ICONIFIED);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

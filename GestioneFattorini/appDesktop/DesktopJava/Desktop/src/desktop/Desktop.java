/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package desktop;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author inf.desderil1609
 */
public class Desktop extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        
            public void run(){new Desktop();}
        
        });
    }
    
    public Desktop() {creazioneFinestra();}
    
    public void creazioneFinestra(){
    
        setTitle("ApplicazioneDesktop");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        JButton btnPacchi = new JButton("Visualizza Tabella Pacchi");
        JButton btnFattorini = new JButton("Visualizza Tabella Fattorini");
               
        setVisible(true);
    }
    
}

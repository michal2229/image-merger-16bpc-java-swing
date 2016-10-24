/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OknoGlowne;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Michał Bokiniec
 */
public class OknoGlowne extends javax.swing.JFrame implements PropertyChangeListener {
    /**
     * Creates new form OknoGlowne
     */
    public OknoGlowne() {
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

        jFileChooser1 = new javax.swing.JFileChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        PrzyciskWybierzFolder = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jRadioButtonSrednia = new javax.swing.JRadioButton();
        jRadioButtonTylkoJasniejsze = new javax.swing.JRadioButton();
        jRadioButtonTylkoCiemniejsze = new javax.swing.JRadioButton();

        jFileChooser1.setAcceptAllFileFilterUsed(false);
        jFileChooser1.setApproveButtonText("Wybierz");
        jFileChooser1.setApproveButtonToolTipText("");
        jFileChooser1.setCurrentDirectory(new java.io.File("D:\\programy\\programowanie\\NetBeans IDE 8.0\\NetBeans 8.0\\Documents"));
        jFileChooser1.setDialogTitle("Wybierz folder");
        jFileChooser1.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        jFileChooser1.setMaximumSize(new java.awt.Dimension(1280, 720));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Uśredniacz obrazów");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1280, 640));
        setPreferredSize(new java.awt.Dimension(360, 145));
        setResizable(false);

        PrzyciskWybierzFolder.setText("Wybierz Folder");
        PrzyciskWybierzFolder.setToolTipText("Wybierz folder, z którego pliki graficzne mają być przetwarzane.");
        PrzyciskWybierzFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrzyciskWybierzFolderActionPerformed(evt);
            }
        });

        jProgressBar1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jProgressBar1.setToolTipText("");
        jProgressBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        buttonGroup1.add(jRadioButtonSrednia);
        jRadioButtonSrednia.setSelected(true);
        jRadioButtonSrednia.setText("średnia");
        jRadioButtonSrednia.setToolTipText("Generowanie pliku graficznego będącego średnią plików graficznych znalezionych w wybranym folderze.");

        buttonGroup1.add(jRadioButtonTylkoJasniejsze);
        jRadioButtonTylkoJasniejsze.setText("tylko jaśniejsze");
        jRadioButtonTylkoJasniejsze.setToolTipText("Generowanie pliku graficznego, którego piksele odpowiadają wartości maksymalnej odpowiednich pikseli znalezionych w wybranym folderze.");

        buttonGroup1.add(jRadioButtonTylkoCiemniejsze);
        jRadioButtonTylkoCiemniejsze.setText("tylko ciemniejsze");
        jRadioButtonTylkoCiemniejsze.setToolTipText("Generowanie pliku graficznego, którego piksele odpowiadają wartości minimalnej odpowiednich pikseli znalezionych w wybranym folderze.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonTylkoCiemniejsze)
                    .addComponent(jRadioButtonTylkoJasniejsze)
                    .addComponent(jRadioButtonSrednia)
                    .addComponent(PrzyciskWybierzFolder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PrzyciskWybierzFolder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonSrednia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonTylkoJasniejsze)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonTylkoCiemniejsze))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void PrzyciskWybierzFolderActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_PrzyciskWybierzFolderActionPerformed
    {//GEN-HEADEREND:event_PrzyciskWybierzFolderActionPerformed
        PrzyciskWybierzFolder.setEnabled(false);

        String directoryName = "";

        if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            List<String> results = new ArrayList<String>();
            File[] files = null;

            try {
                directoryName = jFileChooser1.getSelectedFile().getCanonicalPath();
            } catch (IOException ex) {
                Logger.getLogger(OknoGlowne.class.getName()).log(Level.SEVERE, null, ex);
            }

            files = new File(directoryName).listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (
                            (!
                                (name.toLowerCase().endsWith("final.tif")
                                || name.toLowerCase().endsWith("final.tiff")))
                        &&
                            (name.toLowerCase().endsWith("jpg")
                            || name.toLowerCase().endsWith("jpeg")
                            || name.toLowerCase().endsWith("png")
                            || name.toLowerCase().endsWith("bmp")))
                        return true;
                    else return false;                                      // skomentowane w celach testowych, pozniej odkomentowac
                }
            });

            for (File file : files) {                                       // dla kazdego pliku file w files
                if (file.isFile()) {
                    try {
                        System.out.println("Znaleziono plik: "+file.getCanonicalPath());
                        results.add(file.getCanonicalPath());
                    } catch (IOException ex) {
                        Logger.getLogger(OknoGlowne.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }


            try {
                if (jRadioButtonSrednia.isSelected()) {
                    Srednia sr = new Srednia(results, directoryName);
                    sr.addPropertyChangeListener(this);// tu odpalana jest funkcja
                    sr.execute();
                }
                if (jRadioButtonTylkoJasniejsze.isSelected()) {
                    TylkoJasniejsze tj = new TylkoJasniejsze(results, directoryName);
                    tj.addPropertyChangeListener(this);// tu odpalana jest funkcja
                    tj.execute();
                }
                if (jRadioButtonTylkoCiemniejsze.isSelected()) {
                    TylkoCiemniejsze tc = new TylkoCiemniejsze(results, directoryName);
                    tc.addPropertyChangeListener(this);// tu odpalana jest funkcja
                    tc.execute();
                }
                //new TylkoJasniejsze(results, directoryName);                    // tu odpalana jest funkcja
                //new TylkoCiemniejsze(results, directoryName);                   // tu odpalana jest funkcja
            } catch (IOException ex) {
                Logger.getLogger(OknoGlowne.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Brak poprawnych plikow (jpg/jpeg/png/bmp).");
            PrzyciskWybierzFolder.setEnabled(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_PrzyciskWybierzFolderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OknoGlowne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OknoGlowne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OknoGlowne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OknoGlowne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OknoGlowne().setVisible(true);
            }
        });
    } // wygenerowane automatycznie przez Swing Designer

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PrzyciskWybierzFolder;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JRadioButton jRadioButtonSrednia;
    private javax.swing.JRadioButton jRadioButtonTylkoCiemniejsze;
    private javax.swing.JRadioButton jRadioButtonTylkoJasniejsze;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt1) {
        if ("progress" == evt1.getPropertyName()) {
            int progress = (Integer) evt1.getNewValue();
            jProgressBar1.setValue(progress);
            jProgressBar1.setString("Wykonano " + progress + "% zadania..." );
            jProgressBar1.setStringPainted(true);
            if (progress>99) {
                PrzyciskWybierzFolder.setEnabled(true);
                jProgressBar1.setValue(100);
                jProgressBar1.setString("Zadanie wykonano." );
            }
        }
    } // tu reaguje na zmiane wartosci postepu, ustawiam pasek postepu
}

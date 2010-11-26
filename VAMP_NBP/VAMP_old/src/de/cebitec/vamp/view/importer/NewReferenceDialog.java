package de.cebitec.vamp.view.importer;

import java.awt.Component;
import de.cebitec.vamp.importer.JobManagerI;
import java.io.File;
import java.io.FileFilter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import de.cebitec.vamp.parsing.common.ParserI;
import de.cebitec.vamp.parsing.reference.ReferenceParserI;
import de.cebitec.vamp.parsing.reference.embl.biojava.BioJavaEmblParser;
import de.cebitec.vamp.parsing.reference.fasta.FastaReferenceParser;
import de.cebitec.vamp.parsing.reference.genbank.biojava.BioJavaGenBankParser;

/**
 *
 * @author ddoppmeier
 */
public class NewReferenceDialog extends javax.swing.JDialog {

    private static final long serialVersionUID = 8362375;
    private File refGenFile = null;
    private JobManagerI taskManager;
    private ReferenceParserI[] availableParsers = new ReferenceParserI[]{new BioJavaEmblParser(), new BioJavaGenBankParser(), new FastaReferenceParser()};
    private ReferenceParserI currentParser;

    /** Creates new form NewReferenceGenomeDialog */
    public NewReferenceDialog(java.awt.Frame parent, JobManagerI taskmanager) {
        super(parent, false);
        initComponents();
        this.taskManager = taskmanager;
       // setPreferences(refGenFile);
        currentParser = availableParsers[0];
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filetypeBox = new javax.swing.JComboBox(availableParsers);
        filetypeLabel = new javax.swing.JLabel();
        fileLabel = new javax.swing.JLabel();
        fileField = new javax.swing.JTextField();
        fileChooserButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        addRefGenButton = new javax.swing.JButton();
        descriptionLabel = new javax.swing.JLabel();
        descriptionField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add reference");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        filetypeBox.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                if(value instanceof ParserI){
                    return super.getListCellRendererComponent(list, ((ParserI) value).getParserName(), index, isSelected, cellHasFocus);
                } else {
                    return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                }
            }

        });
        filetypeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filetypeBoxActionPerformed(evt);
            }
        });

        filetypeLabel.setText("Filetype:");

        fileLabel.setText("File:");

        fileField.setEditable(false);
        fileField.setText("no file chosen");

        fileChooserButton.setText("Open");
        fileChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserButtonActionPerformed(evt);
            }
        });

        addRefGenButton.setText("OK");
        addRefGenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRefGenButtonActionPerformed(evt);
            }
        });

        descriptionLabel.setText("Description:");

        nameLabel.setText("Name:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(filetypeLabel)
                            .addComponent(fileLabel)
                            .addComponent(descriptionLabel)
                            .addComponent(nameLabel))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fileField, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                .addGap(22, 22, 22)
                                .addComponent(fileChooserButton))
                            .addComponent(filetypeBox, 0, 260, Short.MAX_VALUE)
                            .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(descriptionField, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
                    .addComponent(addRefGenButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filetypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filetypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileLabel)
                    .addComponent(fileChooserButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addRefGenButton)
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-374)/2, (screenSize.height-226)/2, 374, 226);
    }// </editor-fold>//GEN-END:initComponents

    private void addRefGenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRefGenButtonActionPerformed

        String description = descriptionField.getText();
        String name = nameField.getText();

        if (refGenFile == null || name.equals("") || description.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill out the complete form!", "Missing information", JOptionPane.ERROR_MESSAGE);
        } else {
            this.setVisible(false);
            taskManager.createRefGenTask(currentParser, refGenFile, description, name);
        }

    }//GEN-LAST:event_addRefGenButtonActionPerformed

    private void fileChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserButtonActionPerformed

        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter(currentParser.getInputFileDescription(), currentParser.getFileExtensions()));
        Preferences prefs2 = Preferences.userNodeForPackage(NewReferenceDialog.class);
        String path = prefs2.get("RefGenome.Filepath", null);
        if(path!=null){
        fc.setCurrentDirectory(new File(path));
        }
        int result = fc.showOpenDialog(this);

        File file = null;

        if (result == 0) {
            // file chosen
            file = fc.getSelectedFile();

            if (file.canRead()) {
                refGenFile = file;
                Preferences prefs = Preferences.userNodeForPackage(NewReferenceDialog.class);
                prefs.put("RefGenome.Filepath", refGenFile.getAbsolutePath());
                fileField.setText(refGenFile.getAbsolutePath());
                try {
                    prefs.flush();
                } catch (BackingStoreException ex) {
                    Logger.getLogger(NewReferenceDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.err.print("NewReferenceDialog couldnt read file");
            }
        }


    }//GEN-LAST:event_fileChooserButtonActionPerformed

    private void filetypeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filetypeBoxActionPerformed

        ReferenceParserI newparser = (ReferenceParserI) filetypeBox.getSelectedItem();
        if (currentParser != newparser) {
            currentParser = newparser;
            refGenFile = null;
            nameField.setText("");
            fileField.setText("");
            descriptionField.setText("");
        }

    }//GEN-LAST:event_filetypeBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRefGenButton;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JButton fileChooserButton;
    private javax.swing.JTextField fileField;
    private javax.swing.JLabel fileLabel;
    private javax.swing.JComboBox filetypeBox;
    private javax.swing.JLabel filetypeLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}
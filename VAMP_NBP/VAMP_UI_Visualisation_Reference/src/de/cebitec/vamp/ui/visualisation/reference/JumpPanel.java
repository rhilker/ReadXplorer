package de.cebitec.vamp.ui.visualisation.reference;

import de.cebitec.vamp.databackend.connector.ProjectConnector;
import de.cebitec.vamp.databackend.connector.ReferenceConnector;
import de.cebitec.vamp.databackend.dataObjects.PersistantAnnotation;
import de.cebitec.vamp.databackend.dataObjects.PersistantReference;
import de.cebitec.vamp.view.dialogMenus.JTextFieldPasteable;
import de.cebitec.vamp.view.dataVisualisation.BoundsInfoManager;
import de.cebitec.vamp.view.dataVisualisation.abstractViewer.AbstractViewer;
import de.cebitec.vamp.view.dataVisualisation.referenceViewer.IThumbnailView;
import de.cebitec.vamp.view.dataVisualisation.referenceViewer.ReferenceViewer;
import de.cebitec.vamp.view.dialogMenus.StandardMenuEvent;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;

/**
 *
 * @author ddoppmeier, rhilker
 */
public class JumpPanel extends javax.swing.JPanel implements LookupListener {

    private final static long serialVersionUID = 247246234;
    private int jumpPosition;
    private String searchPattern;
    private PersistantReference refGen;
    private ReferenceConnector refGenCon;
    private BoundsInfoManager boundsManager;
    private ReferenceViewer curRefViewer;
    private Lookup.Result<ReferenceViewer> res;
    private AbstractViewer viewer;

    public BoundsInfoManager getBoundsManager() {
        return boundsManager;
    }

    /** Creates new form JumpPanel */
    public JumpPanel() {
        this.initComponents();
        this.completeComponents();
        this.setMinimumSize(new Dimension(50, 50));
        this.setPreferredSize(new Dimension(288, 500));
        this.setSize(new Dimension(288, 500));
        jumpPosition = 1;
        filterTextfield.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFilter();

            }
        });        

        //Listener for TableSelect-Events
        annotationTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] selectedRows = annotationTable.getSelectedRows();
                if (selectedRows.length > 0) {
                    int correctedRow = annotationTable.convertRowIndexToModel(selectedRows[0]);
                    PersistantAnnotation annotation = (PersistantAnnotation) annotationTable.getModel().getValueAt(correctedRow, 0);
                    if (annotation.getStrand() == 1) {
                        boundsManager.navigatorBarUpdated(annotation.getStart());
                    } else {
                        boundsManager.navigatorBarUpdated(annotation.getStop());
                    }
                }
            }
        });

        annotationTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getButton() == MouseEvent.BUTTON3) || (e.isPopupTrigger())) {
                    final IThumbnailView thumb = Lookup.getDefault().lookup(IThumbnailView.class);
                    if (thumb != null) {
                        thumb.showTablePopUp(annotationTable, curRefViewer, e);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if ((e.getButton() == MouseEvent.BUTTON3) || (e.isPopupTrigger())) {
                    final IThumbnailView thumb = Lookup.getDefault().lookup(IThumbnailView.class);
                    if (thumb != null) {
                        thumb.showTablePopUp(annotationTable, curRefViewer, e);
                    }
                }
            }
        });

        //PropertyChangeListener for RevViewer
        res = Utilities.actionsGlobalContext().lookupResult(ReferenceViewer.class);
        res.addLookupListener(this);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jumpTextfield = new JTextFieldPasteable();
        jumpButton = new javax.swing.JButton();
        featureGroundPanel = new javax.swing.JPanel();
        filterProperties = new javax.swing.JPanel();
        jumpFilterLabel = new javax.swing.JLabel();
        filterTextfield = new JTextFieldPasteable();
        filterForLabel = new javax.swing.JLabel();
        radioProduct = new javax.swing.JRadioButton();
        radioEC = new javax.swing.JRadioButton();
        radioAnnotationButton = new javax.swing.JRadioButton();
        radioGene = new javax.swing.JRadioButton();
        tableScrollPane = new javax.swing.JScrollPane();
        annotationTable = new javax.swing.JTable();
        searchPatternField = new JTextFieldPasteable();
        searchPatternButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Navigation"));
        setPreferredSize(new java.awt.Dimension(190, 500));

        jumpTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumpTextfieldActionPerformed(evt);
            }
        });
        jumpTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumpTextfieldKeyTyped(evt);
            }
        });

        jumpButton.setText("Jump to Pos");
        jumpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumpButtonActionPerformed(evt);
            }
        });

        featureGroundPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        filterProperties.setBorder(javax.swing.BorderFactory.createTitledBorder("FilterProperties"));

        jumpFilterLabel.setText("RegEx Filter:");

        filterTextfield.setMinimumSize(jumpTextfield.getPreferredSize());

        filterForLabel.setText("Column:");

        buttonGroup1.add(radioProduct);
        radioProduct.setText("Product");
        radioProduct.setActionCommand("product");
        radioProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioProductActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioEC);
        radioEC.setText("EC-Number");
        radioEC.setActionCommand("ec");
        radioEC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioECActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioAnnotationButton);
        radioAnnotationButton.setSelected(true);
        radioAnnotationButton.setText("Annotation");
        radioAnnotationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioAnnotationButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioGene);
        radioGene.setText("Gene");
        radioGene.setActionCommand("ec");
        radioGene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioGeneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout filterPropertiesLayout = new javax.swing.GroupLayout(filterProperties);
        filterProperties.setLayout(filterPropertiesLayout);
        filterPropertiesLayout.setHorizontalGroup(
            filterPropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterPropertiesLayout.createSequentialGroup()
                .addGroup(filterPropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jumpFilterLabel)
                    .addComponent(filterForLabel))
                .addGroup(filterPropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(filterPropertiesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(filterTextfield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(filterPropertiesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radioAnnotationButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioGene)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioProduct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioEC)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        filterPropertiesLayout.setVerticalGroup(
            filterPropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterPropertiesLayout.createSequentialGroup()
                .addGroup(filterPropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jumpFilterLabel)
                    .addComponent(filterTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(filterPropertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterForLabel)
                    .addComponent(radioProduct)
                    .addComponent(radioEC)
                    .addComponent(radioAnnotationButton)
                    .addComponent(radioGene))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableScrollPane.setViewportView(annotationTable);

        javax.swing.GroupLayout featureGroundPanelLayout = new javax.swing.GroupLayout(featureGroundPanel);
        featureGroundPanel.setLayout(featureGroundPanelLayout);
        featureGroundPanelLayout.setHorizontalGroup(
            featureGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filterProperties, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        featureGroundPanelLayout.setVerticalGroup(
            featureGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(featureGroundPanelLayout.createSequentialGroup()
                .addComponent(filterProperties, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
        );

        searchPatternField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPatternFieldActionPerformed(evt);
            }
        });
        searchPatternField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchPatternFieldKeyTyped(evt);
            }
        });

        searchPatternButton.setText("Search Pattern");
        searchPatternButton.setToolTipText("<html>\n<b>First click</b> with new pattern searches the pattern. <b>Second click</b> with same pattern jumps to next occurrence beyond current interval. Fwd strand is checked first, then rev strand.\n</html>");
        searchPatternButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPatternButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jumpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchPatternButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jumpTextfield, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(searchPatternField))
                .addContainerGap())
            .addComponent(featureGroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jumpTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumpButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchPatternField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPatternButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(featureGroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jumpTextfieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumpTextfieldKeyTyped
        //jumpList.setSelectedValue(null, false);
        //DefaultListSelectionModel model = (DefaultListSelectionModel) jumpList.getSelectionModel();
        //model.clearSelection();
}//GEN-LAST:event_jumpTextfieldKeyTyped

    private void jumpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumpButtonActionPerformed
        if (isValidNumberInput(this.jumpTextfield.getText())) {
            this.jumpPosition = Integer.parseInt(this.jumpTextfield.getText().trim());
            this.boundsManager.navigatorBarUpdated(this.jumpPosition);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid position! (1-"+this.refGen.getSequence().length()+")", "Invalid Position", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_jumpButtonActionPerformed

    private void jumpTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumpTextfieldActionPerformed
        this.jumpButtonActionPerformed(evt);
    }//GEN-LAST:event_jumpTextfieldActionPerformed

    private void radioProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioProductActionPerformed
        this.clearFilter();
    }//GEN-LAST:event_radioProductActionPerformed

    private void radioECActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioECActionPerformed
        this.clearFilter();
    }//GEN-LAST:event_radioECActionPerformed

    private void radioAnnotationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioAnnotationButtonActionPerformed
        this.clearFilter();
    }//GEN-LAST:event_radioAnnotationButtonActionPerformed

    private void searchPatternFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPatternFieldActionPerformed
        this.searchPatternButtonActionPerformed(evt);
    }//GEN-LAST:event_searchPatternFieldActionPerformed

    private void searchPatternFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchPatternFieldKeyTyped
        // add your handling code here:
    }//GEN-LAST:event_searchPatternFieldKeyTyped

    private void searchPatternButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPatternButtonActionPerformed

        String pattern = this.searchPatternField.getText().toLowerCase();
//        if (SequenceUtils.isValidDnaString(pattern)) {
            int newPos;

            if (this.searchPattern != null && this.searchPattern.equals(pattern)) {
                newPos = this.viewer.getSequenceBar().findNextPatternOccurrence();
            } else {
                this.searchPattern = pattern;
                newPos = this.viewer.getSequenceBar().showPattern(this.searchPattern);
            }

            if (newPos > -1) {
                this.boundsManager.navigatorBarUpdated(newPos);
            }

//        } else {
//            JOptionPane.showMessageDialog(this, "Please enter a valid DNA string!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_searchPatternButtonActionPerformed

private void radioGeneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioGeneActionPerformed
    this.clearFilter();
}//GEN-LAST:event_radioGeneActionPerformed

    /**
     * Cecks if the input string is a valid number in the range of the reference genome.
     * @param s input string to check
     * @return <code>true</code> if it is a valid input string, <code>false</code> otherwise
     */
    private boolean isValidNumberInput(String s) {
        try {
            int tmp = Integer.parseInt(s);
            if (tmp >= 1 && tmp <= refGen.getSequence().length()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable annotationTable;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel featureGroundPanel;
    private javax.swing.JLabel filterForLabel;
    private javax.swing.JPanel filterProperties;
    private javax.swing.JTextField filterTextfield;
    private javax.swing.JButton jumpButton;
    private javax.swing.JLabel jumpFilterLabel;
    private javax.swing.JTextField jumpTextfield;
    private javax.swing.JRadioButton radioAnnotationButton;
    private javax.swing.JRadioButton radioEC;
    private javax.swing.JRadioButton radioGene;
    private javax.swing.JRadioButton radioProduct;
    private javax.swing.JButton searchPatternButton;
    private javax.swing.JTextField searchPatternField;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables

    public void setReferenceGenome(PersistantReference refGen) {
        this.refGen = refGen;
        refGenCon = ProjectConnector.getInstance().getRefGenomeConnector(refGen.getId());
        this.fillAnnotationList();
    }

    private void fillAnnotationList() {
        List<PersistantAnnotation> annotations = refGenCon.getAnnotationsForRegion(0, refGen.getSequence().length());
        
        List<PersistantAnnotation> featList = new ArrayList<PersistantAnnotation>(annotations);
        Collections.sort(featList, new AnnotationNameSorter());
        PersistantAnnotation[] annotationData = featList.toArray(new PersistantAnnotation[0]);

        //Create new Model for Table
        annotationTable.setModel(new AnnotationTableModel(annotationData));
        annotationTable.setRowSorter(new TableRowSorter<TableModel>(annotationTable.getModel()));
        annotationTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        updateFilter();

    }

    /*
     * Uses regular expression to filter all matching entries in Annotation, Product- or EC-Column.
     */
    private void updateFilter() {
        RowFilter<TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            if (radioAnnotationButton.isSelected()) {
                rf = RowFilter.regexFilter(filterTextfield.getText(), 0);
            } else if (radioGene.isSelected()) {
                rf = RowFilter.regexFilter(filterTextfield.getText(), 1);
            } else if (radioProduct.isSelected()) {
                rf = RowFilter.regexFilter(filterTextfield.getText(), 2);
            } else if (radioEC.isSelected()){
                rf = RowFilter.regexFilter(filterTextfield.getText(), 3);
            }

        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        ((TableRowSorter<TableModel>) annotationTable.getRowSorter()).setRowFilter(rf);
    }

    public void setBoundsInfoManager(BoundsInfoManager boundsManager) {
        this.boundsManager = boundsManager;

    }

    @Override
    public void resultChanged(LookupEvent le) {
        for (ReferenceViewer refViewer : res.allInstances()) {
            curRefViewer = refViewer;
        }
    }

    void clearFilter() {
        this.filterTextfield.setText("");
        this.updateFilter();
    }

    /**
     * Loads the copy, paste, cut, select all right click menus for all 
     * text fields beloning to this panel.
     */
    private void completeComponents() {
        this.jumpTextfield.addMouseListener(new StandardMenuEvent());
        this.searchPatternField.addMouseListener(new StandardMenuEvent());
        this.filterTextfield.addMouseListener(new StandardMenuEvent());
    }

    private class AnnotationNameSorter implements Comparator<PersistantAnnotation> {

        @Override
        public int compare(PersistantAnnotation o1, PersistantAnnotation o2) {
            String name1 = o1.getLocus();
            String name2 = o2.getLocus();

            // null string is always "bigger" than anything else
            if (name1 == null && name2 != null) {
                return 1;
            } else if (name1 != null && name2 == null) {
                return -1;
            } else if (name1 == name2) { //== comparison desired here 4 nullcheck
                // both are null
                return 0;
            } else {
                return name1.compareTo(name2);
            }
        }
    }

    public void setGenomeViewer(AbstractViewer viewer) {
        this.viewer = viewer;
    }
}

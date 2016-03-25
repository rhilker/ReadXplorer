/*
 * Copyright (C) 2014 Institute for Bioinformatics and Systems Biology, University Giessen, Germany
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.cebitec.readxplorer.vcfhandling.visualization;


import de.cebitec.readxplorer.databackend.ResultTrackAnalysis;
import de.cebitec.readxplorer.ui.analysis.ResultTablePanel;
import de.cebitec.readxplorer.ui.datavisualisation.BoundsInfoManager;
import de.cebitec.readxplorer.ui.tablevisualization.TableUtils;
import de.cebitec.readxplorer.ui.tablevisualization.tablefilter.TableRightClickFilter;
import de.cebitec.readxplorer.utils.UneditableTableModel;
import htsjdk.variant.variantcontext.VariantContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Marie
 */
public class SnpVcfResultPanel extends ResultTablePanel {

    public static final String SOURCE = "Source";
    public static final String CONTIG = "Contig";
    public static final String START = "Start";
    public static final String STOP = "Stop";
    public static final String ALLELES = "Alleles";
    public static final String GENOTYPE = "Genotype";
    public static final String LOG10ERROR = "Quality";
    public static final String FILTERS = "Filter";
    public static final String ATTRIBUTES = "Attributes";

    private static final long serialVersionUID = 1L;
    private Map<String, Integer> vcfStatsMap;
    private SnpVcfResult completeVcfData;
    private final TableRightClickFilter<UneditableTableModel> tableFilter;
    private Object[] rowData;


    /**
     * Creates new form Snp_VcfResultPanel.
     * <p>
     * @param bim BoundsInfoManager of the reference on which this analysis was
     *            performed.
     */
    public SnpVcfResultPanel( BoundsInfoManager bim ) {
        setBoundsInfoManager( bim );
        initComponents();
        final int sourColumn = 3;
        final int trackColumn = 2;
        final int chromColumn = 1;
        tableFilter = new TableRightClickFilter<>( UneditableTableModel.class, sourColumn, trackColumn );
        this.vcfTable.getTableHeader().addMouseListener( tableFilter );
        //ensures number of lines will adapt to number of translations (features) for each snp

        TableUtils.addTableListSelectionListener( vcfTable, sourColumn, chromColumn, getBoundsInfoManager() );
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        vcfTable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 304));

        vcfTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Source", "Contig", "Start", "Stop", "Alleles", "Genotypes", "log 10Error", "Filters", "Attributes"
            }
        ));
        jScrollPane1.setViewportView(vcfTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable vcfTable;
    // End of variables declaration//GEN-END:variables


    @Override
    public int getDataSize() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void addResult( ResultTrackAnalysis newResult ) {
        if( newResult instanceof SnpVcfResult ) {
            SnpVcfResult vcfData = (SnpVcfResult) newResult;

            if( this.completeVcfData == null ) {
                this.completeVcfData = vcfData;
                this.vcfStatsMap = new HashMap<>( vcfData.getStatsMap() );
                vcfStatsMap.put( SOURCE, 0 );
                vcfStatsMap.put( CONTIG, 0 );
                vcfStatsMap.put( START, 0 );
                vcfStatsMap.put( STOP, 0 );
                vcfStatsMap.put( ALLELES, 0 );
                vcfStatsMap.put( GENOTYPE, 0 );
                vcfStatsMap.put( LOG10ERROR, 0 );
                vcfStatsMap.put( FILTERS, 0 );
                vcfStatsMap.put( ATTRIBUTES, 0 );
                vcfData.setStatsMap( vcfStatsMap );
            } else {
                this.completeVcfData.getSnpVcfList().addAll( vcfData.getSnpVcfList() );
            }
            final int snpVcfDataSize = 10;
            List<VariantContext> snpVcf = vcfData.getSnpVcfList();
            DefaultTableModel m = (DefaultTableModel) vcfTable.getModel();

            for( VariantContext vc : snpVcf ) {
                int i = 0;
                rowData = new Object[snpVcfDataSize];
                rowData[i++] = vc.getSource();
                rowData[i++] = vc.getChr();
                rowData[i++] = vc.getStart();
                rowData[i++] = vc.getEnd();
                rowData[i++] = vc.getAlleles();
                rowData[i++] = vc.getGenotypes(); //vorverarbeitung
                rowData[i++] = vc.getLog10PError();
                rowData[i++] = vc.getFilters();  //vorverarbeitung
                rowData[i++] = vc.getAttributes(); //evtl. eigene spalten für immer vorkommende einträge, rest zusammenfassen
                m.addRow( rowData );
            }
        }

    }


}
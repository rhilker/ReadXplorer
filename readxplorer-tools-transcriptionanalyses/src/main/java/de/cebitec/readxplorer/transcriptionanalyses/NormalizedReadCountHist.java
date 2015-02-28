/*
 * Copyright (C) 2015 Rolf Hilker <rolf.hilker at mikrobio.med.uni-giessen.de>
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

package de.cebitec.readxplorer.transcriptionanalyses;


import de.cebitec.readxplorer.transcriptionanalyses.datastructures.NormalizedReadCount;
import de.cebitec.readxplorer.utils.MathUtils;
import java.awt.BorderLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javax.swing.JPanel;



/**
 * Histogram panel for displaying a histogram of TPM or RPKM values.
 *
 * @author Rolf Hilker <rolf.hilker at mikrobio.med.uni-giessen.de>
 */
public class NormalizedReadCountHist extends JPanel implements ComponentListener {

    private static final long serialVersionUID = 1L;

    private static final String TPM = "TPM";
    private static final String RPKM = "RPKM";

    private JFXPanel fxPanel;
    private final NormalizationAnalysisResult normalizationResult;
    private BarChart<String, Number> barChart;
    private boolean isRPKM = false;
    private String type = "TPM";
    private NormHistogramRunnable histRunnable;

    /**
     * Histogram panel for displaying a histogram of TPM or RPKM values.
     */
    public NormalizedReadCountHist( NormalizationAnalysisResult normalizationResult ) {
        initComponents();
        this.normalizationResult = normalizationResult;
        initSwingComponents();
        initFxComponents();
        addComponentListener( this );
    }


    /** This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        switchButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();

        org.openide.awt.Mnemonics.setLocalizedText(switchButton, org.openide.util.NbBundle.getMessage(NormalizedReadCountHist.class, "NormalizedReadCountHist.switchButton.text")); // NOI18N
        switchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchButtonActionPerformed(evt);
            }
        });

        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(switchButton)
                .addGap(0, 293, Short.MAX_VALUE))
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(switchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void switchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchButtonActionPerformed
        isRPKM = !isRPKM;
        showHistogram();
    }//GEN-LAST:event_switchButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton switchButton;
    // End of variables declaration//GEN-END:variables


    /**
     * Paint the histogram for the current result.
     */
    public void showHistogram() {
        setType();
        String buttonType = RPKM;
        if( isRPKM ) {
            buttonType = TPM;
        }
        switchButton.setText( "Switch to " + buttonType );
        initFxComponents();
    }


    /**
     * Set the name of the panel according to the shown data.
     */
    private void setType() {
        type = TPM;
        if( isRPKM ) {
            type = RPKM;
        }
//        setName( type + " Value Histogram for " + GeneralUtils.generateConcatenatedString( normalizationResult.getTrackNameList(), 20 ) );
    }


    @Override
    public void componentHidden( ComponentEvent e ) {

    }


    @Override
    public void componentMoved( ComponentEvent e ) {

    }


    @Override
    public void componentResized( ComponentEvent e ) {
        this.fxPanel.validate();
    }


    @Override
    public void componentShown( ComponentEvent e ) {

    }


    /**
     * @return <code>true</code>, if the histogram shows RPKM values,
     *         <code>false</code> if it shows TPM values.
     */
    public boolean isRPKM() {
        return isRPKM;
    }


    /**
     * @param isRPKM <code>true</code>, if the histogram shows RPKM values,
     *               <code>false</code> if it shows TPM values.
     */
    public void setIsRPKM( boolean isRPKM ) {
        this.isRPKM = isRPKM;
    }


    /**
     * Initializes the swing components of this panel.
     */
    private void initSwingComponents() {
        fxPanel = new JFXPanel();
        mainPanel.add( fxPanel, BorderLayout.CENTER );
    }


    /**
     * Initializes all javafx components.
     */
    private void initFxComponents() {
        histRunnable = new NormHistogramRunnable();
        Platform.runLater( histRunnable );
    }


    /**
     * The runnable actually creating the histogram.
     */
    private class NormHistogramRunnable implements Runnable {


        /**
         * The runnable actually creating the histogram.
         */
        public NormHistogramRunnable() {
        }


        @Override
        public void run() {
            BorderPane border = new BorderPane();
            Application.setUserAgentStylesheet( Application.STYLESHEET_CASPIAN );
            Scene scene = new Scene( border, 1200, 600 );

            createHistogram();

            border.setCenter( barChart );
            fxPanel.setScene( scene );
            Platform.setImplicitExit( false );
        }


        /**
         * Create the TPM or RPKM histogram depending on the type set in this
         * TopComponent.
         */
        private void createHistogram() {
            double max = 0;
            double min = Integer.MAX_VALUE;
            List<Double> rpkmList = new ArrayList<>();
            List<NormalizedReadCount> normResults = normalizationResult.getResults();
            for( NormalizedReadCount rpkmValue : normResults ) {
                double rpkm = isRPKM ? rpkmValue.getRPKM() : rpkmValue.getTPM();
                if( rpkm < min ) {
                    min = rpkm;
                }
                if( rpkm >= max ) {
                    max = rpkm;
                }
                rpkmList.add( rpkm );
            }
            Collections.sort( rpkmList );

            int[] intervals = new int[21]; //intervals of bars that are shown later
            for( int l = 0; l < intervals.length; l++ ) {
                intervals[l] = 0;
            }

            //calculate quantile borders for useful resolution of the histogram
            double quantileBorder20 = MathUtils.getQuantileBorder( 0.2, rpkmList );
            double quantileBorder90 = MathUtils.getQuantileBorder( 0.9, rpkmList );
            double shift20 = quantileBorder20 / 5;
            double shift90 = quantileBorder90 / 13;
            double shiftLargest = (max + 0.1) / 3;

            //add rpkm values into their corresponding interval = histogram bar
            for( NormalizedReadCount normValue : normResults ) {
                double value = isRPKM ? normValue.getRPKM() : normValue.getTPM();
                int index;
                if( value <= quantileBorder20 ) {
                    index = (int) Math.floor( value / shift20 );
                } else if( value <= quantileBorder90 ) {
                    index = 5 + (int) Math.floor( value / shift90 );
                } else {
                    index = 18 + (int) Math.floor( value / shiftLargest );
                }
                intervals[index]++;
            }

            double maxY = Math.log( normResults.size() );
            NumberAxis lineYAxis = new NumberAxis( 0, maxY, 2 );
            lineYAxis.setLabel( "Number of Features (Log scale)" );
            CategoryAxis lineXAxis = new CategoryAxis();
            lineXAxis.setLabel( type + " Value Ranges" );
            barChart = new BarChart<>( lineXAxis, lineYAxis );
            XYChart.Series<String, Number> bar = new XYChart.Series<>();
            bar.setName( "Frequency of " + type + " Values" );
            /* for (int i = 0; i < rpkmValues.size(); i++) {
             * double rpkm = rpkmValues.get(i).getRPKM();
             * String name = rpkmValues.get(i).getFeature().getFeatureName();
             * //bar.getData().add(getData(rpkm, name));
             * XYChart.Data o = getData(rpkm, name);
             * /* o.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
             *
             *  @Override public void handle(MouseEvent t) {
             *      System.out.println("MouseEvent!!");
             *  }
             *
             * });
             * bar.getData().add(o);
             * } */
            double start = 0.0;
            start = addDataAndDescriptionsToBar( 0, 5, shift20, start, intervals, bar );
            start = addDataAndDescriptionsToBar( 5, 18, shift90, start, intervals, bar );
            addDataAndDescriptionsToBar( 18, intervals.length, shiftLargest, start, intervals, bar );

            barChart.getData().addAll( bar );

            for( XYChart.Series<String, Number> series : barChart.getData() ) {
                for( XYChart.Data<String, Number> data : series.getData() ) {
                    Tooltip.install( data.getNode(), new Tooltip( "# features: " + data.getExtraValue().toString() ) );
//                        this.addLabelToEntry(data, data.getExtraValue().toString());
                }
            }
        }


        /**
         * Adds the given interval data and corresponding descriptions to the
         * given bar chart.
         * <p>
         * @param startIdx  The index from the interval list to start adding the
         *                  data to the bar chart
         * @param endIdx    The end index of the interval list for adding data
         *                  to the bar chart
         * @param shift     The length of each interval in the list
         * @param start     The actual start value of the first interval
         * @param intervals The list of intervals to add to the bar chart
         * @param bar       The bar chart to add the data to
         * <p>
         * @return The updated start value. This is the largest value of the
         *         last interval added to the chart.
         */
        private double addDataAndDescriptionsToBar( int startIdx, int endIdx, double shift, double start, int[] intervals, XYChart.Series<String, Number> bar ) {
            addDataAndDescriptionToBar( start, shift, intervals, startIdx, bar );
            start = shift;
            for( int i = startIdx + 1; i < endIdx; i++ ) {
                addDataAndDescriptionToBar( start, start + shift, intervals, i, bar );
                start += shift;
            }
            return start;
        }


        /**
         * Adds the data of a given single interval and its corresponding
         * description to the given bar chart.
         * <p>
         * @param start     The start value of the interval to add
         * @param end       The end value of the interval to add
         * @param intervals The list of intervals to add to the bar chart
         * @param i         The interval index. This interval is treated in this
         *                  method call.
         * @param bar       bar The bar chart to add the data to
         */
        private void addDataAndDescriptionToBar( double start, double end, int[] intervals, int i, XYChart.Series<String, Number> bar ) {
            DecimalFormat decimalFormat = new DecimalFormat( "#.##" );
            String name = decimalFormat.format( start ) + " - " + decimalFormat.format( end );
            double logValue = Math.log( intervals[i] );
            logValue = logValue == 0 ? 0.1 : logValue;
            XYChart.Data<String, Number> entry = new XYChart.Data<String, Number>( name, logValue );
            entry.setExtraValue( intervals[i] );
            bar.getData().add( entry );
        }

//            /**
//             * Adds a label to a data entry of a javafx chart. The label is able
//             * to reposition and resize, depending on change events of their
//             * node.
//             */
//            private void addLabelToEntry(XYChart.Data<String, Number> entry, String value) {
//                final Node node = entry.getNode();
//                final Text dataText = new Text(String.valueOf(value));
//                node.parentProperty().addListener(new ChangeListener<Parent>() {
//                    @Override
//                    public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
//                        Group parentGroup = (Group) parent;
//                        parentGroup.getChildren().add(dataText);
//                    }
//                });
//
//                node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
//                    @Override
//                    public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
//                        dataText.setLayoutX(
//                                Math.round(bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2));
//                        dataText.setLayoutY(
//                                Math.round(bounds.getMinY() - dataText.prefHeight(-1) * 0.5));
//                    }
//                });
//            }

    }
}

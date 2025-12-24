import java.util.ArrayList;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;

public class Listening_spinner extends JSpinner {

    public Listening_spinner(int gridColumns, Panel_using_array thisPanel, ArrayList<Panel_using_array> panelsToBeControlled) {

        // gridColumns argument defines spinner behavior
        this.setModel(new SpinnerNumberModel
            (0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
        this.addChangeListener((ChangeEvent e) -> {
            if (gridColumns == 2) {
                // only update particular labels with spinner values totals
                Integer total = 0;
                for (Listening_spinner spinner2 : thisPanel.getListeningSpinnersToBeCreated()) {
                    total += Integer.valueOf(spinner2.getValue().toString());
                }
                thisPanel.getJlabelsToBeCreated()[1].setText(total.toString());
                // also update budget result labels
                if (panelsToBeControlled != null) {
                    // so it's the bottom (controlling) panel
                    // get left (income) subtotal
                    if (panelsToBeControlled.size() == 3) {
                        Integer budgetResult = 
                            Integer.valueOf(panelsToBeControlled.get(1).getJlabelsToBeCreated()[1].getText());
                        // subtract right (expenses) subtotal
                        budgetResult -=
                            Integer.valueOf(panelsToBeControlled.get(2).getJlabelsToBeCreated()[1].getText());
                        // set budget result label
                        panelsToBeControlled.get(0).getJlabelsToBeCreated()[1].setText(budgetResult.toString());
                        //System.out.println(budgetResult);
                    }
                    
                }
            } else if (gridColumns == 4) {
                //// update righter spinners and labels with spinner totals
                // create an array wich holds subtotals for every column
                int[] spinnerRowSubTotals = new int[gridColumns - 1];
                // update righer spinners first
                for (int spinnerIndex = 0; 
                    spinnerIndex < thisPanel.getListeningSpinnersToBeCreated().length;
                    spinnerIndex += (gridColumns - 1)) {
                    Integer righterSpinnerSubTotal = 0;
                    for (int rowTupleIndex = spinnerIndex;
                        rowTupleIndex < spinnerIndex + (gridColumns - 1) - 1;
                        rowTupleIndex++) {
                        // calculate left spinners subtotals for the labels
                        spinnerRowSubTotals[rowTupleIndex - spinnerIndex]
                            += Integer.valueOf(thisPanel.getListeningSpinnersToBeCreated()[rowTupleIndex].getValue().toString());
                        // calculate subtotals of righter spinners, set those spinners value
                        righterSpinnerSubTotal
                            += Integer.valueOf(thisPanel.getListeningSpinnersToBeCreated()[rowTupleIndex].getValue().toString());
                    }
                    // calculate righter spinners totals for the labels
                    spinnerRowSubTotals[spinnerRowSubTotals.length - 1] += righterSpinnerSubTotal;
                    thisPanel.getListeningSpinnersToBeCreated()
                        [spinnerIndex + (gridColumns - 1) - 1].setValue(righterSpinnerSubTotal);
                }
                // place subtotals on labels
                // if this is a panel which uses labels
                if (thisPanel.getJlabelsToBeCreated()[0] != null ) {
                    // first label is text, not value
                    for (int st = 0; st < spinnerRowSubTotals.length; st++) {
                        Integer currentSubTotal = spinnerRowSubTotals[st];
                        thisPanel.getJlabelsToBeCreated()[st + 1].
                            setText(currentSubTotal.toString());
                    }
                }
                //// place general totals on bottom panel general total labels
                //Integer currentGeneralTotal;
                int[] columnSubTotals = new int[gridColumns -1];
                for (int panelIndex = 1; panelIndex < panelsToBeControlled.size(); panelIndex++) {
                    // if this is a panel which does not use labels
                    if (panelsToBeControlled.get(panelIndex).getJlabelsToBeCreated()[0] == null ) {
                        // calculate and put subtotals of every spinner column in columnSubTotals
                        // for every spinner of this column
                        for (int panelSpinnerIndex = 0; panelSpinnerIndex < panelsToBeControlled.get(panelIndex).getListeningSpinnersToBeCreated().length; panelSpinnerIndex+= (gridColumns - 1)) {
                            for (int columnTupleIndex = panelSpinnerIndex;
                            columnTupleIndex < panelSpinnerIndex + (gridColumns - 1);
                            columnTupleIndex++) {
                                columnSubTotals[columnTupleIndex - panelSpinnerIndex]
                                    += Integer.valueOf(panelsToBeControlled.get(panelIndex).getListeningSpinnersToBeCreated()[columnTupleIndex].getValue().toString());
                            }
                        }
                    }  else if (panelsToBeControlled.get(panelIndex).getJlabelsToBeCreated()[0] != null ) {
                        // if this is a panel which uses labels
                        // add subtotal label values to column subtotals
                        // first label (0) has no values
                        for (int panelLabelIndex = 1; 
                            panelLabelIndex < panelsToBeControlled.get(panelIndex).getJlabelsToBeCreated().length;
                            panelLabelIndex++) {
                            columnSubTotals[panelLabelIndex - 1]
                            += Integer.valueOf(panelsToBeControlled.get(panelIndex).getJlabelsToBeCreated()[panelLabelIndex].getText());
                        }
                    }
                    // finally, put column subtotals in bottom panel labels
                    // again, first label (0) has no value 
                    for (int totalLabelIndex = 1; 
                        totalLabelIndex < panelsToBeControlled.get(0).getJlabelsToBeCreated().length;
                        totalLabelIndex++) {
                        panelsToBeControlled.get(0).
                        getJlabelsToBeCreated()[totalLabelIndex].
                        setText(Integer.toString(columnSubTotals[totalLabelIndex - 1]));
                    }
                }
            }
        });

    }
}

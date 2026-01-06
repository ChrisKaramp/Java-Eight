package gr.aueb.javaeight;

import java.util.ArrayList;

import javax.swing.JButton;

/**
 * Creates a button that includes a listener and a button handler.
 */
public class InteractiveButton extends JButton {
      /**
       * Defines what this button does using arguments.
       * More parameters are given to do the job.
       * @param buttonCaption button text
       * @param buttonType button type
       * @param somePanel panel to be controlled
       * @param dataFilePath absolute path to data file
       * @param panelsToBeRun panels to be run
       * @param panelContainingYear panel that contains selected year menu
       */
      public InteractiveButton(String buttonCaption, String buttonType, Object somePanel, String dataFilePath, ArrayList<ArrayBasedPanel> panelsToBeRun, TopPanelBOModel panelContainingYear) {
        this.setText(buttonCaption);
        addActionListener(new ButtonHandler(buttonType, somePanel, dataFilePath, panelsToBeRun, panelContainingYear));
      }
}

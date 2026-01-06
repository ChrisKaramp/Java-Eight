import java.util.ArrayList;

import javax.swing.JButton;

/**
 * Creates a button that includes a listener and a button handler.
 */
public class InteractiveButton extends JButton {
      /**
       * Defines what this button does using arguments.
       * More parameters are given to do the job.
       * @param buttonCaption
       * @param buttonType
       * @param somePanel
       * @param dataFilePath
       * @param panelsToBeRan
       * @param panelContainingYear
       */
      public InteractiveButton(String buttonCaption, String buttonType, Object somePanel, String dataFilePath, ArrayList<ArrayBasedPanel> panelsToBeRan, TopPanelBOModel panelContainingYear) {
        this.setText(buttonCaption);
        addActionListener(new ButtonHandler(buttonType, somePanel, dataFilePath, panelsToBeRan, panelContainingYear));
      }
}

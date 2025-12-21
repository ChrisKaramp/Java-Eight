import java.util.ArrayList;
import javax.swing.JButton;

public class Listening_button extends JButton {
  
      public Listening_button(String buttonCaption, String buttonType, Object somePanel, String dataFilePath, ArrayList<Panel_using_array> panelsToBeRan, Top_panel_bo_model panelContainingYear) {
        this.setText(buttonCaption);
        addActionListener(new Button_handler(buttonType, somePanel, dataFilePath, panelsToBeRan, panelContainingYear));
      }
}

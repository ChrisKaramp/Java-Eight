import javax.swing.JButton;

public class Listening_button extends JButton {
  
      public Listening_button(String button_caption, String button_type) {
        this.setText(button_caption);
        addActionListener(new Button_handler(button_type));
      }
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Button_handler implements ActionListener {

    private String bt;

    public Button_handler(String button_type) {
        setBt(button_type);
  	}   

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ( getBt().equalsIgnoreCase("add year") ) {
            try {
                Add_year.add_year_into_data_file(Constants.DATA_FILE_PATH, Create_main_window.yearadd_JTextField.getText());
            } catch (IOException ex) {
                System.out.println("An error occurred while adding year in data file. Exit code: 1.");
                System.exit(1);
            }
        } else {
            System.out.println(getBt());
        }
    }   

    private String getBt() {
        return bt;
    }

    private void setBt(String bt) {
        this.bt = bt;
    }
}   
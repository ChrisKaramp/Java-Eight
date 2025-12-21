import java.awt.*;
import java.io.IOException;

public class Pfad {
    public static void main(String[] args) throws IOException {

        // check if data file exists, then get all data in array, else create and initialize it
        if ( Build_data_file.create_data_file(Constants.DATA_FILE_PATH).equalsIgnoreCase("created") ) {
            Build_data_file.initialize_data_file(Constants.DATA_FILE_PATH);
            System.out.println("Data file initialized successfully.");
        } else if ( Build_data_file.create_data_file(Constants.DATA_FILE_PATH).equalsIgnoreCase("error") ) {
            System.out.println("An error occurred while creating data file. Exit code: 1.");
            System.exit(1);
        }

        // get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Double screen_width = screenSize.getWidth();
        Double screen_height = screenSize.getHeight() * 0.9;
        int frame_width = screen_width.intValue();
        int frame_height = screen_height.intValue();
        //System.out.println(screen_width);
        //System.out.println(screen_height);
        //System.out.println(frame_width);
        //System.out.println(frame_height);

        // create budget_origin_window, dimensions same as screen
        Create_main_window budget_origin_window = new Create_main_window();
        budget_origin_window.configure_window(frame_width, frame_height);

    }
}

package startup;

import app.Application;

import java.sql.SQLException;
import javax.swing.*;

/**
 * Created by root on 18.02.18.
 */
public class Launch {

    public static void main (String[] args) throws SQLException {


        javax.swing.SwingUtilities.invokeLater(Application.self.mainController::createAndShowGUI);
    }
}

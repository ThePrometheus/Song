package app;
import controllers.MainController;

import javax.swing.*;

/**
 * Created by root on 18.02.18.
 */
public class Application {
    public final static Application self = new Application();

    // REPOSITORIES


    //SERVICES
    public final IMusicianService musicianService = new MusicianService();
    public final IAlbumService albumService = new AlbumService();
    public final ISongService songService = new  SongService();



    //CONTROLLERS
    public final MainController mainController = new MainController();


    //GENERAL BEHAVIOUR
    public static void  showMessage(String message){
        JOptionPane.showMessageDialog(null,message);

    }



}

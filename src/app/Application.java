package app;
import controllers.MainController;
import controllers.SongViewController;
import repository.*;
import repository.impl.*;
import service.IAlbumService;
import service.IMusicianService;
import service.IMusicianSongService;
import service.ISongService;
import service.impl.AlbumService;
import service.impl.MusicianService;
import service.impl.MusicianSongService;
import service.impl.SongService;

import javax.swing.*;

/**
 * Created by root on 18.02.18.
 */
public class Application {
    public final  static Application self = new Application();

    // REPOSITORIES

    public final IMusicianRepository musicianRepository = new MusicianRepository();
    public final IAlbumPriceRepository albumPriceRepository = new AlbumPriceRepository();
    public final IAlbumRepository albumRepository = new AlbumRepository();
    public final ISongRepository songRepository = new SongRepository();
    public final IMusicianSongRepository musicianSongRepository = new MusicianSongRepository();



    //SERVICES
    public final IMusicianService musicianService = new MusicianService();
    public final IAlbumService albumService = new AlbumService();
    public final ISongService songService = new SongService();
    public final IMusicianSongService musicianSongService = new MusicianSongService();




    //CONTROLLERS
    public final MainController mainController = new MainController();
    public  SongViewController songViewController;


    //GENERAL BEHAVIOUR
    public static void  showMessage(String message){
        JOptionPane.showMessageDialog(null,message);

    }



}

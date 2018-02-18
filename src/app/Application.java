package app;
import controllers.MainController;
import repository.IAlbumPriceRepository;
import repository.IAlbumRepository;
import repository.IMusicianRepository;
import repository.ISongRepository;
import repository.impl.AlbumPriceRepository;
import repository.impl.AlbumRepository;
import repository.impl.MusicianRepository;
import repository.impl.SongRepository;
import service.IAlbumService;
import service.IMusicianService;
import service.ISongService;
import service.impl.AlbumService;
import service.impl.MusicianService;
import service.impl.SongService;

import javax.swing.*;

/**
 * Created by root on 18.02.18.
 */
public class Application {
    public final static Application self = new Application();

    // REPOSITORIES

    public final IMusicianRepository musicianRepository = new MusicianRepository();
    public final IAlbumPriceRepository albumPriceRepository = new AlbumPriceRepository();
    public final IAlbumRepository albumRepository = new AlbumRepository();
    public final ISongRepository songRepository = new SongRepository();


    //SERVICES
    public final IMusicianService musicianService = new MusicianService();
    public final IAlbumService albumService = new AlbumService();
    public final ISongService songService = new SongService();



    //CONTROLLERS
    public final MainController mainController = new MainController();


    //GENERAL BEHAVIOUR
    public static void  showMessage(String message){
        JOptionPane.showMessageDialog(null,message);

    }



}

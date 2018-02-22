package service;

import model.MusicianSong;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by root on 22.02.18.
 */
public interface IMusicianSongService {

    List<MusicianSong> getById(long id) throws SQLException;


}

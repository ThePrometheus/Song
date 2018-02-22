package repository;

import model.MusicianSong;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by root on 22.02.18.
 */
public interface IMusicianSongRepository {

    List<MusicianSong> getMusicians(long id) throws SQLException;

 }

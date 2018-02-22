package service.impl;

import app.Application;
import model.MusicianSong;
import service.IMusicianSongService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by root on 22.02.18.
 */
public class MusicianSongService implements IMusicianSongService {

    @Override
    public List<MusicianSong> getById(long id) throws SQLException {
        return Application.self.musicianSongRepository.getMusicians(id);

    }
}

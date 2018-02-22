package repository.impl;

import app.DBConnector;
import model.Musician;
import model.MusicianSong;
import repository.IMusicianSongRepository;
import service.IMusicianSongService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 22.02.18.
 */
public class MusicianSongRepository implements IMusicianSongRepository {

    @Override
    public List<MusicianSong> getMusicians(long id) throws SQLException {
        Connection c = DBConnector.shared.getConnect();
        PreparedStatement ps = c.prepareStatement(getMusicianShare);
        ps.setLong(1, id);
        List<MusicianSong> result = listMusicianSongFrom(ps.executeQuery());
        return (result.isEmpty()) ? null : (List<MusicianSong>) result.get(0);


    }

    private final static String getMusicianShare = "SELECT * FROM musician_song WHERE song_id =?;";

    private List<MusicianSong> listMusicianSongFrom(ResultSet resultSet) throws SQLException {
        List<MusicianSong> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(musicianSongFrom(resultSet));
        }
        return list;

    }
    private MusicianSong musicianSongFrom(ResultSet resultSet) throws SQLException {
        MusicianSong musicianSong = new MusicianSong();
        musicianSong.setMusician_id(resultSet.getLong("musician_id"));
        musicianSong.setSong_id(resultSet.getLong("song_id"));
        musicianSong.setFee_share(resultSet.getDouble("fee_share"));

        return musicianSong;
    }

}
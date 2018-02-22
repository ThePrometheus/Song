package repository.impl;

import app.Constants;
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

    @Override
    public boolean insert(MusicianSong musicianSong) throws SQLException {
        Connection c = DBConnector.shared.getConnect();
        PreparedStatement ps = c.prepareStatement(insert);
        ps.setLong(1,musicianSong.getMusician_id());
        ps.setLong(2,musicianSong.getSong_id());
        ps.setDouble(3,musicianSong.getFee_share());
        int songCode = ps.executeUpdate();
        return songCode== Constants.DB_SUCCESS_EXECUTION_CODE;


    }


    private final static String getMusicianShare = "SELECT * FROM musician_song WHERE song_id =?;";
private final static String insert = "INSERT  INTO musician_song(musician_id,song_id,fee_share) VALUES(?,?,?);";
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
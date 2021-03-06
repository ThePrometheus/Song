package repository;

import model.Album;
import model.Musician;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by root on 18.02.18.
 */
public interface IMusicianRepository {

    List<Musician> all() throws SQLException;
    Musician getBy(long id) throws SQLException;
    Musician getBy(Album album) throws SQLException;
    boolean insert(Musician musician);
    boolean update(Musician musician);
}


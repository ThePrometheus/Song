package repository;

import java.sql.SQLException;

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
}

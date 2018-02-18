package repository;

import model.Album;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by root on 18.02.18.
 */
public interface IAlbumRepository {
    List<Album> all() throws SQLException;
    Album getBy(long id) throws SQLException;
    boolean insert(Album album) throws Exception;
    boolean update(Album album) throws SQLException;


}

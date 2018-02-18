package repository;

import model.Album;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by root on 18.02.18.
 */
public interface IAlbumPriceRepository {
    Double getLastPriceFor(Album album) throws SQLException;
    Double getPriceFor(Album album, Date date);
    boolean save(Double price, Album album) throws Exception;
}

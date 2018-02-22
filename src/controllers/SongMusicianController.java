package controllers;

import app.Application;
import jdk.nashorn.internal.runtime.regexp.joni.ApplyCaseFoldArg;
import model.Album;
import model.Musician;
import model.MusicianSong;
import model.Song;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 22.02.18.
 */
public class SongMusicianController extends JFrame {
    private JPanel contentView;
    private JList musicianList;
    private JLabel feeLabel;
    private JButton addMusician;
    private JPanel shareInfoPanel;
    private Song currentSong;
    private JButton buttonCancel;
    private List<MusicianSong> dataSource;
    private MusicianSong currentMusicianSong;
    private long song_id;


    public SongMusicianController(long id){
        System.out.println("Id:"+id);

    setup();
    }

    public SongMusicianController(){}

    private void setup(){
        setContentPane(contentView);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentView.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void setupComponents(){
        reloadListData();
        musicianList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        musicianList.addListSelectionListener(e -> didSelectListItem(e));
        musicianList.setSelectedIndex(0);
        addMusician.addActionListener(e -> Application.self.songViewController.addMusician(e));
    }

    private void  reloadData(){
        try {
            System.out.println("2");


            dataSource = Application.self.musicianSongRepository.getMusicians(this.song_id);
            System.out.println("3");
        }
        catch(SQLException e){
            System.out.println("4");
            e.printStackTrace();
        }
    }
    private void reloadListData() {
        System.out.println("1");
        reloadData();


        DefaultListModel<MusicianSong> dlm = new DefaultListModel<>();
        for (MusicianSong s : dataSource) {
            dlm.addElement(s);
            System.out.println("added musicians" + s.toString());
        }
        musicianList.setModel(dlm);


    }
    private void onCancel(){dispose();}


    private void didSelectListItem(ListSelectionEvent e) {
        int index = musicianList.getSelectedIndex();
        if (index < 0) return;
        currentMusicianSong = dataSource.get(index);

        repaintDetails();


    }



    private void repaintDetails(){
        feeLabel.setText(String.valueOf(currentMusicianSong.getFee_share()));



    }


    public static void presentDialog(long id){
        SongMusicianController dialog = new SongMusicianController( id);
        doPresent(dialog);
    }
    private static void doPresent(SongMusicianController songMusicianController){
        songMusicianController.setupComponents();
        songMusicianController.pack();
        songMusicianController.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

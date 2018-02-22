package controllers;


import app.Application;
import app.Strings;
import model.Musician;
import model.MusicianSong;
import model.Song;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class SongViewController {
    private List<Song> dataSource;

    private Song currentSong;

    private JList<Song> songList;

    private JPanel contentView = new JPanel();

    private JLabel songNameLabel = new JLabel();

    private JLabel albumNameLabel = new JLabel();

    private JLabel authorLabel = new JLabel();

    private JLabel musicianNameLabel = new JLabel();
    private JLabel musicianLastNameLabel = new JLabel();

    private JButton addMusician = new JButton();

    private JLabel musicianInstrumentLabel = new JLabel();
    private List<Musician> musicians = new ArrayList<>();


    public SongViewController() {

        reloadListData();

        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        songList.addListSelectionListener(e -> didSelectListItem(e));
        songList.setSelectedIndex(0);
        addMusician.addActionListener(e -> addMusician(e));


    }

    public JPanel getContentView() {
        return contentView;
    }


    public long getCurrentId() {
        int index = songList.getSelectedIndex();
        if (index < 0) return -1;
        currentSong = dataSource.get(index);
        return currentSong.getId();
    }

    public void refresh() {
        reloadListData();
        repaintDetails();
    }

    private void reloadListData() {
        reloadData();

        DefaultListModel<Song> dlm = new DefaultListModel<>();
        for (Song s : dataSource) {
            dlm.addElement(s);
            System.out.println("added song" + s.toString());
        }
        songList.setModel(dlm);


    }

    private void reloadData() {

        dataSource = Application.self.songService.all();


    }

    private void repaintDetails() {
        if (currentSong == null) return;
        songNameLabel.setText(currentSong.getName());
        albumNameLabel.setText(currentSong.toString());
        authorLabel.setText(currentSong.getAuthor());


    }


    private void didSelectListItem(ListSelectionEvent e) {
        int index = songList.getSelectedIndex();
        if (index < 0) return;
        currentSong = dataSource.get(index);

        repaintDetails();


    }

    private void addMusician(ActionEvent e) {
        musicians = Application.self.musicianService.all();
        JTextField feeShare = new JTextField();
        JComboBox musicianBox = new JComboBox();

        DefaultComboBoxModel<Musician> dlm = new DefaultComboBoxModel<>();
        for (Musician m : musicians) {
            dlm.addElement(m);
        }
        musicianBox.setModel(dlm);
        Object[] message = {
                "Musician:", musicianBox,
                "Fee share:", feeShare
        };
        int option = JOptionPane.showConfirmDialog(null, message, Strings.DIALOG_ADD_MUSICIAN, JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            MusicianSong ms = new MusicianSong();
            ms.setMusician_id(musicianBox.getSelectedIndex());
            ms.setSong_id(getCurrentId());
            Double fee = null;
            try {
                fee = Double.parseDouble(feeShare.getText());
            } catch (NumberFormatException ex) {
                Application.showMessage(Strings.DIALOG_NUMBER_FORMAT_ERROR);
                return;
            } catch (NullPointerException ex) {
                return;
            }

            try {
                Application.self.musicianSongRepository.insert(ms);
            } catch (Exception ex) {
                Application.showMessage(ex.getMessage());
            }
            repaintDetails();
        }

    }


}












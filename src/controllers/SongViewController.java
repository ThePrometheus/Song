package controllers;


import app.Application;
import app.Strings;
import model.Musician;
import model.MusicianSong;
import model.Song;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongViewController {
    private List<Song> dataSource;


    private Song currentSong ;

    private JList<Song> songList;

    private JPanel contentView ;

    private JLabel songNameLabel ;

    private JLabel albumNameLabel ;

    private JLabel authorLabel ;

    private JLabel musicianNameLabel ;
    private JLabel musicianLastNameLabel ;

    private JButton addMusician;

    private JLabel musicianShareLabel ;
    private JPanel songInfoPanel;
    private List<Musician> musicians ;


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

    private void repaintDetails()  {
        if (currentSong == null) return;

        songNameLabel.setText(currentSong.getName());

        long album_id = currentSong.getAlbum_id();

        String albumName = null;

        try{
            albumName = Application.self.albumRepository.getBy(album_id).getName();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        albumNameLabel.setText(albumName);
        authorLabel.setText(currentSong.getAuthor());


        //add at least one musician

       List<Musician> mList = new ArrayList<>();

       try{
           mList= Application.self.songService.getSongMusicians(getCurrentId());
       }catch(Exception e){
           e.printStackTrace();
       }

       musicianLastNameLabel.setText(mList.get(0).getLastName());
       musicianNameLabel.setText(mList.get(0).getName());
       long mus_id = mList.get(0).getId();
        double fee_share = 0;
        try {
            fee_share = Application.self.musicianSongRepository.getFee(mus_id,getCurrentId());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        musicianShareLabel.setText(String.valueOf(fee_share));

        for (int i=1;i<mList.size();i++){
            JPanel musNamePanel = new JPanel();
            JPanel musLastnamePanel = new JPanel();
            JPanel musFeePanel = new JPanel();
            JLabel musNameLabel = new JLabel("Name:");
            JLabel musLastnameLabel = new JLabel("Last name:");
            JLabel musFeeSharelabel = new JLabel("Fee:");
            musNameLabel.setText(mList.get(i).getName());
            musLastnameLabel.setText(mList.get(i).getLastName());
            long temp_id = mList.get(i).getId();
            double temp_fee_share = 0;
            try {
                temp_fee_share = Application.self.musicianSongRepository.getFee(temp_id,getCurrentId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            musFeeSharelabel.setText(String.valueOf(temp_fee_share));
            musNamePanel.add(musNameLabel);
            musLastnamePanel.add(musLastnameLabel);
            musFeePanel.add(musFeeSharelabel);
            //songInfoPanel.add(musNamePanel);
            //songInfoPanel.add(musLastnamePanel);
            //songInfoPanel.add(musFeePanel);







        }




    }


    private void didSelectListItem(ListSelectionEvent e) {
        int index = songList.getSelectedIndex();
        if (index < 0) return;
        currentSong = dataSource.get(index);

        repaintDetails();


    }

    private void addMusician(ActionEvent e) {
        musicians = Application.self.musicianService.all();
        Collections.sort(musicians,Musician.musComparator);
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












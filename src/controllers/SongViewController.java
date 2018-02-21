package controllers;


import app.Application;
import app.Strings;
import model.Song;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.ActionEvent;
import java.util.List;

public class SongViewController {
    private List<Song> dataSource ;

    private Song currentSong;

    private JList<Song> songList;

    private JPanel contentView = new JPanel();

    private JLabel songNameLabel= new JLabel();

   private JLabel albumNameLabel = new JLabel();

    private JLabel authorLabel= new JLabel();

    private JLabel  musicianNameLabel =new JLabel();
    private JLabel musicianLastNameLabel = new JLabel();

    private JLabel musicianShareLabel = new JLabel();
    private JButton newShareButton = new JButton();

    private JLabel musicianInstrumentLabel = new JLabel();





    public SongViewController(){

        reloadListData();

        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        songList.addListSelectionListener(e -> didSelectListItem(e));
        songList.setSelectedIndex(0);
        newShareButton.addActionListener(e -> editFeeMusician(e));



    }

    public JPanel getContentView(){
        return contentView;
    }


    public long getCurrentId(){
        int index = songList.getSelectedIndex();
        if(index <0) return -1;
        currentSong = dataSource.get(index);
        return currentSong.getId();
    }

    public void refresh(){
        reloadListData();
        repaintDetails();
    }

    private void reloadListData(){
        reloadData();

        DefaultListModel<Song> dlm = new DefaultListModel<>();
        for (Song s : dataSource){
            dlm.addElement(s);
            System.out.println("added song"+s.toString());
        }
        songList.setModel(dlm);


    }
    private void reloadData(){

       dataSource = Application.self.songService.all();



    }
    private void repaintDetails(){
        if (currentSong == null) return;
        songNameLabel.setText(currentSong.getName());
        albumNameLabel.setText(currentSong.toString());
        authorLabel.setText(currentSong.getAuthor());







    }






    private void didSelectListItem(ListSelectionEvent e){
        int index =   songList.getSelectedIndex();
        if (index <0)    return;
        currentSong = dataSource.get(index);

        repaintDetails();


    }

    private void editFeeMusician(ActionEvent e ){
        String newFeeStr  = JOptionPane.showInputDialog(null, Strings.MENU_SONG_CHANGE_SHARE);
        Double newFee = null;
        try {
            newFee = Double.parseDouble(newFeeStr);
        } catch(NumberFormatException ne){
            Application.showMessage(Strings.DIALOG_NUMBER_FORMAT_ERROR);
            ne.printStackTrace();
            return;
        }catch(NullPointerException e2){
            return ;
        }

    }







}

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

    private JList<Song> songLabel;

    private JPanel contentView = new JPanel();

    private JLabel songNameLabel= new JLabel();

   private JLabel albumNameLabel = new JLabel();

    private JLabel authorNameLabel= new JLabel();

    private JLabel  musicianNameLabel =new JLabel();

    private JLabel musicianFeelabel = new JLabel();
    private JButton musicianNewFeeButton = new JButton();

    private JLabel musicianInstrumentLabel = new JLabel();
    private JScrollPane ScrollList;


    public SongViewController(){
        reloadData();
        reloadListData();

        songLabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        songLabel.addListSelectionListener(e -> didSelectListItem(e));
        songLabel.setSelectedIndex(0);
        musicianNewFeeButton.addActionListener(e -> editFeeMusician(e));
        contentView.add(songLabel);



    }

    public JPanel getContentView(){
        return contentView;
    }
    public long getCurrentId(){
        int index = songLabel.getSelectedIndex();
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
        songLabel.setModel(dlm);


    }
    private void reloadData(){

       dataSource = Application.self.songService.all();



    }
    private void repaintDetails(){
        if (currentSong == null) return;
        songNameLabel.setText(currentSong.getName());
        albumNameLabel.setText(currentSong.toString());
        authorNameLabel.setText(currentSong.getAuthor());







    }






    private void didSelectListItem(ListSelectionEvent e){
        int index =   songLabel.getSelectedIndex();
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

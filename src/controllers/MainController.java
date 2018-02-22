package controllers;

import app.Application;
import app.Strings;

import javax.swing.*;
import java.awt.*;

/**
 * Created by root on 18.02.18.
 */
public class MainController {
    private JFrame frame ;


    public void createAndShowGUI(){
        //Create and set up the window.
        frame = new JFrame("Studio");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //min sizes
        frame.setMinimumSize(new Dimension(600, 400));

        setMenuBar();

        Application.self.songViewController = new SongViewController();
        frame.add(Application.self.songViewController.getContentView());

        //Display the window.
        frame.pack();
        frame.setVisible(true);


    }

    private void setMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        //song menu
        JMenu songMenu = new JMenu(Strings.MENU_SONG);

        JMenuItem songNewItem = new JMenuItem(Strings.MENU_SONG_NEW);


        songNewItem.addActionListener(e -> SongDetailsController.presentDialog(true));


        songMenu.add(songNewItem);
        JMenuItem songEditCurrent = new JMenuItem(Strings.MENU_SONG_EDIT);

        songEditCurrent.addActionListener(e -> SongDetailsController.presentDialog(false,
                Application.self.songViewController.getCurrentId()));
        songMenu.add(songEditCurrent);
        JMenuItem songMusicianShareMenu = new JMenuItem(Strings.MENU_MUSICIAN_SHARE);
        songMusicianShareMenu.addActionListener(e -> SongMusicianController.presentDialog(Application.self.songViewController.getCurrentId()));

        songMenu.add(songMusicianShareMenu);

        menuBar.add(songMenu);

        //help menu
        JMenu helpMenu = new JMenu(Strings.MENU_SONG_HELP);
        JMenuItem docsItem = new JMenuItem(Strings.MENU_SONG_DOCS);
        docsItem.addActionListener(e -> System.out.println("DOCS STUB"));
        helpMenu.add(docsItem);
        helpMenu.addSeparator();
        JMenuItem aboutItem = new JMenuItem(Strings.MENU_SONG_ABOUT_US);
        aboutItem.addActionListener(e -> System.out.println("ABOUT STUB"));
        helpMenu.add(aboutItem);

        menuBar.add(helpMenu);

        menuBar.setVisible(true);
        frame.setJMenuBar(menuBar);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;


import dominio.VideoFile;
import gui.VideoPlayer;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Ellet
 */
public class DragListener implements DropTargetListener,DragGestureListener {

    DefaultListModel playList;
    
    
    public DragListener(DefaultListModel playList){
        this.playList = playList;
    }
    
    
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable transfer = dtde.getTransferable();
        DataFlavor [] information = transfer.getTransferDataFlavors();
        for(DataFlavor data : information){
            try{
                if(data.isFlavorJavaFileListType()){
                    List<File> files = (List<File>) transfer.getTransferData(data);
                    for(File file : files){
                        if(FilenameUtils.getExtension(file.getName()).equals("mp4") || FilenameUtils.getExtension(file.getName()).equals("mp3")){
                            VideoFile song = new VideoFile(file);
                            song.setTitle(file.getName());
                            addSongToPlayList(song);
                        }
                    }
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private void addSongToPlayList(VideoFile song){
        playList.addElement(song);
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        
    }
 
    
}

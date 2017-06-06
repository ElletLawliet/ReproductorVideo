/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import dominio.VideoFile;

/**
 *
 * @author serbr
 */
public class TreeSong {
    private VideoFile videoFile;
    private TreeSong padre;
    private TreeSong hijoDerecho;
    private TreeSong hijoIzquierdo;

    public TreeSong(VideoFile videoFile) {
        this.videoFile = videoFile;
    }

    public VideoFile getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(VideoFile videoFile) {
        this.videoFile = videoFile;
    }

    public TreeSong getPadre() {
        return padre;
    }

    public void setPadre(TreeSong padre) {
        this.padre = padre;
    }

    public TreeSong getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(TreeSong hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public TreeSong getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(TreeSong hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author serbr
 */
public class Tree {
    private TreeSong raiz;
    
    public Tree(){
        raiz = null;
    }

    public TreeSong getRaiz() {
        return raiz;
    }

    public void setRaiz(TreeSong raiz) {
        this.raiz = raiz;
    }
    
    
    
    public void InicializarNodo(TreeSong nodo){
        this.InsertarNodo(nodo, this.raiz);
    }
    
    
    private void InsertarNodo(TreeSong nodo, TreeSong raiz){
        if(raiz == null){
            this.raiz = nodo;
        }
        else{
            if(nodo.getVideoFile().getTitle().charAt(0) < raiz.getVideoFile().getTitle().charAt(0)){
                if(raiz.getHijoIzquierdo() == null){
                    raiz.setHijoIzquierdo(nodo);
                }
                else{
                    InsertarNodo(nodo, raiz.getHijoIzquierdo());
                }
            }
            else{
                if(raiz.getHijoDerecho() == null){
                    raiz.setHijoDerecho(nodo);
                }
                else{
                    InsertarNodo(nodo,raiz.getHijoDerecho());
                }
            }
        }
    }
    
    public void preorden(TreeSong nodo){
        if(nodo != null){
            System.out.println(nodo.getVideoFile().getTitle());
            preorden(nodo.getHijoIzquierdo());
            preorden(nodo.getHijoDerecho());
        }
    }
    
    public void inorden(TreeSong nodo){
        if(nodo != null){
            inorden(nodo.getHijoIzquierdo());
            System.out.println(nodo.getVideoFile().getTitle());
            inorden(nodo.getHijoDerecho());
        }
    }
    
    public void postorden(TreeSong nodo){
        if(nodo != null){
            postorden(nodo.getHijoIzquierdo());
            postorden(nodo.getHijoDerecho());
            System.out.println(nodo.getVideoFile().getTitle());
        }
    }
    
    
    
}

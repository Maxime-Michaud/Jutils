/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.listeners;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

/**
 *
 * @author Maxime
 */
public class staticFunctions
{
    /**
     * Ajoute les Listener dans tous les éléments et leurs enfants si ils
     * sont des containers. 
     * <br />
     * Je devrais vraiment pas avoir a faire ça, mais
     * apparament un ingénieur chez Sun à décidé que permettre aux événements
     * d'être envoyer aux parents des composantes qui les reçoivent n'est pas
     * une bonne idée, donc au lieu de this.addKeyListener(new Listener);,
     * on à une méthode récursive de plusieurs lignes
     * @param c Conteneur sur lequel on veut ajouter le capslistener
     * @param l Listener a ajouter sur le frame
     */
    public static void addContainerListeners(Container c, KeyListener l)
    {
        for (Component component : c.getComponents())
        {
            if (component.isFocusable())
                component.addKeyListener(l);

            if (component instanceof Container)
                addContainerListeners((Container) component, l);
        }
    }

    /**
     * Ajoute un keystroke a l'inputmap du jFrame et exécute le runnable si le keystroke est détecté
     * @param f Frame sur lequel ajouter l'écouteur
     * @param k Keystroke à écouter
     * @param a Action à exécuter
     */
    public static void addToInputMap(JFrame f, KeyStroke k, Action a)
    {
        //Ajoute le keystroke au inputmap
        f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, k.hashCode());
        //Ajoute l'action
        f.getRootPane().getActionMap().put(k.hashCode(), a);

    }
    
    /**
     * Ajoute un keystroke a l'inputmap du jFrame et exécute le runnable si le keystroke est détecté
     * @param f Frame sur lequel ajouter l'écouteur
     * @param k Keystroke à écouter
     * @param r Action à exécuter
     */
    public static void addToInputMap(JFrame f, KeyStroke k, final Runnable r)
    {
        //Ajoute le keystroke au inputmap
        f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, k.hashCode());
        //Ajoute l'action
        f.getRootPane().getActionMap().put(k.hashCode(),
                new AbstractAction()
                {

                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        r.run();
                    }
                });

    }
}

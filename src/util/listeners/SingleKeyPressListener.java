/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Maxime
 */
/**
 * Écouteur sur une seule touche qui exécute une action lorsque la touche est
 * utilisée.
 */
public class SingleKeyPressListener extends KeyAdapter
{

    private final Runnable action;
    private final int Key;

    /**
     * Construit un singleKeyPressListener
     * <p>
     * @param Key Touche pour laquelle écouter
     * @param action Action à exécuter
     */
    public SingleKeyPressListener(int Key, Runnable action)
    {
        this.action = action;
        this.Key = Key;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == Key)
            action.run();
    }
}

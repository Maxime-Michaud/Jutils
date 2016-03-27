/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.listeners;

import java.awt.event.KeyEvent;

/**
 * Filtre a utiliser avec le filterKeyListener
 * @author Maxime
 */
public interface Filter
{
    public boolean filter(KeyEvent e);
}

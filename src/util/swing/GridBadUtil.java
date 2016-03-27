/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Maxime
 */
public class GridBadUtil
{
    /**
     * Ajoute une rangée dans l'affichage
     * <p>
     * @param container Conteneur pour les composantes
     * @param comp Composante a mettre dans la rangée. null permet d'insérer un
     * espace de la largeur d'une composante
     */
    static public void ajoutLigne(Container container, Component... comp)
    {
        for (int i = 0; i < comp.length; ++i)
        {
            if (comp[i] == null)
                comp[i] = new JLabel(" ");

            GridBagConstraints c = new GridBagConstraints();

            //Le dernier élément rempli la ligne
            if (i == (comp.length - 1))
            {
                c.gridwidth = GridBagConstraints.REMAINDER;
                c.insets.right = 25;
            }
            c.insets.top = 5;
            c.insets.left = 2;
            
            if (!(comp[i] instanceof JButton))
                c.fill = GridBagConstraints.HORIZONTAL;
            else
                c.fill = GridBagConstraints.NONE;
            
            c.weightx = 1.0 / comp.length;

            container.add(comp[i], c);
        }
    }
}

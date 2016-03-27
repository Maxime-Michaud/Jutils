/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.IO;

import javax.swing.ImageIcon;

/**
 *
 * @author Maxime
 */
public class Ressources
{

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     * source: http://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
     * @param path Filepath de l'image
     * @param description Description de l'icone
     * @return L'image, ou null si elle est introuvable
     */
    protected ImageIcon createImageIcon(String path, String description)
    {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null)
        {
            return new ImageIcon(imgURL, description);
        }
        else
        {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}

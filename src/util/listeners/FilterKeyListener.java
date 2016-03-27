/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Filtre l'entrée de l'utilisateur afin de ne permettre qu'une ou plusieurs touches
 * @author Maxime
 */
public class FilterKeyListener extends KeyAdapter
{
    final private SortedSet<Character> acceptable;
    
    private final ArrayList<Filter> filters;
    /**
     * Crée un filterKeyListener avec une liste de touches acceptables.
     * @param Keys Touches acceptables. Elles doivent etre en paires, et toutes les 
     * touches entre deux paires sont ajoutées (par example: a, z ajoute toutes les touches entre a et z.
     * pour en ajouter une seule, il faut faire a, a.
     * Si la plus petite touche est en premiere et la plus grosse en deuxième, rien ne se passe
     */
    public FilterKeyListener(char... Keys)
    {
        filters = new ArrayList<>();
        
        if (Keys.length % 2 != 0)
            throw new IllegalArgumentException("Le nombre de touche est invalide");
    
        acceptable = new TreeSet<>();
        
        //Ajoute les touches dans le set acceptable
        for (int i = 0; i < Keys.length; i += 2)
            for (int j = Keys[i]; j <= Keys[i + 1]; ++j)
                acceptable.add((char)j);
    }

    public void addFilter(Filter f)
    {
        filters.add(f);
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
        //Vérifie si l'entrée match un des charactères du filtre
        if (!acceptable.contains(e.getKeyChar()))
        {
            for (Filter f: filters) //Vérifie si l'entrée match un des filtres
                if (f.filter(e))
                    return;
            
            e.consume();
        }
    }

    
    
    
    
}

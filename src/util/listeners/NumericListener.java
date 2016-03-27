/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.listeners;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

/**
 * Version spécialisée du filterKeyListener qui ne permet d'entrer que des valeurs numériques 
 * @author Maxime
 */
public class NumericListener extends FilterKeyListener{
    NumericListener() {
        super('0', '9');
        
        //Filtre qui permet le point décimal
        addFilter(e -> {
            if (e.getKeyChar() != '.')
                return false;
            
            if (!(e.getComponent() instanceof JTextComponent))
                return false;
            
            JTextComponent c = (JTextComponent)e.getComponent();
            return !c.getText().contains(".");
        });
        
        //Permet les nombres négatifs
        addFilter( e -> {
            if (!(e.getComponent() instanceof JTextComponent)) 
                return false;
            
            JTextComponent c = (JTextComponent)e.getComponent();
            
            return (c.getCaretPosition() == 0 &&  //On est au début du textfield                   ET
                e.getKeyChar() == '-' &&          //Le charactère est un - (moins)                 ET
                (c.getText().length() == 0 ||     //Le textfield est vide                          (OU
                c.getText().charAt(0) != '-')) ;  //Le premier charactere du textfield n'est pas -  )       
        });
        
        //Corrige un bug avec l'évenement utilisé lorsqu'on tient la touche trop longtemps
        addFilter(e -> {
            if (!(e.getComponent() instanceof JTextComponent))
                return false;

            JTextComponent c = (JTextComponent)e.getComponent();
                
            try {
                while (c.getText().contains("-")
                        && c.getText().charAt(0) != '-') {
                    c.setText(c.getText(1, c.getText().length() - 2));
                }
            } 
            catch (BadLocationException ex) {
                c.setText("");
            }
            
            return false;
        });
    }
    
}

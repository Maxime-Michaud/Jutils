/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.IO;

import java.io.File;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Maxime
 */
final public class SafeIO {
    private SafeIO(){}; //La classe ne doit pas etre instantiée
    
    /**
     * Lis un entier dans un scanner quelconque. 
     * Correspond a readInt(sc, "", "")
     * @param sc Scanner qui va lire le prochain entier
     * @return La valeur lue dans le scanner. Null si le scanner ne contiens 
     * plus rien.
     * @throws IllegalStateException Si le scanner est dans un état interdit, 
     * rethrow son exception
     */
    public static Integer readInt(Scanner sc) {
        return readInt(sc, "", "");
    }
    
    /**
     * Lis un entier dans un scanner quelconque et affiche un message avant la
     * lecture. Correspond a readInt(sc, msg, "")
     * @param sc Scanner qui va lire le prochain entier
     * @param msg Message à afficher avant la lecture
     * @return La valeur lue dans le scanner. Null si le scanner ne contiens 
     * plus rien.
     * @throws IllegalStateException Si le scanner est dans un état interdit, 
     * rethrow son exception
     */ 
    public static Integer readInt(Scanner sc, String msg) {
        return readInt(sc, msg, "");
    }
    
    /**
     * Lis un entier dans un Scanner quelconque. Affiche un message avant la 
     * lecture. Si il y a une exception de type InputMismatchException, la 
     * méthode affiche errmsg et est appelée une autre fois.
     * @param sc Scanner dans lequel lire.
     * @param msg Message a afficher
     * @param errmsg Message en cas d'erreur
     * @return La valeur lue dans le scanner. Null si le scanner ne contiens 
     * plus rien.
     * @throws IllegalStateException Si le scanner est dans un état interdit, 
     * rethrow son exception
     */
    public static Integer readInt(Scanner sc, String msg, String errmsg) {
        try {
            System.out.print(msg);
            return sc.nextInt();
        }
        catch (InputMismatchException e)
        {
            System.out.print(errmsg);
            return readInt(sc, msg, errmsg);
        }
        catch (NoSuchElementException e)
        {
            System.err.println("Aucun element ne se trouve dans le scanner!");
            return null;
        }
        catch (IllegalStateException e)
        {
            throw e;
        }
    }
    
    /**
     * Lis un float dans un scanner quelconque. 
     * Correspond a readFloat(sc, "", "")
     * @param sc Scanner qui va lire le prochain float
     * @return La valeur lue dans le scanner. Null si le scanner ne contiens 
     * plus rien.
     * @throws IllegalStateException Si le scanner est dans un état interdit, 
     * rethrow son exception
     */
    public static Float readFloat(Scanner sc) {
        return readFloat(sc, "", "");
    }
    
    /**
     * Lis un Float dans un scanner quelconque et affiche un message avant la
     * lecture. Correspond a readFloat(sc, msg, "")
     * @param sc Scanner qui va lire le prochain Float
     * @param msg Message à afficher avant la lecture
     * @return La valeur lue dans le scanner. Null si le scanner ne contiens 
     * plus rien.
     * @throws IllegalStateException Si le scanner est dans un état interdit, 
     * rethrow son exception
     */ 
    public static Float readFloat(Scanner sc, String msg) {
        return readFloat(sc, msg, "");
    }
    
    /**
     * Lis un Float dans un Scanner quelconque. Affiche un message avant la 
     * lecture. Si il y a une exception de type InputMismatchException, la 
     * méthode affiche errmsg et est appelée une autre fois.
     * @param sc Scanner dans lequel lire.
     * @param msg Message a afficher
     * @param errmsg Message en cas d'erreur
     * @return La valeur lue dans le scanner. Null si le scanner ne contiens 
     * plus rien.
     * @throws IllegalStateException Si le scanner est dans un état interdit, 
     * rethrow son exception
     */
    public static Float readFloat(Scanner sc, String msg, String errmsg) {
        try {
            System.out.print(msg);
            return sc.nextFloat();
        }
        catch (InputMismatchException e)
        {
            System.out.print(errmsg);
            return readFloat(sc, msg, errmsg);
        }
        catch (NoSuchElementException e)
        {
            System.err.println("Aucun element ne se trouve dans le scanner!");
            return null;
        }
        catch (IllegalStateException e)
        {
            throw e;
        }
    }
    
    /**
     * Pose une question qui peut prendre certains charactères en entrée.
     * @param sc Scanner dans lequel lire l'entrée
     * @param Q Question a poser
     * @param chars Réponses valides
     * @return La réponse. Est Null si l'utilisateur refuse de répondre 
     *  plus que 6 fois 
     */
    public static Character question(Scanner sc, String Q, char chars[])
    {
        System.out.println(Q);
        String in = sc.nextLine();
        
        for (char c : chars)
            if (Character.toLowerCase(c) == Character.toLowerCase(in.charAt(0)))
                return c;
        
        //La question n'a pas été répondue, donc on redemande
        System.out.println("");
        return questionLimite(sc, Q, chars);
    }    
    
    private static Character questionLimite(Scanner sc, String Q, char chars[])
    {
        for (int i = 0; i < 5; ++i)
        {
            System.out.println(Q);
            String in = sc.nextLine();

            for (char c : chars)
                if (Character.toLowerCase(c) == Character.toLowerCase(in.charAt(0)))
                    return c;

            //La question n'a pas été répondue, donc on redemande
            System.out.println("");   
        }
        return null;
    }
    
    /**
     * Affiche un message et permet a l'utilisateur d'entrer un message
     * @param sc Scanner à utiliser pour la lecture. Ne doit pas etre null
     * @param msg message a afficher
     * @return Une chaine de characteres saisie par l'utilisateur. 
     * @throws NullPointerException si le scanner est null
     */
    public static String readString(Scanner sc, String msg)
    {
        System.out.println(msg);
        return sc.nextLine();
    }
    
    /**
     * Ouvre un fichier en lecture. 
     * @param nom nom du fichier
     * @param msgErr message a afficher si il y a une erreur
     * @return le fichier ou null si il y a une erreur, comme :
     *      nom null
     *      le fichier n'existe pas
     *      le "fichier" est un repertoire 
     */
    public static File ouvrirFichierLecture(String nom, String msgErr)
    {
        File f = ouvrirFichier(nom);
        
        if (f == null ||     //Si le nom étais null, f est null 
            !f.exists() ||   //f doit exister puisquil sera utilisé en lecture 
            f.isDirectory()) //f ne doit pas etre un repertoire, car il sera probablement utilisé comme un fichier
        {
            if (msgErr != null)
                System.err.println(msgErr);
            
            return null;
        }
        
        return f;
        
    }
    
    /**
     * Ouvre un fichier avec le nom donné
     * @param nom Nom du fichier
     * @return Fichier ouvert ou null si le nom est null
     */
    public static File ouvrirFichier(String nom)
    {
        if (nom == null) return null;
        
        return new File(nom);   
    }
}

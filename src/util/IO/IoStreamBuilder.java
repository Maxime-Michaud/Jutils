/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.IO;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 *
 * @author Maxime
 */
public class IoStreamBuilder
{
    /**
     * Construit un scanner a partir d'un bufferedInputStream, qui à été 
     * construit à partir d'un FileInputStream, qui est construit a partir
     * d'un File
     * @param file chemin (relatif ou absolu) du fichier
     * @return Le scanner construit
     * @throws FileNotFoundException Si le fileInputStream throw l'exception
     */
    public static Scanner sequentialBufferedFileInputScanner(String file) 
            throws FileNotFoundException
    {
        return new Scanner( 
                new BufferedInputStream(
                    new FileInputStream( 
                        new File(file))));
    }
    
       /**
     * Construit un scanner a partir d'un bufferedInputStream, qui à été 
     * construit à partir d'un FileInputStream, qui est construit a partir
     * d'un File
     * @param file fichier a ouvrir
     * @return Le scanner construit
     * @throws FileNotFoundException Si le fileInputStream throw l'exception
     */
    public static Scanner sequentialBufferedFileInputScanner(File file) 
            throws FileNotFoundException
    {
        return new Scanner( 
                new BufferedInputStream(
                    new FileInputStream(file)));
    }
    
    /**
     * Construit un scanner a partir d'un bufferedInputStream, qui à été 
     * construit à partir d'un FileInputStream, qui est construit a partir
     * d'un File. 
     * Si il y a une exception du type FileNotFoundException, l'exception est 
     * attrapée et errorString est utilisé pour afficher l'exception. errorString 
     * peut etre un formatString qui prend un parametre (le message de l'exception).
     * Il sera affiché dans os. 
     * @param file chemin (relatif ou absolu) du fichier
     * @param os Flux de sortie dans lequel on veux afficher le message d'erreur.
     * Si null, deviens System.err
     * @param errorString Formatstring a partir duquel on obtiens le message d'erreur
     * a afficher. Si null, affiche le message de l'exception ( = %1$s).
     * @return Le scanner construit ou Null si il y a eu une exception.
     */
    public static Scanner sequentialBufferedFileInputScanner(String file, OutputStream os, String errorString)  
    {
        try 
        {
            return IoStreamBuilder.sequentialBufferedFileInputScanner(file);
        }
        catch (FileNotFoundException e)
        {
            if (os == null)
                os = System.err;
            
            if (errorString == null)
                errorString = "%1$s";
            
            try {
            os.write(String.format(errorString, e.getMessage()).
                    getBytes(Charset.forName("UTF-8")));
            }
            //Cette exception ne devrais jamais se produire, puisque tout est initialisé 
            //et que UTF-8 devrais etre disponible partout
            catch (IOException ioe) 
            {
                System.err.println(ioe.getMessage());
            }
            
            return null;
        }
    }
}

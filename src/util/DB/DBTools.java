/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gère les accès à une base de donnée.
 * @author Maxime
 */
public class DBTools
{
    final private String url;
    final private String username;
    final private String password;
    final private String driver;

    /**
     * Construit l'accesseur a la base de donnée
     * @param url Url de la bd.
     * @param username Username a utiliser pour se connecter a la bd
     * @param password Password a utiliser pour se connecter a la bd
     * @param driver jbdc a utiliser pour se connecter a la bd
     */
    public DBTools(String url, String username, String password, String driver)
    {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driver = driver;
    }
    
    /**
     * @return the url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @return the driver
     */
    public String getDriver()
    {
        return driver;
    }
    
    /**
     * Crée une connection à la base de donnée
     * @return La connection
     * @throws ClassNotFoundException Si le driver est introuvable
     * @throws InstantiationException Si le driver est impossible a instancier
     * @throws IllegalAccessException Si le programme n'a pas la permission d'acceder au driver
     * @throws SQLException Si la connection échoue
     */
    public Connection createConnection() 
            throws ClassNotFoundException, 
            InstantiationException, 
            IllegalAccessException, 
            SQLException 
    {
        /* Instantiation du driver de la base de données */
        Class.forName(driver).newInstance();

        /* La connection avec username et password */
        return DriverManager.getConnection(this.url, this.username,
                        this.password);        
    }
}

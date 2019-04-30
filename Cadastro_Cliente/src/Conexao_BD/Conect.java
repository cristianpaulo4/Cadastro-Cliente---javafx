/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao_BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author montero
 */
public class Conect {
    
    private final String url="jdbc:mysql://localhost:3306/Cliente";
    private final String usuario = "cristian1";
    private final String senha = "nunes@142536";


public Connection conectar(){
    try{
        Connection con = DriverManager.getConnection(url, usuario, senha);
        System.out.println("Conectado");
        
        return con;
       
                
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Erro ao conectar:\n" + e);
        
        
    }
        return null;
    
    
    
}

    
}

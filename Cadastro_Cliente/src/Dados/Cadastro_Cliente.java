/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import Conexao_BD.Conect;
import Objeto.CadastrosController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author montero
 */
public class Cadastro_Cliente extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
      CadastrosController cadControl = new CadastrosController();
      cadControl.AbrirTela();
      
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

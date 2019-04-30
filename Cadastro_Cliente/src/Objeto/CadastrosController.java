/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objeto;

import Conexao_BD.Conect;
import Dados.Cliente;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author montero
 */
public class CadastrosController implements Initializable {

    @FXML
    private TextField cxCpf;
    @FXML
    private TextField cxNome;
    @FXML
    private TextField cxTel;
    @FXML
    private TextField cxEmail;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;
    @FXML
    private TableView<Cliente> tablist;
    @FXML
    private TableColumn<Cliente, Integer> colCpf;
    @FXML
    private TableColumn<Cliente, String> colNome;
    @FXML
    private TableColumn<Cliente, String> colTel;
    @FXML
    private TableColumn<Cliente, String> colEmail;
    int valor;
    
    ArrayList<Cliente> listaCliente = new ArrayList<>();

    Cliente cli = new Cliente();
    Cliente cl = new Cliente();
    @FXML
    private TextField cxPesquisar;
    @FXML
    private TableColumn<Cliente, Integer> colCodigo;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listar();

        } catch (SQLException ex) {
            Logger.getLogger(CadastrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AbrirTela() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Cadastros.fxml"));
        Conect con = new Conect();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        con.conectar();

    }

    @FXML
    private void limpar() {
        cxCpf.setText("");
        cxNome.setText("");
        cxTel.setText("");
        cxEmail.setText("");
        btnSalvar.setText("Novo");
        bloquear(false);
        btnCancelar.setDisable(true);

    }

  
    @FXML
    private void cadCliente(MouseEvent event) throws SQLException {

        if (btnSalvar.getText().equals("Salvar")) {

            cl.setCPF((cxCpf.getText()));
            cl.setNome(cxNome.getText());
            cl.setTelefone(cxTel.getText());
            cl.setEmail(cxEmail.getText());

            cl.cadastrar(cl);
            btnSalvar.setText("Novo");
            listar();
            limpar();
           
        }
        if (btnSalvar.getText().equals("Alterar")) {
            cl.setCPF(cxCpf.getText());
            cl.setNome(cxNome.getText());
            cl.setTelefone(cxTel.getText());
            cl.setEmail(cxEmail.getText());
            
            cli.Editar(valor, cl);
            btnSalvar.setText("Salvar");
            listar();
            limpar();
        }
        if (btnSalvar.getText().equals("Novo")) {
            limpar();
            bloquear(true);
            btnSalvar.setText("Salvar");
            
        }

        btnCancelar.setDisable(false);

    }

    public void listar() throws SQLException {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("IdCliente"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        ObservableList<Cliente> obs = FXCollections.observableArrayList(cli.listar());

        tablist.setItems(obs);

    }
    
    

    @FXML
    private void ativar_cancelar(KeyEvent event) {
        btnCancelar.setDisable(false);

    }

    @FXML
    public void excluir() throws SQLException {
        Cliente cli = new Cliente();
        int cpf = tablist.getSelectionModel().getSelectedItem().getIdCliente();
        cli.Excluir(cpf);
        listar();
        limpar();
        btnSalvar.setText("Novo");

    }

    @FXML
    public void selecionarCliente() throws SQLException {
        Cliente cliente = new Cliente();
        int Cod = tablist.getSelectionModel().getSelectedItem().getIdCliente();
        cliente = cliente.buscar(Cod);

        cxCpf.setText((cliente.getCPF()));
        cxNome.setText(cliente.getNome());
        cxTel.setText(cliente.getTelefone());
        cxEmail.setText(cliente.getEmail());
        bloquear(false);
        btnSalvar.setText("Novo");

    }

    public void bloquear(Boolean op) {
        cxCpf.setEditable(op);
        cxNome.setEditable(op);
        cxTel.setEditable(op);
        cxEmail.setEditable(op);

    }

    @FXML
    public void editar() {
        bloquear(true);
        valor = tablist.getSelectionModel().getSelectedItem().getIdCliente();
        btnSalvar.setText("Alterar");
        

    }

    @FXML
    private void buscarNome(KeyEvent event) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("IdCliente"));        
        colCpf.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));        
        
        try {
            int codigo = Integer.parseInt(cxPesquisar.getText());
            listaCliente = cli.buscarCodigo(codigo);
            
        } catch (Exception e) {
            
            listaCliente = cli.buscarNome(cxPesquisar.getText());
            
            
        }
        ObservableList<Cliente> obs = FXCollections.observableArrayList(listaCliente);

        tablist.setItems(obs);
        
    }

   
    private void teste(MouseEvent event) {
    }

}

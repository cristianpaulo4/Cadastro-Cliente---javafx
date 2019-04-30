/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import Conexao_BD.Conect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author montero
 */
public class Cliente {
    private int IdCliente;
    private String CPF;
    private String nome;
    private String telefone;
    private String email;

    Conect con = new Conect();
    
       public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
   }
      
    
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
  
    
    public void cadastrar(Cliente cli){
        
        try{
            PreparedStatement cad = con.conectar().prepareStatement("insert into dadosClientes (CPF, nome, telefone, email)values(?,?,?,?)");
            
            cad.setString(1, cli.getCPF());
            cad.setString(2, cli.getNome());
            cad.setString(3, cli.getTelefone());
            cad.setString(4, cli.getEmail());
            cad.execute();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente!" +e);
        }
        
    }
    
    public ArrayList<Cliente> listar() throws SQLException{
         ArrayList <Cliente> listaCliente = new ArrayList<>();
         try{   
         PreparedStatement list = con.conectar().prepareStatement("select * from dadosClientes");
            ResultSet res = list.executeQuery();
            
            while(res.next()){
                Cliente cliente = new Cliente();
                
                cliente.setIdCliente(res.getInt(1));
                cliente.setCPF(res.getString(2));
                cliente.setNome(res.getString(3));
                cliente.setTelefone(res.getString(4));
                cliente.setEmail(res.getString(5));
                
                listaCliente.add(cliente);
                         
                
            }
            return listaCliente;
        
        
    }catch(SQLException e){
         JOptionPane.showMessageDialog(null, "Erro ao listar cliente:\n" + e);
     
    }
         return null;
         
    }
    
    public void Editar(int codCliente, Cliente cli) throws SQLException{
        
        PreparedStatement editar = con.conectar().prepareStatement ("update dadosClientes set CPF=?, nome=?, telefone=?, email=? where IdCliente="+codCliente);
        
        editar.setString(1, cli.getCPF());
        editar.setString(2, cli.getNome()); 
        editar.setString(3, cli.getTelefone());
        editar.setString(4, cli.getEmail());
        editar.execute();
        
    }
    
    public void Excluir(int codCliente){ 
        try{
        PreparedStatement excluir = con.conectar().prepareStatement ("delete from dadosClientes where IdCliente="+codCliente);
        excluir.execute();
        JOptionPane.showMessageDialog(null, "Registro excluuido com sucesso!");
             
        
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Erro na exclusão dos registro." + e);
        
    }
    
}
    public Cliente buscar(int codCliente) throws SQLException{
        
        try{
            PreparedStatement buscar = con.conectar().prepareStatement ("select * from dadosClientes where IdCliente="+codCliente);
            ResultSet res = buscar.executeQuery();
            
                while(res.next()){
                    Cliente cli = new Cliente();
                    
                    cli.setIdCliente(res.getInt(1));
                    cli.setCPF(res.getString(2));
                    cli.setNome(res.getString(3));
                    cli.setTelefone(res.getString(4));
                    cli.setEmail(res.getString(5));
                    
                    return cli;
                    
                }
                
            
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar cliente:\n" + ex, "Erro", 0);
        }
        return null;
            
            
            
        }

  public ArrayList<Cliente> buscarNome (String nome){
        ArrayList <Cliente> listaCliente = new ArrayList<>();
         try{   
         PreparedStatement list = con.conectar().prepareStatement("select *from dadosClientes where nome like '" +nome+"%'");
            ResultSet res = list.executeQuery();
            
            while(res.next()){
                Cliente cliente = new Cliente();
                
                cliente.setIdCliente(res.getInt(1));
                cliente.setCPF(res.getString(2));
                cliente.setNome(res.getString(3));
                cliente.setTelefone(res.getString(4));
                cliente.setEmail(res.getString(5));
                
                listaCliente.add(cliente);
                         
                
            }
            return listaCliente;
            
          }catch(SQLException e){
         JOptionPane.showMessageDialog(null, "Erro ao listar cliente:\n" + e);
     
    }
         return null;
      
      
  }
        
    // buscar por codigo
  public ArrayList<Cliente> buscarCodigo (int codigo){
        ArrayList <Cliente> listaCliente = new ArrayList<>();
         try{   
         PreparedStatement list = con.conectar().prepareStatement("select *from dadosClientes where idCliente like '" +codigo+"%'");
            ResultSet res = list.executeQuery();
            
            while(res.next()){
                Cliente cliente = new Cliente();
                
                cliente.setIdCliente(res.getInt(1));
                cliente.setCPF(res.getString(2));
                cliente.setNome(res.getString(3));
                cliente.setTelefone(res.getString(4));
                cliente.setEmail(res.getString(5));
                
                listaCliente.add(cliente);
                         
                
            }
            return listaCliente;
            
          }catch(SQLException e){
         JOptionPane.showMessageDialog(null, "Erro ao listar cliente:\n" + e);
     
    }
         return null;
      
  }
  
  
  
  
  
    }
    
    

    
    


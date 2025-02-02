/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Caio Cezar Dias
 */
public class Funcionario extends Pessoa implements Serializable {
    private String cargo;
    private String password;
    private float salario;
    private boolean habilitado;
    private Timestamp created_at;
    private Timestamp updated_at;
    
    public Funcionario(){
        
    }
 
    public Funcionario(int id, String nome, String cpf, String endereco, String telefone, String cargo, String password, float salario, boolean habilitado, Timestamp created_at, Timestamp updated_at) {
        super(id, nome, cpf, endereco, telefone);
        setCargo(cargo);
        setPassword(password);
        setSalario(salario);
        setHabilitado(habilitado);
        setCreated_at(created_at);
        setUpdated_at(updated_at);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}

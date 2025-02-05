/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Unidade;

/**
 *
 * @author Rafae
 */
public interface InterfaceUnidade extends Remote{
    void inserirUnidade(Unidade unidade) throws RemoteException;

    void atualizarUnidade(Unidade unidade) throws RemoteException;

    void desativarUnidade(int id) throws RemoteException;

    Unidade obterUnidade(int id) throws RemoteException;
    
    List<Unidade> obterUnidade(List<Integer> ids) throws RemoteException;

    List<Unidade> buscarUnidadePorNome(String nome) throws RemoteException;
}

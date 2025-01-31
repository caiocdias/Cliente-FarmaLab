/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import controller.InterfaceCliente;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Cliente;
/**
 *
 * @author Isabely
 */
public class GerenciarClientePanel extends javax.swing.JPanel {

    DefaultListModel MODELO;
    int Enter = 0;
    List<Cliente> clientes = new ArrayList<>();
    Integer [] ids;
    
    public GerenciarClientePanel() {
        initComponents();
        Lista.setVisible(false);
        MODELO = new DefaultListModel();
        Lista.setModel(MODELO);
    }
    
    public void ListaDePesquisa() {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceCliente icliente = (InterfaceCliente) registro.lookup("Cliente");
            clientes = icliente.buscarClientesPorNome(PesquisaNome.getText());

            MODELO.removeAllElements();
            ids = new Integer[clientes.size()];
            
            for (int i = 0; i < clientes.size(); i++) {
                Cliente cliente = clientes.get(i);
                MODELO.addElement(cliente.getNome());
                ids[i] = cliente.getId();
            }
            
            Lista.setVisible(!clientes.isEmpty());
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + e.getMessage());
        }
    }

    public void MostraPesquisa() {
        int indice = Lista.getSelectedIndex();
        if (indice >= 0) {
            try{
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceCliente icliente = (InterfaceCliente) registro.lookup("Cliente");
                Cliente cliente = icliente.obterCliente(ids[indice], null);
                if (cliente != null) {
                    nomeField.setText(cliente.getNome());
                    CpfField.setText(cliente.getCpf());
                    telefoneField.setText(cliente.getTelefone());
                    enderecoField.setText(cliente.getEndereco());
                }
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + e.getMessage());
            }
        }
    }
    
    private void limparCampos() {
        nomeField.setText("");
        CpfField.setText("");
        telefoneField.setText("");
        enderecoField.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        PesquisaNome = new javax.swing.JTextField();
        Lista = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nomeField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CpfField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        telefoneField = new javax.swing.JTextField();
        enderecoField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        confirmarButton = new javax.swing.JButton();
        excluirButton = new javax.swing.JButton();

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N

        PesquisaNome.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        PesquisaNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisaNomeActionPerformed(evt);
            }
        });
        PesquisaNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PesquisaNomeKeyReleased(evt);
            }
        });

        Lista.setBorder(null);
        Lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ListaListaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PesquisaNome)
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Lista, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(PesquisaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(Lista, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Edição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel6.setText("Nome:");

        nomeField.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel4.setText("CPF:");

        CpfField.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel5.setText("Telefone:");

        telefoneField.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        enderecoField.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel3.setText("Endereço:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enderecoField))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(CpfField, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(telefoneField, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                            .addComponent(nomeField))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CpfField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(telefoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(enderecoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        confirmarButton.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        confirmarButton.setForeground(new java.awt.Color(51, 102, 0));
        confirmarButton.setText("Confirmar edição");
        confirmarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarButtonActionPerformed(evt);
            }
        });

        excluirButton.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        excluirButton.setForeground(new java.awt.Color(255, 51, 51));
        excluirButton.setText("Excluir cliente");
        excluirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(confirmarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(excluirButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmarButton)
                    .addComponent(excluirButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PesquisaNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomeActionPerformed
        Lista.setVisible(false);
    }//GEN-LAST:event_PesquisaNomeActionPerformed

    private void ListaListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaListaMousePressed
        MostraPesquisa();
        Lista.setVisible(false);
    }//GEN-LAST:event_ListaListaMousePressed

    private void confirmarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarButtonActionPerformed
        int indice = Lista.getSelectedIndex();
        if (indice >= 0) {
            try {
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceCliente icliente = (InterfaceCliente) registro.lookup("Cliente");
                
                Cliente cliente = new Cliente();
                cliente.setId(ids[indice]);
                cliente.setNome(nomeField.getText());
                cliente.setCpf(CpfField.getText());
                cliente.setTelefone(telefoneField.getText());
                cliente.setEndereco(enderecoField.getText());
                
                icliente.atualizarCliente(cliente);
                JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
                limparCampos();
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum cliente selecionado!");
        }
    }//GEN-LAST:event_confirmarButtonActionPerformed

    private void excluirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirButtonActionPerformed
        int indice = Lista.getSelectedIndex();
        if (indice >= 0) {
            int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                try {
                    Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                    InterfaceCliente icliente = (InterfaceCliente) registro.lookup("Cliente");
                    
                    icliente.desativarCliente(ids[indice]);
                    JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
                    limparCampos();
                    ListaDePesquisa(); // Atualiza a lista após a exclusão
                } catch (RemoteException | NotBoundException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum cliente selecionado!");
        }
    }//GEN-LAST:event_excluirButtonActionPerformed

    private void PesquisaNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeKeyReleased
        ListaDePesquisa();
    }//GEN-LAST:event_PesquisaNomeKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CpfField;
    private javax.swing.JList<String> Lista;
    private javax.swing.JTextField PesquisaNome;
    private javax.swing.JButton confirmarButton;
    private javax.swing.JTextField enderecoField;
    private javax.swing.JButton excluirButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField nomeField;
    private javax.swing.JTextField telefoneField;
    // End of variables declaration//GEN-END:variables
}

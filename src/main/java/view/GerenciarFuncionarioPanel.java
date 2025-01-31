/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import controller.InterfaceFuncionario;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Funcionario;

/**
 *
 * @author Isabely
 */
public class GerenciarFuncionarioPanel extends javax.swing.JPanel {

    DefaultListModel MODELO;
    List<Funcionario> funcionarios = new ArrayList<>();
    Integer [] ids;
    
    public GerenciarFuncionarioPanel() {
        initComponents();
        Lista.setVisible(false);
        MODELO = new DefaultListModel();
        Lista.setModel(MODELO);
    }

    public void ListaDePesquisa() {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceFuncionario ifuncionario = (InterfaceFuncionario) registro.lookup("Funcionario");
            funcionarios = ifuncionario.buscarFuncionariosPorNome(PesquisaNome.getText());

            MODELO.removeAllElements();
            ids = new Integer[funcionarios.size()];
            
            for (int i = 0; i < funcionarios.size(); i++) {
                Funcionario funcionario = funcionarios.get(i);
                MODELO.addElement(funcionario.getNome());
                ids[i] = funcionario.getId();
            }
            
            Lista.setVisible(!funcionarios.isEmpty());
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar funcionario: " + e.getMessage());
        }
    }

    public void MostraPesquisa() {
        int indice = Lista.getSelectedIndex();
        if (indice >= 0) {
            try{
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceFuncionario ifuncionario = (InterfaceFuncionario) registro.lookup("Funcionario");
                Funcionario funcionario = ifuncionario.obterFuncionario(ids[indice], null);
                if (funcionario != null) {
                    nomeField.setText(funcionario.getNome());
                    CpfField.setText(funcionario.getCpf());
                    telefoneField.setText(funcionario.getTelefone());
                    enderecoField.setText(funcionario.getEndereco());
                    cargoComboBox1.setSelectedItem(funcionario.getCargo());
                    senhaTextPane3.setText(funcionario.getPassword());
                    salarioTextPane2.setText(String.valueOf(funcionario.getSalario()));
                }
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar funcionario: " + e.getMessage());
            }
        }
    }
    
    private void limparCampos() {
        nomeField.setText("");
        CpfField.setText("");
        telefoneField.setText("");
        enderecoField.setText("");
        cargoComboBox1.setSelectedIndex(0);
        senhaTextPane3.setText("");
        salarioTextPane2.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nomeField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CpfField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        telefoneField = new javax.swing.JTextField();
        enderecoField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        cargoComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        salarioTextPane2 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        senhaTextPane3 = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        confirmarButton = new javax.swing.JButton();
        excluirButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        PesquisaNome = new javax.swing.JTextField();
        Lista = new javax.swing.JList<>();

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

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel7.setText("Cargo: ");

        cargoComboBox1.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        cargoComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Funcionário comum", "Administrador" }));

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel8.setText("Salário:");

        salarioTextPane2.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(salarioTextPane2);

        senhaTextPane3.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jScrollPane3.setViewportView(senhaTextPane3);

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel9.setText("Senha:");

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
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
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
                                        .addComponent(telefoneField, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
                                    .addComponent(nomeField)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(cargoComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(cargoComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
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
        excluirButton.setText("Desabilitar funcionário");
        excluirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirButtonActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar funcionário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N

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
                .addContainerGap(104, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(Lista, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(excluirButton)
                    .addComponent(confirmarButton))
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void confirmarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarButtonActionPerformed
        int indice = Lista.getSelectedIndex();
        if (indice >= 0) {
            try {
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceFuncionario ifuncionario = (InterfaceFuncionario) registro.lookup("Funcionario");
                
                Funcionario funcionario = new Funcionario();
                funcionario.setId(ids[indice]);
                funcionario.setNome(nomeField.getText());
                funcionario.setCpf(CpfField.getText());
                funcionario.setTelefone(telefoneField.getText());
                funcionario.setEndereco(enderecoField.getText());
                funcionario.setCargo(cargoComboBox1.getName());
                funcionario.setPassword(senhaTextPane3.getText());
                float salario = Float.parseFloat(salarioTextPane2.getText());
                funcionario.setSalario(salario);
                funcionario.setHabilitado(true);
                
                ifuncionario.atualizarFuncionario(funcionario);
                JOptionPane.showMessageDialog(null, "Funcionario atualizado com sucesso!");
                limparCampos();
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar funcionario: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum funcionario selecionado!");
        }
    }//GEN-LAST:event_confirmarButtonActionPerformed

    private void excluirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirButtonActionPerformed
        int indice = Lista.getSelectedIndex();
        if (indice >= 0) {
            int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este funcionário?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                try {
                    Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                    InterfaceFuncionario ifuncionario = (InterfaceFuncionario) registro.lookup("Funcionario");
                    
                    ifuncionario.desativarFuncionario(ids[indice]);
                    JOptionPane.showMessageDialog(null, "Funcionario excluído com sucesso!");
                    limparCampos();
                    ListaDePesquisa();
                } catch (RemoteException | NotBoundException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir funcionario: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum funcionario selecionado!");
        }
    }//GEN-LAST:event_excluirButtonActionPerformed

    private void PesquisaNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomeActionPerformed
        Lista.setVisible(false);
    }//GEN-LAST:event_PesquisaNomeActionPerformed

    private void PesquisaNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeKeyReleased
        ListaDePesquisa();
    }//GEN-LAST:event_PesquisaNomeKeyReleased

    private void ListaListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaListaMousePressed
        MostraPesquisa();
        Lista.setVisible(false);
    }//GEN-LAST:event_ListaListaMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CpfField;
    private javax.swing.JList<String> Lista;
    private javax.swing.JTextField PesquisaNome;
    private javax.swing.JComboBox<String> cargoComboBox1;
    private javax.swing.JButton confirmarButton;
    private javax.swing.JTextField enderecoField;
    private javax.swing.JButton excluirButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nomeField;
    private javax.swing.JTextPane salarioTextPane2;
    private javax.swing.JTextPane senhaTextPane3;
    private javax.swing.JTextField telefoneField;
    // End of variables declaration//GEN-END:variables
}

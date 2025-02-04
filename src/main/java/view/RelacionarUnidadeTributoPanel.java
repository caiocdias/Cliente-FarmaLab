/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import controller.InterfaceTributo;
import controller.InterfaceUnidade;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.List;
import java.rmi.Remote;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import model.Tributo;
import model.Unidade;




/**
 *
 * @author thaissa-fernandes
 */

public class RelacionarUnidadeTributoPanel extends javax.swing.JPanel {

    DefaultListModel MODELOunidade;
    List<Unidade> unidades = new ArrayList<>();
    Integer [] idsUnidade;
    
    DefaultListModel MODELOtributo;
    List<Tributo> tributos = new ArrayList<>();
    Integer [] idsTributo;
    
    public RelacionarUnidadeTributoPanel() {
        initComponents();
        ListaUnidade.setVisible(false);
        MODELOunidade = new DefaultListModel();
        ListaUnidade.setModel(MODELOunidade);
        
        ListaTributo.setVisible(false);
        MODELOtributo = new DefaultListModel();
        ListaTributo.setModel(MODELOtributo);
    }
    
    public Unidade ListaDePesquisaUnidade() {
        Unidade unidade = null;
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceUnidade iunidade = (InterfaceUnidade) registro.lookup("Unidade");
            unidades = iunidade.buscarUnidadePorNome(PesquisaNomeUnidade.getText());

            MODELOunidade.removeAllElements();
            idsUnidade = new Integer[unidades.size()];
            
            for (int i = 0; i < unidades.size(); i++) {
                unidade = unidades.get(i);
                MODELOunidade.addElement(unidade.getNome());
                idsUnidade[i] = unidade.getId();
            }
            
            ListaUnidade.setVisible(!unidades.isEmpty());
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar unidades: " + e.getMessage());
        }
        return unidade;
    }
    
    public Tributo ListaDePesquisaTributo() {
        Tributo tributo = null;
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceTributo itributo = (InterfaceTributo) registro.lookup("Tributo");
            tributos = itributo.buscarTributoPorNome(PesquisaNomeTributo.getText());

            MODELOtributo.removeAllElements();
            idsTributo = new Integer[tributos.size()];
            
            for (int i = 0; i < tributos.size(); i++) {
                tributo = tributos.get(i);
                MODELOtributo.addElement(tributo.getNome_imposto());
                idsTributo[i] = tributo.getId();
            }
            
            ListaTributo.setVisible(!tributos.isEmpty());
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar tributo: " + e.getMessage());
        }
        return tributo;
    }
    
    public Unidade MostraPesquisaUnidade() {
        Unidade unidade = null;
        int indiceUnidade = ListaUnidade.getSelectedIndex();
        if (indiceUnidade >= 0) {
            try{
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceUnidade iunidade = (InterfaceUnidade) registro.lookup("Unidade");
                unidade = iunidade.obterUnidade(idsUnidade[indiceUnidade]);
                if (unidade != null) {
                    PesquisaNomeUnidade.setText(unidade.getNome());
                }
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar unidade: " + e.getMessage());
            }
        } else {
            unidade = ListaDePesquisaUnidade();
        }
        return unidade;
    }

    public Tributo MostraPesquisaTributo() {
        Tributo tributo = null;
        int indiceTributo = ListaTributo.getSelectedIndex();
        if (indiceTributo >= 0) {
            try{
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceTributo itributo = (InterfaceTributo) registro.lookup("Tributo");
                tributo = itributo.obterTributo(idsTributo[indiceTributo]);
                if (tributo != null) {
                    PesquisaNomeTributo.setText(tributo.getNome_imposto());
                }
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar tributo: " + e.getMessage());
            }
        } else {
            tributo = ListaDePesquisaTributo();
        }
        return tributo;
    }

    private void limparCampos() {
        PesquisaNomeUnidade.setText("");
        PesquisaNomeTributo.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        PesquisaNomeTributo = new javax.swing.JTextField();
        ListaTributo = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        PesquisaNomeUnidade = new javax.swing.JTextField();
        ListaUnidade = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar tributo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N

        PesquisaNomeTributo.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        PesquisaNomeTributo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisaNomeTributoActionPerformed(evt);
            }
        });
        PesquisaNomeTributo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PesquisaNomeTributoKeyReleased(evt);
            }
        });

        ListaTributo.setBorder(null);
        ListaTributo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ListaTributoListaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PesquisaNomeTributo)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(ListaTributo, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(PesquisaNomeTributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(ListaTributo, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar unidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N

        PesquisaNomeUnidade.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        PesquisaNomeUnidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisaNomeUnidadeActionPerformed(evt);
            }
        });
        PesquisaNomeUnidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PesquisaNomeUnidadeKeyReleased(evt);
            }
        });

        ListaUnidade.setBorder(null);
        ListaUnidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ListaUnidadeListaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PesquisaNomeUnidade, javax.swing.GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(ListaUnidade, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(PesquisaNomeUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(ListaUnidade, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Relacionamento Unidade-Tributo");

        jButton1.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 153, 255));
        jButton1.setText("Relacionar unidade e tributo");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(46, 46, 46)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PesquisaNomeTributoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomeTributoActionPerformed

        ListaTributo.setVisible(false);

    }//GEN-LAST:event_PesquisaNomeTributoActionPerformed

    private void PesquisaNomeTributoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeTributoKeyReleased
        //ListaDePesquisa();
    }//GEN-LAST:event_PesquisaNomeTributoKeyReleased

    private void ListaTributoListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaTributoListaMousePressed
        //MostraPesquisa();

        ListaTributo.setVisible(false);

    }//GEN-LAST:event_ListaTributoListaMousePressed

    private void PesquisaNomeUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomeUnidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PesquisaNomeUnidadeActionPerformed

    private void PesquisaNomeUnidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeUnidadeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_PesquisaNomeUnidadeKeyReleased

    private void ListaUnidadeListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaUnidadeListaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListaUnidadeListaMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Tributo tributo = new Tributo();
        tributo.setNome_imposto(PesquisaNomeTributo.getText());

        try {
            Registry registro = LocateRegistry.getRegistry("localhost",1099);
            InterfaceTributo itributo = (InterfaceTributo) registro.lookup("Tributo");
            itributo.inserirTributo(tributo);
            JOptionPane.showMessageDialog(null, "Tributo inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } catch (RemoteException | NotBoundException e) {
           JOptionPane.showMessageDialog(null, "Erro ao atualizar tributo: " + e.getMessage());
        }
        
        Unidade unidade = new Unidade();
        unidade.setNome(PesquisaNomeUnidade.getText());

        try {
            Registry registro = LocateRegistry.getRegistry("localhost",1099);
            InterfaceUnidade iunidade = (InterfaceUnidade) registro.lookup("Unidade");
            iunidade.inserirUnidade(unidade);
            JOptionPane.showMessageDialog(null, "Unidade inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar unidade: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListaTributo;
    private javax.swing.JList<String> ListaUnidade;
    private javax.swing.JTextField PesquisaNomeTributo;
    private javax.swing.JTextField PesquisaNomeUnidade;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}

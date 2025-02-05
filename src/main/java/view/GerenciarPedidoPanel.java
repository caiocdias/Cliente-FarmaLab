/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import controller.InterfaceCliente;
import controller.InterfaceFuncionario;
import controller.InterfacePedido;
import controller.InterfacePrescricao;
import controller.InterfaceUnidade;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Funcionario;
import model.Pedido;
import model.Prescricao;
import model.Produto;
import model.Unidade;
import model.enums.StatusPedido;

/**
 *
 * @author Isabely
 */
public class GerenciarPedidoPanel extends javax.swing.JPanel {
    
    
    DefaultListModel MODELOcliente;
    List<Cliente> clientes = new ArrayList<>();
    Integer [] idsCliente;
    
    DefaultListModel MODELOfuncionario;
    List<Funcionario> funcionarios = new ArrayList<>();
    Integer [] idsFuncionario;
    
    DefaultListModel MODELOunidade;
    List<Unidade> unidades = new ArrayList<>();
    Integer [] idsUnidade;
    
    public GerenciarPedidoPanel() {
        initComponents();
        
        MODELOcliente = new DefaultListModel();
        ListaCliente.setModel(MODELOcliente);
        
        MODELOfuncionario = new DefaultListModel();
        ListaFuncionario.setModel(MODELOfuncionario);
        
        MODELOunidade = new DefaultListModel();
        ListaUnidade.setModel(MODELOunidade);
        
    }
    
    public StatusPedido getStatusSelecionado() {
        String descricaoSelecionada = (String) statusComboBox.getSelectedItem();
        for (StatusPedido status : StatusPedido.values()) {
            if (status.getDescricao().equals(descricaoSelecionada)) {
                return status;
            }
        }
        return null;  // Caso não encontre um status correspondente
    }
    
    public Cliente ListaDePesquisaCliente() {
        Cliente cliente = null;
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceCliente icliente = (InterfaceCliente) registro.lookup("Cliente");
            clientes = icliente.buscarClientesPorNome(PesquisaNomeCliente.getText());

            MODELOcliente.removeAllElements();
            idsCliente = new Integer[clientes.size()];
            
            for (int i = 0; i < clientes.size(); i++) {
                cliente = clientes.get(i);
                MODELOcliente.addElement(cliente.getNome());
                idsCliente[i] = cliente.getId();
            }
            
            ListaCliente.setVisible(!clientes.isEmpty());
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + e.getMessage());
        }
        return cliente;
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
    
    public Funcionario ListaDePesquisaFuncionario() {
        Funcionario funcionario = null;
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceFuncionario ifuncionario = (InterfaceFuncionario) registro.lookup("Funcionario");
            funcionarios = ifuncionario.buscarFuncionariosPorNome(PesquisaNomeFuncionario.getText());

            MODELOfuncionario.removeAllElements();
            idsFuncionario = new Integer[funcionarios.size()];
            
            for (int i = 0; i < funcionarios.size(); i++) {
                funcionario = funcionarios.get(i);
                MODELOfuncionario.addElement(funcionario.getNome());
                idsFuncionario[i] = funcionario.getId();
            }
            
            ListaFuncionario.setVisible(!funcionarios.isEmpty());
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar funcionario: " + e.getMessage());
        }
        return funcionario;
    }
    
    
    
    public Prescricao PesquisaPrescricao (){
        Prescricao prescricao = null;
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfacePrescricao iPrescricao = (InterfacePrescricao) registro.lookup("Prescricao");
            prescricao = iPrescricao.obterPrescricao(null, PrescricaoField.getText());
       
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar prescricao: " + e.getMessage());
        }
        return prescricao;
    }
    
    
    public Cliente MostraPesquisaCliente() {
        Cliente cliente = null;
        int indiceCliente = ListaCliente.getSelectedIndex();
        if (indiceCliente >= 0) {
            try{
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceCliente icliente = (InterfaceCliente) registro.lookup("Cliente");
                cliente = icliente.obterCliente(idsCliente[indiceCliente], null);
                if (cliente != null) {
                    PesquisaNomeCliente.setText(cliente.getNome());
                }
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + e.getMessage());
            }
        }else {
            cliente = ListaDePesquisaCliente();
        }
        return cliente;
    }
    
    public Funcionario MostraPesquisaFuncionario() {
        Funcionario funcionario = null;
        int indiceFuncionario = ListaFuncionario.getSelectedIndex();
        if (indiceFuncionario >= 0) {
            try{
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceFuncionario ifuncionario = (InterfaceFuncionario) registro.lookup("Funcionario");
                funcionario = ifuncionario.obterFuncionario(idsFuncionario[indiceFuncionario], null);
                if (funcionario != null) {
                    ProdutoField.setText(funcionario.getNome());
                }
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar funcionario: " + e.getMessage());
            }
        }else {
            funcionario = ListaDePesquisaFuncionario();
        }
        return funcionario;
    }
    
    public Unidade MostraPesquisaUnidade() {
        Unidade unidade = null;
        int indiceUnidade = ListaFuncionario.getSelectedIndex();
        if (indiceUnidade >= 0) {
            try{
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceUnidade iunidade = (InterfaceUnidade) registro.lookup("Unidade");
                unidade = iunidade.obterUnidade(idsUnidade[indiceUnidade]);
                if (unidade != null) {
                    PesquisaNomeFuncionario.setText(unidade.getNome());
                }
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar unidade: " + e.getMessage());
            }
        }else {
            unidade = ListaDePesquisaUnidade();
        }
        return unidade;
    }

    private void limparCampos() {
        PesquisaNomeFuncionario.setText("");
        PesquisaNomeCliente.setText("");
        ProdutoField.setText("");
        PesquisaNomeUnidade.setText("");
        PrescricaoField.setText("");
        statusComboBox.setSelectedIndex(0);
        ProntaEntregaCheckBox.setSelected(false);
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
        PesquisaId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        BuscarButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        PesquisaNomeUnidade = new javax.swing.JTextField();
        ListaUnidade = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        PrescricaoField = new javax.swing.JTextField();
        ProntaEntregaCheckBox = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        PesquisaNomeFuncionario = new javax.swing.JTextField();
        ListaFuncionario = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        PesquisaNomeCliente = new javax.swing.JTextField();
        ListaCliente = new javax.swing.JList<>();
        Produtos = new javax.swing.JPanel();
        ProdutoField = new javax.swing.JTextField();
        excluirButton = new javax.swing.JButton();
        confirmarButton = new javax.swing.JButton();

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N

        PesquisaId.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Id:");

        BuscarButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BuscarButton.setText("Buscar");
        BuscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 937, Short.MAX_VALUE)
                        .addComponent(BuscarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PesquisaId)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PesquisaId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BuscarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Edição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Unidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        PesquisaNomeUnidade.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PesquisaNomeUnidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addComponent(ListaUnidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(PesquisaNomeUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListaUnidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Prescrição:");

        PrescricaoField.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        PrescricaoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrescricaoFieldActionPerformed(evt);
            }
        });

        ProntaEntregaCheckBox.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        ProntaEntregaCheckBox.setText("Pronta-entrega");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Status:");

        statusComboBox.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aguardando Pagamento", "Pronto para Produção", "Em andamento", "Para retirada", "Concluído" }));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Funcionário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        PesquisaNomeFuncionario.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        PesquisaNomeFuncionario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PesquisaNomeFuncionarioKeyReleased(evt);
            }
        });

        ListaFuncionario.setBorder(null);
        ListaFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ListaFuncionarioListaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PesquisaNomeFuncionario)
                    .addComponent(ListaFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(PesquisaNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListaFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel6.setAutoscrolls(true);
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        PesquisaNomeCliente.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        PesquisaNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PesquisaNomeClienteKeyReleased(evt);
            }
        });

        ListaCliente.setBorder(null);
        ListaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ListaClienteListaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PesquisaNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                    .addComponent(ListaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(PesquisaNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        Produtos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        ProdutoField.setEditable(false);
        ProdutoField.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        javax.swing.GroupLayout ProdutosLayout = new javax.swing.GroupLayout(Produtos);
        Produtos.setLayout(ProdutosLayout);
        ProdutosLayout.setHorizontalGroup(
            ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ProdutoField)
                .addContainerGap())
        );
        ProdutosLayout.setVerticalGroup(
            ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdutosLayout.createSequentialGroup()
                .addComponent(ProdutoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ProntaEntregaCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PrescricaoField, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Produtos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProntaEntregaCheckBox)
                    .addComponent(jLabel8)
                    .addComponent(PrescricaoField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(227, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addComponent(Produtos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        excluirButton.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        excluirButton.setForeground(new java.awt.Color(255, 51, 51));
        excluirButton.setText("Desabilitar pedido");
        excluirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirButtonActionPerformed(evt);
            }
        });

        confirmarButton.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        confirmarButton.setForeground(new java.awt.Color(51, 102, 0));
        confirmarButton.setText("Confirmar edição");
        confirmarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(excluirButton)
                    .addComponent(confirmarButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarButtonActionPerformed
       
        try{
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfacePedido iPedido = (InterfacePedido) registro.lookup("Pedido");

            int id = Integer.parseInt(PesquisaId.getText());
            PesquisaId.getText();
            Pedido pedido = iPedido.obterPedido(id);
            Produto produto = null;
            
            if (pedido != null) {
                PesquisaNomeUnidade.setText(pedido.getUnidade().getNome());
                PesquisaNomeCliente.setText(pedido.getCliente().getNome());
                PesquisaNomeFuncionario.setText(pedido.getFuncionario().getNome());
                PrescricaoField.setText(pedido.getPrescricao().getCrm());
                ProntaEntregaCheckBox.setSelected(pedido.isPronta_entrega());
                List<Produto> produtos = pedido.getProdutos();
                for(int i = 0; i < produtos.size(); i++){
                    produto = produtos.get(i);
                }
                ProdutoField.setText(produto.getTipo_produto().getNome());
                statusComboBox.setSelectedItem(pedido.getStatus().getDescricao());
            }else{
                JOptionPane.showMessageDialog(null, "Nenhum pedido com esse id");
            }
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar pedido: " + e.getMessage());
        }
    }//GEN-LAST:event_BuscarButtonActionPerformed

    private void PesquisaNomeUnidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeUnidadeKeyReleased
        ListaDePesquisaUnidade();
    }//GEN-LAST:event_PesquisaNomeUnidadeKeyReleased

    private void ListaUnidadeListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaUnidadeListaMousePressed
        Unidade unidade = MostraPesquisaUnidade();

    }//GEN-LAST:event_ListaUnidadeListaMousePressed

    private void PesquisaNomeFuncionarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeFuncionarioKeyReleased
        ListaDePesquisaFuncionario();
    }//GEN-LAST:event_PesquisaNomeFuncionarioKeyReleased

    private void ListaFuncionarioListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaFuncionarioListaMousePressed
        Funcionario funcionario = MostraPesquisaFuncionario();

    }//GEN-LAST:event_ListaFuncionarioListaMousePressed

    private void PesquisaNomeClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeClienteKeyReleased
        ListaDePesquisaCliente();
    }//GEN-LAST:event_PesquisaNomeClienteKeyReleased

    private void ListaClienteListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaClienteListaMousePressed
        Cliente cliente = MostraPesquisaCliente();
    }//GEN-LAST:event_ListaClienteListaMousePressed

    private void excluirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirButtonActionPerformed
        int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este pedido", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfacePedido iPedido = (InterfacePedido) registro.lookup("Pedido");

                int id = Integer.parseInt(PesquisaId.getText());
                iPedido.desativarPedido(id);
                JOptionPane.showMessageDialog(null, "Pedido excluído com sucesso!");
                limparCampos();
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir pedido: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_excluirButtonActionPerformed

    private void confirmarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarButtonActionPerformed
        Pedido pedido = new Pedido();
        
        try {
            Cliente cliente = MostraPesquisaCliente();
            Funcionario funcionario = MostraPesquisaFuncionario();
            Unidade unidade = MostraPesquisaUnidade();
            Prescricao prescricao = PesquisaPrescricao();
            
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfacePedido iPedido = (InterfacePedido) registro.lookup("Pedido");
            int id = Integer.parseInt(PesquisaId.getText());
            
            pedido.setId(id);
            pedido.setCliente(cliente);
            pedido.setFuncionario(funcionario);
            pedido.setUnidade(unidade);
            
            if(prescricao == null){
                JOptionPane.showMessageDialog(null, "Prescrição não encontrada!", "Falha", JOptionPane.ERROR_MESSAGE);
            } else {
                pedido.setPrescricao(prescricao);
            }
            
            StatusPedido statusSelecionado = getStatusSelecionado();
            pedido.setStatus(statusSelecionado);
            pedido.setPronta_entrega(ProntaEntregaCheckBox.isSelected());
            pedido.setHabilitado(true);

            iPedido.atualizarPedido(pedido);
            JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
            limparCampos();
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar pedio: " + e.getMessage());
        }

    }//GEN-LAST:event_confirmarButtonActionPerformed

    private void PrescricaoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrescricaoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrescricaoFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuscarButton;
    private javax.swing.JList<String> ListaCliente;
    private javax.swing.JList<String> ListaFuncionario;
    private javax.swing.JList<String> ListaUnidade;
    private javax.swing.JTextField PesquisaId;
    private javax.swing.JTextField PesquisaNomeCliente;
    private javax.swing.JTextField PesquisaNomeFuncionario;
    private javax.swing.JTextField PesquisaNomeUnidade;
    private javax.swing.JTextField PrescricaoField;
    private javax.swing.JTextField ProdutoField;
    private javax.swing.JPanel Produtos;
    private javax.swing.JCheckBox ProntaEntregaCheckBox;
    private javax.swing.JButton confirmarButton;
    private javax.swing.JButton excluirButton;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JComboBox<String> statusComboBox;
    // End of variables declaration//GEN-END:variables
}

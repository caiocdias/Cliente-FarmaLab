/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import controller.InterfaceCliente;
import controller.InterfaceFuncionario;
import controller.InterfacePrescricao;
import controller.InterfaceProduto;
import controller.InterfaceTipoProduto;
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
import model.Prescricao;
import model.Produto;
import model.TipoProduto;
import model.Unidade;

/**
 *
 * @author Isabely
 */
public class CadastrarPedidoPanel extends javax.swing.JPanel {

    DefaultListModel MODELOcliente;
    List<Cliente> clientes = new ArrayList<>();
    Integer [] idsCliente;
    
    DefaultListModel MODELOfuncionario;
    List<Funcionario> funcionarios = new ArrayList<>();
    Integer [] idsFuncionario;
    
    DefaultListModel MODELOunidade;
    List<Unidade> unidades = new ArrayList<>();
    Integer [] idsUnidade;
    
    DefaultListModel MODELOtipoproduto;
    List<TipoProduto> tipoProdutos = new ArrayList<>();
    Integer [] idsTipoProduto;
    
    public CadastrarPedidoPanel() {
        initComponents();
        
        ListaCliente.setVisible(false);
        MODELOcliente = new DefaultListModel();
        ListaCliente.setModel(MODELOcliente);
        
        ListaFuncionario.setVisible(false);
        MODELOfuncionario = new DefaultListModel();
        ListaFuncionario.setModel(MODELOfuncionario);
        
        ListaUnidade.setVisible(false);
        MODELOunidade = new DefaultListModel();
        ListaUnidade.setModel(MODELOunidade);
        
        ListaProduto.setVisible(false);
        MODELOtipoproduto = new DefaultListModel();
        ListaProduto.setModel(MODELOtipoproduto);
        
    }
    
    public void ListaDePesquisaCliente() {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceCliente icliente = (InterfaceCliente) registro.lookup("Cliente");
            clientes = icliente.buscarClientesPorNome(PesquisaNomeCliente.getText());

            MODELOcliente.removeAllElements();
            idsCliente = new Integer[clientes.size()];
            
            for (int i = 0; i < clientes.size(); i++) {
                Cliente cliente = clientes.get(i);
                MODELOcliente.addElement(cliente.getNome());
                idsCliente[i] = cliente.getId();
            }
            
            ListaCliente.setVisible(!clientes.isEmpty());
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + e.getMessage());
        }
    }
    
   public void ListaDePesquisaUnidade() {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceUnidade iunidade = (InterfaceUnidade) registro.lookup("Unidade");
            unidades = iunidade.buscarUnidadePorNome(PesquisaNomeUnidade.getText());

            MODELOunidade.removeAllElements();
            idsUnidade = new Integer[unidades.size()];
            
            for (int i = 0; i < unidades.size(); i++) {
                Unidade unidade = unidades.get(i);
                MODELOunidade.addElement(unidade.getNome());
                idsUnidade[i] = unidade.getId();
            }
            
            ListaUnidade.setVisible(!unidades.isEmpty());
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar unidades: " + e.getMessage());
        }
    }
    
    public void ListaDePesquisaFuncionario() {
        
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceFuncionario ifuncionario = (InterfaceFuncionario) registro.lookup("Funcionario");
            funcionarios = ifuncionario.buscarFuncionariosPorNome(PesquisaNomeFuncionario.getText());

            MODELOfuncionario.removeAllElements();
            idsFuncionario = new Integer[funcionarios.size()];
            
            for (int i = 0; i < funcionarios.size(); i++) {
                Funcionario funcionario = funcionarios.get(i);
                MODELOfuncionario.addElement(funcionario.getNome());
                idsFuncionario[i] = funcionario.getId();
            }
            
            ListaFuncionario.setVisible(!funcionarios.isEmpty());
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar funcionarios: " + e.getMessage());
        }
    }
    
    public void ListaDePesquisaTipoProduto() {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceTipoProduto itipo = (InterfaceTipoProduto) registro.lookup("Tipo Produto");
            tipoProdutos = itipo.buscarTipoProdutoPorNome(PesquisaNomeProduto.getText());
            
            InterfaceProduto iproduto = (InterfaceProduto) registro.lookup("Produto");

            MODELOcliente.removeAllElements();
            idsTipoProduto = new Integer[tipoProdutos.size()];
            
            for (int i = 0; i < tipoProdutos.size(); i++) {
                TipoProduto tipoProduto = tipoProdutos.get(i);
                MODELOcliente.addElement(tipoProduto.getNome());
                idsCliente[i] = tipoProduto.getId();
            }
            ListaCliente.setVisible(!clientes.isEmpty());
            
            
            
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        }
    }
    
    
    
    public void ListaDePesquisaPrescricao() {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfacePrescricao iprescricao = (InterfacePrescricao) registro.lookup("Prescricao");

            String crm = PesquisaNomePrescricao.getText();
            if (crm.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Informe o CRM para buscar prescrições.");
                return;
            }
            Prescricao prescricao = iprescricao.obterPrescricao(null, crm);

            if (prescricao != null) {
                PesquisaNomePrescricao.setText(prescricao.getCrm());
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma prescrição encontrada para o CRM informado.");
            }

        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar prescrição: " + e.getMessage());
        }
    }
    
    public void MostraPesquisaProdutos() {
        
        int indice = ListaProduto.getSelectedIndex();
        if (indice >= 0) {
            try{
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceTipoProduto itipo = (InterfaceTipoProduto) registro.lookup("Tipo Produto");
                InterfaceProduto iproduto = (InterfaceProduto) registro.lookup("Produto");
                TipoProduto tipoProduto = itipo.obterTipoProduto(idsTipoProduto[indice]);
                if (tipoProduto != null) {
                    PesquisaNomeCliente.setText(tipoProduto.getNome());
                    Produto produtoDisp = iproduto.produtoDisponivel(tipoProduto.getId());
                    System.out.println(produtoDisp);
                    if(produtoDisp == null){
                        Produto produto  = new Produto();
                        produto.setId(0);
                        produto.setColetado(false);
                        produto.setData_validade(null);
                        produto.setEstoque(null);
                        produto.setTipo_produto(tipoProduto);
                        //produto.setPronta_entrega(ProntaEntregaCheckBox.getActionCommand());
                    }
                }
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + e.getMessage());
            }
        }
    }
    
    public void MostraPesquisaCliente() {
        int indiceCliente = ListaCliente.getSelectedIndex();
        if (indiceCliente >= 0) {
            try{
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceCliente icliente = (InterfaceCliente) registro.lookup("Cliente");
                Cliente cliente = icliente.obterCliente(idsCliente[indiceCliente], null);
                if (cliente != null) {
                    PesquisaNomeCliente.setText(cliente.getNome());
                }
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + e.getMessage());
            }
        }
    }
    
    
    
    public void MostraPesquisaFuncionario() {
        int indiceFuncionario = ListaFuncionario.getSelectedIndex();
        if (indiceFuncionario >= 0) {
            try{
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceFuncionario ifuncionario = (InterfaceFuncionario) registro.lookup("Funcionario");
                Funcionario funcionario = ifuncionario.obterFuncionario(idsFuncionario[indiceFuncionario], null);
                if (funcionario != null) {
                    PesquisaNomeFuncionario.setText(funcionario.getNome());
                }
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar funcionario: " + e.getMessage());
            }
        }
    }
    
    public void MostraPesquisaUnidade() {
        int indiceUnidade = ListaUnidade.getSelectedIndex();
        if (indiceUnidade >= 0) {
            try{
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceUnidade iunidade = (InterfaceUnidade) registro.lookup("Unidade");
                Unidade unidade = iunidade.obterUnidade(idsUnidade[indiceUnidade]);
                if (unidade != null) {
                    PesquisaNomeUnidade.setText(unidade.getNome());
                }
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar unidade: " + e.getMessage());
            }
        }
    }

    private void limparCampos() {
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        PesquisaNomeUnidade = new javax.swing.JTextField();
        ListaUnidade = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        PesquisaNomeCliente = new javax.swing.JTextField();
        ListaCliente = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        PesquisaNomeFuncionario = new javax.swing.JTextField();
        ListaFuncionario = new javax.swing.JList<>();
        SalvarButton = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        PesquisaNomePrescricao = new javax.swing.JTextField();
        ListaPrescricao = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();
        ProntaEntregaCheckBox = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        PesquisaNomeProduto = new javax.swing.JTextField();
        ListaProduto = new javax.swing.JList<>();

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pedido");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Unidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PesquisaNomeUnidade)
                    .addComponent(ListaUnidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(PesquisaNomeUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListaUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel6.setAutoscrolls(true);
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        PesquisaNomeCliente.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        PesquisaNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisaNomeClienteActionPerformed(evt);
            }
        });
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
                    .addComponent(PesquisaNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addComponent(ListaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(PesquisaNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Funcionario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        PesquisaNomeFuncionario.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        PesquisaNomeFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisaNomeFuncionarioActionPerformed(evt);
            }
        });
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PesquisaNomeFuncionario)
                    .addComponent(ListaFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(PesquisaNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        SalvarButton.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        SalvarButton.setForeground(new java.awt.Color(51, 153, 255));
        SalvarButton.setText("Salvar");
        SalvarButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        SalvarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarButtonActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prescrição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        PesquisaNomePrescricao.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        PesquisaNomePrescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisaNomePrescricaoActionPerformed(evt);
            }
        });
        PesquisaNomePrescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PesquisaNomePrescricaoKeyReleased(evt);
            }
        });

        ListaPrescricao.setBorder(null);
        ListaPrescricao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ListaPrescricaoListaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PesquisaNomePrescricao)
                    .addComponent(ListaPrescricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(PesquisaNomePrescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListaPrescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Status:");

        statusComboBox.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aguardando pagamento", "Pronto para produção", "Em andamento", "Para retirada", "Concluído" }));

        ProntaEntregaCheckBox.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        ProntaEntregaCheckBox.setText("Pronta-entrega");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        PesquisaNomeProduto.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        PesquisaNomeProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisaNomeProdutoActionPerformed(evt);
            }
        });
        PesquisaNomeProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PesquisaNomeProdutoKeyReleased(evt);
            }
        });

        ListaProduto.setBorder(null);
        ListaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ListaProdutoListaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ListaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PesquisaNomeProduto))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(PesquisaNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ListaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SalvarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ProntaEntregaCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ProntaEntregaCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(SalvarButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PesquisaNomeUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomeUnidadeActionPerformed
        ListaUnidade.setVisible(false);
    }//GEN-LAST:event_PesquisaNomeUnidadeActionPerformed

    private void PesquisaNomeUnidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeUnidadeKeyReleased
        ListaDePesquisaUnidade();
    }//GEN-LAST:event_PesquisaNomeUnidadeKeyReleased

    private void ListaUnidadeListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaUnidadeListaMousePressed
        MostraPesquisaUnidade();
        ListaUnidade.setVisible(false);
    }//GEN-LAST:event_ListaUnidadeListaMousePressed

    private void PesquisaNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomeClienteActionPerformed
        ListaCliente.setVisible(false);
    }//GEN-LAST:event_PesquisaNomeClienteActionPerformed

    private void PesquisaNomeClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeClienteKeyReleased
        ListaDePesquisaCliente();
    }//GEN-LAST:event_PesquisaNomeClienteKeyReleased

    private void ListaClienteListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaClienteListaMousePressed
        MostraPesquisaCliente();
        ListaCliente.setVisible(false);
    }//GEN-LAST:event_ListaClienteListaMousePressed

    private void PesquisaNomeFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomeFuncionarioActionPerformed
        ListaFuncionario.setVisible(false);
    }//GEN-LAST:event_PesquisaNomeFuncionarioActionPerformed

    private void PesquisaNomeFuncionarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeFuncionarioKeyReleased
        ListaDePesquisaFuncionario();
    }//GEN-LAST:event_PesquisaNomeFuncionarioKeyReleased

    private void ListaFuncionarioListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaFuncionarioListaMousePressed
        MostraPesquisaFuncionario();
        ListaFuncionario.setVisible(false);
    }//GEN-LAST:event_ListaFuncionarioListaMousePressed

    private void SalvarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarButtonActionPerformed

    }//GEN-LAST:event_SalvarButtonActionPerformed

    private void PesquisaNomePrescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomePrescricaoActionPerformed
        ListaPrescricao.setVisible(false);
    }//GEN-LAST:event_PesquisaNomePrescricaoActionPerformed

    private void PesquisaNomePrescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomePrescricaoKeyReleased
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) { 
            ListaDePesquisaPrescricao();
        }
    }//GEN-LAST:event_PesquisaNomePrescricaoKeyReleased

    private void ListaPrescricaoListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaPrescricaoListaMousePressed
        ListaPrescricao.setVisible(false);
    }//GEN-LAST:event_ListaPrescricaoListaMousePressed

    private void PesquisaNomeProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomeProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PesquisaNomeProdutoActionPerformed

    private void PesquisaNomeProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeProdutoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_PesquisaNomeProdutoKeyReleased

    private void ListaProdutoListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaProdutoListaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListaProdutoListaMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListaCliente;
    private javax.swing.JList<String> ListaFuncionario;
    private javax.swing.JList<String> ListaPrescricao;
    private javax.swing.JList<String> ListaProduto;
    private javax.swing.JList<String> ListaUnidade;
    private javax.swing.JTextField PesquisaNomeCliente;
    private javax.swing.JTextField PesquisaNomeFuncionario;
    private javax.swing.JTextField PesquisaNomePrescricao;
    private javax.swing.JTextField PesquisaNomeProduto;
    private javax.swing.JTextField PesquisaNomeUnidade;
    private javax.swing.JCheckBox ProntaEntregaCheckBox;
    private javax.swing.JButton SalvarButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JComboBox<String> statusComboBox;
    // End of variables declaration//GEN-END:variables
}

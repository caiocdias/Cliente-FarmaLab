/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import controller.InterfaceCliente;
import controller.InterfaceEstoque;
import controller.InterfaceFuncionario;
import controller.InterfacePedido;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Estoque;
import model.Funcionario;
import model.Pedido;
import model.Prescricao;
import model.Produto;
import model.TipoProduto;
import model.Unidade;
import model.enums.StatusPedido;

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
    
    DefaultListModel MODELOestoque;
    List<Estoque> estoques = new ArrayList<>();
    Integer [] idsEstoque;
    
    public CadastrarPedidoPanel() {
        initComponents();
        
        MODELOcliente = new DefaultListModel();
        ListaCliente.setModel(MODELOcliente);
        
        MODELOfuncionario = new DefaultListModel();
        ListaFuncionario.setModel(MODELOfuncionario);
        
        MODELOunidade = new DefaultListModel();
        ListaUnidade.setModel(MODELOunidade);
        
        MODELOtipoproduto = new DefaultListModel();
        ListaProduto.setModel(MODELOtipoproduto);
        
        MODELOestoque = new DefaultListModel();
        ListaEstoque.setModel(MODELOestoque);
        
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
    
    public void ListaDePesquisaEstoque() {
        
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceEstoque iEstoque = (InterfaceEstoque) registro.lookup("Estoque");
            estoques = iEstoque.buscarEstoquePorNome(PesquisaNomeEstoque.getText());

            MODELOestoque.removeAllElements();
            idsEstoque = new Integer[estoques.size()];
            
            for (int i = 0; i < estoques.size(); i++) {
                Estoque estoque = estoques.get(i);
                MODELOestoque.addElement(estoque.getNome());
                idsEstoque[i] = estoque.getId();
            }
            
            ListaEstoque.setVisible(!estoques.isEmpty());
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar estoques: " + e.getMessage());
        }
    }
    
    public void ListaDePesquisaTipoProduto() {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceTipoProduto itipo = (InterfaceTipoProduto) registro.lookup("TipoProduto");
            tipoProdutos = itipo.buscarTipoProdutoPorNome(PesquisaNomeProduto.getText());
            
            MODELOtipoproduto.removeAllElements();
            idsTipoProduto = new Integer[tipoProdutos.size()];
            
            for (int i = 0; i < tipoProdutos.size(); i++) {
                TipoProduto tipoProduto = tipoProdutos.get(i);
                MODELOtipoproduto.addElement(tipoProduto.getNome());
                idsTipoProduto[i] = tipoProduto.getId();
            }
            ListaProduto.setVisible(!tipoProdutos.isEmpty());
            
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        }
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
    
    public Estoque MostraPesquisaEstoque() {
        Estoque estoque = null;
        int indiceEstoque = ListaEstoque.getSelectedIndex();
        if (indiceEstoque >= 0) {
            try{
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                InterfaceEstoque iEstoque = (InterfaceEstoque) registro.lookup("Estoque");
                estoque = iEstoque.obterEstoque(idsEstoque[indiceEstoque]);
                if (estoque != null) {
                    PesquisaNomeEstoque.setText(estoque.getNome());
                }
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar estoque: " + e.getMessage());
            }
        }
        return estoque;
    }
    
    public Produto MostraPesquisaProdutos() {
    Produto produtoDisp = null;
    TipoProduto tipoProduto = null;
    int indice = ListaProduto.getSelectedIndex();

    if (indice >= 0) {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceTipoProduto itipo = (InterfaceTipoProduto) registro.lookup("TipoProduto");
            InterfaceProduto iproduto = (InterfaceProduto) registro.lookup("Produto");

            List<Integer> ids = new ArrayList<>();
            ids.add(idsTipoProduto[indice]);
            List<TipoProduto> tipoProdutos = itipo.obterTipoProduto(ids);

            if (!tipoProdutos.isEmpty()) {
                tipoProduto = tipoProdutos.get(0);
                PesquisaNomeProduto.setText(tipoProduto.getNome());

                produtoDisp = iproduto.produtoDisponivel(tipoProduto.getId());

                if (produtoDisp == null) {
                    produtoDisp = new Produto();
                    Estoque estoque = MostraPesquisaEstoque();
                    produtoDisp.setId(0);
                    produtoDisp.setHabilitado(true);
                    produtoDisp.setColetado(false);
                    produtoDisp.setData_validade(null);
                    produtoDisp.setEstoque(estoque);
                    produtoDisp.setTipo_produto(tipoProduto);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum produto encontrado.");
            }
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produto: " + e.getMessage());
        }
    }
    Carrinho.setText(tipoProduto != null ? tipoProduto.getNome() : "Produto não encontrado");

    return produtoDisp;
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
                    PesquisaNomeFuncionario.setText(funcionario.getNome());
                }
            } catch (RemoteException | NotBoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar funcionario: " + e.getMessage());
            }
        }
        return funcionario;
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
        }
        return unidade;
    }

    private void limparCampos() {
        PesquisaNomeUnidade.setText("");
        PesquisaNomeCliente.setText("");
        PesquisaNomeFuncionario.setText("");
        PesquisaNomeProduto.setText("");
        PesquisaNomeEstoque.setText("");
        PrescricaoField.setText("");
        Carrinho.setText("");
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
        jLabel6 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();
        ProntaEntregaCheckBox = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        PesquisaNomeProduto = new javax.swing.JTextField();
        ListaProduto = new javax.swing.JList<>();
        Carrinho = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        PrescricaoField = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        PesquisaNomeEstoque = new javax.swing.JTextField();
        ListaEstoque = new javax.swing.JList<>();

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
                    .addComponent(PesquisaNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
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
                .addContainerGap(10, Short.MAX_VALUE))
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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Status:");

        statusComboBox.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aguardando Pagamento", "Pronto para Produção", "Em andamento", "Para retirada", "Concluído" }));
        statusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusComboBoxActionPerformed(evt);
            }
        });

        ProntaEntregaCheckBox.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        ProntaEntregaCheckBox.setText("Pronta-entrega");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo do Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

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
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PesquisaNomeProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addComponent(ListaProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(PesquisaNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        Carrinho.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Carrinho:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Prescrição:");

        PrescricaoField.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estoque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel9.setAutoscrolls(true);
        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        PesquisaNomeEstoque.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        PesquisaNomeEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisaNomeEstoqueActionPerformed(evt);
            }
        });
        PesquisaNomeEstoque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PesquisaNomeEstoqueKeyReleased(evt);
            }
        });

        ListaEstoque.setBorder(null);
        ListaEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ListaEstoqueListaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PesquisaNomeEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addComponent(ListaEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(PesquisaNomeEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListaEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(Carrinho, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel8)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(PrescricaoField))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(ProntaEntregaCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(360, 360, 360)))))
                                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(ProntaEntregaCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(PrescricaoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(Carrinho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(SalvarButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PesquisaNomeUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomeUnidadeActionPerformed
        
    }//GEN-LAST:event_PesquisaNomeUnidadeActionPerformed

    private void PesquisaNomeUnidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeUnidadeKeyReleased
        ListaDePesquisaUnidade();
    }//GEN-LAST:event_PesquisaNomeUnidadeKeyReleased

    private void ListaUnidadeListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaUnidadeListaMousePressed
        Unidade unidade = MostraPesquisaUnidade();
        
    }//GEN-LAST:event_ListaUnidadeListaMousePressed

    private void PesquisaNomeFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomeFuncionarioActionPerformed
        
    }//GEN-LAST:event_PesquisaNomeFuncionarioActionPerformed

    private void PesquisaNomeFuncionarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeFuncionarioKeyReleased
        ListaDePesquisaFuncionario();
    }//GEN-LAST:event_PesquisaNomeFuncionarioKeyReleased

    private void ListaFuncionarioListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaFuncionarioListaMousePressed
        Funcionario funcionario = MostraPesquisaFuncionario();
        
    }//GEN-LAST:event_ListaFuncionarioListaMousePressed

    private void SalvarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarButtonActionPerformed
        Pedido pedido = new Pedido();
        Cliente cliente = MostraPesquisaCliente();
        Funcionario funcionario = MostraPesquisaFuncionario();
        Unidade unidade = MostraPesquisaUnidade();
        Produto produto = MostraPesquisaProdutos();
        Prescricao prescricao = PesquisaPrescricao();
        List <Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        if (cliente == null || funcionario == null || unidade == null || produto == null || prescricao == null) {
            JOptionPane.showMessageDialog(null, "Erro: Dados incompletos.", "Falha", JOptionPane.ERROR_MESSAGE);
            return;
        }
        pedido.setCliente(cliente);
        pedido.setFuncionario(funcionario);
        pedido.setUnidade(unidade);
        pedido.setProdutos(produtos);
        if(prescricao == null){
            JOptionPane.showMessageDialog(null, "Prescrição não encontrada!", "Falha", JOptionPane.ERROR_MESSAGE);
        } else {
            pedido.setPrescricao(prescricao);
        }
        StatusPedido statusSelecionado = getStatusSelecionado();
        pedido.setStatus(statusSelecionado);
        pedido.setPronta_entrega(ProntaEntregaCheckBox.isSelected());
        pedido.setHabilitado(true);
        
        try {
            Registry registro = LocateRegistry.getRegistry("localhost",1099);
            InterfacePedido ipedido = (InterfacePedido) registro.lookup("Pedido");
            ipedido.inserirPedido(pedido);
            JOptionPane.showMessageDialog(null, "Pedido inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(CadastrarPedidoPanel.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_SalvarButtonActionPerformed

    private void ListaProdutoListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaProdutoListaMousePressed
        Produto produto = MostraPesquisaProdutos();
        
    }//GEN-LAST:event_ListaProdutoListaMousePressed

    private void PesquisaNomeProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeProdutoKeyReleased
        ListaDePesquisaTipoProduto();
    }//GEN-LAST:event_PesquisaNomeProdutoKeyReleased

    private void PesquisaNomeProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomeProdutoActionPerformed
        
    }//GEN-LAST:event_PesquisaNomeProdutoActionPerformed

    private void ListaClienteListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaClienteListaMousePressed
        Cliente cliente = MostraPesquisaCliente();
    }//GEN-LAST:event_ListaClienteListaMousePressed

    private void PesquisaNomeClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeClienteKeyReleased
        ListaDePesquisaCliente();
    }//GEN-LAST:event_PesquisaNomeClienteKeyReleased

    private void PesquisaNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomeClienteActionPerformed
        
    }//GEN-LAST:event_PesquisaNomeClienteActionPerformed

    private void statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusComboBoxActionPerformed

    private void PesquisaNomeEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaNomeEstoqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PesquisaNomeEstoqueActionPerformed

    private void PesquisaNomeEstoqueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PesquisaNomeEstoqueKeyReleased
        ListaDePesquisaEstoque();
    }//GEN-LAST:event_PesquisaNomeEstoqueKeyReleased

    private void ListaEstoqueListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaEstoqueListaMousePressed
        Estoque estoque = MostraPesquisaEstoque();
    }//GEN-LAST:event_ListaEstoqueListaMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Carrinho;
    private javax.swing.JList<String> ListaCliente;
    private javax.swing.JList<String> ListaEstoque;
    private javax.swing.JList<String> ListaFuncionario;
    private javax.swing.JList<String> ListaProduto;
    private javax.swing.JList<String> ListaUnidade;
    private javax.swing.JTextField PesquisaNomeCliente;
    private javax.swing.JTextField PesquisaNomeEstoque;
    private javax.swing.JTextField PesquisaNomeFuncionario;
    private javax.swing.JTextField PesquisaNomeProduto;
    private javax.swing.JTextField PesquisaNomeUnidade;
    private javax.swing.JTextField PrescricaoField;
    private javax.swing.JCheckBox ProntaEntregaCheckBox;
    private javax.swing.JButton SalvarButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JComboBox<String> statusComboBox;
    // End of variables declaration//GEN-END:variables
}

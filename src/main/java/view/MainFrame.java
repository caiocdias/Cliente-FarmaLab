package view;

import controller.InterfaceCliente;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 *
 * @author Isabely
 */
public class MainFrame extends javax.swing.JFrame {
    private final Map<String, JPanel> panelMap = new HashMap<>();
    
    public MainFrame() {
        initComponents();
        initPanels();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree2 = new javax.swing.JTree();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("JFrame"); // NOI18N

        jSplitPane1.setDividerLocation(200);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Menu");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Clientes");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Cadastrar Cliente");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Gerenciar Cliente");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Funcionários");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Cadastrar Funcionário");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Gerenciar Funcionário");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Médico e Prescrição");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Cadastrar Médico Parceiro");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Gerenciar Médico Parceiro");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Cadastrar Prescrição");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Unidade e Estoque");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Cadastrar Unidade");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Cadastrar Estoque");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Tributo");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Cadastrar Tributo");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Relacionar Unidade Tributo");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Pedidos");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Cadastrar Pedido");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Gerenciar Pedido");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Orçamentos");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Cadastrar Orçamento");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Gerenciar Orçamento");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Vendas");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Cadastrar Venda");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Gerenciar Venda");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Imprimir Nota Fiscal");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Área do Manipulador");
        treeNode1.add(treeNode2);
        jTree2.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree2.setName(""); // NOI18N
        jTree2.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jTree2);

        jSplitPane1.setLeftComponent(jScrollPane2);

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSplitPane1.setRightComponent(jTabbedPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void initPanels() {
        
        panelMap.put("Cadastrar Cliente", new CadastrarClientePanel());
        panelMap.put("Gerenciar Cliente", new GerenciarClientePanel());

        panelMap.put("Cadastrar Funcionário", new CadastrarFuncionarioPanel());
        panelMap.put("Gerenciar Funcionário", new GerenciarFuncionarioPanel());
        
        panelMap.put("Cadastrar Médico Parceiro", new CadastrarMedicoParcPanel());
        panelMap.put("Gerenciar Médico Parceiro", new GerenciarMedicoParcPanel());
        panelMap.put("Cadastrar Prescrição", new CadastrarPrescricaoPanel());

        panelMap.put("Cadastrar Orçamento", new CadastrarOrcamentoPanel());
        panelMap.put("Gerenciar Orçamento", new GerenciarOrcamentoPanel());
        
        panelMap.put("Cadastrar Pedido", new CadastrarPedidoPanel());
        //panelMap.put("Gerenciar Pedido", new GerenciarPedidoPanel());
        
        panelMap.put("Cadastrar Unidade", new CadastrarUnidadePanel());
        panelMap.put("Cadastrar Tributo", new CadastrarTributoPanel());
        panelMap.put("Cadastrar Estoque", new CadastrarEstoquePanel());
        //panelMap.put("Relacionar Unidade Tributo", new RelacionarUnidadeTributoPanel());

        //panelMap.put("Cadastrar Venda", new CadastrarVendaPanel());
        //panelMap.put("Gerenciar Venda", new GerenciarVendaPanel());
        //panelMap.put("Imprimir Nota Fiscal", new GerenciarVendaPanel());

        //panelMap.put("Área do Manipulador", new AreaManipuladorPanel());
    }
    
    private void jTree2ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree2ValueChanged
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree2.getLastSelectedPathComponent();

        if (selectedNode != null && selectedNode.isLeaf()) {
            String nodeName = selectedNode.toString();
            abrirAba(nodeName);
        }
    }//GEN-LAST:event_jTree2ValueChanged

    public class AbaComBotao extends JPanel {
        public AbaComBotao(JTabbedPane tabbedPane, String titulo) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setOpaque(false);

        JLabel label = new JLabel(titulo);
        add(label);

        JButton botaoFechar = new JButton();
        botaoFechar.setMargin(new Insets(0, 2, 0, 2)); 
        botaoFechar.setPreferredSize(new Dimension(12, 12)); 
        botaoFechar.setFocusable(false);
        

        botaoFechar.addActionListener(e -> fecharAba(tabbedPane, titulo));
        add(botaoFechar);
    }

        private void fecharAba(JTabbedPane tabbedPane, String titulo) {
            int index = tabbedPane.indexOfTab(titulo);
            if (index != -1) {
                tabbedPane.remove(index);
            }
        }
    }

    private void abrirAba(String titulo) {
        int index = jTabbedPane1.indexOfTab(titulo);
        if (index == -1) { 
            JPanel conteudo = panelMap.getOrDefault(titulo, criarAbaGenerica(titulo));
            jTabbedPane1.addTab(titulo, conteudo);
            int novaAbaIndex = jTabbedPane1.indexOfTab(titulo);
            jTabbedPane1.setTabComponentAt(novaAbaIndex, new AbaComBotao(jTabbedPane1, titulo));
        }
        jTabbedPane1.setSelectedIndex(jTabbedPane1.indexOfTab(titulo));
    }
    
    private JPanel criarAbaGenerica(String titulo) {
        JPanel defaultPanel = new JPanel();
        defaultPanel.add(new JLabel("Conteúdo não definido para: " + titulo));
        return defaultPanel;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTree jTree2;
    // End of variables declaration//GEN-END:variables
}

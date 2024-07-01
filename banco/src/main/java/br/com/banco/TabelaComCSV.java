package br.com.banco;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaComCSV extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabela;
    private DefaultTableModel model;

    public TabelaComCSV() {
        super("Tabela com Importação CSV");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        // CRIA OS DADOS DA TABELA VAZIO
        Object[][] dados = {};

        // CRIA AS COLUNAS DA TABELA
        Object[] colunas = {"ID", "AGENCIA", "CONTA", "SALDO"};

        // CRIA O MODELO DA TABELA COM OS DADOS E COLUNAS
        model = new DefaultTableModel(dados, colunas);

        // CRIA A TABELA COM O MODELO CRIADO
        tabela = new JTable(model);

        // CRIA UM PAINEL COM SCROLL PARA EXIBIR A TABELA
        JScrollPane scrollPane = new JScrollPane(tabela);

        // ADICIONA O PAINEL COM SCROLL AO FRAME
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // CRIA UM BOTÃO PARA IMPORTAR CSV
        JButton btnImportarCSV = new JButton("Importar CSV");
        btnImportarCSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importarCSV();
            }
        });

        // ADICIONA O BOTÃO NA PARTE INFERIOR DO FRAME
        getContentPane().add(btnImportarCSV, BorderLayout.SOUTH);
    }
    


    private void importarCSV() {
        JFileChooser fileChooser = new JFileChooser();
        int escolha = fileChooser.showOpenDialog(this);

        if (escolha == JFileChooser.APPROVE_OPTION) {
            try {
                // LIMPA A TABELA ANTES DE IMPORTAR NOVOS DADOS
                model.setRowCount(0);

                FileReader reader = new FileReader(fileChooser.getSelectedFile());
                BufferedReader br = new BufferedReader(reader);
                
                int proximoID = 1; // REINICIA O ID SEQUENCIAL
                
                // IGNORA A PRIMEIRA LINHA (CABEÇALHO) // String LINHA;
                String linha = br.readLine(); // LÊ A PRIMEIRA LINHA (CABEÇALHO) E DESCARTA
                
                while ((linha = br.readLine()) != null) {
                    String[] dados = linha.split(",");
                   

                    // ADICIONA O ID AUTOMÁTICO AO INÍCIO DOS DADOS
                    String[] dadosComID = new String[dados.length + 1];
                    dadosComID[0] = String.valueOf(proximoID++); // ID SEQUENCIAL
                    System.arraycopy(dados, 0, dadosComID, 1, dados.length);

                    // ADICIONA OS DADOS NA TABELA
                    model.addRow(dadosComID);
                }

                br.close();
                reader.close();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao importar arquivo CSV: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

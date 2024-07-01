package br.com.banco;

import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TabelaComCSV frame = new TabelaComCSV();
                frame.setVisible(true);
            }
        });
    }
}



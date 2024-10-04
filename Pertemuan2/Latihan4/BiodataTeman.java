import java.awt.event.*;
import javax.swing.*;

public class BiodataTeman extends JFrame {

    public BiodataTeman() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label dan TextField untuk Nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(50, 40, 100, 10);
        
        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(150, 40, 200, 30);
        
        // Label dan TextField untuk Nomor Telepon
        JLabel labelTelepon = new JLabel("Nomor Telepon:");
        labelTelepon.setBounds(50, 80, 100, 10);
        
        JTextField textFieldTelepon = new JTextField();
        textFieldTelepon.setBounds(150, 80, 200, 30);

        // Tombol untuk menambahkan biodata ke dalam JTextArea
        JButton button = new JButton("Tambahkan Biodata");
        button.setBounds(150, 120, 200, 40);
        
        // JTextArea untuk menampilkan biodata
        JTextArea txtOutput = new JTextArea();
        txtOutput.setBounds(50, 180, 300, 200); 
        txtOutput.setEditable(false); // Agar JTextArea tidak dapat diedit
        
        // Menambahkan aksi pada tombol
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldNama.getText();
                String telepon = textFieldTelepon.getText();

                if (!nama.isEmpty() && !telepon.isEmpty()) {
                    txtOutput.append("Nama: " + nama + "\n");
                    txtOutput.append("Nomor Telepon: " + telepon + "\n");
                    txtOutput.append("===============================\n"); // Separator antara setiap biodata
                    
                    // Mengosongkan field input setelah data dimasukkan
                    textFieldNama.setText("");
                    textFieldTelepon.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Harap masukkan nama dan nomor telepon!");
                }
            }
        });

        // Menambahkan komponen ke JFrame
        this.add(button);
        this.add(textFieldNama);
        this.add(labelNama);
        this.add(textFieldTelepon);
        this.add(labelTelepon);
        this.add(txtOutput);
        
        this.setSize(400, 450);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BiodataTeman frame = new BiodataTeman();
                frame.setVisible(true);
            }
        });
    }
}
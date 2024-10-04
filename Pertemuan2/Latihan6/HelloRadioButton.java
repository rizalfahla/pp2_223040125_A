import java.awt.event.*;
import javax.swing.*;

public class HelloRadioButton extends JFrame {

    public HelloRadioButton() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label untuk Input Nama
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        // TextField untuk input nama
        JTextField textField = new JTextField();
        textField.setBounds(15, 60, 350, 30);

        // Label untuk Radio Button (Jenis Member)
        JLabel labelRadio = new JLabel("Jenis Member:");
        labelRadio.setBounds(15, 100, 350, 10);

        // Radio Buttons untuk pilihan jenis member
        JRadioButton radioButton1 = new JRadioButton("Silver", true);
        radioButton1.setBounds(15, 115, 350, 30);

        JRadioButton radioButton2 = new JRadioButton("Gold");
        radioButton2.setBounds(15, 145, 350, 30);

        JRadioButton radioButton3 = new JRadioButton("Platinum");
        radioButton3.setBounds(15, 175, 350, 30);

        // Grouping radio buttons
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);
        bg.add(radioButton3);

        // Tombol Simpan
        JButton button = new JButton("Simpan");
        button.setBounds(15, 205, 100, 40);

        // TextArea untuk output
        JTextArea txtOutput = new JTextArea();
        txtOutput.setBounds(15, 250, 350, 100);
        txtOutput.setEditable(false); // Membuat textarea tidak bisa diedit oleh user

        // Action listener untuk tombol Simpan
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mendapatkan jenis member yang dipilih
                String jenisMember = "";
                if (radioButton1.isSelected()) {
                    jenisMember = radioButton1.getText();
                } else if (radioButton2.isSelected()) {
                    jenisMember = radioButton2.getText();
                } else if (radioButton3.isSelected()) {
                    jenisMember = radioButton3.getText();
                }

                // Mendapatkan nama dari TextField
                String nama = textField.getText();

                // Menampilkan output di TextArea
                txtOutput.append("Hello " + nama + "\n");
                txtOutput.append("Anda adalah member " + jenisMember + "\n");

                // Mengosongkan TextField setelah data disimpan
                textField.setText("");
            }
        });

        // Menambahkan komponen ke JFrame
        this.add(button);
        this.add(textField);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(radioButton3);
        this.add(labelInput);
        this.add(txtOutput);

        // Setting ukuran JFrame
        this.setSize(400, 500);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloRadioButton h = new HelloRadioButton();
                h.setVisible(true);
            }
        });
    }
}
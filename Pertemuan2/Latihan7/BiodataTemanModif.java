import java.awt.event.*;
import javax.swing.*;

public class BiodataTemanModif extends JFrame{

    private boolean checkBoxSelected;


    public BiodataTemanModif(){
        this.checkBoxSelected = false;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label untuk Input Nama
        JLabel labelInput = new JLabel("Nama");
        labelInput.setBounds(15,10,350,10);
        
        JTextField textField = new JTextField();
        textField.setBounds(15,30,350,30);

        // Label untuk Input Nomor
        JLabel labelInput2 = new JLabel("Nomor HP: ");
        labelInput2.setBounds(15,80,350,10);
        
        JTextField textField2 = new JTextField();
        textField2.setBounds(15,100,350,30);

        // Radio Buttons untuk pilihan
        JLabel labelRadio = new JLabel("Jenis Kelamin");
        labelRadio.setBounds(15,132,350,10);

        JRadioButton radioButton1 = new JRadioButton("Laki-Laki",true);
        radioButton1.setBounds(15,145,350,30);

        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15,170,350,30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        // CheckBox 
        JCheckBox checkBox = new JCheckBox("Warga Negara Asing");
        checkBox.setBounds(15,200,350,30);

        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e){
                checkBoxSelected = e.getStateChange() == 1;
            }
        });



        JButton button = new JButton("Simpan");
        button.setBounds(15,230,100,40);

        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(15,280,350,100);


        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String jenisKelamin = "";
                String WNA = "";
                if(radioButton1.isSelected()){
                    jenisKelamin = radioButton1.getText();
                }
                if(radioButton2.isSelected()){
                    jenisKelamin = radioButton2.getText();
                }

                if(checkBox.isSelected()){
                    WNA = "Ya";
                } else {
                    WNA = "Tidak";
                }

                String nama = textField.getText();
                String nohp = textField2.getText();
                txtOutput.setText(" ");
                txtOutput.append("Nama: " + nama + "\n");
                txtOutput.append("Nomor HP: " + nohp + "\n");
                txtOutput.append("Jenis Kelamin: " + jenisKelamin + "\n");
                txtOutput.append("WNA: " + WNA + "\n");
                txtOutput.append("==============\n");
                textField.setText("");
                textField2.setText("");
            }
        });


        this.add(button);
        this.add(textField);
        this.add(textField2);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelInput2);
        this.add(labelInput);
        this.add(txtOutput);
        this.add(checkBox);

        this.setSize(400,500);
        this.setLayout(null);

    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                BiodataTemanModif h = new BiodataTemanModif();
                h.setVisible(true);
            }
        });
    }
}
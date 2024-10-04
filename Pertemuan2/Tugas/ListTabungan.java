import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ListTabungan extends JFrame {
    private JTextField textFieldNama, textFieldHP;
    private JRadioButton radioLakiLaki, radioPerempuan;
    private JCheckBox checkBoxWNA;
    private JTextArea txtOutput;
    private JList<String> listTabungan;
    private JSlider slider;
    private JPasswordField passwordField, confirmPasswordField;
    private ButtonGroup group;
    private JSpinner spinnerTanggalLahir;  

    public ListTabungan() {
        // Set default close operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout to null
        this.setLayout(null);

        // Membuat menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Menambahkan menu item ke menu
        menu.add(resetItem);
        menu.add(exitItem);

        // Menambahkan menu ke menu bar
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        // ActionListener untuk Reset
        resetItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetForm(); // Panggil method resetForm untuk membersihkan inputan dan output
            }
        });

        // ActionListener untuk Exit
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Keluar dari aplikasi
            }
        });

        // Label Nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(20, 20, 100, 20);
        this.add(labelNama);

        // TextField Nama
        textFieldNama = new JTextField();
        textFieldNama.setBounds(130, 20, 200, 25);
        this.add(textFieldNama);

        // Label Nomor HP
        JLabel labelHP = new JLabel("Nomor HP:");
        labelHP.setBounds(20, 60, 100, 20);
        this.add(labelHP);

        // TextField Nomor HP
        textFieldHP = new JTextField();
        textFieldHP.setBounds(130, 60, 200, 25);
        this.add(textFieldHP);

        // Label Tanggal Lahir
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        labelTanggalLahir.setBounds(20, 100, 100, 20);
        this.add(labelTanggalLahir);

        // JSpinner untuk Tanggal Lahir
        SpinnerDateModel model = new SpinnerDateModel();
        spinnerTanggalLahir = new JSpinner(model);
        spinnerTanggalLahir.setBounds(130, 100, 200, 25);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd/MM/yyyy");
        spinnerTanggalLahir.setEditor(editor);  // Menggunakan editor untuk hanya menampilkan tanggal, bulan, dan tahun
        this.add(spinnerTanggalLahir);

        // Label Jenis Kelamin
        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin:");
        labelJenisKelamin.setBounds(20, 140, 100, 20);
        this.add(labelJenisKelamin);

        // Radio Button Laki-Laki
        radioLakiLaki = new JRadioButton("Laki-Laki");
        radioLakiLaki.setBounds(130, 140, 100, 20);
        this.add(radioLakiLaki);

        // Radio Button Perempuan
        radioPerempuan = new JRadioButton("Perempuan");
        radioPerempuan.setBounds(130, 170, 100, 20);
        this.add(radioPerempuan);

        // Group for radio buttons
        group = new ButtonGroup();
        group.add(radioLakiLaki);
        group.add(radioPerempuan);

        // Checkbox Warga Negara Asing
        checkBoxWNA = new JCheckBox("Warga Negara Asing");
        checkBoxWNA.setBounds(130, 200, 200, 20);
        this.add(checkBoxWNA);

        // Label untuk jenis tabungan
        JLabel labelTabungan = new JLabel("Jenis Tabungan:");
        labelTabungan.setBounds(20, 240, 100, 20);
        this.add(labelTabungan);

        // List pilihan jenis tabungan
        String[] tabunganOptions = { "Tabungan Rumah", "Tabungan Pendidikan", "Tabungan Investasi", "Tabungan Umum" };
        listTabungan = new JList<>(tabunganOptions);
        listTabungan.setBounds(130, 240, 200, 80);
        listTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // hanya bisa memilih satu
        this.add(listTabungan);

        // Label untuk frekuensi transaksi
        JLabel labelFrekuensi = new JLabel("Frekuensi Transaksi (per bulan):");
        labelFrekuensi.setBounds(20, 340, 250, 20);
        this.add(labelFrekuensi);

        // JSlider untuk frekuensi transaksi
        slider = new JSlider(0, 100, 0);
        slider.setBounds(15, 370, 350, 50);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);
        this.add(slider);  // Jangan lupa menambahkan slider ke frame

        // Label Password
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(20, 430, 100, 20);
        this.add(labelPassword);

        // PasswordField untuk Password
        passwordField = new JPasswordField();
        passwordField.setBounds(130, 430, 200, 25);
        this.add(passwordField);

        // Label Confirm Password
        JLabel labelConfirmPassword = new JLabel("Confirm Password:");
        labelConfirmPassword.setBounds(20, 470, 150, 20);
        this.add(labelConfirmPassword);

        // PasswordField untuk Confirm Password
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(170, 470, 160, 25);
        this.add(confirmPasswordField);

        // Button Simpan
        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(130, 510, 100, 30);
        this.add(buttonSimpan);

        // TextArea for output
        txtOutput = new JTextArea();
        txtOutput.setEditable(false); // Tidak dapat di-edit oleh user

        // JScrollPane untuk JTextArea
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBounds(20, 550, 350, 100); // Set ukuran area scroll
        this.add(scrollPane);

        // ActionListener for button
        buttonSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simpanBiodata();
            }
        });

        // Set frame size and visibility
        this.setSize(450, 700);
        this.setVisible(true);
    }

    // Method untuk menyimpan dan menampilkan biodata
    private void simpanBiodata() {
        String nama = textFieldNama.getText();
        String nomorHP = textFieldHP.getText();
        Date tanggalLahir = (Date) spinnerTanggalLahir.getValue(); // Mengambil nilai dari spinner
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Format untuk tanggal tanpa jam
        String formattedTanggalLahir = sdf.format(tanggalLahir); // Format tanggal lahir
        String jenisKelamin = radioLakiLaki.isSelected() ? "Laki-Laki" : radioPerempuan.isSelected() ? "Perempuan" : "";
        String wna = checkBoxWNA.isSelected() ? "Ya" : "Tidak";
        String jenisTabungan = listTabungan.getSelectedValue();
        int frekuensi = slider.getValue();  // Mengambil nilai dari slider, bukan spinner

        // Validasi apakah tabungan dipilih
        if (jenisTabungan == null) {
            jenisTabungan = "Belum Dipilih";
        }

        // Validasi password
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String passwordMessage = password.equals(confirmPassword) ? "Password cocok" : "Password tidak cocok";

        // Menampilkan output
        txtOutput.append("Nama       : " + nama + "\n");
        txtOutput.append("Nomor HP   : " + nomorHP + "\n");
        txtOutput.append("Tanggal Lahir : " + formattedTanggalLahir + "\n");  // Tanggal lahir tanpa jam
        txtOutput.append("Jenis Kelamin : " + jenisKelamin + "\n");
        txtOutput.append("WNA        : " + wna + "\n");
        txtOutput.append("Jenis Tabungan : " + jenisTabungan + "\n");
        txtOutput.append("Frekuensi Transaksi : " + frekuensi + "\n");
        txtOutput.append(passwordMessage + "\n");
        txtOutput.append("============================================\n");
    }

    // Method untuk reset form
    private void resetForm() {
        textFieldNama.setText("");
        textFieldHP.setText("");
        group.clearSelection();
        checkBoxWNA.setSelected(false);
        listTabungan.clearSelection();
        spinnerTanggalLahir.setValue(new Date());  // Set tanggal lahir kembali ke hari ini
        slider.setValue(0);  // Set slider kembali ke 0
        passwordField.setText("");
        confirmPasswordField.setText("");
        txtOutput.setText("");
    }

    public static void main(String[] args) {
        new ListTabungan();
    }
}

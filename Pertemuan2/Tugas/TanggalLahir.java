import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TanggalLahir extends JFrame {
    private JTextField textFieldNama, textFieldHP;
    private JRadioButton radioLakiLaki, radioPerempuan;
    private JCheckBox checkBoxWNA;
    private JTextArea txtOutput;
    private JList<String> listTabungan;
    private JSpinner spinnerFrekuensi, spinnerTanggalLahir;
    private JPasswordField passwordField, confirmPasswordField;
    private ButtonGroup group;

    public TanggalLahir() {
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

        // Label Password
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(20, 100, 100, 20);
        this.add(labelPassword);

        // PasswordField untuk Password
        passwordField = new JPasswordField();
        passwordField.setBounds(130, 100, 200, 25);
        this.add(passwordField);

        // Label Confirm Password
        JLabel labelConfirmPassword = new JLabel("Confirm Password:");
        labelConfirmPassword.setBounds(20, 140, 150, 20);
        this.add(labelConfirmPassword);

        // PasswordField untuk Confirm Password
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(170, 140, 160, 25);
        this.add(confirmPasswordField);

        // Label Tanggal Lahir
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        labelTanggalLahir.setBounds(20, 180, 100, 20);
        this.add(labelTanggalLahir);

        // Spinner untuk Tanggal Lahir
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        spinnerTanggalLahir = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd/MM/yyyy");
        spinnerTanggalLahir.setEditor(dateEditor);
        spinnerTanggalLahir.setBounds(130, 180, 200, 25);
        this.add(spinnerTanggalLahir);

        // Label Jenis Kelamin
        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin:");
        labelJenisKelamin.setBounds(20, 220, 100, 20);
        this.add(labelJenisKelamin);

        // Radio Button Laki-Laki
        radioLakiLaki = new JRadioButton("Laki-Laki");
        radioLakiLaki.setBounds(130, 220, 100, 20);
        this.add(radioLakiLaki);

        // Radio Button Perempuan
        radioPerempuan = new JRadioButton("Perempuan");
        radioPerempuan.setBounds(130, 250, 100, 20);
        this.add(radioPerempuan);

        // Group for radio buttons
        group = new ButtonGroup();
        group.add(radioLakiLaki);
        group.add(radioPerempuan);

        // Checkbox Warga Negara Asing
        checkBoxWNA = new JCheckBox("Warga Negara Asing");
        checkBoxWNA.setBounds(130, 280, 200, 20);
        this.add(checkBoxWNA);

        // Label untuk jenis tabungan
        JLabel labelTabungan = new JLabel("Jenis Tabungan:");
        labelTabungan.setBounds(20, 310, 100, 20);
        this.add(labelTabungan);

        // List pilihan jenis tabungan
        String[] tabunganOptions = { "Tabungan Pendidikan", "Tabungan Haji", "Tabungan Investasi", "Tabungan Umum" };
        listTabungan = new JList<>(tabunganOptions);
        listTabungan.setBounds(130, 310, 200, 80);
        listTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // hanya bisa memilih satu
        this.add(listTabungan);

        // Label untuk frekuensi transaksi
        JLabel labelFrekuensi = new JLabel("Frekuensi Transaksi (per bulan):");
        labelFrekuensi.setBounds(20, 400, 250, 20);
        this.add(labelFrekuensi);

        // Spinner untuk frekuensi transaksi
        SpinnerModel model = new SpinnerNumberModel(1, 1, 100, 1); // nilai default, minimum, maksimum, increment
        spinnerFrekuensi = new JSpinner(model);
        spinnerFrekuensi.setBounds(280, 400, 50, 25);
        this.add(spinnerFrekuensi);

        // Button Simpan
        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(130, 440, 100, 30);
        this.add(buttonSimpan);

        // TextArea for output
        txtOutput = new JTextArea();
        txtOutput.setEditable(false); // Tidak dapat di-edit oleh user

        // JScrollPane untuk JTextArea
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBounds(20, 480, 350, 150); // Set ukuran area scroll
        this.add(scrollPane);

        // ActionListener for button
        buttonSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simpanBiodata();
            }
        });

        // Set frame size and visibility
        this.setSize(400, 700);
        this.setVisible(true);
    }

    // Method untuk menyimpan dan menampilkan biodata
    private void simpanBiodata() {
        String nama = textFieldNama.getText();
        String nomorHP = textFieldHP.getText();
        String jenisKelamin = radioLakiLaki.isSelected() ? "Laki-Laki" : radioPerempuan.isSelected() ? "Perempuan" : "";
        String wna = checkBoxWNA.isSelected() ? "Ya" : "Tidak";
        String jenisTabungan = listTabungan.getSelectedValue();
        int frekuensi = (Integer) spinnerFrekuensi.getValue();

        // Validasi apakah tabungan dipilih
        if (jenisTabungan == null) {
            jenisTabungan = "Belum Dipilih";
        }

        // Validasi password
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String passwordMessage = password.equals(confirmPassword) ? "Password cocok" : "Password tidak cocok";

        // Ambil nilai tanggal lahir dari spinner
        Date tanggalLahir = (Date) spinnerTanggalLahir.getValue();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String tanggalLahirFormatted = dateFormat.format(tanggalLahir);

        // Menampilkan output
        txtOutput.append("Nama                : " + nama + "\n");
        txtOutput.append("Nomor HP            : " + nomorHP + "\n");
        txtOutput.append("Tanggal Lahir       : " + tanggalLahirFormatted + "\n");
        txtOutput.append("Jenis Kelamin       : " + jenisKelamin + "\n");
        txtOutput.append("WNA                 : " + wna + "\n");
        txtOutput.append("Jenis Tabungan      : " + jenisTabungan + "\n");
        txtOutput.append("Frekuensi Transaksi : " + frekuensi + "\n");
        txtOutput.append(passwordMessage + "\n");
        txtOutput.append("============================================\n");
    }

    // Method untuk reset form
    private void resetForm() {
        textFieldNama.setText("");
        textFieldHP.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        group.clearSelection();
        checkBoxWNA.setSelected(false);
        listTabungan.clearSelection();
        spinnerFrekuensi.setValue(1); // Reset spinner ke nilai default
        txtOutput.setText("");
        spinnerTanggalLahir.setValue(new Date()); // Reset tanggal lahir ke tanggal hari ini
    }

    public static void main(String[] args) {
        new TanggalLahir();
    }
}
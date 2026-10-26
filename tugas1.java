import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class tugas1 {

    private static final Color BIRU = new Color(41, 98, 255);
    private static final Color LATAR = new Color(243, 246, 251);
    private static final Color TEKS = new Color(35, 48, 68);
    private static final Color ABU = new Color(105, 119, 140);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(tugas1::tampilkanForm);
    }

    private static void tampilkanForm() {

        // Jendela utama
        JFrame frame = new JFrame("Aplikasi Data Barang");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel utama
        JPanel utama = new JPanel(new BorderLayout(0, 20));
        utama.setBackground(LATAR);
        utama.setBorder(new EmptyBorder(24, 28, 24, 28));

        // Header
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);

        JLabel judul = new JLabel("FORM DATA BARANG");
        judul.setFont(new Font("SansSerif", Font.BOLD, 23));
        judul.setForeground(TEKS);
        judul.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subjudul = new JLabel(
                "Masukkan informasi barang yang ingin didata.");
        subjudul.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subjudul.setForeground(ABU);
        subjudul.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(judul);
        header.add(Box.createVerticalStrut(7));
        header.add(subjudul);

        // Panel form
        JPanel kartu = new JPanel(new BorderLayout(0, 20));
        kartu.setBackground(Color.WHITE);
        kartu.setBorder(new EmptyBorder(24, 22, 22, 22));

        JLabel judulForm = new JLabel("Informasi Barang");
        judulForm.setFont(new Font("SansSerif", Font.BOLD, 16));
        judulForm.setForeground(TEKS);

        JPanel form = new JPanel(new GridLayout(3, 2, 12, 16));
        form.setBackground(Color.WHITE);

        JTextField namaField = buatField();
        JTextField stokField = buatField();
        JTextField hargaField = buatField();

        tambahBaris(form, "Nama Barang", namaField);
        tambahBaris(form, "Stok Awal", stokField);
        tambahBaris(form, "Harga (Rp)", hargaField);

        // Bagian tengah kartu
        JPanel bagianForm = new JPanel(new BorderLayout(0, 20));
        bagianForm.setBackground(Color.WHITE);
        bagianForm.add(judulForm, BorderLayout.NORTH);
        bagianForm.add(form, BorderLayout.CENTER);

        // Bagian bawah: status dan tombol
        JPanel bawah = new JPanel(new BorderLayout(12, 12));
        bawah.setBackground(Color.WHITE);

        JLabel statusLabel = new JLabel(
                "Silakan lengkapi semua data barang.");
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        statusLabel.setForeground(ABU);

        JButton simpanButton = new JButton("Simpan Data");
        simpanButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        simpanButton.setForeground(Color.WHITE);
        simpanButton.setBackground(BIRU);
        simpanButton.setFocusPainted(false);
        simpanButton.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        simpanButton.setBorder(
                new EmptyBorder(12, 22, 12, 22));
        simpanButton.setOpaque(true);

        bawah.add(statusLabel, BorderLayout.CENTER);
        bawah.add(simpanButton, BorderLayout.EAST);

        kartu.add(bagianForm, BorderLayout.CENTER);
        kartu.add(bawah, BorderLayout.SOUTH);

        // Aksi tombol Simpan
        simpanButton.addActionListener(event -> {

            String nama = namaField.getText().trim();
            String teksStok = stokField.getText().trim();
            String teksHarga = hargaField.getText().trim();

            if (nama.isEmpty() || teksStok.isEmpty()
                    || teksHarga.isEmpty()) {

                statusLabel.setForeground(new Color(200, 55, 55));
                statusLabel.setText(
                        "Semua kolom wajib diisi!");
                return;
            }

            try {
                int stok = Integer.parseInt(teksStok);

                // Titik diperbolehkan sebagai pemisah ribuan
                String angkaHarga = teksHarga.replace(".", "");

                long harga = Long.parseLong(angkaHarga);

                if (stok < 0 || harga < 0) {
                    statusLabel.setForeground(
                            new Color(200, 55, 55));
                    statusLabel.setText(
                            "Stok dan harga tidak boleh negatif!");
                    return;
                }

                NumberFormat rupiah = NumberFormat.getNumberInstance(
                        new Locale("id", "ID"));

                statusLabel.setForeground(
                        new Color(27, 140, 85));

                statusLabel.setText(
                        "<html><b>Data valid!</b> "
                        + nama + " | Stok: " + stok
                        + " | Harga: Rp "
                        + rupiah.format(harga)
                        + "</html>");

            } catch (NumberFormatException e) {

                statusLabel.setForeground(
                        new Color(200, 55, 55));

                statusLabel.setText(
                        "Stok harus bilangan bulat dan harga harus angka!");
            }
        });

        // Susunan akhir jendela
        utama.add(header, BorderLayout.NORTH);
        utama.add(kartu, BorderLayout.CENTER);

        frame.setContentPane(utama);
        frame.pack();
        frame.setMinimumSize(new Dimension(540, 330));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Membuat kolom input
    private static JTextField buatField() {

        JTextField field = new JTextField(18);
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setForeground(TEKS);
        field.setBackground(new Color(250, 251, 254));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 226, 236)),
                new EmptyBorder(10, 12, 10, 12)));

        return field;
    }

    // Menambahkan label dan kolom ke form
    private static void tambahBaris(
            JPanel panel, String teks, JTextField field) {

        JLabel label = new JLabel(teks);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(TEKS);

        panel.add(label);
        panel.add(field);
    }
}
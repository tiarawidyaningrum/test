public class Peminjaman {
    private Kurir kurir;
    private Kendaraan kendaraan;
    private String tanggalPinjam;
    private String tanggalKembali;

    public Peminjaman(Kurir kurir, Kendaraan kendaraan, String tanggalPinjam) {
        this.kurir = kurir;
        this.kendaraan = kendaraan;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = null;
    }

    // Setter
    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    // Getter
    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public Kurir getKurir() {
        return kurir;
    }

    public String getTanggalKembali() { // <<< PENTING: Method ini harus ada
        return tanggalKembali;
    }
    
    public void displayInfo() {
        System.out.println("=== Detail Peminjaman ===");
        System.out.println("Kurir: " + kurir.getNamaKurir());
        System.out.println("Kendaraan: " + kendaraan.getMerek() + " (" + kendaraan.getNoPlat() + ")");
        System.out.println("Tanggal Pinjam: " + tanggalPinjam);
        System.out.println("Tanggal Kembali: " + (tanggalKembali == null ? "Belum Kembali" : tanggalKembali));
    }
}
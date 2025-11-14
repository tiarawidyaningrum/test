import java.util.ArrayList;

public class PTBurhan {
    // Atribut 
    private String namaPerusahaan;
    private ArrayList<Kurir> daftarKurir;
    private ArrayList<Paket> daftarPaket;
    private double totalProfit;
    private ArrayList<Kendaraan> daftarKendaraan;
    private ArrayList<Peminjaman> daftarPeminjaman;

    public PTBurhan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
        this.daftarKurir = new ArrayList<>();
        this.daftarPaket = new ArrayList<>();
        this.totalProfit = 0.0;
        this.daftarKendaraan = new ArrayList<>();
        this.daftarPeminjaman = new ArrayList<>();
        System.out.println("Perusahaan " + namaPerusahaan + " berhasil dibuat!");
    }

    // Tambah Kurir (Menu 1)
    public void tambahKurir(String tipe, String nama, int kapasitas) {
        for (Kurir k : daftarKurir) {
            if (k.getNamaKurir().equalsIgnoreCase(nama)) {
                System.out.println("Gagal: Kurir dengan nama " + nama + " sudah terdaftar.");
                return;
            }
        }

        Kurir kurirBaru = null;
        if (tipe.equalsIgnoreCase("Tetap")) {
            kurirBaru = new KurirTetap(nama, kapasitas);
        } else if (tipe.equalsIgnoreCase("Freelance")) {
            kurirBaru = new KurirFreelance(nama, kapasitas);
        } else {
            System.out.println("Gagal: Tipe kurir tidak valid!");
            return;
        }

        daftarKurir.add(kurirBaru);
        System.out.println("Kurir " + nama + " bergabung ke " + this.namaPerusahaan);
    }

    // Tambah Paket (Menu 2)
    public void tambahPaket(String tipe, String namaPenerima) {
        Paket paketBaru = null;
        if (tipe.equalsIgnoreCase("SameDay")) {
            paketBaru = new PaketSameDay(namaPenerima);
        } else if (tipe.equalsIgnoreCase("NextDay")) {
            paketBaru = new PaketNextDay(namaPenerima);
        } else if (tipe.equalsIgnoreCase("Hemat")) {
            paketBaru = new PaketHemat(namaPenerima);
        } else {
            System.out.println("Gagal: Tipe paket tidak valid!");
            return;
        }

        daftarPaket.add(paketBaru);
        System.out.println("Paket baru untuk " + namaPenerima + " dimasukkan ke sistem " + this.namaPerusahaan);
    }

    // Tampilkan Semua Kurir (Menu 3)
    public void tampilkanSemuaKurir() {
        System.out.println("\n=== Daftar Kurir di " + this.namaPerusahaan + " ===");
        if (daftarKurir.isEmpty()) {
            System.out.println("Belum ada kurir terdaftar.");
            return;
        }
        for (Kurir k : daftarKurir) {
            k.detailKurir();
        }
    }

    // Tampilkan Semua Paket (Menu 4)
    public void tampilkanSemuaPaket() {
        System.out.println("\n=== Daftar Paket di " + this.namaPerusahaan + " ===");
        if (daftarPaket.isEmpty()) {
            System.out.println("Belum ada paket terdaftar.");
            return;
        }
        for (Paket p : daftarPaket) {
            p.detailPaket();
            System.out.println(); 
        }
    }

    // Assign Paket (Menu 5)
    public void assignPaket(String namaKurir, String noTracking) {
        Kurir kurir = cariKurir(namaKurir);
        Paket paket = cariPaket(noTracking);

        if (kurir == null) {
            System.out.println("Gagal: Kurir " + namaKurir + " tidak ditemukan!");
            return;
        }
        if (paket == null) {
            System.out.println("Gagal: Paket dengan no tracking " + noTracking + " tidak ditemukan!");
            return;
        }
        
        kurir.addJob(paket);
    }

    // Selesaikan Paket (Menu 6)
    public void selesaikanPaket(String namaKurir, String noTracking) {
        Kurir kurir = cariKurir(namaKurir);

        if (kurir == null) {
            System.out.println("Gagal: Kurir " + namaKurir + " tidak ditemukan!");
            return;
        }
        
        double tax = kurir.paketDiterima(noTracking);
        
        // Tambahkan tax ke profit perusahaan
        this.totalProfit += tax;
    }

    // Laporan Keuangan (Menu 7)
    public void lihatLaporanKeuangan() {
        System.out.println("\n=== Laporan Keuangan " + this.namaPerusahaan + " ===");
        System.out.printf("Total Profit: Rp%.0f\n", this.totalProfit);
    }
    
    // Pencarian Paket (Menu 8)
    // menampilkan semua paket dengan nama penerima
    public void pencarianPaket(String namaPenerima) {
        System.out.println("\nPencarian Paket " + namaPenerima);
        ArrayList<Paket> ditemukan = new ArrayList<>();
    
        for (Paket p : daftarPaket) {
            if (p.getNamaPenerima().equalsIgnoreCase(namaPenerima)) { 
                ditemukan.add(p);
            }
        }

        if (ditemukan.isEmpty()) {
            System.out.println("Tidak ada paket ditemukan dengan nama penerima: " + namaPenerima);
        } else {
            System.out.println(ditemukan.size() + " paket ditemukan:");
            for (int i = 0; i < ditemukan.size(); i++) {
                System.out.println("Paket " + (i + 1) + ":");
                ditemukan.get(i).detailPaket();
            }
        }
    }

    // (Menu 9)
    public void tambahKendaraan(String tipe, String noPlat, String merek, int tahun, int detail) {
        for (Kendaraan k : daftarKendaraan) {
            if (k.getNoPlat().equalsIgnoreCase(noPlat)) {
                System.out.println("Gagal: Kendaraan dengan plat " + noPlat + " sudah terdaftar.");
                return;
            }
        }

        Kendaraan kendaraanBaru = null;
        if (tipe.equalsIgnoreCase("Mobil")) {
            kendaraanBaru = new Mobil(noPlat, merek, tahun, detail); // detail = jumlahPintu
        } else if (tipe.equalsIgnoreCase("Motor")) {
            kendaraanBaru = new Motor(noPlat, merek, tahun, detail); // detail = kapasitasMesinCC
        } else {
            System.out.println("Gagal: Tipe kendaraan tidak valid!");
            return;
        }

        daftarKendaraan.add(kendaraanBaru);
        System.out.println("Kendaraan " + merek + " (" + noPlat + ") berhasil ditambahkan.");
    }

    // Tampilkan Semua Kendaraan (Menu Baru)
    public void tampilkanSemuaKendaraan() {
        System.out.println("\n=== Daftar Kendaraan di " + this.namaPerusahaan + " ===");
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada kendaraan terdaftar.");
            return;
        }
        for (Kendaraan k : daftarKendaraan) {
            k.displayInfo();
        }
    }

    // Pinjam Kendaraan (Menu Baru)
    public void pinjamKendaraan(String namaKurir, String noPlatKendaraan, String tanggalPinjam) {
        Kurir kurir = cariKurir(namaKurir);
        Kendaraan kendaraan = cariKendaraan(noPlatKendaraan);

        if (kurir == null) {
            System.out.println("Gagal: Kurir " + namaKurir + " tidak ditemukan!");
            return;
        }
        if (kendaraan == null) {
            System.out.println("Gagal: Kendaraan dengan plat " + noPlatKendaraan + " tidak ditemukan!");
            return;
        }
        if (kendaraan.isDipinjam()) {
            System.out.println("Gagal: Kendaraan dengan plat " + noPlatKendaraan + " sedang dipinjam!");
            return;
        }
        if (kurir.getKendaraanSaatIni() != null) {
            System.out.println("Gagal: Kurir " + namaKurir + " sudah meminjam kendaraan lain!");
            return;
        }

        kurir.pinjamKendaraan(kendaraan);

        Peminjaman peminjamanBaru = new Peminjaman(kurir, kendaraan, tanggalPinjam);
        daftarPeminjaman.add(peminjamanBaru);
        
        System.out.println("Peminjaman berhasil! Kendaraan " + kendaraan.getMerek() + " (" + kendaraan.getNoPlat() + ") dipinjam oleh " + kurir.getNamaKurir());
    }
    
    // Kembalikan Kendaraan (Menu Baru)
    public void kembalikanKendaraan(String namaKurir, String tanggalKembali) {
        Kurir kurir = cariKurir(namaKurir);

        if (kurir == null) {
            System.out.println("Gagal: Kurir " + namaKurir + " tidak ditemukan!");
            return;
        }
        
        Kendaraan k = kurir.kembalikanKendaraan();
        
        if (k == null) {
            return;
        }

        boolean found = false;
        for (Peminjaman p : daftarPeminjaman) {
            if (p.getKurir() == kurir && p.getKendaraan() == k && p.getTanggalKembali() == null) {
                p.setTanggalKembali(tanggalKembali);
                System.out.println("Pengembalian berhasil. Peminjaman dicatat selesai pada tanggal " + tanggalKembali);
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Error: Data peminjaman aktif tidak ditemukan. Kendaraan dikembalikan, tetapi catatan peminjaman bermasalah.");
        }
    }
    
    // Tampilkan Riwayat Peminjaman (Menu Baru)
    public void tampilkanRiwayatPeminjaman() {
        System.out.println("\n=== Riwayat Peminjaman Kendaraan ===");
        if (daftarPeminjaman.isEmpty()) {
            System.out.println("Tidak ada riwayat peminjaman.");
            return;
        }
        for (Peminjaman p : daftarPeminjaman) {
            p.displayInfo();
        }
    }

    // Helper
    public Kurir cariKurir(String namaKurir) {
        for (Kurir k : daftarKurir) {
            if (k.getNamaKurir().equalsIgnoreCase(namaKurir)) {
                return k;
            }
        }
        return null;
    }

    public Paket cariPaket(String noTracking) {
        for (Paket p : daftarPaket) {
            if (p.getNoTracking().equalsIgnoreCase(noTracking)) {
                return p;
            }
        }
        return null;
    }

    public Kendaraan cariKendaraan(String noPlat) { // <<< PENTING: Method ini harus ada di PTBurhan.java
    for (Kendaraan k : daftarKendaraan) {
        if (k.getNoPlat().equalsIgnoreCase(noPlat)) {
            return k;
        }
    }
    return null;
}
    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }
}
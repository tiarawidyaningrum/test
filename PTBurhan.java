import java.util.ArrayList;

public class PTBurhan {
    // Atribut 
    private String namaPerusahaan;
    private ArrayList<Kurir> daftarKurir;
    private ArrayList<Paket> daftarPaket;
    private double totalProfit;

    public PTBurhan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
        this.daftarKurir = new ArrayList<>();
        this.daftarPaket = new ArrayList<>();
        this.totalProfit = 0.0;
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

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }
}
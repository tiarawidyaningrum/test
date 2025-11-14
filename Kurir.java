public class Kurir {
    // Atribut 
    protected String namaKurir;
    protected double gajiPokok;
    protected int kapasitas;
    protected Paket[] listPaket; 
    protected int countPaket;  
    protected Kendaraan kendaraanSaatIni; 

    // Konstruktor 
    public Kurir(String nama, int kapasitas) {
        this.namaKurir = nama;
        this.gajiPokok = 0.0;
        this.kapasitas = kapasitas;
        this.listPaket = new Paket[kapasitas];
        this.countPaket = 0;
        this.kendaraanSaatIni = null;
    }

    public void detailKurir() {
        System.out.println("=== Info Kurir ===");
        System.out.println("Nama Kurir: " + this.namaKurir);
        System.out.println("Gaji Kurir: " + (int)Math.round(this.gajiPokok)); 
        System.out.println("Kapasitas Kurir: " + this.countPaket + " dari " + this.kapasitas + " paket");

        if (this.kendaraanSaatIni != null) {
            System.out.println("Kendaraan Dipinjam: " + this.kendaraanSaatIni.getMerek() + " (" + this.kendaraanSaatIni.getNoPlat() + ")");
        } else {
            System.out.println("Kendaraan Dipinjam: -");
        }

        System.out.println();
    }

    public void addJob(Paket paket) {
        if (!paket.getStatus().equals("Menunggu Kurir")) {
            System.out.println("Paket telah diambil oleh kurir lain!");
            return;
        }

        if (countPaket >= kapasitas) {
            System.out.println("Kapasitas kurir " + this.namaKurir + " sudah penuh");
            return;
        }

        listPaket[countPaket] = paket;
        countPaket++;
        
        paket.setStatus("Dikirim oleh Kurir " + this.namaKurir);
        
        System.out.println("Paket telah diambil oleh Kurir " + this.namaKurir);
    }
    
    public void printListPaket() {
        System.out.println("=================== List Penerima ===================");
        if (this.countPaket == 0) {
            System.out.println("Tidak ada paket yang sedang dikirim.");
        } else {
            for (int i = 0; i < this.countPaket; i++) {
                System.out.println("Paket " + (i + 1) + ":");
                this.listPaket[i].detailPaket(); 
            }
        }
    }

    public double paketDiterima(String noTracking) {
        int indexDitemukan = -1;
        
        for (int i = 0; i < this.countPaket; i++) {
            if (this.listPaket[i] != null && this.listPaket[i].getNoTracking().equals(noTracking)) {
                indexDitemukan = i;
                break;
            }
        }

        if (indexDitemukan != -1) {
            Paket paketSelesai = this.listPaket[indexDitemukan];
            
            // Cek status
            if (paketSelesai.getStatus().equals("Diterima")) {
                System.out.println("Paket dengan nomor tracking:\n=> " + noTracking + " sudah diterima");
                return 0.0;
            }
            
            double feePajak = paketSelesai.getFee() * 0.10;      // 10% Tax/Profit
            double feeBersih = paketSelesai.getFee() - feePajak; // 90% untuk kurir
            
            this.gajiPokok += feeBersih;
            
            paketSelesai.setStatus("Diterima");
            
            for (int i = indexDitemukan; i < this.countPaket - 1; i++) {
                this.listPaket[i] = this.listPaket[i + 1];
            }

            this.listPaket[this.countPaket - 1] = null;
            this.countPaket--;
            
            System.out.println("Paket dengan nomor tracking:\n=> " + noTracking + " sudah diterima oleh " + paketSelesai.getNamaPenerima());
            
            return feePajak; 
            
        } else {
            System.out.println("Paket tidak ditemukan!");
            return 0.0;
        }
    }

    public void pinjamKendaraan(Kendaraan k) {
        if (this.kendaraanSaatIni != null) {
            return;
        }
        
        this.kendaraanSaatIni = k;
        k.setDipinjam(true);
    }

    public Kendaraan kembalikanKendaraan() {
        if (this.kendaraanSaatIni == null) {
            System.out.println("Kurir " + this.namaKurir + " tidak sedang meminjam kendaraan.");
            return null;
        }

        Kendaraan k = this.kendaraanSaatIni;
        k.setDipinjam(false);
        this.kendaraanSaatIni = null;
        
        return k; 
    }

    public Kendaraan getKendaraanSaatIni() {
        return kendaraanSaatIni;
    }
    
    public String getNamaKurir() {
        return namaKurir;
    }

    public double hitungGaji() {
        return this.gajiPokok;
    }
}
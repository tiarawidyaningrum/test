public class KurirFreelance extends Kurir {
    private int totalJamTerbang;

    public KurirFreelance(String nama, int kapasitas) {
        super(nama, kapasitas);
        this.totalJamTerbang = 0;
    }
    
    public double hitungGaji() {
        return this.gajiPokok + (this.gajiPokok * 0.01 * this.totalJamTerbang);
    }

    public void paketDiterima(String noTracking) {
        int indexDitemukan = -1;
        
        // 1. Cari paket
        for (int i = 0; i < this.countPaket; i++) {
            if (this.listPaket[i] != null && this.listPaket[i].getNoTracking().equals(noTracking)) {
                indexDitemukan = i;
                break;
            }
        }

        if (indexDitemukan != -1) {
            Paket paketSelesai = this.listPaket[indexDitemukan];
            
            // Cek status paket agar tidak menggaji dua kali
            if (paketSelesai.getStatus().equals("Diterima")) {
                System.out.println("Paket dengan nomor tracking:\n=> " + noTracking + " sudah diterima");
                return;
            }
            
            // LOGIKA SPESIFIK KurirFreelance: Tambahkan totalJamTerbang
            this.totalJamTerbang += paketSelesai.getEta();
            
            // 2. Tambahkan gajiPokok dengan fee paket
            this.gajiPokok += paketSelesai.getFee();
            
            // 3. Update status paket
            paketSelesai.setStatus("Diterima");
            
            // 4. Geser array (Kompaksi)
            for (int i = indexDitemukan; i < this.countPaket - 1; i++) {
                this.listPaket[i] = this.listPaket[i + 1];
            }
            // Kosongkan elemen terakhir dan kurangi countPaket
            this.listPaket[this.countPaket - 1] = null;
            this.countPaket--;
            
            // 5. Output
            System.out.println("Paket dengan nomor tracking:\n=> " + noTracking + " sudah diterima");
            
        } else {
            System.out.println("Paket tidak ditemukan!");
        }
    }
}
public class KurirFreelance extends Kurir {
    private int totalJamTerbang;

    public KurirFreelance(String nama, int kapasitas) {
        super(nama, kapasitas);
        this.totalJamTerbang = 0;
    }
    @Override
    public double hitungGaji() {
        return this.gajiPokok + (this.gajiPokok * 0.01 * this.totalJamTerbang);
    }

    @Override
    public double paketDiterima(String noTracking) {
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
            
            if (paketSelesai.getStatus().equals("Diterima")) {
                System.out.println("Paket dengan nomor tracking:\n=> " + noTracking + " sudah diterima");
                return 0.0;
            }
            
            this.totalJamTerbang += paketSelesai.getEta();
            
            double feePajak = paketSelesai.getFee() * 0.10;
            double feeBersih = paketSelesai.getFee() - feePajak;
            
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
}
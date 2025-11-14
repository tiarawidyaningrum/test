public class PaketHemat extends Paket {
    public PaketHemat(String nama) {
        super(nama, "Hemat");
        
        this.fee = 10000.0;
        this.eta = 48; 
    }

    @Override
    public void detailPaket() {
        super.detailPaket();
        System.out.println("Estimasi Waktu: " + this.eta + " Jam");
        System.out.println("Biaya tambahan: " + (int)this.fee);
        System.out.println();
    }
}
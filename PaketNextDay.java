public class PaketNextDay extends Paket {
    public PaketNextDay(String nama) {
        super(nama, "NextDay");
        
        this.fee = 15000.0;
        this.eta = 24; 
    }

    @Override
    public void detailPaket() {
        super.detailPaket();
        System.out.println("Estimasi Waktu: " + this.eta + " Jam");
        System.out.println("Biaya tambahan: " + (int)this.fee);
        System.out.println();
    }
}
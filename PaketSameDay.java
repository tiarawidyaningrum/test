public class PaketSameDay extends Paket {
    public PaketSameDay(String nama) {
        super(nama, "SameDay"); 
        
        this.fee = 25000.0;
        this.eta = 8; 
    }

    @Override
    public void detailPaket() {
        super.detailPaket();
        System.out.println("Estimasi Waktu: " + this.eta + " Jam");
        System.out.println("Biaya tambahan: " + (int)this.fee);
        System.out.println();
    }
}
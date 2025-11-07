public class KurirFreelance extends Kurir {
    private int totalJamTerbang;

    public KurirFreelance(String nama, int kapasitas) {
        super(nama, kapasitas);
        this.totalJamTerbang = 0;
    }
    public double hitungGaji() {
        return this.gajiPokok + (this.gajiPokok * 0.01 * this.totalJamTerbang);
    }
}
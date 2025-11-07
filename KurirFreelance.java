public class KurirFreelance extends Kurir {
    private int totalJamTerbang;

    public KurirFreelance(String nama, int kapasitas) {
        super(nama, kapasitas);
        this.totalJamTerbang = 0;
    }
}
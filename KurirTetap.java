public class KurirTetap extends Kurir {

    public KurirTetap(String nama, int kapasitas) {
        super(nama, kapasitas);
    }

    @Override
    public double hitungGaji() {
        // Gaji + 75% dari gajiPokok
        return this.gajiPokok * 1.75;
    }
}
public class Kurir {
    // Atribut 
    protected String namaKurir;
    protected double gajiPokok;
    protected int kapasitas;
    protected Paket[] listPaket; 
    protected int countPaket;   

    // Konstruktor 
    public Kurir(String nama, int kapasitas) {
        this.namaKurir = nama;
        this.gajiPokok = 0.0;
        this.kapasitas = kapasitas;
        this.listPaket = new Paket[kapasitas];
        this.countPaket = 0;
    }

    // Getter
    public void detailKurir() {
        System.out.println("=== Info Kurir ===");
        System.out.println("Nama Kurir: " + this.namaKurir);
        System.out.println("Gaji Kurir: " + (int)this.gajiPokok); 
        System.out.println("Kapasitas Kurir: " + this.kapasitas + " paket");
        System.out.println();
    }
}
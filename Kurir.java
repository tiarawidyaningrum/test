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
        System.out.println("Kapasitas Kurir: " + this.countPaket + " dari " + this.kapasitas + " paket");
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
    
    public String getNamaKurir() {
        return namaKurir;
    }

    public double hitungGaji() {
        return this.gajiPokok;
    }
}
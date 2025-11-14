public class Mobil extends Kendaraan {
    private int jumlahPintu;

    public Mobil(String noPlat, String merek, int tahun, int jumlahPintu) {
        super(noPlat, merek, tahun);
        this.jumlahPintu = jumlahPintu;
    }

    @Override
    public void displayInfo() {
        System.out.println("Jenis Kendaraan: Mobil");
        System.out.println("  No. Plat: " + this.noPlat);
        System.out.println("  Merek: " + this.merek);
        System.out.println("  Tahun: " + this.tahun);
        System.out.println("  Jumlah Pintu: " + this.jumlahPintu);
        System.out.println("  Status: " + (this.isDipinjam ? "Dipinjam" : "Tersedia"));
    }
}
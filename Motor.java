public class Motor extends Kendaraan {
    private int kapasitasMesinCC;

    public Motor(String noPlat, String merek, int tahun, int kapasitasMesinCC) {
        super(noPlat, merek, tahun);
        this.kapasitasMesinCC = kapasitasMesinCC;
    }

    @Override
    public void displayInfo() {
        System.out.println("Jenis Kendaraan: Motor");
        System.out.println("  No. Plat: " + this.noPlat);
        System.out.println("  Merek: " + this.merek);
        System.out.println("  Tahun: " + this.tahun);
        System.out.println("  Kapasitas Mesin: " + this.kapasitasMesinCC + " CC");
        System.out.println("  Status: " + (this.isDipinjam ? "Dipinjam" : "Tersedia"));
    }
}
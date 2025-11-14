public abstract class Kendaraan {
    // Atribut
    protected String noPlat;
    protected String merek;
    protected int tahun;
    protected boolean isDipinjam;

    // Konstruktor
    public Kendaraan(String noPlat, String merek, int tahun) {
        this.noPlat = noPlat;
        this.merek = merek;
        this.tahun = tahun;
        this.isDipinjam = false;
    }

    // Method abstrak
    public abstract void displayInfo();

    // Getter
    public String getNoPlat() {
        return noPlat;
    }

    public String getMerek() {
        return merek;
    }

    public boolean isDipinjam() {
        return isDipinjam;
    }

    // Setter
    public void setDipinjam(boolean isDipinjam) {
        this.isDipinjam = isDipinjam;
    }
}
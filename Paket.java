import java.util.UUID;

public class Paket {
    // Atribut
    protected String noTracking;
    protected String namaPenerima;
    protected String metodePengiriman;
    protected String status;
    protected double fee;
    protected int eta;

    // Konstruktor
    public Paket(String nama, String metode) {
        this.noTracking = UUID.randomUUID().toString();
        this.namaPenerima = nama;
        this.metodePengiriman = metode;
        this.status = "Menunggu Kurir";
        this.fee = 0.0;
        this.eta = 0;
    }

    // Getter
    public void detailPaket() {
        System.out.println("=== Info Paket ===");
        System.out.println("Nomor Tracking: " + this.noTracking);
        System.out.println("Nama Penerima: " + this.namaPenerima);
        System.out.println("Metode Pengiriman: " + this.metodePengiriman);
        System.out.println("Status Paket: " + this.status);
    }

    // Sub-Task 2
    // Setter
    public void setStatus(String statusBaru) {
        this.status = statusBaru;
    }

    public String getNoTracking() {
        return noTracking;
    }

    public String getStatus() {
        return status;
    }

    public double getFee() {
        return fee;
    }

    public int getEta() {
        return eta;
    }
}
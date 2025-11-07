public class BurhanpediaTP4 {
    public static void main(String[] args) {
        Paket sameDay = new PaketSameDay("Test1");
        Paket nextDay = new PaketNextDay("Test2");

        sameDay.detailPaket();
        nextDay.detailPaket();

        Kurir tetap = new KurirTetap("Bonita", 5);
        Kurir freelance = new KurirFreelance("Sofita", 5);

        tetap.detailKurir();
        freelance.detailKurir();
    }
}
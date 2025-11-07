public class BurhanpediaTP4 {
    public static void main(String[] args) {
        Paket sameDay = new PaketSameDay("Test1");
        Paket nextDay = new PaketNextDay("Test2");
        Paket newPaket = new PaketNextDay("Test3");

        sameDay.detailPaket();
        nextDay.detailPaket();
        newPaket.detailPaket();

        Kurir tetap = new KurirTetap("Bonita", 2);
        Kurir freelance = new KurirFreelance("Sofita", 1);

        tetap.addJob(nextDay);
        freelance.addJob(sameDay);
        freelance.addJob(newPaket);
        tetap.addJob(sameDay);
        System.out.println();

        tetap.detailKurir();
        freelance.detailKurir();

        sameDay.detailPaket();
        nextDay.detailPaket();
        newPaket.detailPaket();
    }
}
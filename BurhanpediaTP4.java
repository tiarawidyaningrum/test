public class BurhanpediaTP4 {
    public static void main(String[] args) {
        Paket sameDay = new PaketSameDay("Test1");
        Paket nextDay = new PaketNextDay("Test2");
        Paket newPaket = new PaketNextDay("Test3");

        String[] listNoTracking = {
            sameDay.getNoTracking(),
            nextDay.getNoTracking(),
            newPaket.getNoTracking(),
        };

        Kurir tetap = new KurirTetap("Bonita", 2);
        Kurir freelance = new KurirFreelance("Sofita", 1);

        sameDay.detailPaket();
        nextDay.detailPaket();
        newPaket.detailPaket();

        tetap.addJob(nextDay);
        freelance.addJob(sameDay);
        freelance.addJob(newPaket); 
        tetap.addJob(sameDay);     
        System.out.println();

        tetap.detailKurir();
        freelance.detailKurir();
        
        tetap.addJob(newPaket); 
        System.out.println();
        
        tetap.printListPaket();
        freelance.printListPaket();
        System.out.println(); 

        tetap.paketDiterima(listNoTracking[0]); 
        tetap.paketDiterima(listNoTracking[1]);
        System.out.println(); 
        tetap.paketDiterima(listNoTracking[2]);
        System.out.println(); 
        
        freelance.paketDiterima(listNoTracking[2]);
        freelance.paketDiterima(listNoTracking[0]);
        System.out.println();

        tetap.detailKurir();
        freelance.detailKurir();
    }
}
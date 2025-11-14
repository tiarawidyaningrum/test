import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PTBurhanTest {
    private PTBurhan perusahaan;

    @Before
    public void setUp() {
        // Inisialisasi ulang PTBurhan sebelum setiap test
        perusahaan = new PTBurhan("TestExpress");

        // Tambah Kurir (Membutuhkan KurirTetap/Freelance atau Kurir base class)
        perusahaan.tambahKurir("Tetap", "Bambang", 5);
        perusahaan.tambahKurir("Freelance", "Joko", 3);

        // Tambah Kendaraan
        perusahaan.tambahKendaraan("Mobil", "B1234ABC", "Toyota Avanza", 2020, 4); // 4 pintu
        perusahaan.tambahKendaraan("Motor", "D5678XYZ", "Honda Beat", 2022, 125); // 125 CC
    }

    // 2. Method testTambahKendaraanSuccess() should be public
    @Test
    public void testTambahKendaraanSuccess() {
        // Cek kendaraan yang ditambahkan di setUp
        Kendaraan mobil = perusahaan.cariKendaraan("B1234ABC");
        Kendaraan motor = perusahaan.cariKendaraan("D5678XYZ");

        assertNotNull(mobil);
        assertEquals("Toyota Avanza", mobil.getMerek());
        assertFalse(mobil.isDipinjam());

        assertNotNull(motor);
        assertEquals("Honda Beat", motor.getMerek());
        assertFalse(motor.isDipinjam());
    }

    // 5. Method testPinjamKendaraanSuccess() should be public
    @Test
    public void testPinjamKendaraanSuccess() {
        String namaKurir = "Bambang";
        String noPlat = "B1234ABC";
        String tanggal = "14/11/2025";
        
        perusahaan.pinjamKendaraan(namaKurir, noPlat, tanggal);

        Kurir kurir = perusahaan.cariKurir(namaKurir);
        Kendaraan kendaraan = perusahaan.cariKendaraan(noPlat);

        // Assert status kendaraan dan kurir
        assertTrue(kendaraan.isDipinjam());
        assertNotNull(kurir.getKendaraanSaatIni());
        assertEquals(noPlat, kurir.getKendaraanSaatIni().getNoPlat());
    }

    // 9. Method testPinjamKendaraanAlreadyBorrowedFailure() should be public
    @Test
    public void testPinjamKendaraanAlreadyBorrowedFailure() {
        // 1. Pinjam pertama kali
        perusahaan.pinjamKendaraan("Bambang", "B1234ABC", "14/11/2025");
        
        // 2. Coba pinjam kendaraan yang sama oleh kurir lain
        perusahaan.pinjamKendaraan("Joko", "B1234ABC", "14/11/2025");
        
        // Assert: Joko tidak berhasil pinjam (masih null)
        Kurir joko = perusahaan.cariKurir("Joko");
        assertNull(joko.getKendaraanSaatIni());
        
        // 3. Coba pinjam kendaraan lain oleh Bambang (kurir sudah pinjam)
        perusahaan.pinjamKendaraan("Bambang", "D5678XYZ", "14/11/2025");
        
        // Assert: Bambang tetap dengan kendaraan lamanya
        // Catatan: Akses ke getKendaraanSaatIni() di Joko tetap harus diuji untuk memastikan kegagalan pinjam kedua
        Kendaraan bambangVehicle = perusahaan.cariKurir("Bambang").getKendaraanSaatIni();
        assertNotNull(bambangVehicle);
        assertEquals("B1234ABC", bambangVehicle.getNoPlat());
    }

    // 6. Method testKembalikanKendaraanSuccess() should be public
    @Test
    public void testKembalikanKendaraanSuccess() {
        String namaKurir = "Bambang";
        String noPlat = "B1234ABC";
        String tanggalPinjam = "14/11/2025";
        String tanggalKembali = "15/11/2025";
        
        // Pinjam dulu
        perusahaan.pinjamKendaraan(namaKurir, noPlat, tanggalPinjam);
        
        // Kembalikan
        perusahaan.kembalikanKendaraan(namaKurir, tanggalKembali);

        Kurir kurir = perusahaan.cariKurir(namaKurir);
        Kendaraan kendaraan = perusahaan.cariKendaraan(noPlat);
        
        // Assert status kendaraan dan kurir
        assertFalse(kendaraan.isDipinjam());
        assertNull(kurir.getKendaraanSaatIni());
    }

    // 7. Method testPaketHematCreation() should be public
    @Test
    public void testPaketHematCreation() {
        perusahaan.tambahPaket("Hemat", "Diana");
        
        // Ambil Paket yang baru ditambahkan (memerlukan daftarPaket package-private)
        Paket paket = perusahaan.daftarPaket.get(perusahaan.daftarPaket.size() - 1); 

        // Verifikasi Fee (Biaya tambahan: Rp10.000)
        assertEquals(10000.0, paket.getFee(), 0.001); 

        // Verifikasi ETA (Estimasi waktu: 48 jam)
        assertEquals(48, paket.getEta());
    }
    
    // 4. Method testGajiKurirFreelanceDenganPaketHemat() should be public
    @Test
    public void testGajiKurirFreelanceDenganPaketHemat() {
        // Setup: Fee bersih PaketHemat = 10000 * 0.90 = 9000. ETA = 48 jam.
        perusahaan.tambahPaket("Hemat", "Penerima X");
        String noTracking = perusahaan.daftarPaket.get(perusahaan.daftarPaket.size() - 1).getNoTracking();
        
        perusahaan.assignPaket("Joko", noTracking);
        perusahaan.selesaikanPaket("Joko", noTracking);
        
        KurirFreelance joko = (KurirFreelance) perusahaan.cariKurir("Joko");
        
        // Gaji total = 9000 + (9000 * 0.01 * 48) = 13320.0
        assertEquals(13320.0, joko.hitungGaji(), 0.001); 
    }
    
    // --- TEST INTEGRASI PAKET DAN KURIR ---
    
    // 3. Method testAssignPaketAndStatusChange() should be public
    @Test
    public void testAssignPaketAndStatusChange() {
        // Asumsi PaketNextDay ada
        perusahaan.tambahPaket("NextDay", "Mario");
        String noTracking = perusahaan.daftarPaket.get(perusahaan.daftarPaket.size() - 1).getNoTracking();
        
        perusahaan.assignPaket("Bambang", noTracking); 
        
        Kurir bambang = perusahaan.cariKurir("Bambang");
        
        // Cek paket ter-assign
        assertEquals(1, bambang.countPaket);
        assertEquals("Dikirim oleh Kurir Bambang", bambang.listPaket[0].getStatus());
    }

    // 8. Method testTotalProfitPerusahaan() should be public
    @Test
    public void testTotalProfitPerusahaan() {
        // SameDay: Fee 25000. Profit = 2500
        perusahaan.tambahPaket("SameDay", "P1");
        String trackingP1 = perusahaan.daftarPaket.get(perusahaan.daftarPaket.size() - 1).getNoTracking();
        perusahaan.assignPaket("Bambang", trackingP1);
        perusahaan.selesaikanPaket("Bambang", trackingP1);
        
        // NextDay: Fee 15000. Profit = 1500
        perusahaan.tambahPaket("NextDay", "P2");
        String trackingP2 = perusahaan.daftarPaket.get(perusahaan.daftarPaket.size() - 1).getNoTracking();
        perusahaan.assignPaket("Joko", trackingP2);
        perusahaan.selesaikanPaket("Joko", trackingP2);
        
        // Total Profit = 2500 + 1500 = 4000
        assertEquals(4000.0, perusahaan.totalProfit, 0.001);
    }

    // 10. Method testTambahKurirDuplicateFailure() should be public
    @Test
    public void testTambahKurirDuplicateFailure() {
        int initialSize = perusahaan.daftarKurir.size();
        perusahaan.tambahKurir("Tetap", "Bambang", 5); // Coba tambah Bambang lagi
        
        // Ukuran array harus tetap (2)
        assertEquals(initialSize, perusahaan.daftarKurir.size());
    }
}
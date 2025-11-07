import java.util.Scanner;

public class BurhanpediaTP4 {
    
    // ASCII Art untuk BURHAN EKSPRESS
    private static void printAscii() {
        System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                      ║");
        System.out.println("║   __ _  _ ___ _  _  __  __  _   ___ _  __ __  ___ ___ ___  __   __   ║");
        System.out.println("║  |  | || | _ | || |/  \\   || | | __| |/ /' _/| _,| _\\ __/' _//' _/   ║");
        System.out.println("║  | -< || | v / >< | /| | | ' | | _||   <`._`.| v_/ v / _|`._`.`._`.  ║");
        System.out.println("║  |__/\\__/|_|_|_||_|_||_|_| \\_| |___|_||_|___/|_| |_|_\\__|___/|___/   ║");
        System.out.println("║                                                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
        System.out.println();
    } 
    // Method untuk menampilkan menu
    private static void displayMenu(String namaPerusahaan) {
        System.out.println("\n=== MENU " + namaPerusahaan + " ===");
        System.out.println("1. Tambah Kurir");
        System.out.println("2. Tambah Paket");
        System.out.println("3. Tampilkan Semua Kurir");
        System.out.println("4. Tampilkan Semua Paket");
        System.out.println("5. Assign Paket ke Kurir");
        System.out.println("6. Selesaikan Paket");
        System.out.println("7. Lihat Laporan Keuangan");
        System.out.println("8. Keluar dari " + namaPerusahaan);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. Initial Greetings
        printAscii();
        System.out.print("Masukan nama kamu: ");
        String namaPengguna = sc.nextLine();
        System.out.println("Hallo, " + namaPengguna + "! Selamat datang di Burhan Express");

        System.out.print("\nMasukkan nama perusahaan: ");
        String namaPT = sc.nextLine();
        // Buat objek perusahaan utama
        PTBurhan perusahaan = new PTBurhan(namaPT);
        
        boolean running = true;
        
        // 2. Main Menu Loop
        while (running) {
            displayMenu(perusahaan.getNamaPerusahaan());
            System.out.print("Pilih menu: ");
            
            // Validasi input integer
            if (!sc.hasNextInt()) {
                System.out.println("Pilihan tidak valid!");
                sc.nextLine(); // Buang input yang salah
                continue;
            }
            
            int pilihan = sc.nextInt();
            sc.nextLine(); // Buang newline
            
            switch (pilihan) {
                case 1: // Tambah Kurir
                    System.out.print("Masukkan tipe kurir (Freelance/Tetap): ");
                    String tipeKurir = sc.nextLine().trim();
                    System.out.print("Masukkan nama kurir: ");
                    String namaKurir = sc.nextLine().trim();
                    System.out.print("Masukkan kapasitas kurir: ");
                    // Memastikan input kapasitas adalah integer
                    int kapasitasKurir = 0;
                    if (sc.hasNextInt()) {
                        kapasitasKurir = sc.nextInt();
                        sc.nextLine();
                    } else {
                        System.out.println("Kapasitas tidak valid!");
                        sc.nextLine(); // Buang input yang salah
                        break;
                    }
                    
                    perusahaan.tambahKurir(tipeKurir, namaKurir, kapasitasKurir);
                    break;
                    
                case 2: // Tambah Paket
                    System.out.print("Masukkan tipe paket (SameDay/NextDay): ");
                    String tipePaket = sc.nextLine().trim();
                    System.out.print("Masukkan nama penerima: ");
                    String namaPenerima = sc.nextLine().trim();
                    perusahaan.tambahPaket(tipePaket, namaPenerima);
                    break;
                    
                case 3: // Tampilkan Semua Kurir
                    perusahaan.tampilkanSemuaKurir();
                    break;
                    
                case 4: // Tampilkan Semua Paket
                    perusahaan.tampilkanSemuaPaket();
                    break;
                    
                case 5: // Assign Paket ke Kurir
                    System.out.print("Masukkan nama kurir: ");
                    String namaKurirAssign = sc.nextLine().trim();
                    System.out.print("Masukkan nomor tracking: ");
                    String noTrackingAssign = sc.nextLine().trim();
                    perusahaan.assignPaket(namaKurirAssign, noTrackingAssign);
                    break;

                case 6: // Selesaikan Paket
                    System.out.print("Masukkan nama kurir: ");
                    String namaKurirSelesai = sc.nextLine().trim();
                    System.out.print("Masukkan nomor tracking paket: ");
                    String noTrackingSelesai = sc.nextLine().trim();
                    perusahaan.selesaikanPaket(namaKurirSelesai, noTrackingSelesai);
                    break;
                    
                case 7: // Lihat Laporan Keuangan
                    perusahaan.lihatLaporanKeuangan();
                    break;

                case 8: // Keluar
                    System.out.println("Keluar dari " + perusahaan.getNamaPerusahaan() + "...");
                    System.out.println("\nTerima kasih telah menggunakan layanan Burhan Express!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Menu tidak valid. Silakan pilih antara 1 hingga 8.");
                    break;
            }
        }

        sc.close();
    }
}
package week04.milano.id.ac.umn;
import java.util.Scanner;

class Barang {
    private int id, stock, harga;
    private String nama;

    public Barang(int id, String nama, int stock, int harga) {
        this.id = id;
        this.nama = nama;
        this.stock = stock;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }
    public int getStock() {
        return stock;
    }
    public int getHarga() {
        return harga;
    }
    public String getNama() {
        return nama;
    }

    public void minusStock(int qty) {
        stock -= qty;
    }
}

class Order {
    private int id, jumlah;
    private Barang barang;
    public static int total = 0;

    public Order(int id, Barang barang, int jumlah) {
        this.id = id;
        this.barang = barang;
        this.jumlah = jumlah;
        total += barang.getHarga() * jumlah;
    }

    public int getId() {
        return id;
    }
    public int getJumlah() {
        return jumlah;
    }
    public Barang getBarang() {
        return barang;
    }
}

public class Assignment {
    static Scanner sc = new Scanner(System.in);


    static Barang[] daftarBarang = {
            new Barang(1, "Pulpen Easy Gel 0.5mm", 120, 2000),
            new Barang(2, "Penggaris 30cm", 20, 5000),
            new Barang(3, "Tipe-x Roller", 30, 7000),
            new Barang(4, "Pensil Mekanik", 50, 5000),
            new Barang(5, "Buku Tulis", 100, 6000)
    };

    static Order[] daftarOrder = new Order[100];
    static int jumlahOrder = 0;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n-------------Menu Toko Multiguna-------------");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.print("Pilihan : ");
            int pilih = sc.nextInt();

            switch (pilih) {
                case 1:
                    pesanBarang();
                    break;
                case 2:
                    lihatPesanan();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Menu tidak tersedia!");
            }
        }
    }

    static void tampilBarang() {
        System.out.println("\nDaftar Barang Toko Multiguna");
        for (Barang b : daftarBarang) {
            System.out.println("ID    : " + b.getId());
            System.out.println("Nama  : " + b.getNama());
            System.out.println("Stock : " + b.getStock());
            System.out.println("Harga : " + b.getHarga());
            System.out.println("--------------------------------------");
        }
    }

    static void pesanBarang() {
        tampilBarang();
        System.out.println("Ketik 0 untuk batal");
        System.out.print("Pesan Barang (ID) : ");
        int id = sc.nextInt();
        if (id == 0) return;

        Barang dipilih = null;
        for (Barang b : daftarBarang) {
            if (b.getId() == id) {
                dipilih = b;
                break;
            }
        }

        if (dipilih == null) {
            return;
        }

        int qty;
        while (true) {
            System.out.print("Masukkan Jumlah : ");
            qty = sc.nextInt();
            if (qty > 0 && qty <= dipilih.getStock()) {
                break;
            }
        }

        int totalHarga = dipilih.getHarga() * qty;
        System.out.println(qty + " @ " + dipilih.getNama() + " dengan total harga " + totalHarga);

        System.out.print("Masukkan jumlah uang : ");
        int uang = sc.nextInt();

        if (uang < totalHarga) {
            System.out.println("Jumlah Uang Tidak Mencukupi");
            return;
        }

        dipilih.minusStock(qty);
        daftarOrder[jumlahOrder++] = new Order(jumlahOrder + 1, dipilih, qty);
        System.out.println("Berhasil dipesan");
    }

    static void lihatPesanan() {
        System.out.println("\nDaftar Pesanan Toko Multiguna");
        if (jumlahOrder == 0) {
            System.out.println("Belum ada pesanan.");
            return;
        }
        for (int i = 0; i < jumlahOrder; i++) {
            Order o = daftarOrder[i];
            System.out.println("ID     : " + o.getId());
            System.out.println("Nama   : " + o.getBarang().getNama());
            System.out.println("Jumlah : " + o.getJumlah());
            System.out.println("Total  : " + (o.getBarang().getHarga() * o.getJumlah()));
            System.out.println("--------------------------------------");
        }
    }
}
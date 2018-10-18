import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {

	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        
        List<Jegy> jegyek = new ArrayList<Jegy>();

        Beolvas(jegyek);
        Listaz(jegyek);
        
        UjJegy(jegyek);
        Listaz(jegyek);
        FajlbaIr(jegyek);
        
    }

    private static void Beolvas(List<Jegy> jegyek) throws ParseException {
        File f = new File("jegyek.txt");
      
        try {
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine()){
                String sor=scan.nextLine();
                String[] adatok = sor.split(";");
                Jegy j = new Jegy();
                j.nev = adatok[0];
                j.targy = adatok[1];
                j.jegy = Integer.parseInt(adatok[2]);
                j.datum =df.parse(adatok[3]);
                jegyek.add(j);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Nem taláható a fájl!");
        }
        
    }

    private static void Listaz(List<Jegy> jegyek) {
        for (int i = 0; i < jegyek.size(); i++) {
            System.out.println(jegyek.get(i));
        }
    }

    private static void UjJegy(List<Jegy> jegyek) {
        Jegy j = new Jegy();
        System.out.println("Kérem a nevet!");
        j.nev = sc.nextLine();
        System.out.println("Kérem a targyat!");
        j.targy=sc.nextLine();
        System.out.println("Kérem a jegyet!");
        j.jegy =Integer.parseInt(sc.nextLine());
        System.out.println("Kérem a dátumat!");
        try {
            j.datum=df.parse(sc.nextLine());
        } catch (ParseException ex) {
            System.out.println("Érvénytelen dátum!");;
        }
        jegyek.add(j);
    }

    private static void FajlbaIr(List<Jegy> jegyek) {
        try {
            PrintStream f2 = new PrintStream(new File("jegyek.txt"));
            
            for (Jegy j : jegyek) {
                f2.println(j.nev+";"+j.targy+";"+j.jegy+";"+j.df.format(j.datum));
            }
            
        } catch (FileNotFoundException ex) {
        	System.out.println("Nincs ilyen fájl!");
        }
    }

}

class Jegy {

    String nev;
    int jegy;
    String targy;
    Date datum;
    static SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");

    @Override
    public String toString() {
        return "Jegy{" + "nev=" + nev + ", jegy=" + jegy + ", datum=" + df.format(datum) + '}';
    }

}
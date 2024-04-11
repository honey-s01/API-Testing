package com.sparta;

import java.io.FilterOutputStream;
import java.time.LocalDate;

public class SpartanApp {
    public static void main(String[] args) {
        Spartan s1 = new Spartan(4, "Cathy", "Java", LocalDate.now());
        Spartan s2 = new Spartan(8, "James", "C#", LocalDate.now().minusDays(2));
        SpartanRepository repository = new SpartanRepository();
        repository.addSpartan(s1);
        repository.addSpartan(s2);
        System.out.println("Number of Spartans: " + repository.getNumSpartans());
        System.out.println(repository.findSpartan(4).toString());
        System.out.println();
        System.out.println(repository.getAllSpartans());
        System.out.println("New Spartans");
        System.out.println(repository.getSpartansCreatedLast24Hours());
    }
}

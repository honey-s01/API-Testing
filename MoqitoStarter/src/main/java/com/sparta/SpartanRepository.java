package com.sparta;

import java.time.LocalDate;
import java.util.ArrayList;

public class SpartanRepository {

    private final ArrayList<Spartan> spartans = new ArrayList<>();

    public void addSpartan(Spartan spartan) {
        spartans.add(spartan);
    }

    public int getNumSpartans() {
        return spartans.size();
    }

    // this is not an optimal algorithm!
    public Spartan findSpartan(int id) {
        Spartan returnedSpartan = null;
        for (Spartan spartan : spartans) {
            if (spartan.getId() == id) {
                returnedSpartan = spartan;
            }
        }
        return returnedSpartan;
    }

    public boolean changeName(int id, String newName) {
        Spartan spartan = findSpartan(id);
        if (spartan == null) return false;
        try {
            spartan.setName(newName);
            return true;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }

    public String getAllSpartans() {
        StringBuilder SpartansInArray = new StringBuilder();
        for (Spartan spartan : spartans) {
            SpartansInArray.append(spartan.getId() + " " + spartan.getName()).append("\n");
        }
        return SpartansInArray.toString();
    }

    public ArrayList<Spartan> getSpartansCreatedLast24Hours() {
        ArrayList<Spartan> newSpartans = new ArrayList<>();
        for (var spartan : spartans) {
            if (spartan.getStartDate().plusDays(1).isAfter(LocalDate.now())) {
                newSpartans.add(spartan);
            }
        }
        return newSpartans;
    }

    public boolean removeSpartan(int id) {
        return spartans.removeIf(spartan -> spartan.getId() == id);
    }
}
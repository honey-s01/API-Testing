package com.sparta;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

import org.mockito.InOrder;
import org.mockito.Mockito;

import java.time.LocalDate;

public class SpartanRespositoryTests {

    //setup
    SpartanRepository sut = null;
    Spartan mock1 = null;
    Spartan mock2 = null;

    @BeforeEach
    public void setup() {
        sut = new SpartanRepository(); //SUT = SPARTANREPOSITORY
        mock1 = mock(Spartan.class);
        mock2 = mock(Spartan.class);
        sut.addSpartan(mock1);
        sut.addSpartan(mock2);
    }

    @Test
    @DisplayName("Testing AddSpartan")
    public void testAddSpartan(){
        Assertions.assertEquals(2, sut.getNumSpartans());  //Fake
    }

    @Test
    @DisplayName("Testing findSpartan") //stub
    public void findSpartanTest() {
        // Arrange
        Mockito.when(mock1.getId()).thenReturn(345);
        Mockito.when(mock1.toString()).thenReturn("Found 345");
        Mockito.when(mock2.toString()).thenReturn("wrong spartan");
        // Act
        var result = sut.findSpartan(345);
        // Assert
        Assertions.assertEquals("Found 345", result.toString());
    }

    @Test
    @DisplayName("Testing findSpartan WHERE THE SPARTAN DOESNT EXIST")
    public void findSpartanTest_notpresent() {

        // Act
        var result = sut.findSpartan(345);
        // Assert
        Assertions.assertNull(result);
    }


    @Test
    @DisplayName("Testing getSpartansCreatedLast24Hours")
    public void testingLast24Hours() {
        Mockito.when(mock1.getStartDate())
                .thenReturn(LocalDate.now().minusDays(3)
                );

        Mockito.when(mock2.getStartDate()).thenReturn(LocalDate.now());

        var newSpartans = sut.getSpartansCreatedLast24Hours();
        Assertions.assertEquals(1,newSpartans.size());
    }

    @Test
    @DisplayName("Testing getSpartansCreatedLast24Hours with different return values ")
    public void testingLast24Hours_differentReturnValue() {
        Mockito.when(mock1.getStartDate())
                .thenReturn(LocalDate.now().minusDays(3))
                        .thenReturn(LocalDate.now());

        Mockito.when(mock2.getStartDate()).thenReturn(LocalDate.now());

        var newSpartans = sut.getSpartansCreatedLast24Hours();
        Assertions.assertEquals(1,newSpartans.size());

        var newSpartans2 = sut.getSpartansCreatedLast24Hours();
        Assertions.assertEquals(2,newSpartans2.size());


    }

    @Test
    public void testChangingName() {
        // arrange
        Mockito.when(mock1.getId()).thenReturn(3);
        doThrow(IllegalArgumentException.class).when(mock1).setName(any(String.class));
        // act and assert
        Assertions.assertFalse(sut.changeName(3, "Doris"));
    }

    @Test
    @DisplayName("Check getId is called once per Spartan") //Spies
    public void checkGetIdCalledOnce() {
        sut.findSpartan(20);
        Mockito.verify(mock1, Mockito.times(1)).getId();
        Mockito.verify(mock2, Mockito.times(1)).getId();
    }

    @Test
    @DisplayName("Check methods are called in order") // Checking if getId was called first and then getName //spies
    public void checkOrder() {
        sut.getAllSpartans();

        InOrder inOrder = inOrder(mock2);
        inOrder.verify(mock2).getId();
        inOrder.verify(mock2).getName();
    }

    @Test
    @DisplayName("test correct parameter")
    public void testMethodIsCalledWithCorrectParam() {
        // arrange
        Mockito.when(mock1.getId()).thenReturn(3);

        // act
        sut.changeName(3, "Cathy");

        // and assert
        Mockito.verify(mock1).setName("Cathy");
    }











}

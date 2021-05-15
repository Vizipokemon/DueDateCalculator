package com.github.vizipokemon;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

public class DueDateCalculatorTest {

    DueDateCalculator dueDateCalculator;

    @BeforeEach
    void init(){
        dueDateCalculator = new DueDateCalculator();
    }

    @Test
    void calculateDueDate_DueDate_Is_In_Same_Week(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 10, 14, 0);
        int turnaroundTime = 16;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 5, 12, 14, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_DueDate_Is_In_Next_Week(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 13, 14, 0);
        int turnaroundTime = 26;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 5, 18, 16, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_DueDate_Is_In_Next_Month(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 28, 14, 0);
        int turnaroundTime = 30;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 6, 3, 12, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_DueDate_Is_In_Next_Year(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2020, 12, 31, 14, 0);
        int turnaroundTime = 16;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 1, 4, 14, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_1_Weekend_Passed(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 13, 14, 0);
        int turnaroundTime = 32;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 5, 19, 14, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_2_Weekends_Passed(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 13, 14, 0);
        int turnaroundTime = 88;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 5, 28, 14, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_Due_Date_Comes_Out_To_Be_On_Saturday_Should_Skip_Weekend(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 4, 16, 9, 0);
        int turnaroundTime = 8;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 4, 19, 9, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_Due_Date_Comes_Out_To_Be_On_Sunday_Should_Skip_Weekend(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 4, 16, 9, 0);
        int turnaroundTime = 16;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 4, 20, 9, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_Due_Date_Comes_Out_To_Be_On_Saturday_And_Multiple_Weekends_Passed_Should_Skip_All_Weekends(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 4, 16, 9, 0);
        int turnaroundTime = 64;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 4, 28, 9, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_Due_Date_Comes_Out_To_Be_On_Sunday_And_Multiple_Weekends_Passed_Should_Skip_All_Weekends(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 4, 16, 9, 0);
        int turnaroundTime = 72;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 4, 29, 9, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_turnaroundTime_Is_5(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 10, 14, 0);
        int turnaroundTime = 5;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 5, 11, 11, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_turnaroundTime_Is_7(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 10, 14, 0);
        int turnaroundTime = 7;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 5, 11, 13, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_turnaroundTime_Is_8(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 10, 14, 0);
        int turnaroundTime = 8;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 5, 11, 14, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_turnaroundTime_Is_9(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 10, 14, 0);
        int turnaroundTime = 9;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 5, 11, 15, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_turnaroundTime_Is_16(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 10, 14, 0);
        int turnaroundTime = 16;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 5, 12, 14, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_turnaroundTime_Is_20(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 10, 14, 0);
        int turnaroundTime = 20;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = LocalDateTime.of(2021, 5, 13, 10, 0);
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_turnaroundTime_Is_0_Should_Return_Submit_Date(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 10, 14, 0);
        int turnaroundTime = 0;

        //act
        LocalDateTime dueDate = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);

        //assert
        LocalDateTime expectedDueDate = submitDate;
        Assertions.assertEquals(expectedDueDate, dueDate);
    }

    @Test
    void calculateDueDate_turnaroundTime_Is_Less_Than_Zero_Should_Throw_Illegal_Argument_Exception(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 10, 14, 0);
        int turnaroundTime = -1;

        //act + assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> dueDateCalculator.calculateDueDate(submitDate, turnaroundTime));
    }

    @Test
    void calculateDueDate_SubmitDate_Is_Saturday_Should_Throw_Illegal_Argument_Exception(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 15, 14, 0);
        int turnaroundTime = 12;

        //act + assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> dueDateCalculator.calculateDueDate(submitDate, turnaroundTime));
    }

    @Test
    void calculateDueDate_SubmitDate_Is_Sunday_Should_Throw_Illegal_Argument_Exception(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 16, 14, 0);
        int turnaroundTime = 12;

        //act + assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> dueDateCalculator.calculateDueDate(submitDate, turnaroundTime));
    }

    @Test
    void calculateDueDate_SubmitTime_Is_After_Working_Hours_Should_Throw_Illegal_Argument_Exception(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 16, 8, 59);
        int turnaroundTime = 12;

        //act + assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> dueDateCalculator.calculateDueDate(submitDate, turnaroundTime));
    }

    @Test
    void calculateDueDate_SubmitTime_Is_Before_Working_Hours_Should_Throw_Illegal_Argument_Exception(){
        //arrange
        LocalDateTime submitDate = LocalDateTime.of(2021, 5, 16, 17, 1);
        int turnaroundTime = 12;

        //act + assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> dueDateCalculator.calculateDueDate(submitDate, turnaroundTime));
    }
}

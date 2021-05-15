package com.github.vizipokemon;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;

public class DueDateCalculator {
    private final int startOfWorkingHours = 9;
    private final int endOfWorkingHours = 17;
    private final DayOfWeek[] workDays = {
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY};

    /**
     * Calculates the due date according to working days and working hours,
     * given a submit date and a turnaround time.
     *
     * @param submitDateTime The local date and time of the submission.
     *                       The submit date can only be work days (Monday-Friday) and
     *                       the time can only be working hours (9AM - 5PM).
     * @param turnaroundTime The amount of working hours between the submit date and due date.
     * @throws IllegalArgumentException if turnaroundTime is less, than 0.
     *                                  if submitDateTime is not within working hours (9AM - 5PM).
     *                                  if submitDateTime is not a work day (Monday-Friday).
     * @return The due date.
     */
    public LocalDateTime calculateDueDate(LocalDateTime submitDateTime, int turnaroundTime){
        checkParametersValidity(submitDateTime, turnaroundTime);

        LocalDateTime dueDate = submitDateTime;
        final int workingHoursPerDay = (endOfWorkingHours - startOfWorkingHours);
        int daysToAdd = turnaroundTime / workingHoursPerDay;
        final int hoursThatWillBeAddedAsDays = daysToAdd * workingHoursPerDay;
        int hoursToAdd = turnaroundTime - hoursThatWillBeAddedAsDays;

        dueDate = addHoursToDueDate(dueDate, hoursToAdd);
        dueDate = dueDate.plusDays(daysToAdd);
        dueDate = addNonWorkDaysToDueDate(submitDateTime, dueDate);

        return dueDate;
    }

    private void checkParametersValidity(LocalDateTime submitDateTime, int turnaroundTime){
        if(turnaroundTime < 0){
            throw new IllegalArgumentException("calculateDueDate: turnaroundTime cannot be negative! It was: (" + turnaroundTime + ")");
        }

        if(submitDateTime.getHour() > endOfWorkingHours || submitDateTime.getHour() < startOfWorkingHours){
            throw new IllegalArgumentException("calculateDueDate: submitDateTime hour is not within working hours! It was: (" + submitDateTime.getHour() + ")");
        }

        boolean IsSubmitDateAWorkingDay = Arrays.stream(workDays).toList().contains(submitDateTime.getDayOfWeek());
        if(!IsSubmitDateAWorkingDay){
            throw new IllegalArgumentException("calculateDueDate: submitDateTime day is not a working day! It was: (" + submitDateTime.getDayOfWeek() + ")");
        }
    }

    private LocalDateTime addHoursToDueDate(LocalDateTime dueDate, int hoursToAdd){
        int newHour = dueDate.getHour() + hoursToAdd;
        if(newHour > endOfWorkingHours){
            int surplusHours = newHour - endOfWorkingHours;
            return addHoursToNextDay(dueDate, surplusHours);
        }else{
            return dueDate.plusHours(hoursToAdd);
        }
    }

    private LocalDateTime addHoursToNextDay(LocalDateTime date, int hours){
        date = date.plusDays(1);
        return date.withHour(startOfWorkingHours + hours);
    }

    private LocalDateTime addNonWorkDaysToDueDate(LocalDateTime submitDateTime, LocalDateTime dueDate){
        int numberOfWeekends = getNumberOfWeekendsBetweenSubmitAndDueDate(submitDateTime, dueDate);
        final int numberOfDaysInAWeekend = 2;
        return dueDate.plusDays(numberOfWeekends * numberOfDaysInAWeekend);
    }

    private int getNumberOfWeekendsBetweenSubmitAndDueDate(LocalDateTime submitDate, LocalDateTime dueDate){
        LocalDateTime date = submitDate;
        int numberOfWeekends = 0;
        while(date.isBefore(dueDate)) {
            date = date.plusDays(1);
            if(date.getDayOfWeek() == DayOfWeek.SATURDAY){
                numberOfWeekends++;
            }
        }
        return numberOfWeekends;
    }
}

package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        List<TrainingSession> mondaySession = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);

        Assertions.assertEquals(1, mondaySession.size());
        Assertions.assertSame(singleTrainingSession, mondaySession.get(0));
        Assertions.assertTrue(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).isEmpty());
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        List<TrainingSession> mondaySession = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);

        Assertions.assertEquals(1, mondaySession.size());
        Assertions.assertSame(mondayChildTrainingSession, mondaySession.get(0));

        List<TrainingSession> thursdaySession = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);

        Assertions.assertEquals(2, thursdaySession.size());
        Assertions.assertSame(thursdayChildTrainingSession, thursdaySession.get(0));
        Assertions.assertSame(thursdayAdultTrainingSession, thursdaySession.get(1));

        Assertions.assertTrue(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).isEmpty());
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        List<TrainingSession> mondayAt13 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        Assertions.assertEquals(1, mondayAt13.size());
        Assertions.assertSame(singleTrainingSession, mondayAt13.get(0));

        List<TrainingSession> mondayAt14 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0));

        Assertions.assertTrue(mondayAt14.isEmpty());
    }

    @Test
    void testGetCountByCoachesEmptyTimetable() {
        Timetable timetable = new Timetable();

        List<CounterOfTrainings> trainingSessions = timetable.getCountByCoaches();

        Assertions.assertTrue(trainingSessions.isEmpty());
    }

    @Test
    void testOneTrainerLeadsSeveralTrainingSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        List<CounterOfTrainings> trainingSessions = timetable.getCountByCoaches();

        Assertions.assertEquals(1, trainingSessions.size());
        Assertions.assertEquals(coach, trainingSessions.get(0).getCoach());
        Assertions.assertEquals(3, trainingSessions.get(0).getCount());
    }

    @Test
    public void multipleTrainersAndSortInDescendingOrder() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach2 = new Coach("Петров", "Григорий", "Петрович");
        Coach coach3 = new Coach("Сидоров", "Валерий", "Сергеевич");
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession coach1MondayChildTrainingSession = new TrainingSession(groupChild, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession coach1ThursdayChildTrainingSession = new TrainingSession(groupChild, coach1,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession coach1SaturdayChildTrainingSession = new TrainingSession(groupChild, coach1,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));
        TrainingSession coach2MondayChildTrainingSession = new TrainingSession(groupChild, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(18, 0));
        TrainingSession coach2ThursdayChildTrainingSession = new TrainingSession(groupChild, coach2,
                DayOfWeek.THURSDAY, new TimeOfDay(14, 0));
        TrainingSession coach3SaturdayChildTrainingSession = new TrainingSession(groupChild, coach3,
                DayOfWeek.SATURDAY, new TimeOfDay(17, 0));

        timetable.addNewTrainingSession(coach1MondayChildTrainingSession);
        timetable.addNewTrainingSession(coach1ThursdayChildTrainingSession);
        timetable.addNewTrainingSession(coach1SaturdayChildTrainingSession);
        timetable.addNewTrainingSession(coach2MondayChildTrainingSession);
        timetable.addNewTrainingSession(coach2ThursdayChildTrainingSession);
        timetable.addNewTrainingSession(coach3SaturdayChildTrainingSession);

        List<CounterOfTrainings> trainingSessions = timetable.getCountByCoaches();

        Assertions.assertEquals(3, trainingSessions.size());

        Assertions.assertEquals(coach1, trainingSessions.get(0).getCoach());
        Assertions.assertEquals(3, trainingSessions.get(0).getCount());

        Assertions.assertEquals(coach2, trainingSessions.get(1).getCoach());
        Assertions.assertEquals(2, trainingSessions.get(1).getCount());

        Assertions.assertEquals(coach3, trainingSessions.get(2).getCoach());
        Assertions.assertEquals(3, trainingSessions.get(2).getCount());
    }
}

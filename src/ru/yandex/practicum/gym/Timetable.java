package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek dayOfWeek = trainingSession.getDayOfWeek();
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();
        if (!timetable.containsKey(dayOfWeek)) {
            TreeMap<TimeOfDay, List<TrainingSession>> day = new TreeMap<>();
            timetable.put(dayOfWeek, day);
        }
        TreeMap<TimeOfDay, List<TrainingSession>> day = timetable.get(dayOfWeek);
        if (!day.containsKey(timeOfDay)) {
            List<TrainingSession> sessionList = new ArrayList<>();
            day.put(timeOfDay, sessionList);
        }
        List<TrainingSession> sessionList = day.get(timeOfDay);
        sessionList.add(trainingSession);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        if (!timetable.containsKey(dayOfWeek)) {
            return new ArrayList<>();
        }
        TreeMap<TimeOfDay, List<TrainingSession>> day = timetable.get(dayOfWeek);
        List<TrainingSession> sessions = new ArrayList<>();
        for (TimeOfDay time : day.navigableKeySet()) {
            sessions.addAll(day.get(time));
        }
        return sessions;
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        if (!timetable.containsKey(dayOfWeek)) {
            return new ArrayList<>();
        }
        TreeMap<TimeOfDay, List<TrainingSession>> day = timetable.get(dayOfWeek);
        if (day.containsKey(timeOfDay)) {
            return day.get(timeOfDay);
        } else {
            return new ArrayList<>();
        }
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        HashMap<Coach, Integer> countCoaches = new HashMap<>();
        for (TreeMap<TimeOfDay, List<TrainingSession>> day : timetable.values()) {
            for (List<TrainingSession> sessions : day.values()) {
                for (TrainingSession trainingSession : sessions) {
                    Coach coach = trainingSession.getCoach();
                    if (!countCoaches.containsKey(coach)) {
                        countCoaches.put(coach, 1);
                    } else {
                        int countCurrent = countCoaches.get(coach);
                        int newCount = countCurrent + 1;
                        countCoaches.put(coach, newCount);
                    }
                }
            }
        }
        List<CounterOfTrainings> rosterCoach = new ArrayList<>();
        for (Map.Entry<Coach, Integer> entry : countCoaches.entrySet()) {
            Coach oneCoach = entry.getKey();
            Integer count = entry.getValue();
            CounterOfTrainings counter = new CounterOfTrainings(oneCoach, count);
            rosterCoach.add(counter);
        }
        CounterOfTrainingsComparator comparator = new CounterOfTrainingsComparator();
        rosterCoach.sort(comparator);
        return rosterCoach;
    }
}

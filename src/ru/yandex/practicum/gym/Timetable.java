package ru.yandex.practicum.gym;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

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
}

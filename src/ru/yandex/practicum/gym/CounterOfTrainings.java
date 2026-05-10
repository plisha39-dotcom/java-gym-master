package ru.yandex.practicum.gym;

public class CounterOfTrainings {
    private final Coach coach;
    private final int count;

    public CounterOfTrainings(Coach coach, int count) {
        this.count = count;
        this.coach = coach;
    }

    public int getCount() {
        return count;
    }

    public Coach getCoach() {
        return coach;
    }
}

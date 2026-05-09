package ru.yandex.practicum.gym;

public class CounterOfTrainings {
    Coach coach;
    int count;
    public CounterOfTrainings(int count, Coach coach) {
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

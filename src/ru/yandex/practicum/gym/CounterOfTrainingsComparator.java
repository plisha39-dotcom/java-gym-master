package ru.yandex.practicum.gym;

import java.util.Comparator;

public class CounterOfTrainingsComparator implements Comparator<CounterOfTrainings> {
    @Override
    public int compare(CounterOfTrainings counter1, CounterOfTrainings counter2) {
        return Integer.compare(counter2.getCount(), counter1.getCount());
    }
}

package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Класс нарушает принцип OCP, т.к. возвращает и принимает в параметрах конкретную реализацию коллекции,
 * а не абстракцию от этой коллекции, что может в дальнейшем привести к изменению данного класса.
 */
public class Find {
    public ArrayList<String> findAll(ArrayList<String> list, Predicate<String> predicate) {
        return null;
    }
}
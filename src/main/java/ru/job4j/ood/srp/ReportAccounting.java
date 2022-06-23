package ru.job4j.ood.srp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportAccounting implements Report {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private final Store store;

    public ReportAccounting(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary($);")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            String salary = new DecimalFormat("#.##").format(employee.getSalary() / 60);
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(salary).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
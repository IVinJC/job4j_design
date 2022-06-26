package ru.job4j.ood.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Calendar;

public class ReportTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        DateFormatter dateFormatter = new SimpleDateFormatter();
        Report engine = new ReportEngine(dateFormatter, store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(dateFormatter.convertToString(worker.getHired())).append(";")
                .append(dateFormatter.convertToString(worker.getHired())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void accountingTest() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        DateFormatter dateFormatter = new SimpleDateFormatter();
        Report engine = new ReportAccounting(dateFormatter, store);
        String salary = new DecimalFormat("#.##").format(worker.getSalary() / 60);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary($);")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(dateFormatter.convertToString(worker.getHired())).append(";")
                .append(dateFormatter.convertToString(worker.getHired())).append(";")
                .append(salary).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void hrTest() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Petr", now, now, 120);
        store.add(worker);
        store.add(worker1);
        Report engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void itTest() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        DateFormatter dateFormatter = new SimpleDateFormatter();
        Report engine = new ReportIT(dateFormatter, store);
        String ln = System.lineSeparator();
        StringBuilder expect = new StringBuilder()
                .append("<html>").append(ln)
                .append("<head>").append(ln)
                .append("<title>Employees</title>").append(ln)
                .append("</head>").append(ln)
                .append("</body>").append(ln)
                .append("Name; Hired; Fired; Salary;").append(ln)
                .append(worker.getName()).append(";")
                .append(dateFormatter.convertToString(worker.getHired())).append(";")
                .append(dateFormatter.convertToString(worker.getHired())).append(";")
                .append(worker.getSalary()).append(";").append(ln)
                .append("</body>").append(ln)
                .append("</html>").append(ln);
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}

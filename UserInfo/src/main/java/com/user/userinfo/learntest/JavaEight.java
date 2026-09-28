package com.user.userinfo.learntest;

import org.hibernate.id.IntegralDataTypeHolder;

import java.util.*;
import java.util.stream.Collectors;

public class JavaEight {

    public static void main(String[] args) {

        String str = "SumitSumitKumar";
        String str1 = "abcba";

        Character ans1 = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().findFirst().get().getKey();

        System.out.println("First repeating character is: " + ans1);

        Character ans2 = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getKey() != null && e.getValue() > 1)
                .map(Map.Entry::getKey)
                .skip(1).findFirst().orElse(null);

        System.out.println("Second repeating character is: " + ans2);

        Character ans3 = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().
                filter(e -> e.getKey() != null && e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);

        System.out.println("First non-repeating character is: " + ans3);

        Character ans4 = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(e -> e, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .skip(1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Not found"));

        System.out.println("Second non-repeating character is: " + ans4);

        LinkedHashMap<Character, Long> map = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(e -> e, LinkedHashMap::new, Collectors.counting()));

        for (Character ch : map.keySet()) {
            System.out.println(ch + " : " + map.get(ch));
        }


        System.out.println("===========================================================================");
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", 50000, "Engineering"),
                new Employee(2, "Alice", 60000, "Marketing"),
                new Employee(3, "Bob", 55000, "Sales"),
                new Employee(4, "John", 50000, "Engineering"),
                new Employee(5, "Alice", 60000, "Marketing"),
                new Employee(3, "Bob", 55000, "Sales")
        );

        // Find the employee with the highest salary
        Employee highestSalaryEmployee = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .orElse(null);

        System.out.println("Employee with the highest salary: " + highestSalaryEmployee);

        Employee e1 = employees.stream()
                .sorted((e2, e3) -> Double.compare(e2.getSalary(), e3.getSalary()))
                .skip(2).findFirst().orElse(null);
        System.out.println("Employee with the third highest salary: " + e1);


    List<Integer> li = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
    li.stream().filter(e ->e %2==0).forEach(System.out::print);

    int sumT= li.stream().mapToInt(Integer::intValue).sum();
    int sumT1= li.stream().reduce(0, Integer::sum);

}

}

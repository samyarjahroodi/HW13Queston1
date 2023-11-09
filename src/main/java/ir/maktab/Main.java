package ir.maktab;


import ir.maktab.mockdata.MockData;
import ir.maktab.model.Person;
import ir.maktab.model.PersonSummary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        System.out.println("--------DEVELOPED BY SAMYAR JAHROODI--------");
//        System.out.println();
        //1
//        System.out.println("-----Sorted people below 50-----");
//        List<Person> people = sortedBelow50();
//        System.out.println(people);
//        //2
//        System.out.println("-----Sorted people by their username-----");
//        List<Person> sortedByUser = sortedByUsername();
//        System.out.println(sortedByUser);
//        //3
//        System.out.println("-----Sorted people by their age and then username-----");
//        List<Person> sortedlistBaseOnAgeAndUsername = sortedByAgeAndThenUsername();
//        System.out.println(sortedlistBaseOnAgeAndUsername);
//        //4
//        Set<String> strings = ipv4();
//        System.out.println("-----IPV4-----");
//        System.out.println(strings);
//        //5
//        Map<String, Person> sortedAndFiltered1 = complicatedSorting();
//        System.out.println(sortedAndFiltered1);
        //6
        List<Person> people = MockData.getPeople();
        assert people != null;
        System.out.println(getAvg(people));

    }

    //1
    public static List<Person> sortedBelow50() {
        return Objects.requireNonNull(MockData.getPeople()).stream().
                filter(p -> p.getAge() < 20).toList();
    }

    //2
    public static List<Person> sortedByUsername() {
        List<Person> people = MockData.getPeople();
        assert people != null;
        return people.stream().sorted(Comparator.comparing(Person::getUsername)).toList();
    }

    //3
    public static List<Person> sortedByAgeAndThenUsername() {
        List<Person> people = MockData.getPeople();
        assert people != null;
        return people.stream()
                .sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getUsername)).toList();
    }

    //4
    public static Set<String> ipv4() {
        List<Person> people = MockData.getPeople();
        assert people != null;
        return people.stream().map(Person::getIpv4).collect(Collectors.toSet());
    }


    //5
    public static Map<String, Person> complicatedSorting() {
        List<Person> people = MockData.getPeople();
        assert people != null;
        return people.stream()
                .sorted(Comparator.comparing(Person::getUsername))
                .filter(person -> person.getGender().equals("Female"))
                .filter(p -> p.getAge() > 40)
                .dropWhile(p -> p.getFirstName().startsWith("A"))
                .skip(5)
                .limit(100)
                .collect(Collectors.toMap(
                        person -> person.getFirstName() + " " + person.getLastName(),
                        Function.identity(),
                        (a, b) -> a
                ));
        // corrected version!!!
        //        return list.stream()
//                .sorted(Comparator.comparing(Person::getLastName))
//                .filter(person -> person.getGender().equals("Female") && person.getAge() > 40 && !person.getFirstName().startsWith("A"))
//                .skip(5)
//                .limit(100)
//                .collect(Collectors.toMap(
//                        person -> person.getFirstName() + " " + person.getLastName(),
//                        person -> person ,
//                        (existingPerson,newPerson)->existingPerson));
    }


    //6
//    public static void correctingAge(List<Person> people) {
//        SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd/MM/yyyy");
//        return people.stream().filter(p -> p.getGender().equals("Male"))
//                .map(p -> {
//                    try {
//                        return new PersonSummary(p.getId(),
//                                p.getFirstName(), p.getLastName(), p.getAge(),
//                                (2023 - (simpleFormatter.parse(p.getBirthDate()).getYear() + 1900)
//                                , (simpleFormatter.parse(p.getBirthDate()))));
//                    } catch (ParseException exception) {
//                        throw new RuntimeException(exception);
//                    }
//                })
//                .map(PersonSummary::getAge).mapToDouble(Integer::doubleValue).average();
//    }
    static Double getAvg(List<Person> list) {
        List<PersonSummary> summaries = list.stream()
                .filter(person -> person.getGender().equals("Male"))
                .map(PersonSummary::new)
                .toList();

        OptionalDouble averageAge = summaries.stream()
                .map(PersonSummary::getAge)
                .mapToDouble(Integer::doubleValue).average();

        return averageAge.orElse(0);
    }
}

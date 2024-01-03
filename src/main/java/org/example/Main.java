package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // Find number on array
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(10);
        integerList.add(5);
        integerList.add(8);

        Integer number = integerList.stream()
                .filter(value -> value == 10)
                .findAny()
                .orElse(null);
        System.out.println("Number: " + number);

        // Find number using Optional
        Optional<Integer> optionalInteger = integerList.stream()
                .filter(value -> value == 10)
                .findFirst();
        System.out.println("Optional Number: " + (optionalInteger.isPresent() ? optionalInteger.get() : ""));

        // Find number using collect
        List<Integer> collectNumber = integerList.stream()
                .filter(value -> value == 5)
                .collect(Collectors.toList());
        System.out.println("Collect Number: " + collectNumber.get(0));

        // Create list from stream
        List<String> items = new ArrayList<String>();
        items.add("one");
        items.add("two");
        items.add("three");
        Stream<String> stream = items.stream();
        System.out.println("Stream List: " + stream.toList());

        // Initialize stream
        Stream<Integer> initializeStream = Stream.of(1, 2, 3, 4, 5, 10, 20);
        System.out.println("Count Initialize Stream: " + initializeStream.count());


        // ########################################## Person Test #################################################


        // Using filter
        List<Person> personFilterList = new Person().populatePerson();
        Stream<Person> personNationality = personFilterList.stream()
                .filter(person -> person.getNationality().equals("Brazil"));
        System.out.println("Count Person Nationality - Filter: " + personNationality.count());


        // Using Map to get a int
        List<Person> personMapList = new Person().populatePerson();
        IntStream ageList = personMapList.stream()
                .filter(person -> person.getNationality().equals("Brazil"))
                .mapToInt(Person::getAge);
        System.out.println("Count Person Age - Map Int: " + ageList.count());


        // Using sorted
        List<Person> personSortedList = new Person().populatePerson();
        Stream<Person> sortedPerson = personSortedList.stream()
                .filter(person -> person.getNationality().equals("Brazil"))
                .sorted(Comparator.comparing(person -> person.getName()));
        System.out.println("Count Person - Sorted: " + sortedPerson.count());


        // Using distinct
        // It uses the equals method
        // Review
        List<Person> personDistinctList = new Person().populatePerson();
        Stream<Person> distinctPerson = personDistinctList.stream()
                .distinct();
        System.out.println("Count Person - Distinct: " + distinctPerson.count());


        // Using limit
        // Get only the 2 first elements
        List<Person> personLimitList = new Person().populatePerson();
        Stream<Person> limitPerson = personLimitList.stream()
                .limit(2);
        System.out.println("Count Person - Limit: " + limitPerson.count());


        // Using forEach
        // When we are using a method reference as System.out::println it will use the toString method
        List<Person> personForEacheList = new Person().populatePerson();
        personForEacheList.stream()
                 .forEach(pessoa -> System.out.println(pessoa.getName()));


        // Using average
        List<Person> personAverageList = new Person().populatePerson();
        double averagePerson = personAverageList.stream()
                .filter(person -> person.getNationality().equals("Brazil"))
                .mapToDouble(Person::getAge)
                .average()
                .getAsDouble();
        System.out.println("Person Age - Average: " + averagePerson);


        // Using collect
        // Return Person object, with duplicate
        List<Person> personCollectDuplicateList = new Person().populatePerson();
        List<Person> collectPersonDuplicate = personCollectDuplicateList.stream()
                .filter(person -> person.getNationality().equals("Brazil"))
                .collect(Collectors.toList());
        System.out.println("Person duplicate object - Collect: " + collectPersonDuplicate);


        // Using collect
        // Return Person object using distinct
        List<Person> personCollectList = new Person().populatePerson();
        List<Person> collectPerson = personCollectList.stream()
                .filter(person -> person.getNationality().equals("Brazil"))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Person distinct object - Collect: " + collectPerson);


        // Using collect
        // Return Person object without duplicates
        List<Person> personCollectSetList = new Person().populatePerson();
        Set<Person> collectPersonSet = personCollectSetList.stream()
                .filter(person -> person.getNationality().equals("Brazil"))
                .collect(Collectors.toSet());
        System.out.println("Person Set object - Collect: " + collectPersonSet);


        // Using collect
        // Return list of name
        List<Person> personCollectNameList = new Person().populatePerson();
        List<String> collectName = personCollectNameList.stream()
                .filter(person -> person.getNationality().equals("Brazil"))
                .map(person -> person.getName())
                .distinct()
                .toList();
        System.out.println("Person Name - Collect: " + collectName);


        // Using collect
        // Return list of id
        List<Person> personCollectIdList = new Person().populatePerson();
        List<String> collectId = personCollectIdList.stream()
                .filter(person -> person.getNationality().equals("Brasil"))
                .map(person -> person.getId())
                .collect(Collectors.toList());
        System.out.println("Person ID - Collect: " + collectId);


        // Using collect
        // Return list of id
        // Using map to filter
        List<Person> personCollectIdMapFilterList = new Person().populatePerson();
        List<String> collectIdMapFilter = personCollectIdMapFilterList.stream()
                .map((person) -> {
                    if (person.getNationality().equals("Brazil")) {
                        return person.getId();
                    }
                    return null;
                })
                .collect(Collectors.toList());
        System.out.println("Person ID - Collect using Map as Filter: " + collectIdMapFilter);


        // Using collect
        // Return list of id
        // Using lambda to filter
        List<Person> personCollectIdLambda = new Person().populatePerson();
        List<String> collectIdLambda = personCollectIdLambda.stream()
                .filter((value) -> {
                    if (value.getNationality().equals("Brazil") && value.getName().startsWith("N")) {
                        return true;
                    }
                    return false;
                })
                .map(Person::getId)
                .toList();
        System.out.println("Person ID - Collect using Lambda: " + collectIdLambda);


        // Using collect
        // Return list of age
        // Boxed() convert a int to Integer
        List<Person> personCollectAge = new Person().populatePerson();
        List<Integer> collectAge = personCollectAge.stream()
                .filter(person -> person.getNationality().equals("Brazil"))
                .mapToInt(Person::getAge)
                .boxed()
                .toList();
        System.out.println("List Person Age - Collect Age from int To Integer: " + collectAge);


        // Using allMatch
        List<Person> personAllMatch = new Person().populatePerson();
        boolean allMatch = personAllMatch.stream()
                .allMatch(person -> person.getNationality().equals("Mexico"));
        System.out.println("Person boolean - AllMatch: " + allMatch);


        // Using anyMatch
        // Check if at least one data match the condition
        List<Person> personAnyMatch = new Person().populatePerson();
        boolean anyMatch = personAnyMatch.stream()
                .anyMatch(person -> person.getNationality().equals("Germany"));
        System.out.println("Person boolean - AnyMatch: " + anyMatch);


        // Using findFirst
        List<Person> personFindFirstList = new Person().populatePerson();
        Optional<Person> findFirst = personFindFirstList.stream()
                .filter(person -> person.getNationality().equals("Brazil"))
                .findFirst();
        System.out.println("Person - FindFirst: " + (findFirst.isPresent() ? findFirst.get().toString() : ""));


        // Using findAny
        List<Person> personFindAnyList = new Person().populatePerson();
        Optional<Person> findAny = personFindAnyList.stream()
                .filter(person -> person.getNationality().equals("Brazil"))
                .findAny();
        System.out.println("Person - FindAny: " + (findAny.isPresent() ? findAny.get().toString() : ""));

    }
}

class Person {
    String id;
    String name;
    String nationality;
    int age;

    public Person(){}

    public Person(String id, String name, String nationality, int age){
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Person> populatePerson(){
        Person person1 = new Person("p1" , "Matheus Henrique", "Brazil", 18);
        Person person2 = new Person("p2" , "Hernandez Roja", "Mexico", 21);
        Person person3 = new Person("p3" , "Mario Fernandes","Canada", 22);
        Person person4 = new Person("p4" , "Neymar Junior", "Brazil", 22);
        Person person5 = new Person("p5" , "Pele", "Brazil", 50);
        List<Person> list = new ArrayList<Person>();
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);
        list.add(person4);
        list.add(person1);
        return list;
    }

    @Override  public String toString() {
        return this.name;
    }
}
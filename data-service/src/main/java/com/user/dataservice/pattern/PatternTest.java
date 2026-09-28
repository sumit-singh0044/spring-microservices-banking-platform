package com.user.dataservice.pattern;

public class PatternTest {

    public static void main(String[] args) {

        BuilderPattern build = new BuilderPattern.Builder()
                .setName("John")
                .setAge(30)
                .setCity("New York")
                .setCountry("USA")
                .setSurname("Doe")
                .build();


        BuilderPattern build2 = new BuilderPattern.Builder()
                .setName("John")
                .setAge(30)
//                .setCity("New York")
//                .setCountry("USA")
//                .setSurname("Doe")
                .build();

        System.out.println(build);
        System.out.println("================================");
        System.out.println(build2);
    }

}

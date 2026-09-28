package com.user.dataservice.pattern;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuilderPattern {
    
    private final String name;
    private final int age;
    private final String city;
    private final String country;
    private final String surname;

    public BuilderPattern(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.city = builder.city;
        this.country = builder.country;
        this.surname = builder.surname;
    }

    public static class Builder{
        
        private String name;
        private int age;
        private String city;
        private String country;
        private String surname;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public BuilderPattern build() {
            return new BuilderPattern(this);
        }
    }

    @Override
    public String toString() {
        return "BuilderPattern{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}

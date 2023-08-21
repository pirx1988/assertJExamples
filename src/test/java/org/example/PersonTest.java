package org.example;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PersonTest {
    private static final Person RANDOM_PERSON = new Person("Random Person", "123 W. St.", "Chicago", "50-538", 12);
    private static final Person JOHN_SMITH = new Person("JohN Smith", "123 W. St.", "Los angeles", "50-000", 33);
    private static final Person JOHN_DOE = new Person("John Doe", "123 W. St.", "New York", "50-123", 35);
    private static final Person RANDOM_PERSON_2 = new Person("Random Person2", "123 W. St.", "Chicago", "50-538", 12);


    @Test
    void basicAssertionsTest() {
        assertThat(RANDOM_PERSON.getName()).isEqualTo("Random Person");
        assertThat(RANDOM_PERSON).isNotEqualTo(JOHN_SMITH);
    }

    @Test
    void chainingAssertionTest() {
        assertThat(JOHN_SMITH.getName())
                .startsWith("Jo")
                .endsWith("th")
                .isEqualToIgnoringCase("John Smith");
    }

    @Test
    void collectionAssertionTest() {
        List<Person> personList = List.of(JOHN_SMITH, JOHN_DOE);

        // Collection specific assertions (there are plenty more)
        // easy to follow, very redeable
        assertThat(personList)
                .hasSize(2)
                .contains(JOHN_SMITH, JOHN_DOE)
                .doesNotContain(RANDOM_PERSON, RANDOM_PERSON_2);
    }

    @Test
    void asAssertions_test() {
        // as() is used to describe the test and will be shown before the error message
        assertThat(JOHN_SMITH.getAge())
                .as("Check %s's age", JOHN_SMITH.getName())
                .isEqualTo(33);
    }

    @Test
    void exceptionAssertionTest() {
        assertThatThrownBy(() -> {
            Calculator.divideTwoNumbersReturnResult(5, 0);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("division by zero is forbidden");
    }

    @Test
    void extractingAssertionsTest() {
        List<Person> testPepoleList = List.of(RANDOM_PERSON, JOHN_SMITH, JOHN_DOE);
        assertThat(testPepoleList).extracting(Person::getName).doesNotContain("Random", "Person");

        assertThat(testPepoleList).extracting("name", "age", "city")
                .contains(tuple("Random Person", 12, "Chicago"),
                        tuple("John Doe", 33, "New York"));

    }

    @Test
    void filteringCollectionAssertionsTest() {
        List<Person> testPepoleList = List.of(RANDOM_PERSON, JOHN_SMITH, JOHN_DOE);

        assertThat(testPepoleList).filteredOn(person -> person.getName().contains("J"))
                .containsOnly(JOHN_SMITH, JOHN_DOE).hasSize(2);

        // combining filtering with extracting
        assertThat(testPepoleList).filteredOn(person -> person.getName().contains("J"))
                .containsOnly(JOHN_SMITH, JOHN_DOE).hasSize(2)
                .extracting(person -> person.getAge()).contains(33, 35);
    }


}
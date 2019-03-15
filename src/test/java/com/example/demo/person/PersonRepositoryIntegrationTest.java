package com.example.demo.person;

import com.example.demo.group.E2ETests;
import com.example.demo.group.IntegrationTests;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Category(IntegrationTests.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryIntegrationTest {

    @Autowired
    private PersonRepository subject;

    @After
    public void tearDown() throws Exception {
        subject.deleteAll();
    }

    @Test
    public void shouldSaveAndFetchPerson() throws Exception {
        // Insert data for testing
        Person somkiat = new Person("Somkiat", "Pui");
        subject.save(somkiat);

        // Act
        Optional<Person> maybePeter = subject.findByLastName("Pui");

        // Assert
        assertThat(maybePeter, is(Optional.of(somkiat)));
    }
}
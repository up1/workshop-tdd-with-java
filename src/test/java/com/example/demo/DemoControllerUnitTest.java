package com.example.demo;

import com.example.demo.group.UnitTests;
import com.example.demo.person.Person;
import com.example.demo.person.PersonRepository;
import com.example.demo.pm25.PM25Gateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@Category(UnitTests.class)
public class DemoControllerUnitTest {

    private DemoController subject;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PM25Gateway pm25Gateway;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subject = new DemoController(personRepository, pm25Gateway);
    }

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        assertThat(subject.hello(), is("Hello World!"));
    }

    @Test
    public void shouldReturnFullNameOfAPerson() throws Exception {
        // Create stub
        Person somkiat = new Person("Somkiat", "Pui");
        given(personRepository.findByLastName("Pui")).willReturn(Optional.of(somkiat));

        // Act
        HelloResponse greeting = subject.hello("Pui");

        // Assert
        assertThat(greeting.getMessage(), is("Hello Somkiat Pui!"));
    }

    @Test
    public void shouldTellIfPersonIsUnknown() throws Exception {
        // Create stub
        given(personRepository.findByLastName(anyString())).willReturn(Optional.empty());

        // Act
        HelloResponse greeting = subject.hello("Pui");

        // Assert
        assertThat(greeting.getMessage(), is("Who is this 'Pui' you're talking about?"));
    }

}
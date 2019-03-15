package com.example.demo;

import com.example.demo.group.UnitTests;
import com.example.demo.person.Person;
import com.example.demo.person.PersonRepository;
import com.example.demo.pm25.PM25Gateway;
import com.example.demo.pm25.PM25Response;
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
public class DemoControllerWithPM25GatewayUnitTest {

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
    public void shouldReturnPM25Result() throws Exception {
        // Create stub
        PM25Response pm25Response = new PM25Response(100);
        given(pm25Gateway.fetchData()).willReturn(Optional.of(pm25Response));

        // Act
        String pm25 = subject.getDataPM25();

        // Assert
        assertThat(pm25, is("100"));
    }

    @Test
    public void shouldReturnErrorMessageIfPM25IsUnavailable() throws Exception {
        // Create stub
        given(pm25Gateway.fetchData()).willReturn(Optional.empty());

        // Act
        String pm25 = subject.getDataPM25();

        // Assert
        assertThat(pm25, is("Can not get PM2.5 from API"));
    }

}
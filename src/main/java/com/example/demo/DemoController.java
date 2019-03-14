package com.example.demo;

import com.example.demo.person.Person;
import com.example.demo.person.PersonRepository;
import com.example.demo.pm25.PM25Gateway;
import com.example.demo.pm25.PM25Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DemoController {
    private final PersonRepository personRepository;
    private final PM25Gateway pm25Gateway;

    @Autowired
    public DemoController(final PersonRepository personRepository, final PM25Gateway pm25Gateway) {
        this.personRepository = personRepository;
        this.pm25Gateway = pm25Gateway;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/hello/{lastName}")
    public HelloResponse hello(@PathVariable final String lastName) {
        Optional<Person> foundPerson = personRepository.findByLastName(lastName);

        return foundPerson
                .map(person -> new HelloResponse(String.format("Hello %s %s!", person.getFirstName(), person.getLastName())))
                .orElse(new HelloResponse(String.format("Who is this '%s' you're talking about?", lastName)));
    }

    @GetMapping("/pm25")
    public String getDataPM25() {
        return pm25Gateway.fetchData()
                .map(PM25Response::getAqi)
                .orElse("Can not get PM2.5 from API");
    }
}

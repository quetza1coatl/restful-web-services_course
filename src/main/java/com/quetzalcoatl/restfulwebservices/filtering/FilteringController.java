package com.quetzalcoatl.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public SomeBean getSomeBean(){
        return new SomeBean(1, 2, 3);
    }

    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue getSomeBeanWithDynamicFiltering(){
        SomeBean bean = new SomeBean(1, 2, 3);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(bean);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @PostMapping("/filtering")
    public void createSomeBean(@RequestBody SomeBean bean){
        System.out.println(bean);
    }

}

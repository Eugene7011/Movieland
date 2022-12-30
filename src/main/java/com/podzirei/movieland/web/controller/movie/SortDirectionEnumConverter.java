package com.podzirei.movieland.web.controller.movie;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SortDirectionEnumConverter implements Converter<String, SortDirection> {
    @Override
    public SortDirection convert(String value) {
        return SortDirection.valueFrom(value);
    }
}

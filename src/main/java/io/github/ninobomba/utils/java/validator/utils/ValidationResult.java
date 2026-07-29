package io.github.ninobomba.utils.java.validator.utils;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ToString
@EqualsAndHashCode
public class ValidationResult {

    private final List<String> errors = new ArrayList<>();

    public void add(String msg) {
        errors.add(Objects.requireNonNull(msg, "msg must not be null"));
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

}
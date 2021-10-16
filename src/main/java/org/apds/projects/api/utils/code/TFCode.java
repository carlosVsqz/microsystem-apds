package org.apds.projects.api.utils.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TFCode implements BaseEnumCode<String> {

    TRUE("T"),
    FALSE("F"),
    NULL("")
    ;

    private final String value;
}

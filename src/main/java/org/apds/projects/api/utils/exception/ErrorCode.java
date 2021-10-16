package org.apds.projects.api.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apds.projects.api.utils.code.BaseEnumCode;

@Getter
@AllArgsConstructor
public enum ErrorCode implements BaseEnumCode<String> {

    BASE_CODE("Error.Base"),
    CODE2("Error.Code2"),
    CODE3("Error.Code3")
    ;

    private final String value;

}

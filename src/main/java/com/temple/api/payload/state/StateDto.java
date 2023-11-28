package com.temple.api.payload.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StateDto {
    private Long stateId;
    private Integer stateNo;
    private String state;
    private Integer gstStateNo;
    private boolean builtIn;
}

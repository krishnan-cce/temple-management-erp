package com.temple.api.payload.district;

import com.temple.api.entity.state.StateMaster;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDto {
    private Long districtId;
    private Integer districtNo;
    private String district;
    private long stateId;
    private int stateNo;
    private String addUid;
    private Date addDate;
}

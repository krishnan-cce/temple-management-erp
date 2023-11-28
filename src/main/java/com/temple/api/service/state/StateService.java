package com.temple.api.service.state;

import com.temple.api.payload.state.StateDto;
import com.temple.api.payload.state.StateResponse;

public interface StateService {
    StateResponse getAllState(int pageNo, int pageSize, String sortBy, String sortDir);
    StateDto createState(StateDto stateDto);
    StateDto getStateById(long StateId);
    StateDto updateState(StateDto stateDto,long stateId);
    void deleteById(long id);
}

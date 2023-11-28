package com.temple.api.serviceimpl.state;

import com.temple.api.entity.state.StateMaster;
import com.temple.api.exception.ResourceNotFoundException;
import com.temple.api.payload.state.StateDto;
import com.temple.api.payload.state.StateResponse;
import com.temple.api.repository.state.StateRepository;
import com.temple.api.service.state.StateService;
import com.temple.api.utils.UserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.plaf.nimbus.State;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateServiceImpl implements StateService {

    private final UserUtils userUtils;
    private ModelMapper modelMapper;
    private StateRepository stateRepository;

    public StateServiceImpl(UserUtils userUtils, ModelMapper modelMapper, StateRepository stateRepository) {
        this.userUtils = userUtils;
        this.modelMapper = modelMapper;
        this.stateRepository = stateRepository;
    }

    @Override
    public StateResponse getAllState(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("stateId").ascending()
                : Sort.by("stateId").descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<StateMaster> state = stateRepository.findAll(pageable);

        List<StateMaster> listOfState = state.getContent();
        List<StateDto> content = listOfState.stream().map(this::mapToDto).collect(Collectors.toList());

        StateResponse response = new StateResponse();
        response.setContent(content);
        response.setPageNo(state.getNumber());
        response.setPageSize(state.getSize());
        response.setTotalElements(state.getTotalElements());
        response.setTotalPages(state.getTotalPages());
        response.setLast(state.isLast());

        return response;
    }

    @Override
    public StateDto createState(StateDto stateDto) {
        StateMaster state = mapToEntity(stateDto);
        StateMaster newState = stateRepository.save(state);
        return mapToDto(newState);
    }

    @Override
    public StateDto getStateById(long StateId) {
        StateMaster state = stateRepository.findById(StateId).orElseThrow(() -> new ResourceNotFoundException("State","id",StateId));
        return mapToDto(state);
    }

    @Override
    public StateDto updateState(StateDto stateDto, long stateId) {
        StateMaster state = stateRepository.findById(stateId).orElseThrow(() -> new ResourceNotFoundException("State","id",stateId));
        state.setState(stateDto.getState());
        StateMaster updatedState = stateRepository.save(state);
        return mapToDto(updatedState);
    }

    @Override
    public void deleteById(long id) {
        StateMaster state = stateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("State","id",id));
        stateRepository.delete(state);
    }

    private StateDto mapToDto(StateMaster state){
        return modelMapper.map(state,StateDto.class);
    }

    private StateMaster mapToEntity(StateDto stateDto){
        return modelMapper.map(stateDto,StateMaster.class);
    }
}

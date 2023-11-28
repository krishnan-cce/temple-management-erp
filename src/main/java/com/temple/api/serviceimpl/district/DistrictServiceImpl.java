package com.temple.api.serviceimpl.district;

import com.temple.api.entity.district.DistrictMaster;
import com.temple.api.entity.state.StateMaster;
import com.temple.api.exception.ResourceNotFoundException;
import com.temple.api.payload.district.DistrictDto;
import com.temple.api.payload.district.DistrictResponse;
import com.temple.api.repository.district.DistrictMasterBusiness;
import com.temple.api.repository.district.DistrictRepository;
import com.temple.api.repository.state.StateRepository;
import com.temple.api.service.district.DistrictService;
import com.temple.api.utils.UserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl extends DistrictMasterBusiness implements DistrictService {

    private final UserUtils userUtils;
    private DistrictRepository districtRepository;
    private StateRepository stateRepository;

    public DistrictServiceImpl(UserUtils userUtils, DistrictRepository districtRepository, StateRepository stateRepository) {
        super();
        this.userUtils = userUtils;
        this.districtRepository = districtRepository;
        this.stateRepository = stateRepository;
    }

    @Override
    public DistrictResponse getAllDistricts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("districtId").ascending()
                : Sort.by("districtId").descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<DistrictMaster> district = districtRepository.findAll(pageable);

        List<DistrictDto> content = district.getContent().stream()
                .map(this::mapToDistrictDto)
                .collect(Collectors.toList());

        DistrictResponse response = new DistrictResponse();
        response.setContent(content);
        response.setPageNo(district.getNumber());
        response.setPageSize(district.getSize());
        response.setTotalElements(district.getTotalElements());
        response.setTotalPages(district.getTotalPages());
        response.setLast(district.isLast());

        return response;
    }

    @Override
    public DistrictResponse listAllByState(long stateId, int pageNo, int pageSize) {
        StateMaster state = stateRepository.findById(stateId)
                .orElseThrow(() -> new ResourceNotFoundException("State", "id", stateId));

        List<DistrictMaster> districtMasterList = super.getAllDistrictByStateWithPagination(state,pageNo,pageSize);

//        int start = pageNo * pageSize;
//        int end = Math.min((start + pageSize), districtMasterList.size());
//        List<DistrictDto> content = new ArrayList<>();
//        for (int i = start; i < end; i++) {
//            content.add(mapToDistrictDto(districtMasterList.get(i)));
//        }
//        Page<DistrictMaster> districtPage = new PageImpl<>(districtMasterList.subList(start, end),
//                PageRequest.of(pageNo, pageSize), districtMasterList.size());

        List<DistrictDto> content = districtMasterList.stream()
                .map(this::mapToDistrictDto)
                .collect(Collectors.toList());

        DistrictResponse response = new DistrictResponse();
        response.setContent(content);
        response.setPageNo(pageNo);
        response.setPageSize(pageSize);
        response.setTotalElements(districtMasterList.size());

        int totalPages = (int) Math.ceil((double) districtMasterList.size() / pageSize);
        response.setTotalPages(totalPages);
        response.setLast(pageNo >= totalPages - 1);

        return response;
    }



    @Override
    public DistrictDto createDistrict(DistrictDto districtDto) {
        StateMaster state = stateRepository.findById(districtDto.getStateId()).orElseThrow(() -> new ResourceNotFoundException("State","id",districtDto.getStateId()));
        DistrictMaster districtMaster = mapToEntity(districtDto,state);
        districtMaster.setAddDate(new Date());
        districtMaster.setAddUid(userUtils.getLoggedInUser().getId().toString());

        DistrictMaster newDistrictMaster = districtRepository.save(districtMaster);
        return mapToDistrictDto(newDistrictMaster);
    }

    @Override
    public DistrictDto updateDistrict(DistrictDto districtDto,long districtId) {
        DistrictMaster districtMaster = districtRepository.findById(districtId).orElseThrow(() -> new ResourceNotFoundException("District","id",districtId));
        districtMaster.setDistrict(districtDto.getDistrict());

        DistrictMaster updatedDistrict = districtRepository.save(districtMaster);
        return mapToDistrictDto(updatedDistrict);
    }

    @Override
    public DistrictDto getByDistrictId(long districtId) {
        DistrictMaster districtMaster = districtRepository.findById(districtId).orElseThrow(() -> new ResourceNotFoundException("District","id",districtId));
        return mapToDistrictDto(districtMaster);
    }

    @Override
    public void deleteDistrictById(long id) {
        DistrictMaster districtMaster = districtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("District","id",id));
        districtRepository.delete(districtMaster);
    }

    private DistrictDto mapToDistrictDto(DistrictMaster districtMaster) {
        DistrictDto dto = new DistrictDto();

        dto.setDistrictId(districtMaster.getDistrictId());
        dto.setDistrict(districtMaster.getDistrict());
        dto.setAddDate(districtMaster.getAddDate());
        dto.setAddUid(districtMaster.getAddUid());
        dto.setStateId(districtMaster.getStateMaster().getStateId());
        dto.setStateNo(districtMaster.getStateMaster().getStateNo());
        dto.setDistrictNo(districtMaster.getDistrictNo());

        return dto;
    }

    private DistrictMaster mapToEntity(DistrictDto districtDto,StateMaster stateMaster) {
        DistrictMaster districtMaster = new DistrictMaster();

        districtMaster.setDistrictId(districtDto.getDistrictId());
        districtMaster.setDistrict(districtDto.getDistrict());
        districtMaster.setStateMaster(stateMaster);
        districtMaster.setStateNo(stateMaster.getStateNo());
        districtMaster.setDistrictNo(districtDto.getDistrictNo());

        return districtMaster;
    }
}

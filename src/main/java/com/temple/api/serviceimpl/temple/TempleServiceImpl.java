package com.temple.api.serviceimpl.temple;

import com.temple.api.entity.temple.Temple;
import com.temple.api.exception.ResourceNotFoundException;
import com.temple.api.payload.temple.TempleDto;
import com.temple.api.payload.temple.TempleResponse;
import com.temple.api.repository.temple.TempleRepository;
import com.temple.api.service.temple.TempleService;
import com.temple.api.utils.UserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TempleServiceImpl implements TempleService {
    private final UserUtils userUtils;
    private ModelMapper modelMapper;
    private TempleRepository templeRepository;

    public TempleServiceImpl(UserUtils userUtils, ModelMapper modelMapper, TempleRepository templeRepository) {
        this.userUtils = userUtils;
        this.modelMapper = modelMapper;
        this.templeRepository = templeRepository;
    }

    @Override
    public TempleResponse getAllTemples(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("templeID").ascending()
                : Sort.by("templeID").descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Temple> temples = templeRepository.findAll(pageable);

        List<Temple> listOfTemples = temples.getContent();

        List<TempleDto> content= listOfTemples.stream().map(this::mapToDTO).collect(Collectors.toList());

        TempleResponse response = new TempleResponse();
        response.setContent(content);
        response.setPageNo(temples.getNumber());
        response.setPageSize(temples.getSize());
        response.setTotalElements(temples.getTotalElements());
        response.setTotalPages(temples.getTotalPages());
        response.setLast(temples.isLast());

        return response;
    }

    @Override
    public TempleDto createTemple(TempleDto templeDto) {
        Temple temple = mapToEntity(templeDto);
        temple.setAddedDate(new Date());
        temple.setAddUID(userUtils.getLoggedInUser().getId().toString());
        Temple newTemple = templeRepository.save(temple);
        return mapToDTO(newTemple);
    }
    @Override
    public TempleDto getTempleById(long templeId) {
        Temple temple = templeRepository.findById(templeId).orElseThrow(() -> new ResourceNotFoundException("Temple","id",templeId));
        return mapToDTO(temple);
    }

    @Override
    public TempleDto updateTemple(TempleDto templeDto, long templeId) {
        Temple temple = templeRepository.findById(templeId).orElseThrow(() -> new ResourceNotFoundException("Temple","id",templeId));
        temple.setTempleName(templeDto.getTempleName());
        temple.setTempleAddress(templeDto.getTempleAddress());
        temple.setContactNumber(templeDto.getContactNumber());
        Temple updatedTemple = templeRepository.save(temple);
        return mapToDTO(updatedTemple);
    }

    @Override
    public void deleteTempleById(long id) {
        Temple temple = templeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Temple","id",id));
        templeRepository.delete(temple);
    }

    private TempleDto mapToDTO(Temple temple) {
        return modelMapper.map(temple,TempleDto.class);
    }

    private Temple mapToEntity(TempleDto templeDto) {
        return modelMapper.map(templeDto,Temple.class);
    }
}

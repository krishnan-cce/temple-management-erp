package com.temple.api.service.temple;

import com.temple.api.payload.temple.TempleDto;
import com.temple.api.payload.temple.TempleResponse;

public interface TempleService {

    TempleResponse getAllTemples(int pageNo, int pageSize, String sortBy, String sortDir);

    TempleDto createTemple(TempleDto templeDto);

    TempleDto getTempleById(long templeId);

    TempleDto updateTemple(TempleDto templeDto,long templeId);

    void deleteTempleById(long id);
}

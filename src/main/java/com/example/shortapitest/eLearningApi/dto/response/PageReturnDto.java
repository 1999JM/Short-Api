package com.example.shortapitest.eLearningApi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageReturnDto {

    private long pageNumber;

    private long pageSize;

    private long totalPages;

    private long totalElements;

    @Builder.Default
    private List<Object> eLearningDto = new ArrayList<>();

    public PageReturnDto(Page<ELSettingReturnDto> elSettingReturnDtoList) {

        this.pageNumber = elSettingReturnDtoList.getNumber();
        this.pageSize = elSettingReturnDtoList.getSize();
        this.totalPages = elSettingReturnDtoList.getTotalPages();
        this.totalElements = elSettingReturnDtoList.getTotalElements();

        this.eLearningDto = Collections.singletonList(elSettingReturnDtoList.getContent());
    }
}

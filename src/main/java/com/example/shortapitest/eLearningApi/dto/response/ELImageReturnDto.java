package com.example.shortapitest.eLearningApi.dto.response;

import com.example.shortapitest.eLearningApi.entity.image.MenuImage;
import com.example.shortapitest.eLearningApi.entity.image.QuestionImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ELImageReturnDto {

    private Long imageId;

    private int imageSequence;

    private String filename;

    private String fileUrl;

    public static ELImageReturnDto setELImageReturnDto(MenuImage menuImage) {
        ELImageReturnDto elImageReturnDto = new ELImageReturnDto();
        elImageReturnDto.setImageId(menuImage.getId());
        elImageReturnDto.setFilename(menuImage.getFilename());
        elImageReturnDto.setFileUrl(menuImage.getFileUrl());
        elImageReturnDto.setImageSequence(menuImage.getMenuImageSequence());

        return elImageReturnDto;
    }

    public static ELImageReturnDto setELImageReturnDto(QuestionImage questionImage) {
        ELImageReturnDto elImageReturnDto = new ELImageReturnDto();
        elImageReturnDto.setImageId(questionImage.getId());
        elImageReturnDto.setFilename(questionImage.getFilename());
        elImageReturnDto.setFileUrl(questionImage.getFileUrl());

        return elImageReturnDto;
    }
}

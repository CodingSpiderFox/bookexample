package com.example.test.service;

import com.example.test.service.dto.AutobiographyDTO;
import com.example.test.service.dto.LetAuthorBeBornAndPublishAutobiographyDto;

public interface AutobiographyCommandHandler {
    AutobiographyDTO letAuthorBeBornAndPublishAutobiography(
        LetAuthorBeBornAndPublishAutobiographyDto letAuthorBeBornAndPublishAutobiographyDto
    );
}

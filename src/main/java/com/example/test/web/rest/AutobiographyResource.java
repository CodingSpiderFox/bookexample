package com.example.test.web.rest;

import com.example.test.service.AutobiographyCommandHandler;
import com.example.test.service.dto.AutobiographyDTO;
import com.example.test.service.dto.LetAuthorBeBornAndPublishAutobiographyDto;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.HeaderUtil;

@RestController
@RequestMapping("/api")
public class AutobiographyResource {

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutobiographyCommandHandler autobiographyCommandHandler;

    public AutobiographyResource(AutobiographyCommandHandler autobiographyCommandHandler) {
        this.autobiographyCommandHandler = autobiographyCommandHandler;
    }

    @PostMapping("/command/let-author-be-born-and-write-autobiography")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AutobiographyDTO> letAuthorBeBornAndPublishAutobiography(
        @Valid @RequestBody LetAuthorBeBornAndPublishAutobiographyDto dto
    ) throws URISyntaxException {
        AutobiographyDTO result;
        try {
            result = autobiographyCommandHandler.letAuthorBeBornAndPublishAutobiography(dto);
            return ResponseEntity
                .created(new URI("/api/books/" + result.getBookId()))
                .headers(HeaderUtil.createAlert(applicationName, "Succeeded", ""))
                .body(result);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}

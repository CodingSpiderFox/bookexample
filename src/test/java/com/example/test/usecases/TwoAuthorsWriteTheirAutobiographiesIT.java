package com.example.test.usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.test.IntegrationTest;
import com.example.test.repository.AuthorRepository;
import com.example.test.repository.BookRepository;
import com.example.test.service.dto.AutobiographyDTO;
import com.example.test.service.dto.LetAuthorBeBornAndPublishAutobiographyDto;
import com.example.test.service.mapper.AuthorMapper;
import com.example.test.service.mapper.BookMapper;
import com.example.test.web.rest.AutobiographyResource;
import com.example.test.web.rest.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@IntegrationTest
@WithMockUser
@AutoConfigureMockMvc
public class TwoAuthorsWriteTheirAutobiographiesIT {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AutobiographyResource underTest;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    void twoAuthorsAreBornAndLaterWriteTheirAutobiographiesSuccessfullyTest() throws Exception {
        long authorsCountBefore = authorRepository.findAll().size();
        long booksCountBefore = bookRepository.findAll().size();

        LetAuthorBeBornAndPublishAutobiographyDto commandDto = new LetAuthorBeBornAndPublishAutobiographyDto();
        commandDto.setFirstName("Peter");
        commandDto.setLastName("Paul");
        commandDto.setBirthTimestampUtc(ZonedDateTime.of(1980, 1, 1, 1, 1, 1, 12, ZoneId.of("Z")).toString());
        commandDto.setWriteStartTimestampUtc(ZonedDateTime.of(2021, 1, 1, 1, 1, 1, 12, ZoneId.of("Z")).toString());
        commandDto.setPublishTimestampUtc(ZonedDateTime.of(2021, 3, 1, 1, 1, 1, 12, ZoneId.of("Z")).toString());
        commandDto.setBookPrice(1.99D);
        commandDto.setBookTitle("Peter Biography");

        MvcResult result = mockMvc
            .perform(
                post("/api/command/let-author-be-born-and-write-autobiography")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(commandDto))
            )
            .andExpect(status().isCreated())
            .andReturn();

        AutobiographyDTO autobiographyDTO = new ObjectMapper().readValue(result.getResponse().getContentAsString(), AutobiographyDTO.class);

        assertEquals("Peter", autobiographyDTO.getFirstName());
        assertNotNull(autobiographyDTO.getAuthorId());
        assertNotNull(autobiographyDTO.getBookId());

        LetAuthorBeBornAndPublishAutobiographyDto command2Dto = new LetAuthorBeBornAndPublishAutobiographyDto();
        command2Dto.setFirstName("Laura");
        command2Dto.setLastName("Paul");
        command2Dto.setBirthTimestampUtc(ZonedDateTime.of(1980, 1, 1, 1, 1, 1, 12, ZoneId.of("Z")).toString());
        command2Dto.setWriteStartTimestampUtc(ZonedDateTime.of(2021, 1, 1, 1, 1, 1, 12, ZoneId.of("Z")).toString());
        command2Dto.setPublishTimestampUtc(ZonedDateTime.of(2021, 3, 1, 1, 1, 1, 12, ZoneId.of("Z")).toString());
        command2Dto.setBookPrice(1.99D);
        command2Dto.setBookTitle("Laura Biography");

        MvcResult resultLaura = mockMvc
            .perform(
                post("/api/command/let-author-be-born-and-write-autobiography")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(command2Dto))
            )
            .andExpect(status().isCreated())
            .andReturn();

        AutobiographyDTO laurasAutobiographyDTO = new ObjectMapper()
            .readValue(resultLaura.getResponse().getContentAsString(), AutobiographyDTO.class);

        assertEquals("Laura", laurasAutobiographyDTO.getFirstName());
        assertNotNull(laurasAutobiographyDTO.getAuthorId());
        assertNotNull(laurasAutobiographyDTO.getBookId());

        assertNotEquals(laurasAutobiographyDTO.getAuthorId(), autobiographyDTO.getAuthorId());
        assertNotEquals(laurasAutobiographyDTO.getBookId(), autobiographyDTO.getBookId());

        assertEquals(authorsCountBefore + 2, authorRepository.findAll().size());
        assertEquals(booksCountBefore + 2, bookRepository.findAll().size());
    }

    @Test
    void birthDateAfterWriteStartDateResultsInBadRequest() throws Exception {
        long authorsCountBefore = authorRepository.findAll().size();
        long booksCountBefore = bookRepository.findAll().size();

        LetAuthorBeBornAndPublishAutobiographyDto commandDto = new LetAuthorBeBornAndPublishAutobiographyDto();
        commandDto.setFirstName("Peter");
        commandDto.setLastName("Paul");
        commandDto.setBirthTimestampUtc(ZonedDateTime.of(2021, 1, 1, 1, 1, 1, 12, ZoneId.of("Z")).toString());
        commandDto.setWriteStartTimestampUtc(ZonedDateTime.of(1980, 1, 1, 1, 1, 1, 12, ZoneId.of("Z")).toString());
        commandDto.setPublishTimestampUtc(ZonedDateTime.of(2021, 3, 1, 1, 1, 1, 12, ZoneId.of("Z")).toString());
        commandDto.setBookPrice(1.99D);
        commandDto.setBookTitle("Peter Biography");

        MvcResult result = mockMvc
            .perform(
                post("/api/command/let-author-be-born-and-write-autobiography")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(commandDto))
            )
            .andExpect(status().isBadRequest())
            .andReturn();

        assertEquals(authorsCountBefore, authorRepository.findAll().size());
        assertEquals(booksCountBefore, bookRepository.findAll().size());
    }
}

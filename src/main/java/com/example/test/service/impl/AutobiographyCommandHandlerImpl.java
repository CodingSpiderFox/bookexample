package com.example.test.service.impl;

import com.example.test.domain.Author;
import com.example.test.domain.Book;
import com.example.test.repository.AuthorRepository;
import com.example.test.repository.BookRepository;
import com.example.test.service.AutobiographyCommandHandler;
import com.example.test.service.dto.AutobiographyDTO;
import com.example.test.service.dto.LetAuthorBeBornAndPublishAutobiographyDto;
import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AutobiographyCommandHandlerImpl implements AutobiographyCommandHandler {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final long minSecondsBetweenBirthAndWriteStart = Period.ofYears(30).getDays() * 24 * 60 * 60;

    public AutobiographyCommandHandlerImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public AutobiographyDTO letAuthorBeBornAndPublishAutobiography(
        LetAuthorBeBornAndPublishAutobiographyDto letAuthorBeBornAndPublishAutobiographyDto
    ) {
        Instant birthTimestampUtc = timestampStringToUtcInstant(letAuthorBeBornAndPublishAutobiographyDto.getBirthTimestampUtc());
        Instant writeStartTimestampUtc = timestampStringToUtcInstant(letAuthorBeBornAndPublishAutobiographyDto.getWriteStartTimestampUtc());
        Instant publishTimestampUtc = timestampStringToUtcInstant(letAuthorBeBornAndPublishAutobiographyDto.getPublishTimestampUtc());

        long secondsBetweenBirthAndWriteStart = secondsBetween(birthTimestampUtc, writeStartTimestampUtc);

        if (secondsBetweenBirthAndWriteStart < minSecondsBetweenBirthAndWriteStart) {
            throw new IllegalArgumentException(
                "At least " + minSecondsBetweenBirthAndWriteStart + " seconds must pass between birth and write start"
            );
        }

        if (publishTimestampUtc.isBefore(writeStartTimestampUtc)) {
            throw new IllegalArgumentException("Write start must not be after publish timestamp");
        }

        Author author = authorRepository.save(
            new Author()
                .firstName(letAuthorBeBornAndPublishAutobiographyDto.getFirstName())
                .lastName(letAuthorBeBornAndPublishAutobiographyDto.getLastName())
                .birthTimestamp(birthTimestampUtc)
        );

        Book book = bookRepository.save(
            new Book()
                .title(letAuthorBeBornAndPublishAutobiographyDto.getBookTitle())
                .price(letAuthorBeBornAndPublishAutobiographyDto.getBookPrice())
                .authors(Collections.singleton(author))
                .writeStartTimestamp(writeStartTimestampUtc)
                .publishTimestamp(publishTimestampUtc)
        );

        return buildAutobiographyResultDTO(author, book);
    }

    private long secondsBetween(Instant timestamp1, Instant timestamp2) {
        return Duration.between(timestamp1, timestamp2).getSeconds();
    }

    private Instant timestampStringToUtcInstant(String timestampString) {
        return ZonedDateTime.parse(timestampString).toInstant().atZone(ZoneId.of("Z")).toInstant();
    }

    private AutobiographyDTO buildAutobiographyResultDTO(Author author, Book book) {
        AutobiographyDTO result = new AutobiographyDTO();
        result.setAuthorId(author.getId());
        result.setFirstName(author.getFirstName());
        result.setLastName(author.getLastName());
        result.setBirthTimestampUtc(author.getBirthTimestamp().toString());
        result.setBookId(book.getId());
        result.setBookTitle(book.getTitle());
        result.setBookPrice(book.getPrice());
        result.setWriteStartTimestampUtc(book.getWriteStartTimestamp().toString());
        result.setPublishTimestampUtc(book.getPublishTimestamp().toString());

        return result;
    }
}

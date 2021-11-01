package com.example.test.service.mapper;

import com.example.test.domain.Book;
import com.example.test.service.dto.BookDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Book} and its DTO {@link BookDTO}.
 */
@Mapper(componentModel = "spring", uses = { AuthorMapper.class })
public interface BookMapper extends EntityMapper<BookDTO, Book> {
    @Mapping(target = "writtenBies", source = "writtenBies", qualifiedByName = "idSet")
    BookDTO toDto(Book s);

    @Mapping(target = "removeWrittenBy", ignore = true)
    Book toEntity(BookDTO bookDTO);
}

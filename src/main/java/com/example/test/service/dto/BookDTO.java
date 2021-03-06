package com.example.test.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.example.test.domain.Book} entity.
 */
public class BookDTO implements Serializable {

    private Long id;

    @NotNull
    private Double price;

    @NotNull
    private String title;

    private Instant writeStartTimestamp;

    private Instant publishTimestamp;

    private Set<AuthorDTO> authors = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getWriteStartTimestamp() {
        return writeStartTimestamp;
    }

    public void setWriteStartTimestamp(Instant writeStartTimestamp) {
        this.writeStartTimestamp = writeStartTimestamp;
    }

    public Instant getPublishTimestamp() {
        return publishTimestamp;
    }

    public void setPublishTimestamp(Instant publishTimestamp) {
        this.publishTimestamp = publishTimestamp;
    }

    public Set<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDTO> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookDTO)) {
            return false;
        }

        BookDTO bookDTO = (BookDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bookDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookDTO{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", title='" + getTitle() + "'" +
            ", writeStartTimestamp='" + getWriteStartTimestamp() + "'" +
            ", publishTimestamp='" + getPublishTimestamp() + "'" +
            ", authors=" + getAuthors() +
            "}";
    }
}

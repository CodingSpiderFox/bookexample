package com.example.test.service.dto;

public class LetAuthorBeBornAndPublishAutobiographyDto {

    private String firstName;

    private String lastName;

    private String bookTitle;

    private Double bookPrice;

    private String birthTimestampUtc;

    private String writeStartTimestampUtc;

    private String publishTimestampUtc;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBirthTimestampUtc() {
        return birthTimestampUtc;
    }

    public void setBirthTimestampUtc(String birthTimestampUtc) {
        this.birthTimestampUtc = birthTimestampUtc;
    }

    public String getWriteStartTimestampUtc() {
        return writeStartTimestampUtc;
    }

    public void setWriteStartTimestampUtc(String writeStartTimestampUtc) {
        this.writeStartTimestampUtc = writeStartTimestampUtc;
    }

    public String getPublishTimestampUtc() {
        return publishTimestampUtc;
    }

    public void setPublishTimestampUtc(String publishTimestampUtc) {
        this.publishTimestampUtc = publishTimestampUtc;
    }

    @Override
    public String toString() {
        return (
            "LetAuthorBeBornAndPublishAutobiographyDto{" +
            "firstName='" +
            firstName +
            '\'' +
            ", lastName='" +
            lastName +
            '\'' +
            ", bookTitle='" +
            bookTitle +
            '\'' +
            ", bookPrice=" +
            bookPrice +
            ", birthTimestampUtc='" +
            birthTimestampUtc +
            '\'' +
            ", writeStartTimestampUtc='" +
            writeStartTimestampUtc +
            '\'' +
            ", publishTimestampUtc='" +
            publishTimestampUtc +
            '\'' +
            '}'
        );
    }
}

package pl.achcinski.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "book")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Pole 'tytuł' nie może być puste")
    private String title;

    @NotBlank(message = "Pole 'autor' nie może być puste")
    private String author;

    @Lob
    private String image;

    private String description;
    private String publisher;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate publishedOn;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private BookCategory category;

    @Min(value = 1, message = "Ilość stron musi być większa od 0")
    private int pages;

    @Min(value = 0, message = "Cena nie może być ujemna")
    private float price;

    public Book(String title, String author, String description, String publisher, LocalDate publishedOn,
                BookCategory category, int pages, float price) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.publisher = publisher;
        this.publishedOn = publishedOn;
        this.category = category;
        this.pages = pages;
        this.price = price;
    }


}

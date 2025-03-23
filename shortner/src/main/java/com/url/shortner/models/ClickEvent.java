package com.url.shortner.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class ClickEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name="url_mapping_id")
    private UrlMapping urlMapping; 

    
}

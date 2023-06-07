package com.imdb.imdbbackend.dto.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * Pageable request class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class PageableRequest {
    private @Valid Pageable pageable;
}

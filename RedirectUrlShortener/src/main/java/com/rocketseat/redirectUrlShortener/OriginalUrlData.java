package com.rocketseat.redirectUrlShortener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OriginalUrlData {

    private String originalUrl;
    private long expirationTime;
}

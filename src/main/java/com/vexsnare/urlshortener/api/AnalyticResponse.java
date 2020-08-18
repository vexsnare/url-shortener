package com.vexsnare.urlshortener.api;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author vinay.saini
 * @created 18/08/2020 - 10:51 AM
 */

@Data
@Builder
public class AnalyticResponse {
    @NotNull
    String key;

    @NotNull
    String status;

    @NotNull
    Integer count;
}

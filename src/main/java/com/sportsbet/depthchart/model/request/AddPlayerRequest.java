package com.sportsbet.depthchart.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class AddPlayerRequest {

    private Long playerId;
    private String name;
    private String position;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer positionDepth;
    private String gameType;

}

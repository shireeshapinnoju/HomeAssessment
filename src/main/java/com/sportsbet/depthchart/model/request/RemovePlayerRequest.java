package com.sportsbet.depthchart.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class RemovePlayerRequest {

    private Long playerId;
    private String name;
    private String position;
    private String gameType;

}

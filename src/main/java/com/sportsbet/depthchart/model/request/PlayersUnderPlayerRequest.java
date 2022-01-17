package com.sportsbet.depthchart.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class PlayersUnderPlayerRequest {

    private String name;
    private String position;
    private String gameType;

}

package com.sportsbet.depthchart.model.request;


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
public class Player {

    private Long playerId;
    private String name;


    public String toString() {
        return "Name: '" + this.name + "', PlayerId: '" + this.playerId + "'";
    }

}

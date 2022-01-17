package com.sportsbet.depthchart.controller;



import com.sportsbet.depthchart.model.response.DepthChartResponse;
import com.sportsbet.depthchart.service.DepthChartFactory;
import com.sportsbet.depthchart.service.DepthChartService;
import com.sportsbet.depthchart.service.implementation.NFLDepthChartService;
import com.sportsbet.depthchart.model.request.Player;
import com.sportsbet.depthchart.model.request.AddPlayerRequest;
import com.sportsbet.depthchart.model.request.PlayersUnderPlayerRequest;
import com.sportsbet.depthchart.model.request.RemovePlayerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class DepthController {

    private final DepthChartFactory depthChartFactory;


    @Autowired
    public DepthController(DepthChartFactory depthChartFactory) {
        this.depthChartFactory = depthChartFactory;
    }

    @PostMapping(path = "addPlayer", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<DepthChartResponse> addPlayer(@RequestBody final AddPlayerRequest addPlayer) {

        Player player = Player.builder()
                            .playerId(addPlayer.getPlayerId())
                            .name(addPlayer.getName())
                            .build();
        String gameType = addPlayer.getGameType();
        DepthChartService depthChartService = depthChartFactory.getGameType(gameType);
       // return depthChartService.addRemovePlayerFromDepthChart(player, addPlayer.getPosition(), addPlayer.getPositionDepth(), true);
        if(gameType != null) {
            return depthChartService.addRemovePlayerFromDepthChart(player, addPlayer.getPosition(), addPlayer.getPositionDepth(), true);
        } else {
            DepthChartResponse response = DepthChartResponse.builder()
                                            .code(400)
                                            .message("Provide valid Game Type")
                                            .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
    }


    @GetMapping(path = "getFullDepthChart")
    public ResponseEntity<String>  getFullDepthChart(@RequestParam String gameType) {
        DepthChartService depthChartService = depthChartFactory.getGameType(gameType);
        if(gameType != null) {
            return depthChartService.getFullDepthChart();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Provide valid Game Type");
        }
    }

    @PostMapping(path = "removePlayer", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<DepthChartResponse> removePlayer(@RequestBody final RemovePlayerRequest removePlayer) {

        Player player = Player.builder()
                .playerId(removePlayer.getPlayerId())
                .name(removePlayer.getName())
                .build();

        Integer positionDepth = null;
        String gameType = removePlayer.getGameType();
        DepthChartService depthChartService = depthChartFactory.getGameType(gameType);
        //depthChartService.addRemovePlayerFromDepthChart(player, removePlayer.getPosition(), positionDepth, false);
        if(gameType != null) {
            return depthChartService.addRemovePlayerFromDepthChart(player, removePlayer.getPosition(), positionDepth, false);
        } else {
            DepthChartResponse response = DepthChartResponse.builder()
                    .code(400)
                    .message("Provide valid Game Type")
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
    }

    @PostMapping(path = "getPlayersUnderPlayer", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPlayersUnderPlayerInDepthChart(@RequestBody final PlayersUnderPlayerRequest request) {

        String gameType = request.getGameType();
        DepthChartService depthChartService = depthChartFactory.getGameType(gameType);
        if(gameType != null) {
            return depthChartService.getPlayersUnderPlayerInDepthChart(request);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Provide valid Game Type");
        }
    }
}

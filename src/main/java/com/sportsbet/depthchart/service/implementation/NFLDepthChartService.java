package com.sportsbet.depthchart.service.implementation;

import com.sportsbet.depthchart.model.request.Player;
import com.sportsbet.depthchart.model.request.PlayersUnderPlayerRequest;
import com.sportsbet.depthchart.model.response.DepthChartResponse;
import com.sportsbet.depthchart.service.DepthChartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NFLDepthChartService extends DepthChartService {

    List<Stack<Player>> wrPositions = new ArrayList<>();
    List<Stack<Player>> qbPositions = new ArrayList<>();
    List<Stack<Player>> rbPositions = new ArrayList<>();
    List<Stack<Player>> tePositions = new ArrayList<>();
    List<Stack<Player>> kPositions = new ArrayList<>();
    List<Stack<Player>> pPositions = new ArrayList<>();
    List<Stack<Player>> krPositions = new ArrayList<>();
    List<Stack<Player>> prPositions = new ArrayList<>();

    public ResponseEntity<DepthChartResponse> addRemovePlayerFromDepthChart(final Player player, final String position, final Integer positionDepth, final Boolean add) {

        String positionUpper = position.toUpperCase();
        switch (positionUpper) {
            case "WR" :
                System.out.println("initial wrPositions in NFL service: "+ wrPositions);
                wrPositions =  add ? addPlayer(wrPositions,  positionDepth, player) : removePlayer(wrPositions, player);
                System.out.println("wrPositions in NFL service: "+ wrPositions);
                depthFullChart.put("WR", wrPositions);
                if(wrPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "QB" :
                System.out.println("initial qbPositions in NFL service: "+ qbPositions);
                qbPositions = add ? addPlayer(qbPositions,  positionDepth, player)  : removePlayer(qbPositions, player);
                System.out.println("qbPositions in NFL service: "+ qbPositions);
                depthFullChart.put("QB", qbPositions);
                if(qbPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "RB" :
                System.out.println("initial rbPositions in NFL service: "+ rbPositions);
                rbPositions = add ? addPlayer(rbPositions,  positionDepth, player)  : removePlayer(rbPositions, player);
                System.out.println("rbPositions in NFL service: "+ rbPositions);
                depthFullChart.put("RB", rbPositions);
                if(rbPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "TE" :
                System.out.println("initial tePositions in NFL service: "+ tePositions);
                tePositions = add ? addPlayer(tePositions,  positionDepth, player)  : removePlayer(tePositions, player);
                System.out.println("tePositions in NFL service: "+ tePositions);
                depthFullChart.put("TE", tePositions);
                if(tePositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "K" :
                System.out.println("initial kPositions in NFL service: "+ kPositions);
                kPositions = add ? addPlayer(kPositions,  positionDepth, player)    : removePlayer(kPositions, player);
                System.out.println("kPositions in NFL service: "+ kPositions);
                depthFullChart.put("K", kPositions);
                if(kPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "P" :
                System.out.println("initial pPositions in NFL service: "+ pPositions);
                pPositions = add ? addPlayer(pPositions,  positionDepth, player)    : removePlayer(pPositions, player);
                System.out.println("pPositions in NFL service: "+ pPositions);
                depthFullChart.put("P", pPositions);
                if(pPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "KR" :
                System.out.println("initial krPositions in NFL service: "+ krPositions);
                krPositions = add ? addPlayer(krPositions,  positionDepth, player)  : removePlayer(krPositions, player);
                System.out.println("krPositions in NFL service: "+ krPositions);
                depthFullChart.put("KR", krPositions);
                if(krPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "PR" :
                System.out.println("initial prPositions in NFL service: "+ prPositions);
                prPositions = add ? addPlayer(prPositions,  positionDepth, player)  : removePlayer(prPositions, player);
                System.out.println("prPositions in NFL service: "+ prPositions);
                depthFullChart.put("PR", prPositions);
                if(prPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            default:
                DepthChartResponse response = DepthChartResponse.builder()
                        .code(400)
                        .message("Provide valid position type for game NFL")
                        .build();

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(response);

        }

        System.out.println("========================");
        System.out.println("depthFullChart in NFL service: "+depthFullChart);
        System.out.println("========================");

       String message =  add ? "Successfully Added Player to the NFL Depth Chart" : "Successfully removed Player from the NFL Depth Chart";

       DepthChartResponse response = DepthChartResponse.builder()
                                        .code(200)
                                        .message(message)
                                        .build();

       return ResponseEntity.status(HttpStatus.OK)
               .body(response);

    }


}

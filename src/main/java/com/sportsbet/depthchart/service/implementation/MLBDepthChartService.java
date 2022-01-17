package com.sportsbet.depthchart.service.implementation;

import com.sportsbet.depthchart.model.request.Player;
import com.sportsbet.depthchart.model.response.DepthChartResponse;
import com.sportsbet.depthchart.service.DepthChartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class MLBDepthChartService extends DepthChartService {

    List<Stack<Player>> spPositions = new ArrayList<>();
    List<Stack<Player>> rpPositions = new ArrayList<>();
    List<Stack<Player>> cPositions = new ArrayList<>();
    List<Stack<Player>> positions1B = new ArrayList<>();
    List<Stack<Player>> positions2B = new ArrayList<>();
    List<Stack<Player>> positions3B = new ArrayList<>();
    List<Stack<Player>> ssPositions = new ArrayList<>();
    List<Stack<Player>> lfPositions = new ArrayList<>();
    List<Stack<Player>> rfPositions = new ArrayList<>();
    List<Stack<Player>> cfPositions = new ArrayList<>();
    List<Stack<Player>> dhPositions = new ArrayList<>();

    public ResponseEntity<DepthChartResponse> addRemovePlayerFromDepthChart(final Player player, final String position, final Integer positionDepth, final Boolean add) {

        String positionUpper = position.toUpperCase();
        switch (positionUpper) {
            case "SP" :
                System.out.println("initial spPositions in MLB service: "+ spPositions);
                spPositions =  add ? addPlayer(spPositions,  positionDepth, player) : removePlayer(spPositions, player);
                System.out.println("spPositions in MLB service: "+ spPositions);
                depthFullChart.put("SP", spPositions);
                if(spPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "RP" :
                System.out.println("initial rpPositions in MLB service: "+ rpPositions);
                rpPositions = add ? addPlayer(rpPositions,  positionDepth, player)  : removePlayer(rpPositions, player);
                System.out.println("rpPositions in MLB service: "+ rpPositions);
                depthFullChart.put("RP", rpPositions);
                if(rpPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "C" :
                System.out.println("initial cPositions in MLB service: "+ cPositions);
                cPositions = add ? addPlayer(cPositions,  positionDepth, player)  : removePlayer(cPositions, player);
                System.out.println("cPositions in MLB service: "+ cPositions);
                depthFullChart.put("C", cPositions);
                if(cPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "1B" :
                System.out.println("initial 1Bpositions in MLB service: "+ positions1B);
                positions1B = add ? addPlayer(positions1B,  positionDepth, player)  : removePlayer(positions1B, player);
                System.out.println("1Bpositions in MLB service: "+ positions1B);
                depthFullChart.put("1B", positions1B);
                if(positions1B.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "2B" :
                System.out.println("initial 2BPositions in MLB service: "+ positions2B);
                positions2B = add ? addPlayer(positions2B,  positionDepth, player)    : removePlayer(positions2B, player);
                System.out.println("2BPositions in MLB service: "+ positions2B);
                depthFullChart.put("2B", positions2B);
                if(positions2B.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "3B" :
                System.out.println("initial 3BPositions in MLB service: "+ positions3B);
                positions3B = add ? addPlayer(positions3B,  positionDepth, player)    : removePlayer(positions3B, player);
                System.out.println("3BPositions in MLB service: "+ positions3B);
                depthFullChart.put("3B", positions3B);
                if(positions3B.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "SS" :
                System.out.println("initial ssPositions in MLB service: "+ ssPositions);
                ssPositions = add ? addPlayer(ssPositions,  positionDepth, player)  : removePlayer(ssPositions, player);
                System.out.println("ssPositions in MLB service: "+ ssPositions);
                depthFullChart.put("SS", ssPositions);
                if(ssPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "LF" :
                System.out.println("initial lfPositions in MLB service: "+ lfPositions);
                lfPositions = add ? addPlayer(lfPositions,  positionDepth, player)  : removePlayer(lfPositions, player);
                System.out.println("lfPositions in MLB service: "+ lfPositions);
                depthFullChart.put("LF", lfPositions);
                if(lfPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "RF" :
                System.out.println("initial rfPositions in MLB service: "+ rfPositions);
                rfPositions = add ? addPlayer(rfPositions,  positionDepth, player)  : removePlayer(rfPositions, player);
                System.out.println("rfPositions in MLB service: "+ rfPositions);
                depthFullChart.put("RF", ssPositions);
                if(rfPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "CF" :
                System.out.println("initial cfPositions in MLB service: "+ cfPositions);
                cfPositions = add ? addPlayer(cfPositions,  positionDepth, player)  : removePlayer(cfPositions, player);
                System.out.println("cfPositions in MLB service: "+ cfPositions);
                depthFullChart.put("CF", cfPositions);
                if(cfPositions.size()== 0) {
                    return formResponsePayloadForZeroSizePositions();
                }
                break;
            case "DH" :
                System.out.println("initial dhPositions in MLB service: "+ dhPositions);
                dhPositions = add ? addPlayer(dhPositions,  positionDepth, player)  : removePlayer(dhPositions, player);
                System.out.println("dhPositions in MLB service: "+ dhPositions);
                depthFullChart.put("DH", dhPositions);
                if(dhPositions.size()== 0) {
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
        System.out.println("depthFullChart in MLB service: "+depthFullChart);
        System.out.println("========================");

       String message =  add ? "Successfully Added Player to the MLB Depth Chart" : "Successfully removed Player from the MLB Depth Chart";

       DepthChartResponse response = DepthChartResponse.builder()
                                        .code(200)
                                        .message(message)
                                        .build();

       return ResponseEntity.status(HttpStatus.OK)
               .body(response);

    }

}

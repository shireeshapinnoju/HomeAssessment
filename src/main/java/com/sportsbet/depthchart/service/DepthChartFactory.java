package com.sportsbet.depthchart.service;

import com.sportsbet.depthchart.service.implementation.NFLDepthChartService;
import com.sportsbet.depthchart.service.implementation.MLBDepthChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepthChartFactory {

    public final NFLDepthChartService NFLDepthChartService;
    public final MLBDepthChartService MLBDepthChartService;

    @Autowired
    public DepthChartFactory(NFLDepthChartService nflDepthChartService,MLBDepthChartService mlbDepthChartService) {
        NFLDepthChartService = nflDepthChartService;
        MLBDepthChartService = mlbDepthChartService;
    }


    public DepthChartService getGameType(String gameType){
        if(gameType == null){
            return null;
        }
        if(gameType.equalsIgnoreCase("NFL")) {
            return NFLDepthChartService;
        }
        else if(gameType.equalsIgnoreCase("MLB")){
            return MLBDepthChartService;
        }
        return null;
    }
}


package com.sportsbet.depthchart.service;

import com.sportsbet.depthchart.model.request.Player;
import com.sportsbet.depthchart.model.request.PlayersUnderPlayerRequest;
import com.sportsbet.depthchart.model.response.DepthChartResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public abstract class DepthChartService {


   protected Map<String, List<Stack<Player>>> depthFullChart = new HashMap<>();
   protected Map<String, List<Long>> playersUnderPlayer = new HashMap<>();


   public abstract ResponseEntity<DepthChartResponse> addRemovePlayerFromDepthChart(final Player player, final String position, final Integer positionDepth, final Boolean add);

   public List<Stack<Player>> addPlayer(final List<Stack<Player>> positionType, final Integer positionDepth, final Player player) {

      Integer positionDepthValue = positionDepth;
      System.out.println("initial postionType values : "+ positionType);
      if (positionType.size() == 0) {
         if(positionDepthValue == null) {
            positionDepthValue = 0;
         }
         for (int i = 0; i <= positionDepthValue; i++) {
            Stack<Player> wr = new Stack<>();
            if (i == positionDepthValue) {
               wr.push(player);
            }
            positionType.add(wr);
         }
      } else if (positionDepthValue != null && !(positionDepthValue < positionType.size())) {
         for (int i = positionType.size(); i <= positionDepthValue; i++) {
            Stack<Player> wr = new Stack<>();
            if (i == positionDepthValue) {
               wr.push(player);
            }
            positionType.add(wr);
         }
      } else {
         if(positionDepthValue == null){
            positionDepthValue = positionType.size()-1;
         }
         Stack<Player> existingWr = positionType.get(positionDepthValue);
         existingWr.push(player);
      }
      System.out.println("after adding postionType values : "+ positionType);
      return positionType;

   }

   public List<Stack<Player>> removePlayer(final List<Stack<Player>> positionType, final Player player) {

      if(positionType.size()>0) {
         for (int i = 0; i < positionType.size(); i++) {
            Stack<Player> temp = positionType.get(i);
            Optional<Player> plt = temp.stream().filter(p -> p.getPlayerId() == player.getPlayerId() && p.getName().equals(player.getName())).findAny();
            if (plt.isPresent()) {
               System.out.println(plt.get());
               temp.remove(plt.get());
               System.out.println("After removing temp: " + temp);
            }
         }

         System.out.println("After removing response: " + positionType);
         return positionType;
      }
      System.out.println("There are no players in the given position to delete. So, no player is deleted");
      return positionType;
   }

   public ResponseEntity<String> getFullDepthChart() {
      String response = "";
      if(depthFullChart.size() > 0) {
         Iterator entries = depthFullChart.entrySet().iterator();
         while (entries.hasNext()) {
            List<Long> playerId = new ArrayList<>();
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            List<Stack<Player>> value = (List<Stack<Player>>) entry.getValue();
            for (Stack<Player> temp : value) {
               for (int i = temp.size() - 1; i >= 0; i--) {
                  playerId.add(temp.get(i).getPlayerId());
               }
            }
            playersUnderPlayer.put(key, playerId);
            System.out.println("responseMap: " + playersUnderPlayer);
            response = response + "\n" + key + ": " + playerId;
            System.out.println(response);
         }
         return ResponseEntity.status(HttpStatus.OK)
                 .body(response);
      }
      return ResponseEntity.status(HttpStatus.OK)
              .body("No Players are added to the given Game DepthChart");
   }

   public ResponseEntity<String> getPlayersUnderPlayerInDepthChart(final PlayersUnderPlayerRequest request) {

      Long playerId = null;
      String position = request.getPosition().toUpperCase();
      List<Stack<Player>> value = depthFullChart.get(position);
      if(value != null) {
         for (Stack<Player> sp : value) {
            Optional<Player> player = sp.stream().filter(p -> p.getName().equals(request.getName())).findAny();
            if (player.isPresent()) {
               System.out.println(player.get());
               playerId = player.get().getPlayerId();
            }
         }
      }
      if(playerId != null) {
         getFullDepthChart();
         List<Long> playersList = playersUnderPlayer.get(position);
         List<Long> playersUnderPlayerList = playersList.subList(playersList.indexOf(playerId)+1,playersList.size());
         System.out.println("playersUnderPlayerList: "+ playersUnderPlayerList);
         return ResponseEntity.status(HttpStatus.OK)
                 .body(playersUnderPlayerList.toString());
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body("Provide valid player name");
   }

   protected ResponseEntity<DepthChartResponse> formResponsePayloadForZeroSizePositions() {
      DepthChartResponse response = DepthChartResponse.builder()
              .code(200)
              .message("There are no players in the given position to delete. So, no player is deleted")
              .build();

      return ResponseEntity.status(HttpStatus.OK)
              .body(response);
   }



}

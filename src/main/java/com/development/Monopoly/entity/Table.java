package com.development.Monopoly.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.development.Monopoly.Utils.EstateColor;
import com.development.Monopoly.Utils.GameState;
import com.development.Monopoly.Utils.PlayerStatus;
import com.development.Monopoly.controller.TableController;
import com.development.Monopoly.entity.card.ChanceCard;
import com.development.Monopoly.entity.card.CommunityChestCard;
import com.development.Monopoly.entity.space.*;
import com.development.Monopoly.exception.DuplicateColorException;
import com.development.Monopoly.exception.DuplicateNameException;
import com.development.Monopoly.exception.GameFullException;
import com.development.Monopoly.exception.PlayerNotFoundException;
import com.development.Monopoly.exception.UnExpectedErrorException;


// This class stands for a game table
public class Table {

    private  int id;

    private String name;

    private String password;

    private int hostId;

    private GameState state;

    private List<Player> playerList;

    private int playerInTurn;

    private Event event;

    private Dice dice;

    private List<Space> spaces;

    private Queue<ChanceCard> listChances;

    private Queue<CommunityChestCard> listChests;

    // constructor
    private Table(int id, String name, String password){
        this.id = id;
        playerList = new ArrayList<>();
        this.name = name;
        this.password = password;
        hostId = 0;
        state = GameState.NOT_STARTED;
        playerInTurn = 0;
        this.event = new Event();
        event.setGamePlayMessage("Sẵn sàng và chờ chủ phòng bắt đầu.");
        this.dice = new Dice();

        spaces = new ArrayList<>();
        
        spaces.add(new Start(0, "Start"));
        spaces.add(new Estate(1, "Nguyễn Huệ", EstateColor.RED, 60, 50 ));
        spaces.add(new CommunityChest(2, "Khí vận"));
        spaces.add(new Estate(3, "Lê Lợi", EstateColor.RED, 60, 50));
        spaces.add(new Tax(4, "Nộp thuế"));
        spaces.add(new BusStation(5, "Bến xe Cần Giuộc", 200));
        spaces.add(new Estate(6, "Lương Định Của", EstateColor.GRAY, 100, 50));
        spaces.add(new Chance(7, "Cơ hội"));
        spaces.add(new Estate(8, "Võ Thị Sáu", EstateColor.GRAY, 100, 50));
        spaces.add(new Estate(9, "Hai Bà Trưng", EstateColor.GRAY, 120, 50));
        spaces.add(new Jail(10, "Nhà tù"));
        spaces.add(new Estate(11, "Nguyễn tất thành", EstateColor.GREEN, 140, 100));
        spaces.add(new Company(12, "Công ty điện lực", 200));
        spaces.add(new Estate(13, "Nguyễn Trãi", EstateColor.GREEN, 140, 100));
        spaces.add(new Estate(14, "An Dương Vương", EstateColor.GREEN, 140, 100));
        spaces.add(new BusStation(15, "Bến xe miền Tây", 200));
        spaces.add(new Estate(16, "Hậu Giang", EstateColor.BLACK, 160, 100));
        spaces.add(new CommunityChest(17, "Khí vận"));
        spaces.add(new Estate(18, "Hùng Vương", EstateColor.BLACK, 180, 100));
        spaces.add(new Estate(19, "Huỳnh Tấn Phát", EstateColor.BLACK, 200, 100));
        spaces.add(new CarPark(20, "Bãi đậu xe"));
        spaces.add(new Estate(21, "Phạm Thế Hiển", EstateColor.YELLOW, 220, 150));
        spaces.add(new Chance(22, "Cơ hội"));
        spaces.add(new Estate(23, "Kha Vạn Cân", EstateColor.YELLOW, 220, 150));
        spaces.add(new Estate(24, "Nguyễn Tri Phương", EstateColor.YELLOW, 240, 150));
        spaces.add(new BusStation(25, "Bến xe chợ lớn", 200));
        spaces.add(new Estate(26, "Lê Đại Hành", EstateColor.PURPLE, 280, 150));
        spaces.add(new Estate(27, "Trường Chinh", EstateColor.PURPLE, 280, 150));
        spaces.add(new Company(28, "Công ty cấp nước", 200));
        spaces.add(new Estate(29, "Hoàng Văn Thụ", EstateColor.PURPLE, 280, 150));
        spaces.add(new GoToJail(30, "Vào tù"));
        spaces.add(new Estate(31, "Cộng Hòa", EstateColor.LIGHTBLUE, 300, 200));
        spaces.add(new Estate(32, "Nguyễn Kiệm", EstateColor.LIGHTBLUE, 300, 200));
        spaces.add(new CommunityChest(33, "Khí Vận"));
        spaces.add(new Estate(34, "Quang Trung", EstateColor.LIGHTBLUE, 320, 200));
        spaces.add(new BusStation(35, "Bến xe miền Đông", 200));
        spaces.add(new Chance(36, "Cơ hội"));
        spaces.add(new Estate(37, "Lũy Bán Bích", EstateColor.DARKBLUE, 350, 200));
        spaces.add(new SpecialTax(38, "Thuế đặc biệt"));
        spaces.add(new Estate(39, "Tân Kỳ Tân Quý", EstateColor.DARKBLUE, 350, 200));
    
        listChances = new LinkedList<>();
        listChances.add(new ChanceCard(0, "NGHỈ HƯU", "Đi qua ô bắt đầu, lãnh lương 200"));
        listChances.add(new ChanceCard(1, "ĐI ĐỂ TRỞ VỀ", "Bạn được quyền tự do ra tù (có thể giữ lại)"));
        listChances.add(new ChanceCard(2, "PHI LÔI THẦN THUẬT", "Lắc 2 xúc xắc và di chuyển bằng tích của 2 xúc xắc"));
        listChances.add(new ChanceCard(3, "RÚT THĂM TRÚNG THƯỞNG", "Rút thêm 1 lá khí vận hoặc cơ hội nữa"));
        listChances.add(new ChanceCard(4, "PHIẾU GIẢM GIÁ", "Giảm 50% cho một lần trả tiền bất kỳ (có thế giữ lại)"));
        listChances.add(new ChanceCard(5, "KHÔNG CHÙN BƯỚC", "Bạn được lắc thêm 1 lần nữa"));
        listChances.add(new ChanceCard(6, "LƯU LẠC", "Đến ô Nguyễn Tri Phương lãnh 50"));
        listChances.add(new ChanceCard(7, "EM BÉ", "Thu mỗi người 50 vì là em bé cute"));

        listChests = new LinkedList<>();
        listChests.add(new CommunityChestCard(0, "TÀU CAO TỐC", "Cứ trả 50 đồng sẽ được tiến 1 ô"));
        listChests.add(new CommunityChestCard(1, "ĐÁNH THUẾ", "Chọn 1 người và đánh thuế họ 20%"));
        listChests.add(new CommunityChestCard(2, "ĐỪNG THẤY HOA NỞ MÀ NGỠ XUÂN VỀ", "Bất động sản của bạn bị đóng băng cho đến khi bạn đi qua ô bắt đầu"));
        listChests.add(new CommunityChestCard(3, "TRẢ TIỀN SỬA NHÀ", "Trả 50/nhà và 125/khách sạn bạn sở hữu"));
        listChests.add(new CommunityChestCard(4, "CƯỚP CẠN", "Ngẫu nhiên chọn một tờ tiền từ một người chơi bất kỳ"));
        listChests.add(new CommunityChestCard(5, "MONG ƯỚC KỶ NIỆM XƯA", "Lắc xúc xắc và đi lùi lại bằng số lắc được"));
        listChests.add(new CommunityChestCard(6, "TÂN GIA", "Đặt thêm 1 nhà ở ô đất bất kỳ do mình sở hữu (Không được đặt khách sạn)"));
        listChests.add(new CommunityChestCard(7, "VỀ QUÊ CẮM CÂU", "Đến ô bến xe gần nhất. Trả gấp đôi tiền thuê`"));
    };

    public Player findPlayerById(int id){
        for (Player player : playerList) {
            if(player.getId() == id){
                return player;
            }
        }
        throw new PlayerNotFoundException();
    }

    public ChanceCard drawChanceCard(){
        return listChances.remove();
    }

    public void returnChanceCard(ChanceCard card){
        listChances.add(card);
    }

    public CommunityChestCard drawChestCard(){
        return listChests.remove();
    }

    public void returnChestCard(CommunityChestCard card){
        listChests.add(card);
    }

    public Space findSpaceById(int id){
        for (Space space : spaces) {
            if(space.getId() == id) return space;
        }
        return null;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int gethostId() {
        return hostId;
    }

    public void sethostId(int hostId) {
        this.hostId = hostId;
    }


    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public int getPlayerInTurn(){
        return playerInTurn;
    }

    public Player PlayerOnTurn() {
        return playerList.get(playerInTurn);
    }

    public void setPlayerInTurn(int playerInTurn) {
        this.playerInTurn = playerInTurn;
    }

    public List<Space> listSpaces() {
        return spaces;
    }

    public Space getSpace(int index){
        return spaces.get(index);
    }

    public Player addPlayer(Player newPlayer){
        if(playerList.size() == 4){
            throw new GameFullException();
        }

        for (Player player : playerList) {
            if (player.getName().equals(newPlayer.getName())) {
                throw new DuplicateNameException();
            }
            if(player.getTokenColor().equals(newPlayer.getTokenColor())){
                throw new DuplicateColorException();
            }
        }

        if(this.state == GameState.STARTED){
            throw new UnExpectedErrorException("Trò chơi đang diễn ra.");
        }

        if(playerList.size() == 0){
            hostId = newPlayer.getId();
            newPlayer.setHost(true);
        }

        playerList.add(newPlayer);
        newPlayer.setRollAble(1);
        return newPlayer;
    }

    public int countPlayer(){
        return playerList.size();
    }

    public Player kickPlayer(int playerId) {

        Player player = findPlayerById(playerId);

        if (playerList.remove(player) == false) {
            throw new UnExpectedErrorException(2);
        }

        if(playerList.size() == 0){
            TableController.deleteTable(this.id);
        } else {
            Player newHost = playerList.get(0);
            newHost.setHost(true);
        }

        return player;
    }

    public void startGame() {
        for (Player player : playerList) {
            if(player.getStatus() != PlayerStatus.READY){
                throw new UnExpectedErrorException("Người chơi chưa sẵn sàng.");
            }
        }
        this.state = GameState.STARTED;
        event.setGamePlayMessage("Trò chơi đã được bắt đầu.");
        Player player = playerList.get(0);
        String message = "Đã đến lượt chơi của ---> " + player.getName() + " <--- mời lắc xúc xắc!";
        event.setEventMessage(message); 
    }

    public Dice rollDice(int playerId) {
        Player player = playerList.get(playerInTurn);
        if(state == GameState.NOT_STARTED){
            throw new UnExpectedErrorException("Trò chơi chưa bắt đầu.");
        }
        if(playerId != player.getId()){
            throw new UnExpectedErrorException("Chưa tới lượt của bạn");
        }

        if(player.getRollAble() == 0){
            throw new UnExpectedErrorException("Lắc rồi không được lắc nữa nghen.");
        } else{
            player.setRollAble(player.getRollAble() - 1);
        }

        dice.roll();
        player.setStepToGo(dice.getSum());
        String message = "---> " + player.getName() + " <--- đã lắc xúc xắc được " + dice.getSum() + " nút, mời bạn đi ạ :))";
        event.setEventMessage(message);
        return dice;
    }

    public Player playerInTurnId(){
        return playerList.get(playerInTurn);
    }

    public void nextPlayer(int playerId) {
        Player currentPlayer = playerList.get(playerInTurn);
        String message = "---> " + currentPlayer.getName() + " <--- đã đi xong.";
        if(currentPlayer.getId() != playerId){
            throw new UnExpectedErrorException("Chưa có tới lượt mà xong gì ba?");
        }

        if(currentPlayer.getRollAble() > 0){
            message +=  " Nhưng còn "+currentPlayer.getRollAble()+" lượt lắc xúc xắc nữa, mời bạn lắc tiếp.";
            event.setEventMessage(message);    
        } else{
            // chuyen sang nguoi choi ke tiep
            playerInTurn = (playerInTurn + 1) % playerList.size();
            Player nextPlayer = playerList.get(playerInTurn);
            
            while (nextPlayer.getStatus() != PlayerStatus.READY) {
                // neu nguoi ke tiep da chiu thua
                if(nextPlayer.getStatus() == PlayerStatus.LOOSED) {
                    playerInTurn = (playerInTurn + 1) % playerList.size();
                    nextPlayer = playerList.get(playerInTurn);
                }
                // neu nguoi ke tiep dang trong ben xe
                if(nextPlayer.getStatus() == PlayerStatus.PARKING){
                    message += " Đến lượt " + nextPlayer.getName() + " nhưng bị mất một lượt.";
                    playerInTurn = (playerInTurn + 1) % playerList.size();
                    nextPlayer.setStatus(PlayerStatus.READY);
                    nextPlayer = playerList.get(playerInTurn);
                }
                // neu nguoi ke tiep dang trong tu 
                if(nextPlayer.getStatus() == PlayerStatus.INJAIL_1 
                   || nextPlayer.getStatus() == PlayerStatus.INJAIL_2
                   || nextPlayer.getStatus() == PlayerStatus.INJAIL_3
                   || nextPlayer.getStatus() == PlayerStatus.INJAIL_4
                   || nextPlayer.getStatus() == PlayerStatus.INJAIL)
                {
                    break;
                }
            }
            nextPlayer.setRollAble(1);
            message +=  " Tới lượt ---> " + nextPlayer.getName() + " <--- đi kìa!!!";
            event.setEventMessage(message);
        }

    }
    
    public void checkWinner(Event event) {
        //int countReady = 0;
        int countLoosed = 0;
        for (Player player : playerList) {
            //if (player.getStatus() == PlayerStatus.READY) countReady++;
            if (player.getStatus() == PlayerStatus.LOOSED) countLoosed++;
        }
        if (countLoosed == playerList.size() - 1) {
            event.setEventMessage("Người chơi --->" + this.getName() + "<--- đã thắng. Xin chúc mừng");
            return;
        }
        for (Player player : playerList) {
            int countProperty = 0;
            for (Property property : player.getOwnedProperty()) {
                if(property instanceof BusStation || property instanceof Company) countProperty++;
            }
            if (countProperty == 6) {
                event.setEventMessage("Người chơi --->" + this.getName() + "<--- đã thắng vì đã sở hữu 4 bến xe và 2 công ty. Xin chúc mừng");
                return;
            }
            int count = player.hasFullEstateOfColor(EstateColor.RED) + player.hasFullEstateOfColor(EstateColor.GREEN) + player.hasFullEstateOfColor(EstateColor.BLACK) + player.hasFullEstateOfColor(EstateColor.YELLOW) + player.hasFullEstateOfColor(EstateColor.PURPLE) + player.hasFullEstateOfColor(EstateColor.LIGHTBLUE) + player.hasFullEstateOfColor(EstateColor.DARKBLUE);
            if (count == 4) {
                event.setEventMessage("Người chơi --->" + this.getName() + "<--- đã thắng vì sở hữu tất cả các ô đất của 4 loại màu. Xin chúc mừng");
                return;
            }
        }
    }

    public boolean isPlayerInTurn(int playerId) {
        return playerList.get(this.playerInTurn).getId() == playerId;
    }
}
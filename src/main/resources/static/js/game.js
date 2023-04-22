window.onload = function (){


    /**
     * Truong
     */
    const openCreatePlayerModalButton = document.getElementById("create-player-modal-button");
    const closeCreatePlayerModalButton = document.getElementById("close-create-player-modal-button");
    const createPlayerButton = document.getElementById("create-player-button");
    const ready_button = document.getElementById("ready-button");
    const quit_button = document.getElementById("quit-button");
    const tableName = document.getElementById("table-name");
    const tableNumber = document.getElementById("table-id");
    const password = document.getElementById("table-password");

    if(localStorage.getItem("player-name") == null){
        openCreatePlayerModalButton.click();
    } else {
        syncronize();
    }

    ready_button.addEventListener("click", ready);
    quit_button.addEventListener("click", quit);
    createPlayerButton.addEventListener("click", createPlayer);

    tableName.innerText = localStorage.getItem("table_name");
    tableNumber.innerText = localStorage.getItem("table_id");
    password.innerText = localStorage.getItem("table_password");

    /**
     * Khoa
     */
    createSpaceInfo();

    //const estateid = [1,3,6,8,9,11,13,14,16,18,19,21,23,24,26,27,29,31,32,34,37,39];
    // const estateid_row1 = [1,3,6,8,9];
    // const estateid_row2 = [11,13,14,16,18,19];
    // const estateid_row3 = [21,23,24,26,27,29];
    // const estateid_row4 = [31,32,34,37,39];
    // const bus_station_id = [5, 15, 25, 35];
    // const company_id = [12];

    // for (let index = 0; index < 40; index++) {
    //     const button = document.getElementsByClassName('square-'+index)[0];
    //     if (estateid_row1.includes(index)) {
    //         button.addEventListener('click', function(){
    //             const content = document.getElementById('content');
    //             content.innerHTML = 'Chủ nhà: <br>Giá mua 1 căn nhà = $50 <br>Số lượng nhà hiện tại:';
    //         })
    //     } else if(estateid_row2.includes(index)){
    //         button.addEventListener('click', function(){
    //             const content = document.getElementById('content');
    //             content.innerHTML = 'Chủ nhà: <br>Giá mua 1 căn nhà = $100 <br>Số lượng nhà hiện tại:';
    //         })
    //     } else if(estateid_row3.includes(index)){
    //         button.addEventListener('click', function(){
    //             const content = document.getElementById('content');
    //             content.innerHTML = 'Chủ nhà: <br>Giá mua 1 căn nhà = $150 <br>Số lượng nhà hiện tại:';
    //         })
    //     } else if(estateid_row4.includes(index)){
    //         button.addEventListener('click', function(){
    //             const content = document.getElementById('content');
    //             content.innerHTML = 'Chủ nhà: <br>Giá mua 1 căn nhà = $200 <br>Số lượng nhà hiện tại:';
    //         })
    //     } else if(bus_station_id.includes(index)){
    //         button.addEventListener('click', function(){
    //             const content = document.getElementById('content');
    //             content.innerHTML = 'Chủ bến xe:';
    //         })
    //     } else if(company_id.includes(index)){
    //         button.addEventListener('click', function(){
    //             const content = document.getElementById('content');
    //             content.innerHTML = 'Chủ công ty: ';
    //         })
    //     } /*else {
    //         button.addEventListener('click', function(){
    //             const content = document.getElementById('content');
    //             content.innerHTML = 'khong phai dat';
    //         })
    //     }*/
    // }
}

//truong
function createPlayer() {

    localStorage.removeItem("player-id");
    localStorage.removeItem("player-name");
    localStorage.removeItem("player-tokenColor");

    const name = document.getElementById("player-name-input").value;
    const tokenColor = document.getElementById("token-color-input").value;
    const toSend = {
        id: 0,
        name: name,
        tokenColor: tokenColor,
        currentSquareId: localStorage.getItem("table_id"),
        position: 0,
        status: "NOT_READY",
        money: 2000
    };

    const jsonString = JSON.stringify(toSend);
    const ajax = new XMLHttpRequest();

    ajax.onload = function () {

        if(this.status != 200){
            alert(this.responseText);
        } else {
            const returnJson = JSON.parse(this.responseText);
            localStorage.setItem("player-id", returnJson.id);
            localStorage.setItem("player-name", returnJson.name);
            localStorage.setItem("player-token-color", returnJson.tokenColor);
            localStorage.setItem("player-position", returnJson.position);
            localStorage.setItem("player-status", returnJson.status);
            localStorage.setItem("player-money", returnJson.money);
            localStorage.setItem("owner", returnJson.host);
            alert("Saved!");
            const closeCreatePlayerModalButton = document.getElementById("close-create-player-modal-button");
            closeCreatePlayerModalButton.click();
            syncronize();
        }

    }

    ajax.open("POST", "/tables/"+localStorage.getItem("table_id")+"/add-player", false);
    ajax.setRequestHeader('Content-Type', 'application/json');
    ajax.send(jsonString);
}

// truong
function sleep(delay){
    return new Promise (resolve => setTimeout(resolve, delay));
}

// truong
async function syncronize(){
    console.log("syncronizing");
    await sleep(1000);
    const ajax = new XMLHttpRequest();

    ajax.onload = function (){
        if (this.status != 200) {
            localStorage.clear();
            alert(this.responseText);
            window.location.href = "/";
        } else {
            const returnJson = JSON.parse(this.responseText);
            checkStatus(returnJson.playerList, returnJson.state);
            renderPlayer(returnJson.playerList);
            renderEvent(returnJson.event, returnJson.state);
            setDice(returnJson.dice);
        }
    }

    ajax.open("GET", "/tables/"+localStorage.getItem("table_id")+"/syncronize", false);
    ajax.setRequestHeader('Content-type', 'application/json');
    ajax.send();

    requestAnimationFrame(syncronize);
}

// truong
function renderPlayer(players){
    clearScreen();
    // console.log("rendering players");
    players.forEach(player => {
        const color = player.tokenColor;
        const position = player.position;
        const status = player.status;
        const name = player.name;
        const money = player.money;
        const id = player.id;
        if(status == "IN_JAIL"){
            const player_container = document.getElementById("jail-container");
            const player_token = '<div class="player ' + color + '-player"></div>';
            if(!player_container.innerHTML.includes(player_token)){
                player_container.innerHTML += player_token;
            }
        } else {
            const player_container = document.getElementById("player-container-" + position);
            const player_token = '<div class="player ' + color + '-player"></div>';
            if(player_container.innerHTML.includes(player_token) == false){
                player_container.innerHTML += player_token;
            } 
        }
        const playerList = document.getElementById('playerList');
        if (localStorage.getItem("owner") == "true") {
            const player_bar = '<div class="d-flex align-items-center justify-content-between m-2 p-2 border rounded border-primary"> <div class="player '+ color +'-player"></div><p class="m-0 mx-1">'+name+'</p> <p class="bg-light m-0 px-1 rounded">$'+money+'</p> <small class="small-text bg-light m-0 p-2 rounded">'+status+'</small> <button onclick="kick('+id+')">Kick</button>  </div>';
            playerList.innerHTML += player_bar;
        } else {
            const player_bar = '<div class="d-flex align-items-center justify-content-between m-2 p-2 border rounded border-primary"> <div class="player '+ color +'-player"></div><p class="m-0 mx-1">'+name+'</p> <p class="bg-light m-0 px-1 rounded">$'+money+'</p> <small class="small-text bg-light m-0 p-2 rounded">'+status+'</small>  </div>';
            playerList.innerHTML += player_bar;
        }
    });
}

// truong
function clearScreen() {
    
    var player_container = document.getElementsByClassName('player-container');
    for (let i = 0; i < player_container.length; i++) {
        const container = player_container[i];
        container.innerHTML = "";
    }
    const playerList = document.getElementById('playerList');
    playerList.innerHTML = "";
}

// truong
function checkStatus(players, state){
    if(isNotKicked(players, state) == false){
        localStorage.clear();
        alert("Bạn bị đuổi khỏi phòng.");
        window.location.href = "/";
    }

}

// truong
function isNotKicked(players, state){
    var result = false;
    players.forEach(player => {
        if (player.id === parseInt(localStorage.getItem("player-id"))) {
            result = true;
        }
        
        // check host
        if(player.host == true && player.id == parseInt(localStorage.getItem("player-id")) && state == "NOT_STARTED") {
            localStorage.setItem("owner", true);
            const btn_group = document.getElementById("btn-group");
            const start_btn = '<button class="w-50 btn-success" id="start-button" onclick="startGame()">Start</button>';
            if(btn_group.innerHTML.includes(start_btn) == false){
                btn_group.innerHTML = start_btn + btn_group.innerHTML;
            }
        }
    });
    return result;
}

// kick
function kick(id) {
    if (id == localStorage.getItem("player-id")) {
        alert("Bạn không thể đuổi chính mình!");
        return;
    }

    ajax = new XMLHttpRequest();

    ajax.onload = function(){
        
    }

    ajax.open("DELETE", "/tables/"+localStorage.getItem("table_id")+"/kick/"+id, false);
    ajax.setRequestHeader('Content-Type', 'application/json');
    ajax.send();
}

function ready(){
    console.log("Sdfsdf");
    const ajax = new XMLHttpRequest();
    ajax.onload = function(){
        if(this.status != 200){
            localStorage.clear();
            alert(this.responseText);
            window.location.href = "/";
        } else{
            const ready_button = document.getElementById("ready-button");
            ready_button.style.display = "none";
        }
    }

    const table_id = localStorage.getItem("table_id");
    const player_id = localStorage.getItem("player-id");
    const uri = "/tables/" + table_id + "/" + player_id + "/ready";
    ajax.open("GET", uri, false);
    ajax.setRequestHeader('Content-type', 'application/json');
    ajax.send();
}

function quit(){
    const ajax = new XMLHttpRequest();
    ajax.onload = function(){
        if(this.status != 200){
            localStorage.clear();
            alert(this.responseText);
            window.location.href = "/";
        } else{
            localStorage.clear();
            window.location.href = "/";
        }
    }

    const table_id = localStorage.getItem("table_id");
    const player_id = localStorage.getItem("player-id");
    const uri = "/tables/" + table_id + "/" + player_id + "/quit";
    ajax.open("GET", uri, false);
    ajax.setRequestHeader('Content-type', 'application/json');
    ajax.send();
}

function startGame(){
    const ajax = new XMLHttpRequest();
    ajax.onload = function(){
        if(this.status != 200){
            alert(this.responseText);
        } else{
            const btn_group = document.getElementById("btn-group");
            const start_btn = '<button class="w-50 btn-success" id="start-button" onclick="startGame()">Start</button>';
            if(btn_group.innerHTML.includes(start_btn)){
                btn_group.innerHTML = "";
            }
        }
    }

    const table_id = localStorage.getItem("table_id");
    const player_id = localStorage.getItem("player-id");
    const uri = "/tables/" + table_id + "/start";
    ajax.open("GET", uri, false);
    ajax.setRequestHeader('Content-type', 'application/json');
    ajax.send();
}

// truong
function renderEvent(event, gameState){
    const gamePlayeMessage = document.getElementById("game-play-message");
    gamePlayeMessage.innerText = event.gamePlayMessage;
    
    const eventMessageContainer = document.getElementById("event-message");
    const lastMessage = eventMessageContainer.firstChild.innerText;

    if(lastMessage != event.eventMessage){
        eventMessageContainer.innerHTML = "<p>" + event.eventMessage + "</p>" + eventMessageContainer.innerHTML;
    }

}

// truong
function rollDice() {
    const ajax = new XMLHttpRequest();
    ajax.onload = function () {
        if(this.status != 200){
            alert(this.responseText);
        } else {
            const returnJson = JSON.parse(this.responseText);
            const dice1 = document.getElementById("dice-1");
            const dice2 = document.getElementById("dice-2");
            dice1.innerText = returnJson.dice1;
            dice2.innerText = returnJson.dice2;
        }
    }
    const uri = "/tables/" + localStorage.getItem("table_id") + "/" + localStorage.getItem("player-id") + "/roll-dice";
    ajax.open("GET", uri, false);
    ajax.setRequestHeader('Content-type', 'application/json');
    ajax.send();
}

// truong 
function setDice(dice){
    const dice1 = document.getElementById("dice-1");
    const dice2 = document.getElementById("dice-2");
    dice1.innerText = dice.dice1;
    dice2.innerText = dice.dice2;
}

// truong
function go(){
    const ajax = new XMLHttpRequest();
    ajax.onload = function () {
        if(this.status != 200){
            alert(this.responseText);
        } else {
            
        }
    }
    const uri = "/tables/" + localStorage.getItem("table_id") + "/" + localStorage.getItem("player-id") + "/go";
    ajax.open("GET", uri, false);
    ajax.setRequestHeader('Content-type', 'application/json');
    ajax.send();
}

function buyEstate(){
    const ajax = new XMLHttpRequest();
    ajax.onload = function () {
        if(this.status != 200){
            alert(this.responseText);
        } else {
            alert("Mua thành công");
        }
    }
    const uri = "/tables/" + localStorage.getItem("table_id") + "/" + localStorage.getItem("player-id") + "/buy-estate";
    ajax.open("GET", uri, false);
    ajax.setRequestHeader('Content-type', 'application/json');
    ajax.send();
}

function done(){
    const ajax = new XMLHttpRequest();
    ajax.onload = function () {
        if(this.status != 200){
            alert(this.responseText);
        } else {
            
        }
    }
    const uri = "/tables/" + localStorage.getItem("table_id") + "/" + localStorage.getItem("player-id") + "/done";
    ajax.open("GET", uri, false);
    ajax.setRequestHeader('Content-type', 'application/json');
    ajax.send();
}
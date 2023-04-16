window.onload = function (){


    /**
     * Truong
     */
    const openCreatePlayerModalButton = document.getElementById("create-player-modal-button");
    const closeCreatePlayerModalButton = document.getElementById("close-create-player-modal-button");
    const createPlayerButton = document.getElementById("create-player-button");
    
    if(localStorage.getItem("player-name") == null){
        openCreatePlayerModalButton.click();
    } else {
        syncronize();
    }

    createPlayerButton.addEventListener('click', createPlayer);


    /**
     * Khoa
     */
    //const estateid = [1,3,6,8,9,11,13,14,16,18,19,21,23,24,26,27,29,31,32,34,37,39];
    const estateid_row1 = [1,3,6,8,9];
    const estateid_row2 = [11,13,14,16,18,19];
    const estateid_row3 = [21,23,24,26,27,29];
    const estateid_row4 = [31,32,34,37,39];
    const bus_station_id = [5, 15, 25, 35];
    const company_id = [12];

    for (let index = 0; index < 40; index++) {
        const button = document.getElementsByClassName('square-'+index)[0];
        if (estateid_row1.includes(index)) {
            button.addEventListener('click', function(){
                const content = document.getElementById('content');
                content.innerHTML = 'Chủ nhà: <br>Giá mua 1 căn nhà = $50 <br>Số lượng nhà hiện tại:';
            })
        } else if(estateid_row2.includes(index)){
            button.addEventListener('click', function(){
                const content = document.getElementById('content');
                content.innerHTML = 'Chủ nhà: <br>Giá mua 1 căn nhà = $100 <br>Số lượng nhà hiện tại:';
            })
        } else if(estateid_row3.includes(index)){
            button.addEventListener('click', function(){
                const content = document.getElementById('content');
                content.innerHTML = 'Chủ nhà: <br>Giá mua 1 căn nhà = $150 <br>Số lượng nhà hiện tại:';
            })
        } else if(estateid_row4.includes(index)){
            button.addEventListener('click', function(){
                const content = document.getElementById('content');
                content.innerHTML = 'Chủ nhà: <br>Giá mua 1 căn nhà = $200 <br>Số lượng nhà hiện tại:';
            })
        } else if(bus_station_id.includes(index)){
            button.addEventListener('click', function(){
                const content = document.getElementById('content');
                content.innerHTML = 'Chủ bến xe:';
            })
        } else if(company_id.includes(index)){
            button.addEventListener('click', function(){
                const content = document.getElementById('content');
                content.innerHTML = 'Chủ công ty: ';
            })
        } /*else {
            button.addEventListener('click', function(){
                const content = document.getElementById('content');
                content.innerHTML = 'khong phai dat';
            })
        }*/
    }
}

//truong
function createPlayer() {

    localStorage.removeItem("player-id");
    localStorage.removeItem("player-name");
    localStorage.removeItem("player-tokenColor");

    const name = document.getElementById("player-name-input").value;
    const tokenColor = document.getElementById("token-color-input").value;
    const toSend = {
        name: name,
        tokenColor: tokenColor,
        currentSquareId: localStorage.getItem("table_id")
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
            alert("Saved!");
            const closeCreatePlayerModalButton = document.getElementById("close-create-player-modal-button");
            closeCreatePlayerModalButton.click();
            syncronize();
        }

    }

    ajax.open("POST", "/squares/"+localStorage.getItem("table_id")+"/add-player", false);
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
            alert(this.responseText);
        } else {
            const returnJson = JSON.parse(this.responseText);
            checkStatus(returnJson);
            renderPlayer(returnJson);
        }
    }

    ajax.open("GET", "/squares/"+localStorage.getItem("table_id")+"/get-players", false);
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
                console.log("rendering position " + position);
                player_container.innerHTML += player_token;
            } 
        }
        const playerList = document.getElementById('userList');
        if (localStorage.getItem("owner")) {
            const player_bar = '<div class="d-flex align-items-center justify-content-between m-2 p-2 border rounded border-primary"> <div class="player '+ color +'-player"></div><p class="m-0 mx-1">'+name+'</p> <p class="bg-light m-0 px-1 rounded">$'+money+'</p> <button onclick="kick('+id+')">Kick</button>  </div>';
            playerList.innerHTML += player_bar;
        } else {
            const player_bar = '<div class="d-flex align-items-center justify-content-between m-2 p-2 border rounded border-primary"> <div class="player '+ color +'-player"></div><p class="m-0 mx-1">'+name+'</p> <p class="bg-light m-0 px-1 rounded">$'+money+'</p>  </div>';
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
    const playerList = document.getElementById('userList');
    playerList.innerHTML = "";
}

// truong
function checkStatus(players){
    if(isNotKicked(players) == false){
        alert("Bạn bị đuổi khỏi phòng.");
        localStorage.clear();
        window.location.href = "/";
    }
}

// truong
function isNotKicked(players){
    players.forEach(player => {
        if (player.id == localStorage.getItem("player-id")) {
            return true;
        }
    });
    return false;
}

// kick
function kick(id) {
    if (id == localStorage.getItem("player-id")) {
        alert("Bạn không thể dduổi chính mình!");
        return;
    }

    ajax = new XMLHttpRequest();

    ajax.onload = function(){

    }

    ajax.open("GET", "/squares/"+localStorage.getItem("table_id")+"/kick/"+id, false);
    ajax.setRequestHeader('Content-Type', 'application/json');
    ajax.send();
}
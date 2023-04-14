window.onload = function (){


    /**
     * Truong
     */
    const openCreatePlayerModalButton = document.getElementById("create-player-modal-button");
    const closeCreatePlayerModalButton = document.getElementById("close-create-player-modal-button");
    const createPlayerButton = document.getElementById("create-player-button");
    
    if(localStorage.getItem("player-name") == null){
        openCreatePlayerModalButton.click();
    }

    createPlayerButton.addEventListener('click', createPlayer);

    console.log("sdfhslkdjfhskdjfhsk");

    loadLocalPlayer();
    // syncronize();



    /**
     * Khoa
     */
    // const estateid = [1,3,6,8,9,11,13,14,16,18,19,21,23,24,26,27,29,31,32,34,37,39];
    // for (let index = 0; index < 40; index++) {
    //     const button = document.getElementsByClassName('square-'+index)[0];
    //     if (estateid.includes(index)) {
    //         button.addEventListener('click', function(){
    //             const content = document.getElementById('content');
    //             content.innerHTML = 'Chủ nhà: <br>Giá mua 1 căn nhà = $100 <br>Số lượng nhà hiện tại:';
    //         })
    //     } else {
    //         button.addEventListener('click', function(){
    //             const content = document.getElementById('content');
    //             content.innerHTML = 'khong phai dat';
    //         })
    //     }
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
        }

    }

    ajax.open("POST", "/squares/"+localStorage.getItem("table_id")+"/add-player", false);
    ajax.setRequestHeader('Content-Type', 'application/json');
    ajax.send(jsonString);
}

// truong
function loadLocalPlayer() {

    if(localStorage.getItem("player-name") == null) return;

    const toSend = {
        id: localStorage.getItem("player-id"),
        name: localStorage.getItem("player-name"),
        tokenColor: localStorage.getItem("player-token-color"),
        currentSquareId: localStorage.getItem("table_id")
    };

    const jsonString = JSON.stringify(toSend);
    const ajax = new XMLHttpRequest();

    ajax.onload = function () {

        
        if(this.status != 200){
            // alert(this.responseText);
            
            if (this.status == 404) {
                alert(this.responseText);
                window.location.href = "/";
            } else{
                console.log(this.responseText);
            }
        } else {
            const returnJson = JSON.parse(this.responseText);
            localStorage.setItem("player-id", returnJson.id);
            localStorage.setItem("player-name", returnJson.name);
            localStorage.setItem("player-token-color", returnJson.tokenColor);
        }

    }

    ajax.open("POST", "/squares/"+localStorage.getItem("table_id")+"/add-player", false);
    ajax.setRequestHeader('Content-Type', 'application/json');
    ajax.send(jsonString);
    alert("helo");
}

// truong
function syncronize(){

    const ajax = new XMLHttpRequest();

    ajax.onload = function (){
        if (this.status != 200) {
            alert(this.responseText);
        } else {
            const returnJson = JSON.parse(this.responseText);
            // renderPlayer(returnJson);
            console.log(returnJson);
        }
    }

    ajax.open("GET", "/squares/"+localStorage.getItem("table_id")+"/get-players", false);
    ajax.setRequestHeader('Content-type', 'application/json');
    ajax.send();
}

// truong
function renderPlayer(players){
    players.forEach(player => {
        const color = player.tokenColor;
        const position = player.position;
        const square = document.getElementsByClassName("square-"+position);
        square.innerHTML += '<div class="player ' + color + '-player"></div>';
    });
}
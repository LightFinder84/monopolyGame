window.onload = function () {

    save_name_button = document.getElementById("save-player-name");
    change_name_button = document.getElementById("change-player-name");
    player_name_input = document.getElementById("player-name-input");
    join_game_button = document.getElementById("join-game-button");

    if (localStorage.getItem("name") != null) {
        player_name_input.value = localStorage.getItem("name");
        save_name_button.style.display = "none";
    } else {
        change_name_button.style.display = "none";
    }


    save_name_button.addEventListener("click", createPlayer);
    change_name_button.addEventListener("click", replacePlayer);
    join_game_button.addEventListener("click", joinGame);
}

function replacePlayer() {

    const id = localStorage.getItem("id");

    localStorage.removeItem("id");
    localStorage.removeItem("name");

    const name = document.getElementById("player-name-input").value;
    const toSend = {
        name: name
    }

    jsonString = JSON.stringify(toSend);
    

    const ajax = new XMLHttpRequest();
    ajax.onload = function () {
        const responseJson = JSON.parse(this.responseText);
        localStorage.setItem("name", responseJson.name);
        localStorage.setItem("id", responseJson.id);
        alert("Name changed!")
    }
    ajax.open("PUT", "/players/" + id, false);
    ajax.setRequestHeader('Content-Type', 'application/json');
    ajax.send(jsonString);
    console.log(localStorage.getItem("id"));
    console.log(localStorage.getItem("name"));
}

function createPlayer() {

    localStorage.removeItem("id");
    localStorage.removeItem("name");

    const name = document.getElementById("player-name-input").value;
    const toSend = {
        name: name
    };

    const jsonString = JSON.stringify(toSend);
    const ajax = new XMLHttpRequest();

    ajax.onload = function () {
        const returnJson = JSON.parse(this.responseText);
        localStorage.setItem("id", returnJson.id);
        localStorage.setItem("name", returnJson.name);
        alert("Name saved!");
        save_name_button = document.getElementById("save-player-name");
        change_name_button = document.getElementById("change-player-name");
        save_name_button.style.display = "none";
        change_name_button.style.display = "block";
    }

    ajax.open("POST", "/players", false);
    ajax.setRequestHeader('Content-Type', 'application/json');
    ajax.send(jsonString);
    console.log(localStorage.getItem("id"));
    console.log(localStorage.getItem("name"));
}

function joinGame(){
    const table_id = document.getElementById("table-id").value;
    const password = document.getElementById("password").value;
    if(table_id == 0 || password == ""){
        alert("Bạn chưa nhập đủ số bàn và mật khẩu");
        return;
    }

    ajax = new XMLHttpRequest();
    const toSend = {
        id: table_id,
        password: password
    }
    ajax.onload = function(){
        if(this.status != 200){
            alert(this.responseText);
            return;
        } else {
            returnJson = JSON.parse(this.responseText);
            localStorage.setItem("table_id", returnJson.id);
            localStorage.setItem("table_password", returnJson.password);
            localStorage.setItem("table_name", returnJson.name);
            window.location.href = "/game.html";
        }
    }
    ajax.open("POST", "/join", false);
    ajax.setRequestHeader('Content-type', 'application/json');
    ajax.send(JSON.stringify(toSend));
}
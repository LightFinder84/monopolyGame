window.onload = function (){

    const createPlayerButton = document.getElementById("create-player-button");
    
    if(localStorage.getItem("player-name") == null){
        createPlayerButton.click();
    }

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
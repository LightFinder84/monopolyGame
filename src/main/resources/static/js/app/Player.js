class Player{


    constructor(id, name){
        this.id = id;
        this.name = name;
    }

    static createPlayer(name){
        
        const toSend = {
            name: name
        };

        const jsonString = JSON.stringify(toSend);
        const ajax = new XMLHttpRequest();
        
        ajax.onload = function(player){
            returnJson = JSON.parse(this.responseText);
            player.name = returnJson.name;
            player.id = returnJson.id;
        }
        
        ajax.open("POST", "/players");
        ajax.setRequestHeader('Content-Type', 'application/json');
        ajax.send(jsonString);

    }

}
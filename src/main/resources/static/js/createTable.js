window.onload = function(){
    var createButton = document.getElementById("create-button");
    createButton.addEventListener("click", createTable);
}

function createTable() {

    localStorage.removeItem("table_id");
    localStorage.removeItem("table_name");
    localStorage.removeItem("table_password");

    var name = document.getElementById("name").value;
    var password = document.getElementById("password").value;
    if(name == "" || password == ""){
        alert("Phải nhập đầy đủ tên và mật khẩu.");
        return;
    }

    const ajax = new XMLHttpRequest();
    ajax.onload = function(){
        if(this.status == 200){
            console.log(this.responseText);
            const returnTable = JSON.parse(this.responseText);
            localStorage.setItem("table_id", returnTable.id);
            localStorage.setItem("table_name", returnTable.name);
            localStorage.setItem("table_password", returnTable.password);
            localStorage.setItem("owner", true);
            alert("Tạo bàn chơi thành công.");
            window.location.href = "/game.html";
        } else {
            alert("Error");
        }
    }
    ajax.open("POST", "/create-table", false);
    ajax.setRequestHeader('Content-Type', 'application/json');
    const toSend = {
        name: name,
        password: password
    }
    ajax.send(JSON.stringify(toSend));
}
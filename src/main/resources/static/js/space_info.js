function createSpaceInfo() {
    const estateid_row1 = [1, 3, 6, 8, 9];
    const estateid_row2 = [11, 13, 14, 16, 18, 19];
    const estateid_row3 = [21, 23, 24, 26, 27, 29];
    const estateid_row4 = [31, 32, 34, 37, 39];
    const bus_station_id = [5, 15, 25, 35];
    const company_id = [12];

    for (let index = 0; index < 40; index++) {
        const button = document.getElementsByClassName('square-' + index)[0];
        if (estateid_row1.includes(index)) {
            button.addEventListener('click', getSquare(index))
        } else if (estateid_row2.includes(index)) {
            button.addEventListener('click', function () {
                const content = document.getElementById('content');
                content.innerHTML = 'Chủ nhà: <br>Giá mua 1 căn nhà = $100 <br>Số lượng nhà hiện tại:';
            })
        } else if (estateid_row3.includes(index)) {
            button.addEventListener('click', function () {
                const content = document.getElementById('content');
                content.innerHTML = 'Chủ nhà: <br>Giá mua 1 căn nhà = $150 <br>Số lượng nhà hiện tại:';
            })
        } else if (estateid_row4.includes(index)) {
            button.addEventListener('click', function () {
                const content = document.getElementById('content');
                content.innerHTML = 'Chủ nhà: <br>Giá mua 1 căn nhà = $200 <br>Số lượng nhà hiện tại:';
            })
        } else if (bus_station_id.includes(index)) {
            button.addEventListener('click', function () {
                const content = document.getElementById('content');
                content.innerHTML = 'Chủ bến xe:';
            })
        } else if (company_id.includes(index)) {
            button.addEventListener('click', function () {
                const content = document.getElementById('content');
                content.innerHTML = 'Chủ công ty: ';
            })
        }
    }
}

function getSpace(spaceid){
    const estateIds = [1,3,6,,8,9,11,13,14,16,18,19,21,23,24,26,27,29,31,32,34,37,39];
    const chanceIds = [7, 22, 36];
    const chestIds = [2, 17, 33];
    const ajax = new XMLHttpRequest();
    ajax.onload = function () {
        if(this.status != 200){
            alert(this.responseText);
        } else {
            var content = document.getElementById("content");
            content.innerHTML = this.responseText;
            if(estateIds.includes(spaceid)){
                content.innerHTML += '<div class="row"><div class="col"><div class="btn btn-warning" onclick="sellABuilding('+spaceid+')">Bán 1 nhà</div></div><div class="col"><div class="btn btn-danger" onclick="sellEstate()">Bán đất</div></div></div>';
            }
            if(chanceIds.includes(spaceid)){
                content.innerHTML += '<div class="w-100 d-flex justify-content-center"><div class="btn btn-info" onclick="takeChance()">Rút</div></div>';
            }
            if(chestIds.includes(spaceid)){
                content.innerHTML += '<div class="w-100 d-flex justify-content-center"><div class="btn btn-info" onclick="takeChest()">Rút</div></div>';
            }
        }
    }
    const uri = "/space/" + localStorage.getItem("table_id") + "/" + spaceid;
    ajax.open("GET", uri, false);
    ajax.setRequestHeader('Content-type', 'application/json');
    ajax.send();
}
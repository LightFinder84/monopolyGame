window.onload = function (params) {

    const estateid = [1,3,6,8,9,11,13,14,16,18,19,21,23,24,26,27,29,31,32,34,37,39];
    for (let index = 0; index < 40; index++) {
        const button = document.getElementsByClassName('square-'+index)[0];
        if (estateid.includes(index)) {
            button.addEventListener('click', function(){
                const content = document.getElementById('content');
                content.innerHTML = 'Chủ nhà: <br>Giá mua 1 căn nhà = $100 <br>Số lượng nhà hiện tại:';
            })
        } else {
            button.addEventListener('click', function(){
                const content = document.getElementById('content');
                content.innerHTML = 'khong phai dat';
            })
        }
    }
    // const button_0 = document.getElementById("start-space");

//     const button_1 = document.getElementById("nguyen-hue-space");
//     const button_2 = document.getElementById("community-chest-space-1");
//     const button_3 = document.getElementById("le-loi-space");
//     const button_4 = document.getElementById("tax-space");
//     const button_5 = document.getElementById("can-giuoc-bus-station");
//     const button_6 = document.getElementById("luong-dinh-cua-space");
//     const button_7 = document.getElementById("chance-1-space");
//     const button_8 = document.getElementById("vo-thi-sau-space");
//     const button_9 = document.getElementById("hai-ba-trung-space");
//     const button_10 = document.getElementById("jail-space");

//     const button_11 = document.getElementById("nguyen-tat-thanh-space");
//     const button_12 = document.getElementById("electricity-company-space");
//     const button_13 = document.getElementById("nguyen-trai-space");
//     const button_14 = document.getElementById("an-duong-vuong-space");
//     const button_15 = document.getElementById("western-bus-station");
//     const button_16 = document.getElementById("hau-giang-space");
//     const button_17 = document.getElementById("community-chest-space-2");
//     const button_18 = document.getElementById("hung-vuong-space");
//     const button_19 = document.getElementById("huynh-tan-phat-space");
//     const button_20 = document.getElementById("car-park");
    
//     const button_21 = document.getElementById("car-park");
//     const button_22 = document.getElementById("car-park");
//     const button_23 = document.getElementById("car-park");
//     const button_24 = document.getElementById("car-park");
//     const button_25 = document.getElementById("car-park");
//     const button_26 = document.getElementById("car-park");
//     const button_27 = document.getElementById("car-park");
//     const button_28 = document.getElementById("car-park");
//     const button_29 = document.getElementById("car-park");
//     const button_30 = document.getElementById("car-park");

//     const button_30 = document.getElementById("car-park");
//     const button_31 = document.getElementById("car-park");
//     const button_32 = document.getElementById("car-park");
//     const button_33 = document.getElementById("car-park");
//     const button_34 = document.getElementById("car-park");
//     const button_35 = document.getElementById("car-park");
//     const button_36 = document.getElementById("car-park");
//     const button_37 = document.getElementById("car-park");
//     const button_38 = document.getElementById("car-park");
//     const button_39 = document.getElementById("car-park");

//     button_0.addEventListener('click', add_content_0);

//     button_1.addEventListener('click', add_content_1);
//     button_2.addEventListener('click', add_content_2);
//     button_3.addEventListener('click', add_content_3);
//     button_4.addEventListener('click', add_content_4);
//     button_5.addEventListener('click', add_content_5);
//     button_6.addEventListener('click', add_content_6);
//     button_7.addEventListener('click', add_content_7);
//     button_8.addEventListener('click', add_content_8);
//     button_9.addEventListener('click', add_content_9);
//     button_10.addEventListener('click', add_content_10);

//     button_11.addEventListener('click', add_content_11);
//     button_12.addEventListener('click', add_content_12);
//     button_13.addEventListener('click', add_content_13);
//     button_14.addEventListener('click', add_content_14);
//     button_15.addEventListener('click', add_content_15);
//     button_16.addEventListener('click', add_content_16);
//     button_17.addEventListener('click', add_content_17);
//     button_18.addEventListener('click', add_content_18);
//     button_19.addEventListener('click', add_content_19);
//     button_20.addEventListener('click', add_content_20);

//     button_21.addEventListener('click', add_content_20);
//     button_22.addEventListener('click', add_content_20);
//     button_23.addEventListener('click', add_content_20);
//     button_24.addEventListener('click', add_content_20);
//     button_25.addEventListener('click', add_content_20);
//     button_26.addEventListener('click', add_content_20);
//     button_27.addEventListener('click', add_content_20);
//     button_28.addEventListener('click', add_content_20);
//     button_29.addEventListener('click', add_content_20);
//     button_30.addEventListener('click', add_content_20);
}
// function add_content_0() {
//     const content = document.getElementById("content");
//     content.innerHTML = "";
// }
// function add_content_1() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ nhà: <br>Giá mua 1 căn nhà = $50 <br>Số lượng nhà hiện tại: ";
// }
// function add_content_2() {
//     const content = document.getElementById("content");
//     content.innerHTML = "2";
// }
// function add_content_3() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ nhà: <br>Giá mua 1 căn nhà = $50 <br>Số lượng nhà hiện tại: ";
// }
// function add_content_4() {
//     const content = document.getElementById("content");
//     content.innerHTML = "";
// }   
// function add_content_5() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ bến xe:";
// }
// function add_content_6() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ nhà: <br>Giá mua 1 căn nhà = $50 <br>Số lượng nhà hiện tại: ";
// }
// function add_content_7() {
//     const content = document.getElementById("content");
//     content.innerHTML = "<h1>helo<h1>";
// }
// function add_content_8() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ nhà: <br>Giá mua 1 căn nhà = $50 <br>Số lượng nhà hiện tại: ";
// }
// function add_content_9() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ nhà: <br>Giá mua 1 căn nhà = $50 <br>Số lượng nhà hiện tại: ";
// }
// function add_content_10() {
//     const content = document.getElementById("content");
//     content.innerHTML = "<h1>helo<h1>";
// }
// function add_content_11() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ nhà: <br>Giá mua 1 căn nhà = $100 <br>Số lượng nhà hiện tại: ";
// }
// function add_content_12() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ công ty: ";
// }
// function add_content_13() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ nhà: <br>Giá mua 1 căn nhà = $100 <br>Số lượng nhà hiện tại: ";
// }
// function add_content_14() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ nhà: <br>Giá mua 1 căn nhà = $100 <br>Số lượng nhà hiện tại: ";
// }
// function add_content_15() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ bến xe:";
// }
// function add_content_16() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ nhà: <br>Giá mua 1 căn nhà = $100 <br>Số lượng nhà hiện tại: ";
// }
// function add_content_17() {
//     const content = document.getElementById("content");
//     content.innerHTML = "<h1>helo<h1>";
// }
// function add_content_18() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ nhà: <br>Giá mua 1 căn nhà = $100 <br>Số lượng nhà hiện tại: ";
// }
// function add_content_19() {
//     const content = document.getElementById("content");
//     content.innerHTML = "Chủ nhà: <br>Giá mua 1 căn nhà = $100 <br>Số lượng nhà hiện tại: ";
// }
// function add_content_20() {
//     const content = document.getElementById("content");
//     content.innerHTML = "<h1>helo<h1>";
// }

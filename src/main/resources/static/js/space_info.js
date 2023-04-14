window.onload = function (params) {
    const button = document.getElementById("start-space");

    button.addEventListener('click', add_content);
}

function add_content() {
    const content = document.getElementById("content");
    content.innerHTML += "<h1>helo<h1>";
}
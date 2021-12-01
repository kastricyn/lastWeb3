"use strict"

function sendRequest(x, y, r) {
    $.ajax({
        type: "POST",
        data: {
            x: x,
            y: y,
            r: r,
        }
    }).always(function (result) {
        const res = result || "error with connection to server";
        document.getElementsByTagName("main")[0].innerHTML = res;
    });
}

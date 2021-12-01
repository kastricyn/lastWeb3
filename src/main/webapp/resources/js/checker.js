"use strict"
const Y_MIN = -3;
const Y_MAX = 3;

$("#areaForm input.someSold_areaForm_inputY").bind("input", function () {
    if (this.value === "-")
        return;
    let str = this.value.replaceAll(",", ".");
    while (str.length > 0 && !checkNumberInSegment(str, Y_MIN, Y_MAX))
        str = str.slice(0, str.length - 1);
    this.value = str;
});

function getCurrentR() {
    return PrimeFaces?.widgets?.widget_areaForm_rInput?.getValue() || 1;
}
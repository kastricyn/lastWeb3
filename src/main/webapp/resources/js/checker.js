"use strict"
const Y_MIN = -3;
const Y_MAX = 3;

/**
 * @param number строка с предполагаем числом
 * @param start левая граница отрезка
 * @param end правая граница отрезка
 * @returns {boolean} true если строка - число, лежащее в заданном отрезке, иначе false
 */
function checkNumberInSegment(number, start, end) {
    return number.length > 0 && isFinite(number) && parseFloat(number) >= start && parseFloat(number) <= end;
}

$("#areaForm input.someSold_areaForm_inputY").bind("input", function () {
    if (this.value === "-")
        return;
    let str = this.value.replaceAll(",", ".");
    while (str.length > 0 && !checkNumberInSegment(str, Y_MIN, Y_MAX)){
        str = str.slice(0, str.length - 1);}
    this.value = str.replaceAll(".", ",");
});

function getCurrentR() {
    return PrimeFaces?.widgets?.widget_areaForm_rInput?.getValue() || 1;
}
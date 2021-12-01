function replaceDotOnComma(){
    this.value = this.value.replace(/./g, ",");
}

function getCurrentR() {
    return PrimeFaces?.widgets?.widget_areaForm_rInput?.getValue() || 1;
}
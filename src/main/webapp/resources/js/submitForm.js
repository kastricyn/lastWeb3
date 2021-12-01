"use strict"

$("#areaCanvas").mouseup(function (event) {
    console.log(event);
    const r = parseFloat(getCurrentR());
    const prevX = event.pageX - $(this).offset().left;
    const prevY = event.pageY - $(this).offset().top;
    const {x, y} = new CoordinateTransform(r, this.width, this.height).getXYFromStandardAxes(prevX, prevY);
    console.log({x, y, r});
    $("#hiddenForm .someSold_hiddenClass_for_x").val(x.toString());
    $("#hiddenForm .someSold_hiddenClass_for_y").val(y.toString());
    $("#hiddenForm .someSold_hiddenClass_for_r").val(r.toString());
    $("#hiddenForm .hiddenClass").click();
});
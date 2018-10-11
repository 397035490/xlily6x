/**
 * 设置背景颜色
 */
var backgroundRuning = function (id) {
    var rgb = RandomColor();
    $("#"+id).css({"background": "linear-gradient(to left bottom, "+rgb+" 0%, rgba(255, 179, 253, 0.419608) 100%)","color":rgb})
}

/**
 * 随机产生颜色
 * @returns {string}
 * @constructor
 */
var RandomColor = function () {
    var r = Math.floor((Math.random()*255)+1);
    var g = Math.floor((Math.random()*255)+1);
    var b = Math.floor((Math.random()*255)+1);
    var color = "rgb("+r+","+g+","+b+")";
    return color;
}
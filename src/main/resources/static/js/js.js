let src = $('.background').css('background-image');
let url = src.match(/\((.*?)\)/)[1].replace(/('|")/g,'');

let img = new Image();
img.onload = function() {
    alert('image loaded');
}
img.src = url;
// if (img.complete) img.onload();
$("#daynight").click(function() {

  $("body").toggleClass("day");
  $("#sun").toggleClass("now");
  $("#moon").toggleClass("now");

});

var dt = new Date();
var time = dt.getHours();
console.log(time);
if (time < "8" || time >= "22") {
  $("#sun").toggleClass("now");
    $("body").toggleClass("day");
} else {
  $("#moon").toggleClass("now");
}

setTimeout(function(){
  $("#sun").css("transition", "2s");
  $("#moon").css("transition", "2s");
  $("body").css("transition", "2s");
}, 300);

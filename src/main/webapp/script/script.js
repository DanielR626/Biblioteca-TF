$('#login-button').click(function(){
  $('#login-button').fadeOut("slow",function(){
    $("#container").fadeIn();
    TweenMax.from("#container", .4, { scale: 0, ease:Sine.easeInOut});
    TweenMax.to("#container", .4, { scale: 1, ease:Sine.easeInOut});
  });
});

$(".close-btn").click(function(){
  TweenMax.from("#container", .4, { scale: 1, ease:Sine.easeInOut});
  TweenMax.to("#container", .4, { left:"0px", scale: 0, ease:Sine.easeInOut});
  $("#container, #register-container").fadeOut(800, function(){
    $("#login-button").fadeIn(800);
  });
});

/* Forgotten Password */
$('#register').click(function(){
  $("#container").fadeOut(function(){
    $("#register-container").fadeIn();
  });
});

function redirigirAotraPagina() {
  console.log("vamos a rederigirnos con ajax")
}

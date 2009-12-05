$(function(){
    $('#menuTopList a')
    .css( {
        backgroundPosition: "0 0"
    } )
    .mouseover(function(){
        $(this).stop().animate({
            backgroundPosition:"(0 -110px)"
        }, {
            duration:500
        })
    })
    .mouseout(function(){
        $(this).stop().animate({
            backgroundPosition:"(0 0)"
        }, {
            duration:500
        })
    })
    $('#menuSideList a')
    .css( {
        backgroundPosition: "0 0"
    } )
    .mouseover(function(){
        $(this).stop().animate({
            backgroundPosition:"(-280px 0)"
        }, {
            duration:500
        })
    })
    .mouseout(function(){
        $(this).stop().animate({
            backgroundPosition:"(0 0)"
        }, {
            duration:500
        })
    })
    
});

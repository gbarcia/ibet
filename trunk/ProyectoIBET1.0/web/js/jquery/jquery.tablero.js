$(document).ready(function() {

    $(function () {
        var $alert = $('#alert');
        if($alert.length)
        {
            var alerttimer = window.setTimeout(function () {
                $alert.trigger('click');
            }, 3000);
            $alert.animate({
                height: $alert.css('line-height') || '50px'
                }, 200)
            .click(function () {
                window.clearTimeout(alerttimer);
                $alert.animate({
                    height: '0'
                }, 200);
            });
        }
    });

    $("#slipForm").hide();

    var active = null;
    var init = false;

    $(".tablero .item").click(function() {
        if (!init){
            $("#slipNoBet").hide();
            $("#slipForm").fadeIn(1000);
            init = true;
        }
        if (active != null){
            $('#'.concat(active)).removeClass().addClass("item");
        }
        active = $(this).attr("id");
        var arregloIds = active.split('-');
        $("#idEvento").val(arregloIds[0]);
        $("#idParticipante").val(arregloIds[1]);
        $("#nombreParticipante").text($(this).find(".participante").text());
        $("#proporcion").text($(this).find(".proporcion").text());
        $("#nombreEvento").text("@ "+$(this).parent("tr").attr("id"));
        $(this).removeClass().addClass("itemClick");

        var ganancias = "";
        if ($("#importe").val() != ""){
            ganancias = parseFloat($("#importe").val()) * $("#proporcion").text();
            if (isNaN(ganancias)){
                ganancias = "";
            } else {
                ganancias = "$" + ganancias;
            }
        }
        $("#ganancias").text(ganancias);
    });

    $(".tablero .item").hover(
        function() {
            if ($(this).attr("id") != active){
                $(this).removeClass().addClass("itemHover");
            }
        },
        function() {
            if ($(this).attr("id") != active){
                $(this).removeClass().addClass("item");
            }
        })

    $("#importe").keyup(function(){
        var ganancias = "";
        if ($(this).val() != ""){
            ganancias = parseFloat($(this).val()) * $("#proporcion").text();
            if (isNaN(ganancias)){
                ganancias = "";
            } else {
                ganancias = "$" + ganancias;
            }
        }
        $("#ganancias").text(ganancias);
    })

    $("#botonApostar").click(function(){
        window.location = "/ProyectoIBET/privado/front/apuesta/tableroApuesta.htm?ide=" + $("#idEvento").val() +
        "&idp=" + $("#idParticipante").val() +
        "&m=" + $("#importe").val();
    }
    )


});
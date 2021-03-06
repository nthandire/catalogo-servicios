$(document).ready(function() {
    $("#cpuauto").autocomplete({
    	  source: function(request, response){
    	   $.ajax({
    	    url: "/catalogo-servicios/solicitud/listarEquipo" , // remote datasource
    	    data: request,
    	    success: function(data){
    	     response(data); // set the response
    	    },
    	    error: function(){ // handle server errors
            console.log("No puedo traer los equipos");
    	      //$.jGrowl("No puedo traer los equipos", {
    	      //  theme: 'ui-state-error ui-corner-all'
    	      //});
    	    }
    	   });
    	  },
    	  minLength: 2, // triggered only after minimum 2 characters have been entered.
    	  select: function(event, ui) { // event handler when user selects a company from the list.
            event.preventDefault();
    	    $("#serie").val(ui.item.serie); // update the hidden field.
    	    $("#marca").val(ui.item.marca); // populate the employee field with the nasdaq symbol.
    	    $("#modelo").val(ui.item.modelo);
    	    $("#economico").val(ui.item.economico);
            $("#equipo").val(ui.item.equipo);
            $("#ubicacion").val(ui.item.ubicacion);
            $("#cuerpo").val(ui.item.cuerpo);
            $("#empleado").val(ui.item.empleado);
            $("#garantia").val(ui.item.garantia);
           // alert(ui.item.value)
            $("#idResguardoentregadetalle").val(ui.item.value);
            $(this).val("");
          }
        });

    $("#reporta").autocomplete({
          source: function(request, response){
           $.ajax({
            url: "/catalogo-servicios/solicitud/listarUsuario" ,
            data: request,
            success: function(data){
             response(data); // set the response
            },
            error: function(){ // handle server errors
            console.log("No puedo traer los usuarios");
              //$.jGrowl("No puedo traer los equipos", {
              //  theme: 'ui-state-error ui-corner-all'
              //});
            }
           });
          },
          minLength: 2, // triggered only after minimum 2 characters have been entered.
          select: function(event, ui) { // event handler when user selects a company from the list.
            event.preventDefault();
            $("#idReporta").val(ui.item.id);
            $(this).val(ui.item.nombre);
            $("#extension").val(ui.item.extension);
          }
        });

})

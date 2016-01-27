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
    	   $("#serie").val(ui.item.serie); // update the hidden field.
    	   $("#marca").val(ui.item.marca); // populate the employee field with the nasdaq symbol.
    	   $("#modelo").val(ui.item.modelo);
    	   $("#economico").val(ui.item.economico);
         $("#equipo").val(ui.item.equipo);
         $("#ubicacion").val(ui.item.ubicacion);
         $("#cuerpo").val(ui.item.cuerpo);
         $("#empleado").val(ui.item.empleado);
    	   $("#garantia").val(ui.item.garantia);
    	  }
	 	});

    $('#idReporta').change(function() {
        $.ajax({
            url: "/catalogo-servicios/incidente/extension" , // remote datasource
            data: {reporta: $('#idReporta').val()},
            success: function(data) {
              $("#extension").val(data);
            },
            error: function() {
            $("#extension").val("No puedo traer la extensión");
            }
        });

    });
})

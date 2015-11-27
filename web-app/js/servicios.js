$(document).ready(function() {
    $("#cpuauto").autocomplete({
    	  source: function(request, response){
    	   $.ajax({
    	    url: "/catalogo-servicios/incidente/listarEquipo" , // remote datasource
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
    	   $("#empleado").val(ui.item.empleado);
    	  }
	 	});
	})

$(function(){
	$('#selequipo').change(function() {
		$("#cpuauto").val("");
		$("#nombreArea").val("");
		$("#economico").val("");
		$("#equipo").val("");
		$("#marca").val("");
		$("#modelo").val("");
		$("#serie").val("");
		var myselect = document.getElementById("selequipo");
		switch(myselect.options[myselect.selectedIndex].value) {
		    case "SNS":
		    	$("#cpuauto").attr("readonly",true);
		    	$("#area").attr("style", "visibility: visible");
		    	$("#nombreArea").attr("style", "width: 250px;text-transform: uppercase");
		    	$("#nombreArea").attr("required","");
		    	$("#equipo").removeAttr("readonly");
		    	$("#marca").removeAttr("readonly");
		    	$("#modelo").removeAttr("readonly");
		    	$("#serie").removeAttr("readonly");
		    	$("#economico").val("SNS");
		        break;
		    default:
		    	$("#cpuauto").removeAttr("readonly");
		    	$("#area").attr("style", "visibility: hidden");
		    	$("#nombreArea").removeAttr("required");
		    	$("#equipo").attr("readonly",true);
			    $("#marca").attr("readonly",true);
			    $("#modelo").attr("readonly",true);
			    $("#serie").attr("readonly",true);
		}
    });
});





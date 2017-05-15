/**
 * 
 */
var comments = ["Sin mascotas","Mascotas permitidas","Se exige venir duchado",
                "Paradas para comer", "Voy sin seguro",
                "Me teneis que dejar el carnet de conducir, que no tengo",
                "Si se para, empujais detrás vosotros", "traed vino que sin una copa no me arranco",
                "Sin la itv desde hace 16 años", "Sin niños que han ruidos o similares"];

var button = document.getElementById("autocompleteButton");
button.addEventListener("click", autocomplete, false);

function autocomplete(){
	document.getElementById("formRegisterTrip:departureaddress").value= "Uría 12";
	document.getElementById("formRegisterTrip:departurecity_input").value= "Oviedo";
	document.getElementById("formRegisterTrip:departurestate_input").value= "Asturias";
	document.getElementById("formRegisterTrip:departurecountry_input").value= "España";
	document.getElementById("formRegisterTrip:departurezipcode").value= "33100";
	
	document.getElementById("formRegisterTrip:destinationaddress").value= "Gran Vía 84";
	document.getElementById("formRegisterTrip:destinationcity_input").value= "Madrid";
	document.getElementById("formRegisterTrip:destinationstate_input").value= "Madrid";
	
	document.getElementById("formRegisterTrip:destinationcountry_input").value= "España";
	document.getElementById("formRegisterTrip:destinationzipcode").value= "27123";
	document.getElementById("formRegisterTrip:estimatedcost").value= parseInt(Math.random()*100)+1;
	var seats = parseInt(Math.random()*6)+1;
	document.getElementById("formRegisterTrip:maxpax").value= seats;
	document.getElementById("formRegisterTrip:availablepax").value= seats;
	
	document.getElementById("formRegisterTrip:comments").value= comments[parseInt(Math.random()*comments.length)];
	
	
	//Fechas
	var closingDate = new Date();
	var departureDate = new Date();
	var arrivalDate = new Date();
	closingDate.setDate(closingDate.getDate()+1);
	closingDate.setHours((parseInt(Math.random()*24)), (parseInt(Math.random()*60)), 0, 0);
	departureDate.setDate(departureDate.getDate()+2);
	departureDate.setHours((parseInt(Math.random()*24)), (parseInt(Math.random()*60)), 0, 0);
	arrivalDate.setDate(arrivalDate.getDate()+3);
	arrivalDate.setHours((parseInt(Math.random()*24)), (parseInt(Math.random()*60)), 0, 0);
	document.getElementById("formRegisterTrip:departuredate_input").value = departureDate.toLocaleDateString() +" "+departureDate.getHours()+":"+departureDate.getMinutes();
	document.getElementById("formRegisterTrip:destinationdate_input").value = arrivalDate.toLocaleDateString() +" "+arrivalDate.getHours()+":"+arrivalDate.getMinutes();
	document.getElementById("formRegisterTrip:closingdate_input").value = closingDate.toLocaleDateString() +" "+closingDate.getHours()+":"+closingDate.getMinutes();

	return false;
}
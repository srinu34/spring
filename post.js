$(Document).ready(function(){
	
	//to display add form
	$("#add").click(function(){
		
		$("#addTrack").slideToggle("slow");
	});
	
	//to submit form data
	$("#addForm").submit(function(event){
		
		event.preventDefault();
		ajaxPost();
	});

	$("#book").click(function(event){
		$("#bookTrack1").slideToggle("slow");
		
	});

	$("#bookForm1").submit(function(event){
		$("#bookTrack1").slideToggle("slow");
		$("#getTrack").slideToggle("slow");
		event.preventDefault();
		ajaxSearch();
	});

	$("#bookTkt").click(function(event){
		$("#bookTrack2").slideToggle("slow");
		
	});

	$("#bookForm2").submit(function(event){
		event.preventDefault();
		ajaxBook();
	});

	$("#bookings").click(function(event){
		$("#bookingHistory").slideToggle("slow");
		
	});

	$("#historyForm").submit(function(event){
		event.preventDefault();
		$("#bookingHistory").slideToggle("slow");
		$("#historyDiv").slideToggle("slow");
		ajaxHistory();
	});

	$("#cancelBtn").click(function(event){
		$("#cancelTrack").slideToggle("slow");
		
	});
	//--------------------------------Cancel Booking-------------------------------------------

	$("#cancelForm").submit(function(event){
        event.preventDefault()
        
		var id=$("#bID").val();
		
		$.ajax({
			
			type:"DELETE",
            contentType : "application/json",
            headers: {  "Access-Control-Allow-Origin": '*' },
			url:"http://localhost:8989/app/bookings/booking/cancel-booking/"+id,
			success:function(){
				$("#cancel_msg").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>"+
				"Booking cancelled   </p>");
			},
			error:function(e){
				alert("Enter value: ",e);
				// console.log("Error: ",e);
			}
		});
	});

	//------------------------------- Get History------------------------------------------------

	function ajaxHistory(){
		$.ajax({
			type: "GET",
			url: "http://localhost:8989/app/bookings/booking/yourBookings/"+$("#historyUID").val(),
			success: function(result) {
				$.each(result,
					function(i,userVO){
						var trackRow = '<tr>' +
					'<td>' + result[i].bookingId + '</td>' +
					'<td>' + userVO.user.name + '</td>' +
					'<td>' + userVO.flight.fId + '</td>' +
					'<td>' + userVO.flight.source.toUpperCase() + '</td>' +
					'<td>' + userVO.flight.destination.toUpperCase() + '</td>' +
					'<td>' + userVO.flight.dateOfDeparture + '</td>' +
					'<td>' + userVO.flight.farePrice+ '</td>' +
					'<td>' + userVO.flight.seatType+ '</td>' +
				  '</tr>';

                      $('#historyTable tbody').append(trackRow);
					});

			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});
				

	}

	//_____________________________Book Ticket_________________________________________________________

	function ajaxBook(){
		
		$.ajax({
			type: "POST",
			url: "http://localhost:8989/app/bookings/booking/book-flight/"+$("#bookingUserId").val()+"/"+$("#bookingFlightId").val()+"/"+$("#bookingSeatType").val(),
			success: function(result) {
				if(result!=null){
				
					$("#bookingStatus").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>"+
					 "Booking successfull!<br>"+
					 "-->{bookingId:"+result.bookingId+
					 +"}"+
					 "</p>");
					}
					
					console.log(result);
				},
				error:function(e){
					alert("Error!!");
				//	console.log("Error :"+e);
				}

			
		});

	}
	

	//----------------------------search flights by src and dest-------------------------------------------------

	function ajaxSearch(){

		var source = $("#from").val();
		var destination = $("#to").val();


		$.ajax({

			type: "GET",
			url: "http://localhost:8989/app/flights/flight/searchFlights/"+source+"/"+destination,
			success: function(result) {
				$.each(result,
					function(i,flight){
						var trackRow = '<tr>' +
					'<td>' + flight.fId + '</td>' +
					'<td>' + flight.name.toUpperCase() + '</td>' +
					'<td>' + flight.source.toUpperCase() + '</td>' +
					'<td>' + flight.destination.toUpperCase() + '</td>' +
					'<td>' + flight.dateOfDeparture + '</td>' +
					'<td>' + flight.availableNoOfEconomyClassSeats+ '</td>' +
					'<td>' + flight.availableNoOfFirstClassSeats+ '</td>' +
					'<td>' + flight.avaialbleNoOfBusinessClassSeats+ '</td>' +
					'<td>' + flight.priceEconomyClass+ '</td>' +
					'<td>' + flight.priceFirstClass+ '</td>' +
					'<td>' + flight.priceBusinessClass+ '</td>' +
				  '</tr>';

                      $('#getTable tbody').append(trackRow);
					});

			},
			error : function(e) {
				alert("Successfully: ", e);
				console.log("ERROR: ", e);
			}
		});

	}


	//---------------------------------Add User --------------------------------------------------
	
	function ajaxPost(){
		
		// PREPARE FORM DATA
		var formData={
			name:$("#uname").val(),
			age:$("#uage").val(),
			gender:$("#ugender").val()
		}
		
	//	console.log("formData before post: " + formData);

	
		
		// DO POST
		$.ajax({

			
			type : "POST",
			contentType : "application/json",
		//	headers: {  "Access-Control-Allow-Origin": '*' },
			url : "http://localhost:8989/app/users/user/add",
			data : JSON.stringify(formData),
			async : false,
			success:function(response){
				alert("Successfully");
				if(response!=null){
				
				$("#postTrackDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>"+
				 "post successfully!<br>"+
				// "-->{name:"+result.name+
				//",age: "+result.age+",gender:"+result.gender+"}"+
				"</p>");
				}
				
				console.log(response);
			},
			error:function(){
				alert("Error!!");
			//	console.log("Error :"+e);
			}
		
		});
		// Reset FormData after Posting
		resetData();	
		
	}
	 function resetData(){
    	$("#uname").val("");
    	$("#uage").val("")
    	$("#ugender").val("")
    	
    }
    //-----------------logout---------------------------------------------------------------------------------------------

    

	
	//-------------------------------------update user---------------------------------------------------------------------------------

	$("#update").click(function(){
	
		$("#updateTrack").slideToggle("slow");
		});
		
		
		$("#updateForm").submit(function(){
			event.preventDefault();
			ajaxPut();
		});
		
		/*
		 * AJAX PUT updated-form
		 */
		function ajaxPut(){
			//PREPARE FORM DATA
			var formData = {
					id: $("#userId").val(),
					name : $("#updateName").val()
			}
			
			
		//	console.log("formData before PUT: " + formData);
			
			// DO PUT
			$.ajax({
				type : "PUT",
				contentType : "application/json",
			//	headers: {  "Access-Control-Allow-Origin": '*' },
				url :  "http://localhost:8989/app/users/user/update-name/"+$("#userId").val()+"/"+$("#updateName").val(),
				// data : JSON.stringify(formData),
				//dataType : 'json',
				
				// SUCCESS response
				success:function(result){
					if(result!=null){
					// Create successful message
					$("#putTrackDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" + 
												"Put Successfully! <br>" +
												+"</p>");
												}
								console.log(result);
				},
				
				// ERROR response 
				error : function(e) {
					alert("Error!")
					console.log("ERROR: "+ e);
				}
			});
			resetData();
		}
		function resetData(){
			$("#userId").val("");
			$("#updateName").val("");
			
		}
});
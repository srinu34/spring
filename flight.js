
$(Document).ready(function(){

    $("#add").click(function(){
		
		$("#addTrack").slideToggle("slow");
	});

    $("#delete").click(function(){
		
		$("#deleteTrack").slideToggle("slow");
	});


    $("#get").click(function(event){

        $("#getTrack").slideToggle("slow");
        // event.preventDefault();

        getFlights();
    });

    //----------------------------------flight update-----------------------------------
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
					fId: $("#updateFlightId").val(),
					source : $("#updateSrc").val(),
                    destination:$("#updateDest").val()
			}
			
			
		//	console.log("formData before PUT: " + formData);
			
			// DO PUT
			$.ajax({
				type : "PUT",
				contentType : "application/json",
			//	headers: {  "Access-Control-Allow-Origin": '*' },
				url :  "http://localhost:8989/app/flights/flight/update-source/"+$("#updateFlightId").val()+"/"+$("#updateSrc").val(),
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
            $.ajax({
                type : "PUT",
                contentType : "application/json",
            //	headers: {  "Access-Control-Allow-Origin": '*' },
                url :  "http://localhost:8989/app/flights/flight/update-destination/"+$("#updateFlightId").val()+"/"+$("#updateDest").val(),
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


    //----------------------------------delete flight-----------------------------------

    $("#deleteForm").submit(function(event){
        event.preventDefault()
        
		var id=$("#deleteFlightId").val();
		
		$.ajax({
			
			type:"DELETE",
            contentType : "application/json",
            headers: {  "Access-Control-Allow-Origin": '*' },
			url:"http://localhost:8989/app/flights/flight/delete/"+id,
			success:function(){
				$("#delete_msg").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>"+
				"deleted succesfully!!!   </p>");
			},
			error:function(e){
				alert("Enter value: ",e);
				console.log("Error: ",e);
			}
		});
	});
   

    //___________________________________Add Flight ____________________________________
    
    $("#addForm").submit(function(event){
		
		event.preventDefault();
		flightPost();
	});
	
	function flightPost(){
		
		// PREPARE FORM DATA
		var formData={
			name:$("#flightName").val(),
			totalNoOfSeats:$("#totalSeats").val(),
            tottalNoOfFirstClassSeats:$("#totalFCS").val(),
            availableNoOfFirstClassSeats:$("#totalFCS").val(),
            priceFirstClass:$("#priceFC").val(),
            totalNoOfEconomyClassSeats:$("#totalES").val(),
            availableNoOfEconomyClassSeats:$("#totalES").val(),
            priceEconomyClass:$("#priceEC").val(),
            totalNoOfBusinessClassSeats:$("#totalBS").val(),
            avaialbleNoOfBusinessClassSeats:$("#totalBS").val(),
            priceBusinessClass:$("#priceBS").val(),
            source:$("#flightSource").val(),
            destination:$("#flightDestination").val(),
            dateOfDeparture:$("#departDate").val()+" "+$("#departTime").val(),
            estimatedDepartureTime:$("#departDate").val()+" "+$("#departTime").val()


		}
		
	//	console.log("formData before post: " + formData);
		
		// DO POST
		$.ajax({
			
			type : "POST",
			contentType : "application/json",
			url : "http://localhost:8989/app/flights/flight/add",
			data : JSON.stringify(formData),
			dataType : 'json',
			success:function(result){
				if(result!=null){
				$("#postTrackDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>"+
				"post successfully!<br>"+
				// "-->{name:"+result.name+
				// ",basedOn: "+result.basedOn+
                // ",name: "+result.name+
                // ",name: "+result.name+
                // ",name: "+result.name+
                // ",name: "+result.name+
                // ",name: "+result.name+
                //"}
                "</p>"
                );
                }
				
				console.log(result);
			},
			error:function(e){
				alert("Error!!");
				console.log("Error :"+e);
			}
		});
		// Reset FormData after Posting
	//	resetData();	
		
	}

   //----------------------Get All FLights ----------------------------------------------------------- 

    function getFlights(){
        $.ajax({
                    type : "GET",
                    url : "http://localhost:8989/app/flights/flight/getAllFlights",
                    success : function(result){
                            
                            $.each(result,
                                function(i,flight){
                                    var trackRow = '<tr>' +
                                '<td>' + flight.fId + '</td>' +
                                '<td>' + flight.name.toUpperCase() + '</td>' +
                                '<td>' + flight.source.toUpperCase() + '</td>' +
                                '<td>' + flight.destination.toUpperCase() + '</td>' +
                                '<td>' + flight.dateOfDeparture + '</td>' +
                                '<td>' + flight.totalNoOfSeats + '</td>' +
                              '</tr>';
            
            $('#getTable tbody').append(trackRow);
                                });

                        },
                        error : function(e) {
                            alert("ERROR: ", e);
                            console.log("ERROR: ", e);
                        }
                        
                    });
    
                        
    }

//get a flight-----------------------------------------------------------------

    // function getFlight(){
    //     $.ajax({
    //         type : "GET",
    //         url : "http://localhost:8989/app/flights/flight/getdetails" + document.getElementById("updateFlightId").val(),
    //         success : function(result){
    //             if(result.status=="success"){
    //                 var flights = "";
    //                 $.each(result.data,
    //                     function(i,flight){
    //                         var label1 = document.createElement("Label")
    //                         label1.setAttribute("for","source")
    //                         label1.innerHTML="Source"
    //                         $("#updateSubDiv").empty()
    //                         $("#updateSubDiv").append(label1)
    //                         var input1=document.createElement("Input")
    //                         input1.setAttribute("type","text")
    //                         input1.setAttribute("class","form-control")
    //                         input1.id="psrc"
    //                         input1.setAttribute("value",flight.source)
    //                         $("#updateSubDiv").append(input1)

    //                         var label2 = document.createElement("Label")
    //                         label2.setAttribute("for","destination")
    //                         label2.innerHTML="Destination"
    //                         $("#updateSubDiv").append(label2)
    //                         var input2=document.createElement("Input")
    //                         input2.setAttribute("type","text")
    //                         input2.setAttribute("class","form-control")
    //                         input2.id="pdst"
    //                         input2.setAttribute("value",flight.destination)
    //                         $("#updateSubDiv").append(input2)

    //                         var label3 = document.createElement("Label")
    //                         label3.setAttribute("for","name")
    //                         label3.innerHTML="Name"
    //                         $("#updateSubDiv").append(label3)
    //                         var input3=document.createElement("Input")
    //                         input3.setAttribute("type","text")
    //                         input3.setAttribute("class","form-control")
    //                         input3.id="pname"
    //                         input3.setAttribute("value",flight.name)
    //                         $("#updateSubDiv").append(input3)

    //                         var button = document.createElement("Button")
    //                         button.setAttribute("class" ,"btn btn-success")
    //                         button.id="updflt"

    //                         button.onclick=()=>{

    //                         }
    //                     });
    //             }
    //         }
    //     })
    // }
    
});
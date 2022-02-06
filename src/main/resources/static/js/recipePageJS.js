function myFunction(x) {
	  if ( x.classList.contains( "fa-heart") ) {
	        x.classList.remove( "fa-heart" );
	        x.classList.add( "fa-heart-o" );
	    }
	    else {
	        x.classList.remove( "fa-heart-o" );
	        x.classList.add( "fa-heart" );
	    }
}	

function modalFunction (modal){	
        $(modal).modal();
		
}


function notLoggedFunction (){
    
     document.getElementById("overlay").style.display = "block";
	 $('.notification').show();
	 $('.close').click(function(){
	        $(".notification").hide();
			 document.getElementById("overlay").style.display = "none";
	 }); 
}
	
window.addEventListener("load", function(){
	eventButton();
	eventoShowPassword();
})

function eventoShowPassword(){
	var toggle = document.querySelector("#togglePassword");
	toggle.addEventListener("click", function(){
		var input = document.querySelector("#password");
		if (input.type === "password") {
			input.type = "text";
			this.classList.remove("fa-eye");
			this.classList.add("fa-eye-slash");
		} else {
		 	input.type = "password";
			this.classList.add("fa-eye");
			this.classList.remove("fa-eye-slash");
		}
	})
	
	var toggleConfirm = document.querySelector("#toggleConfirmPassword");
	toggleConfirm.addEventListener("click", function(){
		var input = document.querySelector("#confirmPassword");
		if (input.type === "password") {
			input.type = "text";
			this.classList.remove("fa-eye");
			this.classList.add("fa-eye-slash");
		} else {
		 	input.type = "password";
			this.classList.add("fa-eye");
			this.classList.remove("fa-eye-slash");
		}

	})
}

function eventButton(){
	var button = document.querySelector("#btnChange");
	
	button.addEventListener("click", function(event){
		event.preventDefault();
		
		var success = true;
		
		var pass = $("#password");
		var confirm = $("#confirmPassword");
		
		var info = document.querySelector("#info");
		var infoConfirm = document.querySelector("#infoConfirm");
		
		if(pass.val() !== confirm.val()){
			pass.addClass("makeRed");
			confirm.addClass("makeRed");
			info.innerHTML += "Password diverse";
			infoConfirm.innerHTML += "Password Diverse";
			success = false;
		} else{
			pass.removeClass("makeRed");
			confirm.removeClass("makeRed");
			info.innerHTML = "";
			infoConfirm.innerHTML = "";
		}
		
		
		if(success){
			var form = document.querySelector("#formReset");
			form.submit();
		}
	})
}
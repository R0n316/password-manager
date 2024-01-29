let password = document.getElementById("password");
password.type = "password";
function showPassword(){
    let image = document.querySelector("img");
    if(password.type==="password"){
        password.type = "text";
        image.src = "/static/images/eye-open.svg"
    }
    else{
        password.type="password";
        image.src="/static/images/eye-close.svg";
    }
}
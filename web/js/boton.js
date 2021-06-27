let buttons = document.querySelectorAll(".btn");
let login = document.getElementById("login-button");

buttons.forEach(button => {
    button.addEventListener('click', function(){
        buttons.forEach(btn => btn.classList.remove('active'));
        this.classList.add('active');
    })
})

function SetLoginActive(){
    buttons.forEach(btn => btn.classList.remove('active'));
    login.classList.add('active');
    console.log("Activando pestaña login");
}
                        
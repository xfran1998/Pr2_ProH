var boton = document.querySelectorAll('.button');
var container = document.querySelector('#container-body-user');

boton.forEach( btn =>{
    btn.addEventListener('click', function(){
        var tag = btn.classList[1];

        Array.prototype.forEach.call(container.children, ctn =>{
            ctn.classList.remove("active");
            if(ctn.classList.contains(tag))
            {
                ctn.classList.add("active");
            }
        })       
    })
})

var boton_user = document.querySelectorAll('.btn-user');
var containe_user_datos = document.querySelector('.container-user-datos');
var containe_user_hist = document.querySelector('.container-user-hist');

console.log(boton_user);
boton_user.forEach( btn =>{
    btn.addEventListener('click', function(){
        console.log(btn.classList);
        boton_user.forEach(ubtn => ubtn.classList.remove('active'));
        this.classList.add('active');
        if (this.classList.contains("active"))
        {
            if (this.classList.contains("hist"))
            {
                containe_user_datos.classList.remove("active");
                containe_user_hist.classList.add("active");
            }
            if (this.classList.contains("user"))
            {
                containe_user_hist.classList.remove("active");
                containe_user_datos.classList.add("active");
            }
        }
    })    
})
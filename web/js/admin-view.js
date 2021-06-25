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

var boton_master = document.querySelectorAll('.btn-user');
var container_master = document.querySelector('.container-user');

boton_master.forEach( btn =>{
    btn.addEventListener('click', function(){
        var tag = btn.classList[1];
        console.log(tag);
        Array.prototype.forEach.call(container_master.children, ctn =>{
            ctn.classList.remove("active");
            if(ctn.classList.contains(tag))
            {
                ctn.classList.add("active");
            }
        })       
    })
})
var add = document.getElementsByClassName("btn-add");
//localStorage.clear();
var productos = [];

var item = {
    id: 0,
    nombre: "",
    precio: 0,
    cantidad: 1,
    imagen: "",
    stock: 0,
    iv: 0
};

function addItem(id, nombre, precio, imagen, stock, iv, redirect){
    console.log("ID: " + id);
    if (localStorage.length != 0){ 
        productos = JSON.parse(localStorage.getItem("productos"));
    }

    var existe = false;
    // person[0]..[3]
    if (productos != null){
        productos.forEach(function(prod){
            if (id == prod.id){
                existe = true;
                if (prod.cantidad < prod.stock)
                    prod.cantidad++;
                console.log('Ya existe item: '+ id + ' stock: ' + prod.stock);
            }
        });
    }
    else{
        productos = []; //si es null habrá fallo al hacer el push (primer item añadido)
    }

    if (!existe){ //Si es nuevo
        Object.defineProperty(item, "id", {value:id});
        Object.defineProperty(item, "nombre", {value:nombre});
        Object.defineProperty(item, "precio", {value:parseInt(precio)});
        Object.defineProperty(item, "imagen", {value:imagen});
        Object.defineProperty(item, "stock", {value:parseInt(stock)});
        Object.defineProperty(item, "iv", {value:parseInt(iv)});
        console.log('Item añadido: '+ id);
        productos.push(item);
    }

    localStorage.setItem("productos",JSON.stringify(productos));
    //localStorage.clear(); 
    
    if (redirect){
        Cargar('inicio.html','cuerpo');
    }
    
    console.log();
};

function productoTemplate(item, carrito){
    const itemElement = document.createElement('div');
    itemElement.classList.add("carrito-item");

    const itemHTML = `
            <div class="poke-card">
                <div class="card-inner">
                    <div class="card-face card-front">
                        <div class="pokemon" style="background-color: rgb(222, 253, 224);">
                            <div class="img-container">
                                <img src="${item.imagen}" alt="item imagen">
                            </div>
                            <div class="info">
                                <h3 class="name poke-text">${item.nombre}</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item-info">
                <h2>${item.nombre} ${item.iv}% IV</h2>
                <small class="ystock">En Stock</small>
                <div class="cambiarProducto">
                    <input class="mod-prod" type="button" value="+" onclick="modificarProducto('${item.id}', '1')">
                    <div>${item.cantidad}</div>
                    <input class="mod-prod" type="button" value="-" onclick="modificarProducto('${item.id}', '-1')">
                </div>
            </div>
            <div class="money-money"><h3>${item.precio}€ (Unitario)</h3></div>
    `
    itemElement.innerHTML = itemHTML;
    
    carrito.appendChild(itemElement);
}

function modificarProducto(id, cantidad){
    if (localStorage.length != 0)
        productos = JSON.parse(localStorage.getItem("productos"));
    
    if (productos != null){
        productos.forEach(function(prod){
            if (id == prod.id){
                var cant = prod.cantidad + parseInt(cantidad);
                
                if (cant > 0 && cant <= prod.stock){
                    console.log("cant: " + cantidad + " | stock: " +prod.stock);
                    prod.cantidad = cant;
                }
                else if(cant == 0){ //Elimina producto
                    var i = productos.indexOf(prod);
                    productos.splice(i,1);
                }   
            }
        });
    }
    
    console.log(id);
    console.log(productos);
    localStorage.setItem("productos",JSON.stringify(productos));
    mostrarCarrito();
}

function mostrarCarrito(){
    const carrito = document.getElementById('carrito');
    
    if (localStorage.length != 0) 
        productos = JSON.parse(localStorage.getItem("productos"));
    
    // Eliminar HTML anterior para evitar mostrar los antiguos (cargados anteriormente)
    if (carrito != null){
        carrito.querySelectorAll('*').forEach(n => n.remove());
    }

    if (productos != null){
        productos.forEach(function(it){
            productoTemplate(it, carrito);
        });        
    }
}

function getCarrito(){
    productos = JSON.parse(localStorage.getItem("productos"));
    var carrito = new Object();

    if (productos != null){
        productos.forEach(function(prod){
            carrito[prod.id] = prod;
        });
    }

    console.log(carrito);
    return carrito;
}


function resetCarrito(){
    console.log("Reseteando carrito");
    productos = [];
    localStorage.setItem("productos",JSON.stringify(productos));
}
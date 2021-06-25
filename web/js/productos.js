var card = document.querySelectorAll('.poke-card');

card.forEach( pokemon =>{
    pokemon.addEventListener('mouseover', function(){
        let poke = pokemon.firstElementChild;
        poke.classList.toggle('is-flipped');
    })
})

card.forEach( pokemon =>{
    pokemon.addEventListener('mouseout', function(){
        let poke = pokemon.firstElementChild;
        poke.classList.toggle('is-flipped');
    })
})
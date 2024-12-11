function toggleColor(event, button) {
    event.preventDefault();
    if (button.classList.contains('btn-light')) {
        button.classList.remove('btn-light');
        button.classList.add('btn-primary');
    } else {
        button.classList.remove('btn-primary');
        button.classList.add('btn-light');
    }
}

function setPabellon(event, pabellon) {
    event.preventDefault();
    document.getElementById('dropdownPabellon').textContent = pabellon;
}

function cancelar(event) {
    event.preventDefault();
    alert('Acción cancelada.');
}

function enviar(event) {
    event.preventDefault();
    alert('Datos enviados.');
}

const carousel = document.getElementById('formatosCarousel');
const carouselText = document.getElementById('carouselText');

const texts = [
    "Formato Aulas",
    "Formato Panbellones",
    "Formato Pisos",
    "Formato Aulas de Clase"
];

// Cambiar el texto al iniciar el carrusel
carouselText.innerHTML = `<h5>${texts[0]}</h5>`;

// Escuchar el evento de cambio de imagen
carousel.addEventListener('slid.bs.carousel', function () {
    const activeIndex = Array.from(carousel.querySelectorAll('.carousel-item')).findIndex(item => item.classList.contains('active'));
    carouselText.innerHTML = `<h5>${texts[activeIndex]}</h5>`;
});
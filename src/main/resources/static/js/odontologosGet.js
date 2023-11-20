window.addEventListener('load', function () {
    listarOdontologos();
});

function listarOdontologos() {
   const url = '/odontologos/listar';
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener odontólogos. Por favor, inténtalo nuevamente.');
            }
            return response.json();
        })
        .then(odontologos => {
            const odontologosList = document.getElementById('odontologos_list');
            odontologosList.innerHTML = '';
            odontologos.forEach(odontologo => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${odontologo.id}</td>
                    <td>${odontologo.matricula}</td>
                    <td>${odontologo.nombre}</td>
                    <td>${odontologo.apellido}</td>
                    <!-- Agrega más celdas según sea necesario -->
                `;
                odontologosList.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error al obtener odontólogos:', error.message);
        });
}
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <body>


        <!-- ventana Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Detalles del libro</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div id="perro-details">
                            <!-- Aqui se mostraran los detalles del perro -->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function openModal() {
                // Obtén el valor del campo de búsqueda
                var tituloLibro = $('input[name="titulo_libro"]').val();

                console.log("desde modal" + tituloLibro);
                // Realiza una solicitud AJAX al servlet SvBuscar
                $.ajax({
                    url: 'SvBuscar?titulo_libro=' + tituloLibro, 
                    method: 'POST',
                    success: function (data) {
                        
                        $('#perro-details').html(data);
                        // Abre el modal
                        $('#myModal').modal('show');
                    },
                    error: function () {
                        // Maneja errores aquí si es necesario
                        console.log('Error al cargar los detalles del perro.');
                    }
                });

                // Devuelve false para evitar que el formulario se envíe normalmente
                return false;
            }
        </script>

    </body>
</html>

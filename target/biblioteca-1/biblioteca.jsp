
<%@page import="com.umariana.biblioteca.Biblioteca"%>
<%@page import="java.util.List"%>
<%@page import="com.umariana.biblioteca.Libros"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">  
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/e153d37948.js" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/ae360af17e.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.js"></script>
        <link rel="stylesheet" href="css/bibliotecaStyle.css">
        <link rel="stylesheet" href="css/lightbox.css">
        <style>
            .custom-card {
                background: #ffffff;
                border: 1px solid rgb(255, 255, 255);
                border-radius: 10px;
                box-shadow: 0 2px 4px rgba(99, 82, 66, 0.1);
                padding: 20px;
                transition: transform 0.3s;
                margin: 0 auto;
                max-width: 600px;
            }

            .custom-card:hover {
                transform: scale(1.05);
            }

            .form-title {
                color: #333;
                font-size: 24px;
                margin-bottom: 20px;
            }

            .form-group {
                margin-bottom: 20px;
            }

            label {
                font-weight: bold;
                display: block;
                margin-bottom: 5px;
            }

            .form-control {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                transition: border-color 0.3s;
            }

            .form-control:focus {
                border-color: #ff1900;
            }

            .btn-primary {
                background: #ff9900;
                color: #D2B48C;
                border: none;
                border-radius: 5px;
                padding: 10px 20px;
                cursor: pointer;
                transition: background 0.3s;
            }

            .btn-primary:hover {
                background: #0056b3;
            }
            .coffee-card {
                background-color: #b3833c;
                color: #FFF;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(99, 82, 66, 0.1);
                margin-bottom: 20px;
            }

            .coffee-card .card-header {
                background-color: #D2B48C;
                color: #FFF;
                border-bottom: 1px solid #b3833c;
            }

            .event-card {
                background-color: #D2B48C;
                margin: 10px;
                padding: 15px;
                border-radius: 10px;
                box-shadow: 0 2px 4px rgba(99, 82, 66, 0.1);
                max-width: 500px;
                display: inline-block;
                text-align: center;
            }

            .event-card img {
                width: 100%;
                border-radius: 8px;
                margin-bottom: 15px;
            }

            .event-card h5,
            .event-card p,
            .event-card .btn {
                color: white;
                text-align: center;
            }

            .event-card .btn {
                background-color: #b3833c;
            }

            .event-card .btn:hover {
                background-color: #b3833c;
            }
        </style>
    </head>
    <body>
        <%
            String nombreUsuario = (String) session.getAttribute("nombre_usuario");
            ServletContext context = getServletContext();
        %>
        <%@include file="templates/header.jsp"%>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <div class="wrapper"> <!-- clase contenedora -->

            <!-- barra lateral -->
            <aside id="sidebar">
                <div class="h-100">

                    <div class="sidebar-logo">
                        <a href="#" style="color:black;">
                            ¡Bienvenido <%= nombreUsuario%>!
                        </a>
                    </div>
                        <form class="d-flex" role="search" onsubmit="return openModal()">
                        <input class="form-control me-2" type="search" name="titulo_libro" placeholder="Buscar por Título" aria-label="search">
                        <button class="btn btn-outline-success" type="submit">Buscar</button>
                    </form>
                    <!-- Sidebar Navigation -->
                    <ul class="sidebar-nav">
                        <li class="sidebar-header" style="color: black; font-family: 'italic', sans-serif;">
                            <h2>Opciones</h2>
                        </li>

                        <li class="sidebar-item">
    <a href="#" class="sidebar-link" id="inicio" style="color: black; font-family: 'italic', sans-serif;">
        <i class="fa-regular fa-building" style="color:black;"></i>
        Inicio
    </a>
</li>

<li class="sidebar-item">
    <a href="#" class="sidebar-link" id="mostrarTusLibros" style="color: black; font-family: 'italic', sans-serif;">
        <i class="fa-regular fa-eye" style="color:black;"></i>
        Tus libros
    </a>
</li>

<li class="sidebar-item">
    <a href="#" class="sidebar-link" id="mostrarTarea" style="color: black; font-family: 'italic', sans-serif;">
        <i class="fa-regular fa-bookmark" style="color:black;"></i>
        Libros disponibles
    </a>
</li>

<li class="sidebar-item">
    <a class="sidebar-link" id="mostrarFormulario" style="color:black; font-family: 'italic', sans-serif;">
        <i class="fa-regular fa-plus" style="color:black;"></i>
        Agregar libro
    </a>
</li>

<li class="sidebar-item">
    <a href="index.jsp" class="sidebar-link-2" id="" style="color:black; font-family: 'italic', sans-serif;">
        <i class="fa-regular fa-door-open" style="color:black;"></i>
        Salir
    </a>
</li>

                    </ul>

                </div>

            </aside>

            <section>

            </section>

            <div class="container-fluid"> 

                <div class="row">

                    <!-- Inicio -->
                    <div class="col-lg-12 d-flex justify-content-center align-items-center">
                        <section id="contenidoInicio" style="margin-top: 2%; margin-left: 10%;">
                            <div class="container" style="margin-left: 10%;">
                                <div class="row">
                                    <div class="col-lg-12 d-flex justify-content-center align-items-center">
                                        <img src="https://img.freepik.com/vector-gratis/pila-libros-diseno-plano-dibujado-mano_23-2149334862.jpg?w=740&t=st=1700624801~exp=1700625401~hmac=1dd113b8f5f62790db48e45bba4408a9746d49e6a8abf12c157af5d5479a29f0" alt="Imagen acorde" style="width: 50%; height: auto;">
                                    </div>
                                </div>
                            </div>

                            <!-- Jhojan Samir Samboni Cabrera -->
                            <h1 class="text-center">Jhojan Samir Samboni Cabrera</h1>

                            <!-- Brian Daniel Castro -->
                            <h1 class="text-center">Brian Daniel Castro</h1>

                            <!-- Daniel Revelo -->
                            <h1 class="text-center">Daniel Revelo</h1>

                            <!-- Juan Riascos -->
                            <h1 class="text-center">Juan Riascos</h1>
                        </section>
                    </div>
                </div>
            </div>
        </div>


        <!-- Seccion de formulario para agregar nuevo -->
        <div class="col-lg-12 d-flex justify-content-center align-items-center" style='margin-top: 5%;'> <!-- Agrega las clases d-flex, justify-content-center y align-items-center -->



            <form id="formularioTarea" action="/biblioteca/SvAgregar" method="post" style="display: none; margin-left: 20%" enctype="multipart/form-data">
                <div class="custom-card" style="background-color: #98FB98; color: white;" >
                    <div class="row">
                        <div class="col-md-6">
                            <h4 class="form-title" style="color:white">Agregar Libro</h4>

                            <div class="form-group">
                                <label for="id">Id</label>
                                <input type="text" name="id" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <label for="titulo">Titulo</label>
                                <input type="text" name="titulo" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <label for="autor">Autor</label>
                                <input type="text" name="autor" class="form-control" required>
                            </div>
                        </div>
                        <div class="col-md-6" style="margin-top:10%">
                            <div class="form-group">
                                <label for="anio">Año publicación</label>
                                <input type="text" name="anio" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <label for="foto">Foto</label>
                                <input type="file" name="fotos" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <label for="estado">Estado</label>
                                <select name="estado" class="form-control" required>
                                    <option value="Disponible" style="color:white">Disponible</option>
                                    <option value="No disponible" style="color:white">No Disponible</option>
                                </select>
                            </div>

                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary " >Agregar libro</button>
                </div>
            </form>




        </div>


        <%--seccion card para visualizar los libros  agregados, dependiendo de su estado  --%>
        <div class="col-lg-12 d-flex justify-content-center align-items-center">
            <div>
                <section id="tablaTarea" class="table table-striped" style="display: none; margin-left: 20%;">
                    <h2 style="color: rgb(21, 21, 21); text-align: center; margin-top: 5%;font-family: 'italic', sans-serif;">Estantería Literaria</h2>

                    <div class="container">
                        <div style=" color: white;color: rgb(21, 21, 21); display: flex; flex-wrap: wrap; justify-content: center;">
                            <% List<Libros> libros = Biblioteca.obtenerTodosLosLibros();
                                for (Libros libro : libros) {
                                    if (libro.getEstado().equals("Disponible")) {

                            %>
                            <div class="card" style="margin: 10px; width: 250px;" >
                                <img src="./imagenes/<%=libro.getFoto()%>" class="card-img-top" alt="Portada del Libro">
                                <div class="card-body">
                                    <h5 class="card-title"><%=libro.getTitulo()%></h5>
                                    <p class="card-text">Autor: <%=libro.getAutor()%></p>
                                    <p class="card-text">Año de Publicación: <%=libro.getAnio()%></p>
                                    <p class="card-text">Estado: <%=libro.getEstado()%></p>

                                    <!-- Botones de editar y eliminar -->

                                    <form action="SvPedir" method="post">
                                        <input type="hidden" name="id" value="<%=libro.getId()%>">
                                        <button type="submit" class="btn btn-primary">Pedir</button>
                                    </form>


                                </div>
                            </div>
                            <%
                                    }

                                }
                            %>
                        </div>
                    </div>
                </section>
            </div>
        </div>

        <%--seccion mostrar libros pedidos --%>
        <div class="col-lg-12 d-flex justify-content-center align-items-center" >
            <div id="tusLibros" class="table table-striped" style="display: none; margin-left: 20%;">

                <h2 style="color: rgb(21, 21, 21); text-align: center; margin-top: 5%; font-family: 'italic', sans-serif;">Libros</h2>

                <div class="container">
                    <div style="color: rgb(21, 21, 21); display: flex; flex-wrap: wrap; justify-content: center;">
                        <% List<Libros> tusLibros = Biblioteca.cargarDesdeArchivoPedido(context, nombreUsuario);
                            for (Libros libro : tusLibros) {


                        %>
                        <div class="card" style="margin: 10px; width: 250px;" >
                            <img src="./imagenes/<%=libro.getFoto()%>" class="card-img-top" alt="Portada del Libro">
                            <div class="card-body">
                                <h5 class="card-title"><%=libro.getTitulo()%></h5>
                                <p class="card-text">Autor: <%=libro.getAutor()%></p>
                                <p class="card-text">Año de Publicación: <%=libro.getAnio()%></p>
                                <p class="card-text">Estado: <%=libro.getEstado()%></p>

                                <!-- Botones de editar y eliminar -->

                                <form action="SvCambiarEstadoLibro" method="post">
                                    <input type="hidden" name="id" value="<%=libro.getId()%>">
                                    <button type="submit" class="btn btn-success">Devolver</button>
                                </form>
                            </div>
                        </div>
                        <%

                            }
                        %>
                    </div>
                </div>
            </div>
        </div>

        <%--seccion mostrar libros ordenados --%>
        <div>

        </div>

    </div>
</div>


<section>


    <!-- Modal para editar información de la libros -->
    <div class="modal fade" id="editModalConfirm" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content custom-modal">

                <div class="modal-body">


                    <form action="/mavenproject1/SvEditar"  method="POST"  >

                        <div class="modal-header">
                            <h5 class="modal-title" id="editModalLabel">Editar Información de la Tarea de con ID: <input type="text" class="form-control" id="id" name="id"  readonly style="border: none; background: none;" readonly> </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                        </div>

                        <div class="mb-3">
                            <label for="titulo" class="form-label">Título</label>
                            <input type="text" class="form-control" id="titulo" name="titulo">
                        </div>
                        <div class="mb-3">
                            <label for="descripcion" class="form-label">Descripción</label>
                            <input type="text" class="form-control" id="descripcion" name="descripcion">
                        </div>
                        <div class="mb-3">
                            <label for="fecha" class="form-label">Fecha de vencimiento</label>
                            <input type="date" class="form-control" id="fecha" name="fecha">
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-success" id="guardarCambios">Guardar Cambios</button>
                        </div>
                    </form>


                </div>
                <div class="modal-footer">
                    <input  type="submit" class="btn btn-secondary" style="margin-right: 10px;" value="Guardar cambios"> 

                </div>
            </div>
        </div>
    </div>

</section>                

<!-- Agrega Chart.js desde un CDN -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script src="script/lightbox-plus-jquery.min.js"></script>                      
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>    
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<%--Script para mostrar formulario tarea --%>                     

<script>


    $(document).ready(function () {
        $(".card").click(function () {
            var img = $(this).find("img").attr("src");
            var titulo = $(this).find(".card-title").text();
            var autor = $(this).find(".card-text:nth-child(2)").text();
            var anio = $(this).find(".card-text:nth-child(3)").text();

            $.fancybox.open({
                src: '<div style="text-align:center; padding: 15px;">' +
                        '<img src="' + img + '" alt="Portada del Libro" style="width: 100%;">' +
                        '<h5 style="margin-top: 15px;">' + titulo + '</h5>' +
                        '<p>' + autor + '</p>' +
                        '<p>' + anio + '</p>' +
                        '<button type="submit" class="btn btn-success">Pedir prestado</button>' +
                        '</div>',
                type: 'inline'
            });
        });
    });

    // Datos para el gráfico de barras
    var barChartData = {
        labels: ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes'],
        datasets: [{
                label: 'Artículos Leídos',
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1,
                data: [4000, 5200, 6000, 4800, 6000],
            }]
    };

    // Datos para el gráfico de pastel
    var pieChartData = {
        labels: ['Lectura Rápida', 'Lectura Media', 'Lectura Lenta'],
        datasets: [{
                data: [35, 50, 15],
                backgroundColor: ['rgb(255, 99, 132)', 'rgb(54, 162, 235)', 'rgb(75, 192, 192)'],
            }]
    };

    // Configuración de los gráficos
    var barChartOptions = {
        responsive: true,
        legend: {
            display: false,
        },
        scales: {
            xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Días de la Semana'
                    }
                }],
            yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Artículos Leídos'
                    }
                }]
        }
    };

    var pieChartOptions = {
        responsive: true,
        legend: {
            position: 'bottom',
        },
    };

    // Crear los gráficos
    var barChart = new Chart(document.getElementById('barChart').getContext('2d'), {
        type: 'bar',
        data: barChartData,
        options: barChartOptions,
    });

    var pieChart = new Chart(document.getElementById('pieChart').getContext('2d'), {
        type: 'pie',
        data: pieChartData,
        options: pieChartOptions,
    });
</script>




<script>




    // Mostrar el contenido al cargar la página
    window.addEventListener("load", function () {
        var contenidoInicio = document.getElementById("contenidoInicio");
        contenidoInicio.style.display = "block";
    });

    // Mostrar el contenido cuando se hace clic en "Inicio"
    document.getElementById("inicio").addEventListener("click", function () {

        var formulario = document.getElementById("formularioTarea");
        var tabla = document.getElementById("tablaTarea");
        var contenidoInicio = document.getElementById("contenidoInicio");
        var tusLibros = document.getElementById("tusLibros");

        contenidoInicio.style.display = "block";
        tabla.style.display = "none";
        formulario.style.display = "none"
        tusLibros.style.display = "none"

    });

    document.getElementById("mostrarFormulario").addEventListener("click", function () {
        var formulario = document.getElementById("formularioTarea");
        var tabla = document.getElementById("tablaTarea");
        var contenidoInicio = document.getElementById("contenidoInicio");
        var tusLibros = document.getElementById("tusLibros");
        if (formulario.style.display === "none" || formulario.style.display === "") {
            formulario.style.display = "block";
            tabla.style.display = "none";
            contenidoInicio.style.display = "none";
            tusLibros.style.display = "none"
            document.getElementById("formularioActual").value = "tarea"; // Agregar esta línea
        }
    });

    document.getElementById("mostrarTarea").addEventListener("click", function () {
        var formulario = document.getElementById("tablaTarea");
        var tarea = document.getElementById("formularioTarea");
        var contenidoInicio = document.getElementById("contenidoInicio");
        var tusLibros = document.getElementById("tusLibros");
        if (formulario.style.display === "none" || formulario.style.display === "") {
            formulario.style.display = "block";
            tarea.style.display = "none";
            contenidoInicio.style.display = "none";
            tusLibros.style.display = "none"
            document.getElementById("formularioActual").value = "tabla"; // Agregar esta línea
        }
    });



    document.getElementById("mostrarTusLibros").addEventListener("click", function () {
        var formulario = document.getElementById("tablaTarea");
        var tarea = document.getElementById("formularioTarea");
        var contenidoInicio = document.getElementById("contenidoInicio");
        var tusLibros = document.getElementById("tusLibros");

        if (tusLibros.style.display === "none" || formulario.style.display === "") {
            tusLibros.style.display = "block";
            formulario.style.display = "none";
            tarea.style.display = "none";
            contenidoInicio.style.display = "none";

            document.getElementById("formularioActual").value = "tabla"; // Agregar esta línea
        }
    });




</script>


<script>
    function editarTarea(id, titulo, descripcion, fechaVencimiento) {


        // Accede a los datos de la tarea
        console.log("ID de la tarea: " + id);
        console.log("Título de la tarea: " + titulo);
        console.log("Descripción de la tarea: " + fechaVencimiento);
        // ... otros métodos y propiedades de la tarea

        // Luego, puedes usar estos datos para llenar el modal
        document.getElementById('id').value = id;
        document.getElementById('titulo').value = titulo;
        document.getElementById('descripcion').value = descripcion;
        document.getElementById('fecha').value = fechaVencimiento;
        // ... otros campos del modal
    }



    function eliminarTarea(titulo) {

        var confirmar = confirm('¿Estás seguro de que deseas eliminar la tarea con ID ' + titulo + '?');

        if (confirmar) {


            $.ajax({

                url: '/mavenproject1/SvEliminarTarea?titulo=' + titulo,

                method: 'POST',

                success: function () {
                    console.log("Aqui estoy");
                    window.location.href = '/mavenproject1/templates/listas.jsp';
                },
                error: function () {
                    console.log("paila");
                }
            });
            alert('Tarea eliminada con exito');

        }
    }
</script>


</body>
</html>

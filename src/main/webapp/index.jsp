<%-- 
    Document   : index
    Created on : 30/10/2023, 4:08:07 p. m.
    Author     : Samir
--%>

<%@page import="com.umariana.biblioteca.controlUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.biblioteca.Usuarios"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/estilos.css">
    <title>Bienvenido</title>

    <link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Arimo' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Hind:300' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/css2?family=Quahon&display=swap" rel="stylesheet">

</head>
<body>
 
    
    <%
        String relativePath = getServletContext().getRealPath("/data");
        String archivoA = "usuario.txt";

        File data = new File(relativePath);
        File archivo = new File(data + "/" + archivoA);
        data.mkdir();
        archivo.createNewFile();

        // Obtener array list de la solicitud utilizando el método cargarPerros
        ServletContext context = request.getServletContext();
        controlUsuario.cargarArchivo(context);

        

    %>

    <div id="login-button">

        <img src="https://previews.123rf.com/images/tifani1/tifani11801/tifani1180100032/93016694-usuario-icono-de-ilustraci%C3%B3n-vectorial-sobre-fondo-negro.jpg">
        </img>
    </div>
    <div id="Titulo">
        <h1> BIBLIOTECA ONLINE </h1>

    </div>
    <div id="lema"
         <h4> </h4>
    <div id="container">
    <h1>¡Bienvenido!</h1>
    <span class="close-btn">
        <img src="https://cdn4.iconfinder.com/data/icons/miu/22/circle_close_delete_-128.png" alt="Cerrar"></img>
    </span>

    <form action="SvIngresar" method="POST">
        <input type="text" name="usuario" placeholder="Usuario" style="color: #FFF" required>
        <input type="password" name="contrasenia" placeholder="Contraseña" style="color: #FFF" required>
        <input type="submit" value="Ingresar" style="color: #FFF">
        <div id="invitado-container">

        </div>
        <div id="remember-container">
            <span id="register">Registrarse</span>
        </div>
    </form>
</div>

    <!-- Register Container -->
    <div id="register-container">
        <h1>Registro</h1>
        <span class="close-btn">
            <img src="https://cdn4.iconfinder.com/data/icons/miu/22/circle_close_delete_-128.png"></img>
        </span>

        <form action="SvRegistro" method="post">
            <input type="text" name="usuario" placeholder="Usuario" style="color: #FFF" required>
            <input type="text" name="cedula" pattern="[0-9]+" placeholder="Cedula" style="color: #FFF" required>
            <input type="password" name="contrasenia" placeholder="Contraseña" style="color: #FFF" required>
            <input class="submit" type="submit" value="Registrarse" style="color: #FFF">
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.9.1/gsap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.16.1/TweenMax.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>  
    <script src="script/script.js"></script>

</body>
</html>

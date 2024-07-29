<%@page import="java.util.*"%>
<%@page import="molder.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping cart login</title>
        <%@include file="includes/head.jsp" %>
        <!-- Font Icon -->
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
        <link rel="icon" href="images/login.png"
              type="images/x-icon"/>
        <!-- Main css -->
        <link rel="stylesheet" href="css/style_1.css">
        <style>
            .signup-image-link {
                display: block; /* Ensure the link takes up the full width */
                margin-bottom: 10px; /* Add some spacing between the links */
                color: #333; /* Set the default color */
                text-decoration: none; /* Remove underline */
                transition: all 0.3s ease; /* Smooth transition for all properties */
            }

            .signup-image-link:hover {
                color: hotpink; /* Change text color on hover */
                font-size: 1.1em; /* Increase font size */
                background-color: #f0f0f0; /* Add background color */
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.3); /* Add box shadow */
            }

        </style>
    </head>
<body>
        <input type="hidden" id="msg"
               value="<%= request.getAttribute("msg")%>"
               >
        <%
          String error = (String)request.getAttribute("ERROR");
          if(error == null){
              error = "";
            }
        %>
        <!-- Sing in  Form -->
        <div class="main">
            <section class="sign-in">
                <div class="container">
                    <div class="signin-content">
                        <div class="signin-image">
                            <figure><img src="images/signin-image.jpg" alt="sing up image"></figure>
                            <a href="register.jsp" class="signup-image-link">Create an account</a>
                            <a href="forgotPassword.jsp" class="signup-image-link">Forgot password</a>
                        </div>
                        <div class="signin-form">
                            <h2 class="form-title">Sign in</h2>
                            <form action="login" method="post" class="register-form" id="login-form">
                                <div class="form-group">
                                    <label for="email" ><i class="zmdi zmdi-email"></i></label>
                                    <input type="email" name="login-email" value="${email}" id="email" placeholder="Your Email"required>
                                </div>
                                <div class="form-group">
                                    <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                    <input type="password" name="login-password" value="${password}" id="pass" placeholder="Password" required>
                                </div>
                                <div class="form-group">
                                    <input type="checkbox"value="${remember_me}" id="remember_me" name="remember_me" class="agree-term""   />
                                    <label for="remember_me" class="label-agree-term"><span><span></span></span>Remember me</label>
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="signin" id="signin" class="form-submit" value="Login"> 

                                </div>
                            </form>
                            <div class="social-login">
                                <span class="social-label">Or login with</span>
                                <ul class="socials">
                                    <li><a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:9999/FPTLaptopWeb/LoginGoogleHandler&response_type=code&client_id=18993437903-a13m7927ia5chiq6s9ih1cvrpodlbmi6.apps.googleusercontent.com">
                                            <i class="display-flex-center zmdi zmdi-google"></i></a></li>
<li><a href="#"><i class="display-flex-center zmdi zmdi-twitter"></i></a></li>
                                    <li><a href="https://www.facebook.com/dialog/oauth?client_id=1802849310237929&redirect_uri=http://localhost:8080/ShoppingCart/LoginFacebookHandle&scope=email"><i class="display-flex-center zmdi zmdi-facebook"></i></a></li>
                                    
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>
            </section>
        </div>

        <!-- Facebook -->                            
                             


        <!-- JS -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="js/main.js"></script>
        <script src="sweetalert.min.js"></script>
        <script defer src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.0/sweetalert.min.js"></script>
        <script>
                document.addEventListener('DOMContentLoaded', function () {
                    var msg = document.getElementById("msg").value;
                    if (msg === "Email or password incorrect!") {
                        swal("Sorry", "Wrong Email or Password", "error");
                    }
                });


        </script>
        <%@include file="includes/footer.jsp"%>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </body>
</html>

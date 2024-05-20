<%-- 
    Document   : register
    Created on : Jan 21, 2024, 8:35:14 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <!-- Font Icon -->
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="icon" href="images/cash-register.gif"
              type="images/x-icon"/>
    </head>
    <body>
        <input type="hidden" id="status"
               value="<%= request.getAttribute("status")%>"     
               >
        <div class="main">

            <!-- Sign up form -->
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title">Sign up</h2>

                            <form  action="register"  method="POST" class="register-form" id="register-form">
                                <div class="form-group">
                                    <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="name" id="name" placeholder="Your Name" value="${givenname}"/>
                                </div>
                                <div class="form-group">
                                    <label for="email"><i class="zmdi zmdi-email"></i></label>
                                    <input type="email" name="email" id="email" placeholder="Your Email" value="${email}" required/>
                                </div>

                                <div class="form-group">
                                    <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                    <input type="password" name="pass" id="pass" placeholder="Password" required/>
                                </div>
                                <div class="form-group">
                                    <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                    <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password"/>
                                </div>
                                <div class="form-group">
                                    <label for="country"><i class="zmdi zmdi-flag"></i></label>
                                    <input type="text" name="country" id="country" placeholder="Your Country" required/>
                                </div>
                                <div class="form-group">
                                    <label for="contact"><i class="zmdi zmdi-phone"></i></label>
                                    <input type="text" name="contact" id="contact" placeholder="Your Contact Number" required/>
                                </div>
                                <div class="form-group">
                                    <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                    <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                                </div>
                                <p style="color: red">${erro}</p>
                                <p style="color: red">${msg}</p>

                            </form>
                        </div>
                        <div class="signup-image">
                            <figure><img src="images/signup-image.jpg" alt="sing up image"></figure>
                            <a href="user-login" class="signup-image-link">I am already member</a>
                        </div>
                    </div>
                </div>
            </section>
        </div>

        <!-- JS -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="js/main.js"></script>
        <script src="sweetalert.min.js"></script>
        <script defer src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.0/sweetalert.min.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var msg = document.getElementById("status").value;
                if (msg == "success") {
                    swal({
                        title: "Congrats",
                        text: "Account Created Successfully",
                        icon: "success",
                        timer: 3000, // Display the alert for 3 seconds
                        button: false // Hide the close button
                    });

                    setTimeout(function () {
                        window.location.href = "home";
                    }, 3000);
                }
            });
        </script>

    </body>
</html>

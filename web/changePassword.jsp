<%-- 
    Document   : changePassword
    Created on : Feb 6, 2024, 9:26:10 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <link rel="icon" href="images/seb.png"
              type="images/x-icon"/>
        <!-- bootstrap css 4.5.0 -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css" />
        <!-- font awesome 5.13.1 -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css" />
        <style>
            #signupForm a {
                text-decoration: none; /* Remove underline */
                display: inline-block; /* Make it inline with padding */
                transition: color 0.3s ease; /* Smooth transition for color change */
                color: white;
            }

            html{
                background-color: #f9f9f9;

            }
            .mainDiv {
                display: flex;
                min-height: 100%;
                align-items: center;
                justify-content: center;
                background-color: #f9f9f9;
                font-family: 'Open Sans', sans-serif;
            }
            .cardStyle {
                width: 500px;
                border-color: white;
                background: #fff;
                padding: 20px 0 60px 0;
                border-radius: 4px;
                margin: 100px 0;
                box-shadow: 0px 0 2px 0 rgba(0,0,0,0.25);
            }
            #signupLogo {
                max-height: 100px;
                margin: auto;
                display: flex;
                flex-direction: column;
            }
            .formTitle{
                font-weight: 600;
                margin-top: 20px;
                color: #2F2D3B;
                text-align: center;
            }
            .inputLabel {
                font-size: 12px;
                color: #555;
                margin-bottom: 6px;
                margin-top: 24px;
            }
            .inputDiv {
                width: 70%;
                display: flex;
                flex-direction: column;
                margin: auto;
            }
            input {
                height: 40px;
                font-size: 16px;
                border-radius: 4px;
                border: none;
                border: solid 1px #ccc;
                padding: 0 11px;
            }
            input:disabled {
                cursor: not-allowed;
                border: solid 1px #eee;
            }
            .buttonWrapper {
                margin-top: 40px;
            }
            .submitButton {
                width: 70%;
                height: 40px;
                margin: auto;
                display: block;
                color: #fff;
                background-color: #065492;
                border-color: #065492;
                text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.12);
                box-shadow: 0 2px 0 rgba(0, 0, 0, 0.035);
                border-radius: 4px;
                font-size: 14px;
                cursor: pointer;
            }
            .submitButton:disabled,
            button[disabled] {
                border: 1px solid #cccccc;
                background-color: #cccccc;
                color: #666666;
            }

            #loader {
                position: absolute;
                z-index: 1;
                margin: -2px 0 0 10px;
                border: 4px solid #f3f3f3;
                border-radius: 50%;
                border-top: 4px solid #666666;
                width: 14px;
                height: 14px;
                -webkit-animation: spin 2s linear infinite;
                animation: spin 2s linear infinite;
            }
            .back-button {
                display: inline-block;
                padding: 10px 20px;
                margin: 20px 0 0 40px;
                background-color: red;
                color: white  ;
                text-decoration: none;
                border-radius: 5px;
                border: none;
                cursor: pointer;
                box-shadow: 1px 2px 1px black;
            }
        </style>
    </head>
    <body>
        <div class="mainDiv">
            <div class="cardStyle">       
                <form action="profile" method="post" name="signupForm" id="signupForm">
                    <a class="back-button" href="profile">Back</a>
                    <img src="images/Screenshot 2024-02-09 070629.png" id="signupLogo"/>

                    <h2 class="formTitle">
                        Change Password
                    </h2>
                    <% if (request.getAttribute("changePasswordSuccessMessage") != null) { %>
                    <div class="alert alert-success" role="alert">
                        <%= request.getAttribute("changePasswordSuccessMessage") %>
                    </div>
                    <% } %>

                    <% if (request.getAttribute("errorCurrentPassword") != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getAttribute("errorCurrentPassword") %>
                    </div>
                    <% } %>

                    <% if (request.getAttribute("errorNewPassword") != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getAttribute("errorNewPassword") %>
                    </div>
                    <% } %>
                    <div class="inputDiv">
                        <label class="inputLabel" for="password">Current Password</label>
                        <div class="input-group">
                            <input type="password" id="currentPassword" name="currentPassword" class="form-control" placeholder="Enter your password" required>
                            <button class="btn btn-outline-secondary" type="button" id="btnCurrentPassword">
                                <span class="fas fa-eye"></span>
                            </button>
                        </div>
                        <p style="color: red ;font-size: 12px;">${e_password}</p>
                    </div>
                    <div class="inputDiv">
                        <label class="inputLabel" for="password">New Password</label>
                        <div class="input-group">
                            <input type="password" id="password" name="password" class="form-control" placeholder="Enter your password" required>
                            <button class="btn btn-outline-secondary" type="button" id="btnPassword">
                                <span class="fas fa-eye"></span>
                            </button>
                        </div>
                        <p style="color: red ;font-size: 12px;">${e_password}</p>
                    </div>

                    <div class="inputDiv">
                        <label class="inputLabel" for="confirmPassword">Confirm Password</label>
                        <div class="input-group">
                            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Confirm your password" required>
                            <button class="btn btn-outline-secondary" type="button" id="btnConfirmPassword">
                                <span class="fas fa-eye"></span>
                            </button>
                        </div>
                        <p style="color: red;font-size: 12px;">${e_confirmPassword}</p>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"></label>
                        <div class="col-lg-8">
                            <input class="form-control input_type" type="text"  name="action" value="editPassword" hidden>
                        </div>
                    </div>
                    <div class="buttonWrapper">
                        <button type="submit" id="submitButton"  class="submitButton pure-button pure-button-primary">
                            <span>Continue</span>
                        </button>
                    </div>

                </form>
            </div>
        </div>

    </body>
    <script>
        // step 1
        const ipnElement2 = document.querySelector('#currentPassword');
        const btnElement2 = document.querySelector('#btnCurrentPassword');
        const ipnElement = document.querySelector('#password');
        const btnElement = document.querySelector('#btnPassword');
        const ipnElement1 = document.querySelector('#confirmPassword');
        const btnElement1 = document.querySelector('#btnConfirmPassword');
        // step 2
        btnElement.addEventListener('click', function () {
            // step 3
            const currentType = ipnElement.getAttribute('type');

            // step 4
            ipnElement.setAttribute(
                    'type',
                    currentType === 'password' ? 'text' : 'password'
                    );
        });

        btnElement1.addEventListener('click', function () {
            // step 3
            const currentType1 = ipnElement1.getAttribute('type');
            // step 4
            ipnElement1.setAttribute(
                    'type',
                    currentType1 === 'password' ? 'text' : 'password'
                    );
        });

        btnElement2.addEventListener('click', function () {
            // step 3
            const currentType2 = ipnElement2.getAttribute('type');
            // step 4
            ipnElement2.setAttribute(
                    'type',
                    currentType2 === 'password' ? 'text' : 'password'
                    );
        });


    </script>
</html>

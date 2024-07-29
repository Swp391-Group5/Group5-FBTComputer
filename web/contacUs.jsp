<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="stylesheet" href="css/contact.css"/>-->
        <title>Contact</title>
    </head>
    <body>
        <section class="contact">
            <div class="contact">
                <h2 class="d-flex justify-content-center text-primary">Contact Us</h2>
                <div class="contact-wrapper">
                    <div class="contact-form">
                        <h3> Send us a message </h3>
                        <form id="contact-form" onsubmit="SendEmail() ; reset(); return false;"  > 
                            <div class="form-group">
                                <input type="text" id="name" name="name" placeholder="Your Name" required>
                            </div>
                            <div class="form-group">
                                <input type="email" id="email" name="email" placeholder="Your Email" required>
                            </div>
                            <div class="form-group">
                                <input type="text" id="phone" name="phone" placeholder="Phone no." required >
                            </div>
                            <div class="form-group">
                                <input type="text" id="title" name="title" placeholder="Your title" required  >
                            </div>
                            <div class="form-group">
                                <textarea id="message" name="message" placeholder="Your Message"></textarea>

                            </div>
                            <div class="form-group">
                                <div class="g-recaptcha" data-sitekey="6Lce-_wpAAAAALe_YJT0ytdKKTp_8f5eAoUjCif1"></div>

                            </div>
                            <button id="sendButton" class="btn btn-light" >Send Message</button>
                        </form>
                        <noscript>
                        <button id="sendButton" type="button" class="btn btn-light">Send Message</button>
                        </noscript>
                        <a href="home">Back</a>
                    </div>
                    <div class="contact-info">
                        <h3>Contact Information</h3>
                        <p><i class="fas fa-phone"></i> +1 23 456 789</p>
                        <p><i class="fas fa-envelope"></i> info@example.com</p>
                        <p><i class="fas fa-map-marker-alt"></i> 123 Street, City, Country</p>
                          <div class="map">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d29794.87468455396!2d105.79391227910159!3d21.018303400000004!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ac7768d4c1e7%3A0xb583b6d7f4e2a599!2sFPT%20Shop!5e0!3m2!1svi!2sus!4v1716273471328!5m2!1svi!2sus" width="100%" height="400" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
                    </div>
                    </div>
                  
                </div>
            </div>
            <noscript>
            <div style="padding: 10px; background-color: #f44336; color: white; text-align: center;">
                JavaScript is required to submit this form. Please enable JavaScript in your browser settings.
            </div>
            </noscript>

        </section>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script src="https://smtpjs.com/v3/smtp.js"></script>
        <script>
                                function SendEmail() {
                                    let name = document.getElementById("name").value;
                                    let email = document.getElementById("email").value;
                                    let phone = document.getElementById("phone").value;
                                    let title = document.getElementById("title").value;
                                    let message = document.getElementById("message").value;

                                    let recaptchaResponse = grecaptcha.getResponse();

                                    if (recaptchaResponse.length === 0) {
                                        alert("Please complete the reCAPTCHA.");
                                        return;
                                    }

                                    let formattedDate = new Date().toLocaleString();

                                    let body =
                                            '<div style="font-family: Arial, sans-serif; line-height: 1.5;">' +
                                            '<p>Someone just submitted your form:</p>' +
                                            '<table style="width: 100%; border-collapse: collapse; margin: 20px 0;">' +
                                            '<thead>' +
                                            '<tr>' +
                                            '<th style="background-color: #0000ff; color: white; text-align: left; padding: 8px; border: 1px solid #ddd;">Name</th>' +
                                            '<th style="background-color: #0000ff; color: white; text-align: left; padding: 8px; border: 1px solid #ddd;">Value</th>' +
                                            '</tr>' +
                                            '</thead>' +
                                            '<tbody>' +
                                            '<tr>' +
                                            '<td style="border: 1px solid #ddd; padding: 8px;">name</td>' +
                                            '<td style="border: 1px solid #ddd; padding: 8px;">' + name + '</td>' +
                                            '</tr>' +
                                            '<tr>' +
                                            '<td style="border: 1px solid #ddd; padding: 8px;">email</td>' +
                                            '<td style="border: 1px solid #ddd; padding: 8px;">' + email + '</td>' +
                                            '</tr>' +
                                            '<tr>' +
                                            '<td style="border: 1px solid #ddd; padding: 8px;">phone</td>' +
                                            '<td style="border: 1px solid #ddd; padding: 8px;">' + phone + '</td>' +
                                            '</tr>' +
                                            '<tr>' +
                                            '<td style="border: 1px solid #ddd; padding: 8px;">title</td>' +
                                            '<td style="border: 1px solid #ddd; padding: 8px;">' + title + '</td>' +
                                            '</tr>' +
                                            '<tr>' +
                                            '<td style="border: 1px solid #ddd; padding: 8px;">message</td>' +
                                            '<td style="border: 1px solid #ddd; padding: 8px;">' + message + '</td>' +
                                            '</tr>' +
                                            '</tbody>' +
                                            '</table>' +
                                            '<p style="font-size: 0.9em; color: #555;">Submitted on ' + formattedDate + '</p>' +
                                            '</div>';

                                    Email.send({
                                        SecureToken: "326788e0-be47-4fdb-a163-d08a6483fb8d",
                                    Host: "smtp.elasticemail.com", 
                                    Username: "bachdndhe176090@fpt.edu.vn",
                                    Password: "7081FBCDE2D263066C4BA30B3780182967E5",
                                    ReplyFrom : document.getElementById("email").value,
                                    To: 'bachdndhe176090@fpt.edu.vn',
                                    From: "bachdndhe176090@fpt.edu.vn", 
                                    Subject: "New Contact Us Message",
                                    Body: body
                                    }).then(
                                            message => alert(message)
                                    );
                                }



        </script>



    </body>
</html>

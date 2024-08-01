<%-- 
    Document   : contact
    Created on : Jun 28, 2024, 3:31:08 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">    
        <!-- Google reCAPTCHA -->
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script src="https://smtpjs.com/v3/smtp.js"></script>
        <style>

            /* Conatct start */

            .header-title
            {
                text-align: center;
                color:#00bfff;
            }

            #tip
            {
                display:none;
            }

            .fadeIn
            {
                animation-duration: 3s;
            }

            .form-control
            {
                border-radius:0px;
                border:1px solid #EDEDED;
            }

            .form-control:focus
            {
                border:1px solid #00bfff;
            }

            .textarea-contact
            {
                resize:none;
            }

            .btn-send
            {
                border-radius: 0px;
                border:1px solid #00bfff;
                background:#00bfff;
                color:#fff;
            }

            .btn-send:hover
            {
                border:1px solid #00bfff;
                background:#fff;
                color:#00bfff;
                transition:background 0.5s;
            }

            .second-portion
            {
                margin-top:50px;
            }


            .box > .icon {
                text-align: center;
                position: relative;
            }
            .box > .icon > .image {
                position: relative;
                z-index: 2;
                margin: auto;
                width: 88px;
                height: 88px;
                border: 8px solid white;
                line-height: 88px;
                border-radius: 50%;
                background: #00bfff;
                vertical-align: middle;
            }
            .box > .icon:hover > .image {
                background: #333;
            }
            .box > .icon > .image > i {
                font-size: 36px !important;
                color: #fff !important;
            }
            .box > .icon:hover > .image > i {
                color: white !important;
            }
            .box > .icon > .info {
                margin-top: -24px;
                background: rgba(0, 0, 0, 0.04);
                border: 1px solid #e0e0e0;
                padding: 15px 0 10px 0;
                min-height:163px;
            }
            .box > .icon:hover > .info {
                background: rgba(0, 0, 0, 0.04);
                border-color: #e0e0e0;
                color: white;
            }
            .box > .icon > .info > h3.title {
                font-family: "Robot",sans-serif !important;
                font-size: 16px;
                color: #222;
                font-weight: 700;
            }
            .box > .icon > .info > p {
                font-family: "Robot",sans-serif !important;
                font-size: 13px;
                color: #666;
                line-height: 1.5em;
                margin: 20px;
            }
            .box > .icon:hover > .info > h3.title, .box > .icon:hover > .info > p, .box > .icon:hover > .info > .more > a {
                color: #222;
            }
            .box > .icon > .info > .more a {
                font-family: "Robot",sans-serif !important;
                font-size: 12px;
                color: #222;
                line-height: 12px;
                text-transform: uppercase;
                text-decoration: none;
            }
            .box > .icon:hover > .info > .more > a {
                color: #fff;
                padding: 6px 8px;
                background-color: #63B76C;
            }
            .box .space {
                height: 30px;
            }

            @media only screen and (max-width: 768px)
            {
                .contact-form
                {
                    margin-top:25px;
                }

                .btn-send
                {
                    width: 100%;
                    padding:10px;
                }

                .second-portion
                {
                    margin-top:25px;
                }
            }
            /* Conatct end */
        </style>
    </head>
    <body>
        <div class="container animated fadeIn">
            <div class="row">
                <h1 class="header-title"> Contact </h1>
                <hr>
                <div class="col-sm-12" id="parent">
                    <div class="col-sm-6">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d29794.87468455396!2d105.79391227910159!3d21.018303400000004!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ac7768d4c1e7%3A0xb583b6d7f4e2a599!2sFPT%20Shop!5e0!3m2!1svi!2sus!4v1716273471328!5m2!1svi!2sus" width="100%" height="400" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
                    </div>

                    <div class="col-sm-6">
                        <form action="SendEmail" class="contact-form" method="post" >

                            <div class="form-group">
                                <input type="text" required="" placeholder="Name" name="name" class="form-control">
                            </div>


                            <div class="form-group form_left">
                                <input type="email" required="" placeholder="Email" name="email" class="form-control">
                            </div>

                            <div class="form-group">
                                <input type="text" required="" placeholder="Subject" name="subject" class="form-control">
                            </div>
                            <div class="form-group">
                                <textarea class="form-control textarea-contact" rows="5" id="message" name="message" placeholder="Type Your Message/Feedback here..." required=""></textarea>
                                <br>
                                <div class="g-recaptcha" data-sitekey="6Lce-_wpAAAAALe_YJT0ytdKKTp_8f5eAoUjCif1"></div> <br>
                                <br>
                                <button class="btn btn-default btn-send"> <span class="glyphicon glyphicon-send"></span> Send </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="container second-portion">
                <div class="row">
                    <!-- Boxes de Acoes -->
                    <div class="col-xs-12 col-sm-6 col-lg-4">
                        <div class="box">							
                            <div class="icon">
                                <div class="image"><i class="fa fa-envelope" aria-hidden="true"></i></div>
                                <div class="info">
                                    <h3 class="title">MAIL & WEBSITE</h3>
                                    <p>
                                        <i class="fa fa-envelope" aria-hidden="true"></i> &nbsp gondhiyahardik6610@gmail.com
                                        <br>
                                        <br>
                                        <i class="fa fa-globe" aria-hidden="true"></i> &nbsp www.hardikgondhiya.com
                                    </p>

                                </div>
                            </div>
                            <div class="space"></div>
                        </div> 
                    </div>

                    <div class="col-xs-12 col-sm-6 col-lg-4">
                        <div class="box">							
                            <div class="icon">
                                <div class="image"><i class="fa fa-mobile" aria-hidden="true"></i></div>
                                <div class="info">
                                    <h3 class="title">CONTACT</h3>
                                    <p>
                                        <i class="fa fa-mobile" aria-hidden="true"></i> &nbsp (+91)-9624XXXXX
                                        <br>
                                        <br>
                                        <i class="fa fa-mobile" aria-hidden="true"></i> &nbsp  (+91)-756706XXXX
                                    </p>
                                </div>
                            </div>
                            <div class="space"></div>
                        </div> 
                    </div>

                    <div class="col-xs-12 col-sm-6 col-lg-4">
                        <div class="box">							
                            <div class="icon">
                                <div class="image"><i class="fa fa-map-marker" aria-hidden="true"></i></div>
                                <div class="info">
                                    <h3 class="title">ADDRESS</h3>
                                    <p>
                                        <i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp 15/3 Junction Plot 
                                        "Shree Krishna Krupa", Rajkot - 360001.
                                    </p>
                                </div>
                            </div>
                            <div class="space"></div>
                        </div> 
                    </div>		    
                    <!-- /Boxes de Acoes -->

                    <!--My Portfolio  dont Copy this -->

                </div>
            </div>

        </div>
        <!-- Start of LiveChat (www.livechat.com) code -->
        <script>
            window.__lc = window.__lc || {};
            window.__lc.license = 18250662;
            window.__lc.integration_name = "manual_onboarding";
            window.__lc.product_name = "livechat";
            ;
            (function (n, t, c) {
                function i(n) {
                    return e._h ? e._h.apply(null, n) : e._q.push(n)
                }
                var e = {_q: [], _h: null, _v: "2.0", on: function () {
                        i(["on", c.call(arguments)])
                    }, once: function () {
                        i(["once", c.call(arguments)])
                    }, off: function () {
                        i(["off", c.call(arguments)])
                    }, get: function () {
                        if (!e._h)
                            throw new Error("[LiveChatWidget] You can't use getters before load.");
                        return i(["get", c.call(arguments)])
                    }, call: function () {
                        i(["call", c.call(arguments)])
                    }, init: function () {
                        var n = t.createElement("script");
                        n.async = !0, n.type = "text/javascript", n.src = "https://cdn.livechatinc.com/tracking.js", t.head.appendChild(n)
                    }};
                !n.__lc.asyncInit && e.init(), n.LiveChatWidget = n.LiveChatWidget || e
            }(window, document, [].slice))
        </script>
        <noscript><a href="https://www.livechat.com/chat-with/18250662/" rel="nofollow">Chat with us</a>, powered by <a href="https://www.livechat.com/?welcome" rel="noopener nofollow" target="_blank">LiveChat</a></noscript>
        <!-- End of LiveChat code -->
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            $(function () {
                $('form').submit(function (event) {
                    var recaptcha = $("#g-recaptcha-response").val();
                    if (recaptcha === "") {
                        event.preventDefault();
                        alert("Please complete the reCAPTCHA");
                    }
                });
            });

        </script>
    </body>
</html>


<%-- 
    Document   : homeAdmin
    Created on : Mar 5, 2024, 11:28:56 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Admin Dashboard</title>
        <style>
            body {
                margin: 0;
                padding: 0;
            }
        </style>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&amp;display=swap">
        <link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb5/3.8.1/compiled.min.css">
        <link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb-plugins-gathered.min.css">
        <style>
            body {
                background-color: #fbfbfb;
            }
            @media (min-width: 991.98px) {
                main {
                }
            }

            /* Sidebar */
            .sidebar {
                position: fixed;
                top: 0;
                bottom: 0;
                left: 0;
                padding: 58px 0 0; /* Height of navbar */
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
                width: 240px;
                z-index: 600;
            }

            @media (max-width: 991.98px) {
                .sidebar {
                    width: 100%;
                }
            }
            .sidebar .active {
                border-radius: 5px;
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
            }

            .sidebar-sticky {
                position: relative;
                top: 0;
                height: calc(100vh - 48px);
                padding-top: 0.5rem;
                overflow-x: hidden;
                overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
            }
        </style>
        <%@include file="/includes/head.jsp" %>
    </head>
    <body >
        <%--<%@ include file="/includes/sidebar.jsp" %>--%>
        <%@include file="/includes/adminNavbar.jsp"%>
        <br>
        <br>
        <br>
        <main>
            <br>
            <br>
            <h1 style="text-align: center">User's Details</h1>
            <div class="container pt-4">
                <!--Section: Statistics with subtitles-->
                <section>
                    <div class="row" id="total">
                        <div class="col-xl-6 col-md-12 mb-4">
                            <div class="card">
                                <div style="padding: 10px 15px">
                                    <div class="d-flex justify-content-between p-md-1">
                                        <div class="d-flex flex-row">
                                            <div class="align-self-center">
                                                <i style="color: #1B813A; font-size: 50px" class="fa-solid fa-boxes-stacked"></i>
                                            </div>
                                        </div>
                                        <div style="text-align: end">
                                            <h4 style="color: #1B813A">Total Products</h4>
                                            <p class="mb-0"></p>
                                            <h2 class="h1 mb-0">${requestScope.totalProduct}</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-6 col-md-12 mb-4">
                            <div class="card">
                                <div style="padding: 10px 15px">
                                    <div class="d-flex justify-content-between p-md-1">
                                        <div class="d-flex flex-row">
                                            <div class="align-self-center">
                                                <i style="color: #06439F; font-size: 50px" class="fa-solid fa-truck-fast"></i>
                                            </div>
                                        </div>
                                        <div style="text-align: end">
                                            <h4 style="color: #06439F;">Number of products sold</h4>
                                            <p class="mb-0"></p>
                                            <h2 class="h1 mb-0">${requestScope.totalOrder}</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xl-6 col-md-12 mb-4">
                            <div class="card">
                                <div style="padding: 10px 15px">
                                    <div class="d-flex justify-content-between p-md-1">
                                        <div class="d-flex flex-row">
                                            <div class="align-self-center">
                                                <i style="color: #E94644; font-size: 50px" class="fa-solid fa-money-bill"></i>
                                            </div>
                                        </div>
                                        <div style="text-align: end">
                                            <h4 style="color: #E94644;">Total Revenue</h4>
                                            <p class="mb-0"></p>
                                            <h2 class="h1 mb-0">${requestScope.totalSale} VND</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-6 col-md-12 mb-4">
                            <div class="card">
                                <div style="padding: 10px 15px">
                                    <div class="d-flex justify-content-between p-md-1">
                                        <div class="d-flex flex-row">
                                            <div class="align-self-center">
                                                <i style="color: #DD761C; font-size: 50px" class="fa-solid fa-users"></i>
                                            </div>
                                        </div>
                                        <div style="text-align: end">
                                            <h4 style="color: #DD761C;">Total Customer</h4>
                                            <p class="mb-0"></p>
                                            <h2 class="h1 mb-0">${requestScope.totalCustomer}</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xl-6 col-md-12 mb-4">
                            <div class="card">
                                <div style="padding: 10px 15px">
                                    <div class="d-flex justify-content-between p-md-1">
                                        <div class="d-flex flex-row">
                                            <div class="align-self-center">
                                                <i style="color: black; font-size: 50px" class="fa-solid fa-box"></i>
                                            </div>
                                        </div>
                                        <div style="text-align: end">
                                            <h4 style="color: black;">Total Product Type</h4>
                                            <p class="mb-0"></p>
                                            <h2 class="h1 mb-0">${requestScope.totalCategory}</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-6 col-md-12 mb-4">
                            <div class="card">
                                <div style="padding: 10px 15px">
                                    <div class="d-flex justify-content-between p-md-1">
                                        <div class="d-flex flex-row">
                                            <div class="align-self-center">
                                                <i style="color: #1B813A; font-size: 50px" class="fa-solid fa-newspaper"></i>
                                            </div>
                                        </div>
                                        <div style="text-align: end">
                                            <h4 style="color: #1B813A;">Total Blog</h4>
                                            <p class="mb-0"></p>
                                            <h2 class="h1 mb-0">${requestScope.totalBlog} </h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-6 col-md-12 mb-4">
                            <div class="card">
                                <div style="padding: 10px 15px">
                                    <div class="d-flex justify-content-between p-md-1">
                                        <div class="d-flex flex-row">
                                            <div class="align-self-center">
                                                <i style="color: #DD761C; font-size: 50px" class="fa-solid fa-comment"></i>
                                            </div>
                                        </div>
                                        <div style="text-align: end">
                                            <h4 style="color: #DD761C;">Total Feedback</h4>
                                            <p class="mb-0"></p>
                                            <h2 class="h1 mb-0">${requestScope.totalFeedback}</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-6 col-md-12 mb-4">
                            <div class="card">
                                <div style="padding: 10px 15px">
                                    <div class="d-flex justify-content-between p-md-1">
                                        <div class="d-flex flex-row">
                                            <div class="align-self-center">
                                                <i style="color: #E94644; font-size: 50px" class="fa-solid fa-star"></i>
                                            </div>
                                        </div>
                                        <div style="text-align: end">
                                            <h4 style="color: #E94644;">Average Rating</h4>
                                            <p class="mb-0"></p>
                                            <h2 class="h1 mb-0">${requestScope.averageStar}</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-6">
                        <section class="mb-4" id="doanhThuThu">
                            <div class="card">
                                <div class="card-header py-3">
                                    <h3 class="mb-0 text-center">
                                        <strong>Revenue by week</strong>
                                        <form id="f2" method="get" action="adminDashboard">
                                            <div class="d-flex justify-content-center mt-3 ">
                                                <select name="yearRev" class="form-select" id="dropdownYear" style="width: 100px;">
                                                    <c:set var="currentYear" value="2024"/>
                                                    <c:set var="endYear" value="2023"/>
                                                    <c:forEach var="year" begin="0" end="${currentYear - endYear}">
                                                        <option ${requestScope.year == (currentYear - year) ? "selected" : ""} value="${currentYear - year}">${currentYear - year}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                                <input style="width: 200px;" value="${requestScope.year}-W${requestScope.weekNumberRev}" type="week" class="form-control" id="weekInput" onclick="setDefaultWeek()">
                                                <input type="hidden" class="form-control" name="fromRev" id="from">
                                                <input type="hidden" class="form-control" name="toRev" id="to">
                                                <input type="hidden" class="form-control" name="monthRev" id="month">
                                                <button style="width: 100px; padding: 0" class="form-control" type="button" onclick="submitForm()">Submit</button>
                                            </div>
                                        </form>
                                    </h3>
                                </div>
                                <div class="card-body" style="width: 61%; margin: auto">
                                    <canvas class="my-4" id="pieChart"></canvas>
                                </div>
                            </div>
                        </section>
                    </div>
                    <div class="col-6">
                        <section class="mb-4" id="doanhThuThang">
                            <div class="card">
                                <div class="card-header py-3">
                                    <h3 class="mb-0 text-center">
                                        <strong>Revenue by month</strong>
                                        <form id="f1" method="get" action="adminDashboard">
                                            <select name="yearMonth" class="form-select text-center mx-auto mt-3" id="dropdownYear" style="width: 100px;" onchange="getYear(this)">
                                                <c:set var="currentYear" value="2024"/>
                                                <c:set var="endYear" value="2023"/>
                                                <c:forEach var="yearMonth" begin="0" end="${currentYear - endYear}">
                                                    <option ${requestScope.yearMonth == (currentYear - yearMonth) ? "selected" : ""} value="${currentYear - yearMonth}">${currentYear - yearMonth}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </form>
                                    </h3>
                                </div>
                                <div class="card-body my-5" >
                                    <canvas id="horizontalBar" class="w-100 h-100" ></canvas>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
                <section class="mb-4" id="doanhThuThuLineChart">
                    <div class="card">
                        <div class="card-header py-3">
                            <h3 class="mb-0 text-center">
                                <strong>Order by week</strong>
                                <form id="lineChartForm" method="get" action="adminDashboard">
                                    <div class="d-flex justify-content-center mt-3">
                                        <select name="year" class="form-select" id="dropdownYearLineChart" style="width: 100px;">
                                            <c:set var="currentYear" value="2024"/>
                                            <c:set var="endYear" value="2023"/>
                                            <c:forEach var="year" begin="0" end="${currentYear - endYear}">
                                                <option ${requestScope.year == (currentYear - year) ? "selected" : ""} value="${currentYear - year}">${currentYear - year}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <input style="width: 200px;" value="${requestScope.yearOrder}-W${requestScope.weekNumberOrder}" type="week" class="form-control" id="weekInputLineChart" onclick="setDefaultWeek()">
                                        <input type="hidden" class="form-control" name="from" id="fromLineChart">
                                        <input type="hidden" class="form-control" name="to" id="toLineChart">
                                        <input type="hidden" class="form-control" name="month" id="monthLineChart">
                                        <button style="width: 100px; padding: 0" class="form-control" type="button" onclick="submitLineChartForm()">Submit</button>
                                    </div>
                                </form>
                            </h3>
                        </div>
                        <div class="card-body" style="width: 61%; margin: auto">
                            <canvas class="w-100 h-100" id="lineChartCanvas"></canvas>
                        </div>
                    </div>
                </section>
                <br>
                <div class="card">

                    <h2 style="text-align:center"><strong>Top 5 Hottest Products</strong></h2>
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Top</th>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Image</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity sold</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.productTop5}" var="i" varStatus="loop">
                                <tr>
                                    <th>Top ${loop.index +1}</th>
                                    <td>${i.productId}</td>
                                    <td>${i.productName}</td>
                                    <td><img src="${i.productImage}" style="width: 100px"></td>
                                    <td>${i.getFormattedPrice()}VND</td>
                                    <td>${i.productCount}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <br>
            <br>
            <br>
            <br>
            <br>
        </main>
        <script>
            //Bar chart
            function getYear() {
                document.getElementById("f1").submit();
            }
            document.addEventListener("DOMContentLoaded", function () {
                // Graph
                // Horizontal Bar Chart
                new Chart(document.getElementById("horizontalBar"), {
                    "type": "bar",
                    "data": {
                        "labels": ["December", "November", "October", "September", "August", "July", "June", "May", "April", "March", "February", "January"],
                        "datasets": [{
                                "label": "Revenue VND",
                                "data": [${totalMoneyMonth12}, ${totalMoneyMonth11}, ${totalMoneyMonth10}, ${totalMoneyMonth9}, ${totalMoneyMonth8}, ${totalMoneyMonth7}, ${totalMoneyMonth6}, ${totalMoneyMonth5}, ${totalMoneyMonth4}, ${totalMoneyMonth3}, ${totalMoneyMonth2}, ${totalMoneyMonth1}],
                                "fill": false,
                                "backgroundColor": ["rgba(255, 99, 132, 0.2)", "rgba(255, 159, 64, 0.2)",
                                    "rgba(255, 205, 86, 0.2)", "rgba(75, 192, 192, 0.2)", "rgba(54, 162, 235, 0.2)",
                                    "rgba(153, 102, 255, 0.2)", "rgba(201, 203, 207, 0.2)", "#99FF99", "#FFFF99", "#FFC1C1", "#FFB5C5", "#DDC488"
                                ],
                                "borderColor": ["rgb(255, 99, 132)", "rgb(255, 159, 64)", "rgb(255, 205, 86)",
                                    "rgb(75, 192, 192)", "rgb(54, 162, 235)", "rgb(153, 102, 255)", "rgb(201, 203, 207)", "#66FF99", "#FFFF66", "#EEB4B4", "#EEA9B8", "#ECAB53"
                                ],
                                "borderWidth": 1
                            }]
                    },
                    "options": {
                        "indexAxis": 'y',
                        "scales": {
                            "x": {
                                "beginAtZero": true
                            }
                        }
                    }
                });
            });


            //Pie
            var ctxP = document.getElementById("pieChart").getContext('2d');

            var myPieChart = new Chart(ctxP, {
                type: 'pie',
                data: {
                    labels: ["Sunday", "Saturday", "Friday", "Thursday", "Wednesday", "Tuesday", "Monday"],
                    datasets: [{
                            data: [${totalMoney1}, ${totalMoney7}, ${totalMoney6}, ${totalMoney5}, ${totalMoney4}, ${totalMoney3}, ${totalMoney2}],
                            backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360", "#1874CD", "#CDB5CD"],
                            hoverBackgroundColor: ["#FF5A5E", "#5AD3D1", "#FFC870", "#A8B3C5", "#616774", "#1E90FF", "#FFE1FF"]
                        }]
                },
                options: {
                    responsive: true
                }
            });

            function submitForm() {
                var year = document.getElementById("dropdownYear").value;
                var weekInput = document.getElementById("weekInput").value;
                var [, week] = weekInput.split('-W');

                var startDate = getDateFromWeek(year, parseInt(week));
                var endDate = new Date(startDate);
                endDate.setDate(startDate.getDate() + 6);

                var month = startDate.toLocaleString('en-US', {month: 'long'});
                var monthNumber = getMonthNumber(month);

                var startDay = startDate.getDate();
                var endDay = endDate.getDate();

                document.getElementById("from").value = startDay;
                document.getElementById("to").value = endDay;
                document.getElementById("month").value = monthNumber;
                document.getElementById("f2").submit();
            }

            function getDateFromWeek(year, week) {
                var januaryFourth = new Date(year, 0, 4);
                var daysToAdd = (week - 1) * 7;
                januaryFourth.setDate(januaryFourth.getDate() + daysToAdd - januaryFourth.getDay() + 1);
                return januaryFourth;
            }

            function getMonthNumber(monthName) {
                var monthsMap = {
                    'January': 1,
                    'February': 2,
                    'March': 3,
                    'April': 4,
                    'May': 5,
                    'June': 6,
                    'July': 7,
                    'August': 8,
                    'September': 9,
                    'October': 10,
                    'November': 11,
                    'December': 12
                };
                return monthsMap[monthName];
            }


            //Line

            var ctxLineChart = document.getElementById("lineChartCanvas").getContext('2d');

            var myLineChart = new Chart(ctxLineChart, {
                type: 'line',
                data: {
                    labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
                    datasets: [{
                            label: "Orders",
                            data: [${totalOrder1}, ${totalOrder2}, ${totalOrder3}, ${totalOrder4}, ${totalOrder5}, ${totalOrder6}, ${totalOrder7}],
                            backgroundColor: "rgba(75,192,192,0.4)",
                            borderColor: "rgba(75,192,192,1)",
                            borderWidth: 1,
                            fill: false,
                            pointBackgroundColor: "rgba(75,192,192,1)",
                            pointBorderColor: "#fff",
                            pointHoverBackgroundColor: "#fff",
                            pointHoverBorderColor: "rgba(75,192,192,1)"
                        }]
                },
                options: {
                    responsive: true,
                    scales: {
                        x: {
                            display: true,
                            title: {
                                display: true,
                                text: 'Day of the Week'
                            }
                        },
                        y: {
                            display: true,
                            title: {
                                display: true,
                                text: 'Orders'
                            },
                            ticks: {
                                beginAtZero: true,
                                stepSize: 1, // Ensure y-axis ticks are integers
                                //                                callback: function (value) {
                                //                                    if (Number.isInteger(value)) {
                                //                                        return value;
                                //                                    }
                                //                                }
                            }
                        }
                    }
                }
            });

            function submitLineChartForm() {
                var year = document.getElementById("dropdownYearLineChart").value;
                var weekInput = document.getElementById("weekInputLineChart").value;
                var [, week] = weekInput.split('-W');

                var startDate = getDateFromWeek(year, parseInt(week));
                var endDate = new Date(startDate);
                endDate.setDate(startDate.getDate() + 6);

                var month = startDate.toLocaleString('en-US', {month: 'long'});
                var monthNumber = getMonthNumber(month);

                var startDay = startDate.getDate();
                var endDay = endDate.getDate();

                document.getElementById("fromLineChart").value = startDay;
                document.getElementById("toLineChart").value = endDay;
                document.getElementById("monthLineChart").value = monthNumber;
                document.getElementById("lineChartForm").submit();
            }

            function getDateFromWeek(year, week) {
                var januaryFourth = new Date(year, 0, 4);
                var daysToAdd = (week - 1) * 7;
                januaryFourth.setDate(januaryFourth.getDate() + daysToAdd - januaryFourth.getDay() + 1);
                return januaryFourth;
            }

            function getMonthNumber(monthName) {
                var monthsMap = {
                    'January': 1,
                    'February': 2,
                    'March': 3,
                    'April': 4,
                    'May': 5,
                    'June': 6,
                    'July': 7,
                    'August': 8,
                    'September': 9,
                    'October': 10,
                    'November': 11,
                    'December': 12
                };
                return monthsMap[monthName];
            }

        </script>


    </body>
    <%--<%@include file="/includes/finish.jsp"%>--%>
</html>

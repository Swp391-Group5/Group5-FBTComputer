<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Quality Assessment</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- DataTables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
        <!-- Custom Styles -->
        <link rel="stylesheet" href="./blogCss/stylez.css"/>
    </head>
    <body class="sb-nav-fixed">
        
        <div id="layoutSidenav">
            <div class="groundy" id="layoutSidenav_content">
                <h2 class="title-order">Product Quality Assessment</h2>
            <div class="container">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <!-- Toggle Dark Mode -->
                    <label class="switch">
                        <input type="checkbox" id="darkModeToggle">
                        <span class="slider"></span>
                    </label>
                    <!-- Export Blacklist to Excel -->
                    <form action="export-blacklist" method="get"> 
                        <button type="submit" class="btn btn-primary">Export Blacklist to Excel</button> 
                    </form>
                </div>
                <c:if test="${mess != null}">
                    <div id="alertDiv" class="alert alert-success" role="alert">
                        ${mess}
                    </div>
                </c:if>
                <table class="table table-striped table-bordered" id="sortTable">
                    <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>User Name</th>
                            <th>Warranty Count</th>
                            <th>Warranty Ratio (%)</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${productQualityList}" var="pq">
                            <tr>
                                <td>${pq.productName}</td>
                                <td>${pq.userName}</td>
                                <td>${pq.warrantyCount}</td>
                                <td>${pq.warrantyRatio}</td>
                                <td>${pq.status}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

        <!-- jQuery -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
        <!-- DataTables JS -->
        <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#sortTable').DataTable({
                    "language": {
                        "decimal": "",
                        "emptyTable": "No data available in table",
                        "info": " _START_ to _END_ of _TOTAL_ entries",
                        "infoEmpty": "Showing 0 to 0 of 0 entries",
                        "infoFiltered": "(filtered from _MAX_ total entries)",
                        "infoPostFix": "",
                        "thousands": ",",
                        "lengthMenu": "Show _MENU_ entries",
                        "loadingRecords": "Loading...",
                        "processing": "",
                        "search": "Search:",
                        "zeroRecords": "No matching records found",
                        "paginate": {
                            "first": "First",
                            "last": "Last",
                            "next": "Next",
                            "previous": "Previous"
                        },
                        "aria": {
                            "sortAscending": ": activate to sort column ascending",
                            "sortDescending": ": activate to sort column descending"
                        }
                    },
                    "lengthMenu": [5, 10, 15, 20]
                });

                // Dark Mode toggle button
                $('#darkModeToggle').click(function () {
                    $('body').toggleClass('dark-mode');
                    var darkModeEnabled = $('body').hasClass('dark-mode');
                    localStorage.setItem('darkMode', darkModeEnabled);

                    // Adjust text color in table cells when toggling Dark Mode
                    if (darkModeEnabled) {
                        $('#sortTable').find('td').css('color', '#ffffff');
                    } else {
                        $('#sortTable').find('td').css('color', ''); // Reset to default color
                    }
                });

                // Check if Dark Mode is enabled from localStorage
                var darkModeEnabled = localStorage.getItem('darkMode') === 'true';
                if (darkModeEnabled) {
                    $('body').addClass('dark-mode');
                    $('#sortTable').find('td').css('color', '#ffffff'); // Set text color to white in Dark Mode
                }
            });
        </script>
    </body>
</html>

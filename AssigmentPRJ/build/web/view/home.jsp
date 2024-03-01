<%-- 
    Document   : home
    Created on : Mar 1, 2024, 2:16:30 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>
            body {
                display: flex;
                flex-direction: column;
                min-height: 100vh;
            }
            label{
                margin-bottom: -8px;
            }
            main {
                flex: 1;
            }
            nav.navbar {
                margin-bottom: 20px; /* Adjust the margin as needed */
            }

            /* Custom Styles for the Header */
            header {
                background-color: #343a40; /* Dark background color */
                color: #ffffff; /* White text color */
                text-align: center;
                padding: 20px;
            }
        </style>

    </head>
    <body>
        <!-- Header -->
        <header>
            <h1>FPT Education</h1>
        </header>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <c:if test="${sessionScope.account.role eq 1}">
                            
                            <a class="nav-link" href="lecture/timetable?id=${sessionScope.account.lecture.id}">View Schedule</a> 
                        </c:if>
                        <c:if test="${sessionScope.account.role eq 2}">
                            
                            <a class="nav-link" href="lecture/timetable?id=">View Score</a> 
                        </c:if>
                         
                    </li>
                    

                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item ml-auto align-self-center">
                        <label>${sessionScope.account.displayname}</label> 
                    </li>
                    <li class="nav-item ml-auto">
                        <a class="nav-link" href="logout">Logout</a>  
                    </li>
                </ul>
            </div>
        </nav>

        <!-- Content -->
        <main class="container mt-3">
            <h1>Hello World!</h1>
            <!-- Your page content goes here -->
        </main>

        <!-- Footer -->
        <footer class="bg-dark text-white text-center py-3 mt-auto">
            <p>&copy; 2024 Your Website Name</p>
        </footer>



        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>

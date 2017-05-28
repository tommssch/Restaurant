<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="/registration/css/reg.css">
</head>
<body>
    <div class="centr">
        <form action="/Registration" method="post">
            <br> <h2>Личные данные:</h2> </br>
            <br> <input type="text" name="name" placeholder="Name"></br>
            <br> <input type="text" name="lname" placeholder="Last Name"></br>
            <br> <input type="text" name="fname" placeholder="Father Name"></br>
            <br> <input type="date" name="dob"  placeholder=" "></br>
            <br> <input type="text" name="email" placeholder="Email" ></br>
            <br> <input type="text" name="nick" placeholder="Nickname" ></br>
            <br> <input type="password" name="pass" placeholder="Password" ></br>
            <br> <input type="text" name="phone" placeholder="Phone" ></br>
            <br> <h2>Адрес:</h2>
            <br> <input type="text" name="city" placeholder="City"> </br>
            <br> <input type="text" name="street" placeholder="Street"> </br>
            <br> <input type="text" name="house" placeholder="House"> </br>
            <br> <input type="text" name="flat" placeholder="Flat"> </br>
            <br> <input type="submit" value="Enter">
        </form>
    </div>
</body>
</html>

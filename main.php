<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PHP Form</title>
    <style>
        .php_form {
            background-color: #f1dfdf;
            height: 450px;
            width: 800px;
            margin-left: 250px;
            margin-top: 25px;
        }

        .php_col {
            margin-top: 10px;
            padding: 8px;
            display: flex;
            margin-left: 130px;
        }

        .php_span {
            border: 1px solid black;
            padding: 5px;
            width: 120px;
            background-color: #2c3943;
            color: #fff;
            font-size: large;
            text-align: center;
        }

        .php_input {
            width: 170px;
        }

        .php_button {
            margin-left: 300px;
            margin-top: 40px;
        }

        .php_but {
            padding: 10px;
            width: 100px;
            background-color: #5e3c7e;
            color: #ffffff;
            font-size: large;
        }

        .php_but:hover {
            background-color: #eeeeee;
            color: black;
        }
    </style>
</head>

<body>
    <div class="php_form">
        <form method="POST">
            <div class="php_col">
                <span class="php_span">student Id</span>
                <input type="int" name="ID" placeholder="student ID">
            </div>

            <div class="php_col">
                <span class="php_span">Name</span>
                <input type="text" name="name" placeholder="student Name">
            </div>

            <div class="php_col">
                <span class="php_span">Department</span>
                <select name="department" class="php_input">
                    <option>select department</option>
                    <option>computer science & technology</option>
                    <option>civil technology</option>
                    <option>electrical technology</option>
                    <option>electronics technology</option>
                </select>
            </div>
            <div class="php_col">
                <span class="php_span">Address</span>
                <input type="text" name="address" placeholder="student address">
            </div>
            <div class="php_col">
                <span class="php_span">Phone Number</span>
                <input type="text" name="phone_number" placeholder="phone number">
            </div>
            <div class="php_col">
                <span class="php_span">CGPA</span>
                <input type="float" name="cgpa" placeholder="student cgpa">
            </div>

            <div class="php_button">
                <button type="submit" class="php_but" name="submit">Submit</button>
            </div>

            <?php
            $host_name = "localhost";
            $user_name = "root";
            $password = "";
            $database_name = "student_info";

            $conn = mysqli_connect($host_name, $user_name, $password, $database_name);

            if (isset($_POST['submit'])) {

                $ID = $_POST['ID'];
                $name = $_POST['name'];
                $department = $_POST['department'];
                $address = $_POST['address'];
                $phone_number = $_POST['phone_number'];
                $cgpa = $_POST['cgpa'];

                // Check duplicate ID
                $check_query = "SELECT * FROM student_data WHERE ID='$ID'";
                $check_result = mysqli_query($conn, $check_query);

                if (mysqli_num_rows($check_result) > 0) {

                    echo "This Student Roll already exists";
                } else {

                    $query = "INSERT INTO student_data
                        (ID, name, department, address, phone_number, cgpa)
                        VALUES
                        ('$ID', '$name', '$department', '$address', '$phone_number', '$cgpa')";

                    if (mysqli_query($conn, $query)) {

                        echo "Data inserted successfully";
                    } else {

                        echo "Error inserting data: " . mysqli_error($conn);
                    }
                }
            }
            ?>

        </form>
    </div>
</body>

</html>
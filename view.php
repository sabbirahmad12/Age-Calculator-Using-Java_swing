<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        table {
            margin: 50px auto;
            border-collapse: collapse;
            width: 80%;
        }

        th,
        td {
            border: 1.5px solid #2b0731;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #3fd0f8;
        }
    </style>
</head>

<body>
    <table border="1" cellspacing="0" cellpadding="10" class="table">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Department</th>
            <th>Address</th>
            <th>Number</th>
            <th>CGPA</th>
        </tr>
        <?php
        $host_name = "localhost";
        $user_name = "root";
        $password = "";
        $database_name = "student_info";

        $conn = mysqli_connect($host_name, $user_name, $password, $database_name);




        // include 'main.php';
        $read = "SELECT * FROM `student_data`";
        $result = mysqli_query($conn, $read);
        if ($result) {
            while ($row = mysqli_fetch_assoc($result)) {
                $id = $row['ID'];
                $name = $row['name'];
                $department = $row['department'];
                $address = $row['address'];
                $number = $row['phone_number'];
                $cgpa = $row['cgpa'];
                echo '<tr>
                        <td>' . $id . '</td>
                        <td>' . $name . '</td>
                        <td>' . $department . '</td>
                        <td>' . $address . '</td>
                        <td>' . $number . '</td>
                        <td>' . $cgpa . '</td>
                    </tr>';
            }
        }
        ?>
    </table>
</body>

</html>
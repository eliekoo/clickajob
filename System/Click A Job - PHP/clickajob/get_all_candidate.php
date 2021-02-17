<?php

/*
 * Following code will list all the products
 */

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DbConnect();

// get all products from products table
$result = mysql_query("SELECT * FROM employee_resume INNER JOIN employee_application ON employee_resume.id = employee_application.id") or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["products"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $product = array();
        // $product["vid"] = $row["vid"];
        $product["pid"] = $row["pid"];
        $product["id"] = $row["id"];
        $product["pic"] = $row["pic"];
        $product["name"] = $row["name"];
        $product["phone"] = $row["phone"];
        
        $product["email"] = $row["email"];
        $product["address"] = $row["address"];
        $product["course"] = $row["course"];
        $product["school"] = $row["school"];
        $product["CGPA"] = $row["CGPA"];
        $product["year"] = $row["year"];
        $product["org"] = $row["org"];
        $product["des"] = $row["des"];
        $product["dur1"] = $row["dur1"];
        $product["dur2"] = $row["dur2"];
        $product["employ"] = $row["employ"];
        $product["workrole"] = $row["workrole"];
        $product["in1"] = $row["in1"];
        $product["in2"] = $row["in2"];
        $product["in3"] = $row["in3"];
        $product["skill"] = $row["skill"];
        $product["strength"] = $row["strength"];
        $product["ptitle"] = $row["ptitle"];
        $product["pdesc"] = $row["pdesc"];
        $product["pdate1"] = $row["pdate1"];
        $product["pdate2"] = $row["pdate2"];
        $product["prole"] = $row["prole"];
        $product["otitle"] = $row["otitle"];
        $product["odesc"] = $row["odesc"];
        $product["rname"] = $row["rname"];
        $product["rdes"] = $row["rdes"];
        $product["rorg"] = $row["rorg"];
        $product["rphone"] = $row["rphone"];
        $product["remail"] = $row["remail"];
       



        // push single product into final response array
        array_push($response["products"], $product);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";

    // echo no users JSON
    echo json_encode($response);
}
?>

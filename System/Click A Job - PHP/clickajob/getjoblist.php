<?php

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DbConnect();

// get all products from products table
$result = mysql_query("SELECT * FROM vacancy") or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["jobss"] = array();

    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $jobss = array();
        $jobss["vid"] = $row["vid"];
        $jobss["cname"] = $row["cname"];
        $jobss["cphone"] = $row["cphone"];
        $jobss["cemail"] = $row["cemail"];
        $jobss["jobtitle"] = $row["jobtitle"];
        $jobss["jlocation"] = $row["jlocation"];
        $jobss["jsalary"] = $row["jsalary"];
        $jobss["jdate"] = $row["jdate"];
        $jobss["jreq"] = $row["jreq"];
        $jobss["jdesc"] = $row["jdesc"];
       // $product["created_at"] = $row["created_at"];
       // $product["updated_at"] = $row["updated_at"];

        // push single product into final response array
        array_push($response["jobss"], $jobss);
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
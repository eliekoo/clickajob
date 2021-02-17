<?php
// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DbConnect();

// check for post data
if (isset($_GET["pid"])) {
    $pid = $_GET['pid'];

    // get a resume from resume/product table
    $result = mysql_query("SELECT * FROM employee_resume WHERE pid = $pid");
    
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $product = array();

            $product["pid"] = $result["pid"];
            $product["id"] = $result["id"];
            $product["pic"] = $result["pic"];
            $product["name"] = $result["name"];
            $product["phone"] = $result["phone"];
            $product["email"] = $result["email"];
            $product["address"] = $result["address"];
            $product["course"] = $result["course"];
            $product["school"] = $result["school"];
            $product["CGPA"] = $result["CGPA"];
            $product["year"] = $result["year"];
            $product["org"] = $result["org"];
            $product["des"] = $result["des"];
            $product["dur1"] = $result["dur1"];
            $product["dur2"] = $result["dur2"];
            $product["employ"] = $result["employ"];
            $product["workrole"] = $result["workrole"];
            $product["in1"] = $result["in1"];
            $product["in2"] = $result["in2"];
            $product["in3"] = $result["in3"];
            $product["skill"] = $result["skill"];
            $product["strength"] = $result["strength"];
            $product["ptitle"] = $result["ptitle"];
            $product["pdesc"] = $result["pdesc"];
            $product["pdate1"] = $result["pdate1"];
            $product["pdate2"] = $result["pdate2"];
            $product["prole"] = $result["prole"];
            $product["otitle"] = $result["otitle"];
            $product["odesc"] = $result["odesc"];
            $product["rname"] = $result["rname"];
            $product["rdes"] = $result["rdes"];
            $product["rorg"] = $result["rorg"];
            $product["rphone"] = $result["rphone"];
             $product["remail"] = $result["remail"];

            // success
            $response["success"] = 1;

            // user node
            $response["product"] = array();

            array_push($response["product"], $product);

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No product found";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";

        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>
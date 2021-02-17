<?php
define('HOST','localhost');
  define('USER','root');
  define('PASS','');
  define('DB','clickajob');
  $con = mysqli_connect(HOST,USER,PASS,DB);

$cname = $_POST['cname'];
$cphone = $_POST['cphone'];
$cemail = $_POST['cemail'];
$caddress = $_POST['caddress'];
$cid = $_POST['cid'];
$cpassword = $_POST['cpassword'];

$sql = "insert into employer (cname,cphone,cemail,caddress,cid, cpsw) values ('$cname','$cphone','$cemail','$caddress','$cid','$cpassword')";
  if(mysqli_query($con,$sql)){

    echo 'success';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>
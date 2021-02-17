<?php
define('HOST','localhost');
  define('USER','root');
  define('PASS','');
  define('DB','clickajob');
  $con = mysqli_connect(HOST,USER,PASS,DB);

$name = $_POST['name'];
$phone = $_POST['phone'];
$email = $_POST['email'];
$id = $_POST['id'];
$password = $_POST['password'];


$sql = "insert into employee (name,phone,email,id, password) values 
('$name','$phone','$email','$id','$password')";

  if(mysqli_query($con,$sql)){
    echo 'success';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>
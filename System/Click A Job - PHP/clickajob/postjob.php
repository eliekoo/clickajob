<?php
define('HOST','localhost');
  define('USER','root');
  define('PASS','');
  define('DB','clickajob');
  $con = mysqli_connect(HOST,USER,PASS,DB);

$cid = $_POST['cid'];
$cname = $_POST['cname'];
$cphone = $_POST['cpnum'];
$cemail = $_POST['cemail'];
$jlocation = $_POST['jlocation'];
$jobtitle = $_POST['jtitle'];
$jreq = $_POST['jreq'];
$jdesc = $_POST['jdesc'];
$jdate = $_POST['jdate'];
$jsalary = $_POST['jsalary'];


$sql = "insert into vacancy ( cid, cname,cphone,cemail,jlocation, jobtitle, 
	jreq, jdesc, jdate ,jsalary) values 
('$cid','$cname','$cphone','$cemail','$jlocation','$jobtitle','$jreq','$jdesc','$jdate', '$jsalary')";

  if(mysqli_query($con,$sql)){
    echo 'success';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>
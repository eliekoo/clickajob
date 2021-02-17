<?php
define('HOST','localhost');
  define('USER','root');
  define('PASS','');
  define('DB','clickajob');
  $con = mysqli_connect(HOST,USER,PASS,DB);

  
//pic, name, phone, email, address, course, school, CGPA, year ,org, des ,
  //dur1 ,dur2,employ,workrole;
//in1,in2,in3 ,skill ,strength , ptitle ,pdesc ,pdate1, pdate2 ,prole, 
  //otitle,odesc ,rname ,rdes ,rorg,rphone ,remail ;

$id = $_POST['id'];
$pic = $_POST['pic'];
$name = $_POST['name'];
$phone = $_POST['phone'];
$email = $_POST['email'];
$address = $_POST['address'];
$course = $_POST['course'];
$school = $_POST['school'];
$CGPA = $_POST['CGPA'];
$year = $_POST['year'];
$org = $_POST['org'];
$des = $_POST['des'];
$dur1 = $_POST['dur1'];
$dur2 = $_POST['dur2'];
$employ = $_POST['employ'];
$workrole = $_POST['workrole'];
$in1 = $_POST['in1'];
$in2 = $_POST['in2'];
$in3 = $_POST['in3'];
$skill = $_POST['skill'];
$strength = $_POST['strength'];
$ptitle = $_POST['ptitle'];
$pdesc = $_POST['pdesc'];
$pdate1 = $_POST['pdate1'];
$pdate2 = $_POST['pdate2'];
$prole = $_POST['prole'];
$otitle = $_POST['otitle'];
$odesc = $_POST['odesc'];
$rname = $_POST['rname'];
$rdes = $_POST['rdes'];
$rorg = $_POST['rorg'];
$rphone = $_POST['rphone'];
$remail = $_POST['remail'];



$sql = "insert into employee_resume (id,pic, name,phone,email,address, course, 
	school, CGPA, year ,org, des,dur1 ,dur2,employ,workrole, in1,in2,in3 , skill ,strength ,
	 ptitle ,pdesc ,pdate1, pdate2 ,prole, otitle,odesc ,rname ,rdes ,rorg,rphone ,remail) values 
('$id','$pic', '$name','$phone','$email','$address','$course','$school','$CGPA','$year', '$org',
	'$des','$dur1','$dur2','$employ','$workrole','$in1','$in2','$in3' ,'$skill','$strength','$ptitle','$pdesc',
	'$pdate1','$pdate2','$prole','$otitle', '$odesc','$rname','$rdes','$rorg','$rphone','$remail')";

  if(mysqli_query($con,$sql)){
    echo 'success';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>
 <?php  
 require "init.php";  
 $loginname = $_POST["loginname"];  
 $loginpass =  $_POST["loginpass"];  
 $sql_query = "select cname from employer where cid = '$loginname' and cpsw = '$loginpass';";  
 $result = mysqli_query($con,$sql_query);  
 if(mysqli_num_rows($result) >0 )  
 {  
 $row = mysqli_fetch_assoc($result);  
 $cname =$row["cname"];  
 echo "y";
 }  
 else  
 {   
 echo "n";  
 }  
 ?>  
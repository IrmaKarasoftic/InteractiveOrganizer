<?php
//error_reporting(0);
require "init.php";

$name=$POST["name"];
$lastname=$POST["lastname"];
$email=$POST["email"];
$brojac=1;

$sql= "INSERT INTO 'person' ('id','name','lastname','email') VALUES('".$brojac."', '".$name."','".$lastname."','".$email."');";

if(!mysqli_query($con,$sql)){
	echo '{"message":"Unable to save the data to db."}';
}
?>
<?php
class DbConnect {    
    private $conn;
    function __construct() {
        
        // connecting to database
        $this->connect();

    }    
    function __destruct() {
        
        $this->close();
    }
    function connect() {        
        require_once dirname(__DIR__) . '\clickajob\Config.php';
        $conn = mysql_connect(DB_HOST, DB_USERNAME, DB_PASSWORD) or die(mysql_error());
       // get host name, username and password from Config.php file
     
        $db = mysql_select_db(DB_NAME) or die(mysql_error()); // get database name from Config.php
        
        return $conn; // return connection resource
    }   
     // Close function   
    function close() {
        // close db connection
        mysql_close();
    }
}
?>


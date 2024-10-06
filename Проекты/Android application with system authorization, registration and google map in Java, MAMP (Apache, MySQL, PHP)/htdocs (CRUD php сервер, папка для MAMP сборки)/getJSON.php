<?php
ob_start();
$json = file_get_contents("php://input", TRUE);
if(isset($json)){
    //преобразовали json в объект
    $data = json_decode($json, false);
}
?>
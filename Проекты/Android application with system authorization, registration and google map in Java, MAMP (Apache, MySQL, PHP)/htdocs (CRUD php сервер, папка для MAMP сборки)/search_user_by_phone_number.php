<?php
    require 'getJSON.php';
    $phone_number = $data->{'phone_number'};

    require 'configDB.php';
    $sql = "SELECT * FROM `users` WHERE `phone_number` = '$phone_number'";

    $querty = $pdo->query($sql);

    $results = $querty->fetch(PDO::FETCH_OBJ);
    $json = json_encode($results, JSON_UNESCAPED_UNICODE);
    echo $json;
?>
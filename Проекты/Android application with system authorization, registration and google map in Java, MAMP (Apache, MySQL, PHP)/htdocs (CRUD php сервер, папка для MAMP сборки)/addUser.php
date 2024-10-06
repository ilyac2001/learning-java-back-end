<?php
    require 'configDB.php';
    $sql = 'INSERT INTO users(name, phone_number, password)
                    VALUES(:name, :phone_number, :password)';

    $query = $pdo->prepare($sql);
    $query->bindParam(':name', $name);
    $query->bindParam(':phone_number', $phone_number);
    $query->bindParam(':password', $password);

    require 'getJSON.php';
    $name = $data->{'name'};
    $phone_number = $data->{'phone_number'};
    $password = $data->{'password'};

    $query->execute();
?>
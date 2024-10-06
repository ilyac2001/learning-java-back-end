<?php

//подключились к БД
require 'configDB.php';

//создали sql команду (строку)
$sql = 'INSERT INTO users(name, phone_number)
                VALUES(:name, :phone_number)';
//подготавливаем всё к выполнению (команда и сами значения)
$query = $pdo->prepare($sql);
$query->bindParam(':name', $name);
$query->bindParam(':phone_number', $phone_number);

//выполняем команду
require 'getJSON.php';
$name = $data->{'name'};
$phone_number = $data->{'phone_number'};

//$name = "test name";
//$phone_number = "88007006050";
$query->execute();
//в данном случае каждый execute() добавляет одну строку
?>
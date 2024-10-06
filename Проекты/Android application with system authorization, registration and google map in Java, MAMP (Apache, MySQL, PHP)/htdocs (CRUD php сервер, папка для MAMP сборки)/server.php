<?php
require 'getJSON.php';
    //парсим, обращаемся к полям
    $name = $data->{'name'};
    $phone_number = $data->{'phone_number'};

//открыть соединение
    $mysql = new mysqli('localhost', 'root', 'root', 'database');

//операции, запросы к БД
    $mysql->query("INSERT INTO `users` (`name`, `phone_number`)
                        VALUES('$name', '$phone_number')");

//закрыть соединение
$mysql->close();

?>
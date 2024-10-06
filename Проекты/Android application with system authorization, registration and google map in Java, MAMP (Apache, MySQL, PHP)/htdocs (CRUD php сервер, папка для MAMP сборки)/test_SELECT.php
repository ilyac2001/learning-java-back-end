<?php
//подключились
require 'configDB.php';
//создали команду
$sql = "SELECT * FROM `users` WHERE `phone_number` = '111'";
//выполнили (тут ничего подготавливать не надо т.к. SELECT, запрос на получение)
$querty = $pdo->query($sql);
//получаем данные row - строка, каждую строку получаем как объект
while($row = $querty->fetch(PDO::FETCH_OBJ)){
    echo $row->name.'   '.$row->phone_number;
}


//$results = $querty->fetch(PDO::FETCH_OBJ);
//$json = json_encode($results);
//echo $json;
?>
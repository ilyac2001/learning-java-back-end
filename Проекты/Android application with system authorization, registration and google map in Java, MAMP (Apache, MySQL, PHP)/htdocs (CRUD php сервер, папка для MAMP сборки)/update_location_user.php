<?php
require 'configDB.php';

require 'getJSON.php';
$phone_number = $data->{'phone_number'};
$location = $data->{'location'};

$latitude = $data->{'latitude'};
$longitude = $data->{'longitude'};

$sql = "UPDATE `users` SET `latitude`='$latitude', `longitude`='$longitude' WHERE `phone_number`='$phone_number'";
$query = $pdo->query($sql);

$query->execute();
?>
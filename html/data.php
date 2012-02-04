<?php

$data = array(
  array(
    'id' => 1,
    'title' => 'Title here',
    'vendor' => 'Joe Vendor',
    'ts' => time() + rand()
  ),
  array(
    'id' => 2,
    'title' => 'Title here',
    'vendor' => 'Joe Vendor',
    'ts' => time() + rand()
  ),array(
    'id' => 3,
    'title' => 'Title here',
    'vendor' => 'Joe Vendor',
    'ts' => time() + rand()
  )
);


echo json_encode($data);

-- // create haversine 
DELIMITER //
 create function `haversine`(lat1 FLOAT, lat2 FLOAT, lng1 FLOAT, lng2 FLOAT)  RETURNS float LANGUAGE SQL DETERMINISTIC CONTAINS SQL SQL SECURITY INVOKER BEGIN RETURN 3959 * acos( cos( radians(lat1) ) * cos( radians( lat2 ) ) * cos( radians( lng2 ) - radians(lng1) ) + sin( radians(lat1) ) * sin( radians( lat2 ) ) ); END//
DELIMITER ;



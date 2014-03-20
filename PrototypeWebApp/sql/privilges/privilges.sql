CREATE USER 'jchardis'@'localhost' IDENTIFIED BY  'mytestpassword';

GRANT USAGE ON * . * TO  'infinityappsolut'@'localhost' IDENTIFIED BY  '***' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;

GRANT ALL PRIVILEGES ON  `infinityappsolutions` . * TO  'jchardis'@'localhost';
-- Create syntax for TABLE 'categories'
CREATE TABLE `categories` (
  `ctid` int(5) unsigned NOT NULL COMMENT 'CategoryTypeID',
  `did` int(10) unsigned NOT NULL COMMENT 'DealID',
  PRIMARY KEY  (`ctid`,`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'category_types'
CREATE TABLE `category_types` (
  `ctid` int(5) unsigned NOT NULL auto_increment COMMENT 'CategoryTypeID',
  `type` varchar(64) default NULL,
  PRIMARY KEY  (`ctid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'deal_by_location'
CREATE TABLE `deal_by_location` (
  `did` int(10) unsigned NOT NULL,
  `lid` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'deals'
CREATE TABLE `deals` (
  `did` int(10) unsigned NOT NULL auto_increment,
  `vendor` varchar(32) NOT NULL default '',
  `title` varchar(256) NOT NULL default '',
  `link_url` varchar(256) NOT NULL default '',
  `start_date` timestamp NULL default NULL,
  `end_date` timestamp NULL default NULL,
  `active` tinyint(1) NOT NULL default '1',
  `remaining_quantity` int(10) default '-999999999',
  `price` decimal(10,2) NOT NULL,
  `value` decimal(10,2) NOT NULL,
  `discount` decimal(4,4) NOT NULL,
  PRIMARY KEY  (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'groupon'
CREATE TABLE `groupon` (
  `did` int(10) unsigned NOT NULL,
  `groupon_id` varchar(128) NOT NULL,
  `small_image` varchar(128) default NULL COMMENT 'smallImageUrl',
  `med_image` varchar(128) default NULL COMMENT 'mediumImageUrl',
  `grid_image` varchar(128) default NULL COMMENT 'grid4ImageUrl',
  `pitch` varchar(1000) default NULL COMMENT 'pitchHTML',
  `highlights` varchar(1000) default NULL COMMENT 'highlightsHtml',
  `placement_priority` varchar(128) default NULL COMMENT 'placementPriority',
  `announcement` varchar(128) default NULL COMMENT 'announcementTitle',
  `location_note` varchar(128) default NULL COMMENT 'locationNote',
  `ship_req` tinyint(1) default NULL COMMENT 'shippingAddressRequired',
  `is_now_deal` tinyint(1) default NULL,
  `fine_print` varchar(128) default NULL,
  PRIMARY KEY  (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'locations'
CREATE TABLE `locations` (
  `lid` int(10) unsigned NOT NULL auto_increment,
  `street` varchar(64) default NULL,
  `street2` varchar(64) default NULL,
  `neighborhood` varchar(64) default NULL,
  `zip` varchar(10) default NULL,
  `lat` double default NULL,
  `lng` double default NULL,
  `name` varchar(128) default NULL,
  `url` varchar(256) default NULL,
  PRIMARY KEY  (`lid`),
  UNIQUE KEY `UniqueAddress` (`street`,`street2`,`zip`),
  UNIQUE KEY `Unique2` (`lat`,`lng`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'users'
CREATE TABLE `users` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `mail` varchar(64) NOT NULL default '',
  `created` int(11) NOT NULL default '0',
  `access` int(11) NOT NULL default '0',
  `status` tinyint(4) NOT NULL default '1',
  PRIMARY KEY  (`uid`),
  KEY `access` (`access`),
  KEY `created` (`created`),
  KEY `mail` (`mail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'yelp'
CREATE TABLE `yelp` (
  `yelp_id` varchar(64) NOT NULL default '',
  `name` varchar(64) default NULL,
  `rating` float default NULL,
  `reviews_count` int(11) default NULL,
  `url` varchar(128) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
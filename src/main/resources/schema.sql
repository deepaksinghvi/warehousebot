create table if not exists bot
(
   botId varchar(255) not null,
   currLoc varchar(255),
   intermediateLoc varchar(255),
   endLoc varchar(255),
   primary key(botId)
);

create table if not exists rfidtable
(
   rfId varchar(255) not null,
   locId varchar(255),
   primary key(rfId)
);
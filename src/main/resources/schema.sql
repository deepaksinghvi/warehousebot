create table if not exists bot
(
   botId varchar(255) not null,
   currLoc varchar(255),
   intermediateLoc varchar(255),
   endLoc varchar(255),
   primary key(botId)
);
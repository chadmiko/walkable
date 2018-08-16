# Walkable.me
Mobile-focused daily deal aggregator for "walkable" deals that grew out of a Networks class project as Univ Chicago CS Masters program.  Developed during the peak of the daily deals fad in 2012.

App automatically updated the UI as the user explored a neighborhood, showing hyper-local deals as the user walked around a city.  

Consists of an API scraper (/java) and web application (/walk_web).  

## /java (scrapers)
Fetched deal data from APIs of Groupon, LivingSocial, Yelp, and Gilt City then aggregated them in a DB

## /walk_web (web app)
Padrino Framework (Sinatra-based Ruby) and MySQL database, with Backbone.js UI.

*Unfortunately, due to collapse of the daily deals industry, no guarantee scrapers will work today.*

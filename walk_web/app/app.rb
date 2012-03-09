class WalkWeb < Padrino::Application
  register Padrino::Rendering
  register Padrino::Mailer
  register Padrino::Helpers
  register Padrino::Cache
  
  enable :caching  
  set :exceptions_subject, "Walkable"
  set :exceptions_from,    "Walkable App <exceptions@walkable.me>"
  set :exceptions_to,      "chadmiko@gmail.com"
  set :exceptions_page,    "base/errors"
  
  layout :application

end

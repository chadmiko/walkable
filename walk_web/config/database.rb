DataMapper.logger = logger
DataMapper::Property::String.length(255)
DataMapper::Model.raise_on_save_failure = true

case Padrino.env
  when :development then DataMapper.setup(:default, {
    :adapter => 'mysql',
    :username => 'java_walk',
    :port    => 3306,
    :host    => 'walkable.me',
    :database => "walk",
    :password => "daburgh"
  })
  when :production then DataMapper.setup(:default, {
    :adapter => 'mysql',
    :username => 'java_walk',
    :port    => 3306,
    :host    => 'walkable.me',
    :database => "walk",
    :password => "daburgh"
  })
  #when :production  then DataMapper.setup(:default, "mysql://@localhost/walk_web_production")
  when :test        then DataMapper.setup(:default, "mysql://root@localhost/walk_web_test")
end




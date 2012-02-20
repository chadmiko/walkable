#$:.<< File.dirname(__FILE__)

require 'sinatra'
require 'dm-core'
require 'dm-serializer'
require 'json'

module Walkable
	class Application < Sinatra::Base
		configure do
			set :public, File.join(File.dirname(__FILE__, '..', 'public')
			DataMapper::Logger.new(STDOUT, :debug)
			DataMapper::setup(:walk, "mysql://#{File.expand_path(File.join(File.dirname(__FILE__), '..', 'db', 'walkable.db'))}")
		end

		mime :json, "application/json"
		before { content_type :json }

		get '/deals' do
			Walkable::Deals.all.to_json
		end
	end
end

require 'models/deals'

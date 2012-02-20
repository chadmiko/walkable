require 'sinatra'
require 'haml'
require 'dm-core'
require 'dm-serializer'
require 'dm-timestamps'
require 'json'
require 'logger'
require 'helpers/partials'

### SETUP
configure :development do
	DataMapper::setup(:default, {  
		:adapter  => 'mysql',  
		:host     => 'lbe.midsure.com',  
		:username => 'java_walk',
		:password => 'daburgh',  
		:database => 'walk'})
	
	DataMapper::Logger.new(STDOUT, :debug)

	set :session_secret, 'Jump23'
end

configure :production do
	set :show_exceptions, false
	#set :session_secret, '$till3rS'
end

### MODELS

class Deals
	include DataMapper::Resource

    property :did, Serial
    property :vendor, String, :length => 0..32, :required => true
    property :title, String, :length => 0..255, :required => true
    property :link_url, String, :length => 0..128, :required => true
    property :start_date, DateTime
    property :end_date, DateTime
    property :active, Boolean
    property :remaining_quantity, Integer
    property :price, Decimal, :required => true
    property :discount, Decimal, :required => true
end

# call after defining all models
DataMapper.finalize

### ROUTES

get "/" do
	@title = "Walkable.me"
	haml :main
end

get "/deals" do
	@deals = Deals.all		
	haml :deals
end

get "/deals/:id" do
	if @deal = Deals.first(:did => "#{params[:id]}")
		haml :deal_item
	else 
		not_found
	end
end

not_found do 
	haml :'404'
end

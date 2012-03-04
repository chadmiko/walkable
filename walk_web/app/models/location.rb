class Location
  include DataMapper::Resource

  property :lid, Serial
  property :street, String, :length => 64
  property :street2, String, :length => 64
  property :zip, String, :length => 10
  property :lat, Float
  property :lng, Float
  property :name, String, :length => 128
  property :url, String, :length => 256

  has n, :deals, 'Deal', :child_key => [ :did ], :parent_key => [ :lid ], :through => :deal_locations 

end


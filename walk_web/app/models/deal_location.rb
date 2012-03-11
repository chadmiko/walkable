class DealLocation
  include DataMapper::Resource
  
  belongs_to :deal, :key => true,  :parent_key => [:did]
  belongs_to :location, :key => true, :parent_key => [:lid]
end



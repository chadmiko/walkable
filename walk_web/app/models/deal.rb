class Deal
  include DataMapper::Resource

  property :did, Serial
  property :vendor, String, :length => 32
  property :title, String
  property :link_url, String, :length => 128
  property :start_date, DateTime
  property :end_date, DateTime
  property :utc_offset, Integer    #UTC offset
  property :updated_at, DateTime

  validates_presence_of   :title
  validates_length_of     :vendor, :within => 1..32

  has n, :locations, :model => 'Location', :child_key => [:lid], :parent_key => [:did], :through => :deal_locations

  def self.locate_deals(lat, lon, radius = 5)
    lat = 41.9386909 if lat.nil?
    lon = -87.6412235 if lon.nil?
    radius = 5 if radius.nil?
    repository(:default).adapter.select('SELECT deals.*, locations.*, FORMAT(( 3959 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians( ? ) ) + sin( radians( ? ) ) * sin( radians( lat ) ) ) ), 2) AS distance FROM deals JOIN deal_locations on deals.did = deal_locations.did JOIN locations on deal_locations.lid = locations.lid HAVING distance < ? ORDER BY distance LIMIT 200', lat, lon, lat, radius.to_i)
  end

  #
  # B/c of naming conventions, can't use ORM baked rules
  # So this grabs our locations for a given deal
  #
  def self.fetch_locations(did)
    repository(:default).adapter.select('SELECT locations.* FROM locations JOIN deal_locations on locations.lid = deal_locations.lid WHERE deal_locations.did = ?', did)
  end
  
def has_multiple? 
    true
  end 
end

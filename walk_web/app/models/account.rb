class Account
  include DataMapper::Resource

  property :uid, Serial
  property :email, String
  property :created_at, DateTime
  property :updated_at, DateTime
  property :status, Integer
end

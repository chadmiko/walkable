WalkWeb.controllers :api do
  before do
    content_type "application/json"
  end

  get :deals, :map => '/api/deals/:market', :provides => [:json] do

    key = request.path_info + "?" + params.slice("lat","lon","radius").to_params
    @data = cache( key, :expires_in => 120) do
      struct = Deal.locate_deals(params[:lat], params[:lon], params[:radius])
      @deals = []
      struct.each do |s|
        @deals << Hash[s.each_pair.to_a]
      end       
      @deals.to_json
    end
    
    @data
  end

end

WalkWeb.controllers :deals do
  #  41.9386909, -87.6412235
  #  41.59789,   -88.02235  -- Homer
  get :index, :map => "/deals/:market"  do
    render 'deals/blank'
  end

  # Handle no-js using .html suffix?
  get :show_html, :map => "/deals/:market/:did.html"  do
    render 'deals/show' #, :layout => :application_html
  end
  
  get :show, :map => "/deals/:market/:did"  do
    render 'deals/blank'
  end
end

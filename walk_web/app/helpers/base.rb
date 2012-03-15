WalkWeb.helpers do
  def key_density(*words)
    words.compact.join(" ").concat(" - Walkable.me").gsub(/^ - /, '')
  end

  def title(*words)
    str = key_density(*words) if words.present?
    if pjax?
      "<title>#{str}</title>"
    else
      @title = str
      nil
    end
  end

  def pjax?
    env['HTTP_X_PJAX']
  end

  def vendors
    [      
      {:name => "groupon", :imgUrl => "/img/groupon.png", :height => "50", :width => "162",:active => false, :display_name => "Groupon"},
      {:name => "living_social", :imgUrl => "/img/living_social.png", :height => "50", :width => "114", :active => false, :display_name => "Living Social" },
      {:name => "yelp", :imgUrl => "http://media3.ak.yelpcdn.com/static/201012163983986833/img/developers/yelp_logo_100x50.png", :height => "50", :width => "100", :active => false, :display_name => "Yelp"},
      {:name => "gilt_city", :imgUrl => "/img/gilt_city.gif", :height => "31", :width => "88", :active => false, :display_name => "Gilt City" },
=begin
      {:name => "foursquare", :imgUrl => "http://placehold.it/100x50", :height => "50", :width => "100", :active => false, :display_name => "Gilt City" },
=end
    ].to_json
  end
end

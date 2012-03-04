WalkWeb.controllers :base do

  get :index, :map => "/" do
    redirect url(:deals, :index, :market => 'chicago')
    #render "base/index"
  end

end

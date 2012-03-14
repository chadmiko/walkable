WalkWeb.controllers :base do

  get :index, :map => "/" do
    redirect url(:deals, :show, :market => 'chicago')
    #render "base/index"
  end

end

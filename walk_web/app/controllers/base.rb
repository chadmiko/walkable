WalkWeb.controllers :base do

  get :index, :map => "/" do
    redirect url(:deals, :index, :market => 'chicago')
    #render "base/index"
  end

  get :hello do
    "hello!"
  end
end
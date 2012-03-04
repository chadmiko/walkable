ARGV.each do |a|
  next if a.to_s == 'tmp/bootstrap-js_plugin.rb'
  shell.say "Trying bootstrap-#{a.to_s}.js"
  get "https://raw.github.com/twitter/bootstrap/master/js/bootstrap-#{a.to_s}.js", destination_root("public/javascripts/bootstrap-#{a.to_s}.js")
end

shell.say "Done"

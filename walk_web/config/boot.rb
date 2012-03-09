# Defines our constants
PADRINO_ENV  = ENV["PADRINO_ENV"] ||= ENV["RACK_ENV"] ||= "development"  unless defined?(PADRINO_ENV)
PADRINO_ROOT = File.expand_path('../..', __FILE__) unless defined?(PADRINO_ROOT)

# Load our dependencies
require 'rubygems' unless defined?(Gem)
require 'bundler/setup'
Bundler.require(:default, PADRINO_ENV)

##
# Enable devel logging
#
# Padrino::Logger::Config[:development] = { :log_level => :devel, :stream => :stdout }
# Padrino::Logger.log_static = true
#

require 'padrino-contrib/exception_notifier'

##
# Add your before load hooks here
#
Padrino.before_load do
  Padrino.use Rack::Session::Cookie, :key => 'walk.sess'
end

##
# Add your after load hooks here
#
Padrino.after_load do
  #
  # Affects caching to file...This was a killer
  # @see:  https://github.com/padrino/padrino-framework/pull/724
  # semi-related: https://github.com/padrino/padrino-framework/issues/519
  # 
  Encoding.default_internal = nil
  Encoding.default_external = 'ASCII-8BIT'

  DataMapper.finalize
end

Padrino.load!

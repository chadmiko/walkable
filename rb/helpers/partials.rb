#require 'sinatra/base'
class Hash
  # https://github.com/rails/rails/blob/20768176292cbcb883ab152b4aa9ed8c664771cd/activesupport/lib/active_support/core_ext/hash/reverse_merge.rb#L12
	# Merges the caller into +other_hash+. For example,
  # 
  #   options = options.reverse_merge(:size => 25, :velocity => 10)
  #
  # is equivalent to
  #
  #   options = {:size => 25, :velocity => 10}.merge(options)
  #
  # This is particularly useful for initializing an options hash
  # with default values.
  def reverse_merge(other_hash)
    other_hash.merge(self)
  end

  # Destructive +reverse_merge+.
  def reverse_merge!(other_hash)
    # right wins if there is no left
    merge!( other_hash ){|key,left,right| left }
  end

  alias_method :reverse_update, :reverse_merge!
end

module Sinatra
	module PartialPartials
		def local_get(url)
			call(env.merge("PATH_INFO" => url)).last.join
		end
		
		##
		# Render a partials with collections support
		#
		# @param [String] template
		#   Relative path to partial template.
		# @param [Hash] options
		#   Options hash for rendering options.
		# @option options [Object] :object
		#   Object rendered in partial.
		# @option options [Array<Object>] :collection
		#   Partial is rendered for each object in this collection.
		# @option options [Hash] :locals ({})
		#   Local variables accessible in the partial.
		# @option options [Symbol] :engine
		#   Explicit rendering engine to use for this partial
		#
		# @return [String] The html generated from this partial.
		#
		# @example
		#   partial 'photo/item', :object => @photo
		#   partial 'photo/item', :collection => @photos
		#   partial 'photo/item', :locals => { :foo => :bar }
		#   partial 'photo/item', :engine => :erb
		#
		# @note If using this from Sinatra, pass explicit +:engine+ option
		#
		# @api public
		def partial(template, options={})
			options.reverse_merge!(:locals => {}, :layout => false)
			path            = template.to_s.split(File::SEPARATOR)
			object_name     = path[-1].to_sym
			path[-1]        = "_#{path[-1]}"
			explicit_engine = options.delete(:engine)
			template_path   = File.join(path).to_sym
			raise 'Partial collection specified but is nil' if options.has_key?(:collection) && options[:collection].nil?
			if collection = options.delete(:collection)
				options.delete(:object)
				counter = 0
				collection.map { |member|
					counter += 1
					options[:locals].merge!(object_name => member, "#{object_name}_counter".to_sym => counter)
					render(explicit_engine, template_path, options.dup)
				}.join("\n")
			else
				if member = options.delete(:object)
					options[:locals].merge!(object_name => member)
				end
				render(explicit_engine, template_path, options.dup)
			end
		end
=begin
		def partial( template, *args )
			template_array = template.to_s.split('/')
			template = template_array[0..-2].join('/') + "/_#{template_array[-1]}"
			options = args.last.is_a?(Hash) ? args.pop : {}
			options.merge!(:layout => false)
			if collection = options.delete(:collection) then
				collection.inject([]) do |buffer, member|
					buffer << haml(:"#{template}", options.merge(:layout =>
					false, :locals => {template_array[-1].to_sym => member}))
				end.join("\n")
			else
				haml(:"#{template}", options)
			end 
		end
=end		
	end
	helpers PartialPartials
end

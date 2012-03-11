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
end

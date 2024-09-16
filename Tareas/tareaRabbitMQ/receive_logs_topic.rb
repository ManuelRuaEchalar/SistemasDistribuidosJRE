#!/usr/bin/env ruby
require 'bunny'

# Verificar que se pase al menos una clave de enrutamiento
abort "Usage: #{$PROGRAM_NAME} [binding key]" if ARGV.empty?

# Iniciar conexión con RabbitMQ
connection = Bunny.new
connection.start

# Crear un canal y un exchange de tipo 'topic'
channel = connection.create_channel
exchange = channel.topic('topic_logs')

# Crear una cola exclusiva (anónima y temporal)
queue = channel.queue('', exclusive: true)

# Enlazar la cola al exchange usando las claves de enrutamiento (binding keys)
ARGV.each do |severity|
  queue.bind(exchange, routing_key: severity)
end

puts ' [*] Waiting for logs. To exit press CTRL+C'

# Suscribirse a la cola para recibir mensajes
begin
  queue.subscribe(block: true) do |delivery_info, _properties, body|
    puts " [x] #{delivery_info.routing_key}: #{body}"
  end
rescue Interrupt => _
  # Cerrar conexión si se interrumpe el proceso
  channel.close
  connection.close
  exit(0)
end

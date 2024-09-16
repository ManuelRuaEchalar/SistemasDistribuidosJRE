#!/usr/bin/env ruby
require 'bunny'

# Iniciar conexión con RabbitMQ
connection = Bunny.new
connection.start

# Crear un canal y un exchange de tipo 'topic'
channel = connection.create_channel
exchange = channel.topic('topic_logs')

# Leer la severidad de los argumentos, o usar 'anonymous.info' por defecto
severity = ARGV.shift || 'anonymous.info'

# Leer el mensaje, o usar 'Hello World!' por defecto
message = ARGV.empty? ? 'Hello World!' : ARGV.join(' ')

# Publicar el mensaje en el exchange con la routing key (severidad)
exchange.publish(message, routing_key: severity)
puts " [x] Sent #{severity}: #{message}"

# Cerrar la conexión
connection.close

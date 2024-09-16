require 'bunny'

begin
  connection = Bunny.new(host: '26.117.90.79', user: 'vladi', password: '12345')
  connection.start
  puts "Conexión establecida con RabbitMQ."
  connection.close
rescue Bunny::TCPConnectionFailed => e
  puts "No se pudo establecer conexión con RabbitMQ: #{e.message}"
rescue Bunny::AuthenticationError => e
  puts "Error de autenticación: #{e.message}"
rescue StandardError => e
  puts "Error inesperado: #{e.message}"
end
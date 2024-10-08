Imports System.ComponentModel
Imports MySql.Data.MySqlClient ' Cambiar a MySQL
Imports System.Web.Services

<System.Web.Services.WebService(Namespace:="http://tempuri.org/")>
<System.Web.Services.WebServiceBinding(ConformsTo:=WsiProfiles.BasicProfile1_1)>
<ToolboxItem(False)>
Public Class Segip
    Inherits WebService

    ' Cambiar la cadena de conexión para MySQL
    Private connectionString As String = "server=localhost;port=3306;uid=root;pwd='';database=personas;"

    <WebMethod()>
    Public Function BuscarPersonaCI(ByVal NumeroDocumento As String) As Persona
        Dim persona As Persona = Nothing

        ' Usar MySqlConnection en lugar de SqlConnection
        Using connection As New MySqlConnection(connectionString)
            connection.Open()

            Dim query As String = "SELECT * FROM Personas WHERE ci = @ci"
            Dim command As New MySqlCommand(query, connection) ' Cambiar a MySqlCommand
            command.Parameters.AddWithValue("@ci", NumeroDocumento)

            Using reader As MySqlDataReader = command.ExecuteReader() ' Cambiar a MySqlDataReader
                If reader.Read() Then
                    persona = New Persona() With {
                        .Id = Convert.ToInt32(reader("id")),
                        .CI = reader("ci").ToString(),
                        .Nombres = reader("nombres").ToString(),
                        .PrimerApellido = reader("primer_apellido").ToString(),
                        .SegundoApellido = reader("segundo_apellido").ToString()
                    }
                End If
            End Using
        End Using

        Return persona
    End Function

    <WebMethod()>
    Public Function BuscarPersonas(ByVal PrimerApellido As String, ByVal SegundoApellido As String, ByVal Nombres As String) As Persona()
        Dim personas As New List(Of Persona)()

        Using connection As New MySqlConnection(connectionString)
            connection.Open()

            Dim query As String = "SELECT * FROM Personas WHERE primer_apellido = @primerApellido AND segundo_apellido = @segundoApellido AND nombres = @nombres"
            Dim command As New MySqlCommand(query, connection) ' Cambiar a MySqlCommand
            command.Parameters.AddWithValue("@primerApellido", PrimerApellido)
            command.Parameters.AddWithValue("@segundoApellido", SegundoApellido)
            command.Parameters.AddWithValue("@nombres", Nombres)

            Using reader As MySqlDataReader = command.ExecuteReader() ' Cambiar a MySqlDataReader
                While reader.Read()
                    Dim persona As New Persona() With {
                        .Id = Convert.ToInt32(reader("id")),
                        .CI = reader("ci").ToString(),
                        .Nombres = reader("nombres").ToString(),
                        .PrimerApellido = reader("primer_apellido").ToString(),
                        .SegundoApellido = reader("segundo_apellido").ToString()
                    }

                    personas.Add(persona)
                End While
            End Using
        End Using

        Return personas.ToArray()
    End Function
End Class

Imports System.ComponentModel
Imports System.Web.Services
Imports System.Web.Services.Protocols

' Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente.
' <System.Web.Script.Services.ScriptService()> _
<System.Web.Services.WebService(Namespace:="http://tempuri.org/")> _
<System.Web.Services.WebServiceBinding(ConformsTo:=WsiProfiles.BasicProfile1_1)> _
<ToolboxItem(False)> _
Public Class ejercicio1
    Inherits System.Web.Services.WebService
    <WebMethod()>
    Public Function Sumar(ByVal a As Double, ByVal b As Double) As Double
        Return a + b
    End Function

    <WebMethod()>
    Public Function Restar(ByVal a As Double, ByVal b As Double) As Double
        Return a - b
    End Function

    <WebMethod()>
    Public Function Multiplicar(ByVal a As Double, ByVal b As Double) As Double
        Return a * b
    End Function

    <WebMethod()>
    Public Function Dividir(ByVal a As Double, ByVal b As Double) As Double
        If b <> 0 Then
            Return a / b
        Else
            Throw New ArgumentException("No se puede dividir entre cero.")
        End If
    End Function

End Class
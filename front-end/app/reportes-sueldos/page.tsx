import { Sidebar } from "@/components/sidebar"
import { TopBar } from "@/components/top-bar"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"

// Datos de ejemplo para la tabla
const sueldosData = [
  {
    rut: "12345678-9",
    nombre: "Pérez Soto, Juan Carlos",
    categoria: "Analista",
    anosServicio: 5,
    sueldoFijo: 1000000,
    bonificacionAnos: 50000,
    pagoHorasExtras: 100000,
    descuentos: 30000,
    sueldoBruto: 1120000,
    cotizacionPrevisional: 112000,
    cotizacionSalud: 89600,
    sueldoFinal: 918400
  },
  // Agrega más datos de ejemplo aquí...
]

export default function ReporteSueldos() {
  return (
    <div className="flex flex-col h-screen">
      <TopBar />
      <div className="flex flex-1 pt-16">
        <Sidebar />
        <main className="flex-1 overflow-y-auto p-6 bg-gray-100">
          <h1 className="text-2xl font-semibold text-gray-800 mb-4">Reporte de Sueldos</h1>
          
          <Card className="mb-6">
            <CardHeader>
              <CardTitle>Instrucciones</CardTitle>
              <CardDescription>
                Esta página visualiza el reporte de sueldos del mes calculado.
              </CardDescription>
            </CardHeader>
            <CardContent>
              <p>En esta tabla encontrará la siguiente información:</p>
              <ul className="list-disc list-inside mt-2">
                <li>Datos personales del empleado</li>
                <li>Información sobre su categoría y años de servicio</li>
                <li>Desglose de su sueldo, incluyendo bonificaciones y descuentos</li>
                <li>Cotizaciones previsionales y de salud</li>
                <li>Sueldo final</li>
              </ul>
            </CardContent>
          </Card>

          <Card>
            <CardHeader>
              <CardTitle>Tabla de Sueldos</CardTitle>
            </CardHeader>
            <CardContent>
              <div className="overflow-x-auto">
                <Table>
                  <TableHeader>
                    <TableRow>
                      <TableHead>RUT</TableHead>
                      <TableHead>Nombre Empleado</TableHead>
                      <TableHead>Categoría</TableHead>
                      <TableHead>Años de Servicio</TableHead>
                      <TableHead>Sueldo Fijo Mensual</TableHead>
                      <TableHead>Bonificación Años Servicio</TableHead>
                      <TableHead>Pago Horas Extras</TableHead>
                      <TableHead>Descuentos</TableHead>
                      <TableHead>Sueldo Bruto</TableHead>
                      <TableHead>Cotización Previsional</TableHead>
                      <TableHead>Cotización Salud</TableHead>
                      <TableHead>Sueldo Final</TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {sueldosData.map((empleado, index) => (
                      <TableRow key={index}>
                        <TableCell>{empleado.rut}</TableCell>
                        <TableCell>{empleado.nombre}</TableCell>
                        <TableCell>{empleado.categoria}</TableCell>
                        <TableCell>{empleado.anosServicio}</TableCell>
                        <TableCell>{empleado.sueldoFijo.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' })}</TableCell>
                        <TableCell>{empleado.bonificacionAnos.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' })}</TableCell>
                        <TableCell>{empleado.pagoHorasExtras.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' })}</TableCell>
                        <TableCell>{empleado.descuentos.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' })}</TableCell>
                        <TableCell>{empleado.sueldoBruto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' })}</TableCell>
                        <TableCell>{empleado.cotizacionPrevisional.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' })}</TableCell>
                        <TableCell>{empleado.cotizacionSalud.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' })}</TableCell>
                        <TableCell>{empleado.sueldoFinal.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' })}</TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </div>
            </CardContent>
          </Card>
        </main>
      </div>
    </div>
  )
}


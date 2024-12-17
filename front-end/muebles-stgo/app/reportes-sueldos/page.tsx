'use client'

import { useState, useEffect } from 'react'
import { Sidebar } from "@/components/sidebar"
import { TopBar } from "@/components/top-bar"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Alert, AlertDescription } from "@/components/ui/alert"

interface Sueldo {
  rutEmpleado: string;
  nombreEmpleado: string;
  categoria: string;
  añosServicio: number;
  sueldoFijoMensual: number;
  bonificacionAñosServicio: number;
  montoPagoHorasExtras: number;
  montoDescuentos: number;
  sueldoBruto: number;
  cotizacionPrevisional: number;
  cotizacionSalud: number;
  montoSueldoFinal: number;
}

export default function ReporteSueldos() {
  const [sueldos, setSueldos] = useState<Sueldo[]>([])
  const [isLoading, setIsLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    const fetchSueldos = async () => {
      setIsLoading(true)
      setError(null)
      try {
        const response = await fetch('http://localhost:8080/api/planilla/todos?mes=11&anio=2024')
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`)
        }
        const data = await response.json()
        setSueldos(data)
      } catch (error) {
        console.error('Error fetching sueldos:', error)
        setError(error instanceof Error ? error.message : 'An unexpected error occurred')
      } finally {
        setIsLoading(false)
      }
    }

    fetchSueldos()
  }, [])

  const formatCurrency = (value: number) => {
    return new Intl.NumberFormat('es-CL', { style: 'currency', currency: 'CLP' }).format(value)
  }

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
                {isLoading ? (
                    <p>Cargando datos de sueldos...</p>
                ) : error ? (
                    <Alert variant="destructive">
                      <AlertDescription>{error}</AlertDescription>
                    </Alert>
                ) : (
                    <div className="overflow-x-auto">
                      <Table>
                        <TableHeader>
                          <TableRow>
                            <TableHead className="font-bold text-black bg-gray-200">RUT</TableHead>
                            <TableHead className="font-bold text-black bg-gray-200">Nombre Empleado</TableHead>
                            <TableHead className="font-bold text-black bg-gray-200">Categoría</TableHead>
                            <TableHead className="font-bold text-black bg-gray-200">Años de Servicio</TableHead>
                            <TableHead className="font-bold text-black bg-gray-200">Sueldo Fijo Mensual</TableHead>
                            <TableHead className="font-bold text-black bg-gray-200">Bonificación Años Servicio</TableHead>
                            <TableHead className="font-bold text-black bg-gray-200">Pago Horas Extras</TableHead>
                            <TableHead className="font-bold text-black bg-gray-200">Descuentos</TableHead>
                            <TableHead className="font-bold text-black bg-gray-200">Sueldo Bruto</TableHead>
                            <TableHead className="font-bold text-black bg-gray-200">Cotización Previsional</TableHead>
                            <TableHead className="font-bold text-black bg-gray-200">Cotización Salud</TableHead>
                            <TableHead className="font-bold text-black bg-gray-200">Sueldo Final</TableHead>
                          </TableRow>
                        </TableHeader>
                        <TableBody>
                          {sueldos.map((sueldo) => (
                              <TableRow key={sueldo.rutEmpleado}>
                                <TableCell>{sueldo.rutEmpleado}</TableCell>
                                <TableCell>{sueldo.nombreEmpleado}</TableCell>
                                <TableCell>{sueldo.categoria}</TableCell>
                                <TableCell>{sueldo.añosServicio}</TableCell>
                                <TableCell>{formatCurrency(sueldo.sueldoFijoMensual)}</TableCell>
                                <TableCell>{formatCurrency(sueldo.bonificacionAñosServicio)}</TableCell>
                                <TableCell>{formatCurrency(sueldo.montoPagoHorasExtras)}</TableCell>
                                <TableCell>{formatCurrency(sueldo.montoDescuentos)}</TableCell>
                                <TableCell>{formatCurrency(sueldo.sueldoBruto)}</TableCell>
                                <TableCell>{formatCurrency(sueldo.cotizacionPrevisional)}</TableCell>
                                <TableCell>{formatCurrency(sueldo.cotizacionSalud)}</TableCell>
                                <TableCell>{formatCurrency(sueldo.montoSueldoFinal)}</TableCell>
                              </TableRow>
                          ))}
                        </TableBody>
                      </Table>
                    </div>
                )}
              </CardContent>
            </Card>
          </main>
        </div>
      </div>
  )
}


'use client'

import { useState } from 'react'
import { Sidebar } from "@/components/sidebar"
import { TopBar } from "@/components/top-bar"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Alert, AlertDescription } from "@/components/ui/alert"
import { Label } from "@/components/ui/label"
import Link from 'next/link'
import { useRouter } from 'next/navigation'

export default function CalculoPlanillas() {
  const [mes, setMes] = useState<string>('')
  const [estado, setEstado] = useState<'idle' | 'processing' | 'success' | 'error'>('idle')
  const [mensaje, setMensaje] = useState<string>('')
  const router = useRouter()

  const handleCalcular = () => {
    if (!mes) {
      setMensaje('Por favor, seleccione un mes antes de calcular.')
      return
    }

    setEstado('processing')
    setMensaje('')

    // Simulamos el proceso de cálculo
    setTimeout(() => {
      const exito = Math.random() > 0.2 // 80% de probabilidad de éxito
      if (exito) {
        setEstado('success')
        setMensaje(`Cálculo de planillas para ${mes} completado con éxito.`)
      } else {
        setEstado('error')
        setMensaje(`Error en el cálculo de planillas para ${mes}. Por favor, inténtelo de nuevo.`)
      }
    }, 3000)
  }

  return (
    <div className="flex flex-col h-screen">
      <TopBar />
      <div className="flex flex-1 pt-16">
        <Sidebar />
        <main className="flex-1 overflow-y-auto p-6 bg-gray-100">
          <h1 className="text-2xl font-semibold text-gray-800 mb-4">Cálculo de Planillas</h1>
          
          <Card className="mb-6">
            <CardHeader>
              <CardTitle>Instrucciones</CardTitle>
              <CardDescription>
                En esta sección podrá calcular las planillas de sueldos para el mes seleccionado.
              </CardDescription>
            </CardHeader>
            <CardContent>
              <p>Pasos para calcular las planillas:</p>
              <ol className="list-decimal list-inside mt-2">
                <li>Seleccione el mes para el cual desea calcular las planillas.</li>
                <li>Haga clic en el botón "Calcular Planillas" para iniciar el proceso.</li>
                <li>Espere a que el proceso finalice y revise el mensaje de estado.</li>
              </ol>
            </CardContent>
          </Card>

          <Card className="max-w-md mx-auto">
            <CardHeader>
              <CardTitle>Calcular Planillas</CardTitle>
            </CardHeader>
            <CardContent className="space-y-4">
              <div className="space-y-2">
                <Label htmlFor="mes">Mes a procesar</Label>
                <Select onValueChange={setMes}>
                  <SelectTrigger id="mes">
                    <SelectValue placeholder="Seleccione un mes" />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="enero">Enero</SelectItem>
                    <SelectItem value="febrero">Febrero</SelectItem>
                    <SelectItem value="marzo">Marzo</SelectItem>
                    <SelectItem value="abril">Abril</SelectItem>
                    <SelectItem value="mayo">Mayo</SelectItem>
                    <SelectItem value="junio">Junio</SelectItem>
                    <SelectItem value="julio">Julio</SelectItem>
                    <SelectItem value="agosto">Agosto</SelectItem>
                    <SelectItem value="septiembre">Septiembre</SelectItem>
                    <SelectItem value="octubre">Octubre</SelectItem>
                    <SelectItem value="noviembre">Noviembre</SelectItem>
                    <SelectItem value="diciembre">Diciembre</SelectItem>
                  </SelectContent>
                </Select>
              </div>
              
              <Button onClick={handleCalcular} className="w-full">
                Calcular Planillas
              </Button>

              {estado !== 'idle' && (
                <Alert>
                  <AlertDescription>
                    Estado: {estado === 'processing' ? 'Procesando' : estado === 'success' ? 'Completado' : 'Error'}
                    {mensaje && <p className="mt-2">{mensaje}</p>}
                    {estado === 'success' && (
                      <Button
                        onClick={() => router.push('/reportes-sueldos')}
                        className="mt-4"
                      >
                        Ver Reporte de Sueldos
                      </Button>
                    )}
                  </AlertDescription>
                </Alert>
              )}
            </CardContent>
          </Card>
        </main>
      </div>
    </div>
  )
}

